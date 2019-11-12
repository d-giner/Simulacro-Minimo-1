import java.util.*;

public class Comanda {
    public int idComanda;
    private String idUsuari;
    List<LP> productesDemanats = null;

    public Comanda(){ /** Constructor per la comanda, rebrem l'id de l'usuari, l'id de la comanda i un HashMap amb els productes + quantitat*/
        this.productesDemanats = new LinkedList<LP>();
    }

    protected class LP {
        int q;
        String p;

        public LP(int q, String p) {
            this.q = q;
            this.p = p;
        }
    }

    public void addLP(int q, String p) {
        LP lp = new LP(q,p);
        this.productesDemanats.add(lp);
    }

    public String getIdUsuari(){
        return this.idUsuari;
    }

    public int getIdComanda(){
        return this.idComanda;
    }

    public void addUser(String idUsuari) {
        this.idUsuari = idUsuari;
    }

    public List<LP> lps() {
        return this.productesDemanats;
    }
}
