package com.scheduler.objects;

import java.util.*;

public class ScheduleList {
    private Map<String, List<Course>> availableSections;

    public ScheduleList() {
        availableSections = new HashMap<>();
    }

    public void addClasses(String key, List<Course> classes) {
        if (availableSections.get(key) == null) {
            availableSections.put(key, classes);
        } else {
            availableSections.get(key).addAll(classes);
        }
    }

    public void addClass(String key, Course section) {
        List<Course> temp;
        // TODO: if there is a list at key then add the course
        // else make a new list add the course and map that list with key
        // Use 'temp' when needed.
    }
}
