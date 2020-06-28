package org.zhadaev.dao;

import org.zhadaev.model.Contact;
import org.zhadaev.model.ContactType;
import org.zhadaev.model.Person;

import java.util.List;

public interface IContactDao {

    public Contact findById(final long id); //поиск контакта по id

    public List<Contact> findAll(); //список контактов

    public void save(final Contact contact); //добавление контакта в БД

    public void update(final Contact contact); //изменение контакта в БД

    public void delete(final Contact contact); //удаление контакта из БД

}