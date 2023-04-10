import java.io.IOException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Arrays;

public class ContactsMngr {
    public static void main(String[] args) throws IOException {
        String ourDirectory = "src/ContactsMngr/contacts";
        Path pathToContacts = Path.of(ourDirectory);
        System.out.println("pathToContacts = " + pathToContacts);
        if(Files.notExists(pathToContacts)){
            Files.createDirectory(pathToContacts);
        }
        String contactsFile = "contacts.txt";
        Path directoryAndFile = Path.of(ourDirectory, contactsFile);
        System.out.println("directoryAndFile = " + directoryAndFile);
        if(Files.notExists(directoryAndFile)){
            Files.createFile(directoryAndFile);
        }
    }
}
