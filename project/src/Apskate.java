public class Apskate extends Base{


    // Funkcija budzetaPlanaApskate pieņem Lietotajs tipa vertibu un atgriez void tipa vertibu
    public void budzetaPlanaApskate(Lietotajs lietotajs) {
        if (lietotajs.getBudzetasID().isEmpty()) {
            System.out.println(ConsoleColors.YELLOW.code + "Tev nav izveidots neviens budzeta plans." + ConsoleColors.RESET.code);
            return;
        }

        System.out.println("Kadu budzeta planu velies apskatit?");

        budzetaPlaniList(lietotajs);
        if (lietotajs.getBudzetasID().isEmpty()) {
            return;
        }

        System.out.print("Ievadi:");
        String id_String = scanner.nextLine().trim();
        if (id_String.isEmpty()) {
            System.out.println(ConsoleColors.RED.code + "ID nevar but tukss. Meginiet velreiz." + ConsoleColors.RESET.code);
            return;
        } else if (!regex.checkNumber(id_String)) {
            System.out.println(ConsoleColors.RED.code + "ID ir jaraksta skaitliska forma. Meginiet velreiz." + ConsoleColors.RESET.code);
            return;
        }
        int id = Integer.parseInt(id_String);
        
        BudzetaPlans plans = null;
        if (lietotajs.getBudzetasID().contains(id)) {
            plans = getBudzetaPlansById(id);
            System.out.println(ConsoleColors.CYAN.code +plans);
        } else {
            System.out.println(ConsoleColors.RED.code + "Nederigs budzeta plana ID." + ConsoleColors.RESET.code);
            return;
        }

        System.out.println("Budzeta plana summa: " + ConsoleColors.YELLOW.code + plans.getSumma() + ConsoleColors.RESET.code);
        System.out.println(ConsoleColors.CYAN.code + "Budzeta plana nosaukums: " +  ConsoleColors.YELLOW.code + plans.getPlana_nosaukums() + ConsoleColors.RESET.code);
        System.out.println(ConsoleColors.CYAN.code + "Kategorijas:");
        System.out.println("-".repeat(60) + ConsoleColors.YELLOW.code);

        for (BudzetaKategorijas kategorijas: budzetaKategorijas) {
            if (kategorijas.getBudzeta_plana_id() == plans.getId()) {
                System.out.println("- " + kategorijas);
            }
        }
        System.out.println(ConsoleColors.RESET.code);
    }

    // Funkcija dienasLimitaApskate pieņem Lietotajs tipa vertibu un atgriez void tipa vertibu
    public void dienasLimitaApskate(Lietotajs lietotajs) {
        DienasLimits limits = getDienasLimitsByLietotajaId(lietotajs.getID());
        if (limits != null) {
            System.out.println(ConsoleColors.CYAN.code + "Dienas limita summa: " + ConsoleColors.YELLOW.code + limits.getLimits() + ConsoleColors.RESET.code);
            System.out.println(ConsoleColors.CYAN.code + "Dienas limita iztereta summa: " + ConsoleColors.YELLOW.code + limits.getIztereta_summa() + ConsoleColors.RESET.code);
        } else {
            System.out.println(ConsoleColors.YELLOW.code + "Dienas limits nav atrasts." + ConsoleColors.RESET.code);
        }
    }

    // Funkcija uzkrajumaMerkasApskate pieņem Lietotajs tipa vertibu un atgriez void tipa vertibu
    public void uzkrajumaMerkasApskate(Lietotajs lietotajs) {
        if (lietotajs.getMerkasID().isEmpty()) {
            System.out.println(ConsoleColors.YELLOW.code + "Tev nav izveidots neviens uzkrajuma merkis." + ConsoleColors.RESET.code);
            return;
        }

        System.out.println(ConsoleColors.CYAN.code + "-".repeat(22));
        System.out.println("|Tavi uzkrajuma merki|");
        System.out.println("-".repeat(60));
        for (UzkrajumaMerkis merki : uzkrajumaMerkis) {
            if (merki.getLietotaja_id() == lietotajs.getID()) {
                System.out.println("- " + merki);
            }
        }
        System.out.println(ConsoleColors.RESET.code);
    }

}
