package com.fleet.test.CarCreate;

import com.fleet.pages.DashboardPage;
import com.fleet.pages.LoginPage;
import com.fleet.pages.VehiclesPage;
import com.fleet.test.TestBase;
import com.fleet.utilities.BrowserUtils;
import com.fleet.utilities.ConfigurationReader;
import com.fleet.utilities.ExcelUtil;
import com.github.javafaker.Faker;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class CreateRandomCar extends TestBase {

    @Test
    public void RandomCar() {

        LoginPage loginPage = new LoginPage();
        String username = ConfigurationReader.get("storemanager_username");
        String password = ConfigurationReader.get("storemanager_password");
        loginPage.login(username, password);
        new DashboardPage().waitUntilLoaderScreenDisappear();
        new DashboardPage().navigateToModule("Fleet", "Vehicles");
        VehiclesPage vehiclesPage = new VehiclesPage();
        vehiclesPage.waitUntilLoaderScreenDisappear();
        BrowserUtils.clickWithJS(vehiclesPage.createCarButton);
        vehiclesPage.waitUntilLoaderScreenDisappear();

        Faker faker = new Faker();
        Random random= new Random();
        DecimalFormat dc= new DecimalFormat("0.0");






        for (int i = 0; i < 1; i++) {
            String [] month = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
            String date1 = month[random.nextInt(12)] +" "+ random.nextInt(31)  + ", "+ (random.nextInt(40)+1980);
            String date2 = month[random.nextInt(12)] +" "+ random.nextInt(31)  + ", "+ (random.nextInt(40)+1980);
            String licensePlate= faker.bothify("##????").toUpperCase();
            String driver = faker.name().fullName();
            String location = faker.country().name();
            String chassisNumber= faker.bothify("##????????").toUpperCase();
            int modelYear = random.nextInt(40)+1980;
            int lastOdometer= random.nextInt(80-20)*1000;

            int catalogValue = random.nextInt(80-5)*1000;
            int seatsNumber = random.nextInt(7-3);
            int doorsNumber = random.nextInt(6-3);
            String color = faker.color().name();
            double emissions= random.nextInt(9-1)*0.1;
            int horsePower = random.nextInt(180)+120;
            double horsePowerTaxation = random.nextInt(9-1)*0.1;
            int power = random.nextInt(300-20);


            vehiclesPage.waitUntilLoaderScreenDisappear();
            vehiclesPage.licence_plate_inputbox.sendKeys(licensePlate);
            vehiclesPage.driver_inputbox.sendKeys(driver);
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
            vehiclesPage.fuelType.click();

            vehiclesPage.emissions_inputbox.sendKeys(emissions+"");
            vehiclesPage.horsePower_inputbox.sendKeys(horsePower+"");
            vehiclesPage.horsePowerTaxation_inputbox.sendKeys(horsePowerTaxation+"");
            vehiclesPage.power_inputbox.sendKeys(power+"");
        }



    }
}
