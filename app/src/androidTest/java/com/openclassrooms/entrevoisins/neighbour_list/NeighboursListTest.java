
package com.openclassrooms.entrevoisins.neighbour_list;

import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.ext.junit.rules.ActivityScenarioRule;



import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.FavoriteNeighbourHandler;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;
import com.openclassrooms.entrevoisins.utils.ShowDetailViewAction;

import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;




/**
 * Test class for list of neighbours
 */

@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;

    // This is not fixed
    private int favoritesCount = -1;

    private ActivityScenario<ListNeighbourActivity> listNeighbourActivity;

    @Rule
    public ActivityScenarioRule<ListNeighbourActivity> listNeighbourActivityScenarioRule =
            new ActivityScenarioRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        this.listNeighbourActivity = listNeighbourActivityScenarioRule.getScenario();
        Assert.assertThat(this.listNeighbourActivity, notNullValue());
        this.favoritesCount = FavoriteNeighbourHandler.getInstance().getFavoriteNeighbours().size();
    }

    /**
     * We ensure that our recyclerview is displaying at least one item
     */

    @Test
    public void neighboursListShouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void neighboursListDeleteActionShouldRemoveItem() {
        onView(ViewMatchers.withId(R.id.list_neighbours)).check((ViewAssertion) withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of elements is 11
        onView(ViewMatchers.withId(R.id.list_neighbours)).check((ViewAssertion) withItemCount(ITEMS_COUNT - 1));
    }

    /**
     * Test the detail activity launching
     * https://zestedesavoir.com/tutoriels/272/introduction-aux-tests-android-avec-espresso/
     */

    @Test
    public void neighboursListShowDetailActionShouldLaunchDetailActivity() {
        // Perform click on neighbour at position 0
        onView(ViewMatchers.withId(R.id.list_neighbours))
               .perform(RecyclerViewActions.actionOnItemAtPosition(0, new ShowDetailViewAction()));
        // Suppose to be on detail activity
        onView(ViewMatchers.withId(R.id.detail_neighbour)).check(matches(notNullValue()));
    }

    @Test
    public void neighboursListShowDetailActionShouldLaunchDetailActivityWithNeighbourName() {
        // Perform click on neighbour at position 0 (Caroline)
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new ShowDetailViewAction()));
        // Detail activity should show neighbour name
        onView(ViewMatchers.withId(R.id.detail_neighbour_name)).check(matches(withText("Caroline")));
    }


    /**
     * Test list of favorites
     */
    @Test
    public void favoritesListShouldNotBeNull() {
        onView(ViewMatchers.withId(R.id.list_favorites))
                .check(matches(notNullValue()));
    }

    @Test
    public void favoritesListIsInitiallyEmpty() {
        onView(ViewMatchers.withId(R.id.list_favorites)).check(matches(hasMinimumChildCount(0)));
    }

    private void addFavorite() {
        Neighbour neighbour = DI.getNeighbourApiService().getNeighbourById(1);
        FavoriteNeighbourHandler.getInstance().addToFavorites(neighbour);
    }

    @Test
    public void favoritesListShouldContainOnlyFavorites() {
        this.addFavorite();
        onView(ViewMatchers.withId(R.id.list_favorites)).check(matches(hasMinimumChildCount(1)));
// TODO
    }




}