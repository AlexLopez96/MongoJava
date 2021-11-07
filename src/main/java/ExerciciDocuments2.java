import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.DoubleStream;

import org.bson.Document;
import org.bson.json.JsonWriterSettings;

public class ExerciciDocuments2 {

    public static void main(String[] args) {

        //Creació del contingut de cada registre
        Rental[] rentals = {new Rental(1185, "2005-06-15 00:54:12.0","2005-06-23 02:42:12.0",2,611,
                            "MUSKETEERS WAIT"),
                            new Rental(1476, "2005-06-15 21:08:46.0", "2005-06-25 02:26:46.0",1,308,
                        "FERRIS MOTHER"),
                             new Rental(1725, "2005-06-16 15:18:57.0", "2005-06-17 21:05:57.0",1,159,
                        "CLOSER BANG")
        };

        Payment[] payments = {new Payment(3,6.00,"2005-06-15 00:54:12.0"),
                        new Payment(5,10.00,"2005-06-15 21:08:46.0"),
                        new Payment(6,5.00,"2005-06-16 15:18:57.0")
        };

        Person person = new Person(1, "MARY", "SMITH","1913 Hanoi Way", "Nagasaki",
                            "Sasebo", "Japan", "28303384290");

        //Bucle per afegir tots els camps de 'payments' a l'array 'rentals'
        for(int i=0;i< rentals.length;i++){
            rentals[i].payments.add(payments[i]);
        }
        //Afegim tots els camps a l'array de l'objecte 'person'
        person.addRentals(rentals);

        //I els passem a 'Document'
        Document document = person.toJsonDoc();

        JsonWriterSettings settings = JsonWriterSettings.builder().indent(true).build();
        System.out.println(document.toJson(settings));
    }
}

class Person {
    public final int id;
    public final String fName;
    public final String lName;
    public final String address;
    public final String district;
    public final String city;
    public final String country;
    public final String phone;
    public final List<Rental> rentals = new ArrayList<Rental>(); //Array on guardem el contingut de 'Rental' de cada 'Person'

    //Constructor de 'Person'
    public Person(int id, String fName, String lName, String address, String district,
                  String city, String country, String phone) {
        super();
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.address = address;
        this.district = district;
        this.city = city;
        this.country = country;
        this.phone = phone;
    }

    //Funció que afegeix a l'arrayList 'rental' de cada persona els seus camps, en el array 'rentals' també tenim els 'payments'
    // per tant ja tenim tots el camps aqui
    public void addRentals(Rental... rentals) {
        for (Rental rental : rentals) {
            this.rentals.add(rental);
        }
    }

    //Funció que retorna l'objecte 'Document' amb tots els camps per poder-se imprimir per pantalla
    public Document toJsonDoc(){
        Document personDoc = new Document("_id", this.id).append("First Name", this.fName).append("Last Name", this.lName)
                .append("Address", this.address).append("District", this.district).append("City", this.city)
                .append("Country", this.country).append("Phone", this.phone);

        ArrayList <Document> rentalArray = new ArrayList<>(); //Creació del arrayList per afegir els camps de 'Rentals' i 'Payments'

        for (Rental rental : this.rentals) {
            /*S'afageixen els camps de 'Rental' al array i es crida a la funció similar a aquesta (toJsonDoc) que afageix els 'Payments'.
            *
            * Bàsicament funciona en cadena. S'executa la funció 'toJsonDoc' per afegir els camps de 'Rental', aquesta funció sempre
            * retorna els camps en un objecte de tipus 'Document'.
            * I a la class 'Rental' hi ha una funció amb el mateix funcionament que aquesta però amb els camps de 'Payment'.
            *
            * Així que al funcionar en cadena, una vegada cridem aquesta funció ens retornarà un 'Document' amb
            * els camps de 'Person', 'Rental' i 'Payment'
             */
            rentalArray.add(rental.toJsonDoc());
        }

        personDoc.append("Rentals",rentalArray); //I per acabar s'afegeix al Document 'personDoc' creat anteriorment
        return personDoc;
    }
}

class Rental {
    public final int rentalId;
    public final String rentalDate;
    public final String returnDate;
    public final int staffId;
    public final int filmId;
    public final String filmTitle;
    public final List<Payment> payments = new ArrayList<Payment>(); //Array on guardem el contingut de 'Payment' de cada 'Rental'

    //Constructor de 'Rental'
    public Rental(int rentalId, String rentalDate, String returnDate, int staffId,
                  int filmId, String filmTitle ) {
        super();
        this.rentalId = rentalId;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.staffId = staffId;
        this.filmId = filmId;
        this.filmTitle = filmTitle;
    }

    //La mateixa funció que en la class 'Person' però en aquest cas es crida el 'toJsonDoc' de la class 'Payment'
    public Document toJsonDoc(){
        Document rentalDoc = new Document("rentalId", this.rentalId).append("Rental Date", this.rentalDate).append("Return Date", this.returnDate)
                .append("staffId", this.staffId).append("filmId", this.filmId).append("Film Title", this.filmTitle);

        ArrayList <Document> paymentArray = new ArrayList<>();

        for (Payment payment : this.payments) {
            /*
            * Aquí demanem a la funció 'toJsonDoc' de 'Payment' que ens retorni l'objecte 'Document' amb tots els camps de 'Payments'
            * */
            paymentArray.add(payment.toJsonDoc());
        }
        rentalDoc.append("Payments",paymentArray); //I els afegim al 'Document' de 'rentalDoc'
        return rentalDoc;
    }
}

class Payment {
    public final int paymentId;
    public final double amount;
    public final String paymentDate;

    //Constructor de 'Payment'
    public Payment(int paymentId, double amount, String paymentDate) {
        super();
        this.paymentId = paymentId;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    //Funció que retorna l'objecte 'Document' amb totes les dades de 'Payments'
    public Document toJsonDoc(){
        Document paymentDoc = new Document("payment Id", this.paymentId).append("Amount", this.amount)
                .append("Payment Date", this.paymentDate);

        return paymentDoc;
    }
}


