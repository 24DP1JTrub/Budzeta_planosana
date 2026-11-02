import java.time.LocalDate;

public class Creater extends Base{
    
    // veic jauna lietotaja registraciju
    public Lietotajs registracija(String email, String password, String name){
        int id = Base.lietotaji.isEmpty() ? 1 : Base.lietotaji.get(Base.lietotaji.size() - 1).getID() + 1;
        LocalDate date = LocalDate.now();
        Lietotajs lietotajs = new Lietotajs(id, email, password, name, date);
        lietotaji.add(lietotajs);
        save(0);
        return lietotajs;
    }

    // izveido noklusejuma dienas limitu jaunam lietotajam
    public void newDienasLimits(Lietotajs lietotajs) {
        int id = Base.dienasLimiti.isEmpty() ? 1 : Base.dienasLimiti.get(Base.dienasLimiti.size() - 1).getId() + 1;
        DienasLimits limits = new DienasLimits(id, 100.0, 0.0, lietotajs.getID());
        lietotajs.setDienas_limita_id(id);
        dienasLimiti.add(limits);
        save(1);
    }

    // izveido jaunu uzkrajuma merki
    public void newUzkrajumaMerkis(Lietotajs lietotajs) {
        System.out.print("Ievadi uzkrajuma merka nosaukumu: ");
        String nosaukums = scanner.nextLine().trim();

        if (nosaukums.isEmpty() || !regex.checkName(nosaukums)) {
            System.out.println(ConsoleColors.RED.code + "Nederigs nosaukums. Megini velreiz." + ConsoleColors.RESET.code);
            return;
        }

        System.out.print("Ievadi uzkrajuma merka summu: ");
        String summa_String = scanner.nextLine().trim();

        if (summa_String.isEmpty()) {
            System.out.println(ConsoleColors.RED.code + "Summa nevar but tuksa. Meginiet velreiz." + ConsoleColors.RESET.code);
            return;
        } else if (!regex.checkNumber(summa_String)) {
            System.out.println(ConsoleColors.RED.code + "Summa ir jaraksta skaitliska forma. Meginiet velreiz." + ConsoleColors.RESET.code);
            return;
        }

        double summa = Double.parseDouble(summa_String);

        if (summa < 0) {
            System.out.println(ConsoleColors.RED.code + "Summa nevar but negativa. Meginiet velreiz." + ConsoleColors.RESET.code);
            return;
        }

        int id = Base.uzkrajumaMerkis.isEmpty() ? 1 : Base.uzkrajumaMerkis.get(Base.uzkrajumaMerkis.size() - 1).getId() + 1;

        UzkrajumaMerkis merkis = new UzkrajumaMerkis(id, nosaukums, summa, 0.0, lietotajs.getID());
        uzkrajumaMerkis.add(merkis);
        System.out.println("Merkis izlabots vejksmigi!");
        lietotajs.getMerkasID().add(id);
        save(2);
        System.out.println(ConsoleColors.GREEN.code + "Uzkrajuma merkis izveidots veiksmigi!" + ConsoleColors.RESET.code);
    }

    //izveido jaunu budzeta planu (piemeram menesa budzetu)
    public void newBudzetaPlans(Lietotajs lietotajs) {
        System.out.print("Ievadi budzeta plana nosaukumu: ");
        String nosaukums = scanner.nextLine().trim();

        if (nosaukums.isEmpty() || !regex.checkName(nosaukums)) {
            System.out.println(ConsoleColors.RED.code + "Nederigs nosaukums. Megini velreiz." + ConsoleColors.RESET.code);
            return;
        }

        System.out.print("Ievadi budzeta plana summu: ");
        String summa_String = scanner.nextLine().trim();

        if (summa_String.isEmpty()) {
            System.out.println(ConsoleColors.RED.code + "Summa nevar but tuksa. Meginiet velreiz." + ConsoleColors.RESET.code);
            return;
        } else if (!regex.checkNumber(summa_String)) {
            System.out.println(ConsoleColors.RED.code + "Summa ir jaraksta skaitliska forma. Meginiet velreiz." + ConsoleColors.RESET.code);
            return;
        }

        double summa = Double.parseDouble(summa_String);

        if (summa < 0) {
            System.out.println(ConsoleColors.RED.code + "Summa nevar but negativa. Meginiet velreiz." + ConsoleColors.RESET.code);
            return;
        }

        int id = Base.budzetaPlani.isEmpty() ? 1 : Base.budzetaPlani.get(Base.budzetaPlani.size() - 1).getId() + 1;

        BudzetaPlans plans = new BudzetaPlans(id, nosaukums, summa, lietotajs.getID());
        budzetaPlani.add(plans);
        lietotajs.getBudzetasID().add(id);
        save(3);
        Creater creater = new Creater();
        while (true) {
            System.out.println("Vai tu gribi izveidot kategorijas tagad? (j/n)");
            String response = scanner.nextLine().trim();
            if (response.equalsIgnoreCase("j")) {
                creater.newBudzetaKategorijas(lietotajs);
            } else if (response.equalsIgnoreCase("n")) {
                System.out.println("");
                break;
            } else {
                System.out.println(ConsoleColors.RED.code + "Nederiga atbilde. Ludzu, ievadi 'j' vai 'n'." + ConsoleColors.RESET.code);
                continue;
            }
            while (true) {
                System.out.println("Vai tu gribi izveidot vel kategorijas? (j/n)");
                System.out.print("Ievadi: ");
                String more = scanner.nextLine().trim();
                if (more.equalsIgnoreCase("j")) {
                    creater.newBudzetaKategorijas(lietotajs);
                } else if (more.equalsIgnoreCase("n")) {
                    System.out.println(ConsoleColors.GREEN.code + "Budzeta plans izveidots veiksmigi!" + ConsoleColors.RESET.code);
                    break;
                } else {
                    System.out.println(ConsoleColors.RED.code + "Nederiga atbilde. Ludzu, ievadi 'j' vai 'n'." + ConsoleColors.RESET.code);
                    continue;
                }
            }
            break;
        }
    }

    // izveido jaunas kategorijas budzeta planam
    public void newBudzetaKategorijas(Lietotajs lietotajs) {
        while (true) {
            System.out.println("");
            System.out.println("Pie kada budzeta plana pieder kategorija?" + ConsoleColors.YELLOW.code + " Ja veliesies atpakal uz galveno izvelni, ievadi 0." + ConsoleColors.RESET.code);

            budzetaPlaniList(lietotajs);
            if (lietotajs.getBudzetasID().isEmpty()) {
                return;
            }
            System.out.println("");
            System.out.print("Ievadi budzeta plana ID: ");
            String budzeta_plana_id_String = scanner.nextLine().trim();
            
            if (budzeta_plana_id_String.equals("0")) {
                break;
            } else if (budzeta_plana_id_String.isEmpty()) {
                System.out.println(ConsoleColors.RED.code + "Budzeta plana ID nevar but tukss. Meginiet velreiz." + ConsoleColors.RESET.code);
                continue;
            } else if (!regex.checkNumber(budzeta_plana_id_String)) {
                System.out.println(ConsoleColors.RED.code + "Budzeta plana ID ir jaraksta skaitliska forma. Meginiet velreiz." + ConsoleColors.RESET.code);
                continue;
            }
            
            int budzeta_plana_id = Integer.parseInt(budzeta_plana_id_String);
            if (budzeta_plana_id >= 0 && lietotajs.getBudzetasID().contains(budzeta_plana_id)) {
                System.out.print("Ievadi budzeta kategorijas nosaukumu: ");
                String nosaukums = scanner.nextLine().trim();

                if (nosaukums.isEmpty() || !regex.checkName(nosaukums)) {
                    System.out.println(ConsoleColors.RED.code + "Nederigs nosaukums. Megini velreiz." + ConsoleColors.RESET.code);
                    continue;
                }

                System.out.print("Ievadi procentus no budzeta plana: ");
                String procenti_String = scanner.nextLine().trim();
                if (procenti_String.isEmpty()) {
                    System.out.println(ConsoleColors.RED.code + "Procenti nevar but tuksi. Meginiet velreiz." + ConsoleColors.RESET.code);
                    continue;
                } else if (!regex.checkNumber(procenti_String)) {
                    System.out.println(ConsoleColors.RED.code + "Procenti ir jaraksta skaitliska forma. Meginiet velreiz." + ConsoleColors.RESET.code);
                    continue;
                }
                int procenti = Integer.parseInt(procenti_String);

                if ((procenti < 0 || procenti > 100) && summa_procentiem(budzeta_plana_id) <= 100) {
                    System.out.println(ConsoleColors.RED.code + "Procenti ir jaraksta skaitliska forma no 0 lidz 100. Meginiet velreiz." + ConsoleColors.RESET.code);
                    continue;
                } else if (summa_procentiem(budzeta_plana_id) + procenti > 100) {
                    System.out.println(ConsoleColors.RED.code + "Kategoriju procentu summa parsniedz 100%. Meginiet velreiz." + ConsoleColors.RESET.code);
                    continue;
                }
                double summa = procenti * getBudzetaPlansById(budzeta_plana_id).getSumma() / 100;

                double notereta_summa = 0.0;

                int id = Base.budzetaKategorijas.isEmpty() ? 1
                        : Base.budzetaKategorijas.get(Base.budzetaKategorijas.size() - 1).getId() + 1;

                BudzetaKategorijas kategorija = new BudzetaKategorijas(id, nosaukums, procenti, summa, notereta_summa,
                        budzeta_plana_id);

                budzetaKategorijas.add(kategorija);
                BudzetaPlans plans = getBudzetaPlansById(budzeta_plana_id);
                if (plans != null) {
                    plans.getKategorijasID().add(id);
                }
                save(4);
                break;
            } else {
                System.out.println(ConsoleColors.RED.code + "Nederigs budzeta plana ID. Meginiet velreiz." + ConsoleColors.RESET.code);
                continue;
            }
            
        }
        save(4); // saglaba csv failaa
    }
    
}
