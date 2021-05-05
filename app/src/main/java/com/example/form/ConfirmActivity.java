package com.example.form;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ConfirmActivity extends AppCompatActivity {
    TextView textView;

    private static final String TAG = ConfirmActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        //Get the bundle
        Bundle bundle = getIntent().getExtras();
        String stringDate = bundle.getString("date");
        String stringName = bundle.getString("name");
        //make textview
        textView = findViewById(R.id.textView);
        //convert date
        try {
            Calendar date = convertDate(stringDate);
            //compare date
            if(checkAge(date)){
            textView.append(" "+ stringName +" Thank you for signing up");
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