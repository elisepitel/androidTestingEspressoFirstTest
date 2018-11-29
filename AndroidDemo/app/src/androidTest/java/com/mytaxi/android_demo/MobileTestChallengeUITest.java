package com.mytaxi.android_demo;

import android.support.test.espresso.action.ViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.GrantPermissionRule;
import android.support.test.runner.AndroidJUnit4;

import com.mytaxi.android_demo.activities.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class MobileTestChallengeUITest {


    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Rule
    public GrantPermissionRule permissionRule = GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION);


    private final static String testUserName = "crazydog335";
    private final static String testUserPassword = "venture";
    private final static String testTypeText = "sa";
    private final static String testDriverName = "Sarah Scott";

    @Test
    public void loginTest() {

        onView(withId(R.id.edt_username)).perform(typeText(testUserName));
        onView(withId(R.id.edt_password)).perform(typeText(testUserPassword), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }

        onView(withId(R.id.textSearch)).perform(typeText(testTypeText));

        onView(withText(testDriverName))
                .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .perform(click());

        onView(withId(R.id.textViewDriverName)).check(matches(withText(testDriverName)));

        onView(withId(R.id.fab)).perform(click());

    }


}



