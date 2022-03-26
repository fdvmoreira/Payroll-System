/**
 * Define the menus, submenus and direct the user to a chosen submenu
 */
package com.fdvmlab;
public class Menu {

    //used in the submenus to control return to main menu
    boolean backToMainMenu = false;

    //Main menu
    public int mainMenu(){
        System.out.print("\n"+
                "|```````````````````````````````````````````|\n" +
                "|____________| *Payroll System* |___________|\n" +
                "|```````````````````````````````````````````|\n" +
                "| 1 - Register Employee                     |\n" +
                "| 2 - View Employee                         |\n" +
                "| 3 - Remove Employee                       |\n" +
                "| 4 - Generate Payslip                      |\n" +
                "| 5 - E X I T                               |\n" +
                "|_|```````````````````````````````````````|_|\n| ");

        //return the user input cast it to integer
        return  (int)Util.getUserInput(" Please choose an option: ",InputType.INTEGER);
    }

    //Register employee menu
    public void registerEmployeeMenu(){
        while (!backToMainMenu){
            System.out.print("\n"+
                    "|```````````````````````````````````````````|\n" +
                    "|____| Payroll Sys. >  *Register Emp.* |____|\n" +
                    "|```````````````````````````````````````````|\n" +
                    "| 1 - Salaried Employee                     |\n" +
                    "| 2 - Hourly Employee                       |\n" +
                    "| 3 - Commissioned Employee                 |\n" +
                    "| 4 - B A C K    <<==                       |\n" +
                    "|_|```````````````````````````````````````|_|\n| ");

            //return the user input and cast it to integer
            int op = (int)Util.getUserInput(" Please choose an option: ",InputType.INTEGER);
            //Register Employee
            switch (op){
                case 1:
                    //Create Salaried Staff
                    new Register(EmployeeType.SALARIED_EMPLOYEE).setEmployeeDetails();
                    break;
                case 2:
                    //Create Hourly Staff
                    new Register(EmployeeType.HOURLY_EMPLOYEE).setEmployeeDetails();
                    break;
                case 3:
                    //Create Commission Staff
                    new Register(EmployeeType.COMMISSION_EMPLOYEE).setEmployeeDetails();
                    break;
                case 4:
                    backToMainMenu = true;
                    break;
                default:
                    //Pause the console
                    Printer.printError(" Invalid Option\n Press ENTER to continue ...");
                    Util.getUserInput("",InputType.STRING);
            }
        }
    }

    //view employees menu
    public void viewEmployeeMenu(){
        while (!backToMainMenu) {
            System.out.print("\n"+
                    "|```````````````````````````````````````````|\n" +
                    "|______| Payroll Sys. >  *View Emp.* |______|\n" +
                    "|```````````````````````````````````````````|\n" +
                    "| 0 - List All Employees                    |\n" +
                    "| 1 - List By Id                            |\n" +
                    "| 2 - Salaried Employees                    |\n" +
                    "| 3 - Hourly Employees                      |\n" +
                    "| 4 - Commissioned Employees                |\n" +
                    "| 5 - B A C K    <<==                       |\n" +
                    "|_|```````````````````````````````````````|_|\n| ");

            //return the user input and cast it to integer
            int op = (int)Util.getUserInput(" Please choose an option: ",InputType.INTEGER);
            switch (op) {
                case 0:
                    //Display all the employee
                    new Viewer().displayEmployee();
                    break;
                case 1:
                    //List by Id
                    int empID = 0;
                    empID = (int)Util.getUserInput(" Please enter the employee ID search: ",InputType.INTEGER);
                    new Viewer(null,empID).displayEmployee();
                    break;
                case 2:
                    //View salaried Staff
                    new Viewer(EmployeeType.SALARIED_EMPLOYEE,0).displayEmployee();
                    break;
                case 3:
                    //View Hourly Staff
                    new Viewer(EmployeeType.HOURLY_EMPLOYEE,0).displayEmployee();
                    break;
                case 4:
                    //View Commissioned Staff
                    new Viewer(EmployeeType.COMMISSION_EMPLOYEE,0).displayEmployee();
                    break;
                case 5:
                    backToMainMenu = true;
                    break;
                default:
                    //Pause the console
                    Printer.printError(" Invalid Option\n Press ENTER to continue ...");
                    Util.getUserInput("",InputType.STRING);
            }
        }
    }

    //remove employees menu
    public void removeEmployeeMenu(){
        while (!backToMainMenu) {
            System.out.print("\n"+
                    "|```````````````````````````````````````````|\n" +
                    "|_____| Payroll Sys. >  *Remove Emp.* |_____|\n" +
                    "|```````````````````````````````````````````|\n" +
                    "| 1 - Remove Employee By Id                 |\n" +
                    "| 2 - B A C K    <<==                       |\n" +
                    "|_|```````````````````````````````````````|_|\n| ");

            //return the user input and cast it to integer
            int op = (int)Util.getUserInput(" Please choose an option: ",InputType.INTEGER);
            switch (op) {
                case 1:
                    //remove the employee with this id
                    int id = 0;
                    do {
                        id = (int)Util.getUserInput(" Remove Employee with ID :",InputType.INTEGER);
                        if (!(id > 0)){
                            System.err.println("Invalid ID");
                            id = 0;
                        }
                    }while (id == 0);
                    if (Database.removeByID(id)) {
                        Printer.printSuccess(" Employee (ID="+id+") removed successfully!\n Press ENTER to continue ...");
                        Util.getUserInput("",InputType.STRING);
                    }else {
                        Printer.printError(" Unable to remove employee (ID="+id+")");
                    }
                    break;
                case 2:
                    backToMainMenu = true;
                    break;
                default:
                    //Pause the console
                    Printer.printError(" Invalid Option\n Press ENTER to continue ...");
                    Util.getUserInput("",InputType.STRING);
            }
        }
    }

    //generate payslip menu
    public void generatePayslipMenu(){
        while (!backToMainMenu) {
            System.out.print("\n"+
                    "|```````````````````````````````````````````|\n" +
                    "|__| Payroll Sys. >  *Generate Payslips* |__|\n" +
                    "|```````````````````````````````````````````|\n" +
                    "| 1 - Create Payslip                        |\n" +
                    "| 2 - View Payslips                         |\n" +
                    "| 3 - B A C K    <<==                       |\n" +
                    "|_|```````````````````````````````````````|_|\n| ");

            //return the user input and cast it to integer
            int op = (int)Util.getUserInput(" Please choose an option: ",InputType.INTEGER);
            switch (op) {
                case 1:
                    //create a payslip
                    new Payslip().setIDAndMonth();
                    break;
                case 2:
                    //list payslips generated previously
                    try {
                        for (Object object : Database.findPayslip()){
                            new Payslip().showPayslip((String)object);
                        }
                    }catch (NullPointerException e){
                        //System.err.println(e.getMessage());
                    }
                    break;
                case 3:
                    backToMainMenu = true;
                    break;
                default:
                    //Pause the console
                    Printer.printError(" Invalid Option\n Press ENTER to continue ...");
                    Util.getUserInput("",InputType.STRING);

            }
        }
    }
}