package com.example.assignment001;

public class ClassDetails {
    private String classTime;
    private String subjectCode;
    private String roomNumber;

    public ClassDetails(String classTime, String subjectCode, String roomNumber) {
        this.classTime = classTime;
        this.subjectCode = subjectCode;
        this.roomNumber = roomNumber;
    }

    public String getClassTime() {
        return classTime;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public String getRoomNumber() {
        return roomNumber;
    }
}
