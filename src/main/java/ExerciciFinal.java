import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import java.util.Scanner;
public class ExerciciFinal {
    public static Scanner sc = new Scanner(System.in);
    public static MongoClient client = new MongoClient();
    public static int option = 0;

    public static void main(String[] args) {

        MongoDatabase db = client.getDatabase("exInsert");

        printOptions();
        int option = 0;

        try {
            option = sc.nextInt();
        }catch (Exception e){
            System.out.println("***ERROR***");
            System.out.println("Selected option does NOT exists.");
            sc.nextLine();
            main(args);
        }

        selectOption(option);

        client.close();
    }

    public static void selectOption(int option){
        switch (option){
            case 0: client.close(); System.exit(0);
            case 1: addDocument(); break;
            case 2: findDocument();break;
            case 3: modifyDocument();break;
            case 4: deleteDocument();break;
        }
    }

    public static void addDocument(){
        System.out.println("Create selected");
    }
    public static void findDocument(){
        printOptionsFind();
        try {
            option = sc.nextInt();
        }catch (Exception e){
            System.out.println("***ERROR***");
            System.out.println("Selected option does NOT exists.");
            sc.nextLine();
            findDocument();
        }

        switch (option){

        }
    }

    public static void modifyDocument(){
        System.out.println("Modify selected");
    }
    public static void deleteDocument(){
        System.out.println("Delete selected");
    }

    public static void printOptions(){
        System.out.println("Choose one of the options bellow:");
        System.out.println("0-Exit.");
        System.out.println("1-Add a Document.");
        System.out.println("2-Find a Document.");
        System.out.println("3-Modify a Document.");
        System.out.println("4-Delete a Document.");
    }

    public static void printOptionsFind(){
        System.out.println("Choose one of the options bellow:");
        System.out.println("0-Exit.");
        System.out.println("1-Find by ID.");
        System.out.println("2-Find by city.");
        System.out.println("3-Find by population.");
        System.out.println("4-Find by state.");
    }
}
