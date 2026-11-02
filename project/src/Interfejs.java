import java.util.Scanner;

public class Interfejs {
    public static void main(String[] args) { 
        //programmas sākums
        Scanner scanner = new Scanner(System.in);
        menu(scanner);
        
    }
    
    // funkcija menu pieņem Scanner tipa vērtību scanner un uzsāk programmas darbību
    public static void menu(Scanner scanner) {
        Base base = new Base();
        base.loadAll();
        Lietotajs lietotajs = null;
        Creater creater = new Creater();
        Dzesana dzesana = new Dzesana();
        Apskate apskate = new Apskate();
        Changer changer = new Changer();
        NaudasIevade naudasIevade = new NaudasIevade();
        Regex regex = new Regex();
        String line = "+" + "-".repeat(3) + "+" + "-".repeat(14) + "+";
        String line2 = "+" + "-".repeat(3) + "+" + "-".repeat(27) + "+";

        System.out.println("Labdien, sis ir budzeta planosanas riks");

        while (true) {
            System.out.println("");
            System.out.println("Ko tu gribi darit?");
            System.out.println(line);
            System.out.printf("|%-3s|%-14s|%n", "1.", "Autorizeties");
            System.out.println(line);
            System.out.printf("|%-3s|%-14s|%n", "2.", "Registreties");
            System.out.println(line);
            System.out.print("Ievadi numuru: ");
            String command = scanner.nextLine().trim();
            System.out.println("");

            if (command.equals("1")) {  // Autorizācija
                while (true) {
                    System.out.println("Autorizacija." + ConsoleColors.YELLOW.code + " Ja veliesies atpakal uz galveno izvelni, ievadi 0." + ConsoleColors.RESET.code);
                    System.out.print("Ievadi e-pastu: ");
                    String email = scanner.nextLine().trim();

                    if (email.equals("0")) {
                        break;
                    }

                    System.out.print("Ievadi paroli: ");
                    String password = scanner.nextLine().trim();

                    lietotajs = base.autorizacija(email, password);
                    if (lietotajs != null) {
                        lietotajs.collectIds();
                        break;
                    } else {
                        System.out.println(ConsoleColors.RED.code + "Autorizacija neizdevas. Megini velreiz." + ConsoleColors.RESET.code);
                    }
                }
                System.out.println("");
                break;
            } else if (command.equals("2")) {   // Reģistrācija
                while (true) {
                    System.out.println("Registracija." + ConsoleColors.YELLOW.code + " Ja gribi atgriezties uz galveno izvelni, ievadi 0." + ConsoleColors.RESET.code);

                    System.out.print("Ievadi e-pastu: ");
                    String email = scanner.nextLine().trim();

                    if (email.equals("0")) {
                        break;
                    }
                    
                    if (base.emailExists(email)) {
                        System.out.println(ConsoleColors.YELLOW.code + "E-pasts jau ir registrets. Megini citu e-pastu." + ConsoleColors.RESET.code);
                        continue;
                    }

                    if (!regex.checkEmail(email)) {
                        System.out.println(ConsoleColors.RED.code + "Nederigs e-pasta formats. Megini velreiz." + ConsoleColors.RESET.code);
                        continue;
                    }

                    System.out.print("Ievadi paroli: ");
                    String password = scanner.nextLine().trim();

                    if (!regex.checkPassword(password)) {
                        System.out.println(ConsoleColors.RED.code + "Nederigs paroles formats. Parolei jabut vismaz 9 simbolus garai, lidz 20 simbolus garai. Megini velreiz." + ConsoleColors.RESET.code);
                        continue;
                    }

                    System.out.print("Ievadi savu vardu: ");
                    String name = scanner.nextLine().trim();

                    if (!regex.checkName(name)) {
                        System.out.println(ConsoleColors.RED.code + "Nederigs varda formats. Vardam jabut no 1 līdz 50 burtiem. Megini velreiz." + ConsoleColors.RESET.code);
                        continue;
                    }

                    lietotajs = creater.registracija(email, password, name);
                    creater.newDienasLimits(lietotajs);
                    lietotajs.collectIds();
                    System.out.println(ConsoleColors.GREEN.code + "Registracija veiksmiga!" + ConsoleColors.RESET.code);
                    System.out.println(ConsoleColors.GREEN.code + "Labdien, " + name + "!" + ConsoleColors.RESET.code);
                    System.out.println("");
                    break;
                }
                
                break;
            } else {
                System.out.println(ConsoleColors.RED.code + "Nederiga komanda. Ludzu, izvelies 1 vai 2." + ConsoleColors.RESET.code);
            }
        }

        while (true) {
            System.out.println("Izvelies vienu no variantiem un ievadi to:");
            System.out.println(line);
            System.out.printf("|%-3s|%-14s|%n","1.", "Apskatit");
            System.out.println(line);
            System.out.printf("|%-3s|%-14s|%n","2.", "Rediget");
            System.out.println(line);
            System.out.printf("|%-3s|%-14s|%n","3.", "Dzest");
            System.out.println(line);
            System.out.printf("|%-3s|%-14s|%n","4.", "Izveidot jauno");
            System.out.println(line);
            System.out.printf("|%-3s|%-14s|%n","5.", "Ievadit naudu");
            System.out.println(line);
            System.out.printf("|%-3s|%-14s|%n","6.", "Iziet");
            System.out.println(line);
            System.out.print("Ievadi numuru: ");
            String subCommand = scanner.nextLine().trim();

            if (subCommand.equals("1")) {   // Apskatit
                while (true) {
                    System.out.println("");
                    System.out.println("Ko tu gribi apskatit?");
                    System.out.println(line2);
                    System.out.printf("|%-3s|%-27s|%n","1.", "Budzeta plans");
                    System.out.println(line2);
                    System.out.printf("|%-3s|%-27s|%n","2.", "Merkis");
                    System.out.println(line2);
                    System.out.printf("|%-3s|%-27s|%n","3.", "Dienas limits");
                    System.out.println(line2);
                    System.out.printf("|%-3s|%-27s|%n","4.", "Atpakal uz galveno izvelni");
                    System.out.println(line2);
                    System.out.print("Ievadi numuru: ");
                    String item = scanner.nextLine().trim();
                    
                    if (item.equals("1")) { // Apskatit budzeta plānu
                        apskate.budzetaPlanaApskate(lietotajs);
                        System.out.println("");
                    } else if (item.equals("2")) {  // Apskatit mērķus
                        apskate.uzkrajumaMerkasApskate(lietotajs);
                        System.out.println("");
                    } else if (item.equals("3")) {  // Apskatit dienas limitus
                        apskate.dienasLimitaApskate(lietotajs);
                        System.out.println("");
                    } else if (item.equals("4")) {  // Atpakaļ uz galveno izvēlni
                        System.out.println("");
                        break;
                    } else {
                        System.out.println(ConsoleColors.RED.code + "Nederiga izvele" + ConsoleColors.RESET.code);
                    }
                }
                continue;
            } else if (subCommand.equals("2")) {    // Rediģēt
                while (true) {
                    System.out.println("");
                    System.out.println("Ko tu gribi rediget?");
                    System.out.println(line2);
                    System.out.printf("|%-3s|%-27s|%n","1.", "Budzeta plans");
                    System.out.println(line2);
                    System.out.printf("|%-3s|%-27s|%n","2.", "Merkis");
                    System.out.println(line2);
                    System.out.printf("|%-3s|%-27s|%n","3.", "Dienas limits");
                    System.out.println(line2);
                    System.out.printf("|%-3s|%-27s|%n","4.", "Atpakal uz galveno izvelni");
                    System.out.println(line2);
                    System.out.print("Ievadi numuru: ");
                    String item = scanner.nextLine().trim();

                    if (item.equals("1")) { // Rediģēt budžeta plānu
                        while (true) {
                            System.out.println("");
                            System.out.println("Ko tu gribi rediget budzeta plana?");
                            System.out.println(line2);
                            System.out.printf("|%-3s|%-27s|%n","1.", "Izmainit esoso kategoriju");
                            System.out.println(line2);
                            System.out.printf("|%-3s|%-27s|%n","2.", "Izveidot jaunu kategoriju");
                            System.out.println(line2);
                            System.out.printf("|%-3s|%-27s|%n","3.", "Izmainit pasu budzeta planu");
                            System.out.println(line2);
                            System.out.printf("|%-3s|%-27s|%n","4.", "Atpakal uz galveno izvelni");
                            System.out.println(line2);
                            System.out.print("Ievadi numuru: ");
                            String action = scanner.nextLine().trim();

                            if (action.equals("1")) {   // Rediģēt esošo kategoriju
                                changer.changeBudzetaKategorijas(lietotajs);
                                System.out.println("");
                            } else if (action.equals("2")) { // Izveidot jaunu kategoriju
                                creater.newBudzetaKategorijas(lietotajs);
                                System.out.println("");
                                while (true) {
                                    if (lietotajs.getBudzetasID().isEmpty()) {
                                        break;
                                    }
                                    System.out.println("Tu gribi izveidot vel kategorijas? (j/n)");
                                    System.out.println("Ievadi: ");
                                    String response = scanner.nextLine().trim();
                                    if (response.equalsIgnoreCase("n")) {
                                        System.out.println("");
                                        break;
                                    } else if (response.equalsIgnoreCase("j")) {
                                        creater.newBudzetaKategorijas(lietotajs);
                                    } else {
                                        System.out.println(ConsoleColors.RED.code + "Nederiga atbilde. Ludzu, ievadi 'j' vai 'n'." + ConsoleColors.RESET.code);
                                    }
                                }
                                break; 
                            } else if (action.equals("3")) {    // Rediģēt pašu budžeta plānu
                                changer.changeBudzetaPlan(lietotajs);
                                System.out.println("");
                            } else if (action.equals("4")) {    // Atpakaļ uz galveno izvēlni
                                System.out.println("");
                                break;
                            } else {
                                System.out.println(ConsoleColors.RED.code + "Nederiga komanda" + ConsoleColors.RESET.code);
                            }
                        }
                        continue;
                    } else if (item.equals("2")) {  // Rediģēt mērķus
                        changer.changeUzkrajumaMerkis(lietotajs);
                        System.out.println("");
                    } else if (item.equals("3")) {  // Rediģēt dienas limitus
                        changer.changeDienasLimits(lietotajs);
                        System.out.println("");
                    } else if (item.equals("4")) { // Atpakaļ uz galveno izvēlni
                        System.out.println("");
                        break;
                    } else {
                        System.out.println(ConsoleColors.RED.code + "Nederiga izvele" + ConsoleColors.RESET.code);
                    }
                }
                continue;
            } else if (subCommand.equals("3")) {    // Dzēst
                while (true) {
                    System.out.println("");
                    System.out.println("Ko tu gribi dzest?");
                    System.out.println(line2);
                    System.out.printf("|%-3s|%-27s|%n","1.", "Budzeta plans");
                    System.out.println(line2);
                    System.out.printf("|%-3s|%-27s|%n","2.", "Merkis");
                    System.out.println(line2);
                    System.out.printf("|%-3s|%-27s|%n","3.", "Kategorija");
                    System.out.println(line2);
                    System.out.printf("|%-3s|%-27s|%n","4.", "Atpakal uz galveno izvelni");
                    System.out.println(line2);
                    System.out.print("Ievadi numuru: ");
                    String item = scanner.nextLine().trim();

                    if (item.equals("1")) {     // Dzēst budžeta plānu
                        dzesana.dzestBudzetaPlanu(lietotajs);
                        System.out.println("");
                    } else if (item.equals("2")) {  // Dzēst mērķus
                        dzesana.dzestUzkrajumaMerkis(lietotajs);
                        System.out.println("");
                    } else if (item.equals("3")) { // Dzēst kategoriju
                        dzesana.dzestBudzetaKategorijas(lietotajs);
                        System.out.println("");
                    } else if (item.equals("4")) {  // Atpakaļ uz galveno izvēlni
                        System.out.println("");
                        break;
                    } else {
                        System.out.println(ConsoleColors.RED.code + "Nederiga komanda" + ConsoleColors.RESET.code);
                    }
                }
                continue;
            } else if (subCommand.equals("4")) {    // Izveidot jaunu
                while (true) {
                    System.out.println("");
                    System.out.println("Ko tu gribi izveidot?");
                    System.out.println(line2);
                    System.out.printf("|%-3s|%-27s|%n","1.", "Budzeta plans");
                    System.out.println(line2);
                    System.out.printf("|%-3s|%-27s|%n","2.", "Merkis");
                    System.out.println(line2);
                    System.out.printf("|%-3s|%-27s|%n","3.", "Atpakal uz galveno izvelni");
                    System.out.println(line2);
                    System.out.print("Ievadi numuru: ");
                    String item = scanner.nextLine().trim();

                    if (item.equals("1")) { // Izveidot jaunu budžeta plānu
                        System.out.println("");
                        creater.newBudzetaPlans(lietotajs);
                        System.out.println("");
                    } else if (item.equals("2")) {  // Izveidot jaunu mērķi
                        System.out.println("");
                        creater.newUzkrajumaMerkis(lietotajs);
                        System.out.println("");
                    } else if (item.equals("3")) {  // Atpakaļ uz galveno izvēlni
                        System.out.println("");
                        break;
                    } else {
                        System.out.println(ConsoleColors.RED.code + "Nederiga komanda" + ConsoleColors.RESET.code);
                    }
                }
                continue;
            } else if (subCommand.equals("5")) {    // Ievadīt naudu
                while (true) {
                    System.out.println("");
                    System.out.println("Kur tu gribi ievadit naudu?");
                    System.out.println(line2);
                    System.out.printf("|%-3s|%-27s|%n","1.", "Budzeta plans");
                    System.out.println(line2);
                    System.out.printf("|%-3s|%-27s|%n","2.", "Dienas limits");
                    System.out.println(line2);
                    System.out.printf("|%-3s|%-27s|%n","3.", "Uzkrajuma merkis");
                    System.out.println(line2);
                    System.out.printf("|%-3s|%-27s|%n","4.", "Atpakal uz galveno izvelni");
                    System.out.println(line2);
                    System.out.print("Ievadi numuru: ");
                    String item = scanner.nextLine().trim();

                    if (item.equals("1")) { // Ievadīt naudu budžeta plānā
                        naudasIevade.ievadiBudzetaPlanaNaudu(lietotajs);
                        System.out.println("");
                    } else if (item.equals("2")) {  // Ievadīt naudu dienas limitā
                        naudasIevade.ievadiDienasLimitaNauda(lietotajs);
                        System.out.println("");
                    } else if (item.equals("3")) {  // Ievadīt naudu uzkrajuma mērķī
                        naudasIevade.ievadiUzkrajumaMerkisNauda(lietotajs);
                        System.out.println("");
                    } else if (item.equals("4")) {  // Atpakaļ uz galveno izvēlni
                        System.out.println("");
                        break;
                    } else {
                        System.out.println(ConsoleColors.RED.code + "Nederiga komanda" + ConsoleColors.RESET.code);
                    }
                }
                continue;
            } else if (subCommand.equals("6")) {    // Iziet
                System.out.println(ConsoleColors.GREEN.code + "Visu labu "+ lietotajs.getName() +"! Uz redzesanos!" + ConsoleColors.RESET.code);
                break;
            } else {
                System.out.println(ConsoleColors.RED.code + "Nederiga komanda" + ConsoleColors.RESET.code);
            }
        }
    }
}

