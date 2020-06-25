package org.zhadaev.model;

public class Contact {

    private long id;
    private Person person;
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
