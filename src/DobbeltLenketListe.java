import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.Iterator;
import java.util.*;

public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    public DobbeltLenketListe() {
        hode = hale = null;


    }

    public DobbeltLenketListe(T[] a) {
        int indeks = 0;
        antall = 0;

        indeksKontroll(indeks, true);  // Se Liste, true: indeks = antall er lovlig

        for(int j=0; j<a.length; j++) {
            T verdi = a[j];
            if(a[j] != null){
            if (indeks == 0)                     // ny verdi skal ligge først
            {
                hode = new Node<T>(verdi, null, hode);    // legges først
                if (antall == 0) hale = hode;      // hode og hale går til samme node
                indeks++;
            } else if (indeks == antall)           // ny verdi skal ligge bakerst
            {
                hale = hale.neste = new Node<T>(verdi, hale, null);  // legges bakerst
                indeks++;
            } else {
                Node<T> p = hode;                  // p flyttes indeks - 1 ganger
                for (int i = 1; i < indeks; i++) p = p.neste;

                p.neste = new Node<T>(verdi, p.forrige, p.neste);  // verdi settes inn i listen
                indeks++;
            }
                antall++;
            }

            // listen har fått en ny verdi                        // listen har fått en ny verdi
        }


    }

    public Liste<T> subliste(int fra, int til){
        fratilKontroll(antall, fra, til);

        Node<T> startVerdi = finnNode(fra);
        Liste<T> nyListe = new DobbeltLenketListe<T>();
        for(int i= fra; i<til; i++){
            nyListe.leggInn(startVerdi.verdi);
            startVerdi = startVerdi.neste;
        }
        return nyListe;
    }

    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {
        return antall == 0;
    }

    @Override
    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi, "Tabellen a er null!");

        if(tom()){
            hode = hale = null;
            hode = hale = new Node<>(verdi, null, null);
        }else{
            hale = hale.neste = new Node<>(verdi, hale,null);
        }
        endringer++;
        antall++;
        return true;

    }

    @Override
    public void leggInn(int indeks, T verdi) {
        Objects.requireNonNull(verdi, "Tabellen a er null!");

    }

    @Override
    public boolean inneholder(T verdi) {
        return indeksTil(verdi) != -1;
    }

    private Node<T> finnNode(int indeks){
        Node<T> p = null;
        int sisteVerdi = antall-1;
        if(indeks<antall/2){
            p = hode;
            for(int i = 0; i<indeks; i++)p = p.neste;
        }else{
            p = hale;
            for(int i = 0; i < sisteVerdi-indeks; i++)p = p.forrige;
        }
        return p;
    }
    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks, false);
        return finnNode(indeks).verdi;
    }

    @Override
    public int indeksTil(T verdi) {
        if(verdi == null) return -1;

        int indeks = -1; //Setter start indeks lik -1

        Node<T> listeVerdi = hode; // Gir listeVerdi lik første verdien til første elementet i dobbellenketliste

        int i = 0; //Teller runder
        while (i<antall()) {

            //If setning som sjekker om verdien stemmer med listeverdi
            if (verdi.equals(listeVerdi.verdi)) {
                    indeks = i; // Setter indeksen til verdien
                    i = antall(); //Slutter while løkken
            }

            listeVerdi = listeVerdi.neste; //listeverdi får neste verdi
            i++;
        }

        return indeks;
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        Objects.requireNonNull(nyverdi, "Ikke tillatt med null verdier");

        indeksKontroll(indeks, false);

        Node<T> p = finnNode(indeks);
        T gammelVerdi = p.verdi;

        p.verdi = nyverdi;
        endringer++;
        return gammelVerdi;
    }

    @Override
    public boolean fjern(T verdi) {
        throw new NotImplementedException();
    }

    @Override
    public T fjern(int indeks) {
        throw new NotImplementedException();
    }

    @Override
    public void nullstill() {
        throw new NotImplementedException();
    }
    private static void fratilKontroll(int tablengde, int fra, int til)
    {
        if (fra < 0)                                  // fra er negativ
            throw new IndexOutOfBoundsException
                    ("fra(" + fra + ") er negativ!");

        if (til > tablengde)                          // til er utenfor tabellen
            throw new IndexOutOfBoundsException
                    ("til(" + til + ") > antall(" + tablengde + ")");

        if (fra > til)                                // fra er større enn til
            throw new IllegalArgumentException
                    ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
    }

    @Override
    public String toString() {

        if(tom()) return "[]";

        StringBuilder liste = new StringBuilder();;

        Node<T> node = hode;

        liste.append("[").append(hode.verdi);
        node = node.neste;

        while(node != null){
            liste.append(',').append(" ").append(node.verdi);
            node = node.neste;
        }

        liste.append("]");

        return liste.toString();
    }

    public String omvendtString() {

        if(tom()) return "[]";

        StringBuilder liste = new StringBuilder();;

        Node<T> node = hale;

        liste.append("[").append(hale.verdi);
        node = node.forrige;

        while(node != null){
            liste.append(',').append(" ").append(node.verdi);
            node = node.forrige;
        }

        liste.append("]");

        return liste.toString();

    }

    @Override
    public Iterator<T> iterator() {
        throw new NotImplementedException();
    }

    public Iterator<T> iterator(int indeks) {
        throw new NotImplementedException();
    }

    private class DobbeltLenketListeIterator implements Iterator<T>
    {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator(){
            throw new NotImplementedException();
        }

        private DobbeltLenketListeIterator(int indeks){
            throw new NotImplementedException();
        }

        @Override
        public boolean hasNext(){
            throw new NotImplementedException();
        }

        @Override
        public T next(){
            throw new NotImplementedException();
        }

        @Override
        public void remove(){
            throw new NotImplementedException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new NotImplementedException();
    }

} // class DobbeltLenketListe


