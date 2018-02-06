package com.example.carmen.name_app;

import android.content.Intent;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

/**
 * Created by Eier on 05.02.2018.
 */

@RunWith(AndroidJUnit4.class)
public class GalleryTest {

    @Rule
    public ActivityTestRule<AddNewPerson> activityRule
            = new ActivityTestRule<>(
            AddNewPerson.class,
            true,     // initialTouchMode
            false);

    @Test
    public void addNewPersonAddsNewPerson() {


        activityRule.launchActivity(new Intent());


//        String correct = activityRule.getActivity().correctName;
//        int startpoints = activityRule.getActivity().score;
//        onView(withId(R.id.buttonNext)).perform(click());
//        assertEquals(startpoints + 1, activityRule.getActivity().score);
        String name = "name123";


        onView(withId(R.id.addName)).perform(typeText(name + "\n"));
        onView(withId(R.id.saveButton)).perform(click());
        assertEquals(PersonMap.imageItems.get(PersonMap.imageItems.size()-1).getTitle(), name);
        ArrayList<String> pList = new saveFileHandler(activityRule.getActivity()).getPeople();
        assertEquals(pList.get(pList.size()-1).split("\\+")[0], name);


    }
}





