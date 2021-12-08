package com.singlestone.contacts;

import com.singlestone.contacts.model.Contact;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Defines / Extends the Spring CrudRepository interface,  Spring generates the implementation
 */
@Repository
public interface ContactRepository extends CrudRepository<Contact, Integer> {

    /**
     * Returns all Contacts that have a 'home' phone.
     */
    @Query("SELECT c FROM Contact c JOIN c.phone p WHERE p.type = 'home' order by c.name.last, c.name.first")
    List<Contact> findContactsWithHomePhone();

}
