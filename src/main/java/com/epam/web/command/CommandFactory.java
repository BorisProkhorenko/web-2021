package com.epam.web.command;

import com.epam.web.service.ApplicantService;
import com.epam.web.service.ResponseService;
import com.epam.web.service.UserService;
import com.epam.web.service.VacancyService;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class CommandFactory {
    private final static String LOGIN = "login";
    private final static String INVALID_LOGIN = "invalidLogin";
    private final static String MAIN = "mainPage";
    private final static String CV = "cv";
    private final static String RESPONSES = "responses";
    private final static String RESPONSE_DETAILS = "responseDetails";
    private final static String LOGOUT = "logout";
    private final static String VACANCY = "vacancy";
    private final static String EDIT_CV = "editCv";
    private final static String UPDATE_CV = "updateCv";
    private final static String EDIT_VACANCY = "editVacancy";
    private final static String UPDATE_VACANCY = "updateVacancy";
    private final static String CREATE_VACANCY = "createVacancy";
    private final static String DELETE_VACANCY = "deleteVacancy";
    private final static String PHOTO = "photo";
    private final static String EMPTY = "empty";
    private final static String VACANCY_LIST = "vacancyList";
    private final static String RESPONSE_LIST = "responseList";
    private final static String GET_VACANCY = "getVacancy";
    private final static String GET_USER = "getUser";
    private final static String GET_RESPONSE = "getResponse";
    private final static String IMAGE = "image";

    private final static String INDEX_PAGE = "index.jsp";
    private final static String MAIN_PAGE = "WEB-INF/view/main.jsp";
    private final static String CV_PAGE = "WEB-INF/view/cv.jsp";
    private final static String RESPONSES_PAGE = "WEB-INF/view/responses.jsp";
    private final static String RESPONSE_DETAILS_PAGE = "WEB-INF/view/responseDetails.jsp";
    private final static String VACANCY_PAGE = "WEB-INF/view/vacancy.jsp";
    private final static String EDIT_CV_PAGE = "WEB-INF/view/editCv.jsp";
    private final static String EDIT_VACANCY_PAGE = "WEB-INF/view/editVacancy.jsp";
    private final static String EMPTY_PAGE = "WEB-INF/view/fragments/empty.jsp";

    public Command create(String type) {
        switch (type) {
            case LOGIN:
                return new LoginCommand(new UserService());
            case INVALID_LOGIN:
                return new ShowPageCommand(INDEX_PAGE);
            case MAIN:
                return new ShowPageCommand(MAIN_PAGE);
            case EMPTY:
                return new ShowPageCommand(EMPTY_PAGE);
            case VACANCY_LIST:
                return new GetVacanciesByPageCommand(new VacancyService());
            case RESPONSE_LIST:
                return new GetResponsesByUserCommand(new ResponseService());
            case GET_VACANCY:
                return new GetVacancyCommand(new VacancyService());
            case GET_USER:
                return new GetUserCommand(new UserService());
            case GET_RESPONSE:
                return new GetResponseCommand(new ResponseService());
            case CV:
                return new ShowPageCommand(CV_PAGE);
            case RESPONSES:
                return new ShowPageCommand(RESPONSES_PAGE);
            case VACANCY:
                return new ShowPageCommand(VACANCY_PAGE);
            case RESPONSE_DETAILS:
                return new ShowPageCommand(RESPONSE_DETAILS_PAGE);
            case LOGOUT:
                return new LogoutCommand();
            case EDIT_CV:
                return new ShowPageCommand(EDIT_CV_PAGE);
            case UPDATE_CV:
                return new EditCvCommand(new ApplicantService());
            case EDIT_VACANCY:
                return new ShowPageCommand(EDIT_VACANCY_PAGE);
            case UPDATE_VACANCY:
                return new EditVacancyCommand(new VacancyService());
            case CREATE_VACANCY:
                return new CreateVacancyCommand();
            case DELETE_VACANCY:
                return new DeleteVacancyCommand(new VacancyService());
            case IMAGE:
                return new GetPhotoCommand(new ApplicantService());
            case PHOTO:
                ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
                return new UploadFileCommand(new ApplicantService(), servletFileUpload);
            default:
                throw new IllegalArgumentException("Unknown type of command" + type);
        }
    }
}
