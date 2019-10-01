public class Main<T> {

    public static void main(String[] args) {
        //Liste<Integer> liste = new DobbeltLenketListe<>(new Integer[]{});
        //String[] s = { "Ole" , null , "Per" , "Kari ", null };
        //Liste liste = new DobbeltLenketListe(s);
        //System. out .println(liste.antall() + " " + liste. tom ());


        DobbeltLenketListe<Integer> liste = new DobbeltLenketListe<>();
        System. out .println(liste.toString() + " " + liste.omvendtString());
        for ( int i = 1; i <= 3; i++)
        {
            liste.leggInn(i);
            System. out .println(liste.toString() + " " + liste.omvendtString());
        }
// Utskrift:
// [] []
// [1] [1]
// [1, 2] [2, 1]
// [1, 2, 3] [3, 2, 1]

    }
}
