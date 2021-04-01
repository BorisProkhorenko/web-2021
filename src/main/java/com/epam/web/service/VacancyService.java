package com.epam.web.service;

import com.epam.web.dao.*;

import com.epam.web.entity.Vacancy;

import java.sql.SQLException;

import java.util.List;


public class VacancyService {

    private final DaoHelperFactory daoHelperFactory;

    public VacancyService(){
        daoHelperFactory = new DaoHelperFactory();
    }

    public List<Vacancy> getVacancies() throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            VacancyDao vacancyDao = helper.createVacancyDao();
            return vacancyDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }



    /*
    public Object getVacancies() {
        Vacancy javaDev = new Vacancy(1L,"Java Developer","","responsibilities",
                "Description", "We are looking for engineers who are motivated and can take ownership to drive innovative features with an emphasis on quality. Strong technical expertise in Java;" +
                "Developing microservices for distributed systems" +
                "Agile and Scrum development methodologies" +
                "English language skills – sufficient for direct technical communication;" +
                "3+ years of experience in one of the following would be a big plus:" +
                "Cloud technologies (AWS, Azure, GCP)" +
                "Re-architecting legacy applications to microservices");

        Vacancy pyDev = new Vacancy(2L,"Python Engineer","3000-4000$", "responsibilities",
                "Description", "Good English communication skills. 4+ years of experience in web Back-end development Strong fundamental programming skills such as solid coding standard, design patterns." +
                "Experience with Python and Django/Flask, DRF." +
                "Familiar with container technologies." +
                "Strong organizational skills with great attention to detail." +
                "Team player with strong communication and interpersonal skills." +
                "Experience working in an agile environment.");

        Vacancy designer = new Vacancy(3L,"GUI Designer","1500$", "responsibilities",
                "Description", "Bachelor with UX/Visual Design, Human-Computer Interaction, Industrial Design or similar degrees preferred A minimum of 3 years of professional experience working in cross-disciplinary teams." +
                "You know how to use some of these tools: Mastery of Photoshop and/or Sketch and/or Illustrator;" +
                " Prototyping (Invision, Framer, etc.); Animation (After Effects, Web code, etc.) is a plus;" +
                " Basic knowledge (at least) of HTML, CSS, JS is a plus.");

        Vacancy salesforce = new Vacancy(4L,"Salesforce Consultant","", "responsibilities",
                "Description", "excellent communication skills and proficiency in both English and Russian (C1 level);" +
                "ability to prioritize tasks and handle critical situations at the client-end comfortably;" +
                "ability to understand the basics of SQL and databases;" +
                "experience in analysis, modeling and documentation of business processes;" +
                "experience in participating in customer and internal meetings;" +
                "experience in administrating 1С or Bitrix will be a plus;" +
                "experience in working with the Salesforce platform will be a plus.");

        Vacancy hr = new Vacancy(5L,"HR Coordinator","1500$", "responsibilities",
                "Description", "-Proven experience as an HR coordinator or relevant human resources position" +
                "-Knowledge of human resources processes and best practices" +
                "-Outstanding communication and interpersonal skills (English C1 and higher)" +
                "-Ability to handle data with confidentiality" +
                "-Good organizational and time management skills" +
                "-Administration or relevant field; additional education in Human Resource Management will be a plus");

        return Arrays.asList(javaDev, pyDev, designer, salesforce, hr);
    }

     */
}
