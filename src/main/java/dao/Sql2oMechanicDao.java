package dao;

import org.sql2o.Sql2o;

public class Sql2oMechanicDao {
    private final Sql2o sql2o;

    public Sql2oMechanicDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }
}
