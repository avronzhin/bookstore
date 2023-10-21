package ru.rsreu.bookstore.steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class BaseStepDefinition {
    private final static String host = "http://localhost:8080";

    @Given("пользователь открыл страницу {string}")
    public void openPage(String pageUrl) {
        Selenide.open(getAbsoluteUrl(pageUrl));
        Selenide.sleep(5000);
    }

    @When("пользователь нажимает кнопку {string}")
    public void pressButton(String buttonValue) {
        var xPath = "//button[text()='" + buttonValue + "']";
        var button = Selenide.$x(xPath);
        button.click();
    }

    @Then("пользователь нажимает на ссылку {string}")
    public void pressLink(String linkValue){
        var xPath = "//a[text()='" + linkValue + "']";
        var link = Selenide.$x(xPath);
        link.click();
    }

    @Then("открыта страница {string}")
    public void pageIsOpen(String pageUrl) {
        var currentUrl = Selenide.webdriver().driver().url();
        var expectedUrl = getAbsoluteUrl(pageUrl);
        Assert.assertEquals(expectedUrl, currentUrl);
    }

    @And("пользователь авторизован под логином {string}")
    public void userAuthorized(String login) {
        var xPath = "//span[text()='" + login + "']";
        var span = Selenide.$x(xPath);
        Assert.assertTrue(span.isDisplayed());
    }

    @And("пользователь не авторизован")
    public void userNotAuthorized() {
        var xPath = "//span";
        var span = Selenide.$x(xPath);
        Assert.assertFalse(span.exists());
    }

    private String getAbsoluteUrl(String relativeUrl) {
        return host + relativeUrl;
    }

    @When("пользователь вводит в поле ввода {string} значение {string}")
    public void addValueInInput(String inputId, String value) {
        var xPath = "//input[@id='" + inputId + "']";
        var input = Selenide.$x(xPath);
        input.setValue(value);
    }

    @And("выведено сообщение {string}")
    public void messageIsDisplayed(String message) {
        var xPath = "//*[contains(text(), '" + message + "')]";
        var messageElement = Selenide.$x(xPath);
        Assert.assertTrue(messageElement.isDisplayed());
    }

    @And("пользователь нажимает кнопку формы {string}")
    public void pressFormButton(String buttonValue) {
        var xPath = "//input[@value='" + buttonValue + "']";
        var button = Selenide.$x(xPath);
        button.click();
    }
}
