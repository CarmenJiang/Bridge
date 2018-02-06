package com.example.carmen.name_app;

import android.content.Intent;
import android.provider.Contacts;
import android.provider.Telephony;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;


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

    @Test
    public void gameOverAfterTenRounds() {

        Intent intent = new Intent();
        activityRule.launchActivity(intent);

        int turns = 10;
        int score = activityRule.getActivity().score;
        activityRule.getActivity().turns = turns;
        onView(withId(R.id.buttonNext)).perform(click());
        onView(withId(R.id.scoreView)).check(matches(withText("Your score is: " + score)));

    }

}