package com.ggrec.backend.controller;

import com.ggrec.backend.data.InstantSearchReqData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchController {

    @PostMapping(consumes = "application/json")
    public String search(@RequestBody InstantSearchReqData requests) {
        System.out.println(requests);
        return "test";
    }

}
