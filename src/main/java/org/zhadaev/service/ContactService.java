package org.zhadaev.service;

import org.zhadaev.dao.ContactDao;
import org.zhadaev.dao.IContactDao;
import org.zhadaev.model.*;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "org.zhadaev.service.IContactService")
public class ContactService implements IContactService {

    private final IContactDao contactDao = new ContactDao();

    @Override
    public Contact findById(final long id) {
        return contactDao.findById(id);
    }

    @Override
    public List<Contact> findAll() {
        return contactDao.findAll();
    }

    @Override
    public void save(final Contact contact) {
        contactDao.save(contact);
    }

    @Override
    public void update(final Contact contact) {
        contactDao.update(contact);
    }

    @Override
    public void delete(final Contact contact) {
        contactDao.delete(contact);
    }

    @Override
    public Person findPersonById(final long id) {
        return contactDao.findPersonById(id);
    }

    @Override
    public ContactType findContactTypeById(final long id) {
        return contactDao.findContactTypeById(id);
    }

}