package logcore.domain.throwables;


import logcore.domain.throwables.stacktrace.StackTrace;

import java.util.List;

public class Throwables {

    private String message;
    private List<StackTrace> stackTrace;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<StackTrace> getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(List<StackTrace> stackTrace) {
        this.stackTrace = stackTrace;
    }
}
