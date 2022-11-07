package com.ybaspinar.airtiesgraduationproject;

import java.io.File;
import java.io.FileWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Logger {
    public static final SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static void logToFile(String message) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        FileWriter fw = null;
        try {
            fw = new FileWriter("./logs/log.txt", true);
            fw.write(date.format(timestamp)+ "    " + message + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void createLogFile() {
        File file = new File("./logs/log.txt");
        File folder = new File("./logs/");
        if (!folder.exists()) {
            folder.mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
