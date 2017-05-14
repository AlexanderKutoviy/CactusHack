package com.anykeyapp.activity;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.anykeyapp.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.menu_item),
                        withParent(allOf(withId(R.id.header_layout),
                                withParent(withId(R.id.header_layout)))),
                        isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction relativeLayout = onView(
                allOf(withId(R.id.add_item_btn),
                        withParent(withId(R.id.head_part)),
                        isDisplayed()));
        relativeLayout.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.entry_name_edit_text),
                        withParent(allOf(withId(R.id.add_form),
                                withParent(withId(R.id.body)))),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("пл"), closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.entry_name_edit_text), withText("пл"),
                        withParent(allOf(withId(R.id.add_form),
                                withParent(withId(R.id.body)))),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("плп"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.exp_date_title_edit_text),
                        withParent(allOf(withId(R.id.add_form),
                                withParent(withId(R.id.body)))),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText(""), closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.save_btn), withText("SAVE"),
                        withParent(allOf(withId(R.id.calendar_layout),
                                withParent(withId(R.id.body)))),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatImageView2 = onView(
                allOf(withId(R.id.scan_button),
                        withParent(allOf(withId(R.id.add_form),
                                withParent(withId(R.id.body)))),
                        isDisplayed()));
        appCompatImageView2.perform(click());

        pressBack();

    }

}
