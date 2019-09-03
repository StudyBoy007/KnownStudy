package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Create by czq
 * time on 2019/9/2  10:22
 */
public class DateDefine {

    public static String getStringDate() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

    public static String getStringDate2(){
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }


}
