package com.example.tour_guide.UserEvents;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import com.example.tour_guide.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class User_View_All_EventsTest {

    @Rule
    public ActivityTestRule<User_View_All_Events> user_view_all_eventsActivityTestRule = new ActivityTestRule<User_View_All_Events>(User_View_All_Events.class);
    private User_View_All_Events user_view_all_events  = null;
    @Before
    public void setUp() throws Exception {
        user_view_all_events = user_view_all_eventsActivityTestRule.getActivity();
    }
    @Test
    public void testLaunch() {
        View search = user_view_all_events.findViewById(R.id.searchBtn);
        View reyclerView = user_view_all_events.findViewById(R.id.UserEventRecyclerView);
        assertNotNull(search);
        assertNotNull(reyclerView);
    }
    @After
    public void tearDown() throws Exception {
        user_view_all_events = null;
    }
}