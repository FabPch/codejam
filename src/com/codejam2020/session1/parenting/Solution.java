package com.codejam2020.session1.parenting;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
public class Solution {
    static String IMPOSSIBLE = "IMPOSSIBLE";
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        // Number of test cases
        int t = Integer.parseInt(in.nextLine()); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            // Number of activities
            int activityNumber = Integer.parseInt(in.nextLine());

            int[] scheduleC = new int[60 * 24];
            int[] scheduleJ = new int[60 * 24];
            String res = "";

            // Iterate over schedule
            for (int j = 0; j < activityNumber; j++) {
                String inputs = in.nextLine().trim();
                if (res.equals(IMPOSSIBLE)) {
                    continue;
                }
                String[] inputTab = inputs.split("\\s+");
                int minSchedule = Integer.parseInt(inputTab[0]);
                int maxSchedule = Integer.parseInt(inputTab[1]);

                if (isScheduleFree(scheduleC, minSchedule, maxSchedule)) {
                    setSchedule(scheduleC, minSchedule, maxSchedule);
                    res = res.concat("C");
                } else if (isScheduleFree(scheduleJ, minSchedule, maxSchedule)) {
                    setSchedule(scheduleJ, minSchedule, maxSchedule);
                    res = res.concat("J");
                } else {
                    res = IMPOSSIBLE;
                }

            }
            System.out.println("Case #" + i + ": " + res);
        }
    }

    private static boolean isScheduleFree(int[] schedule, int min, int max) {
        for (int i = min; i < max; i++) {
            if (schedule[i] == 1) {
                return false;
            }
        }
        return true;
    }

    private static void setSchedule(int[] schedule, int min, int max) {
        for (int i = min; i < max; i++) {
            schedule[i] = 1;
        }
    }
}
