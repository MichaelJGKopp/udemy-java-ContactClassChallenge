package dev.lpa;

import java.util.HashSet;
import java.util.Set;

public class Contact {

    private String name;
    private Set<String> emails = new HashSet<>();
    private Set<String> phones = new HashSet<>();

    public Contact(String name) {
        this(name, null);
    }

    public Contact(String name, String email) {
       this(name, email, 0);
    }

    public Contact(String name, long phone) {
        this(name, null, phone);
    }

    public Contact(String name, String email, long phone) {
        this.name = name;
        if (email != null) {
            emails.add(email);
        }
        if (phone != 0) {
            String phoneStr = String.format("%010d", phone);
            phoneStr = "(%s) %s-%s"
                    .formatted(phoneStr.substring(0, 3), phoneStr.substring(3, 6), phoneStr.substring(6));
            phones.add(phoneStr);
        }
    }

    public String getName() {
        return name;
    }

/*    public Set<String> getEmails() {
        return new HashSet<>(emails);
    }

    public Set<String> getPhones() {
        return new HashSet<>(phones);
    }*/

    @Override
    public String toString() {
        return "%-20s %s %s".formatted(name, emails, phones);
    }

    public static long phoneNumber(String phone) {
        return Long.parseLong(phone.substring(1,4) + phone.substring(6,8) + phone.substring(9,12));
    }

    public Contact mergeContactData(Contact contact) {

        Contact mergedContact = new Contact(contact.getName());
        mergedContact.emails = new HashSet<>(this.emails);
        mergedContact.phones = new HashSet<>(this.phones);
        mergedContact.emails.addAll(contact.emails);
        mergedContact.phones.addAll(contact.phones);
        return mergedContact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;
        return getName().equals(contact.getName());
    }

    @Override
    public int hashCode() {
        return 33 * getName().hashCode();
    }

    public void addEmail(String companyName) {

        String[] names = name.split(" ");
        String email = "%c%s@%s.com".formatted(name.charAt(0), names[names.length - 1],
                companyName.replaceAll(" ", "").toLowerCase());
        if (!emails.add(email)) {
            System.out.println(email + " already exists!");
        }
    }

    public void replaceEmailIfExists(String oldEmail, String newEmail) {

        if (emails.contains(oldEmail)) {
            emails.remove(oldEmail);
            emails.add(newEmail);
        }
    }

}