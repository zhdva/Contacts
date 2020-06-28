package org.zhadaev.service;

import org.zhadaev.model.*;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use= SOAPBinding.Use.LITERAL)
public interface IContactService {

    @WebMethod
    public Contact findById(final long id); //поиск контакта по id

    @WebMethod
    public List<Contact> findAll(); //список контактов

    @WebMethod
    public void save(final Contact contact); //добавление контакта в БД

    @WebMethod
    public void update(final Contact contact); //изменение контакта в БД

    @WebMethod
    public void delete(final Contact contact); //удаление контакта из БД

}