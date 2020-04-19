package com.codejam2020.session1.parenting;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

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

            // Iterate over activity schedules
            Map<Integer, String> responses = new TreeMap<>();
            List<Schedule> sortedSchedules = readAndSortActivities(in, activityNumber);

            boolean impossible = false;
            for (Schedule schedule : sortedSchedules) {
                int minSchedule = schedule.minSchedule;
                int maxSchedule = schedule.maxSchedule;
                if (impossible) {
                    break;
                }
                if (isScheduleFree(scheduleC, minSchedule, maxSchedule)) {
                    setSchedule(scheduleC, minSchedule, maxSchedule);
                    responses.put(schedule.order, "C");
                } else if (isScheduleFree(scheduleJ, minSchedule, maxSchedule)) {
                    setSchedule(scheduleJ, minSchedule, maxSchedule);
                    responses.put(schedule.order, "J");
                } else {
                    responses.put(schedule.order, IMPOSSIBLE);
                    impossible = true;
                }
            }

            if (impossible) {
                res = IMPOSSIBLE;
            } else {
                for (String val : responses.values()) {
                    res = res.concat(val);
                }
            }

            System.out.println("Case #" + i + ": " + res);
        }
    }

    private static List<Schedule> readAndSortActivities(Scanner in, int activityNumber) {
        List<Schedule> schedules = new ArrayList<>();
        for (int j = 0; j < activityNumber; j++) {
            String inputs = in.nextLine().trim();
            String[] inputTab = inputs.split("\\s+");

            int minSchedule = Integer.parseInt(inputTab[0]);
            int maxSchedule = Integer.parseInt(inputTab[1]);
            schedules.add(new Schedule(minSchedule, maxSchedule, j));
        }

        Collections.sort(schedules);
        return schedules;
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

    static class Schedule implements Comparable<Schedule> {

        public int minSchedule;
        public int maxSchedule;
        public int order;

        @Override
        public int compareTo(Schedule o) {
            return minSchedule - o.minSchedule;
        }

        public Schedule(int minSchedule, int maxSchedule, int order) {
            this.minSchedule = minSchedule;
            this.maxSchedule = maxSchedule;
            this.order = order;
        }
    }
}
