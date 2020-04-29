package com.ggrec.backend.data;

import com.ggrec.backend.domain.VDFEventTag;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class VDFEventTagData {
    private long id;
    private String name;
    private String category;

    public static VDFEventTagData fromDomain(VDFEventTag domain) {
        return new VDFEventTagData()
                .setId(domain.getId())
                .setName(domain.getName())
                .setCategory(domain.getCategory());
    }
}
