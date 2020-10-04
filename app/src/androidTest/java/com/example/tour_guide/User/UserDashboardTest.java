package com.example.tour_guide.User;

import android.app.Activity;
import android.app.Instrumentation;
import android.view.View;

import androidx.test.rule.ActivityTestRule;

import com.example.tour_guide.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertNotNull;

public class UserDashboardTest {
    @Rule
    public ActivityTestRule<UserDashboard> dashboardActivityTestRule = new ActivityTestRule<UserDashboard>(UserDashboard.class);
    private UserDashboard mUserDashboard = null;
    @Before
    public void setUp() throws Exception {
        mUserDashboard = dashboardActivityTestRule.getActivity();
    }
    @Test
    public void testLaucnchOfviewPlacesOnViewClick(){
        View viewPlace = mUserDashboard.findViewById(R.id.all_travelling_place_categories);
        View viewEvent = mUserDashboard.findViewById(R.id.view_all_event_btn);
        View viewHotel = mUserDashboard.findViewById(R.id.view_all_hospitality_btn);
        View viewReview = mUserDashboard.findViewById(R.id.view_all_review_btn);
        assertNotNull(viewPlace);
        assertNotNull(viewEvent);
        assertNotNull(viewHotel);
        assertNotNull(viewReview);
    }
    @After
    public void tearDown() throws Exception {
        mUserDashboard = null;
    }
}