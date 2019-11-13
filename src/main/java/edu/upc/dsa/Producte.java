package edu.upc.dsa;

public class Producte {

    public String id;
    public int vendes;
    public double preu;

    public Producte(String n, double p) {
        this.id =  n;
        this.vendes = 0;
        this.preu = p;
    }

    public String getId(){
        return this.id;
    }

    public void setVendes(int v){
        this.vendes = v;
    }

    public int getVendes(){
        return this.vendes;
    }

    public double getPreu(){
        return this.preu;
    }

    public void updateNumVendes(int q) {
        this.vendes+=q;
    }
}
