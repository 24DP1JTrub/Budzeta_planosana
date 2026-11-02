public class Changer extends Base{
    // funkcija changeBudzetaPlan pieņem Lietotajs tipa vērtību lietotajs un lietotājs maina esošu budžeta plānu
    public void changeBudzetaPlan(Lietotajs lietotajs) {
        while (true) {
            System.out.println("Kuru budzeta planu velies mainit?"+ ConsoleColors.YELLOW.code + " Ja veliesies atpakal uz galveno izvelni, ievadi 0." + ConsoleColors.RESET.code);

            budzetaPlaniList(lietotajs);
            if (lietotajs.getBudzetasID().isEmpty()) {
                return;
            }

            System.out.print("Ievadi budzeta plana ID: ");
            String budzeta_plana_id_String = scanner.nextLine().trim();

            if (budzeta_plana_id_String.equals("0")) {
                break;
            }

            if (!regex.checkNumber(budzeta_plana_id_String)) {
                System.out.println(ConsoleColors.RED.code + "ID ir jaraksta skaitliska forma. Meginiet velreiz." + ConsoleColors.RESET.code);
                continue;
            }

            int budzeta_plana_id = Integer.parseInt(budzeta_plana_id_String);
            

            if (budzeta_plana_id >= 0 && lietotajs.getBudzetasID().contains(budzeta_plana_id)) {
                System.out.print("Ievadi jauno budzeta plana nosaukumu:");
                String nosaukums = scanner.nextLine().trim();
                if (nosaukums.isEmpty() || !regex.checkName(nosaukums)) {
                    System.out.println(ConsoleColors.RED.code + "Nederigs nosaukums. Megini velreiz." + ConsoleColors.RESET.code);
                    continue;
                }
                System.out.print("Ievadi jauno budzeta plana summu:");
                String summaString = scanner.nextLine().trim();
                
                double summa;
                if (Double.parseDouble(summaString) <= 0) {
                    System.out.println(ConsoleColors.RED.code + "Summa nevar but negativa. Meginiet velreiz." + ConsoleColors.RESET.code);
                    continue;
                } else if (!regex.checkNumber(summaString)) {
                    System.out.println(ConsoleColors.RED.code + "Summa ir jaraksta skaitliska forma. Meginiet velreiz." + ConsoleColors.RESET.code);
                    continue;
                } else {
                    summa = Double.parseDouble(summaString);
                }

                BudzetaPlans plans = getBudzetaPlansById(budzeta_plana_id);
                plans.setNosaukums(nosaukums);
                plans.setSumma(summa);
                save(3);
                System.out.println(ConsoleColors.GREEN.code + "Budzeta plana informacija veiksmigi mainita!" + ConsoleColors.RESET.code);
            } else {
                System.out.println(ConsoleColors.RED.code + "Nepareizs ID. MMeginiet velreiz." + ConsoleColors.RESET.code);
            }
        }
    }

    // funkcija changeBudzetaKategorijas pieņem Lietotajs tipa vērtību lietotajs un lietotājs maina esošu budžeta plāna kategoriju
    public void changeBudzetaKategorijas(Lietotajs lietotajs) {
        while (true) {
            System.out.println("No kura budzeta plana kategorija pieder?"+ ConsoleColors.YELLOW.code + " Ja veliesies atpakal uz galveno izvelni, ievadi 0." + ConsoleColors.RESET.code);

            budzetaPlaniList(lietotajs);
            if (lietotajs.getBudzetasID().isEmpty()) {
                return;
            }

            System.out.print("Ievadi budzeta plana ID: ");
            String budzeta_plana_id_String = scanner.nextLine().trim();
            if (budzeta_plana_id_String.equals("0")) {
                break;
            }

            if (!regex.checkNumber(budzeta_plana_id_String)) {
                System.out.println(ConsoleColors.RED.code + "ID ir jaraksta skaitliska forma. Meginiet velreiz." + ConsoleColors.RESET.code);
                continue;
            }

            int budzeta_plana_id = Integer.parseInt(budzeta_plana_id_String);

            if (budzeta_plana_id >= 0 && lietotajs.getBudzetasID().contains(budzeta_plana_id)) {
                System.out.println("Kuras kategorijas informaciju velies mainit? Ievadi kategorijas ID:");  
                budzetaKategorijasList(getBudzetaPlansById(budzeta_plana_id));
                if (getBudzetaPlansById(budzeta_plana_id).getKategorijasID().isEmpty()) {
                    System.out.println(ConsoleColors.YELLOW.code + "Sim budzeta planam nav kategoriju. Meginiet velreiz." + ConsoleColors.RESET.code);
                    return;
                }
                System.out.print("Ievadi kategorijas ID: ");
                String kategorijas_id_String = scanner.nextLine().trim();
                if (!regex.checkNumber(kategorijas_id_String)) {
                    System.out.println(ConsoleColors.RED.code + "ID ir jaraksta skaitliska forma. Meginiet velreiz." + ConsoleColors.RESET.code);
                    continue;
                }
                int kategorijas_id = Integer.parseInt(kategorijas_id_String);
                if (kategorijas_id >= 0 && getBudzetaPlansById(budzeta_plana_id).getKategorijasID().contains(kategorijas_id)) {

                    System.out.print("Ievadi jauno kategorijas nosaukumu:");
                    String nosaukums = scanner.nextLine().trim();

                    if (nosaukums.isEmpty() || !regex.checkName(nosaukums)) {
                        System.out.println(ConsoleColors.RED.code + "Nederigs nosaukums. Megini velreiz." + ConsoleColors.RESET.code);
                        continue;
                    }

                    System.out.println("Ievadi jaunos procentus no budzeta plana:");
                    String procenti_String = scanner.nextLine().trim();
                    if (!regex.checkNumber(procenti_String)) {
                        System.out.println(ConsoleColors.RED.code + "Procenti ir jaraksta skaitliska forma. Meginiet velreiz." + ConsoleColors.RESET.code);
                        continue;
                    }
                    int procenti = Integer.parseInt(procenti_String);

                    if ((procenti < 0 || procenti > 100)) {
                        System.out.println(ConsoleColors.RED.code + "Procenti ir jaraksta skaitliska forma no 0 lidz 100. Meginiet velreiz." + ConsoleColors.RESET.code);
                        continue;
                    } else if (summa_procentiem(budzeta_plana_id) - getBudzetaKategorijasById(kategorijas_id).getProcenti() + procenti > 100) {
                        System.out.println(ConsoleColors.RED.code + "Kategoriju procentu summa parsniedz 100%. Meginiet velreiz." + ConsoleColors.RESET.code);
                        continue;
                    } 

                    double summa = procenti * getBudzetaPlansById(budzeta_plana_id).getSumma() / 100;

                    System.out.println("Tu gribi atstat notereto summu? Ja ne, ievadi 0, ja gribi mainit, ievadi jauno notereto summu:");
                    System.out.print("Ievadi:");
                    String notereta_summa_input_String = scanner.nextLine().trim();
                    if (!regex.checkNumber(notereta_summa_input_String)) {
                        System.out.println(ConsoleColors.RED.code + "Notereta summa ir jaraksta skaitliska forma. Meginiet velreiz." + ConsoleColors.RESET.code);
                        continue;
                    }
                    double notereta_summa_input = Double.parseDouble(notereta_summa_input_String);
                    if (!(0 <= notereta_summa_input && notereta_summa_input <= summa)) {
                        System.out.println(ConsoleColors.RED.code + "Notereta summa ir jaraksta skaitliska forma no 0 lidz kategorijas summai. Meginiet velreiz." + ConsoleColors.RESET.code);
                        continue;
                    }

                    getBudzetaKategorijasById(kategorijas_id).setAll(nosaukums, procenti, summa, notereta_summa_input);

                } else {
                    System.out.println(ConsoleColors.RED.code + "Nepareizs kategorijas ID. Meginiet velreiz." + ConsoleColors.RESET.code);
                }
            }
        }
    }

    // funkcija changeDienasLimits pieņem Lietotajs tipa vērtību lietotajs un lietotājs maina esošu dienas limitu
    public void changeDienasLimits(Lietotajs lietotajs) {
        System.out.print("Ievadi jauno dienas limitu:");
        String dienas_limits_String = scanner.nextLine().trim();
        if (!regex.checkNumber(dienas_limits_String)) {
            System.out.println(ConsoleColors.RED.code + "Dienas limits ir jaraksta skaitliska forma. Meginiet velreiz." + ConsoleColors.RESET.code);
            return;
        }
        double dienas_limits = Double.parseDouble(dienas_limits_String);
        if (dienas_limits < 0) {
            System.out.println(ConsoleColors.RED.code + "Dienas limits ir jaraksta skaitliska forma no 0 lidz bezgalibai. Meginiet velreiz." + ConsoleColors.RESET.code);
        } else {
            getDienasLimitsByLietotajaId(lietotajs.getID()).setLimits(dienas_limits);
            if (getDienasLimitsByLietotajaId(lietotajs.getID()).getIztereta_summa() > dienas_limits) {
                System.out.println(ConsoleColors.RED.code + "Uzmanibu! Jusu iztereta summa parsniedz jauno dienas limitu!" + ConsoleColors.RESET.code);
                System.out.println(ConsoleColors.RED.code + "Ietereta summa: " + getDienasLimitsByLietotajaId(lietotajs.getID()).getIztereta_summa() + ", Dienas limits: " + dienas_limits + ConsoleColors.RESET.code);
                System.out.print(ConsoleColors.RED.code + "Ivadiet jauno iztereto summu, lai atjauninatu iztereto summu atbilstoši jaunajam dienas limitam: " + ConsoleColors.RESET.code);
                String jauna_iztereta_summa_String = scanner.nextLine().trim();
                if (!regex.checkNumber(jauna_iztereta_summa_String)) {
                    System.out.println(ConsoleColors.RED.code + "Jauna iztereta summa ir jaraksta skaitliska forma. Meginiet velreiz." + ConsoleColors.RESET.code);
                    return;
                }
                double jauna_iztereta_summa = Double.parseDouble(jauna_iztereta_summa_String);
                getDienasLimitsByLietotajaId(lietotajs.getID()).setIztereta_summa(jauna_iztereta_summa, true);
            }
            save(1);
            System.out.println(ConsoleColors.GREEN.code + "Dienas limits veiksmigi mainits!" + ConsoleColors.RESET.code);
        }
    }

    // funkcija changeUzkrajumaMerkis pieņem Lietotajs tipa vērtību lietotajs un lietotājs maina esošu uzkrājuma mērķi
    public void changeUzkrajumaMerkis(Lietotajs lietotajs) {
        System.out.println("Kura uzkrajuma merka kategoriju tu velies mainit? Ievadi merka ID:");
        uzkrajumaMerkasList(lietotajs);
        if (lietotajs.getMerkasID().isEmpty()) {
            return;
        }

        System.out.print("Ievadi ID: ");
        String merkis_id_String = scanner.nextLine().trim();
        if (merkis_id_String.isEmpty()) {
            System.out.println(ConsoleColors.RED.code + "ID nevar but tukss. Meginiet velreiz." + ConsoleColors.RESET.code);
            return;
        }
        if (!regex.checkNumber(merkis_id_String)) {
            System.out.println(ConsoleColors.RED.code + "ID ir jaraksta skaitliska forma. Meginiet velreiz." + ConsoleColors.RESET.code);
            return;
        }
        int merkis_id = Integer.parseInt(merkis_id_String);
        if (!lietotajs.getMerkasID().contains(merkis_id)) {
            System.out.println(ConsoleColors.RED.code + "Nederiga ievade. Meginiet velreiz." + ConsoleColors.RESET.code);
            return;
        }

        System.out.print("Ievadi jauno kategorijas nosaukumu: ");
        String nosaukums = scanner.nextLine().trim();

        if (nosaukums.isEmpty() || !regex.checkName(nosaukums)) {
            System.out.println(ConsoleColors.RED.code + "Nederigs nosaukums. Megini velreiz." + ConsoleColors.RESET.code);
            return;
        }

        System.out.print("Ievadi jauno summu, kuru tu velies uzkrat:");
        String summa_String = scanner.nextLine().trim();
        if (!regex.checkNumber(summa_String)) {
            System.out.println(ConsoleColors.RED.code + "Summa ir jaraksta skaitliska forma. Meginiet velreiz." + ConsoleColors.RESET.code);
            return;
        }
        double summa = Double.parseDouble(summa_String);
        if (summa < 0) {
            System.out.println(ConsoleColors.RED.code + "Summa ir jaraksta skaitliska forma no 0 lidz bezgalibai. Meginiet velreiz." + ConsoleColors.RESET.code);
            return;
        }

        System.out.print("Tu gribi atstat jau sakrato summu? Ja ne, ievadi 0, ja gribi mainit, ievadi jauno sakrato summu (sakrata summa bija:"+ getUzkrajumaMerkisById(merkis_id).getSakrata_summa() +") :");
        String sakrata_summa_input_String = scanner.nextLine().trim();
        if (!regex.checkNumber(sakrata_summa_input_String)) {
            System.out.println(ConsoleColors.RED.code + "Sakrata summa ir jaraksta skaitliska forma. Meginiet veglreiz." + ConsoleColors.RESET.code);
            return;
        }
        double sakrata_summa_input = Double.parseDouble(sakrata_summa_input_String);
        if (sakrata_summa_input < 0 || sakrata_summa_input > summa) {
            System.out.println(ConsoleColors.RED.code + "Sakrata summa ir jaraksta skaitliska forma no 0 lidz uzkrajuma merka summai. Meginiet velreiz." + ConsoleColors.RESET.code);
            return;
        }

        getUzkrajumaMerkisById(merkis_id).setAll(nosaukums, summa, sakrata_summa_input);
        save(6);
        System.out.println(ConsoleColors.GREEN.code + "Uzkrajuma merka informacija veiksmigi mainita!" + ConsoleColors.RESET.code);
    }
}
