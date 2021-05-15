package com.example.form;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ConfirmActivity extends AppCompatActivity {
    TextView textView, jobView, ageView, descriptionView;
    ImageView imageView;

    private static final String TAG = ConfirmActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        //Get the bundle
        Bundle bundle = getIntent().getExtras();
        String stringDate = bundle.getString("date");
        String stringName = bundle.getString("name");
        String stringJob = bundle.getString("job");
        String stringDescription = bundle.getString("description");
        //make imageview
        imageView = findViewById(R.id.imageView);
        //make textview
        textView = findViewById(R.id.textView);
        //make job textview
        jobView = findViewById(R.id.job);
        //add input to jobView
        if(stringJob.equals("")){
            jobView.append("N/A");
        } else{
            jobView.setText(stringJob);
        }
        //make descriptionView
        descriptionView = findViewById(R.id.description);
        //add input to descriptionView
        descriptionView.append(" " +stringDescription);
        //make ageView
        ageView=findViewById(R.id.age);
        //make age variable
        int age = 0;
        //Get age
        try {
            Calendar birth = convertDate(stringDate);
            Calendar today = Calendar.getInstance();
            if(today.get(Calendar.DAY_OF_YEAR) < birth.get(Calendar.DAY_OF_YEAR)){
                age= today.get(Calendar.YEAR) - birth.get(Calendar.YEAR) - 1;
            }
            else{
                age= today.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //set age
        ageView.append(String.valueOf(age));
        //convert date
        try {
            Calendar date = convertDate(stringDate);
            //compare date
            if(checkAge(date)){
            textView.setText(" "+ stringName +" Thank you for signing up");
            }
            else{
                textView.append(" Please try again when you're older");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }



    }

    //turn date string into date object
    public Calendar convertDate(String stringDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        Calendar date = Calendar.getInstance();
        date.setTime(sdf.parse(stringDate));
        return date;
    }

    public boolean checkAge(Calendar date){
        Calendar today = Calendar.getInstance();
        int checkYear = today.get(Calendar.YEAR) -18;
        int yob = date.get(Calendar.YEAR);
        int mob = date.get(Calendar.MONTH);
        int dob = date.get(Calendar.DAY_OF_MONTH);
        if(date.after(today)){
            return false;
        }
        if(yob > checkYear){
            return false;
        } else if(mob > today.get(Calendar.MONTH)){
            return false;
        } else if(dob > today.get(Calendar.DAY_OF_MONTH)){
            return false;
        } else{
            return true;
        }

    }



    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy()");
    }
}