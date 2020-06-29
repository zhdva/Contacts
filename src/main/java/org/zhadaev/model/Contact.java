package org.zhadaev.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "person_id", nullable=false)
    private Person person;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "contact_type_id", nullable=false)
    private ContactType contactType;

    @Column(name = "number", nullable=false)
    private String number;

    public long getId() {
        return id;
    }

    public Person getPerson() {
        return person;
    }

    public ContactType getContactType() {
        return contactType;
    }

    public String getNumber() {
        return number;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return id == contact.id &&
                person.equals(contact.person) &&
                contactType.equals(contact.contactType) &&
                number.equals(contact.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, person, contactType, number);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", person=" + person +
                ", contactType=" + contactType +
                ", number='" + number + '\'' +
                '}';
    }
}
