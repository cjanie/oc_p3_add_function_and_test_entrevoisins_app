package com.openclassrooms.entrevoisins.utils;

import android.view.View;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;

import com.openclassrooms.entrevoisins.R;

import org.hamcrest.Matcher;

public class ShowDetailViewAction implements ViewAction {
    @Override
    public Matcher<View> getConstraints() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Click on neighbour item";
    }

    @Override
    public void perform(UiController uiController, View view) {
        View textView = view.findViewById(R.id.item_list_name);
        textView.performClick();
    }
}
