package com.epam.web.command;

import com.epam.web.entity.RecruitingProcess;
import com.epam.web.entity.Response;
import com.epam.web.service.RecruitingProcessService;
import com.epam.web.service.ServiceException;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

public class UpdateProcessWithFeedbackCommand implements Command {

    private final RecruitingProcessService processService;
    private static final String APPLICANTS = "applicants";
    private static final Long MOCK_ID = 0L;
    private static final Date MOCK_DATE = new Date();
    private static final String SUBJECT = "subject";
    private static final String DETAILS = "details";

    public UpdateProcessWithFeedbackCommand(RecruitingProcessService processService) {
        this.processService = processService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String subject = request.getParameter(SUBJECT);
        String details = request.getParameter(DETAILS);
        HttpSession session = request.getSession();
        RecruitingProcess process = (RecruitingProcess) session.getAttribute("process");
        Response feedback = new Response(MOCK_ID, subject, details,MOCK_DATE,process);
        processService.updateWithFeedback(process,feedback);
        return CommandResult.redirect(APPLICANTS);
    }

}
