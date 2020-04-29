package com.ggrec.backend.data;

import com.ggrec.backend.domain.VDFEvent;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Accessors(chain = true)
@Data
public class VDFEventData {
    private long id;
    private String name;
    private String organizer;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private String description;
    private List<VDFEventTagData> tags;

    public static VDFEventData fromDomain(VDFEvent domain) {
        return new VDFEventData()
                .setId(domain.getId())
                .setName(domain.getName())
                .setOrganizer(domain.getOrganizer())
                .setDateStart(domain.getDateStart())
                .setDateEnd(domain.getDateEnd())
                .setDescription(domain.getDescription())
                .setTags(domain.getTags().stream().map(VDFEventTagData::fromDomain).collect(Collectors.toList()));
    }
}
