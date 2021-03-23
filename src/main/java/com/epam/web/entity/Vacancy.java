package com.epam.web.entity;

public class Vacancy {

    private String name;
    private String salary;
    private String contacts;
    private String description;
    private String requirements;

    public Vacancy() {
    }

    public Vacancy(String name, String salary, String contacts, String description, String requirements) {
        this.name = name;
        this.salary = salary;
        this.contacts = contacts;
        this.description = description;
        this.requirements = requirements;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }
}
