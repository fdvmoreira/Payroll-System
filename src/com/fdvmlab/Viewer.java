/**
 * Define the viewer (used to list the employees  registered)
 */
package com.fdvmlab;
import java.util.ArrayList;

public class Viewer {

    //Employee type and employeeId
    private EmployeeType employeeType = null;
    private int employeeId = 0;
    //Store the employees in this variable
    private ArrayList<Employee> employeesList = new ArrayList<>();

    /**
     * Constructor
     * @param employeeType - the type of employee to be displayed
     * @param employeeId - employee IDs to be listed
     */
    public Viewer(EmployeeType employeeType, int employeeId) {
        this.employeeType = employeeType;
        //check if employeeId is not negative number
        if (employeeId >= 0)this.employeeId = employeeId;
    }

    //Default constructor
    public Viewer(){
        this.employeeType = null;
        this.employeeId = 0;
    }

    //Display the employees
    protected void displayEmployee(){

        //return the employees from the array list
        //If the employee type is not provided
        if (this.employeeType != null){
            //store the employees temporarily
            ArrayList<Employee> tempEmployeesArrayList = new ArrayList<>();

            switch (this.employeeType){
                case SALARIED_EMPLOYEE:
                    tempEmployeesArrayList = Database.employeesDB.get(EmployeesIndex.SALARIED_EMPLOYEE);
                    break;
                case HOURLY_EMPLOYEE:
                    tempEmployeesArrayList = Database.employeesDB.get(EmployeesIndex.HOURLY_EMPLOYEE);
                    break;
                case COMMISSION_EMPLOYEE:
                    tempEmployeesArrayList = Database.employeesDB.get(EmployeesIndex.COMMISSIONED_EMPLOYEE);
                    break;
                default:
                    Printer.printError("Unknown Employee Type\n Press ENTER to continue ...");
                    //Pause the console
                    Util.getUserInput("",InputType.STRING);
            }

            //Check if employee with this Id is found
            if (!tempEmployeesArrayList.isEmpty()){
                //Add emp to a list to be printed later
                for (Employee employee : tempEmployeesArrayList) {
                    //check if we are looking for a specific ID
                    if (this.employeeId != 0){
                        //if found add it to list
                        if (employee.getId() == this.employeeId){
                            //add to list
                            employeesList.add(employee);
                        }
                    }else{
                        //Add all [employeeType] to list
                        employeesList.add(employee);
                    }
                }
            }else {
                //No employees log message em return
                Printer.printWarning(this.employeeType.toString().toUpperCase()+"s were not found!\n Press ENTER to continue ...");
                //Pause the console
                Util.getUserInput("",InputType.STRING);
                return;
            }

        }else{
            //Print all the employees
            for (ArrayList<Employee> employeeArrayList : Database.employeesDB){
                if (!employeeArrayList.isEmpty()) {
                    for (Employee employee : employeeArrayList) {
                        //check if we are looking for a specific ID
                        if (this.employeeId != 0){
                            //if found add it to list
                            if (employee.getId() == this.employeeId){
                                //add to list
                                employeesList.add(employee);
                            }
                        }else{
                            //Add every employee to list
                            employeesList.add(employee);
                        }
                    }
                }
            }
        }

        //check if id was provided and found
        if (this.employeeId != 0 && employeesList.isEmpty()){
            Printer.printWarning(" Employee " + this.employeeId + " Was Not Found!");
            //Pause the console
            Util.getUserInput("\n Press ENTER to continue ...",InputType.STRING);
            return;
        }

        //Print the employee if list is not empty
        if (!employeesList.isEmpty()){

            for (Employee e : employeesList) {
                Printer.printInfo("\n" +
                        " ID ________________: " + e.getId() + "\n" +
                        " Title _____________: " + e.getTitle() + "\n" +
                        " Name ______________: " + e.getFirstName() + " " + e.getLastName() + "\n" +
                        " Date Of Birth _____: " + e.getDateOfBirth() + "\n" +
                        " NI Number _________: " + e.getNationalInsuranceNumber() + "\n" +
                        " Job Title _________: " + e.getJobTitle() + "\n" +
                        " Department ________: " + e.getJobDepartment()+"\n");

                //printing the extra info depending on the employee type
                if (e instanceof SalariedEmployee){
                    Printer.printInfo(
                            " Emp Status ________: "+(((SalariedEmployee)e).getEmpStatus())+"\n" +
                            " Annual Salary _____: "+((SalariedEmployee)e).getAnnualSalary()+"£\n");}

                if (e instanceof HourlyEmployee){
                    Printer.printInfo(
                            " Hourly Rate _______: "+((HourlyEmployee)e).getHourlyRate()+"£");}

                if (e instanceof CommissionEmployee){
                    Printer.printInfo(
                            " Annual Salary _____: "+((CommissionEmployee)e).getAnnualGrossSalary()+"£\n" +
                            " Commission Rate ___: "+((CommissionEmployee)e).getCommissionRate()+"%\n");}


            }
            //Pause the console
            Util.getUserInput("\n Press ENTER to continue ...",InputType.STRING);
        }
    }
}
