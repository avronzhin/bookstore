package ru.rsreu;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "ru.rsreu.bookstore.steps",
        plugin = {"pretty", "html:target/cucumber",
                "junit:target/surefire-reports/junit-report.xml"})
public class RunnerTest {

}
