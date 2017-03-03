package com.lukegjpotter.spring.application.parse.y2017;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lukegjpotter.spring.application.model.StageDetail;
import com.lukegjpotter.spring.application.parse.Parsable;
import com.lukegjpotter.spring.application.util.NullCheckUtilsService;
import com.lukegjpotter.spring.application.util.UtilsService;

@Component
class StageDetailsParser implements Parsable<Element, List<StageDetail>> {
    
    @Autowired private UtilsService utils;
    @Autowired private NullCheckUtilsService nullCheckUtils;

    @Override public List<StageDetail> parse(Element htmlToParse) {
        
        // TODO Populate Location In a New Way.
        String location = htmlToParse.getElementsByAttributeValueContaining("onclick", "showEventMapByAddress").first().text().trim();
        
        Elements tableRows = htmlToParse.getElementsByClass("trCourseItem");
        
        List<StageDetail> stageDetails = new ArrayList<>();
        StageDetail stageDetail = null;
        
         // The first two rows of the table are not stages, starts from 2.
        for (int i = 2; i < tableRows.size(); i++) {
            
            Elements rowData = tableRows.get(i).getElementsByTag("td");
            
            stageDetail = new StageDetail();
            stageDetail.setLocation(location);
            stageDetail.setDate(utils.convertDDMMYYYYToDate(rowData.get(0).text()));
            stageDetail.setRaceNumber(nullCheckUtils.integerNullCheck(rowData.get(1).text()));
            stageDetail.setStageNumber(nullCheckUtils.integerNullCheck(rowData.get(2).text()));
            stageDetail.setRaceType(nullCheckUtils.stringNullCheck(rowData.get(3).text()));
            stageDetail.setKilometers(nullCheckUtils.doubleNullCheck(rowData.get(4).text()));
            stageDetail.setMiles(nullCheckUtils.doubleNullCheck(rowData.get(5).text()));
            stageDetail.setCategory(nullCheckUtils.stringNullCheck(rowData.get(6).text()));
            stageDetail.setSignOnTime(nullCheckUtils.timeNullCheck(rowData.get(7).text()));
            stageDetail.setStartTime(nullCheckUtils.timeNullCheck(rowData.get(8).text()));
            stageDetail.setRouteLinkUrl(parseRouteLinkUrl(rowData.get(9)));
            
            stageDetails.add(stageDetail);
        }
        
        return stageDetails;
    }
    
    private String parseRouteLinkUrl(Element link) {
        return nullCheckUtils.stringNullCheck(link.getElementsByTag("a").attr("onclick").replace("window.top.location = \"", "").replace("\"", "").trim());
    }

}