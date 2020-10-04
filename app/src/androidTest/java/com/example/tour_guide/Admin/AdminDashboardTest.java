package com.example.tour_guide.Admin;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import com.example.tour_guide.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class AdminDashboardTest {
    @Rule
    public ActivityTestRule<AdminDashboard> adminDashboardActivityTestRule = new ActivityTestRule<AdminDashboard>(AdminDashboard.class);
    private AdminDashboard adminDashboard = null;
    @Before
    public void setUp() throws Exception {
        adminDashboard = adminDashboardActivityTestRule.getActivity();
    }
    @Test
    public void testLaunch(){
        View view = adminDashboard.findViewById(R.id.tv_title);
        assertNotNull(view);
    }
    @After
    public void tearDown() throws Exception {
        adminDashboard = null;
    }
}