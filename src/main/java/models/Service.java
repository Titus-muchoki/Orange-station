package models;

import java.util.Objects;

public class Service {
    private String name;
    private int id;

    public Service(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Service)) return false;
        Service service = (Service) o;
        return id == service.id && Objects.equals(name, service.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}
