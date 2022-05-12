package ecommerce.stepdefinitions;

import ecommerce.domain.HighlightedProduct;
import ecommerce.homepage.HighlightedProducts;
import ecommerce.navigation.NavigateTo;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HomePageStepDefinitions {

    @Given("{actor} has opened the application home page")
    public void opened_the_application_home_page(Actor actor) {
        actor.wasAbleTo(NavigateTo.theApplicationHomePage());
    }

    List<HighlightedProduct> highlightedProducts;

    @When("{actor} views the highlighted products")
    public void views_the_highlighted_products(Actor actor) {
        highlightedProducts = actor.asksFor(HighlightedProducts.currentlyVisible());
    }

    @Then("she should see an image, title and price for each product")
    public void she_should_see_an_image_title_and_price_for_each_product() {
        assertThat(highlightedProducts).isNotEmpty();

        assertThat(highlightedProducts).allMatch(
                product -> !product.price().isEmpty() && !product.image().isEmpty() && !product.title().isEmpty()
        );
    }
}
