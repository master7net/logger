package logcore.domain.log;

import logcore.domain.log.loggername.LoggerName;
import logcore.domain.log.throwables.Throwables;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection="log")
public class LoggerMessage {

    @Id
    private String id;
    private LocalDateTime timestamp;
    private String level;
    private String thread;
    private String message;
    private LoggerName loggerName;
    private String method;
    private int lineNumber;
    private List<Throwables> throwables;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getThread() {
        return thread;
    }

    public void setThread(String thread) {
        this.thread = thread;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LoggerName getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(LoggerName loggerName) {
        this.loggerName = loggerName;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public List<Throwables> getThrowables() {
        return throwables;
    }

    public void setThrowables(List<Throwables> throwables) {
        this.throwables = throwables;
    }
}
