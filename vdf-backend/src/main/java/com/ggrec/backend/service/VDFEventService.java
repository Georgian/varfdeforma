package com.ggrec.backend.service;

import com.ggrec.backend.data.InstantSearchReqData;
import com.ggrec.backend.data.VDFEventData;
import com.ggrec.backend.domain.VDFEvent;
import com.ggrec.backend.domain.VDFEventTag;
import com.ggrec.backend.domain.VDFFacets;
import com.ggrec.backend.repository.VDFEventRepository;
import com.ggrec.backend.repository.VDFEventTagRepository;
import com.nimbusds.oauth2.sdk.util.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class VDFEventService {

    private static final String EVENT_FOLDER = "event";
    private static final String COVER_PHOTO_FILENAME = "cover.jpg";

    private final VDFEventRepository vdfEventRepository;
    private final VDFEventTagRepository vdfEventTagRepository;
    private final AWSClient awsClient;

    @Autowired
    public VDFEventService(VDFEventRepository vdfEventRepository, VDFEventTagRepository vdfEventTagRepository, AWSClient awsClient) {
        this.vdfEventRepository = vdfEventRepository;
        this.vdfEventTagRepository = vdfEventTagRepository;
        this.awsClient = awsClient;
    }

    public void save(VDFEvent vdfEvent) throws IOException {
        VDFEvent savedEvent = vdfEventRepository.save(vdfEvent);

        if (vdfEvent.doesPhotoLinkNeedUpdating()) {
            String coverPhotoName = MessageFormat.format("{0}/{1}/{2}", EVENT_FOLDER, savedEvent.getId(), COVER_PHOTO_FILENAME);
            URL coverPhotoLink = new URL(vdfEvent.getPhotoLink_Original());
            awsClient.uploadFileFromURL(coverPhotoLink, coverPhotoName, "image/jpeg");
        }
    }

    public void delete(long id) {
        vdfEventRepository.deleteById(id);
    }

    public VDFEvent getById(long id) {
        return vdfEventRepository.getOne(id);
    }

    public List<VDFEvent> getAll() {
        return vdfEventRepository.findAll();
    }

    public List<VDFEventTag> getAllTags() {
        return vdfEventTagRepository.findAll();
    }

    public List<VDFEvent> getFutureEvents() {
        final LocalDate yesterday = LocalDate.now().minusDays(1);
        return getAll().stream()
                .filter(event -> event.getDateStart().isAfter(yesterday))
                .collect(Collectors.toList());
    }

    private boolean matchesFacetFilter(VDFEvent event, VDFEventTag tag, String facetFilter) {
        String[] facet = facetFilter.split(":");
        String facetKey = facet[0];
        String facetValue = facet[1];

        switch (VDFFacets.valueOf(facetKey.toLowerCase())) {
            case sport:
                return tag.getCategory().equalsIgnoreCase(facetValue);

            case discipline:
            case miscellaneous:
                return tag.getName().equalsIgnoreCase(facetValue);

            case organizer:
                return event.getOrganizer().equalsIgnoreCase(facetValue);

            default:
                throw new IllegalArgumentException("Unknown facet " + facetKey);
        }
    }

    private boolean matchesFacetFilters(VDFEvent event, VDFEventTag tag, List<Object> facetFilters) {
        return facetFilters.stream()
                .allMatch(facetFilter -> {
                    if (facetFilter instanceof List) {
                        return ((List) facetFilter).stream()
                                .anyMatch(ff -> matchesFacetFilter(event, tag, (String) ff));
                    } else {
                        return matchesFacetFilter(event, tag, (String) facetFilter);
                    }
                });
    }

    private boolean matchesFacetFilters(VDFEvent vdfEvent, List<Object> facetFilters) {
        return vdfEvent.getTags().stream()
                .anyMatch(tag -> matchesFacetFilters(vdfEvent, tag, facetFilters));
    }

    // TODO Properly implement querying, don't fetch all
    // TODO Maybe move the search in a separate service and cache results in reddis
    public List<VDFEventData> search(InstantSearchReqData request) {
        Stream<VDFEvent> results = vdfEventRepository.findAll().stream();

        String query = request.getQuery();
        if (StringUtils.isNotEmpty(query))
            results = results.filter(event -> event.getName().matches(query));

        List<Object> facetFilters = request.getFacetFilters();
        if (CollectionUtils.isNotEmpty(facetFilters))
            results = results.filter(event -> matchesFacetFilters(event, facetFilters));

        return results.map(VDFEventData::fromDomain).collect(Collectors.toList());
    }

}
