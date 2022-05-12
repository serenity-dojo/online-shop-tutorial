package ecommerce.navigation;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Open;

public class NavigateTo {
    public static Performable theApplicationHomePage() {
        return Open.browserOn().the(EcommerceHomePage.class);
    }
}
