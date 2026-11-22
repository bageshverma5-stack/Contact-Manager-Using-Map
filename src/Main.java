package contactmanager;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ContactOperations contactOperations = new ContactManager();
        
    public static void main(String[] args) {
        while(true){
            showMenu();
            int choice = readChoice();
            try{
                switch (choice) {
                    case 1:
                        addContactUI();break;
                    case 2:
                        deleteContactUI();break;
                    case 3:
                        updateContactUI();break;
                    case 4:
                        searchContactUI();break;
                    case 5:
                        viewAllContactsUI();break;
                    case 6:
                        System.out.println("Bye Bye");
                        return;
                    default:
                        System.out.println("Enter a number between 1 to 6 ");
                }
            }catch(IllegalArgumentException e){
                System.out.println("Error"+ e.getMessage());
            }
        }
    }

    private static void showMenu(){
        System.out.println("\n==========Wlecome to Contact Manager=========");
        System.out.println("1. Add contacts ");
        System.out.println("2. Delete Contact ");
        System.out.println("3. Update contacts ");
        System.out.println("4. Search contacts ");
        System.out.println("5. View all contacts ");
        System.out.println("6. Exit");
        System.out.print("Enter any num 1 to 6 : ");
    }
    private static int readChoice() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1; 
        }
    }

    private static void addContactUI(){
        while (true) {
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            System.out.print("Enter email: ");
            String email = scanner.nextLine();
            System.out.print("Enter phone number: ");
            String phoneNumber = scanner.nextLine();

            try {
                Contact contact = new Contact(name, email, phoneNumber);
                if (contactOperations.addContact(contact)) {
                    System.out.println("Contact added successfully");
                } else {
                    System.out.println("A contact with this name already exists");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Do you want to add more contacts please enter (Yes/No)");
            String option = scanner.nextLine();
            if(option.equalsIgnoreCase("no")){
                break;
            }
            else if(option.equalsIgnoreCase("Yes")){
                continue;
            }
            else{
                throw new IllegalArgumentException("Please enter only (Yes/No)");
            }
        }
        
    }

    private static void deleteContactUI(){
        System.out.println("Enter name : ");
        String name = scanner.nextLine();
        if(contactOperations.deleteContact(name)){
            System.out.println("Contact  deleted successfully");
        }
        else{
            System.out.println("Contact not found");
        }
    }

     private static void updateContactUI() {
        System.out.print("Enter name of the contact to update: ");
        String name = scanner.nextLine();
        System.out.print("Enter new email: ");
        String email = scanner.nextLine();
        System.out.print("Enter new phone number: ");
        String phoneNumber = scanner.nextLine();

        try {
            if (contactOperations.updateContact(name, email, phoneNumber)) {
                System.out.println("Contact updated successfully");
            } else {
                System.out.println("Contact not found");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void searchContactUI() {
        System.out.print("Enter name to search: ");
        String name = scanner.nextLine();

        List<Contact> results = contactOperations.searchContact(name);
        if (results.isEmpty()) {
            System.out.println("Contact not found");
        } else {
            for (Contact c : results) {
                System.out.println(c);
                System.out.println("----------------");
            }
        }
    }

    private static void viewAllContactsUI() {
        List<Contact> contacts = contactOperations.getAllContacts();
        if (contacts.isEmpty()) {
            System.out.println("No contacts available");
            return;
        } 
        System.out.println();
        System.out.println(String.format("%-5s%-20s%-30s%-15s", "No.", "Name", "Email", "Phone"));
        System.out.println("-".repeat(70));

        int i = 1;
            for (Contact c : contacts) {
                System.out.println(String.format("%-5d", i++) + c);
            }
        System.out.println("-".repeat(70));
    }
}
