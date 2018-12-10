package com.example.abc.practice7;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.example.abc.practice7.Adapter.CustomStudentAdapter;

import com.example.abc.practice7.Model.Student;
import com.example.abc.practice7.Model.StudentDetail;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BaseJsonHttpResponseHandler;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class SelectActivity extends AppCompatActivity {

    CustomStudentAdapter customStudentAdapter;
    ArrayList<StudentDetail> arrayList;
    ListView lstStudent;
    GsonUtils gsonUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        gsonUtils = GsonUtils.getInstance();
        lstStudent = (ListView)findViewById(R.id.lstStudent);
        arrayList = new ArrayList<StudentDetail>();
        Webservice();
        customStudentAdapter = new CustomStudentAdapter(this, arrayList);

        lstStudent.setAdapter(customStudentAdapter);

    }


    ProgressDialog pDialog;

    public void Webservice ()
    {
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading... Please wait");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        AsyncHttpClient client = new AsyncHttpClient();
        try
        {
            client.setConnectTimeout(10000);
            client.post("http://www.squarangle.info/API/index.php/student/select", new BaseJsonHttpResponseHandler<Student>() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, Student response) {

                    pDialog.dismiss();
                    Toast.makeText(SelectActivity.this,"Success",Toast.LENGTH_LONG).show();
                    arrayList.addAll(response.getStudentDetail());
                    customStudentAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonData, Student errorResponse) {
                    pDialog.dismiss();
                    Toast.makeText(SelectActivity.this,"Fail",Toast.LENGTH_LONG).show();
                }

                @Override
                protected Student parseResponse(String rawJsonData, boolean isFailure) throws Throwable {
                    try {
                        if (!isFailure && !rawJsonData.isEmpty()) {
                            return gsonUtils.getGson().fromJson(rawJsonData, Student.class);
                        }
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(),"Not converted",Toast.LENGTH_SHORT);
                    }
                    return null;
                }
            });
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage().toString(),Toast.LENGTH_SHORT).show();

        }

    }

}
