package com.ggrec.backend.service;

import org.junit.Test;

import java.io.IOException;

public class FRCCalendarScraperServiceTest {

    @Test
    public void scrapeTest() throws IOException {
        FRCCalendarScraperService service = new FRCCalendarScraperService();
        System.out.println(service.scrape());
    }

}
