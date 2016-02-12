package logcore.dao;

import logcore.domain.security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class SecurityUserDAO {

    @Autowired
    MongoOperations mongoOperations;

    public SecurityUser getUserByUsername(String username) {
        return mongoOperations.findOne(Query.query(Criteria.where("username").is(username)), SecurityUser.class);
    }
}
