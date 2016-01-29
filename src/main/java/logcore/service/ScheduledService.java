package logcore.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ScheduledService {

    private static final Logger LOG = LoggerFactory.getLogger(ScheduledService.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 10000)
    public void reportCurrentTime() {

        LOG.info("The time is now " + dateFormat.format(new Date()));

        for (int i = 0; i < 10; i++) {
           //float f = 1 / (i - 5);
        }

    }
}
