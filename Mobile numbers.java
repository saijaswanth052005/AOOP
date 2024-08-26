import java.util.*;

// Contact class to store details of each contact
class Contact {
    private String name;
    private String phoneNumber;
    private String email;

    public Contact(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(phoneNumber, contact.phoneNumber) &&
               Objects.equals(name, contact.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber, name);
    }

    @Override
    public String toString() {
        return "Contact{name='" + name + "', phoneNumber='" + phoneNumber + "', email='" + email + "'}";
    }
}

// ContactManager class to manage contacts
public class ContactManager {
    // Use a Set to store unique contacts
    private Set<Contact> contactSet = new HashSet<>();

    // Use a Map for quick lookup by phone number
    private Map<String, Contact> contactMap = new HashMap<>();

    // Method to add a contact
    public void addContact(Contact contact) {
        if (contactSet.add(contact)) { // add returns false if the contact already exists
            contactMap.put(contact.getPhoneNumber(), contact);
            System.out.println("Contact added: " + contact);
        } else {
            System.out.println("Contact already exists: " + contact);
        }
    }

    // Method to retrieve a contact by phone number
    public Contact getContactByPhoneNumber(String phoneNumber) {
        return contactMap.get(phoneNumber);
    }

    // Method to remove a contact by phone number
    public void removeContactByPhoneNumber(String phoneNumber) {
        Contact contact = contactMap.remove(phoneNumber);
        if (contact != null) {
            contactSet.remove(contact);
            System.out.println("Contact removed: " + contact);
        } else {
            System.out.println("No contact found with phone number: " + phoneNumber);
        }
    }

    // Method to display all contacts
    public void displayAllContacts() {
        System.out.println("All Contacts:");
        for (Contact contact : contactSet) {
            System.out.println(contact);
        }
    }

    // Main method to demonstrate the functionality
    public static void main(String[] args) {
        ContactManager manager = new ContactManager();

        // Creating some contact objects
        Contact contact1 = new Contact("Alice", "1234567890", "alice@example.com");
        Contact contact2 = new Contact("Bob", "0987654321", "bob@example.com");
        Contact contact3 = new Contact("Charlie", "1234509876", "charlie@example.com");

        // Adding contacts to the manager
        manager.addContact(contact1);
        manager.addContact(contact2);
        manager.addContact(contact3);

        // Try adding a duplicate contact
        manager.addContact(new Contact("Alice", "1234567890", "alice2@example.com"));

        // Get a contact by phone number
        Contact retrievedContact = manager.getContactByPhoneNumber("1234567890");
        System.out.println("Retrieved Contact: " + retrievedContact);

        // Remove a contact by phone number
        manager.removeContactByPhoneNumber("0987654321");

        // Display all contacts
        manager.displayAllContacts();
    }
}