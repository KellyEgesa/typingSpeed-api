package Dao;

import org.junit.rules.ExternalResource;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class DatabaseRule extends ExternalResource {
    @Override
    public void before() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/typing_speed_test", "kelly", "kelly@123");

    }

    @Override
    public void after(){
        try(Connection con = DB.sql2o.open()){
            String deleteUsers = "DELETE FROM users *;";

            con.createQuery(deleteUsers)
                    .executeUpdate();

        }
    }
}
