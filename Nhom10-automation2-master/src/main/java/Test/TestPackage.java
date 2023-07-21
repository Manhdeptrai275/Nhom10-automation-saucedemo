package Test;
import org.junit.After;
import org.junit.Assert;
import org.testng.asserts.SoftAssert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static java.awt.SystemColor.menu;
import static jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle.header;

public class TestPackage extends CommonPageObject {
    public WebDriver edriver;
    public WebDriverWait ewait;

    public WebDriver driver;
    DataField dataField;
    SoftAssert softAssert = new SoftAssert();
    public TestPackage () throws Exception {
        super(CommonPageObject.edriver);
        edriver = CommonPageObject.edriver;
        ewait = CommonPageObject.ewait;
        dataField = new DataField("src/main/java/Test/TestPackage.xlsx");
    }
//    @Before
//    public void setUP(){
//        edriver.get("https://www.saucedemo.com/");
//    }

    @Test()
    public void TestClass1() throws Exception {
        //Test case 1
        //Test login với username và password đúng
        waitUntilElementVisible(userName);
        userName.sendKeys(dataField.getData(0, 0));
        waitUntilElementVisible(passWord);
        passWord.sendKeys(dataField.getData(0, 1));

        waitUntilElementVisible(btnLogin);
        btnLogin.click();
        waitUntilElementVisible(header);

        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        String actualUrl = edriver.getCurrentUrl();
        softAssert.assertEquals(actualUrl, expectedUrl, "Log in không thành công");
        softAssert.assertAll();
    }

    @Test()
    public void TestClass2() throws Exception {
        //Test case 2
        //Test đặt hàng thành công
        waitUntilElementVisible(userName);
        userName.sendKeys(dataField.getData(0, 0));
        waitUntilElementVisible(passWord);
        passWord.sendKeys(dataField.getData(0, 1));

        waitUntilElementVisible(btnLogin);
        btnLogin.click();
        waitUntilElementVisible(header);

        // Add to cart sản phẩm đầu tiên
        waitUntilElementVisible(addspaz1);
        addspaz1.click();

        //Xem giỏ hàng
        waitUntilElementVisible(cartButton);
        cartButton.click();

        //In giỏ hàng
        System.out.println(cartInfo.getText());

        //Click Checkout
        waitUntilElementVisible(checkoutButton);
        checkoutButton.click();

        //Nhập Your Information
        waitUntilElementVisible(co_firstname);
        waitUntilElementVisible(co_lastname);
        waitUntilElementVisible(co_zip);
        co_firstname.sendKeys("First");
        co_lastname.sendKeys("Last");
        co_zip.sendKeys("700000");

        //ContinueCheckout
        waitUntilElementVisible(continueButton);
        continueButton.click();

        //Finish
        waitUntilElementVisible(finish);
        finish.click();
        String expectedUrl = "https://www.saucedemo.com/checkout-complete.html";
        String actualUrl = edriver.getCurrentUrl();
        softAssert.assertEquals(actualUrl, expectedUrl, "Mua hàng không thành công");
        softAssert.assertAll();
    }

    @Test()
    public void TestClass3() throws Exception {
        //Test case 3
        //Test chức năng Remove sản phẩm trong giỏ hàng
        waitUntilElementVisible(userName);
        userName.sendKeys(dataField.getData(0, 0));
        waitUntilElementVisible(passWord);
        passWord.sendKeys(dataField.getData(0, 1));

        waitUntilElementVisible(btnLogin);
        btnLogin.click();
        waitUntilElementVisible(header);

        //Add to cart 2 sản phẩm đầu tiên
        waitUntilElementVisible(addspaz1);
        addspaz1.click();
        waitUntilElementVisible(addspaz2);
        addspaz2.click();

        //Xem giỏ hàng
        waitUntilElementVisible(cartButton);
        cartButton.click();

        //Remove sản phẩm đầu tiên trong giỏ hàng
        waitUntilElementVisible(removespaz1);
        removespaz1.click();
        String expectedremove = "";
        String actualremove = edriver.findElement(By.className("removed_cart_item")).getText();
        //System.out.print(edriver.findElement(By.className("removed_cart_item")).getText());
        softAssert.assertEquals(actualremove, expectedremove, "Chưa remove được sản phẩm");

        //Continue Shopping
        waitUntilElementVisible(continueShoppingButton);
        continueShoppingButton.click();
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        String actualUrl = edriver.getCurrentUrl();
        softAssert.assertEquals(actualUrl, expectedUrl, "Không chuyển sang trang chủ mua hàng");
        softAssert.assertAll();

        //Logout
        waitUntilElementVisible(menu);
        menu.click();
        waitUntilElementVisible(logout);
        logout.click();
    }

    @Test()
    public void TestClass4() throws Exception {
        //Test case 4
        //Test mua sản phẩm ở danh sách low to high
        waitUntilElementVisible(userName);
        userName.sendKeys(dataField.getData(0, 0));
        waitUntilElementVisible(passWord);
        passWord.sendKeys(dataField.getData(0, 1));

        waitUntilElementVisible(btnLogin);
        btnLogin.click();
        waitUntilElementVisible(header);

        //Chọn Price (low to hight)
        waitUntilElementVisible(sort);
        sort.click();

        waitUntilElementVisible(lth);
        lth.click();

        //Add to cart sản phẩm đầu tiên
        waitUntilElementVisible(addsplth1);
        addsplth1.click();

        //Kiểm tra xem đã Add được sản phẩm chưa
        String expectedbutton = "Remove";
        String actualbutton = edriver.findElement(By.id("remove-sauce-labs-onesie")).getText();
//      System.out.print(edriver.findElement(By.id("remove-sauce-labs-onesie")).getText());
        Assert.assertEquals(expectedbutton, actualbutton);

        //Xem giỏ hàng
        waitUntilElementVisible(cartButton);
        cartButton.click();

        //In giỏ hàng
        System.out.println(cartInfo.getText());

        //Click Checkout
        waitUntilElementVisible(checkoutButton);
        checkoutButton.click();

        //Nhập Your Information
        waitUntilElementVisible(co_firstname);
        waitUntilElementVisible(co_lastname);
        waitUntilElementVisible(co_zip);
        co_firstname.sendKeys("First");
        co_lastname.sendKeys("Last");
        co_zip.sendKeys("700000");

        //ContinueCheckout
        waitUntilElementVisible(continueButton);
        continueButton.click();

        //Log out
        waitUntilElementVisible(menu);
        menu.click();
        waitUntilElementVisible(logout);
        logout.click();
    }

    @Test()
    public void TestClass5() throws Exception {
        //Test case 5
        //Test mua hàng khi không có sản phẩm nào trong giỏ hàng
        waitUntilElementVisible(userName);
        userName.sendKeys(dataField.getData(0, 0));
        waitUntilElementVisible(passWord);
        passWord.sendKeys(dataField.getData(0, 1));

        waitUntilElementVisible(btnLogin);
        btnLogin.click();
        waitUntilElementVisible(header);

        //Chọn Name Z to A
        waitUntilElementVisible(sort);
        sort.click();
        waitUntilElementVisible(za);
        za.click();

        //Kiểm tra xem đã chỉnh được Z to A chưa
        String expectedopion = "Name (Z to A)";
        String actualoption = edriver.findElement(By.className("active_option")).getText();
        Assert.assertEquals(expectedopion, actualoption);

        //Add to cart sản phẩm đầu tiên
        waitUntilElementVisible(addspza1);
        addspza1.click();

        //Xem giỏ hàng
        waitUntilElementVisible(cartButton);
        cartButton.click();

        //Remove sản phẩm trong giỏ hàng
        waitUntilElementVisible(removespza1);

        //Click Checkout
        waitUntilElementVisible(checkoutButton);
        checkoutButton.click();

        //Nhập Your Information
        waitUntilElementVisible(co_firstname);
        waitUntilElementVisible(co_lastname);
        waitUntilElementVisible(co_zip);
        co_firstname.sendKeys("First");
        co_lastname.sendKeys("Last");
        co_zip.sendKeys("700000");

        //ContinueCheckout
        waitUntilElementVisible(continueButton);
        continueButton.click();

        //Finish
        waitUntilElementVisible(finish);
        finish.click();

        //Back Home
        waitUntilElementVisible(backhome);
        backhome.click();

        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        String actualtitle = edriver.getCurrentUrl();
        softAssert.assertEquals(expectedUrl, actualtitle, "Chưa trở về trang chủ");
        softAssert.assertAll();

        //Log out
        waitUntilElementVisible(menu);
        menu.click();
        waitUntilElementVisible(logout);
        logout.click();
    }

    @After
    public void tearDown() throws InterruptedException {
        //edriver.close();
    }
}