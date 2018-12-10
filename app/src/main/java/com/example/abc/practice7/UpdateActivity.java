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

import com.google.gson.JsonObject;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

public class UpdateActivity extends AppCompatActivity {
    String Id="",Name="",City="";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Intent in = getIntent();

        Id =in.getStringExtra("Id");
        Name = in.getStringExtra("Name");
        City = in.getStringExtra("City");
        final EditText edtName = (EditText)findViewById(R.id.edtName);
        final EditText edtCity = (EditText)findViewById(R.id.edtCity);

        edtName.setText(Name);
        edtCity.setText(City);
        Button btnUpdate = (Button)findViewById(R.id.btnUpdate);
        Button btnDelete = (Button)findViewById(R.id.btnDelete);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Name = edtName.getText().toString();
                City = edtCity.getText().toString();
                Webservice();

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebservieDelete();

            }
        });

    }
    ProgressDialog pDialog;
    public void Webservice()
    {
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading... Please wait");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        AsyncHttpClient client = new AsyncHttpClient();

        RequestParams params1 = new RequestParams();

        params1.put("student_id", Id);
        params1.put("student_name", Name);
        params1.put("student_city", City);

        try
        {
            client.setConnectTimeout(10000);
            client.post("http://www.squarangle.info/API/index.php/student/update/",params1,new JsonHttpResponseHandler()
            {
                @Override
                public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response)
                {

                    pDialog.dismiss();
                    String message = response.optString("message");
                    String Success = response.optString("success");

                    Toast.makeText(UpdateActivity.this,message, Toast.LENGTH_SHORT).show();


                    Intent in = new Intent(UpdateActivity.this,SelectActivity.class);

                    startActivity(in);


                }
                @Override
                public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, Throwable throwable, JSONObject response)
                {
                    pDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "  Something Wrong ", Toast.LENGTH_SHORT).show();
                }
            });

        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage().toString(),Toast.LENGTH_LONG).show();

        }
    }
    public void WebservieDelete()
    {
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading Please Wait....");
            pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params1 = new RequestParams();
        params1.put("student_id",Id);

        try
        {
            client.setConnectTimeout(10000);
            client.post("http://www.squarangle.info/API/index.php/student/delete", params1, new JsonHttpResponseHandler() {

                public void onSuccess(int statuscode, cz.msebera.android.httpclient.Header[] headers,JSONObject response)
                {
                    pDialog.dismiss();
                    String message = response.optString("message");
                    String Success = response.optString("success");
                    Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                    Intent in = new Intent(UpdateActivity.this,SelectActivity.class);
                    startActivity(in);


                }

                @Override
                public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, Throwable throwable, JSONObject response) {
                    pDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "  Something Wrong ", Toast.LENGTH_SHORT).show();
                }


            });

        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage().toString(),Toast.LENGTH_LONG).show();
        }



    }
}
