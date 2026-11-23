import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ContactFileHandler {

    private final String csvFile = "../Data/contact.csv";

    public void saveContacts(List<Contact> contacts) {

        try (FileWriter fw = new FileWriter(csvFile)) {

            fw.write("Name,Email,Phone Number\n");

            for (Contact c : contacts) {

                fw.write(c.getName() + "," +
                         c.getEmail() + "," +
                         c.getPhoneNumber() + "\n");
            }

            System.out.println("Contacts saved successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
