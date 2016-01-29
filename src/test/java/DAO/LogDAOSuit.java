package DAO;

import logcore.Application;
import logcore.dao.LoggerDAO;
import logcore.dto.LoggerRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class LogDAOSuit {

    public static final int LIMIT = 100;

    @Autowired
    LoggerDAO loggerDAO;

    @Test
    public void test() {
        LoggerRequest loggerRequest = new LoggerRequest();
        loggerRequest.setThreadName("");
        loggerRequest.setGrep("");
        loggerRequest.setLevel("INFO");
        assertEquals(LIMIT, loggerDAO.findSortByIdDesc(loggerRequest).size());
    }

}
