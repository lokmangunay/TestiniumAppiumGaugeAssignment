package com.ozdilekteyim.automationproject;

import com.thoughtworks.gauge.Step;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class StepImplementation extends BaseTest {


    @Step("<seconds> saniye bekle")
    public void waitBySecond(int seconds) throws InterruptedException {
        Thread.sleep(seconds * 1000);
    }

    @Step("<button> butonuna tıkla")
    public void clickMethod(By button) {
        appiumDriver.findElement(button).click();
    }

    @Step("<id> id li input alanına <textInput> kelimesini yaz")
    public void typeMethod(String locator, CharSequence textInput) throws InterruptedException {
        MobileElement inputBox = find(By.id(locator));
        inputBox.sendKeys(textInput);
        Thread.sleep(2500);
        logger.info(textInput+ " kelimesi ID si " +locator+ " olan kutucuğa yazıldı." );

    }

    public MobileElement find(By by) {
        return appiumDriver.findElement(by);
    }

    public List<MobileElement> findAll(String id) throws InterruptedException {
        Thread.sleep(2500);
        return appiumDriver.findElementsById(id);

    }

    @Step("<id> id li elemente tıkla")
    public void clickMethodByID(String elementID) {
        appiumDriver.findElement(By.id(elementID)).click();
    }


    public Boolean isDisplayed(By locator) {
        return find(locator).isDisplayed();
    }


    @Step("<elementID> ID li element ekranda gozukuyor mu kontrolu")
    public void isElementOnTheScreenByID(String elementID) throws InterruptedException {
        Thread.sleep(2500);
        By element = By.id(elementID);
        Assert.assertTrue(elementID + " ID'li element ekranda gözükmemektedir.!", isDisplayed(element));

    }

    @Step("<elementXPath> XPath li element ekranda gozukuyor mu kontrolu")
    public void isElementOnTheScreenByXPath(String elementXPath) throws InterruptedException {
        Thread.sleep(2500);
        By element = By.xpath(elementXPath);
        Assert.assertTrue(elementXPath + " XPath'li element ekranda gözükmemektedir.!", isDisplayed(element));

    }


    @Step("<xpath> xpath li elemente tıkla")
    public void clickMethodByXPath(String xpath) {
        appiumDriver.findElement(By.xpath(xpath)).click();
    }


    @Step("<redo> defa sayfa sonuna kaydır")
    public void scrollDownThePage(int redo) {
        action = new TouchAction(appiumDriver);
        for (int i = 0; i < redo; i++) {
            scrollDownBasedOnScreenSize();
            logger.info("Sayfa sonuna scroll edildi!");
            logger.info("----------------------------");
        }

    }

    public void scrollDownBasedOnScreenSize() {
        Dimension dim = appiumDriver.manage().window().getSize();
        int tapStartY = dim.height;
        int tapStartX = dim.width;
        int scrollStartWidth = tapStartX / 2;
        int scrollEndWidth = tapStartX / 2;
        int scrollStartHeight = (int) (tapStartY * (0.8));
        int scrollEndHeight = (int) (tapStartY * (0.25));
        action.press(PointOption.point(scrollStartWidth, scrollStartHeight)).
                waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).
                moveTo(PointOption.point(scrollEndWidth, scrollEndHeight)).
                release().perform();
    }

    @Step("Ekranda görünen ürünlerden rastgele birini seç")
    public void randomProductSelector() throws InterruptedException {
        selectRandomProduct();
    }

    public List<MobileElement> getAllProductsInAList() throws InterruptedException {
        Thread.sleep(2000);
        By productInfoBox = By.xpath("//*[@resource-id='com.ozdilek.ozdilekteyim:id/textView']");
        return appiumDriver.findElements(productInfoBox);
    }

    public void selectRandomProduct() throws InterruptedException {
        Thread.sleep(3000);

        Random rand = new Random();
        int randomProductIndex = rand.nextInt(getAllProductsInAList().size());
        MobileElement randomlySelectedProduct = getAllProductsInAList().get(randomProductIndex);

        logger.info("Rastgele ürün seçildi. " + randomlySelectedProduct.getText());
        Thread.sleep(4000);

        randomlySelectedProduct.click();

    }

    public void selectRandomSize() throws InterruptedException {
        Thread.sleep(3000);

        Random rand = new Random();
        int randomSizeIndex = rand.nextInt(getAllSizeInAList().size());
        MobileElement randomlySelectedSize = getAllSizeInAList().get(randomSizeIndex);

        logger.info("Rastgele beden seçildi:  " + randomlySelectedSize.getText());
        Thread.sleep(4000);

        randomlySelectedSize.click();
    }

    public List<MobileElement> getAllSizeInAList() throws InterruptedException {
        Thread.sleep(2000);
        By sizeInfoBox = By.xpath("//*[@resource-id='com.ozdilek.ozdilekteyim:id/tvInSizeItem']");
        return appiumDriver.findElements(sizeInfoBox);
    }

    @Step("Rastgele bir beden seç")
    public void randomSizeSelector() throws InterruptedException {
        selectRandomSize();
    }


}
