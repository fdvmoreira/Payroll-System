/**
 * Define the registerer (used to register the employees)
 */
package com.fdvmlab;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class Register {
    private Employee emp;
    private EmployeeType employeeType = null;

    //Constructor
    public Register(EmployeeType type){
        this.employeeType = type;
        switch (type){
            case SALARIED_EMPLOYEE:
                this.emp = new SalariedEmployee();
                break;
            case HOURLY_EMPLOYEE:
                this.emp = new HourlyEmployee();
                break;
            case COMMISSION_EMPLOYEE:
                this.emp = new CommissionEmployee();
                break;
            default:
                Printer.printError("Unknown Employee Type:\n Press ENTER to continue ..."+ type.toString());
                //Pause the console
                Util.getUserInput("",InputType.STRING);
        }
    }

    /**
     * Register an employee
     * Get the index of the array list and add new employee to it
     * @return true if the employee was registered successfully
     */
    private boolean registerEmployee(){

        //operation status
        boolean operationStatus = false;

        switch (this.employeeType){
            case SALARIED_EMPLOYEE:
                operationStatus = (Database.employeesDB.get(EmployeesIndex.SALARIED_EMPLOYEE)).add(this.emp);
                break;
            case HOURLY_EMPLOYEE:
                operationStatus = Database.employeesDB.get(EmployeesIndex.HOURLY_EMPLOYEE).add(this.emp);
                break;
            case COMMISSION_EMPLOYEE:
                operationStatus = Database.employeesDB.get(EmployeesIndex.COMMISSIONED_EMPLOYEE).add(this.emp);
                break;
            default:
                Printer.printError(" Unexpected Employee Type entered!\n Press ENTER to continue ...");
                //Pause the console
                Util.getUserInput("",InputType.STRING);
                return operationStatus;
        }
        return operationStatus;
    }

    /**
     * Get the employee details from user and add them to the employee
     */
    protected void setEmployeeDetails() {

        //Set Employee ID
        int id = 0;
        //Ask user for ID repeatably while id is invalid or been used
        while (id == 0){
            id = (int) Util.getUserInput("Employee ID : ", InputType.INTEGER);

            //check if this id registered
            if (id > 0 && (!Database.isEmployeeRegistered(id))){
                this.emp.setId(id);
            }else if (Database.isEmployeeRegistered(id)){
                Printer.printWarning(" This ID is already used!\n");
                id = 0;
            }else {
                id = 0;
            }
        }

        //title
        String title = "";
        title = (String) Util.getUserInput("Title : ", InputType.STRING);
        //set default value (N/A) if not entered by the user
        if (title.trim().equals("")) title = "N/A";
        this.emp.setTitle(title);

        //Set First and Last name
        String fn = "";
        String ln = "";
        do{
            //Get the input from user
            fn = (String) Util.getUserInput("First Name : ", InputType.STRING);
            ln = (String) Util.getUserInput("Last Name : ", InputType.STRING);

            //remove the white spaces
            fn = fn.trim();
            ln = ln.trim();

            //Set the first and last name if they aren't already set or empty
            if (Database.isEmployeeRegistered(fn,ln)){
                //reset the value
                Printer.printWarning("Employee ["+fn+" "+ln+"] is already registered!\n Press ENTER to continue ...");
                fn = "";
                ln = "";
            }
            //check if the user has entered a value
            if (fn.equals("") || ln.equals("")){
                Printer.printWarning(" No name entered!\n");
            }
        }while (fn.equals("") || ln.equals(""));
        this.emp.setFirstName(fn);
        this.emp.setLastName(ln);

        // Set the DOB
        LocalDate date = null;
        String userProvidedDate = "";
        //Keep asking for date while wrong format is entered
        do try {
            userProvidedDate = (String) Util.getUserInput("Date Of Birth (DD/MM/YYYY): ", InputType.STRING);
            //make sure the user has entered valid date
            try {
                date = LocalDate.parse(userProvidedDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } catch (Exception e) {
                date = null;
                userProvidedDate = "";
            }

            assert date != null; //make sure date is not null

            //Check if the date provided is older than 18 years old
            if (!(date.isBefore(LocalDate.now().minusYears(18L)))) {
                Printer.printError(" TOO YOUNG, 18+ Please! \n");
                userProvidedDate = "";
                date = null;
            }
        } catch (Exception e) {
            Printer.printError("\n DATE: Invalid date entered!\n Press ENTER to continue ...");
            Util.getUserInput("", InputType.STRING);
        } while ((!userProvidedDate.matches("^[0-9]{2}/[0-9]{2}/[0-9]{4}$") && (date == null)));
        this.emp.setDateOfBirth(date);

        //NINO
        String nino = (String) Util.getUserInput("National Insurance (NINO) Number: ", InputType.STRING);
        if (nino.trim().equals("")) nino = "NO NI NO SET";
        this.emp.setNationalInsuranceNumber(nino.toUpperCase());

        /**
         * Get and set the job jobTitle
         */
        int op = 0;
        JobTitle jobTitle = null;

        //keep asking for this detail while the user enters wrong value
        while (true) {
            if ((op > 6) || (op < 1)) {
                op = (int) Util.getUserInput("\n" +
                        " ***********| Job Title |************\n" +
                        " 1 - Supervisor\n" +
                        " 2 - Team Leader\n" +
                        " 3 - Manager\n" +
                        " 4 - CEO\n" +
                        " 5 - Director\n" +
                        " 6 - Engineer\n" +
                        " ____________________________________\n" +
                        " Select an option: ", InputType.INTEGER);
                switch (op) {
                    case 1:
                        jobTitle = JobTitle.SUPERVISOR;
                        break;
                    case 2:
                        jobTitle = JobTitle.TEAM_LEADER;
                        break;
                    case 3:
                        jobTitle = JobTitle.MANAGER;
                        break;
                    case 4:
                        jobTitle = JobTitle.CEO;
                        break;
                    case 5:
                        jobTitle = JobTitle.DIRECTOR;
                        break;
                    case 6:
                        jobTitle = JobTitle.ENGINEER;
                        break;
                    default:
                        Printer.printError("\n Unknown option selected!\n PRESS ENTER to continue ...");
                        jobTitle = null;
                }
            }else {
                //Break out of the loop
                break;
            }
        }
        //reset the option
        op = 0;
        this.emp.setJobTitle(jobTitle);

        /**
         * Get and set the job jobDepartment
         */
        op = 0;
        Department jobDepartment = null;
        //Keep asking for department while user enters invalid value
        while (true) {
            if ((op > 3) || (op < 1)) {
                op = (int) Util.getUserInput("\n" +
                        " **********| DEPARTMENT |**********\n" +
                        " 1 - Production\n" +
                        " 2 - Marketing\n" +
                        " 3 - Sales\n" +
                        " __________________________________\n" +
                        " Select an option: ", InputType.INTEGER);
                switch (op) {
                    case 1:
                        jobDepartment = Department.MARKETING;
                        break;
                    case 2:
                        jobDepartment = Department.PRODUCTION;
                        break;
                    case 3:
                        jobDepartment = Department.SALES;
                        break;
                    default:
                        jobDepartment = null;
                        Printer.printError("\n Unknown option selected!\n Press ENTER to continue ...");
                        //Pause the console
                        Util.getUserInput("",InputType.STRING);
                }
            }else {
                //
                break;
            }
        }
        this.emp.setJobDepartment(jobDepartment);

        //Get the remaining details about the employee based on type
        switch (employeeType){
            //Get the annual salary and employment status
            case SALARIED_EMPLOYEE:
                EmploymentStatus employmentStatus = EmploymentStatus.PART_TIME;
                float annualSalary = 0.0f;

                /*************** set the type of employment ***********************
                ******************************************************************/
                int choice = 0;
                do {
                    try {
                        choice = (int)Util.getUserInput("\n" +
                                " *********[ EMPLOYMENT STATUS ]*******\n" +
                                " 1 - PART-TIME \n" +
                                " 2 - FULLTIME \n" +
                                " _____________________________________\n" +
                                " Please select an option: ",InputType.INTEGER);
                        if (choice ==1 || choice == 2){
                            //set the employment status accordingly
                            if (choice == 2){
                                employmentStatus = EmploymentStatus.FULL_TIME;
                            }
                        }else {
                            //reset the choice
                            choice = 0;
                        }
                    }catch (Exception e){
                        choice = 0;
                        Printer.printError(e.getMessage());
                        Util.getUserInput("",InputType.STRING);
                    }

                }while (choice == 0);
                ((SalariedEmployee)this.emp).setEmpStatus(employmentStatus);
                annualSalary = (float)Util.getUserInput(" Annual Salary: ",InputType.FLOAT);
                //Prevent input of negative values
                if (annualSalary < 1) annualSalary = 1f;
                ((SalariedEmployee)this.emp).setAnnualSalary(annualSalary);
                break;

            // Get the annual gross salary and commission rate
            case COMMISSION_EMPLOYEE:
                float annualGrossSalary = 0.0f;
                float commissionRate = 0.0f;
                annualGrossSalary = (float)Util.getUserInput(" Annual Gross Salary: ",InputType.FLOAT);
                if (annualGrossSalary < 1) annualGrossSalary = 1f;
                ((CommissionEmployee)this.emp).setAnnualGrossSalary(annualGrossSalary);
                commissionRate = (float)Util.getUserInput(" Commission Rate: ",InputType.FLOAT);
                if (commissionRate < 0) commissionRate = 0.01f;
                ((CommissionEmployee)this.emp).setCommissionRate(commissionRate);
                break;

            //Get the hourly rate
            case HOURLY_EMPLOYEE:

                float hourlyRate = 0.0f;
                hourlyRate = (float)Util.getUserInput(" Hourly Rate: ",InputType.FLOAT);
                if (hourlyRate < 1) hourlyRate = 1f;
                ((HourlyEmployee)this.emp).setHourlyRate(hourlyRate);
                break;
            default:
                Printer.printError("Employee Type is Unknown!\n Press ENTER to continue ...");
                Util.getUserInput("",InputType.STRING);
        }

        //Register the employee


        //check if the registration was successful
        if (!registerEmployee()){
            Printer.printError(" Registration Has Failed!\n Please Try again!\n Press ENTER to continue ...");
        }else{
            Printer.printSuccess("****** EMPLOYEE CREATED SUCCESSFULLY! ******");
        }
        //Pause the console
        Util.getUserInput("",InputType.STRING);
    }

    //Getters and setters
    public Employee getEmp() {
        return emp;
    }

    public void setEmp(Employee emp) {
        this.emp = emp;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }
}