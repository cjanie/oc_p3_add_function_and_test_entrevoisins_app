package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_list.DetailNeighbourActivity;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

import static android.support.test.espresso.Espresso.onView;

/**
 * Test class for neighbour item
 */
@RunWith(AndroidJUnit4.class)
public class NeighbourDetailTest {

    private DetailNeighbourActivity mActivity;

    @Rule
    public ActivityTestRule<DetailNeighbourActivity> mActivityRule = new ActivityTestRule(DetailNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that the activity is displaying the item
     */
    @Test
    public void myNeighbourItem_shouldNotBeEmpty() {
        // TODO test the intent, create the neighbour to test. Check if the text view with id detail_neighbour_name matches
    }
}
