package com.epam.lab.manager;

import com.epam.lab.page.HomePageRozetka;
import com.epam.lab.page.SearchResultsPage;
import org.openqa.selenium.WebDriver;

public class PageFactoryManager {

    WebDriver driver;

    public PageFactoryManager(WebDriver driver) {
        this.driver = driver;
    }

    public HomePageRozetka getHomePageRozetka() { return new HomePageRozetka(driver); }

    public SearchResultsPage getSearchResultPage() { return  new SearchResultsPage(driver); }
}
