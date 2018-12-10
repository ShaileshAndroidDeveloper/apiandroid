package com.example.abc.practice7.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Student {

    @SerializedName("student_detail")
    @Expose
    private List<StudentDetail> studentDetail = null;
    @SerializedName("success")
    @Expose
    private Integer success;

    public List<StudentDetail> getStudentDetail()
    {
        return studentDetail;
    }

    public void setStudentDetail(List<StudentDetail> studentDetail) {
        this.studentDetail = studentDetail;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }
}
