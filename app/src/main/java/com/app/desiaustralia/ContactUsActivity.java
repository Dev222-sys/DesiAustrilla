package com.app.desiaustralia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.app.desiaustralia.model.ContactUsModel;
import com.app.desiaustralia.retrofit.RetrofitHelper;
import com.app.desiaustralia.utility.NetworkUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactUsActivity extends AppCompatActivity {

    private EditText nameEt, emailEt, subjectEt, messageEt;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        Toolbar toolbar = findViewById(R.id.toolbar);
        nameEt = findViewById(R.id.nameEt);
        emailEt = findViewById(R.id.emailEt);
        subjectEt = findViewById(R.id.subjectEt);
        messageEt = findViewById(R.id.messageEt);
        progressBar = findViewById(R.id.progressBar);

        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.contact_us_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:
                sendData();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void sendData() {
        if (nameEt.getText().toString().equalsIgnoreCase("")){
            nameEt.setError("Enter name");
            nameEt.requestFocus();
        }
        else if (emailEt.getText().toString().equalsIgnoreCase("")){
            emailEt.setError("Enter email");
            emailEt.requestFocus();
        }
        else if (subjectEt.getText().toString().equalsIgnoreCase("")){
            subjectEt.setError("Enter subject");
            subjectEt.requestFocus();
        }
        else if (messageEt.getText().toString().equalsIgnoreCase("")){
            messageEt.setError("Enter message");
            messageEt.requestFocus();
        }
        else {
            if (NetworkUtil.checkNetworkStatus(ContactUsActivity.this)) {
                progressBar.setVisibility(View.VISIBLE);
                RetrofitHelper.getInstance().contactUs(callback, nameEt.getText().toString().trim(),
                        emailEt.getText().toString().trim(), subjectEt.getText().toString().trim(), messageEt.getText().toString().trim());
            } else {
                Toast.makeText(ContactUsActivity.this, "" + getString(R.string.check_network), Toast.LENGTH_SHORT).show();
            }
        }
    }

    Callback<ContactUsModel> callback = new Callback<ContactUsModel>() {
        @Override
        public void onResponse(Call<ContactUsModel> call, Response<ContactUsModel> response) {
            progressBar.setVisibility(View.GONE);
            try {
                if (response.body().getStatus() == 200){
                    if (response.body().isSuccess()){
                        onBackPressed();
                        Toast.makeText(ContactUsActivity.this, "Submit successfully.", Toast.LENGTH_SHORT).show();
                    }
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(Call<ContactUsModel> call, Throwable t) {
            progressBar.setVisibility(View.GONE);
        }
    };
}