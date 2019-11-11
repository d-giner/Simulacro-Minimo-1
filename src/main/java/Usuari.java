import java.util.*;

public class Usuari {

    public String nom;
    public int id;
    public int totalComandes;

    public List<Integer> lComandes = new List<Integer>(); /**Qué pasaaaaaaaaaa, no me deja crear la lista de enteros*/


    public Usuari(String n, int i){
        this.nom = n;
        this.id = i;
        this.totalComandes = 0;
    }

    public String getNom(){
        return this.nom;
    }

    public int getId(){
        return this.id;
    }

    public void setTotalComandes(int modComandes){
        this.totalComandes += modComandes;
    }

    public int getTotalComandes() {
        return this.totalComandes;
    }
}
