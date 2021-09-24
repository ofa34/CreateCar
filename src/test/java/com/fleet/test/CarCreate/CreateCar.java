package com.fleet.test.CarCreate;

import com.fleet.pages.DashboardPage;
import com.fleet.pages.LoginPage;
import com.fleet.pages.VehiclesPage;
import com.fleet.test.TestBase;
import com.fleet.utilities.BrowserUtils;
import com.fleet.utilities.ConfigurationReader;
import com.fleet.utilities.ExcelUtil;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class CreateCar extends TestBase {

    @Test
    public void carCreation(){


        LoginPage loginPage=new LoginPage();
        String username = ConfigurationReader.get("storemanager_username");
        String password = ConfigurationReader.get("storemanager_password");
        loginPage.login(username,password);
        new DashboardPage().waitUntilLoaderScreenDisappear();
        new DashboardPage().navigateToModule("Fleet","Vehicles");
        VehiclesPage vehiclesPage=new VehiclesPage();
        vehiclesPage.waitUntilLoaderScreenDisappear();
        BrowserUtils.clickWithJS(vehiclesPage.createCarButton);
        vehiclesPage.waitUntilLoaderScreenDisappear();
        ExcelUtil crtCar = new ExcelUtil("src/test/resources/Vytracktestdata.xlsx","crt");



        //get all column names
        System.out.println("crtCar.getColumnsNames() = " + crtCar.getColumnsNames());
        System.out.println("crtCar.getColumnsNames().get(0) = " + crtCar.getColumnsNames().get(0));

        //get all data in list of maps
        List<Map<String, String>> dataList = crtCar.getDataList();
        System.out.println("dataList.get(0) = " + dataList.get(0));

        System.out.println(dataList.get(0).get("Immatriculation Date"));
        System.out.println(dataList.get(0).get("Location"));
        System.out.println(dataList.get(0).get("Chassis number"));
        System.out.println(dataList.get(0).get("Model year"));
        System.out.println(dataList.get(0).get("First Contract Date"));
        System.out.println(dataList.get(0).get("Catalog Value"));
        System.out.println(dataList.get(0).get("Seats Number"));
        System.out.println(dataList.get(0).get("Doors Number"));
        System.out.println(dataList.get(0).get("Color"));
        System.out.println(dataList.get(0).get("CO2 Emissions"));
        System.out.println(dataList.get(0).get("Horsepower"));
        System.out.println(dataList.get(0).get("Horsepower Taxation"));
        System.out.println(dataList.get(0).get("Power"));







        for (int i=0; i<1; i++) {
            vehiclesPage.waitUntilLoaderScreenDisappear();
            vehiclesPage.licence_plate_inputbox.sendKeys(dataList.get(i).get("License Plate"));
            vehiclesPage.driver_inputbox.sendKeys(dataList.get(i).get("driver"));
            vehiclesPage.location_inputbox.sendKeys(dataList.get(i).get("Location"));
            vehiclesPage.chassis_inputbox.sendKeys(dataList.get(i).get("Chassis number"));
            vehiclesPage.modelYear_inputbox.sendKeys((dataList.get(i).get("Model year")));
            vehiclesPage.lastOdometer_inputbox.sendKeys(dataList.get(i).get("Last Odometer"));
            BrowserUtils.scrollToElement(vehiclesPage.immatriculationDate_inputbox);
            vehiclesPage.immatriculationDate_inputbox.sendKeys(dataList.get(i).get("Immatriculation Date"));
            vehiclesPage.firstContractDate_inputbox.sendKeys(dataList.get(i).get("First Contract Date"));
            vehiclesPage.catalogValue_inputbox.sendKeys(dataList.get(i).get("Catalog Value"));
            vehiclesPage.seatNumber_inputbox.sendKeys(dataList.get(i).get("Seats Number"));
            vehiclesPage.doorsNumber_inputbox.sendKeys(dataList.get(i).get("Doors Number"));
            vehiclesPage.color_inputbox.sendKeys(dataList.get(i).get("Color"));
//            vehiclesPage.transmission.get(0).click();
//            vehiclesPage.fuelType.get(0).click();
            vehiclesPage.emissions_inputbox.sendKeys(dataList.get(i).get("CO2 Emissions"));
            vehiclesPage.horsePower_inputbox.sendKeys(dataList.get(i).get("Horsepower"));
            vehiclesPage.horsePowerTaxation_inputbox.sendKeys(dataList.get(i).get("Horsepower Taxation"));
            vehiclesPage.power_inputbox.sendKeys(dataList.get(i).get("Power"));


            driver.close();

        }

    }
}
