import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ContactsMngr {
    public static Scanner scanner = new Scanner(System.in);
    static Path contactsPath = Path.of("src/contacts.txt");
    private static String name;
    private static String num;
    public void contactInfo(String name, String num){
        this.name= name;
        this.num= num;
    }
    public String toString(){
        return "Name: " + this.name + "Number: " + this.num;
    }
    public static List<String> contactArray= new ArrayList<>();
    public static int userMenu (){
        System.out.println("Hello welcome to our contact manager. Please select a menu option between 1-5");
        System.out.println("1) View contacts.");
        System.out.println("2) Add contacts.");
        System.out.println("3) Search contacts.");
        System.out.println("4) Delete contacts.");
        System.out.println("5) Exit.");
        int userInt = scanner.nextInt();
        return userInt;
    }
    public static void viewContacts() throws IOException {
        List<String> contacts = Files.readAllLines(contactsPath);
        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
        } else {
            System.out.println("Name | Phone number");
            System.out.println("-------------------");
            for (String contact : contacts) {
                String[] array= contact.split("\\|");
                System.out.println(array[0] + " | " + array[1]);
            }
        }
    }
    public static void addContact()throws IOException {
        List<String> contactArray = Files.readAllLines(contactsPath);
        scanner.nextLine();
        System.out.println("Name: ");
        String name = scanner.nextLine();
        System.out.println("Number: ");
        String num = scanner.nextLine();
        for (int j = 0; j < contactArray.size(); j++) {
            System.out.println(contactArray.get(j).split("\\|")[0]);
            if (name.equalsIgnoreCase(contactArray.get(j).split("\\|")[0])) {
                System.out.println("That contact already exists.  Do you want to replace it? Enter 'Y' for yes or 'N' for no.");
                String userInput = scanner.nextLine();
                if (userInput.equalsIgnoreCase("Y")) {
                    String combined = name + "|" + stringMaker(num);
                    contactArray.set(j, combined);
                    Files.write(contactsPath, contactArray);
                    break;
                } else {
                    String combined = name + "|" + stringMaker(num);
                    Files.write(contactsPath, Arrays.asList(combined), StandardOpenOption.APPEND);
                    break;
                }
            } else if (j == contactArray.size() - 1) {
                String combined = name + "|" + stringMaker(num);
                Files.write(contactsPath, Arrays.asList(combined), StandardOpenOption.APPEND);
            }
        }
    }
        public static String stringMaker (String contact){
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < contact.length(); i++) {
                char c = contact.charAt(i);
                if (Character.isDigit(c)) {
                    sb.append(c);
                }
            }
            String digitsOnly = sb.toString();


            StringBuilder formattedNum = new StringBuilder(digitsOnly);
            int len = formattedNum.length();
            for (int i = 3; i < len; i += 4) {
                formattedNum.insert(i, '-');
            }


            if (formattedNum.length() >= 3) {
                formattedNum = new StringBuilder("(" + formattedNum.substring(0, 3) + ")" + formattedNum.substring(3));
            }


            String combined = name + "|" + formattedNum.toString();
            System.out.println(combined);
            return formattedNum.toString();
        }
    public static void searchContacts()throws IOException{
        System.out.println("Search for contact by name or number: ");
        scanner.nextLine();
        String userSearch = scanner.nextLine();
        List<String> contacts = Files.readAllLines(contactsPath);
        for (String contact : contacts) {
            if (contact.contains(userSearch)) {
                System.out.println("Contact found: " + contact);
            }
        }
    }
    public static void deleteContacts() throws IOException {
        System.out.println("Please enter name or number of contact you would like to delete: ");
        scanner.nextLine(); //It was falling through before < added here
        String deletedContact = scanner.nextLine();
        List<String> contacts = Files.readAllLines(contactsPath);
        List<String> newContacts = new ArrayList<>();
        for (String contact : contacts) {
            if (contact.contains(deletedContact)) {
                System.out.println("Contact deleted: " + contact);
                continue;
            } else {
                newContacts.add(contact);
            }
        }
        Files.write(contactsPath, newContacts);
    }
    public static void exit() {
        scanner.nextLine();
        System.out.println("Are you sure you want to exit? Press 'Y' for yes or 'N' for no.");
        String userInput = scanner.nextLine();
        if(userInput.equalsIgnoreCase("Y")){
            System.out.println("You have exited the contacts manager");
            System.exit(0);
        }else{
            System.out.println("Please choose another menu option: ");
        }
    }
    public static void main(String[] args) throws IOException {
        while(true){
            int userOption = ContactsMngr.userMenu();
            if(userOption== 1){
                ContactsMngr.viewContacts();
            } else if (userOption== 2) {
                ContactsMngr.addContact();
            } else if (userOption==3) {
                ContactsMngr.searchContacts();
            } else if (userOption==4) {
                ContactsMngr.deleteContacts();
            }else if(userOption== 5){
                ContactsMngr.exit();
            }else{
                System.out.println("Invalid entry, please enter 1-5: ");
            }
        }
    }

}
