package com.epam.web.service;

import com.epam.web.entity.Vacancy;

import java.util.Arrays;

public class VacancyService {


    public Object getVacancies() {
        Vacancy javaDev = new Vacancy("Java Developer","salary","company@mail.ru",
                "Description", "requirementssndfjnfsjfnsjnfsfnsjfnsijfnsijfnsjfsbfhu");

        Vacancy pyDev = new Vacancy("Python Developer","salary", "company@mail.ru",
                "Description", "requirements");

        Vacancy designer = new Vacancy("GUI Designer","salary", "company@mail.ru",
                "Description", "requirements");

        Vacancy salesforce = new Vacancy("Salesforce Manager","salary", "company@mail.ru",
                "Description", "requirements");

        Vacancy hr = new Vacancy("HR","salary", "company@mail.ru",
                "Description", "requirements");

        return Arrays.asList(javaDev, pyDev, designer, salesforce, hr);
    }
}
