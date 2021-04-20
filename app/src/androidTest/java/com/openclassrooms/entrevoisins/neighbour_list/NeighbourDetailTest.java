package com.openclassrooms.entrevoisins.neighbour_list;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;


import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.DetailNeighbourActivity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Test class for neighbour item
 */
@RunWith(AndroidJUnit4.class)
public class NeighbourDetailTest {

    private ActivityScenario<DetailNeighbourActivity> detailNeighbourActivity;

    @Rule
    public ActivityScenarioRule<DetailNeighbourActivity> detailNeighbourActivityRule = new ActivityScenarioRule(DetailNeighbourActivity.class);

    @Before
    public void setUp() {
        detailNeighbourActivity = detailNeighbourActivityRule.getScenario();
        Assert.assertThat(detailNeighbourActivity, notNullValue());
    }

    @Test
    public void neighbourNameShouldNotBeNull() {
        onView(ViewMatchers.withId(R.id.detail_neighbour_name))
                .check(matches(notNullValue()));
    }
}
