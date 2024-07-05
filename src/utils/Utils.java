/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;


import java.util.Scanner;

/**
 *
 * @author hd
 */
public class Utils {
    public static final int MIN = 1;
    public static final int MAX = 100;
    public static String getString(String welcome) {
        boolean check = true;
        String result = "";
        do {
            Scanner sc = new Scanner(System.in);
            System.out.print(welcome);
            result = sc.nextLine();
            if (result.isEmpty()) {
                System.out.println("Input text!!!");
            } else {
                check = false;
            }
        } while (check);
        return result;
    }

    public static String getString(String welcome, String oldData) {
        String result = oldData;
        Scanner sc = new Scanner(System.in);
        System.out.print(welcome);
        String tmp = sc.nextLine();
        if (!tmp.isEmpty()) {
            result = tmp;
        }
        return result;
    }

    public static int getInt(String welcome, int min, int max) {
        boolean check = true;
        int number = 0;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                number = Integer.parseInt(sc.nextLine());
                check = false;
            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check || number > max || number < min);
        return number;
    }
    
    public static double getDouble(String welcome, int min, int max) {
        boolean check = true;
        double number = 0.0;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                number = Double.parseDouble(sc.nextLine());
                check = false;
            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check || number > max || number < min);
        return number;
    }

    public static int getInt(String welcome, int min, int max, int oldData) {
        boolean check = true;
        int number = oldData;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                String tmp = sc.nextLine();
                if (tmp.isEmpty()) {
                    check = false;
                } else {
                    number = Integer.parseInt(tmp);
                    check = false;
                }
            } catch (Exception e) {
                System.out.println("Input number!!!" + e.getMessage());
            }
        } while (check || number > max || number < min);
        return number;
    }
    
    public static double getDouble(String welcome, int min, int max, double oldData) {
        boolean check = true;
        double number = oldData;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                String tmp = sc.nextLine();
                if (tmp.isEmpty()) {
                    check = false;
                } else {
                    number = Double.parseDouble(tmp);
                    check = false;
                }
            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check || number > max || number < min);
        return number;
    }
    
    

    public static boolean confirmYesNo(String welcome) {
        boolean result = false;
        String confirm = Utils.getString(welcome);
        if ("Y".equalsIgnoreCase(confirm)) {
            result = true;
        }
        return result;
    }
    
    
    public static void display (String str) {
        System.out.println(str);
    }
    
    public static void checkTrueFasle(boolean check) {
        if (check) {
            Utils.display("Success");
        } else {
            Utils.display("Fail");
        }
    }
    
    
    private static String inputPhone(String welcome) {
        String phone = "";
        boolean check = true;
        do {
            phone = Utils.getString(welcome);
            if (phone.length() < 10 || phone.length() > 12) {
                Utils.display("Length must be in [10, 12]");
            } else {
                try {
                    int phoneNumber = Integer.parseInt(phone);
                    check = false;
                } catch (Exception e) {
                    Utils.display("Input number only!!!");
                }
            }
        } while (check);
        return phone;
    }
    
    
    public static String inputDate(String welcome) {
        boolean checkLoop = false;
        String date = "";
        String dayString = "";
        String monthString = "";
        String yearString = "";
        int day = 0;
        int month = 0;
        int year = 0;
        do {
            date = Utils.getString(welcome);
            if (date.length() != 10 || date.charAt(2) != '-' || date.charAt(5) != '-') {
                Utils.display("Date of birth must be entered exactly with format (dd-mm-yyyy)!!!");
            } else {
                dayString = date.substring(0, 2);
                monthString = date.substring(3, 5);
                yearString = date.substring(6, 10);
                try {
                    day = Integer.parseInt(dayString);
                    month = Integer.parseInt(monthString);
                    year = Integer.parseInt(yearString);

                    if (year < 1800 || year > 2024) {
                        Utils.display("Year must be in range [1800, 2024]");
                    } else if (month < 1 || month > 12) {
                        Utils.display("Month must be in range [1, 12]");
                    } else if (day < 1 || day > getDayInMonth(year, month)) {
                        Utils.display("Day must be in range [1, " + getDayInMonth(year, month));
                    } else {
                        checkLoop = false;
                    }
                } catch (Exception e) {
                    Utils.display("Invalid date fomat. Enter again!!!");
                }
                
                
                
            }
        } while (checkLoop);
        date = dayString + "-" + monthString + "-" + yearString;
        return date;
    }
    
    public static String updateDate(String welcome, String oldValue) {
        boolean checkLoop = true;
        String date = oldValue;
        String tmp = "";
        String dayString = "";
        String monthString = "";
        String yearString = "";
        int day = 0;
        int month = 0;
        int year = 0;
        
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.print(welcome);
            tmp = scanner.nextLine();
            if (!tmp.isEmpty()) {
                if (tmp.length() != 10 || tmp.charAt(2) != '-' || tmp.charAt(5) != '-') {
                    Utils.display("Date of birth must be entered exactly with format (dd-mm-yyyy)!!!");
                } else {
                    dayString = tmp.substring(0, 2);
                    monthString = tmp.substring(3, 5);
                    yearString = tmp.substring(6, 10);
                    try {
                        day = Integer.parseInt(dayString);
                        month = Integer.parseInt(monthString);
                        year = Integer.parseInt(yearString);
                        
                        if (year < 1800 || year > 2024) {
                            Utils.display("Year must be in range [1800, 2024]");
                        } else if (month < 1 || month > 12) {
                            Utils.display("Month must be in range [1, 12]");
                        } else if (day < 1 || day > getDayInMonth(year, month)) {
                            Utils.display("Day must be in range [1, " + getDayInMonth(year, month));
                        } else {
                            checkLoop = false;
                        }
                    } catch (Exception e) {
                        Utils.display("Invalid date fomat. Enter again!!!");
                    }
                    
                }
            } else {
                checkLoop = false;
            }
        } while (checkLoop);
        
        date = dayString + "-" + monthString + "-" + yearString;
        return date;
    }

    public static boolean isValidDate(String date) {
        boolean result = false;
        String dayString = "";
        String monthString = "";
        String yearString = "";
        int day = 0;
        int month = 0;
        int year = 0;

        try {
            if (date.length() != 10 || date.charAt(2) != '-' || date.charAt(5) != '-') {
               result = false;
            } else {
                dayString = date.substring(0, 2);
                monthString = date.substring(3, 5);
                yearString = date.substring(6, 10);

                day = Integer.parseInt(dayString);
                month = Integer.parseInt(monthString);
                year = Integer.parseInt(yearString);

                if (year < 1800 || year > 2024) {
                    Utils.display("Year must be in range [1800, 2024]");
                } else if (month < 1 || month > 12) {
                    Utils.display("Month must be in range [1, 12]");
                } else if (day < 1 || day > getDayInMonth(year, month)) {
                    Utils.display("Day must be in range [1, " + getDayInMonth(year, month) + "]");
                } else {
                    result = true;
                }
            }
        } catch (NumberFormatException e) {
            Utils.display(e.getMessage());
        }

        return result;
    }

    
    public static int getDayInMonth(int year, int month) {
        int result = 0;
        switch (month) {
            case 4:
            case 6:
            case 9:
            case 11:
                result = 30;
                break;
            case 2:
                if (isLeapYear(year)) {
                    result = 29;
                } else {
                    result = 28;
                }
                break;
            default:
                result = 31;
                break;
        }

        return result;
    }
    
    
    public static boolean isLeapYear(int year) {
        boolean check = false;
        if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
            check = true;
        }
        return check;
    }
    
    
    
    
    
}

