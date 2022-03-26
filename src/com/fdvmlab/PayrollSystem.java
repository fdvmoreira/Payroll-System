/**
 * The entry point, application is ran by executing the main method
 */
package com.fdvmlab;
import java.time.LocalDate;
import java.util.ArrayList;

public class PayrollSystem {

    public static void main(String[] args){

        //Populate the database
        setupDatabase();

        //main loop - holds the application active until the user chooses to terminate it
        while (true){
            //print main menu
            int choice = 5;
            choice = new Menu().mainMenu();
            switch (choice){
                case 1:
                    //Register employee
                    new Menu().registerEmployeeMenu();
                    break;
                case 2:
                    //View Employee
                    new Menu().viewEmployeeMenu();
                    break;
                case 3:
                    //Remove employee
                    new Menu().removeEmployeeMenu();
                    break;
                case 4:
                    //Generate employee
                    new Menu().generatePayslipMenu();
                    break;
                case 5:
                    //Exit the application
                    System.exit(0);
                default:
                    //Pause the console
                    Printer.printError("Invalid Option\n Press ENTER to continue ...");
                    Util.getUserInput("",InputType.STRING);
            }
        }
    }

    //Prepopulate the database
    static void setupDatabase(){
        /**
         * *************************************************************************
         * ********************** Prepopulate the employees ************************
         * *************************************************************************
         */
        //Salaried employee
        ArrayList<Employee> salariedEmployees = new ArrayList<>();
        salariedEmployees.add(new SalariedEmployee(
                1,
                "Mr",
                "John",
                "Quinn",
                LocalDate.now().minusYears(55),
                "PG 13 34 38 B",
                JobTitle.MANAGER,
                Department.PRODUCTION,
                EmploymentStatus.FULL_TIME,
                45000.80F
        ));
        Database.employeesDB.add(EmployeesIndex.SALARIED_EMPLOYEE,salariedEmployees);

        //Hourly Employees
        ArrayList<Employee> hourlyEmployees = new ArrayList<>();
        hourlyEmployees.add(new HourlyEmployee(
                2,
                "Miss",
                "Monika",
                "Gap",
                LocalDate.now().minusYears(24),
                "PN 62 32 70 B",
                JobTitle.TEAM_LEADER,
                Department.SALES,
                15.07F
        ));
        Database.employeesDB.add(EmployeesIndex.HOURLY_EMPLOYEE,hourlyEmployees);

        //Commission Employees
        ArrayList<Employee> commissionEmployees = new ArrayList<>();
        commissionEmployees.add(new CommissionEmployee(
                3,
                "Mrs",
                "Dora",
                "Labia",
                LocalDate.now().minusYears(31),
                "YG 22 87 84 C",
                JobTitle.OFFICE_WORKER,
                Department.MARKETING,
                25000.00F,
                1.09F
        ));
        Database.employeesDB.add(EmployeesIndex.COMMISSIONED_EMPLOYEE,commissionEmployees);
    }
}
