import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ExerciciInsercio1 {
    public static void main(String[] args) {
        int id, pop;
        String city, state;
        double lat, lon;
        String line;
        String[] parts;
        List<Document> zips = new ArrayList<Document>();
        long importedDocs;
        long endTime;
        long startTime = System.currentTimeMillis();


        /*Lectura de cada linia del JSON i assignació de tots els componenets del registre a la seva variable corresponent.
        * Es fa amb un try per tal controlar errors.
         */
        try (BufferedReader reader = new BufferedReader(new FileReader("./src/main/resources/json/zips.json"))) {
            while ((line=reader.readLine())!=null) {
                parts = line.split("\""); //Divisor de cada part de cada registre en el array 'parts'

                //Assignació de cada variable segons la posició on es troba en l'array 'parts'
                id = Integer.parseInt(parts[3]);
                city = parts[7];
                state = parts[15];
                    //Replace traient les comes i els espais en blanc
                pop = Integer.parseInt(parts[12].replace(":", "").replace(",", "").trim());
                parts = parts[10].split(" ");
                lat = Double.parseDouble(parts[3].replace(",", "").trim());
                lon = Double.parseDouble(parts[4].trim());

                //Assignació al document totes les variables anteriors
                zips.add(new Document("_id", id).append("city", city).append("loc", Arrays.asList(lat, lon))
                        .append("pop", pop).append("state", state));

            }

            // Inserir el codi que falta aqui....
            MongoClient client = new MongoClient(); //Objecte per connectar-nos a la BDD
            MongoDatabase db = client.getDatabase("exInsert"); //Nom de la BDD
            MongoCollection<Document> coll = db.getCollection("zips"); //Nom de la colecció

            coll.drop(); //Borrem la colecció

            coll.insertMany(zips); //Inserció del array amb tots els registres a la collecció

            System.out.println("Numero de documents: " + zips.size()); //Impressió de quantitat de documents inserits
            System.out.println(coll.countDocuments()); //Impressió de quantitat de documents inserits



        } catch (IOException e) {
            System.err.println(e.getMessage()); //En cas de trobar un error que aquest s'imprimeixi per pantalla
        }
    }
}