package com.disney.demo.mapper.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class String2LocalDate {
    
    public LocalDate string2LocalDate(String stringDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.parse(stringDate, formatter);
        return localDate;
    }
}
