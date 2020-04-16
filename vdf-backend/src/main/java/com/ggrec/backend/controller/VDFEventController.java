package com.ggrec.backend.controller;

import com.ggrec.backend.domain.VDFEvent;
import com.ggrec.backend.domain.VDFEventTag;
import com.ggrec.backend.service.VDFEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/event")
public class VDFEventController {

    private VDFEventService vdfEventService;

    @Autowired
    public VDFEventController(VDFEventService vdfEventService) {
        this.vdfEventService = vdfEventService;
    }

    @PermitAll
    @GetMapping
    public List<VDFEvent> getFutureEvents() {
        return vdfEventService.getFutureEvents();
    }

    @PermitAll
    @GetMapping("/tags")
    public List<VDFEventTag> getAllTags() { return vdfEventService.getAllTags(); }

    @GetMapping("/{id}")
    public VDFEvent getById(@PathVariable("id") Long id) {
        return vdfEventService.getById(id);
    }

    @RolesAllowed("ADMIN")
    @PostMapping
    public void add(@RequestBody VDFEvent vdfEvent) {
        try {
            vdfEventService.save(vdfEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RolesAllowed("ADMIN")
    @PutMapping("/{id}")
    public void save(@RequestBody VDFEvent vdfEvent) {
        try {
            vdfEventService.save(vdfEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @DeleteMapping(value = "/{id}")
//    public void delete(@PathVariable Long id) {
//        vdfEventService.delete(id);
//    }

}
