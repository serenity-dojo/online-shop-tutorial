package ecommerce.stepdefinitions;

import ecommerce.navigation.NavigateTo;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.questions.page.TheWebPage;
import net.serenitybdd.screenplay.ui.Link;
import org.openqa.selenium.By;

import static org.assertj.core.api.Assertions.assertThat;

public class NavigationStepDefinitions {

    @When("{actor} navigates to the {} section")
    public void sheNavigatesToTheSection(Actor actor, String sectionName) {
        actor.attemptsTo(NavigateTo.theSection(sectionName));
    }

    @Then("{actor} should be shown the {} page")
    public void sheShouldBeShownTheSectionURLPage(Actor actor, String expectedUrl) {
        String currentUrl = actor.asksFor(TheWebPage.currentUrl());
        assertThat(currentUrl).endsWith(expectedUrl);
    }
}
