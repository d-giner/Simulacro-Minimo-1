public class Producte {

    public String nom;
    public int vendes;
    public double preu;

    public Producte(String n, double p) {
        this.nom =  n;
        this.vendes = 0;
        this.preu = p;
    }

    public String getNom(){
        return this.nom;
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

}
