import static com.mongodb.client.model.Filters.lt;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import static com.mongodb.client.model.Sorts.ascending;

import com.mongodb.Block;
import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ExerciciConsultes3 {

    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("exInsert");
        MongoCollection<Document> coll = db.getCollection("zips");

        /*Mostra tots els documents ordenats de forma ascendet per el nom de la ciutat on el 'pop' és menor a 50 i la projecció
        * es busca per 'city' i 'pop' i exlou la id
         */
        coll.find(lt("pop", 50)).sort(ascending("city")).projection(fields(include("city", "pop"),
                excludeId())).forEach((Block<? super Document>) (Document doc) ->
                System.out.println("City: "+doc.getString("city")+" - Pop: "+doc.getInteger("pop")));

        client.close();
    }

}