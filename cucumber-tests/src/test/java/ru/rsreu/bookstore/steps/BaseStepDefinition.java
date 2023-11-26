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
    public void user_opened_page(String pageUrl) {
        Selenide.open(getAbsoluteUrl(pageUrl));
        Selenide.sleep(5000);
    }

    @When("пользователь нажимает кнопку {string}")
    public void user_pressed_button(String buttonValue) {
        var xPath = "//button[text()='" + buttonValue + "']";
        var button = Selenide.$x(xPath);
        button.click();
    }

    @Then("пользователь нажимает на ссылку {string}")
    public void user_pressed_link(String linkValue) {
        var xPath = "//a[text()='" + linkValue + "']";
        var link = Selenide.$x(xPath);
        link.click();
    }

    @Then("открыта страница {string}")
    public void page_is_open(String pageUrl) {
        var currentUrl = Selenide.webdriver().driver().url();
        var expectedUrl = getAbsoluteUrl(pageUrl);
        Assert.assertTrue(currentUrl.startsWith(expectedUrl));
    }

    @And("пользователь авторизован под логином {string}")
    public void user_authorized_under_login(String login) {
        var xPath = "//span[text()='" + login + "']";
        var span = Selenide.$x(xPath);
        Assert.assertTrue(span.isDisplayed());
    }

    @And("пользователь не авторизован")
    public void user_not_authorized() {
        var xPath = "//span";
        var span = Selenide.$x(xPath);
        Assert.assertFalse(span.exists());
    }

    private String getAbsoluteUrl(String relativeUrl) {
        return host + relativeUrl;
    }

    @When("пользователь вводит в поле ввода {string} значение {string}")
    public void user_enters_a_value_in_input_field(String inputId, String value) {
        var xPath = "//input[@id='" + inputId + "']";
        var input = Selenide.$x(xPath);
        input.setValue(value);
    }

    @And("выведено сообщение {string}")
    public void message_is_displayed(String message) {
        var xPath = "//*[contains(text(), '" + message + "')]";
        var messageElement = Selenide.$x(xPath);
        Assert.assertTrue(messageElement.isDisplayed());
    }

    @And("пользователь нажимает кнопку формы {string}")
    public void user_press_form_button(String buttonValue) {
        var xPath = "//input[@value='" + buttonValue + "']";
        var button = Selenide.$x(xPath);
        button.click();
    }
}
