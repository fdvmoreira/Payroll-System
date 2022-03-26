/**
 * Defines the Commission Employee and inherits properties and methods from Employee Class
 */

package com.fdvmlab;
import java.time.LocalDate;

public class CommissionEmployee extends Employee{
    private float annualGrossSalary = 0.0f;
    private float commissionRate = 0.0f;

    //constructor
    public CommissionEmployee(
            int id,
            String title,
            String firstName,
            String lastName,
            LocalDate dataOfBirth,
            String nationalInsuranceNumber,
            JobTitle jobTitle,
            Department jobDepartment,
            float annualGrossSalary,
            float commissionRate) {

        //call base class constructor
        super(
                id,
                title,
                firstName,
                lastName,
                dataOfBirth,
                nationalInsuranceNumber,
                jobTitle,
                jobDepartment);

        this.annualGrossSalary = annualGrossSalary;
        this.commissionRate = commissionRate;
    }
    //no parameters constructor
    public CommissionEmployee() {
    }

    //Getters and Setters
    public float getAnnualGrossSalary() {
        return annualGrossSalary;
    }

    public void setAnnualGrossSalary(float annualGrossSalary) {
        this.annualGrossSalary = annualGrossSalary;
    }

    public float getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(float commissionRate) {
        this.commissionRate = commissionRate;
    }
}
