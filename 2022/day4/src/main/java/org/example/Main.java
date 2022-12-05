package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.apache.commons.lang3.Range;

public class Main {
    public static void main(String[] args) {
        BufferedReader file = null;
        int overlaps = 0;
        int partialOverlap = 0;
        try {
            file = new BufferedReader(new FileReader("sections.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Exception opening file!");
        }
        try {
            String[] ranges = file.readLine().split(",");
            while (true) {
                try {
                    String[] ranges1 = ranges[0].split("-");
                    String[] ranges2 = ranges[1].split("-");
                    Range<Integer> range1 = Range.between(Integer.parseInt(ranges1[0]), Integer.parseInt(ranges1[1]));
                    Range<Integer> range2 = Range.between(Integer.parseInt(ranges2[0]), Integer.parseInt(ranges2[1]));
                    if(range1.containsRange(range2) || range2.containsRange(range1)) {
                        overlaps++;
                    }
                    if(range1.isOverlappedBy(range2) || range2.isOverlappedBy(range1)) {
                        partialOverlap++;
                    }
                    // read next line
                    ranges = file.readLine().split(",");
                } catch (Exception e) {
                    // Ignore
                    break;
                }
            }
            System.out.printf("%d completely overlapping sections!\n", overlaps);
            System.out.printf("%d partially overlapping sections!\n", partialOverlap);
        } catch (IOException e) {
            System.out.println("Exception reading file!");
        }
    }
}