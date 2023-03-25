package dao;

import models.Client;
import models.Mechanic;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oMechanicDao implements MechanicDao {
    private final Sql2o sql2o;

    public Sql2oMechanicDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Mechanic mechanic) {
    String sql = "INSERT INTO mechanics(name, charges, clientid)VALUES(:name, :charges, :clientId)";
    try (Connection con = sql2o.open()){
        int id = (int) con.createQuery(sql, true)
                .bind(mechanic)
                .executeUpdate()
                .getKey();
        mechanic.getId();
    }catch (Sql2oException ex){
        System.out.println(ex);
    }
    }

    @Override
    public List<Mechanic> getAll() {
        try (Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM mechanics")
                    .executeAndFetch(Mechanic.class);
        }
    }

    @Override
    public List<Client> getAllMechanicsByClient(int mechanicId) {
        try (Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM clients WHERE clientId = :clientId")
                    .addParameter("mechanicId", mechanicId)
                    .executeAndFetch(Client.class);
        }
    }

    @Override
    public Mechanic findById(int id) {
        return null;
    }

    @Override
    public void update(int id, String name, String charges) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void clearAll() {

    }
}
