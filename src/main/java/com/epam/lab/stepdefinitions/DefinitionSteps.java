package com.epam.lab.stepdefinitions;

import com.epam.lab.manager.PageFactoryManager;
import com.epam.lab.page.HomePageRozetka;
import com.epam.lab.page.SearchResultsPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;

public class DefinitionSteps {


    private static final long DEFAULT_TIMEOUT = 60;

    WebDriver driver;
    PageFactoryManager pageFactoryManager;
    HomePageRozetka homePageRozetka;
    SearchResultsPage searchResultsPage;

    @Before
    public void testsSetUp() {
        chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        pageFactoryManager = new PageFactoryManager(driver);
    }

    @After
    public void tearDown() {
        driver.close();
    }

    @And("User opens {string} page")
    public void openHomePage(final String url) {
        homePageRozetka = pageFactoryManager.getHomePageRozetka();
        homePageRozetka.openHomePage(url);
        homePageRozetka.waitForPageLoadComplete(DEFAULT_TIMEOUT);
    }

    @And("User searches for a product by its name {string}")
    public void userSearchesForAProduct(final String product) {
        homePageRozetka.enterTextToSearchField(product);
    }

    @And("User clicks search button")
    public void userClicksSearchButton() {
        homePageRozetka.clickSearchButton();
    }

    @And("User checks that the search field is visible")
    public void userChecksThatSearchFieldIsVisible() {
        searchResultsPage = pageFactoryManager.getSearchResultPage();
        searchResultsPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        searchResultsPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, searchResultsPage.getSearchBrandField());
        Assert.assertTrue(searchResultsPage.isSearchBrandFieldVisible());
    }

    @And("User enters the brand name {string} in the search field")
    public void userEntersBrandInTheSearchField(final String brand) throws InterruptedException {
        searchResultsPage.enterTextToSearchBrandField(brand);
        Thread.sleep(3000);
    }

    @And("User checks that the checkbox is visible and click on first one")
    public void userChecksThatCheckboxIsVisible() {
        searchResultsPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, searchResultsPage.getListCheckBox());
        Assert.assertTrue(searchResultsPage.isListCheckBoxVisible());
        Assert.assertTrue(searchResultsPage.isListCheckBoxEnabled());
        searchResultsPage.clickListCheckBox();
    }

    @And("User clicks on the filter dropdown")
    public void clickOnTheFilterDropdown() {
        searchResultsPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, searchResultsPage.getFilterDropDown());
        searchResultsPage.clickFilterDropDown();
    }

    @And("User selects from expensive to cheap")
    public void selectFromExpensiveToCheap() throws InterruptedException {
        searchResultsPage.clickFromExpensiveToCheap();
        searchResultsPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        Thread.sleep(3000);
    }

    @When("User adds the first product from the list to the cart")
    public void userAddsFirstProductFromListToTheCart() {
        searchResultsPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, searchResultsPage.isCartIconVisible());
        searchResultsPage.clickListOfCartIcons();
        searchResultsPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        searchResultsPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, searchResultsPage.getCartButton());
    }

    @And("User opens a shopping cart")
    public void userOpensShoppingCart() {
        searchResultsPage.clickOnCartButton();
    }

    @Then("User compares the given price {string} with the price of the item in the cart")
    public void userComparesGivenPriceWithPriceOfTheItemInCart(final String sum) {
        searchResultsPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, searchResultsPage.getPrice());
        Assert.assertTrue(searchResultsPage.isPriceVisible());
        Assert.assertTrue(Integer.parseInt(sum) < searchResultsPage.getTextFromPrice());
    }
}
