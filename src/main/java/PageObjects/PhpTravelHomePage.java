package PageObjects;

import java.util.ArrayList;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;

import org.openqa.selenium.Keys;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.BookingItems;
import utilities.DriverManager;

public class PhpTravelHomePage extends BasePage {

	List<BookingItems> bookingItems = null;
	Actions actions = new Actions(driver);
	WebDriverWait wait = new WebDriverWait(driver, 30);

	@FindBy(css = "li:nth-child(2) > a > span.hidden-xs")
	public WebElement flight;

	@FindBy(how = How.ID, using = "s2id_location_from")
	public WebElement from;

	@FindBy(how = How.ID, using = "s2id_location_to")
	public WebElement to;

	@FindBy(how = How.XPATH, using = "//div[@class='datepicker dropdown-menu' and contains(@style,'display: block')]")
	public WebElement datePickerDiv;

	@FindBy(how = How.ID, using = "select2-drop")
	public WebElement overlayDiv;

	@FindBy(how = How.CSS, using = "#flights > form > div:nth-child(3) > div > input")
	public WebElement dept;

	@FindBy(how = How.CSS, using = "div.col-md-1.form-group.go-right.col-xs-12 > div > input")
	public WebElement selectPassengers;

	@FindBy(how = How.CSS, using = "select[class='travellercount form-control'][name='madult']")
	public WebElement selectAdults;

	@FindBy(how = How.CSS, using = "select[class='travellercount form-control'][name='mchildren']")
	public WebElement selectChild;

	@FindBy(how = How.CSS, using = "button#sumManualPassenger")
	public WebElement done;

	@FindBy(how = How.CSS, using = "button[class='btn-primary btn btn-lg btn-block pfb0'][type='submit']")
	public WebElement search;

	@FindBy(how = How.XPATH, using = "//input[@type='checkbox' and contains(@class,'airlines_filter')]")
	public List<WebElement> airlineFilterCheckBoxes;

	@FindBy(how = How.CSS, using = "table#load_data tbody tr td div.return-class p.listing-price")
	public List<WebElement> allBookingItems;

	@FindBy(css = "div:nth-child(2) > div > div > ins")
	public WebElement roundTrip;
	
	
	@FindBy(how = How.CSS, using = "span.strong")
	public WebElement price;
	
	
	@FindBy(how = How.ID, using = "bookbtn")
	public WebElement bookNow;
	
	

	public PhpTravelHomePage open(String url) {

		System.out.println("Page Opened");
		DriverManager.getDriver().navigate().to(url);
		return (PhpTravelHomePage) openPage(PhpTravelHomePage.class);
	}

	public void goToFlight() {
		System.out.println("Go To Flight");
		flight.click();
	}

	public void selectFrom(String str) {

		waitForElement(from);
		from.click();

		WebElement overlayText = overlayDiv.findElement(By.className("select2-input"));
		overlayText.sendKeys(str);

		WebElement overlayList = overlayDiv.findElement(By.className("select2-results"));
		wait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.cssSelector("#select2-drop ul.select2-results li.select-no-results")));
		WebElement selectedLi = overlayList.findElement(By.tagName("li"));
		if (selectedLi != null) {
			selectedLi.click();
		}

	}

	public void selectTo(String str)  {
		waitForElement(to);

		//actions.moveToElement(to).build().perform();
		to.click();

		WebElement overlayText = overlayDiv.findElement(By.className("select2-input"));
		overlayText.sendKeys(str);

		WebElement overlayList = overlayDiv.findElement(By.className("select2-results"));
		wait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.cssSelector("#select2-drop ul.select2-results li.select-no-results")));
		WebElement selectedLi = overlayList.findElement(By.tagName("li"));
		if (selectedLi != null)
			selectedLi.click();
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("select2-drop")));

	}

	public void clickOnRoundTrip() {
		roundTrip.click();

	}

	public void selectDeptDate() {
		waitForElement(dept);
		dept.click();

		if (datePickerDiv != null) {
			List<WebElement> dayCells = datePickerDiv
					.findElements(By.cssSelector("div.datepicker-days table.table-condensed tbody td.day"));
			int count = -1;
			for (WebElement cell : dayCells) {
				if (cell.getAttribute("class").trim().contains("day  active")) {
					count = 0;
				} else {
					if (count >= 0 && count <= 13)
						count = count + 1;

					if (count == 14) {
						cell.click();
						break;
					}
				}
			}
		}

	}

	public void selectReturnDate() {

		if (datePickerDiv != null) {
			List<WebElement> dayCells = datePickerDiv
					.findElements(By.cssSelector("div.datepicker-days table.table-condensed tbody td.day"));
			int count = -1;

			for (WebElement cell : dayCells) {
				if (cell.getAttribute("class").trim().contains("day  active")) {
					count = 0;
				} else {
					if (count >= 0 && count <= 13)
						count = count + 1;

					if (count == 14) {
						cell.click();
						break;
					}
				}
			}
		}

	}

	public void clickOnPassengers() {
		selectPassengers.click();

	}

	public void selectAdults(int adults) {

		switchToWindow();
		String s = String.valueOf(adults);
		waitForElement(selectAdults);
		Select dropdown = new Select(selectAdults);
		dropdown.selectByValue(s);

	}

	public void selectChild(int child) {

		String s = String.valueOf(child);
		waitForElement(selectChild);
		Select dropdown = new Select(selectChild);
		dropdown.selectByValue(s);
		done.click();

	}

	public void clickOnSearch() {
		waitForElement(search);
		waitForElementToClickable(search);
        actions.moveToElement(search).build().perform();
		search.click();

	}

	public void selectAirlines(String s) {
		try {
			if (airlineFilterCheckBoxes != null) {
				for (WebElement airlineFilterCheckBox : airlineFilterCheckBoxes) {
					if (airlineFilterCheckBox.getAttribute("value").contains(s)) {
						airlineFilterCheckBox.sendKeys(Keys.SPACE);
						break;
					}
				}

			}
		} catch (ElementNotVisibleException ex) {
			System.out.println(ex.getMessage() + ex.getStackTrace());
		}

	}

	public void clickOnBook() {

		bookingItems = new ArrayList<BookingItems>();
		for (Iterator<WebElement> w = allBookingItems.iterator(); w.hasNext();) {
			WebElement item = w.next();

			actions.moveToElement(item).perform();
			BookingItems bookingItem = new BookingItems();

			
			System.out.println(item.getAttribute("innerHTML"));

			if (price != null) {
				String priceString = price.getText();
				priceString = priceString.replace("$", "");
				System.out.println(priceString);
				try {
					bookingItem.Price = Double.parseDouble(priceString);
				} catch (NumberFormatException ex) {
					System.out.println("Error in parsing price" + priceString);
				}
			}
			if (bookNow != null) {
				bookingItem.BookNowButton = bookNow;
			}
			bookingItems.add(bookingItem);
		}

		List<BookingItems> sortedItems = new ArrayList<BookingItems>(bookingItems);
		Comparator<BookingItems> priceComparer = new Comparator<BookingItems>() {
			public int compare(BookingItems s1, BookingItems s2) {
				return (int) (s1.Price - s2.Price);
			}
		};

		sortedItems.sort(priceComparer);
		sortedItems.get(0).BookNowButton.click();

	}

	public String getBookingURL() {

		String originalStr = getCurrentURL();
		String str = originalStr.substring(0, 45);
		return str;

	}

}
