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
        session.save(contact); //сохранить контакт в БД
        tx1.commit(); //выполнить транзакцию
        session.close(); //закрыть сессию
    }

    @Override
    public void update(final Contact contact) {
        Session session = sf.openSession();
        Transaction tx1 = session.beginTransaction();
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

    @Override
    public Person findPersonById(final long id) {
        Session session = sf.openSession();
        Person person = session.get(Person.class, id);
        session.close();
        return person;
    }

    @Override
    public ContactType findContactTypeById(final long id) {
        Session session = sf.openSession();
        ContactType contactType = session.get(ContactType.class, id);
        session.close();
        return contactType;
    }

}