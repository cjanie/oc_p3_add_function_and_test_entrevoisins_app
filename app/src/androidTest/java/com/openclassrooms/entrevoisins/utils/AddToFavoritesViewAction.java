package com.openclassrooms.entrevoisins.utils;

import android.view.View;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;

import com.openclassrooms.entrevoisins.R;

import org.hamcrest.Matcher;

public class AddToFavoritesViewAction implements ViewAction {

    @Override
    public Matcher<View> getConstraints() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Click on toggle favorite button";
    }

    @Override
    public void perform(UiController uiController, View view) {
        View button = view.findViewById(R.id.detail_neighbour_favorite_button);
        // Maybe check for null
        button.performClick();
    }
}
