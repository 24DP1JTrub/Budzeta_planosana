import java.time.LocalDate;
import java.util.ArrayList;

public class Lietotajs {
    private int id;
    private String email;
    private String password;
    private String name;
    private LocalDate registrationDate;
    private int dienas_limita_id;
    private ArrayList<Integer> BudzetasID = new ArrayList<>();
    private ArrayList<Integer> MerkasID = new ArrayList<>();
    private ArrayList<Integer> KategorijasID = new ArrayList<>();

    public Lietotajs(int id, String email, String password, String name, LocalDate registrationDate) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.registrationDate = registrationDate;
        this.dienas_limita_id = 0;
    }

    // funkcija getEmail atgriež String tipa vērtību email
    public String getEmail() {
        return email;
    }

    // funkcija getPassword atgriež String tipa vērtību password
    public String getPassword() {
        return password;
    }

    // funkcija getName atgriež String tipa vērtību name
    public String getName() {
        return name;
    }

    // funkcija getID atgriež int tipa vērtību id
    public int getID() {
        return id;
    }

    // funkcija getRegistrationDate atgriež LocalDate tipa vērtību registrationDate
    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    // funkcija getBudzetasID atgriež ArrayList tipa vērtību BudzetasID
    public ArrayList<Integer> getBudzetasID() {
        return BudzetasID;
    }

    // funkcija getMerkasID atgriež ArrayList tipa vērtību MerkasID
    public ArrayList<Integer> getMerkasID() {
        return MerkasID;
    }

    // funkcija collectIds savāc visus ar lietotāju asociētos plānus, mērķus un kategorijas un ievieto to id attiecīgajos sarakstos
    public void collectIds() {
        for (BudzetaPlans plans : Base.budzetaPlani) {
            if (plans.getLietotaja_id() == id) {
                BudzetasID.add(plans.getId());
            }
        }
        for (UzkrajumaMerkis merkis : Base.uzkrajumaMerkis) {
            if (merkis.getLietotaja_id() == id) {
                MerkasID.add(merkis.getId());
            }
        }
        for (int budzetaID : BudzetasID) {
            for (BudzetaKategorijas kategorija : Base.budzetaKategorijas) {
                if (kategorija.getBudzeta_plana_id() == budzetaID) {
                    KategorijasID.add(kategorija.getId());
                }
            }
        }
    }

    // funkcija setDienas_limita_id pieņem int tipa vērtību dienas_limita_id un nomaina esošo dienas_limita_id uz jauno dienas_limita_id
    public void setDienas_limita_id(int dienas_limita_id) {
        this.dienas_limita_id = dienas_limita_id;
    }

    // funkcija getDienas_limita_id atgriež int tipa vērtību dienas_limita_id
    public int getDienas_limita_id() {
        return dienas_limita_id;
    }
}
