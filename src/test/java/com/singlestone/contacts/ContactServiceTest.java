package com.singlestone.contacts;

import com.singlestone.contacts.model.Contact;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContactServiceTest {

    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private ContactService contactService;

    @Test
    void getContact() {
        Contact contact = new Contact();
        when(contactRepository.findById(1)).thenReturn(Optional.of(contact));
        Contact contact1 = contactService.getContact(1);
        assertNotNull(contact1);
    }

    @Test
    void getContact_NotFound() {
        when(contactRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(ContactNotFoundException.class, () -> contactService.getContact(1));
    }

    @Test
    void deleteContact_NotFound() {
        when(contactRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(ContactNotFoundException.class, () -> contactService.deleteContact(1));
    }
}
