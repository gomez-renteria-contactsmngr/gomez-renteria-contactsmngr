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
    private  String name;
    private  String num;
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
            for (String contact : contacts) {
                System.out.println(contact);
            }
        }
    }
    public static void addContact()throws IOException{
        System.out.println("Name: ");
        String name = scanner.nextLine();
        System.out.println("Number: ");
        String num = scanner.nextLine();

        //Making it one line:
        String combined = name + num;
        System.out.println(combined);
        Files.write(contactsPath, Arrays.asList(combined), StandardOpenOption.APPEND);
//        contactArray.add("Name: " + name + " is now added to contacts.");
    }
    public static void searchContacts()throws IOException{
        System.out.println("Please enter search info: ");
        String userSearch = scanner.nextLine();
        System.out.println(userSearch);
    }
    public static void deleteContacts() throws IOException {
        System.out.println("Please enter name or number of contact you would like to delete: ");
        String deletedContact = scanner.nextLine();
        List<String> contacts = Files.readAllLines(contactsPath);
        List<String> newContacts = new ArrayList<>();
        for (String contact : contacts) {
            if (contact.contains(deletedContact)) {
                System.out.println("Contact deleted: " + contact);
            } else {
                newContacts.add(contact);
            }
        }
        Files.write(contactsPath, newContacts);
    }
    public static void exit() {
        String userInput = scanner.nextLine();
        System.out.println("are you sure you want to exit?");
        if(userInput.equalsIgnoreCase("yes")){
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
