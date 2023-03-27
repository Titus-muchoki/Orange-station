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
        mechanic.setId(id);
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
        try (Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM mechanics WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Mechanic.class);
        }
    }

    @Override
    public void update(int id, String name, String charges, int clientId) {
    String sql = "UPDATE mechanics SET(name, charges, clientId)=(:name, :charges, :clientId)WHERE id = :id";
    try (Connection con = sql2o.open()){
        con.createQuery(sql)
                .addParameter("name", name)
                .addParameter("charges", charges)
                .addParameter("clientId", clientId)
                .addParameter("id",id)
                .executeUpdate();
    }catch (Sql2oException ex){
        System.out.println(ex);
    }
    }

    @Override
    public void deleteById(int id) {
    String sql = "DELETE from mechanics WHERE id = :id";
    try (Connection con = sql2o.open()){
        con.createQuery(sql)
                .addParameter("id", id)
                .executeUpdate();
    }catch (Sql2oException ex){
        System.out.println(ex);
    }
    }

    @Override
    public void clearAll() {
    String sql = "DELETE from mechanics";
    try (Connection con = sql2o.open()){
        con.createQuery(sql)
                .executeUpdate();
    }
    }
}
