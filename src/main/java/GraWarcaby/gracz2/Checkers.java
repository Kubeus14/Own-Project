package GraWarcaby.gracz2;

//Jest to główna klasa dla Aplikacji. Ma za zadanie wywołanie konstruktorów innych klas, które odpowiadają za rozgrywkę po stronie Gracza 1
//Wywołuje konstruktor klasy Plansza, który posiada całą logike gry. Klasa plansza odpowiada za bicia, poruszanie się pionków.
public class Checkers {

    //Jest to konstruktor klasy Warcaby, w którym jest cała logika aplikacji.
    public Checkers() {
        new Board();
    }

    //Metoda main wywołuje konstruktor klasy Warcaby, co za tym idzie uruchamia ona grę dla Gracza 1
    public static void main(String args[]) {
        new Checkers();
    }
}
