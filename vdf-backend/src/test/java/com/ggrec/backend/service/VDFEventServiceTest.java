package com.ggrec.backend.service;

import com.ggrec.backend.data.InstantSearchReqData;
import com.ggrec.backend.data.VDFEventData;
import com.ggrec.backend.domain.VDFEvent;
import com.ggrec.backend.domain.VDFEventTag;
import com.ggrec.backend.repository.VDFEventRepository;
import com.ggrec.backend.repository.VDFEventTagRepository;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VDFEventServiceTest {

    @Mock private VDFEventRepository vdfEventRepository;
    @Mock private VDFEventTagRepository vdfEventTagRepository;
    @Mock private AWSClient awsClient;

    private VDFEventService vdfEventService;

    private ImmutableList<VDFEvent> events;

    @Before
    public void init() {
        vdfEventService = new VDFEventService(vdfEventRepository, vdfEventTagRepository, awsClient);
    }

    @Test
    public void search() {
        VDFEvent event1 = new VDFEvent()
                .setId(1)
                .setName("Paltinis XCO")
                .setTags(ImmutableSet.of(new VDFEventTag().setName("XCO").setCategory("MTB")));
        VDFEvent event2 = new VDFEvent()
                .setId(2)
                .setName("Paltinis Maraton")
                .setTags(ImmutableSet.of(new VDFEventTag().setName("XCM").setCategory("MTB")));
        VDFEvent event3 = new VDFEvent()
                .setId(3)
                .setName("Cluj Cup")
                .setTags(ImmutableSet.of(new VDFEventTag().setName("Criterium").setCategory("Sosea")));

        when(vdfEventRepository.findAll()).thenReturn(ImmutableList.of(event1, event2, event3));

        InstantSearchReqData data = new InstantSearchReqData();
        data.setFacetFilters(ImmutableList.of(ImmutableList.of("discipline:XCO")));

        assertThat(vdfEventService.search(data)).containsExactly(VDFEventData.fromDomain(event1));
    }

}
