package PageObjects;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.DriverManager;

public class BasePage<T> {
	protected WebDriver driver;
	protected WebDriverWait waitDriver;

	

	public BasePage() {
		this.driver = DriverManager.getDriver();
		waitDriver = new WebDriverWait(driver, 30);
	}

	public T openPage(Class<T> clazz) {
		T page = null;
		try {
			driver = DriverManager.getDriver();
			page = PageFactory.initElements(driver, clazz);

		} catch (NoSuchElementException e) {

			throw new IllegalStateException(String.format("This is not the %s page", clazz.getSimpleName()));
		}
		return page;
	}

	public void waitForElement(WebElement e) {
		waitDriver.until(ExpectedConditions.visibilityOf(e));
	}

	public void waitForElementToClickable(WebElement e) {
		waitDriver.until(ExpectedConditions.elementToBeClickable(e));
	}

	public void waitForElementToVisible(By by) {
		waitDriver.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
	}

	public void waitForElementsToVisible(List<WebElement> e) {
		waitDriver.until(ExpectedConditions.visibilityOfAllElements(e));
	}

	public void switchToWindow() {
		Set<String> winids = driver.getWindowHandles();
		Iterator<String> itr = winids.iterator();
		String windowHandle = itr.next();
		driver.switchTo().window(windowHandle);
		
	}
	public String getCurrentURL()
	{
		Set<String> winids = driver.getWindowHandles();
		Iterator<String> itr = winids.iterator();
		String windowHandle = itr.next();
		String stringURL = driver.switchTo().window(windowHandle).getCurrentUrl();
		return stringURL;
	}

}
