package dao;

import models.Client;

import java.awt.*;
import java.util.List;

public interface ClientDao {
    //CREATE
    void add(Client client);
    //READ
    List<Client> getAll();
    Client findById(int id);
    //UPDATE
    void update(int id, String name, String email, String tel, String car, int serviceId);
    //DELETE
    void deleteById(int id);
    void clearAll();

}
