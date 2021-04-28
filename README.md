At initial state, unit tests and instrumented tests succeed.

# UX

Initial state:
- As a user (neighbour) I can get the list of the users (neighbours)
- As a user I can delete a user
- As a user I can add a user

Issues:
- As a user I can view the detail of a user on a new page
- As a user I can add the user to my favorites

# View Detail Issue
The list of neighbours view is handled by RecyclerView.Adapter, each item view is handled in the ViewHolder of the adapter.
For each item view, the 'delete' button is implemented in the ViewHolder.
The same for the new functionnality view Detail, on click on the item; it uses an intent to navigate to the detail page (activity), that carries the id of the item for the detail page
to call api with a getById method.

The detail activity consists in a ScrollView. Within: a ConstraintLayout that contains:
- the picture of the user at 40% of the screen
- the back icon button, with an intent to the list page
- CardViews with ConstraintLayout, Radius, Elevation

# Handling Favorites Issue

In the detail page, there is a toogle to handle favorites; it is made of two images corresponding to the checked/uncheked status:
depending of these checked/unchecked status, user is added to the favorites or removed from the favorites.

For the list of favorites, it is displayed in the List activity, as the list of neighbours, depending of a ViewPagerAdapter displaying the list of neighbours fragment or the list of favorites fragment.
Both fragments, for both lists, are using the same RecyclerView.Adapter. So they can have the same parent fragment.
The fragment methods (onCreateView...) are implemented in the parent fragment; the parent fragment has abstract method to initialize the correct list and the correct list layout, depending of the child fragments for neigbours or favorites

# The api contains
- a NeighbourService
- a FavoriteHandler
- a FavoriteInterface (Neighbour class implements FavoriteInterface)
The service has a FavoriteHandler property that is needed in the delete implementation: when a neighbour is deleted, it has to be removed from the list of favorites.
https://docs.google.com/presentation/d/1G5H_qggvZ4L6qLUZY3EXCFsJihgFf7dhPeA3GcwhN2M/edit#slide=id.gce3d97d3eb_0_82

# Unit Tests
- Test service
- Test FavoriteHandler

# Instrumented Tests
- All views are tested from the main List Activity