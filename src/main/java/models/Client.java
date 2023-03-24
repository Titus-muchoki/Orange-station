package models;

import java.util.Objects;

public class Client {
    private String name;
    private String email;
    private String tel;
    private String car;
    private int serviceId;
    private int id;

    public Client(String name, String email, String tel, String car, int serviceId) {
        this.name = name;
        this.email = email;
        this.tel = tel;
        this.car = car;
        this.serviceId = serviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return serviceId == client.serviceId && id == client.id && Objects.equals(name, client.name) && Objects.equals(email, client.email) && Objects.equals(tel, client.tel) && Objects.equals(car, client.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, tel, car, serviceId, id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
