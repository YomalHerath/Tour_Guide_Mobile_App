package com.example.tour_guide.User;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import com.example.tour_guide.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class UserViewTravellingPlacesTest {

    @Rule
    public ActivityTestRule<UserViewTravellingPlaces> userViewTravellingPlacesActivityTestRule = new ActivityTestRule<UserViewTravellingPlaces>(UserViewTravellingPlaces.class);
    private UserViewTravellingPlaces userViewTravellingPlaces = null;

    @Before
    public void setUp() throws Exception {
        userViewTravellingPlaces = userViewTravellingPlacesActivityTestRule.getActivity();
    }
    @Test
    public void testLaunch() {
        View search = userViewTravellingPlaces.findViewById(R.id.search);
        View reyclerView = userViewTravellingPlaces.findViewById(R.id.UserRecyclerView);
        assertNotNull(search);
        assertNotNull(reyclerView);
    }
    @After
    public void tearDown() throws Exception {
        userViewTravellingPlaces = null;
    }
}