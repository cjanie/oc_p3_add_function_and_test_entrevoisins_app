# OpenClassrooms

At initial state, unit tests and instrumented tests succeed.

Add a functionality: users would like to have the possibility to print details of other users, and to add to their favorites.
# Issue 1: view users details
# Issue 2: add to favorites
# Issue 3: write tests and test
# Issue 4: write info for developers in the README.md file


---- Technical info for developers ----

# UX
As a community user i can get the list of the other community users (initial state)
As a community user i can remove a community user from the list (initial state)
As a community user i can add a community user to the list (initial state)
As a community user i can get the details of a community user of the list (TODO)
As a community user i can add community users to my favorites (TODO)

# Technical Specifications
On the 'MainActivity', each user avatar of the list has an associated 'delete' button.
Let's add a 'view details' button and an 'add to favorites' button.
Use the data of the user avatar to implement methods:
- onClickViewDetails method to navigate to a new page 'UserDetailsActivity' pressing the 'view details' button.
- the method onClickAddToFavorites should also be called pressing a button in 'UserDetailActivity'.
Activities should use a dedicated service to handle favorites.
