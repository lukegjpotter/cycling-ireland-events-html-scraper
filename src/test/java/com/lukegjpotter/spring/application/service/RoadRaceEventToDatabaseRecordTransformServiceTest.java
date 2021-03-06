package com.lukegjpotter.spring.application.service;

import com.lukegjpotter.spring.application.model.RaceTypesHolder;
import com.lukegjpotter.spring.application.model.RoadRaceEvent;
import com.lukegjpotter.spring.application.model.RoadRaceEventDatabaseRecord;
import com.lukegjpotter.spring.application.testresources.RoadRaceEventToDatabaseRecordTransformServiceTestResources;
import com.lukegjpotter.spring.application.util.UtilsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RoadRaceEventToDatabaseRecordTransformServiceTest {

    @InjectMocks
    private RoadRaceEventToDatabaseRecordTransformService transformService;
    @Mock
    private StageDetailsRaceTypesService raceTypeService;
    @Mock
    private UtilsService utils;

    @Autowired
    private RoadRaceEventToDatabaseRecordTransformServiceTestResources tr;

    @Before public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @SuppressWarnings("unchecked")
    @Test public void testTransformEmptyList() {
        when(raceTypeService.determineRaceTypes(any(List.class))).thenReturn(new RaceTypesHolder());

        List<RoadRaceEventDatabaseRecord> actual = transformService.transform(new ArrayList<>());

        assertTrue(new ArrayList<RoadRaceEventDatabaseRecord>().equals(actual));
    }

    @SuppressWarnings("unchecked")
    @Test public void testTransformOneItemList() {
        when(raceTypeService.determineRaceTypes(any(List.class))).thenReturn(tr.getOneDayRaceTypesHolder());

        List<RoadRaceEvent> roadRaces = tr.getOneDayRaceList();
        List<RoadRaceEventDatabaseRecord> expected = tr.getOneDayRaceDatabaseRecordList();
        List<RoadRaceEventDatabaseRecord> actual = transformService.transform(roadRaces);

        assertTrue(expected.equals(actual));
    }

    @SuppressWarnings("unchecked")
    @Test public void testTransformMultiItemList() {
        when(raceTypeService.determineRaceTypes(any(List.class))).thenReturn(tr.getOneDayRaceTypesHolder(), tr.getStageRaceTypesHolder());

        List<RoadRaceEvent> roadRaces = tr.getTwoRaceList();
        List<RoadRaceEventDatabaseRecord> expected = tr.getTwoRaceDatabaseRecordList();
        List<RoadRaceEventDatabaseRecord> actual = transformService.transform(roadRaces);

        assertTrue(expected.equals(actual));
    }

}
