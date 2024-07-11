/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dto.I_Vehicle_List;
import dto.Vehicle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Utils;

/**
 *
 * @author ACER
 */
public class Vehicle_List extends ArrayList<Vehicle> implements I_Vehicle_List {

    @Override
    public boolean addVehicle() {
        boolean result = false;
        boolean checkLoop = true;
        do {
            String id = Utils.getString("Input id: ");
            if (this.contains(new Vehicle(id))) {
                Utils.display("Vehicle ID already exist!!!");
                Utils.display("Fail");
            } else {
                String name = Utils.getString("Input name: ");
                String color = Utils.getString("Input color: ");
                double price = Utils.getDouble("Input price ($): ", 1000, 10000);
                String brand = Utils.getString("Input brand: ");
                String type = Utils.getString("Input type: ");
                int productYear = Utils.getInt("Input product of year: ", 1800, 2024);
                Vehicle newVehicle = new Vehicle(id, name, color, price, brand, type, productYear);
                this.add(newVehicle);
                result = true;
            }
            if (!Utils.confirmYesNo("Do you want to continue add vehicle? (Y/N) ")) {
                checkLoop = false;
            }
        } while (checkLoop);
        if (result) {
            Utils.display("Succes");
        } else {
            Utils.display("Fail");
        }
        return result;
    }

    @Override
    public boolean checkVehicleExist(String fileName) {
        boolean result = false;
        boolean checkLoop = true;
        String id = "";
        File file = new File(fileName);
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            if (!file.exists()) {
                Utils.display("File does not exist!!!");
            } else {
                id = Utils.getString("Input ID: ");
                fileInputStream = new FileInputStream(file);
                objectInputStream = new ObjectInputStream(fileInputStream);
                while (fileInputStream.available() > 0) {
                    Vehicle vehicle = (Vehicle) objectInputStream.readObject();
                    if (id.equals(vehicle.getId())) {
                        Utils.display("Exist Vehicle");
                        Utils.display(vehicle.toString());
                        result = true;
                        break;
                    }
                }
                if (!result) {
                    Utils.display("No Vehicle Found!");
                }
            }
        } catch (Exception e) {

            Utils.display("Error check vehicle exist!!" + e.getMessage());
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }

            } catch (IOException ex) {
                Logger.getLogger(Vehicle_List.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (result) {
            Utils.display("Succes");
        } else {
            Utils.display("Fail");
        }
        return result;
    }

    @Override
    public boolean updateVehicle() {
        boolean result = false;
        String id = "";
        id = Utils.getString("Input ID: ");
        if (this.contains(new Vehicle(id))) {
            int index = this.indexOf(new Vehicle(id));
            Vehicle vehicle = this.get(index);
            id = Utils.getString("Update ID: ", vehicle.getId());
            String name = Utils.getString("Update name: ", vehicle.getName());
            String color = Utils.getString("Update color: ", vehicle.getColor());
            double price = Utils.getDouble("Update price: ", 1000, 10000, vehicle.getPrice());
            String brand = Utils.getString("Update brand: ", vehicle.getBrand());
            String type = Utils.getString("Update type: ", vehicle.getType());
            int productYear = Utils.getInt("Update product of year: ", 1800, 2024, vehicle.getProductYear());
            Vehicle newVehicle = new Vehicle(id, name, color, price, brand, type, productYear);
            this.set(index, newVehicle);
            result = true;
            Utils.display("Information of updated vehicle");
            Utils.display(newVehicle.toString());
        } else {
            Utils.display("Vehicle does not exist");
        }
        if (result) {
            Utils.display("Succes");
        } else {
            Utils.display("Fail");
        }
        return result;
    }

    @Override
    public boolean deleteVehicle() {
        boolean result = false;
        String id = "";
        id = Utils.getString("Input ID: ");
        if (this.contains(new Vehicle(id))) {
            int index = this.indexOf(new Vehicle(id));
            Vehicle vehicle = this.get(index);
            if (Utils.confirmYesNo("Do you want to detele? (Y/N)")) {
                this.remove(vehicle);
                result = true;
            }
        } else {
            Utils.display("Vehicle does not exist");
        }
        if (result) {
            Utils.display("Succes");
        } else {
            Utils.display("Fail");
        }

        return result;
    }

    @Override
    public void displayDecsendingVehicleListByPrice() {
        Utils.display("Sorted descending vehicle list by price");
        Collections.sort(this);
        for (Vehicle vehicle : this) {
            Utils.display(vehicle.toString());
        }
    }

    @Override
    public void searchVehicleByName(String name) {
        boolean check = false;
        for (Vehicle vehicle : this) {
            if (vehicle.getName().toLowerCase().contains(name.toLowerCase())) {
                Utils.display(vehicle.toString());
                check = true;
            }
        }
        if (!check) {
            Utils.display("Not Found Vehicle With Name '" + name + "'");
        }
    }

    @Override
    public boolean saveDataToFile(String fileName) {
        boolean result = false;
        File file = new File(fileName);
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
                fileOutputStream = new FileOutputStream(file);
                objectOutputStream = new ObjectOutputStream(fileOutputStream);
                for (Vehicle vehicle : this) {
                    objectOutputStream.writeObject(vehicle);
                }
                result = true;
            
        } catch (IOException e) {
            Utils.display("Error!!!" + e.getMessage());
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }

            } catch (IOException ex) {
                Logger.getLogger(Vehicle_List.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (result) {
            Utils.display("Succes");
        } else {
            Utils.display("Fail");
        }

        return result;
    }

    @Override
    public boolean loadDataFromFile(String fileName) {
        boolean result = false;
        File file = new File(fileName);
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;

        try {
            if (!file.exists()) {
                Utils.display("File does not exist!!!");
            } else {
                fileInputStream = new FileInputStream(file);
                objectInputStream = new ObjectInputStream(fileInputStream);
                while (fileInputStream.available() > 0) {
                    Vehicle vehicle = (Vehicle) objectInputStream.readObject();
                    if (this.isEmpty()) {
                        this.add(vehicle);
                    } else {
                        for (Vehicle v : this) {
                            if (!vehicle.getId().equals(v.getId())) {
                                this.add(vehicle);
                            }
                        }
                    }

                }
                result = true;
            }
        } catch (IOException | ClassNotFoundException e) {
            Utils.display("Error!!!!" + e.getMessage());
        } finally {

            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }

            } catch (IOException ex) {
                Logger.getLogger(Vehicle_List.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (result) {
            Utils.display("Succes");
        } else {
            Utils.display("Fail");
        }
        return result;
    }

}
