import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.json.JsonWriterSettings;

public class ExerciciDocuments1 {

    public static void main(String[] args) {
        Actor[] actors = {new Actor(5, "LOLLOBRIGIDA","JOHNNY"),
                new Actor(27, "MCQUEEN", "JULIA"),
                new Actor(37, "BOLGER", "VAL"),
                new Actor(43, "JOVOVICH", "KIRK"),
                new Actor(84, "PITT", "JAMES"),
                new Actor(104, "CRONYN", "PENELOPE")
        };
        Film film = new Film(19, "AMADEUS HOLY", "A Emotional Display of a Pioneer And a Technical Writer who must Battle a Man in A Baloon",
                113, "PG", "Commentaries,Deleted Scenes,Behind the Scenes", 6, 20.99, "Action");
        film.addActors(actors);

        Document document = filmToDocument(film);

        JsonWriterSettings settings = JsonWriterSettings.builder().indent(true).build();
        System.out.println(document.toJson(settings));
    }

    public static Document filmToDocument(Film film) {
        Document filmDoc = new Document("_id", film.id).append("Title", film.title).append("Description", film.description)
                .append("Length", film.length).append("Rating", film.rating).append("Special Features", film.specialFeatures)
                .append("Rental Duration", film.rentalDuration).append("Replacement Cost", film.replacementCost)
                .append("Category", film.category);

        List<Document> actorsDoc = new ArrayList<Document>();
        for (Actor actor : film.actors) {
            Document actorDoc = new Document("actorId", actor.actorId).append("Last name", actor.lastName).append("First name", actor.firstName);
            actorsDoc.add(actorDoc);
        }
        filmDoc.append("Actors", actorsDoc);

        return filmDoc;
    }
}

class Actor {
    public final int actorId;
    public final String lastName;
    public final String firstName;

    public Actor(int actorId, String lastName, String firstName) {
        super();
        this.actorId = actorId;
        this.lastName = lastName;
        this.firstName = firstName;
    }
}

class Film {
    public final int id;
    public final String title;
    public final String description;
    public final int length;
    public final String rating;
    public final String specialFeatures;
    public final int rentalDuration;
    public final double replacementCost;
    public final String category;
    public final List<Actor> actors = new ArrayList<Actor>();

    public Film(int id, String title, String description, int length, String rating, String specialFeatures,
                int rentalDuration, double replaceMentcost, String category) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.length = length;
        this.rating = rating;
        this.specialFeatures = specialFeatures;
        this.rentalDuration = rentalDuration;
        this.replacementCost = replaceMentcost;
        this.category = category;
    }

    public void addActors(Actor... actors) {
        for (Actor actor : actors) {
            this.actors.add(actor);
        }
    }

}