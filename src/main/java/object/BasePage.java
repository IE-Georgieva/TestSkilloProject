package object;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage extends BaseComponent {

    protected WebDriverWait webDriverWait;

    public BasePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void performWait() {

        webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(15));


    }


}

