package steps;

import java.util.List;

import org.testng.Assert;

import com.aventstack.extentreports.Status;

import ExtentListeners.ExtentManager;
import ExtentListeners.ExtentTestManager;
import PageObjects.PhpTravelHomePage;
import cucumber.api.DataTable;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CheaptFlightSteps extends BaseSteps {

	public PhpTravelHomePage phpTravelHomePage;
	
	protected Scenario scenario;
	static String scenarioName;

	@Before
	public synchronized void before(Scenario scenario) {
		
		this.scenario = scenario;
		scenarioName = scenario.getName();
		
		ExtentTestManager.startTest("Scenario: " + scenario.getName());
		ExtentTestManager.getTest().log(Status.INFO, "Scenario started : - " + scenario.getName());

		setUpFramework();

	}

	@After
	public void after(Scenario scenario) {
		if (scenario.isFailed()) {

			ExtentTestManager.logFail("Scenario Failed");
			ExtentTestManager.addScreenShotsOnFailure();
		} else {

			ExtentTestManager.scenarioPass();
		}

		ExtentManager.getReporter().flush();

		quit();

	}

	@Given("^launch browser '(.*?)'$")
	public void launch_browser(String browserName) throws Throwable {

		

		//ExtentListeners.testReport.get().info("opening " + browserName);
		openBrowser(browserName);

	}

	@When("^I visit \"([^\"]*)\" website$")
	public void i_visit_website(String url) throws Throwable {
		phpTravelHomePage = new PhpTravelHomePage().open(url);
	}

	@When("^I click FLIGHTS$")
	public void i_click_FLIGHTS() throws Throwable {

		phpTravelHomePage.goToFlight();

	}

	@When("^I select from \"([^\"]*)\"$")
	public void i_select_from(String londonCity) throws Throwable {
		phpTravelHomePage.selectFrom(londonCity);

	}

	@When("^I select to \"([^\"]*)\"$")
	public void i_select_to(String arg1) throws Throwable {

		phpTravelHomePage.selectTo(arg1);
	}

	@When("^I select Return Trip$")
	public void i_select_Return_Trip() throws Throwable {
		phpTravelHomePage.clickOnRoundTrip();
	}

	@When("^I select departure date (\\d+) weeks from today's date$")
	public void i_select_departure_date_weeks_from_today_s_date(int arg1) throws Throwable {
		phpTravelHomePage.selectDeptDate();
	}

	@When("^I select return date (\\d+) weeks from departure date$")
	public void i_select_return_date_weeks_from_departure_date(int arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		phpTravelHomePage.selectReturnDate();
	}

	@When("^I select (\\d+) Adult$")
	public void i_select_Adult(int noOfAdults) throws Throwable {
		phpTravelHomePage.clickOnPassengers();
		phpTravelHomePage.selectAdults(noOfAdults);

	}

	@When("^I select (\\d+) Child$")
	public void i_select_Child(int noOfChild) throws Throwable {
		phpTravelHomePage.selectChild(noOfChild);
	}

	@When("^I click SEARCH button$")
	public void i_click_SEARCH_button() throws Throwable {
		phpTravelHomePage.clickOnSearch();
	}

	@When("^I filter by the following flight carrier$")
	public void i_filter_by_the_following_flight_carrier(DataTable dt) throws Throwable {
		List<List<String>> filters = dt.asLists(String.class);
		for (int i = 0; i < filters.size(); i++)
			phpTravelHomePage.selectAirlines(filters.get(i).get(0));

	}

	@When("^I click on BOOK NOW with the cheapest price$")
	public void i_click_on_BOOK_NOW_with_the_cheapest_price() throws Throwable {
		phpTravelHomePage.clickOnBook();
	}

	@Then("^I am taken to booking page$")
	public void i_am_taken_to_booking_page() throws Throwable {
		String expectedURL = phpTravelHomePage.getBookingURL();
		Assert.assertEquals("https://www.phptravels.net/flights/book/round", expectedURL);
	}

}
