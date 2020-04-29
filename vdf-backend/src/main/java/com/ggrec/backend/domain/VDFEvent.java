package com.ggrec.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.Set;

@Data
@Accessors(chain = true)
@Entity(name = "vdf_event")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class VDFEvent implements Serializable {

    private static final String COVER_PHOTO_LINK_FORMAT = "https://cdn.varfdeforma.ro/event/{0}/cover.jpg";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private LocalDate dateStart;
    private LocalDate dateEnd;

    @Column(length = 1000)
    private String description;

    private String organizer;

    private String schedule;

    private String prizes;

    private String ageCategories;

    private String tracks;

    private String locationName;
    private String locationCoordinates;

    // Just carrying initial link from the client for now. Has to be removed at some point
    @Transient
    private String photoLink;

    private String registrationTax;

    private String registrationLink;

    private String technicalGuideLink;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<VDFEventTag> tags;

    public boolean doesPhotoLinkNeedUpdating() {
        // photoLink field carries the original URL from the client,
        // but as soon as this entity is saved, it will become empty
        // and only used for edit operations
        return getPhotoLink_Original() != null
                && !getPhotoLink_Original().trim().equals("")
                && !getPhotoLink().equals(getPhotoLink_Original());
    }

    public String getPhotoLink_Original() {
        return photoLink;
    }

    public String getPhotoLink() {
        return MessageFormat.format(COVER_PHOTO_LINK_FORMAT, getId());
    }

}
