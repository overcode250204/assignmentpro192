/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.List;

/**
 *
 * @author ACER
 */
public interface I_Vehicle_List {
    boolean addVehicle();
    boolean checkVehicleExist(String fileName);
    boolean updateVehicle();
    boolean deleteVehicle();
    void displayDecsendingVehicleListByPrice();
    void searchVehicleByName(String name);
    boolean saveDataToFile(String fileName);
    boolean loadDataFromFile(String file);
}
