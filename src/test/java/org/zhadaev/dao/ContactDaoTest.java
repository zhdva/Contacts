package org.zhadaev.dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.zhadaev.model.*;

public class ContactDaoTest {

    private final IContactDao contactDao;
    private Contact actualContact1;
    private Contact actualContact2;
    private Contact actualContact3;
    private Contact actualContact4;

    public ContactDaoTest() {

        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.driver_class", "org.hsqldb.jdbc.JDBCDriver")
                .setProperty("hibernate.connection.url", "jdbc:hsqldb:mem:testdb")
                .setProperty("hibernate.connection.username", "SA")
                .setProperty("hibernate.connection.password", "")
                .setProperty("hibernate.show_sql", "true")
                .setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect")
                .setProperty("hibernate.hbm2ddl.auto", "create")
                .addAnnotatedClass(Contact.class)
                .addAnnotatedClass(ContactType.class)
                .addAnnotatedClass(Person.class);

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());

        contactDao = new ContactDao(sessionFactory);

    }

    @Before
    public void createContacts() {
        Person person1 = new Person();
        person1.setFirstName("Маргарита");
        person1.setMiddleName("Федоровна");
        person1.setLastName("Грязь");
        person1.setPosition("Уборщица");

        ContactType type1 = new ContactType();
        type1.setType("Рабочий");

        actualContact1 = new Contact();
        actualContact1.setPerson(person1);
        actualContact1.setContactType(type1);
        actualContact1.setNumber("88462171819");

        Person person2 = new Person();
        person2.setFirstName("Василий");
        person2.setMiddleName("Григорьевич");
        person2.setLastName("Путин");
        person2.setPosition("Директор");

        ContactType type2 = new ContactType();
        type2.setType("Мобильный");

        actualContact2 = new Contact();
        actualContact2.setPerson(person2);
        actualContact2.setContactType(type2);
        actualContact2.setNumber("89991112233");

        Person person3 = new Person();
        person3.setFirstName("Анатолий");
        person3.setLastName("Безликий");

        ContactType type3 = new ContactType();
        type3.setType("Рабочий");

        actualContact3 = new Contact();
        actualContact3.setPerson(person3);
        actualContact3.setContactType(type3);
        actualContact3.setNumber("88462124419");

        actualContact4 = new Contact();
        actualContact4.setPerson(person3);
        actualContact4.setContactType(type2);
        actualContact4.setNumber("88005553535");

        contactDao.save(actualContact1);
        contactDao.save(actualContact2);
        contactDao.save(actualContact3);
        contactDao.save(actualContact4);

    }

    @Test
    public void testFindById() {

        Contact expectedContact1 = contactDao.findById(1);
        Contact expectedContact2 = contactDao.findById(2);
        Contact expectedContact3 = contactDao.findById(3);
        Contact expectedContact4 = contactDao.findById(4);

        Assert.assertEquals(expectedContact1, actualContact1);
        Assert.assertEquals(expectedContact2, actualContact2);
        Assert.assertEquals(expectedContact3, actualContact3);
        Assert.assertEquals(expectedContact4, actualContact4);

    }

    @Test
    public void testFindAll() {
        Assert.assertEquals(contactDao.findAll().size(), 4);
    }

    @Test
    public void testSave() {
        testFindById();
    }

    @Test
    public void testUpdate() {
        Contact expectedContact = contactDao.findById(1);
        expectedContact.getPerson().setPosition("Пилот");
        contactDao.update(expectedContact);
        expectedContact = contactDao.findById(1);
        Assert.assertNotEquals(expectedContact, actualContact1);

        Contact expectedContact2 = contactDao.findById(2);
        expectedContact2.getContactType().setType("Рабочий");
        contactDao.update(expectedContact2);
        expectedContact2 = contactDao.findById(2);
        Assert.assertNotEquals(expectedContact2, actualContact2);
    }

    @Test
    public void testDelete() {
        contactDao.delete(actualContact3);
        Assert.assertEquals(contactDao.findAll().size(), 3);
    }
}