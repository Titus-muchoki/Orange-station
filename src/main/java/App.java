import dao.Sql2oClientDao;
import dao.Sql2oMechanicDao;
import dao.Sql2oServiceDao;
import org.sql2o.Sql2o;

import static spark.Spark.staticFileLocation;

public class App {
    public void main(String[] args){
        staticFileLocation("/public");
        String connectionString = "jdbc:postgresql://localhost:5432/garage";

        Sql2o sql2o = new Sql2o(connectionString, "kajela", "8444");
        Sql2oClientDao clientDao = new Sql2oClientDao(sql2o);
        Sql2oServiceDao serviceDao = new Sql2oServiceDao(sql2o);
        Sql2oMechanicDao mechanicDao = new Sql2oMechanicDao(sql2o);

    }
}
