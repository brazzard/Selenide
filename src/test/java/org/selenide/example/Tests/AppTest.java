package org.selenide.example.Tests;

import io.qameta.allure.*;
import org.junit.*;
import org.selenide.example.Pages.HomePage;
import org.selenide.example.Pages.OpenedPage;
import org.selenide.example.Pages.SearchResultPage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.CaptureType;
import org.selenide.example.Proxy.Bmp;

import static com.codeborne.selenide.Selenide.*;

/**
 * Unit test for simple App.
 */

public class AppTest {
   private String keyword = "automation";
   private String baseUrl = "https://google.com";
   private String expectedUrl = "https://testautomationday.com";
   private String harPath = "/Users/Brazzard/Downloads/har/automation.har";

    @Test
    @Story("Search results page")
    @Severity(SeverityLevel.CRITICAL)
    public void testFirstLink() {
        HomePage homePage = open(baseUrl, HomePage.class);
        SearchResultPage searchResultPage = homePage.search(keyword);
        OpenedPage openedPage = searchResultPage.firstPage();

        Assert.assertTrue("First link doesn't contain searched word", openedPage.getTitle().toLowerCase().contains(keyword));
    }

    @Test
    @Story("Search results page")
    @Severity(SeverityLevel.MINOR)
    public void testSearchResult() {
        List<String> listOfLinks = new ArrayList<String>();
        int numberOfPages = 5;
        HomePage homePage = open(baseUrl, HomePage.class);
        SearchResultPage searchResultPage = homePage.search(keyword);
        for (int i = 0; i < numberOfPages; i++ ) {
            listOfLinks.addAll(searchResultPage.searchLinks());
            searchResultPage.NextPage();
        }
        Assert.assertTrue("Expected link "+expectedUrl+" not found", listOfLinks.contains(expectedUrl));

    }

    @Test
    public void ProxySetup() {
        //proxy setup
        Bmp.proxyServer = new BrowserMobProxyServer();
        Bmp.proxyServer.start(0);
        Bmp.proxyServer.setHarCaptureTypes(CaptureType.getAllContentCaptureTypes());
        Bmp.proxyServer.newHar("https://www.google.com.ua/search?q=automation");

        //get automation har
        open(baseUrl);
        $("[name = 'q']").setValue(keyword).pressEnter();
        Har automationHar = Bmp.proxyServer.getHar();
        try {
            automationHar.writeTo(new File(harPath));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //get another resource har :)
//        open("https://www.google.com.ua");
//        $("[name = 'q']").setValue("porn").pressEnter();
//        Bmp.proxyServer.addResponseFilter(new ResponseFilter() {
//            @Override
//            public void filterResponse(HttpResponse httpResponse, HttpMessageContents httpMessageContents, HttpMessageInfo httpMessageInfo) {
//                httpMessageContents.setTextContents(automationHar.toString());
//            }
//        });
    }


}
