package com.ggrec.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@Entity(name = "vdf_event_tag")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class VDFEventTag {

    private static final String DEFAULT_CATEGORY = "Miscellaneous";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 20)
    private String name;

    @Column(columnDefinition="VARCHAR(20) default '" + DEFAULT_CATEGORY + "'")
    private String category;

}
