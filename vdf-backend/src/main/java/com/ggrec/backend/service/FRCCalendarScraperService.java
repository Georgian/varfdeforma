package com.ggrec.backend.service;

import lombok.Data;
import lombok.ToString;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class FRCCalendarScraperService {

    public List<ScrapeResult> scrape() throws IOException {
        Document doc = Jsoup.connect("https://www.federatiadeciclism.ro/calendar/").get();
        Elements tableElement = doc.getElementsByClass("row-hover");

        System.out.println( tableElement.get(0) );

         tableElement.get(0).children().stream()
                .map(row -> {
                    Elements columns = row.children();
                    ScrapeResult scrapeResult = new ScrapeResult();
                    scrapeResult.setDateStart(columns.get(0).text());
                    scrapeResult.setDateEnd(columns.get(1).text());
                    scrapeResult.setName(columns.get(2).text());
                    scrapeResult.setLocation(columns.get(3).text());
                    scrapeResult.setType(columns.get(5).text());
                    scrapeResult.setOrganizer(columns.get(6).text());
                    return scrapeResult;
                })
                .collect(Collectors.toList());

         return null;
    }

    @Data
    @ToString
    public static class ScrapeResult {
        private String dateStart;
        private String dateEnd;
        private String name;
        private String location;
        private String type;
        private String organizer;
    }

}
