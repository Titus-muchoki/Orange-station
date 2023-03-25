package dao;

import models.Client;
import models.Mechanic;

import java.util.List;

public interface MechanicDao {
    //CREATE
    void add(Mechanic mechanic);
    //READ
    List<Mechanic> getAll();
    List<Client> getAllMechanicsByClient(int mechanicId);
    Mechanic findById(int id);
    //UPDATE
    void update(int id, String name, String charges);
    //DELETE
    void deleteById(int id);
    void clearAll();
}
