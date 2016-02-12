package logcore.dao;

import logcore.domain.log.LoggerMessage;
import logcore.dto.LoggerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LoggerDAO {

    @Autowired
    MongoOperations mongoOperations;

    public List<LoggerMessage> findSortByIdDesc(LoggerRequest loggerRequest) {
        Query query = createQuery(loggerRequest);
        List<LoggerMessage>  logMessages = mongoOperations.find(query, LoggerMessage.class);
        return logMessages;
    }

    private Query createQuery(LoggerRequest loggerRequest) {
        Criteria criteria = createCriteria(loggerRequest);
        Query query = createQuery(loggerRequest, criteria);
        return query;
    }

    private Query createQuery(LoggerRequest loggerRequest, Criteria criteria) {

        Query query = new Query();

        query.addCriteria(criteria);
        query.with(new Sort(Sort.Direction.DESC, "_id"));

        query.limit(loggerRequest.getLimit());

        return query;
    }

    private Criteria createCriteria(LoggerRequest loggerRequest) {

        Criteria criteria = new Criteria();

        if (!loggerRequest.getLevel().isEmpty()) {

            if (loggerRequest.getLevel().equals("FATAL")) {
                criteria.where("level").is("FATAL");
            }

            if (loggerRequest.getLevel().equals("ERROR")) {
                criteria.orOperator(Criteria.where("level").is("FATAL"),
                        Criteria.where("level").is("ERROR"));
            }

            if (loggerRequest.getLevel().equals("WARN")) {
                criteria.orOperator(Criteria.where("level").is("FATAL"),
                        Criteria.where("level").is("ERROR"),
                        Criteria.where("level").is("WARN"));
            }

            if (loggerRequest.getLevel().equals("INFO")) {
                criteria.orOperator(Criteria.where("level").is("FATAL"),
                        Criteria.where("level").is("ERROR"),
                        Criteria.where("level").is("WARN"),
                        Criteria.where("level").is("INFO"));
            }

            if (loggerRequest.getLevel().equals("DEBUG")) {
                criteria.orOperator(Criteria.where("level").is("FATAL"),
                        Criteria.where("level").is("ERROR"),
                        Criteria.where("level").is("WARN"),
                        Criteria.where("level").is("INFO"),
                        Criteria.where("level").is("DEBUG"));
            }

            if (loggerRequest.getLevel().equals("TRACE")) {
                criteria.orOperator(Criteria.where("level").is("FATAL"),
                        Criteria.where("level").is("ERROR"),
                        Criteria.where("level").is("WARN"),
                        Criteria.where("level").is("INFO"),
                        Criteria.where("level").is("DEBUG"),
                        Criteria.where("level").is("TRACE"));
            }


        }

        loggerRequest.getTimestamp().ifPresent(localDateTime -> criteria.and("timestamp")
                .gt(loggerRequest.getTimestamp().get())
        );

        if (!loggerRequest.getThreadName().isEmpty()) {
            criteria.and("thread")
                    .is(loggerRequest.getThreadName());
        }
        if (!loggerRequest.getGrep().isEmpty()) {
            criteria.and("loggerName.fullyQualifiedClassName").regex(loggerRequest.getGrep(), "i");
        }

        return criteria;
    }
}
