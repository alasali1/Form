package com.example.form;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.form.ui.main.SectionsPagerAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = SecondActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);

        //fragment
        ProfileFragment profileFragment = new ProfileFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragment_profile, profileFragment, "profileFragment");
        transaction.commit();
        Log.i(TAG, "onCreate()");

        //Get the bundle
        Bundle bundle = getIntent().getExtras();
        String stringDate = bundle.getString("date");
        String stringName = bundle.getString("name");
        String stringJob = bundle.getString("job");
        String stringDescription = bundle.getString("description");
        //set arguments
        profileFragment.setArguments(bundle);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    //turn date string into date object
    public static Calendar convertDate(String stringDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        Calendar date = Calendar.getInstance();
        date.setTime(sdf.parse(stringDate));
        return date;
    }

    public static boolean checkAge(Calendar date){
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