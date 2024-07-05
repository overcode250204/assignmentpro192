/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dto.I_Menu;
import dto.I_Vehicle_List;
import java.util.ArrayList;
import utils.Utils;

/**
 *
 * @author ACER
 */
public class Menu extends ArrayList<String> implements I_Menu{
    private I_Vehicle_List vehicle_List;

    public Menu() {
    }

    public Menu(I_Vehicle_List vehicle_List) {
        this.vehicle_List = vehicle_List;
    }
    
    
    @Override
    public void addItem(String str) {
        this.add(str);
    }

    @Override
    public int getChoice() {
        return Utils.getInt("Input your choice: ", 1, this.size());
    }

    @Override
    public void showMenu() {
        for (String menu : this) {
            Utils.display(menu);
        }
    }

    @Override
    public boolean confirmYesNo(String welcome) {
        boolean result = false;
        String confirm = Utils.getString(welcome);
        if ("Y".equalsIgnoreCase(confirm)) {
            result = true;
        }
        return result;
    }

    @Override
    public void searchVehicleByName() {
        String name = Utils.getString("Input name to search: ");
        vehicle_List.searchVehicleByName(name);
    }



    
    
    
}
