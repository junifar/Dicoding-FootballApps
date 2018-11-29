package com.rubahapi.footballapps.home

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.rubahapi.footballapps.R.id.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest{
    @Rule
    @JvmField var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun testHomeActivityBehaviour(){
        Thread.sleep(5000)
        onView(withText("English Premier League")).perform(click())
        onView(withText("English League Championship")).perform(click())
        Thread.sleep(1000)
        onView(withText("Norwich vs Rotherham")).perform(click())
        Thread.sleep(1000)
        onView(ViewMatchers.withId(add_to_favorite)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(1000)
        onView(ViewMatchers.withId(add_to_favorite)).perform(click())
        Thread.sleep(1000)
        Espresso.pressBack()
        Thread.sleep(1000)
        onView(ViewMatchers.withId(navigation))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(1000)
        onView(ViewMatchers.withId(navigation_favorite)).perform(click())
        Thread.sleep(1000)
    }
}