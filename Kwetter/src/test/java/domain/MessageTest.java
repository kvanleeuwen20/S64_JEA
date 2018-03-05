/**
 * <&copy {@link domain.MessageTest.java}>
 * Copyright (c) Jeroen & Pim. All rights reserved.
 * <&copy>
 * <p>
 * Package
 */

/**
 * Package
 */
package domain;

/**
 * Imports
 */

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sun.util.resources.cldr.agq.CurrencyNames_agq;

import java.util.GregorianCalendar;

/**
 * The Junit tests for the {@link Message} class.
 *
 * @author Jeroen Janssen
 * @author Pim Janissen
 */
public class MessageTest {

    // Setup primary data types and strings.
    private static final String CONTENT = "TESTcontent";
    private static final String EMAIL = "TESTemail";
    private static final String NAME = "TESTname";
    private static final String PASSWORD = "TESTpasswword";
    private static final String PROFILEPICTUREPATH = "TESTprofilePicturePath";
    private static final String USERNAME = "TESTusername";

    // Setup variables.
    private final GregorianCalendar postTime = new GregorianCalendar();

    // Setup classes.
    private final User poster = new User(EMAIL, PASSWORD, USERNAME, NAME, PROFILEPICTUREPATH);


    @Before
    public void setUp() throws Exception {
        // Will be used if there needs to be an initialization before the tests are run.
    }

    @After
    public void tearDown() throws Exception {
        // Will be used if there needs to be something done after the tests are run.
    }

    /**
     * {@link Message#Message(String, GregorianCalendar, User)}
     */
    @Test
    public void test_constructor_right() {
        Message testMessage = new Message(CONTENT, postTime, poster);
        Assert.assertNotNull(testMessage);
    }

    /**
     * {@link Message#Message(String, GregorianCalendar, User)}
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_constructor_content_null(){
        new Message(null, postTime, poster);
    }

    /**
     * {@link Message#Message(String, GregorianCalendar, User)}
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_constructor_content_empty() {
        new Message("", postTime, poster);
    }

    /**
     * {@link Message#Message(String, GregorianCalendar, User)}
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_constructor_content_tooLong(){
        new Message("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", postTime, poster);
    }

    /**
     * {@link Message#Message(String, GregorianCalendar, User)}
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_constructor_postTime_null(){
        new Message(CONTENT, null, poster);
    }

    /**
     * {@link Message#Message(String, GregorianCalendar, User)}
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_constructor_postTime_future(){
        GregorianCalendar future = new GregorianCalendar();
        future.add(GregorianCalendar.DATE, 1);

        new Message(CONTENT, future, poster);
    }

    /**
     * {@link Message#Message(String, GregorianCalendar, User)}
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_constructor_poster_null(){
        new Message(CONTENT, postTime, null);
    }

//    @Test
//    public void getContent() {
//    }
//
//    @Test
//    public void setContent() {
//    }
//
//    @Test
//    public void getPostTime() {
//    }
//
//    @Test
//    public void getPoster() {
//    }
//
//    @Test
//    public void getHashtags() {
//    }
//
//    @Test
//    public void getMentions() {
//    }
//
//    @Test
//    public void getLikes() {
//    }
//
//    @Test
//    public void like() {
//    }
//
//    @Test
//    public void unlike() {
//    }
}
