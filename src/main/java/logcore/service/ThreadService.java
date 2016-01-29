package logcore.service;

import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ThreadService {

    public Set<String> getThreads() {

        return  Thread.getAllStackTraces().keySet().stream()
                .map(thread -> getThreadName(thread))
                .collect(Collectors.toSet());

    }

    private String getThreadName(Thread thread) {
        return thread.getName();
    }
}
