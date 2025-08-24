package com.example.BankService.logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Logger {
    private static int num = 1;
    private final static String LOG_ROUTE = "log.txt";
    public static void log(String msg) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(LOG_ROUTE, true))){
            bufferedWriter.write("#" + num + " " + LocalDateTime.now() + " "+ msg + "\n");
            num++;
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }
}
