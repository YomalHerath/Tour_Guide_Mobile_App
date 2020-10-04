package com.example.tour_guide.User;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import com.example.tour_guide.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class loginTest {
    @Rule
    public ActivityTestRule<login> loginActivityTestRule = new ActivityTestRule<login>(login.class);
    private login mlogin = null;
    @Before
    public void setUp() throws Exception {
        mlogin = loginActivityTestRule.getActivity();
    }
    @Test
    public void testLaunch(){
        View viewemail = mlogin.findViewById(R.id.email);
        View viewpass = mlogin.findViewById(R.id.password);
        View viewbtn = mlogin.findViewById(R.id.loginbutton);
        assertNotNull(viewemail);
        assertNotNull(viewpass);
        assertNotNull(viewbtn);
    }
    @After
    public void tearDown() throws Exception {
        mlogin = null;
    }
}