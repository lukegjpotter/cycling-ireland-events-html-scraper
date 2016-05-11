package com.lukegjpotter.spring.application.parse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lukegjpotter.spring.application.model.RoadRaceEvent;
import com.lukegjpotter.spring.application.util.UtilsService;

@Component
public class HeaderParser implements Parsable<RoadRaceEvent> {
    
    @Autowired UtilsService utils;

    @Override public RoadRaceEvent parse(String htmlToParse) {
        
        RoadRaceEvent race = new RoadRaceEvent();
        
        Element roadRaceHeader = Jsoup.parseBodyFragment(htmlToParse).body();
        
        Elements tableData = roadRaceHeader.select("span");
        race.setStartDate(utils.convertDDMMMYYToDate(tableData.first().text()));
        
        tableData = roadRaceHeader.getElementsByTag("div");
        race.setEventName(utils.stringNullCheck(tableData.get(0).text()));
        race.setPromotingClub(utils.stringNullCheck(tableData.get(1).text()));
        race.setOrganiser(utils.stringNullCheck(tableData.get(2).text()));
        
        race.setRegistrationLink(""); // TODO Parse this from CI Website.
        
        return race;
    }
}
