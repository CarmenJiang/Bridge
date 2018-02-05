package com.example.carmen.name_app;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.v4.content.ContextCompat.startActivity;
import static android.util.Log.*;
import static android.util.Log.e;
import static org.junit.Assert.*;

/**
 * Created by jurg on 02/02/2018.
 */

@RunWith(AndroidJUnit4.class)
public class LearningModeQuestionTest {

    @Rule
    public ActivityTestRule<LearningModeQuestion> activityRule
            = new ActivityTestRule<>(
            LearningModeQuestion.class,
            true,     // initialTouchMode
            false);

    @Test
    public void correctAnswerGivesPoint() {

        Intent intent = new Intent();
        activityRule.launchActivity(intent);

        String correct = activityRule.getActivity().correctName;
        int startpoints = activityRule.getActivity().score;
        onView(withId(R.id.enterName)).perform(typeText(correct));
        onView(withId(R.id.buttonNext)).perform(click());
        assertEquals(startpoints + 1, activityRule.getActivity().score);

    }

    @Test
    public void wrongAnswerGivesNoPoint() {

        Intent intent = new Intent();
        activityRule.launchActivity(intent);

        String inCorrect = "wrong";
        int startpoints = activityRule.getActivity().score;
        onView(withId(R.id.enterName)).perform(typeText(inCorrect));
        onView(withId(R.id.buttonNext)).perform(click());
        assertEquals(startpoints, activityRule.getActivity().score);

    }

}