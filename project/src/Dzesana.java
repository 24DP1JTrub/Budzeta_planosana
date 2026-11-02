
public class Dzesana extends Base{
    
    // funkcija dzestUzkrajumaMerkis pieņem Lietotajs tipa vērtību lietotajs un, pārbaudot vai lietotājs tiešām vēlās dzēst mērķi, izdzēš izvēlēto uzkrājuma mērķi
    public void dzestUzkrajumaMerkis(Lietotajs lietotajs) {
        System.out.println("Kuru uzkrajuma merki tu velies dzest? Ievadi merka ID:");

        uzkrajumaMerkasList(lietotajs);
        if (lietotajs.getMerkasID().isEmpty()) {
            return;
        }
        System.out.print("ID ievade:");
        String id_String = scanner.nextLine().trim();
        if (id_String.isEmpty()) {
            System.out.println(ConsoleColors.RED.code + "ID nevar but tukss. Meginiet velreiz." + ConsoleColors.RESET.code);
            return;
        } else if (!regex.checkNumber(id_String)) {
            System.out.println(ConsoleColors.RED.code + "ID ir jaraksta skaitliska forma. Meginiet velreiz." + ConsoleColors.RESET.code);
            return;
        }

        int id = Integer.parseInt(id_String);
        if (id < 0 || !lietotajs.getMerkasID().contains(id)) {
            System.out.println(ConsoleColors.RED.code + "Nederigs merka ID. Meginiet velreiz." + ConsoleColors.RESET.code);
            return;
        }
        
        System.out.println(ConsoleColors.YELLOW.code + "Vai tu esi parliecinats, ka velies dzest so merki? (j/n)" + ConsoleColors.RESET.code);
        System.out.print("Ievadi:");
        String confirmation = scanner.nextLine().trim().toLowerCase();
        if (confirmation.equals("j")) {
            int index = getMerkisIdById(id);
            if (index < 0) {
                System.out.println(ConsoleColors.RED.code + "Nevar atrast merka indeksu. Dzesana atcelta." + ConsoleColors.RESET.code);
                return;
            }
            uzkrajumaMerkis.remove(index);
            lietotajs.getMerkasID().remove(Integer.valueOf(id));
            save(2);
            System.out.println("Merkis ar ID " + id + " ir dzests.");
        } else if (confirmation.equals("n")) {
            System.out.println(ConsoleColors.YELLOW.code + "Dzesana atcelta." + ConsoleColors.RESET.code);
        } else {
            System.out.println(ConsoleColors.RED.code + "Nederiga ievade. Dzesana atcelta." + ConsoleColors.RESET.code);
        }
    }

    // funkcija dzestBudzetaPlanu pieņem Lietotajs tipa vērtību lietotajs un, pārbaudot vai lietotājs tiešām vēlās dzēst plānu, izdzēš izvēlēto budžeta plānu
    public void dzestBudzetaPlanu(Lietotajs lietotajs) {
        System.out.println("Kuru budzeta planu tu velies dzest? Ievadi budzeta plana ID:");
        budzetaPlaniList(lietotajs);
        if (lietotajs.getBudzetasID().isEmpty()) {
            return;
        }
        System.out.print("ID ievade:");
        String id_String = scanner.nextLine().trim();
        if (id_String.isEmpty()) {
            System.out.println(ConsoleColors.RED.code + "ID nevar but tukss. Meginiet velreiz." + ConsoleColors.RESET.code);
            return;
        } else if (!regex.checkNumber(id_String)) {
            System.out.println(ConsoleColors.RED.code + "ID ir jaraksta skaitliska forma. Meginiet velreiz." + ConsoleColors.RESET.code);
            return;
        }
        int id = Integer.parseInt(id_String);

        if (id < 0 || !lietotajs.getBudzetasID().contains(id)) {
            System.out.println(ConsoleColors.RED.code + "Nederigs budzeta plana ID. Meginiet velreiz." + ConsoleColors.RESET.code);
            return;
        }
        
        System.out.println(ConsoleColors.YELLOW.code + "Vai tu esi parliecinats, ka velies dzest so budzeta planu? (j/n)" + ConsoleColors.RESET.code);
        System.out.print("Ievadi:");
        String confirmation = scanner.nextLine().trim().toLowerCase();
        if (confirmation.equals("j")) {
            int index = getBudzetaPlanIdById(id);
            if (index < 0) {
                System.out.println(ConsoleColors.RED.code + "Nevar atrast budzeta plana indeksu. Dzesana atcelta." + ConsoleColors.RESET.code);
                return;
            }
            budzetaPlani.remove(index);
            lietotajs.getBudzetasID().remove(Integer.valueOf(id));
            save(3);
            System.out.println(ConsoleColors.GREEN.code + "Budzeta plans ar ID " + id + " ir dzests." + ConsoleColors.RESET.code);
        } else {
            System.out.println(ConsoleColors.YELLOW.code + "Dzesana atcelta." + ConsoleColors.RESET.code);
        }
    }

    // funkcija dzestBudzetaPlanu pieņem Lietotajs tipa vērtību lietotajs un, pārbaudot vai lietotājs tiešām vēlās dzēst plāna kategoriju, izdzēš izvēlēto budžeta plāna kategoriju
    public void dzestBudzetaKategorijas(Lietotajs lietotajs) {
        System.out.println("Ievadi budzeta plana ID un kategorijas ID, kuru tu velies dzest.");

        int[] ids = kategorijasIzvele(scanner, lietotajs);
        if (ids == null) {
            return;
        }
        
        int budzeta_plana_id = ids[0];
        int kategorijas_id = ids[1];
    
        System.out.println(ConsoleColors.YELLOW.code + "Vai tu esi parliecinats, ka velies dzest so kategoriju? (j/n)" + ConsoleColors.RESET.code);
        System.out.print("Ievadi:");
        String confirmation = scanner.nextLine().trim().toLowerCase();
        if (confirmation.equals("j")) {
            int index = getKategorijasIdById(kategorijas_id);
            if (index < 0) {
                System.out.println(ConsoleColors.RED.code + "Nevar atrast kategorijas indeksu. Dzesana atcelta." + ConsoleColors.RESET.code);
                return;
            }
            budzetaKategorijas.remove(index);
            getBudzetaPlansById(budzeta_plana_id).getKategorijasID().remove(Integer.valueOf(kategorijas_id));
            lietotajs.getMerkasID().remove(Integer.valueOf(kategorijas_id));
            save(4);
            System.out.println(ConsoleColors.GREEN.code + "Kategorija ar ID " + kategorijas_id + " ir dzesta." + ConsoleColors.RESET.code);
        } else {
            System.out.println(ConsoleColors.YELLOW.code + "Dzesana atcelta." + ConsoleColors.RESET.code);
        }
    }

}
