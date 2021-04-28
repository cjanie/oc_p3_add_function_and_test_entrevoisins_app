package com.openclassrooms.entrevoisins.utils;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.matcher.BoundedMatcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;

public class RecyclerViewAtPositionItemViewMatcher {

    public static Matcher<View> atPosition(final int position, @NonNull  final Matcher<View> itemViewMatcher) {
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("has item at position " + position + ": ");
                itemViewMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(final RecyclerView itemView) {
                RecyclerView.ViewHolder viewHolder = itemView.findViewHolderForAdapterPosition(position);
                if(viewHolder == null) {
                    // has no item on such position
                    return false;
                }
                return itemViewMatcher.matches(viewHolder.itemView);
            }
        };
    }
}
