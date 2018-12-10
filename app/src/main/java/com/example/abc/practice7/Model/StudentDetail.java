package com.example.abc.practice7.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudentDetail {

    @SerializedName("student_id")
    @Expose
    private String studentId;
    @SerializedName("student_name")
    @Expose
    private String studentName;
    @SerializedName("student_city")
    @Expose
    private String studentCity;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentCity() {
        return studentCity;
    }

    public void setStudentCity(String studentCity) {
        this.studentCity = studentCity;
    }

}
