package org.zhadaev.model;

import javax.persistence.*;

@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    private Person person;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contact_type_id")
    private ContactType contactType;

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
