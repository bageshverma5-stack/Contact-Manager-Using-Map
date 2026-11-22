package contactmanager;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class ContactManager implements ContactOperations{
    private Map<String, Contact> contacts = new HashMap<>();
    
    @Override
    public boolean addContact(Contact contact) {
        if (contact == null) {
            return false;
        }
        String key = contact.getName().toLowerCase();
        if (contacts.containsKey(key)) {
            return false;
        }
        contacts.put(key, contact);
        return true;
    }

    @Override 
    public boolean deleteContact(String name){
        if (name == null ){
            return false;
        }
        String key = name.trim().toLowerCase();
        if(!contacts.containsKey(key)){
            return false;
        }

        contacts.remove(name);
        return true;
    }

     @Override
    public boolean updateContact(String name, String newEmail, String newPhoneNumber) {
        if (name == null) {
            return false;
        }
        Contact contact = contacts.get(name.trim().toLowerCase());
        if (contact == null) {
            return false;
        }

        String oldEmail = contact.getEmail();
        try {
            contact.setEmail(newEmail);
            contact.setPhoneNumber(newPhoneNumber);
        } catch (IllegalArgumentException e) {
            contact.setEmail(oldEmail);   // undo the half update
            throw e;                      // let Main show the error message
        }
        return true;
    }

    @Override
    public List<Contact> searchContact(String name) {
        List<Contact> result = new ArrayList<>();
        if (name == null) {
            return result;
        }
        String search = name.trim().toLowerCase();
        for (Contact c : contacts.values()) {
            if (c.getName().toLowerCase().contains(search)) {
                result.add(c);
            }
        }
        return result;
    }

    @Override
    public List<Contact> getAllContacts() {
        List<Contact> list = new ArrayList<>(contacts.values());
        list.sort(Comparator.comparing(Contact::getName, String.CASE_INSENSITIVE_ORDER));
        return list;
    }
}
