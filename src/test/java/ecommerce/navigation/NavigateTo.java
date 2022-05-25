package ecommerce.navigation;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.conditions.Check;
import net.serenitybdd.screenplay.questions.CurrentVisibility;
import org.openqa.selenium.By;

public class NavigateTo {
    private static final By HAMBURGER_MENU = By.xpath("(//button[@aria-label='Menu'])[2]");

    public static Performable theApplicationHomePage() {
        return Open.browserOn().the(EcommerceHomePage.class);
    }

    public static Performable theSection2(String sectionName) {
        return Task.where("{0} navigates to the " + sectionName + " section",
                Check.whether(CurrentVisibility.of(By.linkText(sectionName)))
                        .andIfSo(
                                Click.on(By.linkText(sectionName))
                        )
                        .otherwise(
                                Click.on(HAMBURGER_MENU),
                                Click.on(By.linkText(sectionName))
                        )
        );
    }

    public static Performable theSection(String sectionName) {
        return Task.where("{0} navigates to the " + sectionName + " section",
                actor -> {
                    boolean menuItemsVisible = actor.asksFor(CurrentVisibility.of(By.linkText(sectionName)));

                    if (menuItemsVisible) {
                        actor.attemptsTo(
                                Click.on(By.linkText(sectionName))
                        );
                    } else {
                        actor.attemptsTo(
                                Click.on(HAMBURGER_MENU),
                                Click.on(By.linkText(sectionName))
                        );
                    }
                }
        );

    }
}
