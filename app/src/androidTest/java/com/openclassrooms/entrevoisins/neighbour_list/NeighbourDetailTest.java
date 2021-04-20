package com.openclassrooms.entrevoisins.neighbour_list;

import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;


import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.DetailNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.ToggleFavoriteViewAction;

import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
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
        this.detailNeighbourActivity = this.detailNeighbourActivityRule.getScenario();
        Assert.assertThat(this.detailNeighbourActivity, notNullValue());
    }

    @Test
    public void neighbourNameShouldNotBeNull() {
        onView(ViewMatchers.withId(R.id.detail_neighbour_name))
                .check(matches(notNullValue()));
    }



}
