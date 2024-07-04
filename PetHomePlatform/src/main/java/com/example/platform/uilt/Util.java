package com.example.platform.uilt;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Component
public class Util {
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static String uuid(){
        return UUID.randomUUID().toString();
    }

    public static  String initTime(Date date){
        return simpleDateFormat.format(date);
    }


}
