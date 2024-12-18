package hooks;

import java.time.Duration;

import org.config.ConfigReader;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class MyHooks extends BaseClass {
	@Before
	public void beforeScenario() {
		
		setup("chrome");
	    ConfigReader config = new ConfigReader();
		String url = config.getUrl();
		driver.get(url);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));

	}

	@After
	public void afterScenario() throws InterruptedException {
		Thread.sleep(5000);
		quit();
	}
}
 