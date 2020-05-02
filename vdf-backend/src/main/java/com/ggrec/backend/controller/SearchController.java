package com.ggrec.backend.controller;

import com.ggrec.backend.data.InstantSearchReqData;
import com.ggrec.backend.data.InstantSearchResponseData;
import com.ggrec.backend.service.VDFEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private VDFEventService vdfEventService;

    @PostMapping(consumes = "application/json")
    public InstantSearchResponseData search(@RequestBody InstantSearchReqData request) {
        return vdfEventService.search(request);
    }

}
