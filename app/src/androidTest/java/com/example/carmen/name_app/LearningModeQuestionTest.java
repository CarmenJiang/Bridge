package com.example.carmen.name_app;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.junit.runner.RunWith;

import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

    @RunWith(AndroidJUnit4.class)
    public class LearningModeQuestionTest {

        @Rule
        public ActivityTestRule<LearningModeQuestion> mActivityRule;

        public LearningModeQuestionTest() {
            mActivityRule = new ActivityTestRule(LearningModeQuestion.class);
        }

        @Test
        public void correctAnswerGivesPoint() {

            LearningModeQuestion main = mActivityRule.getActivity();
            int startPoints = main.score;
            onView(withId(R.id.enterName)).perform(typeText(main.correctName));
            onView(withId(R.id.buttonNext)).perform(click());
            assertEquals(startPoints + 1, main.score);
        }
    }