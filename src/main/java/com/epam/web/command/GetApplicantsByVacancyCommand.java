package com.epam.web.command;

import com.epam.web.entity.RecruitingProcess;
import com.epam.web.service.RecruitingProcessService;
import com.epam.web.service.ServiceException;
import org.apache.commons.fileupload.FileUploadException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetApplicantsByVacancyCommand implements Command{
    private final RecruitingProcessService service;
    private final static String ID="id";
    private final static String APPLICANT_LIST_ATTRIBUTE ="applicantList";
    private final static String APPLICANTS = "applicants";

    public GetApplicantsByVacancyCommand(RecruitingProcessService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException, FileUploadException {
        String vacancyIdParam = request.getParameter(ID);
        Long id = Long.parseLong(vacancyIdParam);
        List<RecruitingProcess> applicantList = service.getByVacancyId(id);
        request.setAttribute(APPLICANT_LIST_ATTRIBUTE, applicantList);

        return CommandResult.redirect(APPLICANTS);
    }
}
