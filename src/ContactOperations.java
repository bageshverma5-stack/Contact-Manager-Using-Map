package contactmanager;
import java.util.List;

public interface ContactOperations {
    boolean addContact(Contact contact);
    boolean deleteContact(String name);
    boolean updateContact(String phoneNumber, String newName, String newEmail);
    List<Contact> searchContact(String name);
    List<Contact> getAllContacts();
}