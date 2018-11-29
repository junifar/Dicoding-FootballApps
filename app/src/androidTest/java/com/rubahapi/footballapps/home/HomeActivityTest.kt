package com.rubahapi.footballapps.home

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.rubahapi.footballapps.R.id.*
import com.rubahapi.footballapps.home.fragment.match.nextmatch.NextMatchFragment
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
        onView(withId(spinner)).check(matches(isDisplayed()))
        onView(withId(spinner)).perform(click())
        onView(withText("English League Championship")).perform(click())
        Thread.sleep(1000)
        onView(withId(action_search)).check(matches(isDisplayed()))
        onView(withId(action_search)).perform(click())

        Thread.sleep(1000)
        onView(withHint("Search")).perform(click()).perform(typeText("Norwich"))
        Thread.sleep(1000)
        onView(withText("Norwich vs Rotherham")).check(matches(isDisplayed()))
        onView(withText("Norwich vs Rotherham")).perform(click())
        Thread.sleep(1000)
        onView(ViewMatchers.withId(add_to_favorite)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(1000)
        onView(ViewMatchers.withId(add_to_favorite)).perform(click())
        Thread.sleep(1000)
        Espresso.pressBack()

        onView(withText("PAST")).check(matches(isDisplayed()))
        onView(withText("PAST")).perform(click())
        onView(withHint("Search")).perform(clearText()).perform(click()).perform(typeText("Burn"))
        Thread.sleep(1000)
        onView(withText("Burnley vs Newcastle")).check(matches(isDisplayed()))
        onView(withText("Burnley vs Newcastle")).perform(click())
        Thread.sleep(1000)
        onView(ViewMatchers.withId(add_to_favorite)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(1000)
        onView(ViewMatchers.withId(add_to_favorite)).perform(click())
        Thread.sleep(1000)
        Espresso.pressBack()

        Thread.sleep(1000)
        onView(ViewMatchers.withId(navigation))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(navigation_teams)).perform(click())
        Thread.sleep(2000)
        onView(withHint("Search")).perform(clearText()).perform(click()).perform(typeText("Brigh"))
        Thread.sleep(1000)
        onView(withText("Brighton")).check(matches(isDisplayed()))
        onView(withText("Brighton")).perform(click())

        Thread.sleep(1000)
        onView(withText("PLAYERS")).check(matches(isDisplayed()))
        onView(withText("PLAYERS")).perform(click())
        Thread.sleep(1000)
        onView(withText("Dale Stephens")).check(matches(isDisplayed()))
        onView(withText("Dale Stephens")).perform(click())
        Thread.sleep(1000)
        Espresso.pressBack()

        Thread.sleep(1000)
        onView(ViewMatchers.withId(add_to_favorite)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(1000)
        onView(ViewMatchers.withId(add_to_favorite)).perform(click())
        Thread.sleep(1000)
        Espresso.pressBack()

        onView(withHint("Search")).perform(clearText())

        Thread.sleep(1000)
        onView(ViewMatchers.withId(navigation))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(1000)
        onView(ViewMatchers.withId(navigation_favorite)).perform(click())
        Thread.sleep(1000)

        Thread.sleep(1000)
        onView(withText("TEAMS")).check(matches(isDisplayed()))
        onView(withText("TEAMS")).perform(click())
        Thread.sleep(5000)
    }
}