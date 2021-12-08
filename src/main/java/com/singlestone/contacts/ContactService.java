package com.singlestone.contacts;

import com.singlestone.contacts.model.Contact;
import com.singlestone.contacts.model.Phone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.util.Streamable.of;

/**
 * Service methods for CRUD operations, etc. on Contacts
 * Implementation of any Business Logic would be placed here.
 * Shields HTTP Controllers from persistence and business logic concerns,
 * allows for usage in places other than Rest Controllers.
 */
@Service
public class ContactService {

    // Good logging is important - I would typically use Spring AOP for this
    private static final Logger log = LoggerFactory.getLogger(ContactService.class);

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    /**
     * Returns a List of all Contacts
     */
    public List<Contact> getAllContacts() {
        log.info("getAllContacts()");
        return of(contactRepository.findAll()).toList();
    }

    /**
     * Returns the Contact with the given Id
     *
     * @throws ContactNotFoundException
     */
    public Contact getContact(Integer id) {
        log.info("getContact(" + id + ")");
        return contactRepository.findById(id).orElseThrow(() -> new ContactNotFoundException("Contact with id " + id + " was not found."));
    }

    public Integer createContact(Contact contact) {
        log.info("createContact()");
        // Ensure a new Contact is created - alternative would be to throw a 400 bad request if an Id was provided
        contact.setId(null);
        return updateContact(contact);
    }

    /**
     * Creates the Contact if no Id is present, or Updates an existing Contact
     */
    public Integer updateContact(Contact contact) {
        log.info("updateContact()");
        // If phone numbers are provided associate the children with the parent for generating
        // the foreign key
        if (contact.getPhone() != null) {
            for (Phone phone : contact.getPhone()) {
                phone.setContact(contact);
            }
        }
        contactRepository.save(contact);
        return contact.getId();
    }

    /**
     * Deletes the Contact with the given Id
     *
     * @throws ContactNotFoundException
     */
    public void deleteContact(Integer id) {
        log.info("deleteContact(" + id + ")");
        Contact contact = getContact(id);
        contactRepository.delete(contact);
    }

    /**
     * Returns a List of all Contacts that have a Home phone number
     */
    public List<Contact> getCallList() {
        return Streamable.of(contactRepository.findContactsWithHomePhone()).toList();
    }
}
