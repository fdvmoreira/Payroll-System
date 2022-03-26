/**
 * Defines the Hourly employee and inherits super class Employee
 */
package com.fdvmlab;
import java.time.LocalDate;

public class HourlyEmployee extends Employee{
    private float hourlyRate = 0.0f;

    //constructor
    public HourlyEmployee(
            int id,
            String title,
            String firstName,
            String lastName,
            LocalDate dataOfBirth,
            String nationalInsuranceNumber,
            JobTitle jobTitle,
            Department jobDepartment,
            float hourlyRate) {

        //call base constructor
        super(id,
                title,
                firstName,
                lastName,
                dataOfBirth,
                nationalInsuranceNumber,
                jobTitle,
                jobDepartment);
        this.hourlyRate = hourlyRate;
    }

    //no parameters constructor
    public HourlyEmployee() {}

    //Getters and Setters
    public float getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(float hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
}
