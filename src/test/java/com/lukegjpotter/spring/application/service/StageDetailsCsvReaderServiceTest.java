package com.lukegjpotter.spring.application.service;

import com.lukegjpotter.spring.application.model.StageRouteMappingHolder;
import com.lukegjpotter.spring.application.testresources.StageDetailsCsvReaderServiceTestResources;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class StageDetailsCsvReaderServiceTest {

    @Autowired
    private StageDetailsCsvReaderService csvReader;
    @Autowired
    private StageDetailsCsvReaderServiceTestResources tr;

    @Test(expected = FileNotFoundException.class) public void testReadStageRouteFromCsvFile_NoFile() {
        csvReader.setCsvFileLocation("fail");
        csvReader.readStageRouteFromCsvFile();
    }

    @Test public void testReadStageRouteFromCsvFile_2017FormatSingleStage() {
        csvReader.setCsvFileLocation(tr.getStagesCsvFileName2017());
        StageRouteMappingHolder expected = tr.get2017StageRouteMappingHolder();
        StageRouteMappingHolder actual = csvReader.readStageRouteFromCsvFile();

        assertTrue(expected.equals(actual));
    }

}
