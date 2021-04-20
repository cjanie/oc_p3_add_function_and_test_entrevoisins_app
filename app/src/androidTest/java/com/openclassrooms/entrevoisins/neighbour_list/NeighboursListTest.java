
package com.openclassrooms.entrevoisins.neighbour_list;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.ext.junit.rules.ActivityScenarioRule;



import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.service.FavoriteNeighbourHandler;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.AddToFavoritesViewAction;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
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
    public void myNeighboursListShouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursListDeleteActionShouldRemoveItem() {
        onView(ViewMatchers.withId(R.id.list_neighbours)).check((ViewAssertion) withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of elements is 11
        onView(ViewMatchers.withId(R.id.list_neighbours)).check((ViewAssertion) withItemCount(ITEMS_COUNT - 1));
    }

    /*
    @Test
    public void favoritesListShouldNotBeNull() {
        onView(ViewMatchers.withId(R.id.list_favorites))
                .check(matches(hasMinimumChildCount(0)));
    }

    @Test
    public void favoriteListAddActionShouldAddItem() {
        // Given : We add to favorites the element at position 2
        onView(ViewMatchers.withId(R.id.list_favorites)).check((ViewAssertion) withItemCount(favoritesCount));
        // When perform a click on a add to favorites icon
        onView(ViewMatchers.withId(R.id.list_favorites))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new AddToFavoritesViewAction()));
        // Then : the number of elements is 1
        onView(ViewMatchers.withId(R.id.list_favorites)).check((ViewAssertion) withItemCount(favoritesCount + 1));
    }

*/
}