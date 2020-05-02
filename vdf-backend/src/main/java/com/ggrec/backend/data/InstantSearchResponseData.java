package com.ggrec.backend.data;

import com.google.common.collect.ImmutableMap;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;
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
            results.add(new InstantSearchResponseResultData().setFacets(ImmutableMap.of(
                    "sport",
                    ImmutableMap.of(
                            "MTB", 1,
                            "Sosea", 1
                    )
            )));
        }
        return  results.get(0);
    }

    public InstantSearchResponseData setHits(List<VDFEventData> hits) {
        firstResult().setHits(hits);
        return this;
    }

    public InstantSearchResponseData setFacets(Map<String, Map<String, Integer>> facets) {
        firstResult().setFacets(facets);
        return this;
    }

    @Accessors(chain = true)
    @Data
    private static class InstantSearchResponseResultData {
        private List<VDFEventData> hits;
        private Map<String, Map<String, Integer>> facets;
    }

}
