package com.disney.demo.mapper.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDate2String {
    
    public String localDate2String(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String stringDate = localDate.format(formatter);
        return stringDate;
    }
    
}
