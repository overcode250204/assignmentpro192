/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Menu;
import controller.Vehicle_List;
import dto.I_Menu;
import dto.I_Vehicle_List;
import utils.Utils;

/**
 *
 * @author ACER
 */
public class VehicleManagementSystem {

    public static void main(String[] args) {
        boolean checkLoop = true;
        boolean subCheckLoop = true;
        int choice = 0;
        I_Vehicle_List vehicle_List = new Vehicle_List();
        I_Menu menu = new Menu();
        I_Menu subMenu = new Menu(vehicle_List);
        menu.addItem("1. Add new vehicle");
        menu.addItem("2. Check exits vehicle");
        menu.addItem("3. Update vehicle");
        menu.addItem("4. Delete vehicle");
        menu.addItem("5. Search vehicle by name");
        menu.addItem("6. Display vehicle list desecending by price");
        menu.addItem("7. Save vehicle to file");
        menu.addItem("8. Load data to list vehicle");
        menu.addItem("9. Exit");
        do {
            menu.showMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    vehicle_List.addVehicle();
                    break;
                case 2:
                    vehicle_List.checkVehicleExist("vehicle.dat");
                    break;
                case 3:
                    vehicle_List.updateVehicle();
                    break;
                case 4:
                    vehicle_List.deleteVehicle();
                    break;
                case 5:
                    subMenu.searchVehicleByName();
                    break;
                case 6:
                    vehicle_List.displayDecsendingVehicleListByPrice();
                    break;
                case 7:
                    vehicle_List.saveDataToFile("vehicle.dat");
                    break;
                case 8:
                    vehicle_List.loadDataFromFile("vehicle.dat");
                    break;
                case 9:
                    String quit = Utils.getString("Do you want to exit? (Y/N)");
                    if ("Y".equalsIgnoreCase(quit)) {
                        checkLoop = false;
                    }
                    break;
            }
        } while (checkLoop);
    }
}
