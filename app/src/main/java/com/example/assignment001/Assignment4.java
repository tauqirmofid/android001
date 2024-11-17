package com.example.assignment001;

import android.os.Bundle;
import android.widget.ExpandableListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Assignment4 extends AppCompatActivity {

    ExpandableListView expandableListView;
    ExpandableListAdapter adapter;
    List<String> listDataHeader;
    HashMap<String, List<ClassDetails>> listDataChild; // Updated to use ClassDetails objects

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment4);

        expandableListView = findViewById(R.id.routineExpandableListView);
        prepareRoutineData();

        adapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        expandableListView.setAdapter(adapter);
    }

    private void prepareRoutineData() {
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();

        // Adding headers
        listDataHeader.add("Saturday");
        listDataHeader.add("Sunday");
        listDataHeader.add("Monday");
        listDataHeader.add("Tuesday");
        listDataHeader.add("Wednesday");

        // Adding class details for each day using ClassDetails objects
        List<ClassDetails> saturday = new ArrayList<>();
        saturday.add(new ClassDetails("08:55-09:40 AM", "CSE-3232", "NL"));
        saturday.add(new ClassDetails("09:45-10:30 AM", "CSE-3232", "NL"));

        List<ClassDetails> sunday = new ArrayList<>();
        sunday.add(new ClassDetails("10:35-11:20 AM", "GED-2115", "RAB-111"));
        sunday.add(new ClassDetails("11:25-12:10 PM", "GED-2115", "RAB-111"));
        sunday.add(new ClassDetails("01:45-02:30 PM", "CSE-3212", "ACL-3"));
        sunday.add(new ClassDetails("02:30-03:15 PM", "CSE-3212", "ACL-3"));

        List<ClassDetails> monday = new ArrayList<>();
        monday.add(new ClassDetails("08:55-09:40 AM", "CSE-3231", "RAB-306"));
        monday.add(new ClassDetails("09:45-10:30 AM", "CSE-3231", "RAB-306"));
        monday.add(new ClassDetails("10:35-11:20 AM", "GED-1116", "RKB-402"));
        monday.add(new ClassDetails("11:25-12:10 PM", "GED-1116", "RKB-402"));
        monday.add(new ClassDetails("12:15-01:00 PM", "CSE-3213", "RKB-105"));
        monday.add(new ClassDetails("01:00-01:40 PM", "CSE-3213", "RKB-105"));
        monday.add(new ClassDetails("03:20-04:00 PM", "CSE-3232", "ACL-3"));

        List<ClassDetails> tuesday = new ArrayList<>();
        tuesday.add(new ClassDetails("08:55-09:40 AM", "CSE-3214", "ACL-2"));
        tuesday.add(new ClassDetails("09:45-10:30 AM", "CSE-3213", "G-2"));
        tuesday.add(new ClassDetails("10:35-11:20 AM", "CSE-3231", "RKB-304"));
        tuesday.add(new ClassDetails("12:15-01:00 PM", "CSE-3201", "RKB-304"));

        List<ClassDetails> wednesday = new ArrayList<>();
        wednesday.add(new ClassDetails("10:35-11:20 AM", "CSE-3202", "NL"));
        wednesday.add(new ClassDetails("12:15-01:00 PM", "CSE-3212", "ACL-2"));
        wednesday.add(new ClassDetails("01:45-02:30 PM", "CSE-3201", "RKB-106"));
        wednesday.add(new ClassDetails("02:30-03:15 PM", "CSE-3201", "RKB-106"));
        wednesday.add(new ClassDetails("03:20-04:00 PM", "CSE-3214", "ACL-2"));

        // Adding child data to the map
        listDataChild.put(listDataHeader.get(0), saturday);
        listDataChild.put(listDataHeader.get(1), sunday);
        listDataChild.put(listDataHeader.get(2), monday);
        listDataChild.put(listDataHeader.get(3), tuesday);
        listDataChild.put(listDataHeader.get(4), wednesday);
    }
}
