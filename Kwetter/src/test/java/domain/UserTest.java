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

import java.util.ArrayList;
import java.util.List;


/**
 * The Junit tests for the {@link UserTest} class.
 *
 * @author Jeroen Janssen
 * @author Pim Janissen
 */
public class UserTest {

    // Setup primary data types and strings.
    private static final String BIO = "TESTbio";
    private static final String EMAIL = "TESTemail";
    private static final int FOLLOWERID = 1;
    private static final String LOCATION = "TESTlocation";
    private static final String NAME = "TESTname";
    private static final String PASSWORD = "TESTpassword";
    private static final String PROFILEPICTUREPATH = "TESTprofilePicturePath";
    private static final String USERNAME = "TESTusername";
    private static final String WEBSITE = "TESTwebsite";

    // Setup variables
    private UserRole ROLE = UserRole.USER;
    private List<Integer> followers;
    private List<User> following;
    private List<Message> messages;

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
            // Variables
            this.followers = new ArrayList<>();
            this.following = new ArrayList<>();
            this.messages = new ArrayList<>();

            // testUser
            this.testUser = new User(EMAIL, PASSWORD, USERNAME, NAME, PROFILEPICTUREPATH);
        }
        catch (Exception ex) {
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
        try {
            // Variables
            this.followers = null;
            this.following = null;
            this.messages = null;

            // testUser
            this.testUser = null;
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
    @Test(expected = IllegalArgumentException.class)
    public void test_constructor_emailNull() {
        new User(null, PASSWORD, USERNAME, NAME, PROFILEPICTUREPATH);
    }

    /**
     * {@link User#User(String, String, String, String, String)}
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_constructor_emailEmpty() {
        new User("", PASSWORD, USERNAME, NAME, PROFILEPICTUREPATH);
    }

    /**
     * {@link User#User(String, String, String, String, String)}
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_constructor_passwordNull() {
        new User(EMAIL, null, USERNAME, NAME, PROFILEPICTUREPATH);
    }

    /**
     * {@link User#User(String, String, String, String, String)}
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_constructor_passwordEmpty() {
        new User(EMAIL, "", USERNAME, NAME, PROFILEPICTUREPATH);
    }

    /**
     * {@link User#User(String, String, String, String, String)}
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_constructor_usernameNull() {
        new User(EMAIL, PASSWORD, null, NAME, PROFILEPICTUREPATH);
    }

    /**
     * {@link User#User(String, String, String, String, String)}
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_constructor_usernameEmpty() {
        new User(EMAIL, PASSWORD, "", NAME, PROFILEPICTUREPATH);
    }

    /**
     * {@link User#User(String, String, String, String, String)}
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_constructor_nameNull() {
        new User(EMAIL, PASSWORD, USERNAME, null, PROFILEPICTUREPATH);
    }

    /**
     * {@link User#User(String, String, String, String, String)}
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_constructor_profilePicturePathNull() {
        new User(EMAIL, PASSWORD, USERNAME, NAME, null);
    }

    /**
     * {@link User#getBio()}
     */
    @Test
    public void test_getBio_right() {
        this.testUser.setBio(BIO);

        Assert.assertEquals(BIO, this.testUser.getBio());
    }

    /**
     * {@link User#getEmail()}
     */
    @Test
    public void test_getEmail_right() {
        Assert.assertEquals(EMAIL, this.testUser.getEmail());
    }

    /**
     * {@link User#getFollowers()}
     */
    @Test
    public void test_getFollowers_right() {
        Assert.assertEquals(this.followers, this.testUser.getFollowers());
    }

    /**
     * {@link User#getFollowers()}
     */
    @Test
    public void test_getFollowers_rightFilled() {
        // TODO: add test with Followers.
    }

    /**
     * {@link User#getFollowing()}
     */
    @Test
    public void test_getFollowing_right() {
        Assert.assertEquals(this.following, this.testUser.getFollowing());
    }

    /**
     * {@link User#getFollowing()}
     */
    @Test
    public void test_getFollowing_rightFilled() {
        // TODO: Add test with following.
    }

    /**
     * {@link User#getLocation()}
     */
    @Test
    public void test_getLocation_right() {
        this.testUser.setLocation(LOCATION);

        Assert.assertEquals(LOCATION, this.testUser.getLocation());
    }

    /**
     * {@link User#getMessages()}
     */
    @Test
    public void test_getMessages_right() {
        Assert.assertEquals(messages, this.testUser.getMessages());
    }

    /**
     * {@link User#getMessages()}
     */
    @Test
    public void test_getMessages_rightFilled() {
        // TODO: add test with messages.
    }

    /**
     * {@link User#getName()}
     */
    @Test
    public void test_getName_right() {
        Assert.assertEquals(NAME, this.testUser.getName());
    }

    /**
     * {@link User#getPassword()}
     */
    @Test
    public void test_getPassword_right() {
        Assert.assertEquals(PASSWORD, this.testUser.getPassword());
    }

    /**
     * {@link User#getProfilePicturePath()}
     */
    @Test
    public void test_getProfilePicturePath_right() {
        Assert.assertEquals(PROFILEPICTUREPATH, this.testUser.getProfilePicturePath());
    }

    /**
     * {@link User#getRole()}
     */
    @Test
    public void test_getRole_right() {
        Assert.assertEquals(ROLE ,this.testUser.getRole());
    }

    /**
     * {@link User#getUsername()}
     */
    @Test
    public void test_getUsername_right() {
        Assert.assertEquals(USERNAME, this.testUser.getUsername());
    }

    /**
     * {@link User#getWebsite()}
     */
    @Test
    public void test_getWebsite_right() {
        this.testUser.setWebsite(WEBSITE);

        Assert.assertEquals(WEBSITE, this.testUser.getWebsite());
    }

    /**
     * {@link User#setBio(String)}
     */
    @Test
    public void test_setBio_right() {
        this.testUser.setBio(BIO);

        Assert.assertEquals(BIO, this.testUser.getBio());
    }

    /**
     * {@link User#setBio(String)}
     */
    @Test
    public void test_setBio_rightEmptyBio() {
        this.testUser.setBio("");

        Assert.assertEquals("", this.testUser.getBio());
    }

    /**
     * {@link User#setBio(String)}
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_setBio_BioNull() {
        this.testUser.setBio(null);
    }

    /**
     * {@link User#setEmail(String)}
     */
    @Test
    public void test_setEmail_right() {
        String testEmail = "emailTEST";
        this.testUser.setEmail(testEmail);

        Assert.assertEquals(testEmail, this.testUser.getEmail());
    }

    /**
     * {@link User#setEmail(String)}
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_setEmail_emailNull() {
        this.testUser.setEmail(null);
    }

    /**
     * {@link User#setEmail(String)}
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_setEmail_emailEmpty() {
        this.testUser.setEmail("");
    }

    /**
     * {@link User#setFollower(int)}
     */
    @Test
    public void test_setFollower_right() {
        // TODO: Fix test when code is updated.
//        this.followers.add(FOLLOWERID);
//        this.testUser.setFollower(FOLLOWERID);
//
//        Assert.assertEquals(this.followers, this.testUser.getFollowers());
    }

    /**
     * {@link User#setLocation(String)}
     */
    @Test
    public void test_setLocation_right() {
        this.testUser.setLocation(LOCATION);

        Assert.assertEquals(LOCATION, this.testUser.getLocation());
    }

    /**
     * {@link User#setLocation(String)}
     */
    @Test
    public void test_setLocation_rightEmptyString() {
        this.testUser.setLocation("");

        Assert.assertEquals("", this.testUser.getLocation());
    }

    /**
     * {@link User#setLocation(String)}
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_setLocation_LocationNull() {
        this.testUser.setLocation(null);
    }

    /**
     * {@link User#setName(String)}
     */
    @Test
    public void test_setName_right() {
        String testName = "nameTEST";
        this.testUser.setName(testName);

        Assert.assertEquals(testName, this.testUser.getName());
    }

    /**
     * {@link User#setName(String)}
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_setName_nameNull() {
        this.testUser.setName(null);
    }

    /**
     * {@link User#setName(String)}
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_setName_nameEmpty() {
        this.testUser.setName("");
    }

    /**
     * {@link User#setPassword(String)}
     */
    @Test
    public void test_setPassword_right() {
        String testPASSWORD = "passwordTEST";
        this.testUser.setPassword(testPASSWORD);

        Assert.assertEquals(testPASSWORD, this.testUser.getPassword());
    }

    /**
     * {@link User#setPassword(String)}
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_setPassword_passwordNull() {
        this.testUser.setPassword(null);
    }

    /**
     * {@link User#setPassword(String)}
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_setPassword_passwordEmpty() {
        this.testUser.setPassword("");
    }

    /**
     * {@link User#setProfilePicturePath(String)}
     */
    @Test
    public void test_setProfilePicturePath_right() {
        String testProfilePicturePath = "profilePicturePathTEST";
        this.testUser.setProfilePicturePath(testProfilePicturePath);

        Assert.assertEquals(testProfilePicturePath, this.testUser.getProfilePicturePath());
    }

    /**
     * {@link User#setProfilePicturePath(String)}
     */
    @Test
    public void test_setProfilePicturePath_rightEmpty() {
        this.testUser.setProfilePicturePath("");

        Assert.assertEquals("", this.testUser.getProfilePicturePath());
    }

    /**
     * {@link User#setProfilePicturePath(String)}
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_setProfilePicturePath_profilePicturePathNull() {
        this.testUser.setProfilePicturePath(null);
    }

    /**
     * {@link User#setRole(UserRole)}
     */
    @Test
    public void test_setRole_Right() {
        this.testUser.setRole(UserRole.ADMINISTRATOR );

        Assert.assertEquals(UserRole.ADMINISTRATOR, this.testUser.getRole());
    }

    /**
     * {@link User#setRole(UserRole)}
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_setRole_roleNull() {
        this.testUser.setRole(null);
    }

    /**
     * {@link User#setUsername(String)}
     */
    @Test
    public void test_setUsername_right() {
        String testUsername = "usernameTEST";
        this.testUser.setUsername(testUsername);

        Assert.assertEquals(testUsername, this.testUser.getUsername());
    }

    /**
     * {@link User#setUsername(String)}
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_setUsername_usernameNull() {
        this.testUser.setUsername(null);
    }

    /**
     * {@link User#setUsername(String)}
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_setUsername_usernameEmpty() {
        this.testUser.setUsername("");
    }

    /**
     * {@link User#setWebsite(String)}
     */
    @Test
    public void test_setWebsite_right() {
        this.testUser.setWebsite(WEBSITE);

        Assert.assertEquals(WEBSITE, this.testUser.getWebsite());
    }

    /**
     * {@link User#setWebsite(String)}
     */
    @Test
    public void test_setWebsite_rightWebsiteEmpty() {
        this.testUser.setWebsite("");
    }

    /**
     * {@link User#setWebsite(String)}
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_setWebsite_passwordNull() {
        this.testUser.setWebsite(null);
    }

    /**
     * {@link User#isModerator()}
     */
    @Test
    public void test_isModerator_true() {
        this.testUser.setRole(UserRole.ADMINISTRATOR);

        Assert.assertTrue(this.testUser.isModerator());
    }

    /**
     * {@link User#isModerator()}
     */
    @Test
    public void test_isModerator_False() {
        Assert.assertFalse(this.testUser.isModerator());
    }

    /**
     * {@link User#follow(User)}
     */
    @Test
    public void follow() {
        // TODO: add tests when method has been implemented.
    }

    /**
     * {@link User#unfollow(User)}
     */
    @Test
    public void unfollow() {
        // TODO: add tests when method has been implemented.
    }
}