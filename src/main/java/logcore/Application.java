package logcore;

import com.mongodb.MongoClient;
import logcore.web.security.ApplicationSecurity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.Optional;


@SpringBootApplication
@PropertySource("classpath:app.properties")
@EnableScheduling
public class Application implements CommandLineRunner {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }

    @Bean
    public WebSecurityConfigurerAdapter webSecurityConfigurerAdapter() {
        return new ApplicationSecurity();
    }

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
        return new SimpleMongoDbFactory(new MongoClient(), "log");
    }


    @Bean
    public MongoOperations mongoOperations() throws Exception {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
        return (MongoOperations) mongoTemplate;
    }

}
