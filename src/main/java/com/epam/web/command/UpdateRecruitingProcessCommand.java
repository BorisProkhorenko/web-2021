package com.epam.web.command;

import com.epam.web.entity.RecruitingProcess;
import com.epam.web.entity.User;
import com.epam.web.entity.Vacancy;
import com.epam.web.enums.ApplicantState;
import com.epam.web.enums.Gender;
import com.epam.web.service.RecruitingProcessService;
import com.epam.web.service.ServiceException;
import org.apache.commons.fileupload.FileUploadException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UpdateRecruitingProcessCommand implements Command {

    private final RecruitingProcessService processService;
    private final static String DEFAULT_INTERVIEW_MSG = "You are scheduled for an interview";
    private final static String DEFAULT_REJECT_MSG = "Sorry, but our company cannot offer you this position";
    private final static String DEFAULT_HIRED_MSG = "Congratulations on your new job in our company!";

    public UpdateRecruitingProcessCommand(RecruitingProcessService processService) {
        this.processService = processService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, ServiceException, FileUploadException {
        String idParam = request.getParameter("id");
        Long id = Long.parseLong(idParam);
        String pointsParam = request.getParameter("points");
        Integer points = Integer.parseInt(pointsParam);
        String stateParam = request.getParameter("state");
        ApplicantState state = ApplicantState.fromString(stateParam);
        RecruitingProcess process = processService.getById(id);
        User user = process.getUser();
        Vacancy vacancy = process.getVacancy();
        RecruitingProcess newProcess = new RecruitingProcess(id, user, vacancy, state, points);
        if (state == process.getState()|| state ==ApplicantState.NEW) {
            processService.update(newProcess);
            return CommandResult.redirect("applicants");
        } else {
            String msg = getMsgByState(state);
            HttpSession session = request.getSession();
            session.setAttribute("process", newProcess);
            session.setAttribute("msg", msg);
            return CommandResult.redirect("feedback");
        }
    }

    private String getMsgByState(ApplicantState state) {
        switch (state) {
            case REJECTED:
                return DEFAULT_REJECT_MSG;
            case HIRED:
                return DEFAULT_HIRED_MSG;
            default:
                return DEFAULT_INTERVIEW_MSG;
        }
    }
}
