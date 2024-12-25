package Book;

import org.junit.runner.RunWith;

@RunWith(io.cucumber.junit.Cucumber.class)

@io.cucumber.junit.CucumberOptions(
        features = "src/BookCMS/resources/Features/Book.feature",
        glue = {"StepDefinitions"},
        monochrome = true,
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty.html",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "timeline:target/test-output-thread/"
        },
        tags = "@BookAPI "
//                + "or @RequireInputEmail_ForgotPass "
//                + "or @ValidEmailInput_ForgotPass "
//                + "or @EmailMustBeAnEmail_ForgotPass "
//                + "or @AccountDoesNotExist_ForgotPass "
//                + "or @SendLinkSuccessful "
)
public class Book {
}
