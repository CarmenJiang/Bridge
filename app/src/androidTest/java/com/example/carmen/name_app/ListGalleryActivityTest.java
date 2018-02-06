package com.example.carmen.name_app;

import android.app.Activity;
import android.content.Intent;
import android.support.test.espresso.core.internal.deps.guava.collect.Iterables;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;
import android.util.Log;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static android.support.test.InstrumentationRegistry.getContext;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.*;

/**
 * Created by jurg on 05/02/2018.
 */
public class ListGalleryActivityTest {

    @Rule
    public IntentsTestRule<ListGalleryActivity> activityRule
            = new IntentsTestRule<>(
            ListGalleryActivity.class,
            true,     // initialTouchMode
            false);

    @Test
    public void clickingThumbnailGivesCorrectImage() {

        int testIndex = 6;

        Intent intent = new Intent();
        activityRule.launchActivity(intent);
        String name = PersonMap.getNames()[testIndex];

        onData(anything())
                .inAdapterView(withId(R.id.gridView))
                .atPosition(testIndex)
                .perform(click());

        intended(hasExtra("PersonNumber", testIndex));

        onView(withId(R.id.personName)).check(matches(withText(name)));



    }


}