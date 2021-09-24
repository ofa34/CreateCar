package com.fleet.test.CarCreate;

import com.fleet.pages.DashboardPage;
import com.fleet.pages.LoginPage;
import com.fleet.pages.VehiclesPage;
import com.fleet.test.TestBase;
import com.fleet.utilities.BrowserUtils;
import com.fleet.utilities.ConfigurationReader;
import com.fleet.utilities.Driver;
import com.fleet.utilities.ExcelUtil;
import com.github.javafaker.Faker;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class CreateRandomCar extends TestBase {

    @Test
    public void RandomCar() throws InterruptedException {

        LoginPage loginPage = new LoginPage();
        String username = ConfigurationReader.get("storemanager_username");
        String password = ConfigurationReader.get("storemanager_password");
        loginPage.login(username, password);
        new DashboardPage().waitUntilLoaderScreenDisappear();


        Faker faker = new Faker();
        Random random= new Random();
        DecimalFormat dc= new DecimalFormat("0.0");
        VehiclesPage vehiclesPage = new VehiclesPage();


        for (int i = 0; i < 10; i++) {

            new DashboardPage().navigateToModule("Fleet", "Vehicles");

            vehiclesPage.waitUntilLoaderScreenDisappear();
            BrowserUtils.clickWithJS(vehiclesPage.createCarButton);
            vehiclesPage.waitUntilLoaderScreenDisappear();


            Thread.sleep(2000);
            String [] month = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
            String date1 = month[random.nextInt(11)+1] +" "+ (random.nextInt(30)+1)  + ", "+ (random.nextInt(40)+1980);
            String date2 = month[random.nextInt(11)+1] +" "+ (random.nextInt(30)+1)  + ", "+ (random.nextInt(40)+1980);
            String licensePlate= faker.bothify("##????").toUpperCase();
            String Driver = faker.name().fullName();
            String location = faker.address().city().toUpperCase();
            String chassisNumber= faker.bothify("######");
            int modelYear = random.nextInt(40)+1980;
            int lastOdometer= random.nextInt(80-20)*1000;

            int catalogValue = random.nextInt(80-5)*1000;
            int seatsNumber = random.nextInt(4)+3;
            int doorsNumber = random.nextInt(5)+2;
            String color = StringUtils.capitalize(faker.color().name());
            double emissions= random.nextInt(9-1)*0.1;
            int horsePower = random.nextInt(180)+120;
            double horsePowerTaxation = random.nextInt(9-1)*0.1;
            int power = random.nextInt(300-20);


            vehiclesPage.waitUntilLoaderScreenDisappear();
            vehiclesPage.licence_plate_inputbox.sendKeys(licensePlate);
            vehiclesPage.myTags.get(random.nextInt(7)).click();

            vehiclesPage.driver_inputbox.sendKeys(Driver);
            vehiclesPage.location_inputbox.sendKeys(location);
            vehiclesPage.chassis_inputbox.sendKeys(chassisNumber);
            vehiclesPage.modelYear_inputbox.sendKeys(modelYear+"");
            vehiclesPage.lastOdometer_inputbox.sendKeys(lastOdometer+"");
            BrowserUtils.scrollToElement(vehiclesPage.immatriculationDate_inputbox);
            vehiclesPage.immatriculationDate_inputbox.sendKeys(date1);
            vehiclesPage.immatriculationDate_inputbox.sendKeys(Keys.ESCAPE);
            vehiclesPage.firstContractDate_inputbox.sendKeys(date2);
            vehiclesPage.firstContractDate_inputbox.sendKeys(Keys.ESCAPE);
            vehiclesPage.catalogValue_inputbox.sendKeys(catalogValue+"");
            vehiclesPage.seatNumber_inputbox.sendKeys(seatsNumber+"");
            vehiclesPage.doorsNumber_inputbox.sendKeys(doorsNumber+"");
            vehiclesPage.color_inputbox.sendKeys(color);

            vehiclesPage.transmission.click();
            driver.findElements(By.xpath("/html/body/div[8]/ul[2]/li")).get(random.nextInt(2)).click();
            Thread.sleep(1000);
            vehiclesPage.fuelType.click();
            driver.findElements(By.xpath("/html/body/div[9]/ul[2]/li")).get(random.nextInt(4)).click();


            vehiclesPage.emissions_inputbox.sendKeys(emissions+"");
            vehiclesPage.horsePower_inputbox.sendKeys(horsePower+"");
            vehiclesPage.horsePowerTaxation_inputbox.sendKeys(horsePowerTaxation+"");
            vehiclesPage.power_inputbox.sendKeys(power+"");
            Thread.sleep(1000);
            vehiclesPage.saveAndCloseButton.click();
            vehiclesPage.waitUntilLoaderScreenDisappear();
            Thread.sleep(1000);


        }



    }
}
