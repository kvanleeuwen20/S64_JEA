// <&copy {@link domain.MessageTest.java}>
// Copyright (c) Jeroen & Pim. All rights reserved.
// <&copy>

// Package
package domain;

// Imports
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

/**
 * The Junit tests for the {@link Message} class.
 *
 * @author Jeroen Janssen
 * @author Pim Janissen
 */
public class MessageTest {

    // Setup primary data types and strings.
    private static final String CONTENT = "TESTcontent";
    private static final String EMAIL = "TESTemail@email.com";
    private static final String NAME = "TESTname";
    private static final String PASSWORD = "TESTpasswword";
    private static final String PROFILEPICTUREPATH = "TESTprofilePicturePath";
    private static final String USERNAME = "TESTusername";

    // Setup variables.
    private final GregorianCalendar postTime = new GregorianCalendar();
    private Set<String> hashtags;
    private Set<User> likes;
    private Set<User> mentions;

    // Setup classes.
    private final User poster = new User(EMAIL, PASSWORD, USERNAME, NAME, PROFILEPICTUREPATH);

    // Setup testMessage.
    private Message testMessage = null;

    /**
     * The class which handles the setup before a test.
     *
     * @throws Exception catches all unexpected exceptions.
     */
    @Before
    public void setUp() throws Exception {
        try {
            // Variables.
            hashtags = new HashSet<>();
            likes = new HashSet<>();
            mentions = new HashSet<>();

            // testMessage.
            testMessage = new Message(CONTENT, postTime, poster);
        } catch (Exception ex) {
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
            // Variables.
            hashtags = null;
            likes = null;
            mentions = null;

            // testMessage.
            testMessage = null;
        } catch (Exception ex) {
            throw new Exception("An exception has occurred in the tearDown");
        }
    }

    /**
     * {@link Message#Message(String, GregorianCalendar, User)}
     */
    @Test
    public void test_constructor_right() {
        Message assertMessage = new Message(CONTENT, postTime, poster);
        assertMessage = null;
        Assert.assertNotNull(assertMessage);
    }

    /**
     * {@link Message#Message(String, GregorianCalendar, User)}
     */
    @Test
    public void test_constructor_right_content140() {
        String testContent = new String(new char[140]).replace('\0', 'a');

        Message assertMessage = new Message(testContent, postTime, poster);
        Assert.assertNotNull(assertMessage);
    }

    /**
     * {@link Message#Message(String, GregorianCalendar, User)}
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_constructor_contentNull() {
        new Message(null, postTime, poster);
    }

    /**
     * {@link Message#Message(String, GregorianCalendar, User)}
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_constructor_contentEmpty() {
        new Message("", postTime, poster);
    }

    /**
     * {@link Message#Message(String, GregorianCalendar, User)}
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_constructor_contentTooLong() {
        String testString = new String(new char[141]);

        new Message(testString, postTime, poster);
    }

    /**
     * {@link Message#Message(String, GregorianCalendar, User)}
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_constructor_postTimeNull() {
        new Message(CONTENT, null, poster);
    }

    /**
     * {@link Message#Message(String, GregorianCalendar, User)}
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_constructor_postTimeFuture() {
        GregorianCalendar testCalendar = new GregorianCalendar();
        testCalendar.add(GregorianCalendar.DATE, 1);

        new Message(CONTENT, testCalendar, poster);
    }

    /**
     * {@link Message#Message(String, GregorianCalendar, User)}
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_constructor_posterNull() {
        new Message(CONTENT, postTime, null);
    }

    /**
     * {@link Message#getContent()}
     */
    @Test
    public void test_getContent_right() {
        Assert.assertEquals(CONTENT, testMessage.getContent());
    }

    /**
     * {@link Message#getHashtags()}
     */
    @Test
    public void test_getHashtags_right_empty() {
        Assert.assertEquals(hashtags, testMessage.getHashtags());
    }

    /**
     * {@link Message#getHashtags()}
     */
    @Test
    public void test_getHashtags_right_filled() {
        // TODO: add test after the hashtag functionality has been added.
    }

    /**
     * {@link Message#getLikes()}
     */
    @Test
    public void test_getLikes_right_empty() {
        Assert.assertEquals(likes, testMessage.getLikes());
    }

    /**
     * {@link Message#getLikes()}
     */
    @Test
    public void test_getLikes_right_filled() {
        // TODO: add test after the like functionality has been added.
    }

    /**
     * {@link Message#getMentions()}
     */
    @Test
    public void test_getMentions_right_empty() {
        Assert.assertEquals(mentions, testMessage.getMentions());
    }

    /**
     * {@link Message#getMentions()}
     */
    @Test
    public void test_getMentions_right_filled() {
        //TODO: add test after the hashtag functionality has been added.
    }

    /**
     * {@link Message#getPoster()}
     */
    @Test
    public void test_getPoster_right() {
        Assert.assertEquals(poster, testMessage.getPoster());
    }

    /**
     * {@link Message#getPostTime()}
     */
    @Test
    public void test_getPostTime_right() {
        Assert.assertEquals(postTime, testMessage.getPostTime());
    }

    /**
     * {@link Message#setContent(String)}
     */
    @Test
    public void test_setContent_right() {
        String testContent = "setContentTest";

        testMessage.setContent(testContent);

        Assert.assertEquals(testContent, testMessage.getContent());
    }

    /**
     * {@link Message#setContent(String)}
     */
    @Test
    public void test_setContent_right_140() {
        String testContent = new String(new char[140]).replace('\0', 'a');

        testMessage.setContent(testContent);

        Assert.assertEquals(testContent, testMessage.getContent());
    }

    /**
     * {@link Message#setContent(String)}
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_setContent_null() {
        testMessage.setContent(null);
    }

    /**
     * {@link Message#setContent(String)}
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_setContent_empty() {
        testMessage.setContent("");
    }

    /**
     * {@link Message#setContent(String)}
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_setContent_tooLong() {
        String testContent = new String(new char[141]);

        testMessage.setContent(testContent);
    }
}
