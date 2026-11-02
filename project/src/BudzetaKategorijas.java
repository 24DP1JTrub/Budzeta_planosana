public class BudzetaKategorijas {
    private int id;
    private String nosaukums;
    private int procenti;
    private double summa;
    private double notereta_summa;
    private int budzeta_plana_id;

    // Konstruktors, kas izveido jaunu budzeta kategorijas objektu ar visiem datiem
    public BudzetaKategorijas(int id, String nosaukums, int procenti, double summa, double notereta_summa, int budzeta_plana_id) {
        this.id = id;
        this.nosaukums = nosaukums;
        this.procenti = procenti;
        this.summa = summa;
        this.notereta_summa = notereta_summa;
        this.budzeta_plana_id = budzeta_plana_id;
    }

    // Get metodes, kas lauj pieklut privatajiem datiem no citam klasem
    public int getId() {
        return id;
    }

    // // funkcija getKategorijas_nosaukums atgriež String tipa vērtību nosaukums
    public String getKategorijas_nosaukums() {
        return nosaukums;
    }

    // funkcija atgriež int tipa vērtību procenti
    public int getProcenti() {
        return procenti;
    }

    // funkcija atgriež double tipa vērtību summa
    public double getSumma() {
        return summa;
    }

    // funkcija atgriež double tipa vērtību notereta_summa
    public double getNotereta_summa() {
        return notereta_summa;
    }

    // funkcija atgriež int tipa vērtību budzeta_plana_id
    public int getBudzeta_plana_id() {
        return budzeta_plana_id;
    }

    // Metode, lai papildinatu iztereto summu
    public void setNotereta_summa(double notereta_summa) {
        this.notereta_summa += notereta_summa;
    }

    // parraksta, lai butu lasams cilvekam
    @Override
    public String toString() {
        return id + ". " + nosaukums + " (Procenti: " + procenti + "%, Summa: " + summa + ", Notereta summa: " + notereta_summa + ", Atlikusa summa: " + (summa - notereta_summa) + ")";
    }

    // funkcija setAll pieņem String tipa vērtību nosaukums, in ttipa vērtību procenti un double tipa vērtības summa un notereta_summa un nomaina vecās mainīgo vērtības uz jaunajām
    public void setAll(String nosaukums, int procenti, double summa, double notereta_summa) {
        this.nosaukums = nosaukums;
        this.procenti = procenti;
        this.summa = summa;
        this.notereta_summa = notereta_summa;
    }
}
