import static com.mongodb.client.model.Projections.include;
import static com.mongodb.client.model.Sorts.descending;

import com.mongodb.Block;
import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ExerciciConsultes2 {

    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("exInsert");
        MongoCollection<Document> coll = db.getCollection("zips");

        /*Aquest programa fa el mateix que l'altre però ara fa un 'sort' al 'Find' descendent per mostrar de més gran a més petit i després
        * en el projection fa un límit de 10 per mostrar només 10 resultats com a màxmim.
        */
        coll.find().sort(descending("pop")).projection(include("pop")).limit(10).forEach((Block<? super Document>) (Document doc) ->
                System.out.println("Zip: "+doc.getInteger("_id")+" - Pop: "+doc.getInteger("pop")));
        client.close();
    }
}
