// <&copy {@link UserTest}>
// Copyright (c) Jeroen & Pim. All rights reserved.
// <&copy>

// Package
package domain;

// Imports
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * The Junit tests for the {@link UserTest} class.
 *
 * @author Jeroen Janssen
 * @author Pim Janissen
 */
public class UserTest {

    // Setup primary data types and strings.
    private static final String EMAIL = "TESTemail";
    private static final String NAME = "TESTname";
    private static final String PASSWORD = "TESTpassword";
    private static final String PROFILEPICTUREPATH = "TESTprofilePicturePath";
    private static final String USERNAME = "TESTusername";

    // Setup testUser
    private User testUser = null;


    /**
     * The class which handles the setup before a test.
     *
     * @throws Exception catches all unexpected exceptions.
     */
    @Before
    public void setUp() throws Exception {
        try {
            // testUser
            testUser = new User(EMAIL, PASSWORD, USERNAME, NAME, PROFILEPICTUREPATH);
        }
        catch (Exception ex){
            throw new Exception("An exception has occurred in the setUp");
        }
    }

    /**
     * The class which handles the teardown after a test.
     *
     * @throws Exception catches all unexpected exceptions.
     */
    @After
    public void tearDown() throws Exception {
        try{
            // testUser
            testUser = null;
        }
        catch (Exception Ex) {
            throw new Exception("An exception has occurred in the tearDown");
        }
    }

    /**
     * {@link User#User(String, String, String, String, String)}
     */
    @Test
    public void test_constructor_right() {
        User assertUser = new User(EMAIL, PASSWORD, USERNAME, NAME, PROFILEPICTUREPATH);
        Assert.assertNotNull(assertUser);
    }

    /**
     * {@link User#User(String, String, String, String, String)}
     */
    @Test
    public void test_constructor_emailNull() {
        new User(null, PASSWORD, USERNAME, NAME, PROFILEPICTUREPATH);
    }

    /**
     * {@link User#User(String, String, String, String, String)}
     */
    @Test
    public void test_constructor_emailEmpty() {
        new User("", PASSWORD, USERNAME, NAME, PROFILEPICTUREPATH);
    }

    /**
     * {@link User#User(String, String, String, String, String)}
     */
    @Test
    public void test_constructor_passwordNull() {
        new User(EMAIL, null, USERNAME, NAME, PROFILEPICTUREPATH);
    }

    /**
     * {@link User#User(String, String, String, String, String)}
     */
    @Test
    public void test_constructor_passwordEmpty() {
        new User(EMAIL, "", USERNAME, NAME, PROFILEPICTUREPATH);
    }

    /**
     * {@link User#User(String, String, String, String, String)}
     */
    @Test
    public void test_constructor_usernameNull() {
        new User(EMAIL, PASSWORD, null, NAME, PROFILEPICTUREPATH);
    }

    /**
     * {@link User#User(String, String, String, String, String)}
     */
    @Test
    public void test_constructor_usernameEmpty() {
        new User(null, PASSWORD, "", NAME, PROFILEPICTUREPATH);
    }

    /**
     * {@link User#User(String, String, String, String, String)}
     */
    @Test
    public void test_constructor_nameNull() {
        new User(EMAIL, PASSWORD, USERNAME, null, PROFILEPICTUREPATH);
    }

    /**
     * {@link User#User(String, String, String, String, String)}
     */
    @Test
    public void test_constructor_profilePicturePathNull() {
        new User(null, PASSWORD, USERNAME, NAME, null);
    }

    /**
     * {@link User#User(User)}
     */
    @Test
    public void test_constructor_

    @Test
    public void getEmail() {
    }

    @Test
    public void setEmail() {
    }

    @Test
    public void getPassword() {
    }

    @Test
    public void setPassword() {
    }

    @Test
    public void getWebsite() {
    }

    @Test
    public void setWebsite() {
    }

    @Test
    public void getUsername() {
    }

    @Test
    public void setUsername() {
    }

    @Test
    public void getName() {
    }

    @Test
    public void setName() {
    }

    @Test
    public void getLocation() {
    }

    @Test
    public void setLocation() {
    }

    @Test
    public void getBio() {
    }

    @Test
    public void setBio() {
    }

    @Test
    public void getProfilePicturePath() {
    }

    @Test
    public void setProfilePicturePath() {
    }

    @Test
    public void getFollowers() {
    }

    @Test
    public void getFollowing() {
    }

    @Test
    public void getMessages() {
    }

    @Test
    public void isModerator() {
    }

    @Test
    public void follow() {
    }

    @Test
    public void unfollow() {
    }
}