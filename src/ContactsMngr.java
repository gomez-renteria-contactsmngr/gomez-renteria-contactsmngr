import java.io.IOException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;

public class ContactsMngr {
    public static List<String> contactArray= new ArrayList<>();
    public static int userMenu (){
        System.out.println("Hello welcome to our contact manager. Please select a menu option between 1-5");
        System.out.println("1) View contacts.");
        System.out.println("2) Add contacts.");
        System.out.println("3) Search contacts.");
        System.out.println("4) Delete contacts.");
        System.out.println("5) Exit.");
        Scanner scanner = new Scanner (System.in);
        int userInt = scanner.nextInt();
        return userInt;
    }
    public static void main(String[] args) throws IOException {

    }

}
