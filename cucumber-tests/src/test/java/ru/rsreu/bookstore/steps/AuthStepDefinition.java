package ru.rsreu.bookstore.steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class AuthStepDefinition {
    @Given("пользователь зашел на главную страницу")
    public void пользовательЗашелНаГлавнуюСтраницу() {
        Selenide.open("http://localhost:8080");
        Selenide.sleep(2000);
    }

    @When("пользователь не авторизован")
    public void пользовательНеАвторизован() {
        var usernameSpanExists = Selenide.$x("/html/body/taglib/div[1]/span").exists();
        Assert.assertFalse(usernameSpanExists);
    }

    @And("нажимает кнопку Войти на главной странице")
    public void нажимаетКнопкуВойтиНаГлавнойСтранице() {
        var button = Selenide.$x("/html/body/taglib/div[1]/a/button");
        Assert.assertEquals("Войти", button.text());
        Assert.assertTrue(button.isDisplayed());
        button.click();
        Selenide.sleep(2000);
    }

    @Then("форма авторизации открыта")
    public void формаАвторизацииОткрыта() {
        var currentUrl = Selenide.webdriver().driver().url();
        Assert.assertEquals("http://localhost:8080/login", currentUrl);
    }

    @Given("пользователь находится на форме авторизации иначе войти")
    public void пользовательНаходитсяНаФормеАвторизацииИначеВойти() {
        var currentUrl = Selenide.webdriver().driver().url();
        if (!currentUrl.equals("http://localhost:8080/login")) {
            Selenide.open("http://localhost:8080/login");
        }
        Selenide.sleep(2000);
    }

    @When("пользователь вводит корректные данные")
    public void пользовательВводитКорректныеДанные() {
        var loginInput = Selenide.$x("/html/body/form/div[1]/input");
        Assert.assertTrue(loginInput.isDisplayed());
        loginInput.setValue("yura");
        Selenide.sleep(2000);
        var passwordInput = Selenide.$x("/html/body/form/div[2]/input");
        Assert.assertTrue(passwordInput.isDisplayed());
        passwordInput.setValue("123");
        Selenide.sleep(2000);
    }

    @And("нажимает кнопку Войти на странице авторизации")
    public void нажимаетКнопкуВойтиНаСтраницеАвторизации() {
        var button = Selenide.$x("/html/body/form/div[3]/input");
        Assert.assertTrue(button.isDisplayed());
        button.click();
        Selenide.sleep(2000);
    }

    @Then("открыта главная страница")
    public void открытаГлавнаяСтраница() {
        var currentUrl = Selenide.webdriver().driver().url();
        Assert.assertEquals("http://localhost:8080/", currentUrl);
    }

    @And("пользователь авторизован")
    public void пользовательАвторизован() {
        var usernameSpan = Selenide.$x("/html/body/taglib/div[1]/span");
        Assert.assertTrue(usernameSpan.isDisplayed());
        Assert.assertEquals("yura", usernameSpan.text());
    }
}
