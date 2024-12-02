package com.kevinkovach.aoc;

import static java.util.Map.entry;

import java.io.BufferedReader;
import java.util.Map;
import java.util.regex.Matcher;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Day1 {
  public static void main(String[] args) {
    InputStream is = Day1.class.getClassLoader().getResourceAsStream("day1-single.txt");
    Map<String, String> values = Map.ofEntries(
        entry("1", "1"),
        entry("2", "2"),
        entry("3", "3"),
        entry("4", "4"),
        entry("5", "5"),
        entry("6", "6"),
        entry("7", "7"),
        entry("8", "8"),
        entry("9", "9"),
        entry("one", "1"),
        entry("two", "2"),
        entry("three", "3"),
        entry("four", "4"),
        entry("five", "5"),
        entry("six", "6"),
        entry("seven", "7"),
        entry("eight", "8"),
        entry("nine", "9")
    );
    if(is == null)
      System.out.println("Input stream is null!!!");
    assert is != null;
    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    String line;
    int total = 0;
    while (true) {
      try {
        if ((line = reader.readLine()) == null) {
          break;
        } else {
          System.out.println("Line: " + line);
          Pattern first = Pattern.compile("(\\d|one|two|three|four|five|six|seven|eight|nine)");
//          Pattern last =
//              Pattern.compile(
//                  "(\\d|one|two|three|four|five|six|seven|eight|nine)(?!.*(\\d|one|two|three|four|five|six|seven|eight|nine))");
          Pattern last =
              Pattern.compile(
                  "(\\d)|(one)|(two)|(three)|(four)|(five)|(six)|(seven)|(eight)|(nine)(?!.*(\\d|one|two|three|four|five|six|seven|eight|nine))");
          Matcher firstMatcher = first.matcher(line);
          firstMatcher.find();
          int groups = firstMatcher.groupCount();
          System.out.println("Found " + groups + " groups!");
          String fd = firstMatcher.group(1);
          System.out.println(fd + "=" + values.get(fd));
//          System.out.println("Last: " + firstMatcher.group(groups));
          Matcher lastMatcher = last.matcher(line);
//          System.out.print(", ");
          lastMatcher.find();
          System.out.println("Groups: " + lastMatcher.groupCount());
          String ld = lastMatcher.group(lastMatcher.groupCount());
          System.out.println("LD: " + lastMatcher.end());
          System.out.println(ld + "=" + values.get(ld));
          String digits = values.get(fd) + values.get(ld);
          int num = Integer.parseInt(digits);
//          System.out.println("Num is " + num);
          total += num;
        }
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
    System.out.println("Total is " + total);
  }
}
