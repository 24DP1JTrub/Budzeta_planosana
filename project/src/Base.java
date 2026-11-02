import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Base {
    // saraksti datu glabasana programmas izpildes laika
    static ArrayList<Lietotajs> lietotaji = new ArrayList<>();
    static ArrayList<BudzetaPlans> budzetaPlani = new ArrayList<>();
    static ArrayList<DienasLimits> dienasLimiti = new ArrayList<>();
    static ArrayList<BudzetaKategorijas> budzetaKategorijas = new ArrayList<>();
    static ArrayList<UzkrajumaMerkis> uzkrajumaMerkis = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static Regex regex = new Regex();

    // Metode datu saglabasanai CSV failos pec noradite indeksa
    public void save(int index) {
        String filePath;
        if (index == 0) {   // Lietotaji
            filePath = "data/Lietotajs.csv";
            String[] header = {"Lietotaja_ID", "E-pasts", "Parole", "Lietotajvards", "datums"};
            List<String[]> data = new ArrayList<>();
            for (Lietotajs lietotajs : lietotaji) {
                String[] row = {String.valueOf(lietotajs.getID()), lietotajs.getEmail(), lietotajs.getPassword(), lietotajs.getName(), lietotajs.getRegistrationDate().toString()};
                data.add(row);
            }
            CSVReaderWriter.writeCSV(filePath, header, data);
        } else if (index == 1) {    // Dienas limiti
            filePath = "data/Dienas_limits.csv";
            String[] header = {"ID", "Izdevumu_limits", "Ienakumu_limits", "Lietotaja_ID"};
            List<String[]> data = new ArrayList<>();
            for (DienasLimits limits : dienasLimiti) {
                String[] row = {String.valueOf(limits.getId()), String.valueOf(limits.getLimits()), String.valueOf(limits.getIztereta_summa()), String.valueOf(limits.getLietotaja_id())};
                data.add(row);
            }
            CSVReaderWriter.writeCSV(filePath, header, data);
        } else if (index == 2) {    // Uzkrajuma merkis
            filePath = "data/Uzkrajuma_merkis.csv";
            String[] header = {"Uzkrajuma_merka_ID", "Merka_nosaukums", "nepieciesama_summa", "sakrata_summas_dala", "Lietotaja_ID"};
            List<String[]> data = new ArrayList<>();
            for (UzkrajumaMerkis merkis : uzkrajumaMerkis) {
                String[] row = {String.valueOf(merkis.getId()), merkis.getNosaukums(), String.valueOf(merkis.getMerka_summa()), String.valueOf(merkis.getSakrata_summa()), String.valueOf(merkis.getLietotaja_id())};
                data.add(row);
            }
            CSVReaderWriter.writeCSV(filePath, header, data);
        } else if (index == 3) {    // Budzeta plans
            filePath = "data/Budzeta_plans.csv";
            String[] header = {"Budzeta_ID", "Budzeta_nosaukums", "Summa", "Lietotaja_ID"};
            List<String[]> data = new ArrayList<>();
            for (BudzetaPlans plans : budzetaPlani) {
                String[] row = {String.valueOf(plans.getId()), plans.getPlana_nosaukums(), String.valueOf(plans.getSumma()), String.valueOf(plans.getLietotaja_id())};
                data.add(row);
            }
            CSVReaderWriter.writeCSV(filePath, header, data);
        } else if (index == 4) {    // Budzeta kategorijas
            filePath = "data/Budzeta_kategorijas.csv";
            String[] header = {"Kategorijas_ID", "Nosaukums", "Procenti_no_summa", "Summa_no_procentiem", "Notereta_summa", "Budzeta_ID"};
            List<String[]> data = new ArrayList<>();
            for (BudzetaKategorijas kategorija : budzetaKategorijas) {
                String[] row = {String.valueOf(kategorija.getId()), kategorija.getKategorijas_nosaukums(), String.valueOf(kategorija.getProcenti()), String.valueOf(kategorija.getSumma()), String.valueOf(kategorija.getNotereta_summa()), String.valueOf(kategorija.getBudzeta_plana_id())};
                data.add(row);
            }
            CSVReaderWriter.writeCSV(filePath, header, data);
        }
    }

    // Metode lietotaja pārbaudei (autorizacijai)
    public Lietotajs autorizacija(String email, String password){
        for (Lietotajs lietotajs : lietotaji) {
            if (lietotajs.getEmail().equals(email) && lietotajs.getPassword().equals(password)) {
                System.out.println(ConsoleColors.GREEN.code + "Autorizacija veiksmiga!" + ConsoleColors.RESET.code);
                System.out.println(ConsoleColors.GREEN.code + "Labdien, " + lietotajs.getName() + "!" + ConsoleColors.RESET.code);
                return lietotajs;
            }
        }
        System.out.println(ConsoleColors.RED.code + "Nederigs e-pasts vai parole." + ConsoleColors.RESET.code);
        return null;
    }

    // Metode, kas saskaita visus izmantotus procentus konkretam budzeta planam
    public int summa_procentiem(int budzeta_plana_id) {
        int summa = 0;
        for (BudzetaKategorijas kategorija : budzetaKategorijas) {
            if (kategorija.getBudzeta_plana_id() == budzeta_plana_id) {
                summa += kategorija.getProcenti();
            }
        }
        return summa;
    }

    // Metode interaktivai budzeta plana ievadei no konsoles
    public int[] kategorijasIzvele(Scanner scanner, Lietotajs lietotajs) {
        System.out.println("Budzeta plāna ID:");
        budzetaPlaniList(lietotajs);

        if (lietotajs.getBudzetasID().isEmpty()) {
            return null;
        }

        System.out.print("Ievadi:");
        int budzeta_plana_id = Integer.parseInt(scanner.nextLine().trim());
        if (budzeta_plana_id < 0 || !lietotajs.getBudzetasID().contains(budzeta_plana_id)) {
            return null;
        }
        // Metode interaktivai kategorijas ievadei no konsoles
        System.out.println("Kategorijas ID:");
        budzetaKategorijasList(getBudzetaPlansById(budzeta_plana_id));

        if (getBudzetaPlansById(budzeta_plana_id).getKategorijasID().isEmpty()) {
            return null;
        }

        System.out.print("Ievadi:");
        int kategorijas_id = Integer.parseInt(scanner.nextLine().trim());
        if (kategorijas_id < 0 || !getBudzetaPlansById(budzeta_plana_id).getKategorijasID().contains(kategorijas_id)) {
            System.out.println(ConsoleColors.RED.code + "Nederigs kategorijas ID. Meginiet velreiz." + ConsoleColors.RESET.code);
            return null;
        }

        return new int[]{budzeta_plana_id, kategorijas_id};
    }

    public boolean emailExists(String email) {
        for (Lietotajs lietotajs : lietotaji) {
            if (lietotajs.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }



    
    public int getMerkisIdById(int id) {
        for (UzkrajumaMerkis merkis : uzkrajumaMerkis) {
            if (merkis.getId() == id) {
                return uzkrajumaMerkis.indexOf(merkis);
            }
        }
        return -1;
    }

    public int getBudzetaPlanIdById(int id) {
        for (BudzetaPlans plans : budzetaPlani) {
            if (plans.getId() == id) {
                return budzetaPlani.indexOf(plans);
            }
        }
        return -1;
    }

    public int getKategorijasIdById(int id) {
        for (BudzetaKategorijas kategorija : budzetaKategorijas) {
            if (kategorija.getId() == id) {
                return budzetaKategorijas.indexOf(kategorija);
            }
        }
        return -1;
    }


    // Metodes, lai izvadit sarakstus 

    public void uzkrajumaMerkasList(Lietotajs lietotajs) {
        if (lietotajs.getMerkasID().isEmpty()) {
            System.out.println(ConsoleColors.YELLOW.code + "Tev nav izveidots neviens uzkrajuma merkis." + ConsoleColors.RESET.code);
            return;
        }
        System.out.println(ConsoleColors.CYAN.code + "-".repeat(22));
        System.out.println("|Tavi uzkrajuma merki|");
        System.out.println("-".repeat(80));
        for (UzkrajumaMerkis merkis : uzkrajumaMerkis) {
            for (Integer uzkrajuma_merka_id : lietotajs.getMerkasID()) {
                if (merkis.getId() == uzkrajuma_merka_id) {
                    System.out.println("Merka ID: " + merkis.getId() + ", Nosaukums: " + merkis.getNosaukums() + ", Nepieciesama summa: " + merkis.getMerka_summa() + ", Sakrata summa: " + merkis.getSakrata_summa());
                }
            }
        }
        System.out.println(ConsoleColors.RESET.code);
    }

    public void budzetaPlaniList(Lietotajs lietotajs) {
        if (lietotajs.getBudzetasID().isEmpty()) {
            System.out.println(ConsoleColors.YELLOW.code + "Tev nav izveidots neviens budzeta plans." + ConsoleColors.RESET.code);
            return;
        }

        System.out.println(ConsoleColors.CYAN.code + "-".repeat(20));
        System.out.println("|Tavi budzeta plani|");
        System.out.println("-".repeat(60));
        for (BudzetaPlans plans : budzetaPlani) {
            for (Integer budzeta_plana_id : lietotajs.getBudzetasID()) {
                if (plans.getId() == budzeta_plana_id) {
                    System.out.println(" - Budzeta plana ID: "+ plans.getId() + ", Nosaukums: " + plans.getPlana_nosaukums() + ", Summa: " + plans.getSumma());
                }
            }
        }
        System.out.println(ConsoleColors.RESET.code);
    }

    public void budzetaKategorijasList(BudzetaPlans plans) {
        if (plans.getKategorijasID().isEmpty()) {
            System.out.println(ConsoleColors.YELLOW.code + "Sim budzeta planam nav izveidotas kategorijas." + ConsoleColors.RESET.code);
            return;
        }

        System.out.println(ConsoleColors.CYAN.code + "-".repeat(27));
        System.out.println("|Tavas budzeta kategorijas|");
        System.out.println("-".repeat(120));
        for (BudzetaKategorijas kategorija : budzetaKategorijas) {
            for (Integer kategorijas_id : plans.getKategorijasID()) {
                if (kategorija.getId() == kategorijas_id) {
                    System.out.println("Kategorijas ID: " + kategorija.getId() + ", Nosaukums: " + kategorija.getKategorijas_nosaukums() + ", Procenti no budzeta plana: " + kategorija.getProcenti() + "%, Summa no procentiem: " + kategorija.getSumma() + ", Notereta summa: " + kategorija.getNotereta_summa());
                }
            }
        }
        System.out.println(ConsoleColors.RESET.code);
    }


    // Metodes, lai iegutu datus no sarakstiem pēc ID
    
    public BudzetaPlans getBudzetaPlansById(int id) {
        for (BudzetaPlans plans : budzetaPlani) {
            if (plans.getId() == id) {
                return plans;
            }
        }
        return null;
    }

    public BudzetaKategorijas getBudzetaKategorijasById(int id) {
        for (BudzetaKategorijas kategorija : budzetaKategorijas) {
            if (kategorija.getId() == id) {
                return kategorija;
            }
        }
        return null;
    }

    public DienasLimits getDienasLimitsByLietotajaId(int lietotaja_id) {
        for (DienasLimits limits : dienasLimiti) {
            if (limits.getLietotaja_id() == lietotaja_id) {
                return limits;
            }
        }
        return null;
    }

    public UzkrajumaMerkis getUzkrajumaMerkisById(int id) {
        for (UzkrajumaMerkis merkis : uzkrajumaMerkis) {
            if (merkis.getId() == id) {
                return merkis;
            }
        }
        return null;
    }

    // Metodes, lai ieladet datus no CSV failiem

    public void loadLietotaji() {
        String csvPath = "data/Lietotajs.csv";
        List<String[]> data = CSVReaderWriter.readCSV(csvPath);
        for (String[] row : data) {
            if (row.length >= 5) {
                int id = Integer.parseInt(row[0].trim());
                String email = row[1].trim();
                String password = row[2].trim();
                String name = row[3].trim();
                LocalDate date = LocalDate.parse(row[4].trim());
                Lietotajs lietotajs = new Lietotajs(id, email, password, name, date);
                lietotaji.add(lietotajs);
            }
        }
    }

    public void loadDienasLimits() {
        String csvPath = "data/Dienas_limits.csv";
        List<String[]> data = CSVReaderWriter.readCSV(csvPath);
        for (String[] row : data) {
            if (row.length >= 4) {
                int id = Integer.parseInt(row[0].trim());
                double limits = Double.parseDouble(row[1].trim());
                double iztereta_summa = Double.parseDouble(row[2].trim());
                int lietotaja_id = Integer.parseInt(row[3].trim());
                DienasLimits limit = new DienasLimits(id, limits, iztereta_summa, lietotaja_id);
                dienasLimiti.add(limit);
            }
        }
    }

    public void loadUzkrajumaMerkis() {
        String csvPath = "data/Uzkrajuma_merkis.csv";
        List<String[]> data = CSVReaderWriter.readCSV(csvPath);
        for (String[] row : data) {
            if (row.length >= 5) {
                int id = Integer.parseInt(row[0].trim());
                String nosaukums = row[1].trim();
                double merka_summa = Double.parseDouble(row[2].trim());
                double sakrata_summa = Double.parseDouble(row[3].trim());
                int lietotaja_id = Integer.parseInt(row[4].trim());
                UzkrajumaMerkis merkis = new UzkrajumaMerkis(id, nosaukums, merka_summa, sakrata_summa, lietotaja_id);
                uzkrajumaMerkis.add(merkis);
            }
        }
    }

    public void loadBudzetaPlans() {
        String csvPath = "data/Budzeta_plans.csv";
        List<String[]> data = CSVReaderWriter.readCSV(csvPath);
        for (String[] row : data) {
            if (row.length >= 4) {
                int id = Integer.parseInt(row[0].trim());
                String plana_nosaukums = row[1].trim();
                double summa = Double.parseDouble(row[2].trim());
                int lietotaja_id = Integer.parseInt(row[3].trim());
                BudzetaPlans plans = new BudzetaPlans(id, plana_nosaukums, summa, lietotaja_id);
                budzetaPlani.add(plans);
            }
        }
    }

    public void loadBudzetaKategorijas() {
        String csvPath = "data/Budzeta_kategorijas.csv";
        List<String[]> data = CSVReaderWriter.readCSV(csvPath);
        for (String[] row : data) {
            if (row.length >= 6) {
                int id = Integer.parseInt(row[0].trim());
                String nosaukums = row[1].trim();
                int procenti = Integer.parseInt(row[2].trim());
                double summa = Double.parseDouble(row[3].trim());
                double notereta_summa = Double.parseDouble(row[4].trim());
                int budzeta_plana_id = Integer.parseInt(row[5].trim());
                BudzetaKategorijas kategorija = new BudzetaKategorijas(id, nosaukums, procenti, summa, notereta_summa,
                        budzeta_plana_id);
                budzetaKategorijas.add(kategorija);
                BudzetaPlans plans = getBudzetaPlansById(budzeta_plana_id);
                if (plans != null) {
                    plans.getKategorijasID().add(id);
                }
            }
        }
    }

    // Metode visu sistemas datu ieladei vienlaicigi
    public void loadAll() {
        loadLietotaji();
        loadDienasLimits();
        loadUzkrajumaMerkis();
        loadBudzetaPlans();
        loadBudzetaKategorijas();
    }

}