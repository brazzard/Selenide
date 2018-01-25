package org.selenide.example.Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class SearchResultPage {

    public List<String> searchLinks() {
        $("#resultStats").waitUntil(Condition.visible, 8000);
        ElementsCollection links = $$("#res h3 a");
        List<String> linksList = new ArrayList<String>();
        for (int i = 0; i<links.size(); i++) {
            linksList.add(links.get(i).getAttribute("href"));
        }
        return linksList;
    }

    public OpenedPage firstPage() {
        $("#res h3 a").click();
        return page(OpenedPage.class);
    }

    public SearchResultPage NextPage() {
        $("div.mw a#pnnext").click();
        return page(SearchResultPage.class);
    }


}
