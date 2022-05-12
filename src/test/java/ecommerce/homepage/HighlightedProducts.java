package ecommerce.homepage;

import ecommerce.domain.HighlightedProduct;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.By;

import java.util.List;

public class HighlightedProducts {

    public static Question<List<HighlightedProduct>> currentlyVisible() {
        return Question.about("currently visible highlighted products")
                .answeredBy(
                        actor -> BrowseTheWeb.as(actor)
                                .findAll(By.cssSelector("[class='ProductCard_root__HqXTt']"))
                                .map(webElement -> {
                                    String title = webElement.find(By.tagName("h3")).getText();
                                    String image = webElement.find(By.tagName("img")).getAttribute("alt");
                                    String price = webElement.find(By.cssSelector("[class^='ProductTag_price']")).getText();
                                    return new HighlightedProduct(title, image, price);
                                })
                );
    }
}
