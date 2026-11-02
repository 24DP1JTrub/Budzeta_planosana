import java.util.ArrayList;

public class BudzetaPlans {
    private int id;
    private String nosaukums;
    private double summa;
    private int lietotaja_id;
    private ArrayList<Integer> KategorijasID = new ArrayList<>();

    // konstruktors jauna budzeta plana objekta izveidei
    public BudzetaPlans(int id, String nosaukums, double summa, int lietotaja_id) {
        this.id = id;
        this.nosaukums = nosaukums;
        this.summa = summa;
        this.lietotaja_id = lietotaja_id;
    }

    // Atgriez plana identifikatoru
    public int getId() {
        return id;
    }

    // Atgriez plana nosaukumu
    public String getPlana_nosaukums() {
        return nosaukums;
    }

    // Atgriez plana kopejo summa
    public double getSumma() {
        return summa;
    }

    // Atgriez lietotaja ID, kuram pieder plans
    public int getLietotaja_id() {
        return lietotaja_id;
    }

    // Atgriez sarakstu ar saistitajam kategorijam
    public ArrayList<Integer> getKategorijasID() {
        return KategorijasID;
    }

    // funkcija pieņem String tipa vērtību nosaukums un nomaina esošo nosaukumu uz jauno
    public void setNosaukums(String nosaukums) {
        this.nosaukums = nosaukums;
    }

    // funkcija pieņem double tipa vērtību summa un nomaina esošo vērtību summa uz jauno
    public void setSumma(double summa) {
        this.summa = summa;
    }
}
