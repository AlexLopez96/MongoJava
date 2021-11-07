import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.include;

import java.util.Scanner;

import com.mongodb.Block;
import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ExerciciConsultes1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introdueix el nom d'una població: ");
        String city = scanner.nextLine(); //String que emmagatzema el nom de la ciutat introduïda per l'usuari
        zipsByCity(city); //Crida de la funció de cerca
        scanner.close();
    }

    public static void zipsByCity(String city) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("exInsert"); //Connexió amb la BDD
        MongoCollection<Document> coll = db.getCollection("zips"); //Connexió amb la colecció
        Document projection = new Document("city","Chester");

        /*'Find' per trobar la ciutat demanada per l'usuari. En el 'Find' fem un 'equals' de la ciutat que demana l'usuari
         * passada a majúscules. Un cop fet això fa una projecció la qual mostra cada document de la col·lecció amb el filtre del 'Find'
         * i imprimeix per pantalla la id i l'estat dels documents.
        */
        coll.find(eq("city", city.toUpperCase())).projection(include()).forEach(
                (Block<? super Document>) (Document doc) -> System.out.print("Codi postal: "+
                        doc.getInteger("pop")+" - "));
    }
}
