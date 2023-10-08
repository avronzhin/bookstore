package ru.rsreu.bookstore.steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;


public class AfterStepAction {
    @After
    public void after(){
        Selenide.closeWebDriver();
    }

    @AfterStep
    public void afterStep(){
        Selenide.sleep(1000);
        String ignored = Selenide.screenshot("step" + System.currentTimeMillis());
    }
}
