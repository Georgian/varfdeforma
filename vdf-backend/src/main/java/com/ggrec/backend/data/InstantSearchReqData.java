package com.ggrec.backend.data;

import lombok.Data;

import java.util.List;

@Data
public class InstantSearchReqData {
    private String query;
    private Integer hitsPerPage;
    private String highlightPreTag;
    private String highlightPostTag;
    private List<String> facets;
    private String tagFilters;
    private List<Object> facetFilters;
}
