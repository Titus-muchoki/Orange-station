package models;

import java.util.Objects;

public class Mechanic {
    private String name;
    private String charges;
    private int clientId;
    private int id;

    public Mechanic(String name, String charges, int clientId) {
        this.name = name;
        this.charges = charges;
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mechanic)) return false;
        Mechanic mechanic = (Mechanic) o;
        return clientId == mechanic.clientId && id == mechanic.id && Objects.equals(name, mechanic.name) && Objects.equals(charges, mechanic.charges);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, charges, clientId, id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
