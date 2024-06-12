package object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Footer {
    private final WebDriver webDriver;

    public Footer(WebDriver driver) {
        this.webDriver = driver;
        PageFactory.initElements(webDriver, this);
    }
}
