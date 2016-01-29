package logcore.service;

import logcore.dto.LoggerRequest;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    public String generateSessionLogAttribute(LoggerRequest loggerRequest) {
        return loggerRequest.getThreadName() + ":" + loggerRequest.getLevel();
    }

}
