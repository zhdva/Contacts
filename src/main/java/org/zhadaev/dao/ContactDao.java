package org.zhadaev.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.zhadaev.config.HibernateConfig;
import org.zhadaev.model.Contact;
import org.zhadaev.model.ContactType;
import org.zhadaev.model.Person;

import java.util.List;

public class ContactDao implements IContactDao {

    //фабрика сессий
    private final SessionFactory sf;

    public ContactDao() {
        sf = HibernateConfig.getSessionFactory();
    }

    public ContactDao(final SessionFactory sessionFactory) {
        this.sf = sessionFactory;
    }

    @Override
    public Contact findById(final long id) { //
        Session session = sf.openSession(); //открыть сессию
        Contact contact = session.get(Contact.class, id); //найти контакт в БД
        session.close(); //закрыть сессию
        return contact;
    }

    @Override
    public List<Contact> findAll() {
        Session session = sf.openSession();
        List<Contact> contacts = (List<Contact>) session.createQuery("From Contact").list();
        session.close();
        return contacts;
    }

    @Override
    public void save(final Contact contact) {
        Session session = sf.openSession(); //открыть сессию
        Transaction tx1 = session.beginTransaction(); //начать транзакцию

        //проверка на существующие ContactType и Person
        List<ContactType> contactTypes = (List<ContactType>) session.createQuery("From ContactType").list();
        if (!contactTypes.isEmpty() && contactTypes.contains(contact.getContactType())) {
            contact.setContactType(contactTypes.get(contactTypes.indexOf(contact.getContactType())));
        }
        List<Person> persons = (List<Person>) session.createQuery("From Person").list();
        if (!persons.isEmpty() && persons.contains(contact.getPerson())) {
            contact.setPerson(persons.get(persons.indexOf(contact.getPerson())));
        }

        session.persist(contact); //сохранить контакт в БД
        tx1.commit(); //выполнить транзакцию
        session.close(); //закрыть сессию
    }

    @Override
    public void update(final Contact contact) {
        Session session = sf.openSession();
        Transaction tx1 = session.beginTransaction();

        List<ContactType> contactTypes = (List<ContactType>) session.createQuery("From ContactType").list();
        if (!contactTypes.isEmpty() && contactTypes.contains(contact.getContactType())) {
            contact.setContactType(contactTypes.get(contactTypes.indexOf(contact.getContactType())));
        } else {
            ContactType contactType = (ContactType) session.merge(contact.getContactType());
            session.saveOrUpdate(contactType);
        }

        List<Person> persons = (List<Person>) session.createQuery("From Person").list();
        if (!persons.isEmpty() && persons.contains(contact.getPerson())) {
            contact.setPerson(persons.get(persons.indexOf(contact.getPerson())));
        } else {
            Person person = (Person) session.merge(contact.getPerson());
            session.saveOrUpdate(person);
        }

        session.update(contact);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(final Contact contact) {
        Session session = sf.openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(contact);
        tx1.commit();
        session.close();
    }

}