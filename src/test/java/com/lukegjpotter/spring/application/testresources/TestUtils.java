package com.lukegjpotter.spring.application.testresources;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestUtils {

    @Autowired TestResources tr;

    public String oneDayRaceHeaderRawHtml() {
        return readFile(tr.getOneDayRaceHeaderFileName());
    }

    public String oneDayRaceDescriptionRawHtml() {
        return readFile(tr.getOneDayRaceDescriptionFileName());
    }

    public String stageDetailRawHtml() {
        return readFile(tr.getOneDayRaceStageDetailFileName());
    }

    public String stageRaceHeaderRawHtml() {
        return readFile(tr.getStageRaceHeaderFileName());
    }

    public String stageRaceDescriptionRawHtml() {
        return readFile(tr.getStageRaceDescriptionFileName());
    }
    
    private String readFile(String raceFileName) {
        try {
            return new String(Files.readAllBytes(Paths.get(raceFileName))).replace("\n", "").replace("\t", "");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

}
