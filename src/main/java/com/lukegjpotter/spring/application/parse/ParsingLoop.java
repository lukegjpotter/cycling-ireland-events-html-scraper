package com.lukegjpotter.spring.application.parse;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lukegjpotter.spring.application.model.RoadRaceEvent;

@Component
public class ParsingLoop {

    @Autowired private HeaderParser headerParser;
    @Autowired private DescriptionParser descriptionParser;
    @Autowired private StageDetailParser stageDetailParser;

    public List<RoadRaceEvent> startParseLoop(String fileLocation) {

        List<RoadRaceEvent> roadRaces = new ArrayList<>();

        // TODO Parse File Determine Lines/HTML Nodes to parse, do this in a loop.

        RoadRaceEvent roadRace = headerParser.parse("");
        roadRace.addDescription(descriptionParser.parse(""));
        roadRace.setStageDetails(stageDetailParser.parse(""));
        roadRaces.add(roadRace);

        return roadRaces;
    }
}