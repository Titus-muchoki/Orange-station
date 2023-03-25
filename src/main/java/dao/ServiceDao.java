package dao;

import models.Client;
import models.Service;

import java.util.List;

public interface ServiceDao {
    //CREATE
    void add(Service service);
    //READ
    List<Service> getAll();
    List<Client> getAllClientsByService();
    Service findById(int id);
    //UPDATE
    void update(int id, String name);
    //DELETE
    void deleteById(int id);
    void clearAll();
}
