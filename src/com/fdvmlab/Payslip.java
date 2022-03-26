/**
 * Define the Payslip
 */
package com.fdvmlab;
import java.time.LocalDate;

public class Payslip {

    private Employee employee;
    private int id;
    private Month month;

    //Non parameterized constructor
    public Payslip(){
        this.employee = null;
        this.id = 0;
        this.month = null;
    }

    //Parameterized constructor
    public Payslip(int id, Month month) {
        //check if employee is registered first
        this.employee = Database.findEmployee(id);
        if (!(this.employee == null)){
            this.employee = Database.findEmployee(id);
            this.id = id;
            this.month = month;
        }
    }

    /**
     * This method will loop through the list and generate a payslip for every month
     * desired and display them
     */
    public void generatePayslip(Employee employee){

         //check if there is employee to generate payslip
         if (employee!=null){

             LocalDate payslipDate = LocalDate.now();
             float grossPay = 0.0f;
             //Initializes for Salaried Employees
             String extraInfo = "";

             //get extra information (no of hours worked) for Hourly employees
             if (employee instanceof HourlyEmployee){
                 boolean invalidValue = true;
                 int numHoursWorked = 0;
                 while (invalidValue){
                     try {
                         numHoursWorked = (int)Util.getUserInput(" Number of hours worked :",InputType.INTEGER);
                         if (numHoursWorked > 0) {
                             grossPay += ((HourlyEmployee)employee).getHourlyRate() * numHoursWorked;
                             extraInfo = "Pay Per Hour _: "+ grossPay +"\n" +
                                     " No. of hours _: "+numHoursWorked+"\n";
                             invalidValue = false;
                         }
                     }catch (Exception e){
                         Printer.printError(" Something went wrong whilst entering the no. of hours worked!");
                         Util.getUserInput(" Press ENTER to continue ...",InputType.STRING);
                     }
                 }
             }

             //get extra information (sales amount) for Commission employees
             if (employee instanceof CommissionEmployee){
                 boolean invalidValue = true;
                 float sales = 0;

                 //make sure the correct value is entered
                 while (invalidValue){
                     try {
                         sales = (float)Util.getUserInput(" Please enter the sales amount: ",InputType.FLOAT);
                         if (sales > 0) {
                             float commission = (sales * ((CommissionEmployee)employee).getCommissionRate()/12);
                             grossPay = (float) ((CommissionEmployee)employee).getAnnualGrossSalary()/12;
                             extraInfo = "Salary _______: "+grossPay+"\n" +
                                     " Commission ___: "+commission+"\n" +
                                     " Sales Amount _: "+sales+"£\n";
                             invalidValue = false;
                         }
                     }catch (Exception e){
                         Printer.printError(" Something went wrong whilst entering the SALES AMOUNT!\n" +
                                 " Press ENTER to continue ...");
                         Util.getUserInput("",InputType.STRING);
                     }
                 }
             }

             //check if employee is Salaried Emp, if so, get the annual gross salary to compute the month salary
             if (employee instanceof SalariedEmployee) {
                 //Take the average monthly from annual salary
                 grossPay = (float) ((SalariedEmployee) employee).getAnnualSalary() / 12;
                 extraInfo = "Gross Pay ___: "+grossPay+"\n";
             }

             //Construct the payslip structure
             String payslip = "" +
                    " _________________________________________\n" +
                    " |______[ Marline Production Ltd ]_______|\n" +
                    " =========================================\n" +
                    " PAYSLIP DATE _: "+ month.toString().toLowerCase()+" "+payslipDate.getYear()+"\n"+
                    " ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                    " Employee ID __: "+employee.getId()+"\n" +
                    " Full Name ____: "+employee.getFirstName()+" "+employee.getLastName()+"\n" +
                    " NI Number ____: "+employee.getNationalInsuranceNumber()+"\n" +
                    " _________________________________________\n" +
                    " |______________[ EARNING   ]____________|\n" +
                    "                                          \n" +
                    " "+extraInfo+"\n" +
                    " _________________________________________\n"+
                    " |_____________[ DEDUCTIONS ]____________|\n" +
                    "                                          \n" +
                    " Tax __________: "+(grossPay *20/100)+"\n" +
                    " _________________________________________\n" +
                    " |_____________[  NET PAY   ]____________|\n" +
                    "                 "+(grossPay - grossPay*20/100)+"£\n" +
                    " |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n";

             //save payslip to database
             Database.payslips.add(payslip);

             //print the payslip
             Printer.printInfo(payslip);

             Printer.printSuccess(" #### PAYSLIP GENERATE SUCCESSFULLY #### \n Press ENTER to continue ...");
        }else {
             Printer.printError(" Employee Not Registered!\n Press ENTER to continue ...");
         }
         //pause the console
         Util.getUserInput("",InputType.STRING);
    }

    //
    public void setIDAndMonth(){
        //check if the employeeId is valid and if it's registered
        int employeeId = 0;
        do {
            employeeId = (int)Util.getUserInput(" Please Enter Employee ID: ", InputType.INTEGER);
            //clear the buffer
            //Util.getUserInput("",InputType.STRING);

            //Is number entered valid ?
            if (employeeId > 0){
                if (Database.isEmployeeRegistered(employeeId)){

                    //get the month from user
                    int monthNumber = 0;
                    Month month = null;
                    do {
                        monthNumber = (int)Util.getUserInput("" +
                                " ___________________________\n" +
                                " _________[ MONTH ]_________\n" +
                                "  1 - JANUARY\n" +
                                "  2 - FEBRUARY\n" +
                                "  3 - MARCH\n" +
                                "  4 - APRIL\n" +
                                "  5 - MAY\n" +
                                "  6 - JUNE\n" +
                                "  7 - JULY\n" +
                                "  8 - AUGUST\n" +
                                "  9 - SEPTEMBER\n" +
                                " 10 - OCTOBER\n" +
                                " 11 - NOVEMBER\n" +
                                " 12 - DECEMBER\n" +
                                " _____________________________\n" +
                                " Please select a month : ",InputType.INTEGER);
                        //clear the buffer
                        //Util.getUserInput("",InputType.STRING);

                        //check user's choice
                        switch (monthNumber){
                            case 1:
                                month = Month.JANUARY;
                                break;
                            case 2:
                                month = Month.FEBRUARY;
                                break;
                            case 3:
                                month = Month.MARCH;
                                break;
                            case 4:
                                month = Month.APRIL;
                                break;
                            case 5:
                                month = Month.MAY;
                                break;
                            case 6:
                                month = Month.JUNE;
                                break;
                            case 7:
                                month = Month.JULY;
                                break;
                            case 8:
                                month = Month.AUGUST;
                                break;
                            case 9:
                                month = Month.SEPTEMBER;
                                break;
                            case 10:
                                month = Month.OCTOBER;
                                break;
                            case 11:
                                month = Month.NOVEMBER;
                                break;
                            case 12:
                                month = Month.DECEMBER;
                                break;
                            default:
                                Printer.printError(" Invalid Month entered!\n Press ENTER to continue ...");
                                Util.getUserInput("",InputType.STRING);
                        }
                    }while (month == null);
                    //Create and display payslip
                    new Payslip(employeeId,month).generatePayslip(Database.findEmployee(employeeId));
                }else {
                    Printer.printError(" Employee Not Found!\n Press ENTER to continue ...");

                    Util.getUserInput("",InputType.STRING);

                }
            }else {
                employeeId = 0;
                Printer.printWarning(" Value entered is not valid!\n Press ENTER to continue ...");
                Util.getUserInput("",InputType.STRING);
            }
        }while ((employeeId == 0) || (!Database.isEmployeeRegistered(employeeId)));
    }

    /**
     * Print the payslip
     * @param payslipContent
     */
    void showPayslip(String payslipContent){
        Printer.printInfo(payslipContent);
    }
}
