package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class loginMain {

	    WebDriver driver;

	  
	    

	    public loginMain(WebDriver driver) {
			super();
			this.driver = driver;
		}

		// ===== COMMON =====
	    By signupLoginBtn = By.xpath("//a[contains(text(),'Signup / Login')]");
	    By loggedInText = By.xpath("//a[contains(text(),'Logged in as')]");
	    By logoutBtn = By.xpath("//a[contains(text(),'Logout')]");

	    // ===== LOGIN =====
	    By loginEmail = By.xpath("//input[@data-qa='login-email']");
	    By loginPassword = By.xpath("//input[@data-qa='login-password']");
	    By loginBtn = By.xpath("//button[@data-qa='login-button']");
	    By loginError = By.xpath("//p[text()='Your email or password is incorrect!']");

	    // ===== SIGNUP =====
	    By signupName = By.xpath("//input[@data-qa='signup-name']");
	    By signupEmail = By.xpath("//input[@data-qa='signup-email']");
	    By signupBtn = By.xpath("//button[@data-qa='signup-button']");
	    By existingEmailError = By.xpath("//p[contains(text(),'Email Address already exist')]");

	    // ===== REGISTER =====
	    By gender = By.id("id_gender1");
	    By password = By.id("password");
	    By firstName = By.id("first_name");
	    By lastName = By.id("last_name");
	    By address = By.id("address1");
	    By country = By.id("country");
	    By state = By.id("state");
	    By city = By.id("city");
	    By zipcode = By.id("zipcode");
	    By mobile = By.id("mobile_number");
	    By createAccount = By.xpath("//button[@data-qa='create-account']");
	    By accountCreated = By.xpath("//b[text()='Account Created!']");

	    // ===== METHODS =====

	    public void openSignupLogin() {
	        driver.findElement(signupLoginBtn).click();
	    }

	    public void signup(String name, String email) {
	        driver.findElement(signupName).sendKeys(name);
	        driver.findElement(signupEmail).sendKeys(email);
	        driver.findElement(signupBtn).click();
	    }

	    public void registerDetails() {

	        // Title
	        driver.findElement(By.id("id_gender1")).click();

	        // Password
	        driver.findElement(By.id("password")).sendKeys("Test@123");

	        // DOB
	        new Select(driver.findElement(By.id("days"))).selectByValue("10");
	        new Select(driver.findElement(By.id("months"))).selectByValue("5");
	        new Select(driver.findElement(By.id("years"))).selectByValue("2000");

	        // Name & Address
	        driver.findElement(By.id("first_name")).sendKeys("Manoj");
	        driver.findElement(By.id("last_name")).sendKeys("Kumar");
	        driver.findElement(By.id("address1")).sendKeys("Hyderabad");

	        // Country
	        new Select(driver.findElement(By.id("country"))).selectByVisibleText("India");

	        // State, City
	        driver.findElement(By.id("state")).sendKeys("Telangana");
	        driver.findElement(By.id("city")).sendKeys("Hyderabad");

	        // Zip
	        driver.findElement(By.id("zipcode")).sendKeys("500001");

	        // Mobile
	        driver.findElement(By.id("mobile_number")).sendKeys("9999999999");

	        // Submit
	        driver.findElement(By.xpath("//button[text()='Create Account']")).click();
	    }

	    public boolean isAccountCreated() {
	        return driver.findElement(accountCreated).isDisplayed();
	    }

	    public void login(String email, String pass) {
	        driver.findElement(loginEmail).sendKeys(email);
	        driver.findElement(loginPassword).sendKeys(pass);
	        driver.findElement(loginBtn).click();
	    }

	    public boolean isLoggedIn() {
	        return driver.findElement(loggedInText).isDisplayed();
	    }

	    public boolean isLoginError() {
	        return driver.findElement(loginError).isDisplayed();
	    }

	    public boolean isExistingEmailError() {
	        return driver.findElement(existingEmailError).isDisplayed();
	    }

	    public void logout() {
	        driver.findElement(logoutBtn).click();
	    }
	}