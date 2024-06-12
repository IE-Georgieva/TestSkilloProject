package object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Search {

    private final WebDriver webDriver;

    public Search(WebDriver driver) {
        this.webDriver = driver;
        PageFactory.initElements(webDriver, this);
    }
}
