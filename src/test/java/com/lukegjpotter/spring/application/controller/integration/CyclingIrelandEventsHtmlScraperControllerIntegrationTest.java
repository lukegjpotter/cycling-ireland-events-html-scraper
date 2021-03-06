package com.lukegjpotter.spring.application.controller.integration;

import com.lukegjpotter.spring.application.controller.CyclingIrelandEventsHtmlScraperController;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
@Ignore
public class CyclingIrelandEventsHtmlScraperControllerIntegrationTest {

    @Autowired
    private CyclingIrelandEventsHtmlScraperController controller;

    @Test public void testStart() {
        String actual = controller.start();
        String expected = "Success";
        assertTrue(actual.equals(expected));
        // TODO Add Database Checks here, add MockMvc Call.
    }
}
