import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;

import com.mongodb.Block;
import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.Locale;

public class ExerciciConsultes4 {
    static final String POPULATION = "KANSAS CITY"; //En cas de voler canviar la ciutat de cerca
    static int popCount = 0; //Variable que guarda el total de persones

    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("exInsert");
        MongoCollection<Document> coll = db.getCollection("zips");

        /*Mostra tots els documents on la ciutat sigui la constant 'POPULATION', només mostra els camps de 'city' i 'pop'
        * i exclou la 'ID', després per cada document trobat executa la funció d'impressió i suma del total fins que no
        * hi ha més documents
         */
        coll.find(eq("city", POPULATION.toUpperCase())).projection(fields(include("city", "pop"),
                excludeId())).forEach((Block<? super Document>) (Document doc) -> printAndCount(doc)
                 );

        System.out.println("---------------------------");
        System.out.println("Total de població: "+popCount); //Impressió del total de població

        client.close();
    }

    /*Funció que donat un objecte 'Document', imprimeix la ciutat i la població i afageix la quantitat de població
    * a la variable 'popCount'
     */
    public static void printAndCount(Document doc){
        System.out.println("City: "+doc.getString("city")+" - Pop: "+doc.getInteger("pop"));
        popCount+= doc.getInteger("pop");
    }

}