package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] ljono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        initialice(KAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        initialice(kapasiteetti, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        initialice(kapasiteetti, kasvatuskoko);
    }

    private void validointi(int k, int s) {
        if (k < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti väärin");//heitin vaan jotain :D
        }
        if (s < 0) {
            throw new IndexOutOfBoundsException("kapasiteetti2");//heitin vaan jotain :D
        }
    }

    private void initialice(int k, int s) {
        validointi(k, s);
        ljono = new int[k];
        for (int i = 0; i < ljono.length; i++) {
            ljono[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = s;
    }

    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            ljono[alkioidenLkm] = luku;
            alkioidenLkm++;
            kasvatTaulukkonKokoa();
            return true;
        }
        return false;
    }

    private void kasvatTaulukkonKokoa() {
        if (alkioidenLkm % ljono.length == 0) {
            int[] taulukkoOld = new int[ljono.length + kasvatuskoko];
            for (int i = 0; i < ljono.length; i++) {
                taulukkoOld[i] = ljono[i];
            }
            for (int i = ljono.length; i < ljono.length + kasvatuskoko; i++) {
                taulukkoOld[i] = 0;
            }
            ljono = taulukkoOld;
        }
    }

    public boolean kuuluu(int luku) {
        if (alkioidenLkm == 0) {
            return false;
        }
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        boolean t = uusiTaulukko(luku);
        if (t) {
            return false;
        }
        ljono[ljono.length-1] = 0;
        alkioidenLkm--;
        return true;
        
    }
    
    private boolean uusiTaulukko(int luku){
        boolean t = true;
        for (int i = 0; i < ljono.length; i++) {
            if (ljono[i] == luku) {
                t = false;
            } else if (t) {
            } else{
                ljono[i-1] = ljono[i];
            }
        }
        return t;
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        String tuotos = "{";
        if (alkioidenLkm != 0) {
            tuotos += ljono[0];
            for (int i = 1; i < alkioidenLkm; i++) {
                tuotos += ", " + ljono[i];
            }
        }
        tuotos += "}";
        return tuotos;
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = ljono[i];
        }
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            x.lisaa(bTaulu[i]);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    y.lisaa(bTaulu[j]);
                }
            }
        }
        return y;

    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            z.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            z.poista(bTaulu[i]);
        }

        return z;
    }

}
