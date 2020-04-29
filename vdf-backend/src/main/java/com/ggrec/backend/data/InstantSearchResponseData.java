package com.ggrec.backend.data;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

@Data
@Accessors(chain = true)
public class InstantSearchResponseData {
    private List<VDFEventData> hits;
    private Map<String, Map<String, Integer>> facets;
}
