//package com.mjc.school.repository.utils;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.nio.charset.StandardCharsets;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//import java.util.Random;
//
//public class Utils {
//
//    private static Random RANDOM_INSTANCE;
//    private Utils() {
//    }
//
//    public static String getRandomContentByFilePath(String fileName) {
//        final Random random = getRandom();
//        final int numLines = 30;
//            final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//            final InputStream inputStream = classLoader.getResourceAsStream(fileName);
//            final InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
//            final BufferedReader reader = new BufferedReader(streamReader);
//            String contentFromFile;
//            contentFromFile = getFileContent(random, numLines, reader);
//            return contentFromFile;
//    }
//
//    private static String getFileContent(Random random, int numLines, BufferedReader reader)  {
//        String content;
//        try {
//            for (int randomLine = random.nextInt(numLines), lineNum = 0; (content = reader.readLine()) != null && lineNum != randomLine; ++lineNum) {
//            }
//            return content;
//        } catch (IOException ex) {
//            return "We have issue to read file";
//        }
//    }
//
//    private static Random getRandom() {
//        if(RANDOM_INSTANCE == null) {
//            RANDOM_INSTANCE = new Random();
//        }
//        return RANDOM_INSTANCE;
//    }
//
//
//    public static LocalDateTime getRandomDate() {
//        final Random random = getRandom();
//        final int endDay = 30;
//        final LocalDate day = LocalDate.now().plusDays(random.nextInt(endDay));
//        final int hour = random.nextInt(24);
//        final int minute = random.nextInt(60);
//        final int second = random.nextInt(60);
//        final LocalTime time = LocalTime.of(hour, minute, second);
//        return LocalDateTime.of(day, time);
//    }
//}
