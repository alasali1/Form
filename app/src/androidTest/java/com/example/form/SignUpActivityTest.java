package com.example.form;


import android.widget.DatePicker;
import androidx.test.espresso.contrib.PickerActions;
import org.hamcrest.Matchers;
import org.junit.Rule;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.*;
import static org.junit.Assert.*;
@RunWith(JUnit4.class)
public class SignUpActivityTest {
    @Rule
    public ActivityScenarioRule<SignUpActivity> activityScenarioRule
            = new ActivityScenarioRule<>(SignUpActivity.class);

    @Test
    public void retainsStateAfterRotate() {
        // Change state of buttons / textview
        onView(withId(R.id.name)).perform(typeText("Bill"));

        // Ensure change happened
        onView(withId(R.id.name)).check(matches(withText("Bill")));

        TestUtils.rotateScreen(TestUtils.getActivity(activityScenarioRule));

        // Ensure change is still there
        onView(withId(R.id.name)).check(matches(withText("Bill")));
    }

    @Test
    public void checkEmail() {
        onView(withId(R.id.name)).perform(typeText("Bill"));
        onView(withId(R.id.email)).perform(typeText("dfdsfdsfgf"));
        onView(withId(R.id.btn_date)).perform((click()));
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(1935, 01, 01));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.goToSubmitActivity)).perform((click()));
        onView((allOf(withId(R.id.email), hasErrorText("Email invalid"))));

    }

    @Test
    public void checkEmptyName() {
        onView(withId(R.id.name)).perform(typeText(""));
        onView(withId(R.id.email)).perform(typeText("abcmoney@gmail.com"));
        onView(withId(R.id.btn_date)).perform((click()));
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(1950, 01, 01));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.goToSubmitActivity)).perform((click()));
        onView((allOf(withId(R.id.name), hasErrorText("Name can't be empty"))));

    }

    @Test
    public void checkAge(){
        onView(withId(R.id.name)).perform(typeText("Bill"));
        onView(withId(R.id.email)).perform(typeText("abcmoney@gmail.com"));
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2020, 01, 01));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.goToSubmitActivity)).perform((click()));
        onView((allOf(withId(R.id.name), hasErrorText("Too young"))));
    }
}