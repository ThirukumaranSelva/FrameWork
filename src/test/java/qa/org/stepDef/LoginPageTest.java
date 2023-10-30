package qa.org.stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import qa.org.pages.LoginPage;

public class LoginPageTest {

    @When("user enters invalid username {string} and password {string}")
    public void userEntersInvalidUsernameAndPassword(String username, String password) {
        new LoginPage().sendUsername(username).sendPassword(password);
    }

    @Then("Click Login Button")
    public void clickLoginButton() {
        new LoginPage().clickLoginButton();
    }

    @And("Verify user gets Error Message {string}")
    public void verifyUserGetsErrorMessage(String error) {
            Assert.assertEquals(error,new LoginPage().errorMessage());
        }


}
