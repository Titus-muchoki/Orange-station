package dao;

import models.Client;

import java.util.List;

public class Sql2oClientDao implements ClientDao{
    @Override
    public void add(Client client) {

    }

    @Override
    public List<Client> getAll() {
        return null;
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
