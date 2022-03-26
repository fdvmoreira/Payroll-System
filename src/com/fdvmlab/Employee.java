/**
 * Defines the common features of all the employees, will be inherited by Salaried, Hourly and Commission employees
 */
package com.fdvmlab;
import java.time.LocalDate;

public abstract class Employee {
    //fields
    private int id;
    private String title;
    private String firstName;
    private String lastName;
    private LocalDate dataOfBirth;
    private String nationalInsuranceNumber;
    private JobTitle jobTitle;
    private Department jobDepartment;

    //Constructor
    public Employee(int id,
                    String title,
                    String firstName,
                    String lastName,
                    LocalDate dateOfBirth,
                    String nationalInsuranceNumber,
                    JobTitle jobTitle,
                    Department jobDepartment) {
        this.id = id;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dataOfBirth = dateOfBirth;
        this.nationalInsuranceNumber = nationalInsuranceNumber;
        this.jobTitle = jobTitle;
        this.jobDepartment = jobDepartment;
    }

    //no parameters constructor
    public Employee() {}

    ///Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dataOfBirth;
    }

    public void setDateOfBirth(LocalDate dataOfBirth) {
        this.dataOfBirth = dataOfBirth;
    }

    public String getNationalInsuranceNumber() {
        return nationalInsuranceNumber;
    }

    public void setNationalInsuranceNumber(String nationalInsuranceNumber) {
        this.nationalInsuranceNumber = nationalInsuranceNumber;
    }

    public JobTitle getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Department getJobDepartment() {
        return jobDepartment;
    }

    public void setJobDepartment(Department jobDepartment) {
        this.jobDepartment = jobDepartment;
    }
}
