package TreningsApp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ForsteTreningsClass {

    //får dagens dato og angir en peker
    File filSti = new File("#");
    private LocalDate dato = LocalDate.now();
    //Setter formate til månder/dager/år
    private DateTimeFormatter formaterer = DateTimeFormatter.ofPattern("MMMM dd yyyy");
    private String dagensDato = dato.format(formaterer);

    //Deklarerer alle variabler som blir brukt
    private String ovelse;
    private Scanner tastatur = new Scanner(System.in);
    private int set;
    private int reps;
    private int vekt;
    private boolean erForsteOvelse;
    private List<String> treningsListe = new ArrayList<>();
    private List<String> treningsLog = new ArrayList<>();
    private int totalVektLoftet;
    private  List<ForsteTreningsMåler> måler = new ArrayList<>();

    public void lagerListeAvTrening(){
        treningsListe.add("Knebøy");
        treningsListe.add("Benkpress");
        treningsListe.add("Kropps hevning");

    }

    //returnerer boolean true dersom bruker planlegger gjore en ny ovelse elles returner false
    public boolean trenerFortsatt(){
        boolean fortsetterTreningen = false;
        System.out.println("Er du ferdig med treningen for idag? (y/n)");
        char resultat = tastatur.next().charAt(0);
        return fortsetterTreningen = (resultat == 'y' || resultat == 'Y') ? true : false;
    }

    public boolean trenerNå(){
        boolean trener = false;
        System.out.println("Skal du trene idag? (y/n)");
        char resultat = tastatur.next().charAt(0);
        return trener = ( resultat =='y' || resultat == 'Y') ? true : false;
    }

    public void visTreningsliste() {
        int i = 1;
        for (String trening : treningsListe) {
            System.out.println(i++ + ". " + trening);
        }
        System.out.println("0. For å Avslutte");
    }

    // Gir brukeren noen øvelser å velge imellom, basert på brukerens input returnerer den øvelsen med en motivasjonsmelding
    public String hvilkenOvelse() {
        System.out.println("Velg en øvelse fra listen under eller press 0 for å avslutte økten");
        visTreningsliste();
        int ovelsesValg = tastatur.nextInt();
        String ovelseGjort = treningsListe.get(ovelsesValg);
        switch (ovelsesValg) {
            case 0:
                System.out.println("Vi starter igjen imorgen!");
                System.exit(0);
                break;
            case 1:
                System.out.println("Kult, la oss gjøre litt " + ovelseGjort +".");
                return ovelseGjort;
            case 2:
                System.out.println("Rått, eg elsker " + ovelseGjort + "!");
                return ovelseGjort;
            case 3:
                System.out.println(ovelseGjort + " vil få deg godt trent!");
                return ovelseGjort;
            case 4:
            case 5:
            case 6:
            default:
                System.out.println("hva var det ");
                return "";
        }
        return "HVA";
    }

    public int numSet(){
        System.out.println("Hvor mange set?");
        int setGjort = tastatur.nextInt();
        return setGjort;
    }
    public int numReps(){
        System.out.println("Hvor mange reps?");
        int repsGjort = tastatur.nextInt();
        return repsGjort;
    }
    public int vektBrukt(){
        int vektLoftet = 0;
        System.out.println("Hvilken vekt?");
        try {
            vektLoftet = tastatur.nextInt();
        }
        catch (Exception e) {
            System.out.println("Må vere et tall");
            tastatur.nextInt();
            vektBrukt();
        }
        return vektLoftet;
    }

    public void trening() {
        lagerListeAvTrening();
        if(trenerNå()){
            lagFil();
            do {
                ovelse = hvilkenOvelse();
                set =  numSet();
                reps = numReps();
                vekt = vektBrukt();
                totalVektLoftet += vekt;
                måler.add(new ForsteTreningsMåler(ovelse, set, reps, vekt));
            }while(trenerFortsatt());
            logTrening(måler);
        }else{
            System.out.println("Okei kanskje imorgen.");
        }
    }
    public void printResultat(){
        System.out.println(dagensDato + "\n");
        for (ForsteTreningsMåler stat : måler){
            System.out.println(stat);
        }
        System.out.println("\nTotal vekt løftet idag: " + totalVektLoftet + " kilo!");
    }
    public void lagFil(){
        boolean b = false;
        if(!filSti.exists()){
            try {
                b = filSti.createNewFile();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }else{
            System.out.println("Filen eksisterer allerede, legger til treningsLog.");
        }
        if (b){
            System.out.println("Fil blir laget!");
        }
    }

    public void logTrening (List<ForsteTreningsMåler> måler){
        try(FileWriter fw = new FileWriter(filSti)){
            try(BufferedWriter skriver = new BufferedWriter(fw)){
                for(ForsteTreningsMåler stat : måler){
                    skriver.write(stat.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
