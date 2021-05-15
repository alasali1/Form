package com.example.form;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = SignUpActivity.class.getSimpleName();

    private Button submitButton;
    EditText name;
    EditText email;
    EditText job;
    EditText description;
    Button datePicker;
    TextView showDate;

    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        datePicker=findViewById(R.id.btn_date);
        datePicker.setOnClickListener(this);
        showDate=findViewById(R.id.showDate);
        submitButton = findViewById(R.id.goToSubmitActivity);
        submitButton.setOnClickListener(this);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        job=findViewById(R.id.job_input);
        description=findViewById(R.id.input_description);

        Log.i(TAG, "onCreate()");
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.i(TAG, "onStart()");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState()");

        if (savedInstanceState.containsKey("date")){
            showDate.setText(savedInstanceState.getString("date"));
        }

        if (savedInstanceState.containsKey("name")){
            name.setText(savedInstanceState.getString("name"));
        }

        if (savedInstanceState.containsKey("email")){
            email.setText(savedInstanceState.getString("email"));
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i(TAG, "onResume()");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.i(TAG, "onPause()");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);

        Log.i(TAG,"onSaveInstanceState()");
        outState.putString("date", showDate.getText().toString());
        outState.putString("name", name.getText().toString());
        outState.putString("email", email.getText().toString());
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.i(TAG, "onStop()");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_date){
            final Calendar cal = Calendar.getInstance();
            mYear = cal.get(Calendar.YEAR);
            mMonth = cal.get(Calendar.MONTH);
            mDay = cal.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                            showDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }

        if (v.getId() == R.id.goToSubmitActivity){
            Intent confirmActivity = new Intent(SignUpActivity.this, ConfirmActivity.class);
            //create new bundle
            Bundle bundle = new Bundle();
            //get String from editText
            String stringName = name.getText().toString();
            String stringEmail = email.getText().toString();
            String stringDate = showDate.getText().toString();
            String stringJob = job.getText().toString();
            String stringDescription = description.getText().toString();
            //add values to bundle
            bundle.putString("name",stringName);
            bundle.putString("email",stringEmail);
            bundle.putString("date",stringDate);
            bundle.putString("job",stringJob);
            bundle.putString("description",stringDescription);
            //start activity
            confirmActivity.putExtras(bundle);
            startActivity(confirmActivity);
        }
    }
}