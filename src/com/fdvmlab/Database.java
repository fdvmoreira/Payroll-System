/**
 * Define the structure to simulate the database
 */
package com.fdvmlab;

import java.util.ArrayList;

public class Database {
    //This variable will be used as a database to store the three types of employees
    static ArrayList<ArrayList<Employee>> employeesDB = new ArrayList<ArrayList<Employee>>();

    //store the payslips created previously
    static ArrayList<String> payslips = new ArrayList<>();

    /**
     * Find if the employee with this ID is registered
     * @param id - to be found
     * @return was found or not
     */
    public static boolean isEmployeeRegistered(int id){
        boolean isFound = false;
        //look up the list
        for (ArrayList<Employee> employeeList: Database.employeesDB){
            if (!employeeList.isEmpty()){
                for (Employee employee: employeeList){

                    //If the employee is found, information is retrieved
                    if (employee.getId() == id){
                        isFound = true;
                        break;
                    }
                }
            }
        }
        return isFound;
    }

    /**
     * Find if the employee with this first and last name registered
     * @param fn - find first name
     * @param ln - find last name
     * @return was found or nod
     */
    public static boolean isEmployeeRegistered(String fn, String ln){
        boolean isFound = false;
        //look up the list
        for (ArrayList<Employee> employeeList: Database.employeesDB){
            if (!employeeList.isEmpty()){
                for (Employee employee: employeeList){

                    //If the employee is found, information is retrieved
                    if (((employee.getFirstName().equals(fn))) && ((employee.getLastName().equals(ln)))){
                        isFound = true;
                        break;
                    }else {
                    }
                }
            }
        }
        return isFound;
    }

    /**
     * Retrieve the employee with provided id
     * @param id - the employee id to be found
     * @return the employee or null
     */
    public static Employee findEmployee(int id) {
        Employee tempEmployee = null;
        boolean isFound = false;

        //look up the list
        for (ArrayList<Employee> employeeList : Database.employeesDB) {
            if (!employeeList.isEmpty()) {
                for (Employee employee : employeeList) {
                    //If the employee is found, information is retrieved
                    if (employee.getId() == id) {
                        isFound = true;
                        tempEmployee = employee;
                        break;
                    }
                }
            }
        }
        //If the employee was not found
        if (!isFound){
            Printer.printWarning(" The employee (ID=" + id + ") was not found!\n Press ENTER to continue ...");
            Util.getUserInput("",InputType.STRING);
        }
        return tempEmployee;
    }

    public static boolean removeByID(int id){
        //the operation status
        boolean operationStatus = false;
        //look up the list
        for (ArrayList<Employee> employeeList : Database.employeesDB) {
            if (!employeeList.isEmpty()) {
                for (Employee employee : employeeList) {
                    //If the employee is found, information is retrieved
                    if (employee.getId() == id) {
                        if (employeeList.remove(employee)){
                            operationStatus = true;
                            break;
                        }else {
                            Printer.printWarning("Unable to remove employee (ID="+id+")");
                        }
                    }
                }
            }
        }
        return operationStatus;
    }

    /**
     * find out if there are payslips stored in database
     * @return Object[] to be converted to string[] or null if is empty
     */
    public static Object[] findPayslip(){
        String payslipArray;
        if (Database.payslips.size() < 1){
            Printer.printWarning("\n No Payslips saved!");
            return null;
        }
        return payslips.toArray();
    }
}
