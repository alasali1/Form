package com.example.form;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    //turn date string into date object
    public Calendar convertDate(String stringDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        Calendar date = Calendar.getInstance();
        date.setTime(sdf.parse(stringDate));
        return date;
    }

    private boolean validateEmail(){
        String emailInput = email.getText().toString().trim();

        if(emailInput.isEmpty()){
            email.setError("Email can't be empty");
            return false;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            email.setError("Email invalid");
            return false;
        }else{
            email.setError(null);
            return true;
        }
    }

    private boolean validateName(){
        String nameInput = name.getText().toString().trim();

        if(nameInput.isEmpty()){
            name.setError("Name can't be empty");
            return false;
        }
        else {
            name.setError(null);
            return true;
        }
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
            showDate.setError("Too young");
            return false;
        } else if(mob > today.get(Calendar.MONTH)){
            showDate.setError("too young");
            return false;
        } else if(dob > today.get(Calendar.DAY_OF_MONTH)){
            showDate.setError("Too young");
            return false;
        } else if(showDate.toString().isEmpty()) {
            showDate.setError("Date cannot be empty");
            return false;
        }else
        {   showDate.setError(null);
            return true;
        }

    }

    public int getAge(String date){
        //make age variable
        int age = 0;
        //Get age
        try {
            Calendar birth = convertDate(date);
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
        return age;
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

            //Add action if age is <18
            String testDate = stringDate;
            try {
                if(!checkAge(convertDate(stringDate)) | !validateEmail() | !validateName()){
                    return;
                } else {

                    //check if email is valid

                    //add values to bundle
                    bundle.putString("name", stringName);
                    bundle.putString("email", stringEmail);
                    bundle.putString("date", stringDate);
                    bundle.putString("job", stringJob);
                    bundle.putString("description", stringDescription);
                    //start activity
                    confirmActivity.putExtras(bundle);
                    startActivity(confirmActivity);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}