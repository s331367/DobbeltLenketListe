public class Main<T> {

    public static void main(String[] args) {
        //Liste<Integer> liste = new DobbeltLenketListe<>(new Integer[]{});
        //String[] s = { "Ole" , null , "Per" , "Kari ", null };
        //Liste liste = new DobbeltLenketListe(s);
        //System. out .println(liste.antall() + " " + liste. tom ());
        DobbeltLenketListe<String> liste =
                new DobbeltLenketListe<>(new String[]
                        {"Birger","Lars","Anders","Bodil","Kari","Per","Berit"});
        liste.fjernHvis(navn -> navn.charAt(0) == 'B'); // fjerner navn som starter med B
        System.out.println(liste + " " + liste.omvendtString());

      //   System.out.println(liste.subliste(0,11)); // skal kaste unntak
// Utskrift:
// [] []
// [1] [1]
// [1, 2] [2, 1]
// [1, 2, 3] [3, 2, 1]

    }
}
