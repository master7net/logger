package logcore.web.controller;

import logcore.domain.LoggerMessage;
import logcore.dto.LoggerRequest;
import logcore.service.LoggerService;
import logcore.service.SessionService;
import logcore.service.ThreadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class LoggerController {

    private static final Logger LOG = LoggerFactory.getLogger(LoggerController.class);

    @Autowired
    LoggerService loggerService;
    @Autowired
    SessionService sessionService;
    @Autowired
    ThreadService threadService;

    @RequestMapping("/rest/threads/")
    public Set<String> getThreadList() {
        return threadService.getThreads();
    }

    @RequestMapping(value = "/rest/log/", method = RequestMethod.GET)
    public List<LoggerMessage> getLogList() {

        LoggerRequest loggerRequest = new LoggerRequest();
        loggerRequest.setThreadName("");
        loggerRequest.setLevel("");
        loggerRequest.setGrep("");
        loggerRequest.setLimit(50);
        loggerRequest.setSkip(0);

        List<LoggerMessage> logMessages = loggerService.getLogMessages(loggerRequest);

        Collections.reverse(logMessages);

        return logMessages;
    }

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