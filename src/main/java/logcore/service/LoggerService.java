package logcore.service;

import logcore.dao.LoggerDAO;
import logcore.domain.log.LoggerMessage;
import logcore.dto.LoggerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Qualifier("LogService")
public class LoggerService {

    @Autowired
    LoggerDAO loggerDAO;

    public List<LoggerMessage> getLogMessages(LoggerRequest loggerRequest) {
        return loggerDAO.findSortByIdDesc(loggerRequest);
    }

}
