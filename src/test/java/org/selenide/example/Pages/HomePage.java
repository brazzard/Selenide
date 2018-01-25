package org.selenide.example.Pages;

import static com.codeborne.selenide.Selenide.*;

public class HomePage {

    public SearchResultPage search(String query) {
        $("[name = 'q']").setValue(query).pressEnter();
        return page(SearchResultPage.class);
    }

}
