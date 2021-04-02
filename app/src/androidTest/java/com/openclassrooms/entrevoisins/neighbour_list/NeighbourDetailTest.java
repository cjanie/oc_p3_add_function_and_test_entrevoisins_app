package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.DetailNeighbourActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

import static android.support.test.espresso.Espresso.onView;

/**
 * Test class for neighbour item
 */
@RunWith(AndroidJUnit4.class)
public class NeighbourDetailTest {

    private DetailNeighbourActivity detailNeighbourActivity;

    @Rule
    public ActivityTestRule<DetailNeighbourActivity> detailNeighbourActivityRule = new ActivityTestRule(DetailNeighbourActivity.class);

    @Before
    public void setUp() {
        detailNeighbourActivity = detailNeighbourActivityRule.getActivity();
        assertThat(detailNeighbourActivity, notNullValue());
    }

    @Test
    public void neighbourName_shouldNotBeNull() {
        onView(ViewMatchers.withId(R.id.detail_neighbour_name))
                .check(matches(notNullValue()));
    }
}
