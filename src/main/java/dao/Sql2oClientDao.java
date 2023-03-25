package dao;

import models.Client;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;


import java.util.List;

public class Sql2oClientDao implements ClientDao{
    private final Sql2o sql2o;

    public Sql2oClientDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Client client) {
        String sql = "INSERT INTO clients(name, email, tel, car, serviceId)VALUES(:name, :email, :tel, :car, :serviceId)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(client)
                    .executeUpdate()
                    .getKey();
            client.getId();
        }catch (Sql2oException ex){
            System.out.println(ex);
        }

    }

    @Override
    public List<Client> getAll() {
        try(Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM clients")
                    .executeAndFetch(Client.class);
        }
    }

    @Override
    public Client findById(int Id) {
        return null;
    }

    @Override
    public void update(int id, String name, String email, String tel, String car, int serviceId) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void clearAll() {

    }
}
