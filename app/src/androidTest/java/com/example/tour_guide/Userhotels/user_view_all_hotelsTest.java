package com.example.tour_guide.Userhotels;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import com.example.tour_guide.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class user_view_all_hotelsTest {

    @Rule
    public ActivityTestRule<user_view_all_hotels> user_view_all_hotelsActivityTestRule = new ActivityTestRule<user_view_all_hotels>(user_view_all_hotels.class);
    private user_view_all_hotels muser_view_all_hotels = null;
    @Before
    public void setUp() throws Exception {
        muser_view_all_hotels = user_view_all_hotelsActivityTestRule.getActivity();
    }
    @Test
    public void testLaunch() {
        View search = muser_view_all_hotels.findViewById(R.id.search);
        View reyclerView = muser_view_all_hotels.findViewById(R.id.UserRecyclerView);
        assertNotNull(search);
        assertNotNull(reyclerView);
    }
    @After
    public void tearDown() throws Exception {
        muser_view_all_hotels = null;
    }
}