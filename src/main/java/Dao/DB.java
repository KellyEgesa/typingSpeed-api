package Dao;

import org.sql2o.Sql2o;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

public class DB {
        public static Sql2o sql2o;

        static {
                try {
                        sql2o = getConnection();
                } catch (URISyntaxException e) {
                        e.printStackTrace();
                } catch (SQLException e) {
                        e.printStackTrace();
                }
        }

        private static Sql2o getConnection() throws URISyntaxException, SQLException {
                String url = "jdbc:postgresql://";
                String user = "";
                String pass = "";

                if(System.getenv("DATABASE_URL") == null){
                        url += "localhost:5432/typing_speed";
                        user = "kelly";
                        pass = "kelly@123";
                }
                else {
                        URI dbUri = new URI(System.getenv("DATABASE_URL"));
                        String username = dbUri.getUserInfo().split(":")[0];
                        String password = dbUri.getUserInfo().split(":")[1];
                        url += dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
                        user = username;
                        pass = password;
                }

                return new Sql2o(url, user, pass);
        }

}
