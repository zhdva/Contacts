package org.zhadaev.model;

import javax.persistence.*;

@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "person_id", nullable=false)
    private Person person;

    @OneToOne
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

}
