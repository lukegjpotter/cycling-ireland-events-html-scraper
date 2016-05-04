package com.lukegjpotter.spring.application.service;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lukegjpotter.spring.application.CyclingIrelandEventsHtmlScraperApplication;
import com.lukegjpotter.spring.application.model.RaceTypesHolder;
import com.lukegjpotter.spring.application.model.StageDetail;
import com.lukegjpotter.spring.application.testresources.TestResources;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { CyclingIrelandEventsHtmlScraperApplication.class, StageDetailsRaceTypesService.class })
public class StageDetailsRaceTypesServiceTest {
    
    @Autowired StageDetailsRaceTypesService categoryService;
    @Autowired TestResources tr;

    @Test public void testDetermineCategoriesEmptyCategories() {
        RaceTypesHolder expected = new RaceTypesHolder();
        RaceTypesHolder actual = categoryService.determineCategories(new ArrayList<StageDetail>());
        assertTrue(expected.equals(actual));
    }

    @Test public void testDetermineCategoriesOneCategories() {
        RaceTypesHolder expected = new RaceTypesHolder();
        expected.setA1(true);
        
        List<StageDetail> stageDetails = new ArrayList<>();
        StageDetail stageDetail = new StageDetail();
        stageDetail.setRaceType("A1");
        stageDetails.add(stageDetail);
        
        RaceTypesHolder actual = categoryService.determineCategories(stageDetails);
        
        assertTrue(expected.equals(actual));
    }

    @Test public void testDetermineCategoriesMultipleCategories() {
        RaceTypesHolder expected = new RaceTypesHolder();
        expected.setA1(true);
        expected.setA2(true);
        expected.setA3(true);
        expected.setA4(true);
        expected.setWoman(true);
        expected.setYouth(true);
        
        RaceTypesHolder actual = categoryService.determineCategories(tr.getOneDayRaceStageDetails());
        assertTrue(expected.equals(actual));
    }

}