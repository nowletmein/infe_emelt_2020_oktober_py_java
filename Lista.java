package com.company;

public class Lista {


    int ev;
    int ho;
    int nap;
    String cim;
    int evad;
    int ep;
    int hossz;
    int mengezte;

    public Lista(String datum, String cim, String evad, String hossz, String mengezte) {

        this.cim = cim;
        if (!datum.equals("NI")) {
            String tmp[] = datum.split("\\.");
            ev = Integer.parseInt(tmp[0]);
            ho = Integer.parseInt(tmp[1]);
            nap = Integer.parseInt(tmp[2]);
        }
        String arg[] = evad.split("x");
        this.ep = Integer.parseInt(arg[1]);
        this.evad = Integer.parseInt(arg[0]);
        this.hossz = Integer.parseInt(hossz);
        this.mengezte = Integer.parseInt(mengezte);
    }

    public String getDatum() {
        if (ev != 0){
            return String.format("%s%s%s", ev,ho,nap);
        }
        return "NI";
    }

}
