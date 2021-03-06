
package com.openclassrooms.entrevoisins.neighbour_list;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;


import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.FavoriteNeighbourHandler;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;
import com.openclassrooms.entrevoisins.utils.RecyclerViewAtPositionItemViewMatcher;
import com.openclassrooms.entrevoisins.utils.ShowDetailViewAction;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
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

    private ActivityScenario<ListNeighbourActivity> listNeighbourActivity;

    private void addFavoriteAtPosition0() {
        Neighbour neighbour = DI.getNeighbourApiService().getNeighbourById(1);
        FavoriteNeighbourHandler.getInstance().addToFavorites(neighbour);
    }

    private void addFavoriteAtPosition1() {
        Neighbour neighbour = DI.getNeighbourApiService().getNeighbourById(2);
        FavoriteNeighbourHandler.getInstance().addToFavorites(neighbour);
    }

    @Rule
    public ActivityScenarioRule<ListNeighbourActivity> listNeighbourActivityScenarioRule =
            new ActivityScenarioRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        this.listNeighbourActivity = listNeighbourActivityScenarioRule.getScenario();
        Assert.assertThat(this.listNeighbourActivity, notNullValue());
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

    @Test
    public void favoritesListShouldContainOnlyFavorites() {
        this.addFavoriteAtPosition1();
        onView(ViewMatchers.withId(R.id.list_favorites)).check(withItemCount(1));
        onView(ViewMatchers.withId(R.id.list_favorites))
                .check(matches(RecyclerViewAtPositionItemViewMatcher.atPosition(0, hasDescendant(withText("Jack")))));
    }

    /**
     * When we delete an item, the item is no more shown
     */

    @Test
    public void neighboursListDeleteActionShouldRemoveItemFromBothNeighboursAndFavoritesLists() {
        this.addFavoriteAtPosition0();
        // Before click on Delete item at position 0, this item is in the list of favorites
        onView(ViewMatchers.withId(R.id.list_neighbours)).check((ViewAssertion) withItemCount(ITEMS_COUNT));
        onView(ViewMatchers.withId(R.id.list_favorites)).check(withItemCount(1));
        // Perform a click on Delete item at position 0
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new DeleteViewAction()));
        // Then : the number of elements is 11 in the list of neighbourgs, and 0 in the list of favorites
        onView(ViewMatchers.withId(R.id.list_neighbours)).check((ViewAssertion) withItemCount(ITEMS_COUNT - 1));
        onView(ViewMatchers.withId(R.id.list_favorites)).check(withItemCount(0));
    }

}