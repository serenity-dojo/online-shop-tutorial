package toasters;

import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.annotations.CastMember;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.questions.Visibility;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;

@ExtendWith(SerenityJUnit5Extension.class)
class WhenDealingWithToasterMessages {

    @CastMember
    Actor sally;

    @Nested
    class WhenLoggingOnToTheApplication {

        @Test
        void invalidCredentialsShouldDisplayAnErrorMessage() {
            sally.attemptsTo(
                    Open.url("https://auth.horiz.io/"),
                    Enter.theValue("wrong-name").into("#username"),
                    Enter.theValue("wrong-password").into("#password"),
                    Click.on("#doSubmit"),

                    Ensure.that(By.cssSelector(".toast.error")).isDisplayed(),
                    Ensure.that(Visibility.of(".toast.error")).isTrue(),
                    Ensure.that(Text.of(".toast.error")).contains("Nom d'utilisateur ou mot de passe invalide.")
            );
        }

        @Test
        void missingCredentialsShouldDisplayAnErrorMessage() {
            sally.attemptsTo(
                    Open.url("https://auth.horiz.io/"),
                    Enter.theValue("").into("#username"),
                    Enter.theValue("wrong-password").into("#password"),
                    Click.on("#doSubmit"),

                    WaitUntil.the(".toast.error", WebElementStateMatchers.isVisible()).forNoMoreThan(10).seconds(),

                    Ensure.that(By.cssSelector(".toast.error")).isDisplayed(),
                    Ensure.that(Visibility.of(".toast.error")).isTrue(),
                    Ensure.that(Text.of(".toast.error")).contains("Remplissez d’abord les champs du formulaire.")
            );
        }

        @Test
        void errorMessageDissapearsWhenTheUserClicksOnIt() {
            sally.attemptsTo(
                    Open.url("https://auth.horiz.io/"),
                    Enter.theValue("").into("#username"),
                    Enter.theValue("wrong-password").into("#password"),
                    Click.on("#doSubmit"),

                    WaitUntil.the(".toast.error", WebElementStateMatchers.isVisible()).forNoMoreThan(10).seconds(),

                    Click.on(".toast.error"),

                    WaitUntil.the(".toast.error", WebElementStateMatchers.isNotVisible()).forNoMoreThan(10).seconds(),

                    Ensure.that(By.cssSelector(".toast.error")).isNotDisplayed()
            );
        }

    }


}
