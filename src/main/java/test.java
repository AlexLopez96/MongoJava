import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class test {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("exemples"); //Nom de la BDD
        MongoCollection<Document> coll = db.getCollection("mascotes"); //Nom de la colecció

        coll.drop();

        Document document = new Document("nom", "Buffy") //DOCUMENT
                .append("edat", 3)
                .append("espècie", "gat");
        System.out.println(document.toJson());
        coll.insertOne(document); //Inserit
        System.out.println(document.toJson());

        //Impressio de totes les BDD
        client.listDatabaseNames().forEach((Block<? super String>) (String name) -> {System.out.println(name);});
        client.close();

        client.close();
    }
}
