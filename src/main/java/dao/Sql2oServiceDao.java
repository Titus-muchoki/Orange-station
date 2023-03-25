package dao;

import models.Client;
import models.Service;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oServiceDao implements ServiceDao {
        private final Sql2o sql2o;

    public Sql2oServiceDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Service service) {
        String sql = "INSERT INTO services(name)VALUES(:name)";
        try (Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(service)
                    .executeUpdate()
                    .getKey();
            service.getId();
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<Service> getAll() {
        try(Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM services")
                    .executeAndFetch(Service.class);
        }
    }

    @Override
    public List<Client> getAllClientsByService() {
        try (Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM clients WHERE serviceId = serviceId")
                    .executeAndFetch(Client.class);
        }
    }

    @Override
    public Service findById(int id) {
      try (Connection con = sql2o.open()){
          return con.createQuery("SELECT * FROM services WHERE id = :id")
                  .addParameter("id", id)
                  .executeAndFetchFirst(Service.class);
      }
    }

    @Override
    public void update(int id, String name) {
    String sql = "UPDATE services SET(name)=(:name)WHERE id = :id";
    try (Connection con = sql2o.open()){
        con.createQuery(sql)
                .addParameter("name", name)
                .addParameter("id", id)
                .executeUpdate();
    }catch (Sql2oException ex){
        System.out.println(ex);
    }
    }

    @Override
    public void deleteById(int id) {
    String sql = "SELECT from services WHERE id = :id";
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
    String sql = "SELECT from services";
    try (Connection con = sql2o.open()){
        con.createQuery(sql)
                .executeUpdate();
    }catch (Sql2oException ex){
        System.out.println(ex);
    }
    }
}
