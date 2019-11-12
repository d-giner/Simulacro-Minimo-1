import java.util.*;


public class Usuari {

    public String nom;
    public String id;
    private List<Comanda> comandes;


    public Usuari(String id, String name, String surname){
        this.id = id;
        this.nom=name;
        this.comandes = new LinkedList<Comanda>();
    }

    public Usuari(String n, int i){ /** Constructor d'Usuari: nom, identificador i nombre total de comandes. */
        this.nom = n;
        this.id = ""+i;
    }

    /** Mètodes per actualitzar i llegir els atributs */
    public String getNom(){
        return this.nom;
    }

    public String getId(){
        return this.id;
    }

    public void addComanda(Comanda comanda) {
        this.comandes.add(comanda);
    }

    public List<Comanda> getComandesUsuari(){
        return this.comandes;
    }
}
