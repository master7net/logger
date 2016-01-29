package logcore.domain.throwables.stacktrace;

import logcore.domain.throwables.stacktrace.classname.ThrowClass;
import org.springframework.data.mongodb.core.mapping.Field;

public class StackTrace {

    private String fileName;
    private String method;
    private int lineNumber;
    @Field("class")
    private ThrowClass className;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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

    public ThrowClass getClassName() {
        return className;
    }

    public void setClassName(ThrowClass className) {
        this.className = className;
    }
}
