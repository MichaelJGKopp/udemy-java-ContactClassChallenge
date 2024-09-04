package dev.lpa;

import java.util.*;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        String phoneData = """
                Charlie Brown, 3334445555
                Maid Marion, 1234567890
                Mickey Mouse, 9998887777
                Mickey Mouse, 1247489758
                Minnie Mouse, 4567805666
                Robin Hood, 5647893000
                Robin Hood, 7899028222
                Lucy Van Pelt, 5642086852
                Mickey Mouse, 9998887777""";

        String emailData = """
                Mickey Mouse, mckmouse@gmail.com
                Mickey Mouse, micky1@aws.com
                Minnie Mouse, minnie@verizon.net
                Robin Hood, rhood@gmail.com
                Linus Van Pelt, lvpelt2015@gmail.com
                Duffy Duck, daffy@google.com""";

       List<Contact> phoneList = getData(phoneData);
       printData("Phone List", phoneList);
       List<Contact> emailList = getData(emailData);
        printData("Email List", emailList);
        System.out.println();

        Set<Contact> phoneSet = new HashSet<>(phoneList);
        printData("Phone Set", phoneSet);
        Set<Contact> emailSet = new HashSet<>(emailList);
        printData("Email Set", emailSet);
        System.out.println();

        int index = emailList.indexOf(new Contact("Robin Hood"));
        Contact robinHood = emailList.get(index);
        robinHood.addEmail("Sherwood Forest");
        robinHood.addEmail("Sherwood Forest");
        System.out.println(robinHood);
    }

    public static void printData(String header, Collection<Contact> contacts) {
        System.out.println("-------------------------------------------------------------");
        System.out.println(header);
        System.out.println("-------------------------------------------------------------");
        contacts.forEach(System.out::println);
    }

    public static List<Contact> getData(String phoneOrEmailData) {
        try {
            List<Contact> contacts = new ArrayList<>();
            Scanner scanner = new Scanner(phoneOrEmailData);
            while (scanner.hasNext()) {
                String line = scanner.nextLine().trim();
                String[] lineAr = line.split(",");
                if (lineAr.length < 2) {
                    continue;
                }
                String line0 = lineAr[0].trim();
                String line1 = lineAr[1].trim();
                if (Pattern.matches(".+@.+\\..+", line1)) {
                    contacts.add(new Contact(line0, line1));
                } else {
                    try {
                        contacts.add(new Contact(line0, Long.parseLong(line1)));
                    } catch (NumberFormatException e) {
                        System.out.println("Could not parse phone number: " + e.getMessage());
                    }
                }
            }
            return contacts;
        } catch (Exception e) {
                System.out.println("Exception getData: " + e.getMessage());
        }
        return null;
    }

}
