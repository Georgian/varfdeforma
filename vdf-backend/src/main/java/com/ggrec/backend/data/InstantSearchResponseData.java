package com.ggrec.backend.data;

import lombok.Data;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InstantSearchResponseData {
    @Getter
    private List<InstantSearchResponseResultData> results;

    private InstantSearchResponseResultData firstResult() {
        if (CollectionUtils.isEmpty(results)) {
            results = new ArrayList<>();
            results.add(new InstantSearchResponseResultData());
        }
        return results.get(0);
    }

    public InstantSearchResponseData setHits(List<VDFEventData> hits) {
        firstResult().setHits(hits);
        return this;
    }

    public InstantSearchResponseData setFacets(Map<String, Map<String, Integer>> facets) {
        firstResult().setFacets(facets);
        return this;
    }

    @Data
    private static class InstantSearchResponseResultData {
        private List<VDFEventData> hits;
        private Map<String, Map<String, Integer>> facets;
    }

}
