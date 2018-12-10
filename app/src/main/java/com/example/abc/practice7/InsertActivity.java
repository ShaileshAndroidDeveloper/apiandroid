package com.example.abc.practice7;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.ArrayList;

public class InsertActivity extends AppCompatActivity {
    String Name = "";
    String City = "";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        final EditText edtName = (EditText)findViewById(R.id.edtName);
        final EditText edtCity = (EditText)findViewById(R.id.edtCity);
        Button btnInsert = (Button)findViewById(R.id.btnInsert);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Name = edtName.getText().toString();
                City = edtCity.getText().toString();
                Webservice();
            }
        });

    }
    ProgressDialog pDialog;
    public  void Webservice()
    {
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading Please Wait.........");
        pDialog.setIndeterminate(false);

        pDialog.setCancelable(false);
        pDialog.show();;

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params1 = new RequestParams();
        params1.put("student_name",Name);
        params1.put("student_city",City);

        try {
            client.setConnectTimeout(10000);

            client.post("http://www.squarangle.info/API/index.php/student/insert", params1, new JsonHttpResponseHandler() {

                @Override
                public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {

                    pDialog.dismiss();
                    String message = response.optString("message");
                    String Success = response.optString("success");

                    Toast.makeText(InsertActivity.this,message,Toast.LENGTH_SHORT).show();

                    Intent in = new Intent(InsertActivity.this,SelectActivity.class);

                    startActivity(in);

                }

                @Override
                public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, Throwable throwable, JSONObject response) {
                    pDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "  Something Wrong ", Toast.LENGTH_SHORT).show();
                }


            });
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(),e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }

    }




}
