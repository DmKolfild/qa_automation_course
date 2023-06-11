package com.example.serchselenium.pages;

import com.example.serchselenium.pages.mainpage.MainPage;
import com.example.serchselenium.pages.mainpage.MainPageBing;
import com.example.serchselenium.pages.mainpage.MainPageGoogle;
import com.example.serchselenium.pages.resultspage.ResultsPage;
import com.example.serchselenium.pages.resultspage.ResultsPageBing;
import com.example.serchselenium.pages.resultspage.ResultsPageGoogle;
import org.openqa.selenium.WebDriver;

public class PageFactory {

    public MainPage createMainPage(PagesType type, WebDriver driver) {
        MainPage mp = null;

        switch (type) {
            case BING:
                mp =  new MainPageBing(driver);
                break;
            case GOOGLE:
                mp = new MainPageGoogle(driver);
                break;
        }

        return mp;
    }

    public ResultsPage createResultsPage(PagesType type, WebDriver driver) {
        ResultsPage rp = null;

        switch (type) {
            case BING:
                rp =  new ResultsPageBing(driver);
                break;
            case GOOGLE:
                rp = new ResultsPageGoogle(driver);
                break;
        }

        return rp;
    }

}
