package com.example.upload_ziguang.gb1400.util;



import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class CommenUtil {

    public static void cutFile(File oldFile, File newFile) {
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        byte[] bytes = new byte[1024];
        int temp = 0;
        try {
            inputStream = new FileInputStream(oldFile);
            fileOutputStream = new FileOutputStream(newFile);
            while ((temp = inputStream.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, temp);
                fileOutputStream.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Integer getIntegerByObject(Object object){
        Integer in = null;

        if(object!=null){
            try {
                if (object instanceof Integer) {
                    in = (Integer) object;
                } else if (object instanceof String && StringUtils.isNotBlank((String) object)) {
                    in = Integer.parseInt((String) object);
                } else if (object instanceof Double) {
                    in = (int) ((double) object);
                } else if (object instanceof Float) {
                    in = (int) ((float) object);
                } else if (object instanceof BigDecimal) {
                    in = ((BigDecimal) object).intValue();
                } else if (object instanceof Long) {
                    in = ((Long) object).intValue();
                }
            }
            catch (Exception e){
                return null;
            }
        }

        return in;
    }
    /**
     * Object转成指定的类型
     * @param obj
     * @param type
     * @param <T>
     * @return
     */
    public static<T> T convert(Object obj, Class<T> type) {
        if (obj != null && StringUtils.isNotBlank(obj.toString())) {
            if (type.equals(Integer.class)||type.equals(int.class)) {
                return (T)new Integer(obj.toString());
            } else if (type.equals(Long.class)||type.equals(long.class)) {
                return (T)new Long(obj.toString());
            } else if (type.equals(Boolean.class)||type.equals(boolean.class)) {
                return (T) new Boolean(obj.toString());
            } else if (type.equals(Short.class)||type.equals(short.class)) {
                return (T) new Short(obj.toString());
            } else if (type.equals(Float.class)||type.equals(float.class)) {
                return (T) new Float(obj.toString());
            } else if (type.equals(Double.class)||type.equals(double.class)) {
                return (T) new Double(obj.toString());
            } else if (type.equals(Byte.class)||type.equals(byte.class)) {
                return (T) new Byte(obj.toString());
            } else if (type.equals(Character.class)||type.equals(char.class)) {
                return (T)new Character(obj.toString().charAt(0));
            } else if (type.equals(String.class)) {
                return (T) obj;
            } else if (type.equals(BigDecimal.class)) {
                return (T) new BigDecimal(obj.toString());
            } else if (type.equals(LocalDateTime.class)) {
                //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                return (T) LocalDateTime.parse(obj.toString());
            } else if (type.equals(Date.class)) {
                try
                {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    return (T) formatter.parse(obj.toString());
                }
                catch (ParseException e)
                {
                    throw new RuntimeException(e.getMessage());
                }
            }else{
                return null;
            }
        } else {
            if (type.equals(int.class)) {
                return (T)new Integer(0);
            } else if (type.equals(long.class)) {
                return (T)new Long(0L);
            } else if (type.equals(boolean.class)) {
                return (T)new Boolean(false);
            } else if (type.equals(short.class)) {
                return (T)new Short("0");
            } else if (type.equals(float.class)) {
                return (T) new Float(0.0);
            } else if (type.equals(double.class)) {
                return (T) new Double(0.0);
            } else if (type.equals(byte.class)) {
                return (T) new Byte("0");
            } else if (type.equals(char.class)) {
                return (T) new Character('\u0000');
            }else {
                return null;
            }
        }
    }

    public static PropertiesConfiguration config;


    public static PropertiesConfiguration getConfig(String name) {
        try {
            PropertiesConfiguration config = new PropertiesConfiguration();
            config.setEncoding("utf-8");
            config.load(name);
            return config;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static PropertiesConfiguration getConfig() {
        try {
            config=new PropertiesConfiguration("config.properties");
            config.setEncoding("utf-8");
            config.setReloadingStrategy(new FileChangedReloadingStrategy());
            config.setAutoSave(true);
            return config;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static boolean setProperty(String key,String value){
        getConfig();
        config.setProperty(key,value);

        return false;
    }
}
