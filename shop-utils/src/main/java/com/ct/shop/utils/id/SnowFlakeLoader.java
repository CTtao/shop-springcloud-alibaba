package com.ct.shop.utils.id;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Tao
 * @description
 */
public class SnowFlakeLoader {
    public static final String DATA_CENTER_ID = "data.center.id";
    public static final String MACHINE_ID = "machine.id";

    private volatile static Properties instance;
    static {
        InputStream is = SnowFlakeLoader.class.getClassLoader().getResourceAsStream("snowflake/snowflake.properties");
        instance = new Properties();
        try {
            instance.load(is);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private static String getStringValue(String key){
        if (instance == null) return "";
        return instance.getProperty(key,"");
    }

    private static Long getLongValue(String key){
        String value = getStringValue(key);
        return (value == null || value.trim().isEmpty()) ? 0:Long.parseLong(value);
    }

    public static Long getDataCenterId(){
        return getLongValue(DATA_CENTER_ID);
    }

    public static Long getMachineId(){
        return getLongValue(MACHINE_ID);
    }
}
