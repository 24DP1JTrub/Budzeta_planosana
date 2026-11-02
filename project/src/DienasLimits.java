public class DienasLimits {
    private int id;
    private double limits;
    private double iztereta_summa;
    private int lietotaja_id;

    public DienasLimits(int id, double limits, double iztereta_summa, int lietotaja_id) {
        this.id = id;
        this.limits = limits;
        this.iztereta_summa = iztereta_summa;
        this.lietotaja_id = lietotaja_id;
    }

    // funkcija getId atgriež int tipa vērtību id
    public int getId() {
        return id;
    }

    // funkcija getLimits atgriež double tipa vērtību limits
    public double getLimits() {
        return limits;
    }

    // funkcija getIztereta_summa atgriež double tipa vērtību iztereta_summa
    public double getIztereta_summa() {
        return iztereta_summa;
    }

    // funkcija getLietotaja_id atgriež int tipa vērtību lietotaja_id
    public int getLietotaja_id() {
        return lietotaja_id;
    }

    // fuknkcija setIztereta_summa pieņem double tipa vērtību iztereta_summa un boolean tipa vērtību overwrite un atkarībā no tā vai overwrite ir True vai False, pieskaita vai nomaina iztereta_summa
    public void setIztereta_summa(double iztereta_summa, boolean overwrite) {
        if (overwrite) {
            this.iztereta_summa = iztereta_summa;
        } else {
            this.iztereta_summa += iztereta_summa;
        }
    }
    // funkcija setLimits pieņem double tipa vērtību limits un nomaina veco limits uz jauno limits
    public void setLimits(double limits) {
        this.limits = limits;
    }
}
