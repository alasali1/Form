package com.example.form;


import org.junit.Rule;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
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


}