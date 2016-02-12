package logcore.web.controller;

import logcore.domain.log.LoggerMessage;
import logcore.dto.LoggerRequest;
import logcore.service.LoggerService;
import logcore.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class LoggerController {

    @Autowired
    LoggerService loggerService;
    @Autowired
    SessionService sessionService;

    @RequestMapping(value = "/rest/log/", method = RequestMethod.PUT)
    public List<LoggerMessage> getLogList(@RequestBody LoggerRequest loggerRequest, HttpSession session) {

        final String sessionLog = sessionService.generateSessionLogAttribute(loggerRequest);
        if (loggerRequest.getRefresh() == 1) {
            session.removeAttribute(sessionLog);
        }
        else {
            loggerRequest.setTimestamp(Optional.ofNullable((LocalDateTime) session.getAttribute(sessionLog)));
        }

        List<LoggerMessage> logMessages = loggerService.getLogMessages(loggerRequest);

        logMessages.stream()
               .findFirst()
               .ifPresent(loggerMessage -> session.setAttribute(sessionLog, loggerMessage.getTimestamp()));

        Collections.reverse(logMessages);

        return logMessages;
    }

}