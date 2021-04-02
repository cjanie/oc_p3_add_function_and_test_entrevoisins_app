---- Add a functionality ----

At initial state, unit tests and instrumented tests succeed.
Users would like to see the detail of the other users and to add other users to their favorites.
# Issue 1: to make a new page to see the detail of a user
# Issue 2: to handle favorites

---- Technical info for developers ----

# UX
Initial state:
- As a user I can get the list of the users
- As a user I can delete a user
- As a user I can add a user
Issues:
- As a user I can get the detail of a user on a new page
- As a user I can add users to my favorites

# Technical Specifications
In ListFragment each Item is associated to a 'delete' button. Added: 'view detail' and 'add to favorites' buttons.
Buttons are implemented in RecyclerView:
- on click 'view detail' button to navigate to the 'detail' page
- on click 'add to favorites button' to send an event by EventBus such as the 'delete' button does

Architecture CQRS to use API: GET for query from API / EventBus for command to API
- GET to initialize data in UI: get list in ListFragment / get item by id in DetailActivity
- to command to API pressing a button sending a related event by EventBus

EventBus pattern
The Fragment subscribe to receive events sent by EventBus when buttons are pressed.
Events transport data to the fragment for API command execution.
At the receipt of an event, the fragment makes API execute the corresponding command.
- the receipt of the DeleteEvent by the fragment involves API to execute the delete command
- the receipt of the AddToFavoritesEvent by the fragment involves API to execute the add to favorites command

# Architecture and pattern Scheme
https://docs.google.com/presentation/d/1G5H_qggvZ4L6qLUZY3EXCFsJihgFf7dhPeA3GcwhN2M/edit#slide=id.gce3d97d3eb_0_82
