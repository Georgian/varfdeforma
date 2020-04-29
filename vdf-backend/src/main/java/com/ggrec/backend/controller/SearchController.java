package com.ggrec.backend.controller;

import com.ggrec.backend.data.InstantSearchReqData;
import com.ggrec.backend.data.InstantSearchResponseData;
import com.ggrec.backend.data.VDFEventData;
import com.ggrec.backend.data.VDFEventTagData;
import com.ggrec.backend.domain.VDFFacets;
import com.ggrec.backend.service.VDFEventService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private VDFEventService vdfEventService;

    @PostMapping(consumes = "application/json")
    public InstantSearchResponseData search(@RequestBody InstantSearchReqData request) {
        List<VDFEventData> hits = vdfEventService.search(request);
        Map<String, Map<String, Integer>> facets = buildFacets(hits);
        return new InstantSearchResponseData()
                .setFacets(facets)
                .setHits(hits);
    }

    private void incrementFacetCount(String facetKey, String facetValue, Map<String, Map<String, Integer>> facets) {
        facets.computeIfAbsent(facetKey, v -> Maps.newHashMap()).merge(facetValue, 1, Integer::sum);
    }

    private Map<String, Map<String, Integer>> buildFacets(List<VDFEventData> hits) {
        Map<String, Map<String, Integer>> facets = new HashMap<>();
        for (VDFEventData hit : hits) {
            for (VDFEventTagData tag : hit.getTags()) {
                String category = tag.getCategory();
                String tagName = tag.getName();

                if (category.equalsIgnoreCase(VDFFacets.miscellaneous.toString())) {
                    incrementFacetCount(VDFFacets.miscellaneous.toString(), tagName, facets);
                } else {
                    incrementFacetCount(VDFFacets.sport.toString(), category, facets);
                    incrementFacetCount(VDFFacets.discipline.toString(), tagName, facets);
                }
            }

            incrementFacetCount(VDFFacets.organizer.toString(), hit.getOrganizer(), facets);
        }
        return facets;
    }

}
