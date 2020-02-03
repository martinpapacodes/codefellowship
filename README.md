# Code Fellowship - Spring Authorization

Problem Domain: 
Feature Tasks

    Build an app that allows users to create their profile on CodeFellowship.

    The site should have a splash page at the root route (/) that contains basic information about the site, as well as a link to the “sign up” page.
    An ApplicationUser should have a username, password (will be hashed using BCrypt), firstName, lastName, dateOfBirth, bio, and any other fields you think are useful.
    The site should allow users to create an ApplicationUser on the “sign up” page.
        Your Controller should have an @Autowired private PasswordEncoder passwordEncoder; and use that to run passwordEncoder.encode(password) before saving the password into the new user.
    The site should have a page which allows viewing the data about a single ApplicationUser, at a route like /users/{id}.
        This should include a default profile picture, which is the same for every user, and their basic information.
    Using the above cheat sheet, add the ability for users to log in to your app.
    When a user is logged in, the app should display the user’s username on every page (probably in the heading).
    Ensure that your homepage, login, and registration routes are accessible to non-logged in users. All other routes should be limited to logged-in users.
    The site should be well-styled and attractive.
    The site should use reusable templates for its information. (At a minimum, it should have one Thymeleaf fragment that is used on multiple pages.)
    The site should have a non-whitelabel error handling page that lets the user know, at minimum, the error code and a brief message about what went wrong.
    Ensure that user registration also logs users into your app automatically.
    Add a Post entity to your app.
        A Post has a body and a createdAt timestamp.
        A logged-in user should be able to create a Post, and a post should belong to the user that created it.
            hint: this is a relationship between two pieces of data
    A user’s posts should be visible on their profile page.


    Ensure that users can’t perform SQL injection or HTML injection with their posts.
    Allow users to follow other users. Following a user means that their posts show up in the logged-in user’s feed, where they can see what all of their followed users have posted recently.
        Ensure there is some way (like a users index page) that a user can discover other users on the service.
        On a user profile page that does NOT belong to the currently logged-in user, display a “Follow” button. When a user clicks that follow button, the logged-in user is now following the viewed-profile-page user.
            note: this will require a self-join on ApplicationUsers.
        A user can visit a url (like /feed) to view all of the posts from the users that they follow.
            Each post should have a link to the user profile of the user who wrote the post.

    Old feature tasks that are still required

    A splash page with basic information about the site
    The ability for users to register for new accounts and log in.
    The ability for logged-in users to create posts.
    The ability to see a user’s posts, along with their profile information and a default profile picture, on their profile page.
    A pleasing design throughout the site.
    Thymeleaf templates & fragments used appropriately to keep view code DRY.
    Smooth error handling with appropriate responses to bad requests.
    Integration testing on (at minimum) the splash page, login, and sign up routes.



### Running The Application
Open the terminal and CD into codefellowship repo.
Open a browser and type http://localhost:8080/ in the address bar.