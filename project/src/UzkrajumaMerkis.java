public class UzkrajumaMerkis {
    private int id;
    private String nosaukums;
    private double niepieciesama_summa;
    private double sakrata_summa;
    private int lietotaja_id;

    public UzkrajumaMerkis(int id, String nosaukums, double niepieciesama_summa, double sakrata_summa, int lietotaja_id) {
        this.id = id;
        this.nosaukums = nosaukums;
        this.niepieciesama_summa = niepieciesama_summa;
        this.sakrata_summa = sakrata_summa;
        this.lietotaja_id = lietotaja_id;
    }

    // funkcija getId atgriež int tipa vērtību id
    public int getId() {
        return id;
    }

    // funkcija getNosaukums atgriež String tipa vērtību nosaukums
    public String getNosaukums() {
        return nosaukums;
    }

    // funkcija getMerka_summa atgriež double tipa vērtību niepieciesama_summa
    public double getMerka_summa() {
        return niepieciesama_summa;
    }

    // funkcija getSakrata_summa atgriež double tipa vērtību sakrata_summa
    public double getSakrata_summa() {
        return sakrata_summa;
    }

    // funkcija getLietotaja_id atgriež int tipa vērtību lietotaja_id
    public int getLietotaja_id() {
        return lietotaja_id;
    }

    // funkcija setNotereta_summa pieņem double vērtību notereta_summa un pieskaita to double vērtībai sakrata_summa
    public void setNotereta_summa(double notereta_summa) {
        this.sakrata_summa += notereta_summa;
    }

    // funkcija toString atgriež vērtības id, nosaukums, niepieciesama_summa un sakrata_summa (izvada uzkrājuma mērķi)
    @Override
    public String toString() {
        return id + ". " + nosaukums + " - Nepieciesama summa: " + niepieciesama_summa + ", Sakrata summa: " + sakrata_summa;
    }

    // funkcija setAll pieņem String tipa vērtību nosaukums un double tipa vērtības niepieciesama_summa un sakrata_summa un nomaina vecās mainīgo vērtības uz jaunajām
    public void setAll(String nosaukums, double niepieciesama_summa, double sakrata_summa) {
        this.nosaukums = nosaukums;
        this.niepieciesama_summa = niepieciesama_summa;
        this.sakrata_summa = sakrata_summa;
    }
}
