public class Main<T> {

    public static void main(String[] args) {
        //Liste<Integer> liste = new DobbeltLenketListe<>(new Integer[]{});
        //String[] s = { "Ole" , null , "Per" , "Kari ", null };
        //Liste liste = new DobbeltLenketListe(s);
        //System. out .println(liste.antall() + " " + liste. tom ());


        Character[] c = {'A','B','C','D','E','F','G','H','I','J',};
        DobbeltLenketListe<Character> liste = new DobbeltLenketListe<>(c);
        System.out.println(liste.subliste(3,8)); // [D, E, F, G, H]
        System.out.println(liste.subliste(5,5)); // []
        System.out.println(liste.subliste(8,liste.antall())); // [I, J]
     //   System.out.println(liste.subliste(0,11)); // skal kaste unntak
// Utskrift:
// [] []
// [1] [1]
// [1, 2] [2, 1]
// [1, 2, 3] [3, 2, 1]

    }
}
