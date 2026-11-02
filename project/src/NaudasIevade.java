
public class NaudasIevade extends Base{
    
    // funkcija ievadiBudzetaPlanaNaudu pieņem Lietotajs tipa vērtību lietotajs un ļauj lietotājam attiecīgai budžeta plāna kategorijai pievienot patērēto naudas summu
    public void ievadiBudzetaPlanaNaudu(Lietotajs lietotajs) {
        System.out.println("Kura budzeta plana kategoriju tu velies papildinat? Ievadi budzeta plana ID un kategorijas ID.");
        int[] ids = kategorijasIzvele(scanner, lietotajs);
        if (ids == null) {
            return;
        }

        int kategorijas_id = ids[1];
        BudzetaKategorijas kategorija = getBudzetaKategorijasById(kategorijas_id);
        double maxSum = kategorija.getSumma();
        double currentSum = kategorija.getNotereta_summa();
        double fillPercent = maxSum > 0 ? currentSum / maxSum * 100 : 0;
        System.out.println( ConsoleColors.CYAN.code + "Kategorijas limits: " + ConsoleColors.RESET.code + String.format("%.2f", maxSum) + ", jau izterets: " + String.format("%.2f", currentSum) + " (" + String.format("%.0f", fillPercent) + "%). ");
        if (currentSum >= maxSum) {
            System.out.println(ConsoleColors.YELLOW.code + "Kategorijas limits jau ir pilniba piepildits. Vel pievienot nevar." + ConsoleColors.RESET.code);
            return;
        }

        System.out.print("Ievadi summu, kuru tu velies pievienot: ");
        String summaString = scanner.nextLine().trim();
        if (summaString.isEmpty()) {
            System.out.println(ConsoleColors.RED.code + "Summa nevar but tuksa. Meginiet velreiz." + ConsoleColors.RESET.code);
            return;
        } else if (!regex.checkNumber(summaString)) {
            System.out.println(ConsoleColors.RED.code + "Summa ir jaraksta skaitliska forma. Meginiet velreiz." + ConsoleColors.RESET.code);
            return;
        }
        double summa = Double.parseDouble(summaString);

        
        if (!(summa > 0)) {
            System.out.println(ConsoleColors.RED.code + "Summa nevar but negativa. Meginiet velreiz." + ConsoleColors.RESET.code);
            return;
        }

        double newTotal = summa + kategorija.getNotereta_summa();
        if (newTotal > kategorija.getSumma()) {
            double exceed = newTotal - kategorija.getSumma();
            System.out.println(ConsoleColors.YELLOW.code + "Pievienojot so summu, kategorijas limits tiks parsniegts par " + String.format("%.2f", exceed) + " EUR." + ConsoleColors.RESET.code);
        }

        kategorija.setNotereta_summa(summa);

        if (kategorija.getNotereta_summa()/kategorija.getSumma()*100 > 90) {
            System.out.println(ConsoleColors.YELLOW.code + "Uzmanibu! Tu esi sasniedzis 90% no sava dienas limita." + ConsoleColors.RESET.code);
        } else if (kategorija.getNotereta_summa()/kategorija.getSumma()*100 > 80) {
            System.out.println(ConsoleColors.YELLOW.code + "Bridinajums! Tu esi sasniedzis 80% no sava dienas limita." + ConsoleColors.RESET.code);
        } else if (kategorija.getNotereta_summa()/kategorija.getSumma()*100 > 60) {
            System.out.println(ConsoleColors.YELLOW.code + "Bridinajums! Tu esi sasniedzis 60% no sava dienas limita." + ConsoleColors.RESET.code);
        }

        save(4);
        System.out.println(ConsoleColors.GREEN.code + "Nauda ir veiksmigi pievienota!" + ConsoleColors.RESET.code);
        
    }

    // funkcija ievadiDienasLimitaNauda pieņem Lietotajs tipa vērtību lietotajs un ļauj lietotājam attiecīgajam dienas limitam pievienot patērēto naudas summu 
    public void ievadiDienasLimitaNauda(Lietotajs lietotajs) {
        DienasLimits limits = getDienasLimitsByLietotajaId(lietotajs.getID());
        double currentSum = limits.getIztereta_summa();
        double limit = limits.getLimits();
        double fillPercent = limit > 0 ? currentSum / limit * 100 : 0;
        System.out.println( ConsoleColors.CYAN.code + "Dienas limits: " + ConsoleColors.RESET.code + String.format("%.2f", limit) + ", jau izterets: " + String.format("%.2f", currentSum) + " (" + String.format("%.0f", fillPercent) + "%). ");
        if (limit > 0 && currentSum >= limit) {
            System.out.println(ConsoleColors.YELLOW.code + "Dienas limits jau ir sasniegts vai parsniegts. Vel pievienot nevar." + ConsoleColors.RESET.code);
            return;
        }
        System.out.println("");
        System.out.print("Ievadi summu, kuru tu velies pievienot dienas limitam: ");
        double summa;
        while (true) {
            String summaString = scanner.nextLine().trim();
            if (summaString.isEmpty()) {
                System.out.println(ConsoleColors.RED.code + "Summa nevar but tuksa. Meginiet velreiz." + ConsoleColors.RESET.code);
                continue;
            } else if (!regex.checkNumber(summaString)) {
                System.out.println(ConsoleColors.RED.code + "Summa ir jaraksta skaitliska forma. Meginiet velreiz." + ConsoleColors.RESET.code);
                continue;
            }
            summa = Double.parseDouble(summaString);
            break;
        }

        if (summa < 0) {
            System.out.println(ConsoleColors.RED.code + "Summa nevar but negativa. Meginiet velreiz." + ConsoleColors.RESET.code);
            return;
        }

        double newTotal = summa + limits.getIztereta_summa();
        if (newTotal > limits.getLimits()) {
            double exceed = newTotal - limits.getLimits();
            System.out.println(ConsoleColors.YELLOW.code + "Pievienojot so summu, dienas limits tiks parsniegts par " + String.format("%.2f", exceed) + " EUR." + ConsoleColors.RESET.code);
        }
        
        limits.setIztereta_summa(summa, false);

        if (limits.getIztereta_summa()/limits.getLimits()*100 > 90) {
            System.out.println(ConsoleColors.YELLOW.code + "Uzmanibu! Tu esi sasniedzis 90% no sava dienas limita." + ConsoleColors.RESET.code);
        } else if (limits.getIztereta_summa()/limits.getLimits()*100 > 80) {
            System.out.println(ConsoleColors.YELLOW.code + "Bridinajums! Tu esi sasniedzis 80% no sava dienas limita." + ConsoleColors.RESET.code);
        } else if (limits.getIztereta_summa()/limits.getLimits()*100 > 60) {
            System.out.println(ConsoleColors.YELLOW.code + "Bridinajums! Tu esi sasniedzis 60% no sava dienas limita." + ConsoleColors.RESET.code);
        }

        save(5);
        System.out.println(ConsoleColors.GREEN.code + "Nauda ir veiksmigi pievienota dienas limitam!" + ConsoleColors.RESET.code);
        
    }

    // funkcija ievadiUzkrajumaMerkisNauda pieņem Lietotajs tipa vērtību lietotajs un ļauj lietotājam attiecīgajam uzkrājuma mērķim pievienot papildinājuma naudas summu 
    public void ievadiUzkrajumaMerkisNauda(Lietotajs lietotajs) {
        System.out.println("Kura uzkrajuma merka kategoriju tu velies papildinat? Ievadi merka ID.");
        uzkrajumaMerkasList(lietotajs);
        if (lietotajs.getMerkasID().isEmpty()) {
            return;
        }

        int merkis_id = Integer.parseInt(scanner.nextLine().trim());
        if (!lietotajs.getMerkasID().contains(merkis_id)) {
            System.out.println(ConsoleColors.RED.code + "Nederiga ievade. Meginiet velreiz." + ConsoleColors.RESET.code);
            return;
        }

        UzkrajumaMerkis merkis = getUzkrajumaMerkisById(merkis_id);
        double target = merkis.getMerka_summa();
        double current = merkis.getSakrata_summa();
        double fillPercent = target > 0 ? current / target * 100 : 0;
        System.out.println(ConsoleColors.CYAN.code + "Uzkrajuma merka summa: " + ConsoleColors.RESET.code + String.format("%.2f", target) + ", jau sakrats: " + String.format("%.2f", current) + " (" + String.format("%.0f", fillPercent) + "%). ");

        System.out.print("Ievadi summu, kuru tu velies pievienot:");
        String summaString = scanner.nextLine().trim();
        if (summaString.isEmpty()) {
            System.out.println(ConsoleColors.RED.code + "Summa nevar but tuksa. Meginiet velreiz." + ConsoleColors.RESET.code);
            return;
        } else if (!regex.checkNumber(summaString)) {
            System.out.println(ConsoleColors.RED.code + "Summa ir jāraksta skaitliska forma. Meginiet velreiz." + ConsoleColors.RESET.code);
            return;
        }
        double summa = Double.parseDouble(summaString);


        if (summa < 0) {
            System.out.println(ConsoleColors.RED.code + "Summa nevar but negativa. Meginiet velreiz." + ConsoleColors.RESET.code);
            return;
        }

        if (summa + merkis.getSakrata_summa() > merkis.getMerka_summa()) {
            System.out.println(ConsoleColors.YELLOW.code + "Tu esi sasniedzis savu uzkrajuma merka limitu. Tu gribi pievienot visu summu vai atlikumu atdot atpakaļ? Ievadi 1, lai pievienotu visu summu, vai 2, lai pievienotu tikai atlikumu." + ConsoleColors.RESET.code);
        }

        merkis.setNotereta_summa(summa);

        if (merkis.getSakrata_summa()/merkis.getMerka_summa()*100 > 90) {
            System.out.println(ConsoleColors.YELLOW.code + "Uzmanibu! Tu esi sasniedzis 90% no sava uzkrajuma merka." + ConsoleColors.RESET.code);
        } else if (merkis.getSakrata_summa()/merkis.getMerka_summa()*100 > 80) {
            System.out.println(ConsoleColors.YELLOW.code + "Bridinajums! Tu esi sasniedzis 80% no sava uzkrajuma merka." + ConsoleColors.RESET.code);
        } else if (merkis.getSakrata_summa()/merkis.getMerka_summa()*100 > 60) {
            System.out.println(ConsoleColors.YELLOW.code + "Bridinajums! Tu esi sasniedzis 60% no sava uzkrajuma merka." + ConsoleColors.RESET.code);
        }

        save(6);
        System.out.println(ConsoleColors.GREEN.code + "Nauda ir veiksmigi pievienota!" + ConsoleColors.RESET.code);
        
    }

}
