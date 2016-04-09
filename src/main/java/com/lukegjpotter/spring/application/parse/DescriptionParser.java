package com.lukegjpotter.spring.application.parse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lukegjpotter.spring.application.model.Description;
import com.lukegjpotter.spring.application.util.UtilsService;

@Component
public class DescriptionParser implements Parser<Description> {

    @Autowired UtilsService utils;
    
    @Override public Description parse(String htmlToParse) {
        
        Description description = new Description();
        
        Element body = Jsoup.parseBodyFragment(htmlToParse).body();
        Elements tableData = body.getElementsByClass("tdvalueItem");
        
        description.setBookingsOpenDate(utils.convertDDMMYYYYToDate(tableData.get(0).text().trim()));
        description.setBookingsCloseDate(utils.convertDDMMYYYYToDate(tableData.get(1).text().trim()));
        description.setOrganiserPhoneNumber("+353" + tableData.get(2).text().trim());
        description.setOrganiserEmail(tableData.get(3).text().trim());
        description.setLocation(tableData.get(4).text().trim());
        description.setProvince("Munster");
        
        return description;
    }

}