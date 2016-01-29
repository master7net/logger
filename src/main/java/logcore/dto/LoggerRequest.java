package logcore.dto;

import java.time.LocalDateTime;
import java.util.Optional;

public class LoggerRequest {

    private String threadName;
    private String level;
    private String grep;
    private int limit;
    private int skip;
    private int refresh;
    private Optional<LocalDateTime> timestamp = Optional.empty();

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getGrep() {
        return grep;
    }

    public void setGrep(String grep) {
        this.grep = grep;
    }

    public Optional<LocalDateTime> getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Optional<LocalDateTime> timestamp) {
        this.timestamp = timestamp;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getSkip() {
        return skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }

    public int getRefresh() {
        return refresh;
    }

    public void setRefresh(int refresh) {
        this.refresh = refresh;
    }
}
