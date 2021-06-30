package com.example.form;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.util.Calendar;


public class ProfileFragment extends Fragment {
    TextView textView, jobView, ageView, descriptionView;
    ImageView imageView;

    public ProfileFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        String stringDate = getArguments().getString("date");
        String stringName = getArguments().getString("name");
        String stringJob = getArguments().getString("job");
        String stringDescription = getArguments().getString("description");

        //make imageView
        imageView = v.findViewById(R.id.imageView);
        //make textview
        textView = v.findViewById(R.id.textView);
        //make job textview
        jobView = v.findViewById(R.id.job);
        //add input to jobView
        if(stringJob.equals("")){
            jobView.append("N/A");
        } else{
            jobView.setText(stringJob);
        }
        //make descriptionView
        descriptionView = v.findViewById(R.id.description);
        //add input to descriptionView
        descriptionView.append(" " +stringDescription);
        //make ageView
        ageView= v.findViewById(R.id.age);
        //make age variable
        int age = 0;
        //Get age
        try {
            Calendar birth = ConfirmActivity.convertDate(stringDate);
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
            Calendar date = ConfirmActivity.convertDate(stringDate);
            //compare date
            if(ConfirmActivity.checkAge(date)){
                textView.setText(" "+ stringName +" Thank you for signing up");
            }
            else{
                imageView.equals(null);
                textView.append(" Please try again when you're older");
                jobView.equals("");
                ageView.equals("");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return v;


    }
}