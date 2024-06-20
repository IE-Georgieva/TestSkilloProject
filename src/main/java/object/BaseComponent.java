package object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public abstract class BaseComponent {

    protected WebDriver webDriver;
    protected Header header;

    public BaseComponent(Header header) {
        this.header = header;
    }

    public BaseComponent(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(this.webDriver, this);
    }

    protected WebDriverWait webDriverWait;

    public void performWait() {
        webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(15));

    }
}
