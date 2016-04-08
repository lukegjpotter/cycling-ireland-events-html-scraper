package com.lukegjpotter.spring.application.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lukegjpotter.spring.application.CyclingIrelandEventsHtmlScraperApplication;
import com.lukegjpotter.spring.application.model.RoadRaceEvent;
import com.lukegjpotter.spring.application.parse.ParsingLoop;
import com.lukegjpotter.spring.application.testresources.RoadRaceEventTestResources;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { CyclingIrelandEventsHtmlScraperApplication.class, HtmlParsingService.class })
public class HtmlParsingServiceTest {

    @Autowired
    private HtmlParsingService htmlParsingService;
    @Autowired
    private ParsingLoop parsingLoop;
    
    @Before
    public void setUp() {
        
        parsingLoop = mock(ParsingLoop.class);
    }

    @Test
    public void testParseOneDayRace() {
        htmlParsingService.setHtmlFileLocation(RoadRaceEventTestResources.ONE_DAY_RACE_FILE_LOCATION);
        List<RoadRaceEvent> expected = RoadRaceEventTestResources.getOneDayRaceList();
        when(parsingLoop.startParseLoop("")).thenReturn(expected);
        List<RoadRaceEvent> actual = htmlParsingService.parse();
        assertTrue(expected.equals(actual));
    }

    @Test
    @Ignore
    public void testParseStageRace() {
        htmlParsingService.setHtmlFileLocation(RoadRaceEventTestResources.STAGE_RACE_FILE_LOCATION);
        List<RoadRaceEvent> expected = RoadRaceEventTestResources.getStageRaceList();
        List<RoadRaceEvent> actual = htmlParsingService.parse();
        assertTrue(expected.equals(actual));
    }
}
