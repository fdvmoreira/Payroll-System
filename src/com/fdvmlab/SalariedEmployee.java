/**
 * Define the Salaried employee
 */
package com.fdvmlab;
import java.time.LocalDate;

public class SalariedEmployee extends Employee {

    //Fields
    private EmploymentStatus empStatus;
    private float annualSalary = 0.0f;

    //Constructor
    public SalariedEmployee(int id,
                            String title,
                            String firstName,
                            String lastName,
                            LocalDate dataOfBirth,
                            String nationalInsuranceNumber,
                            JobTitle jobTitle,
                            Department jobDepartment,
                            EmploymentStatus empStatus,
                            float annualSalary) {
        //Call the base constructor
        super(
                id,
                title,
                firstName,
                lastName,
                dataOfBirth,
                nationalInsuranceNumber,
                jobTitle,
                jobDepartment);

        this.empStatus = empStatus;
        this.annualSalary = annualSalary;
    }

    public SalariedEmployee() {
        super();
    }

    //Getters and Setter
    public void setEmpStatus(EmploymentStatus empStatus) {
        this.empStatus = empStatus;
    }

    public void setAnnualSalary(float annualSalary) {
        this.annualSalary = annualSalary;
    }

    public EmploymentStatus getEmpStatus() {
        return empStatus;
    }

    public float getAnnualSalary() {
        return annualSalary;
    }
}
