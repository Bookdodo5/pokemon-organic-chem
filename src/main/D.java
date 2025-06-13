package main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class D {
	
	private static final DateTimeFormatter FORMATTER =
	        DateTimeFormatter.ofPattern("ss.SSS");
	
    public static void d(String message) {
    	String time = LocalDateTime.now().format(FORMATTER);
        System.out.println("☠️「DEBUG " + time + "」>> " + message);
    }

    public static void d(Object... args) {
    	String time = LocalDateTime.now().format(FORMATTER);
        StringBuilder sb = new StringBuilder("☠️「DEBUG" + time + "」>> ");
        for (Object arg : args) sb.append(arg).append(" ");
        System.out.println(sb.toString());
    }
}
