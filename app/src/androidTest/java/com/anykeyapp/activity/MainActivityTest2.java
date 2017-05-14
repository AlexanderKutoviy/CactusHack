package com.anykeyapp.activity;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.anykeyapp.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest2 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest2() {
        ViewInteraction relativeLayout = onView(
                allOf(withId(R.id.add_item_btn),
                        withParent(withId(R.id.head_part)),
                        isDisplayed()));
        relativeLayout.perform(click());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.categories_recycler),
                        withParent(allOf(withId(R.id.recycler_layout),
                                withParent(withId(R.id.body)))),
                        isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(2, click()));

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.categories_recycler),
                        withParent(allOf(withId(R.id.recycler_layout),
                                withParent(withId(R.id.body)))),
                        isDisplayed()));
        recyclerView2.perform(actionOnItemAtPosition(1, click()));

        ViewInteraction recyclerView3 = onView(
                allOf(withId(R.id.categories_recycler),
                        withParent(allOf(withId(R.id.recycler_layout),
                                withParent(withId(R.id.body)))),
                        isDisplayed()));
        recyclerView3.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.save_btn), withText("SAVE"),
                        withParent(allOf(withId(R.id.calendar_layout),
                                withParent(withId(R.id.body)))),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction relativeLayout2 = onView(
                allOf(withId(R.id.add_item_btn),
                        withParent(withId(R.id.head_part)),
                        isDisplayed()));
        relativeLayout2.perform(click());

        ViewInteraction recyclerView4 = onView(
                allOf(withId(R.id.categories_recycler),
                        withParent(allOf(withId(R.id.recycler_layout),
                                withParent(withId(R.id.body)))),
                        isDisplayed()));
        recyclerView4.perform(actionOnItemAtPosition(2, click()));

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.save_btn), withText("SAVE"),
                        withParent(allOf(withId(R.id.calendar_layout),
                                withParent(withId(R.id.body)))),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.delete_btn), isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction appCompatImageView2 = onView(
                allOf(withId(R.id.menu_item),
                        withParent(allOf(withId(R.id.header_layout),
                                withParent(withId(R.id.header_layout)))),
                        isDisplayed()));
        appCompatImageView2.perform(click());

        ViewInteraction customTextView = onView(
                allOf(withId(android.R.id.text1), withText("To buy list"),
                        childAtPosition(
                                withId(R.id.left_drawer),
                                0),
                        isDisplayed()));
        customTextView.perform(click());

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
