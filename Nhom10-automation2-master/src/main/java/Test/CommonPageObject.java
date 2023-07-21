package Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.Assertion;

import java.time.Duration;

public class CommonPageObject {
    public static WebDriver edriver;
    public static WebDriverWait ewait;

    public CommonPageObject(WebDriver driver ) {
        edriver = new ChromeDriver();
        PageFactory.initElements(edriver, this);
        ewait = new WebDriverWait(edriver, Duration.ofSeconds(30));;
        edriver.get("https://www.saucedemo.com/");
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"user-name\"]")
    public WebElement userName;
    @FindBy(how = How.XPATH, using = "//*[@id=\"password\"]")
    public WebElement passWord;

    @FindBy(how = How.XPATH, using = "//*[@id=\"login-button\"]")
    public WebElement btnLogin;

    @FindBy(how = How.XPATH, using = "//*[@data-test=\"product_sort_container\"]")
    public WebElement sort;

    @FindBy(how = How.XPATH, using = "//*[@value=\"lohi\"]")
    public WebElement lth;

    @FindBy(how = How.XPATH, using = "//*[@value=\"za\"]")
    public WebElement za;

    //2 sản phẩm đầu A to Z
    @FindBy(how = How.XPATH, using = "//*[@id=\"add-to-cart-sauce-labs-backpack\"]")
    public WebElement addspaz1;
    @FindBy(how = How.XPATH, using = "//*[@id=\"add-to-cart-sauce-labs-bike-light\"]")
    public WebElement addspaz2;


    //2 sản phẩm đầu low to high
    @FindBy(how = How.XPATH, using = "//*[@id=\"add-to-cart-sauce-labs-onesie\"]")
    public WebElement addsplth1;
    @FindBy(how = How.XPATH, using = "//*[@id=\"add-to-cart-sauce-labs-bike-light\"]")
    public WebElement addsplth2;


    //2 sản phẩm đầu Z to A
    @FindBy(how = How.XPATH, using = "//*[@id=\"add-to-cart-test.allthethings()-t-shirt-(red)\"]")
    public WebElement addspza1;
    @FindBy(how = How.XPATH, using = "//*[@id=\"add-to-cart-sauce-labs-onesie\"]")
    public WebElement addspza2;


    @FindBy(how = How.XPATH, using = "//*[@class=\"shopping_cart_link\"]")
    public WebElement cartButton;

    @FindBy(how = How.CLASS_NAME, using = "cart_list")
    public WebElement cartInfo;

    //Remove sản phẩm (A to Z)
    @FindBy(how = How.XPATH, using = "//*[@id=\"remove-sauce-labs-backpack\"]")
    public WebElement removespaz1;
    @FindBy(how = How.XPATH, using = "//*[@id=\"remove-sauce-labs-bike-light\"]")
    public WebElement removespaz2;

    //Remove sản phẩm (low to high)
    @FindBy(how = How.XPATH, using = "//*[@id=\"remove-sauce-labs-onesie\"]")
    public WebElement removesplth1;
    @FindBy(how = How.XPATH, using = "//*[@id=\"remove-sauce-labs-bike-light\"]")
    public WebElement removesplth2;

    //Remove sản phẩm Z to A
    @FindBy(how = How.XPATH, using = "//*[@id=\"remove-test.allthethings()-t-shirt-(red)\"]")
    public WebElement removespza1;
    @FindBy(how = How.XPATH, using = "//*[@id=\"remove-sauce-labs-onesie\"]")
    public WebElement removespza2;


    @FindBy(how = How.XPATH, using = "//*[@id=\"continue-shopping\"]")
    public WebElement continueShoppingButton;

    @FindBy(how = How.XPATH, using = "//*[@id=\"checkout\"]")
    public WebElement checkoutButton;

    @FindBy(how = How.XPATH, using = "//*[@id=\"first-name\"]")
    public WebElement co_firstname;

    @FindBy(how = How.XPATH, using = "//*[@id=\"last-name\"]")
    public WebElement co_lastname;

    @FindBy(how = How.XPATH, using = "//*[@id=\"postal-code\"]")
    public WebElement co_zip;

    @FindBy(how = How.XPATH, using = "//*[@id=\"continue\"]")
    public WebElement continueButton;

    @FindBy(how = How.CLASS_NAME, using = "summary_info")
    public WebElement summaryInfo;

    @FindBy(how = How.XPATH, using = "//*[@id=\"finish\"]")
    public WebElement finish;

    @FindBy(how = How.XPATH, using = "//*[@id=\"back-to-products\"]")
    public WebElement backhome;

    @FindBy(how = How.XPATH, using = "//*[@id=\"react-burger-menu-btn\"]")
    public WebElement menu;

    @FindBy(how = How.XPATH, using = "//*[@id=\"logout_sidebar_link\"]")
    public WebElement logout;

    @FindBy(how = How.XPATH, using = "//*[@class=\"header_label\"]")
    public WebElement header;


    public void waitUntilElementVisible(String path) throws Exception{
        int tryTimes = 0;
        while (tryTimes < 2){
            try {
                WebElement element = edriver.findElement(By.xpath(path));
                ewait.until(ExpectedConditions.visibilityOf(element));
                break;
            }
            catch (StaleElementReferenceException se){
                tryTimes ++;
                if (tryTimes == 2)
                    throw se;
            }
        }
    }
    public void waitUntilElementVisible(WebElement element){
        int tryTimes = 0;
        while (tryTimes < 2){
            try {
                ewait.until(ExpectedConditions.visibilityOf(element));
                break;
            }
            catch (StaleElementReferenceException se){
                tryTimes ++;
                if (tryTimes == 2)
                    throw se;
            }
        }
    }
}
