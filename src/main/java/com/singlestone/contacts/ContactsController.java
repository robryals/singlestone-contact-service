package com.singlestone.contacts;

import com.singlestone.contacts.model.Contact;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Rest Controller for CRUD operations on Contacts
 */
@RestController
@RequestMapping("/contacts")
public class ContactsController {

    private final ContactService contactService;

    public ContactsController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public ResponseEntity<List<Contact>> getContacts() {
        return ResponseEntity.ok(contactService.getAllContacts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContact(@PathVariable Integer id) {
        return ResponseEntity.ok(contactService.getContact(id));
    }

    @PostMapping
    public ResponseEntity createContact(@Valid @RequestBody Contact contact) {
        Integer id = contactService.createContact(contact);
        return ResponseEntity.status(HttpStatus.CREATED).body("Created contact with Id " + id);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateContact(@PathVariable Integer id, @Valid @RequestBody Contact contact) {
        contact.setId(id);
        contactService.updateContact(contact);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteContact(@PathVariable Integer id) {
        contactService.deleteContact(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/call-list")
    public ResponseEntity<List<Contact>> getCallList() {
        return ResponseEntity.ok(contactService.getCallList());
    }
}


