package com.epam.web.entity;

public class Vacancy {

    private String name;
    private String salary;
    private String responsibilities;
    private String description;
    private String requirements;


    public Vacancy(String name, String salary, String responsibilities, String description, String requirements) {
        this.name = name;
        this.salary = salary;
        this.responsibilities = responsibilities;
        this.description = description;
        this.requirements = requirements;
    }

    public String getName() {
        return name;
    }

    public String getSalary() {
        return salary;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public String getDescription() {
        return description;
    }

    public String getRequirements() {
        return requirements;
    }

}
