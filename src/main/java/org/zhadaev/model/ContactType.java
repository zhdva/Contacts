package org.zhadaev.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "contact_types")
public class ContactType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "type", unique = true, nullable=false)
    private String type;

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactType that = (ContactType) o;
        return id == that.id &&
                type.equals(that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }

    @Override
    public String toString() {
        return "ContactType{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
