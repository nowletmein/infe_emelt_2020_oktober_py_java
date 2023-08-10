package com.company;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {


    public static String getDatum_fromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Adjon meg egy dátumot Datum= ");
        String datum = scanner.next();
        return datum;
    }

    public static String hetnapja(int ev, int ho, int nap) {
        String[] napok = {"v", "h", "k", "sze", "cs", "p", "szo"};
        int[] honapok = {0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4};
        if (ho < 3) {
            ev = ev - 1;
        }
        String hetnapja = napok[(ev + ev / 4 - ev / 100 + ev / 400 + honapok[ho - 1] + nap) % 7];
        return hetnapja;
    }

    public static void main(String[] args) {
        // write your code here

        ArrayList<Lista> adatok = new ArrayList<Lista>();
        try{
            BufferedReader adat = new BufferedReader(new FileReader("lista.txt"));
            String[] egySor = new String[5];
            while((egySor[0] = adat.readLine()) != null){
                for (int i = 1; i < egySor.length; i++) {
                    egySor[i] = adat.readLine();
                }
                adatok.add(new Lista(egySor[0], egySor[1], egySor[2], egySor[3], egySor[4]));
            }

        }catch (IOException e){
            System.out.println(e);
        }

        int counter = 0;
        for (Lista adat:adatok) {
            if (!adat.getDatum().equals("NI")){
                counter++;
            }
        }
        System.out.println("2.feladat " + counter);
        //3.
        int megnezte = 0;
        for (Lista adat:adatok) {
            if (adat.mengezte == 1){
                megnezte++;
            }
        }
        System.out.println(megnezte+" \n" + adatok.size());
        double valasz = Double.valueOf(megnezte) / Double.valueOf(adatok.size()) * 100;

        System.out.format("3.feladat %.2f \n", valasz);


        int sumTime = 0;
        for (Lista series : adatok) {
            if (series.mengezte ==  1) {
                sumTime += series.hossz;
            }
        }
        int days = sumTime / (60 * 24);
        int hours = sumTime / 60 % 24;
        int minutes = sumTime % 60;
        System.out.printf("4. feladat Sorozatnézéssel %d napot %d órát és %d percet töltött.\n", days, hours, minutes);

        //5
        System.out.println("5.feladat ");
        String datum = getDatum_fromUser();
        int Datum_ev;
        int Datum_ho;
        int Datum_nap;

        String tmp[] = datum.split("\\.");
        Datum_ev = Integer.parseInt(tmp[0]);
        Datum_ho = Integer.parseInt(tmp[1]);
        Datum_nap = Integer.parseInt(tmp[2]);


        for (Lista adat: adatok) {
            if (adat.ev != 0 && ((adat.ev < Datum_ev) || (adat.ev == Datum_ev && adat.ho < Datum_ho) || (adat.ev == Datum_ev && adat.ho == Datum_ho && adat.nap <= Datum_nap)) && adat.mengezte == 0){
                System.out.println(adat.evad +"x"+adat.ep + "   " + adat.cim);
            }
        }
        //7.feladat
        Scanner scanner = new Scanner(System.in);
        List<String> napisori = new ArrayList<>();
        System.out.println("Adja meg a hét egy napját (például cs)! Nap= ");
        String nap = scanner.nextLine();
        for (Lista adat : adatok) {
            if (!napisori.contains(adat.cim) && adat.ev != 0 && nap.equals(hetnapja(adat.ev,adat.ho ,adat.nap))){
                napisori.add(adat.cim);
            }
        }
        for (String a:napisori) {
            System.out.println(a);
        }



        List<KiLista> kiadatok = new ArrayList<>();
        for (Lista adat : adatok) {
            if (!napisori.contains(adat.cim)){
                napisori.add(adat.cim);

            }
        }

        for (String adat : napisori) {
            int db = 0;
            int hossz = 0;

            for (Lista ad:adatok) {
                String cim = ad.cim;
                if (adat.matches(cim)){
                    db++;
                    hossz += ad.hossz;
                }
            }
            kiadatok.add(new KiLista(adat, hossz, db));
        }

        //8.feladat
        try {
            FileWriter fileWriter = new FileWriter("summa.txt");
            for (KiLista adat:kiadatok) {
                fileWriter.write(adat.cim +" " +adat.hossz + " " + adat.db + "\n");
            }
            fileWriter.close();

        }catch (IOException e){
            System.out.println(e);
        }
    }
}
