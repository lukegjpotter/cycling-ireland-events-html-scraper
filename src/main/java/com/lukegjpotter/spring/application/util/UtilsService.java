package com.lukegjpotter.spring.application.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class UtilsService {
    
    @Autowired private NullCheckUtilsService nullCheckUtilsService;
    
    public Date convertMMMDDYYYYToDate(String date) {
        return convertDateUsingFormat(date, Constants.DATE_FORMAT_MMM_DD_YYYY);
    }

    public Date convertDDMMMYYYYToDate(String date) {
        return convertDateUsingFormat(date, Constants.DATE_FORMAT_DD_MMM_YYYY);
    }

    private Date convertDateUsingFormat(String date, String format) {
        try {
            return new SimpleDateFormat(format).parse(date.trim());
        } catch (ParseException e) {
            return new Date(0L);
        }
    }
    
    public boolean isListElementInString(String string, List<String> elements) {
        
        for (String element : elements) {
            if (string.contains(element))
                return true;
        }

        return false;
    }
    
    public String formatLocation(String location) {
        
        location = location.trim();

        if (nullCheckUtilsService.stringNullCheck(location).isEmpty()) return "";

        int i = 0;
        
        if (!location.isEmpty() && ((Character)location.charAt(i)).equals(',')) {
            
            try {
                for (; !Character.isLetter(location.charAt(i)); ++i) ;
            } catch (StringIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
                
            return location.substring(i);
        }
        
        return location;
    }
    
    /**
     * Uses a {@code Date} object to extract it's month number, January is 1, December 12.
     * 
     * @param date The date to extract the {@code monthNumber} from.
     * @return The {@code monthNumber} for the {@code Date}.
     */
    public int extractMonthNumberFromDate(Date date) {
        
        if (date == null) return -1;
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        
        return calendar.get(Calendar.MONTH) + 1;
    }
}
