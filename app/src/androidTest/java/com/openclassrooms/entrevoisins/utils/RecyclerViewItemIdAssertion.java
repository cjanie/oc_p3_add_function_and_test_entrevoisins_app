package com.openclassrooms.entrevoisins.utils;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;

public class RecyclerViewItemIdAssertion implements ViewAssertion {

    private final Matcher<Long> matcher;

    public static RecyclerViewItemIdAssertion withItemId(long expectedId) {
        return withItemId(Matchers.is(expectedId));
    }
    public static RecyclerViewItemIdAssertion withItemId(Matcher<Long> matcher) {
        return new RecyclerViewItemIdAssertion(matcher);
    }
    private RecyclerViewItemIdAssertion(Matcher<Long> matcher) {
        this.matcher = matcher;
    }


    @Override
    public void check(View view, NoMatchingViewException noViewFoundException) {
        if(noViewFoundException != null) {
            throw noViewFoundException;
        }

        RecyclerView recyclerView = (RecyclerView) view;
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        Assert.assertThat(adapter.getItemId(1), matcher);
    }
}
