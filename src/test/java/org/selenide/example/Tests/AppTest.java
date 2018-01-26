package org.selenide.example.Tests;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Assert;
import org.selenide.example.Pages.HomePage;
import org.selenide.example.Pages.OpenedPage;
import org.selenide.example.Pages.SearchResultPage;


import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

/**
 * Unit test for simple App.
 */
public class AppTest
    extends TestCase
{
    String keyword = "automation";
    String baseUrl = "https://google.com";
    String expectedUrl = "https://testautomationday.com”)";

    @org.junit.Test
    public void testFirstLink() {
        HomePage homePage = open(baseUrl, HomePage.class);
        SearchResultPage searchResultPage = homePage.search(keyword);
        OpenedPage openedPage = searchResultPage.firstPage();
        Assert.assertTrue(openedPage.getTitle().toLowerCase().contains(keyword));
    }

    @org.junit.Test
    public void testSearchResult() {
        List<String> listOfLinks = new ArrayList<String>();
        int numberOfPages = 5;
        HomePage homePage = open(baseUrl, HomePage.class);
        SearchResultPage searchResultPage = homePage.search(keyword);
        for (int i = 0; i < numberOfPages; i++ ) {
            listOfLinks.addAll(searchResultPage.searchLinks());
            searchResultPage.NextPage();
        }
        Assert.assertTrue(listOfLinks.contains(expectedUrl));

    }



    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() { assertTrue( true ); }
}
