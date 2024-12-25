package Book;

import org.junit.runner.RunWith;

@RunWith(io.cucumber.junit.Cucumber.class)

@io.cucumber.junit.CucumberOptions(
        features = "src/BookCMS/resources/Features/Menu.feature",
        glue = {"StepDefinitions"},
        monochrome = true,
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty.html",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "timeline:target/test-output-thread/"
        },
        tags = "@Menu "
                + "or @MenuClick "
                + "or @SubmenuClick "
)
public class MenuRun {
}
