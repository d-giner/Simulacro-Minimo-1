import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.util.*;

public class GestorImplementat implements Gestor {

    /** Singleton */
    private static GestorImplementat instance;

    public static GestorImplementat getInstance(){
        if (instance == null) instance = new GestorImplementat();
        return instance;
    }
    /** Singleton end line */

    public Logger log = LogManager.getLogger(GestorImplementat.class);
    private List<Producte> lProductes = null; /** Llistat de productes */
    private HashMap<String, Usuari> lUsuaris = null;  /** Llistat d'usuaris */
    private Queue<Comanda> cuaComanda = null;  /** Cua de comandes */

    public int numComandes = 0;

    private GestorImplementat() {
        lProductes = new ArrayList<Producte>(); /** Llistat de productes */
        lUsuaris = new HashMap<String, Usuari>(); /** Llistat d'usuaris */
        cuaComanda = new LinkedList<Comanda>();; /** Cua de comandes */
    }

    public void clear() {
        lProductes.clear();
        lUsuaris.clear();
        cuaComanda.clear();
    }

    public void afegirUsuari(String id, String name, String surname) {
        this.lUsuaris.put(id, new Usuari(id, name, surname));
    }

    public void afegirProducte(String nom, double preu){
        this.lProductes.add(new Producte(nom, preu));
    }

    public void anotarComanda(Comanda p) {
        this.cuaComanda.add(p);
    }

    public void servirComanda() { /** La comanda es du a terme, es treu de la cua i s'afegeix a la llista general */
        Comanda comanda = this.cuaComanda.poll();
        Usuari usuari =  this.lUsuaris.get(comanda.getIdUsuari()); /** Atraves de la clau (id usuari), obtenim valor (un usuari) */
        usuari.addComanda(comanda);

        Producte producte = null;
        for (Comanda.LP lp : comanda.lps()){
            producte = getProducte (lp.p);
            producte.updateNumVendes(lp.q);
        }
        this.numComandes++;
        log.info("S'ha servit una comanda a l'usuari " + comanda.getIdUsuari() + " correctament.");
        log.info("Nombre total de comandes realitzades: " + numComandes);
    }

    public Producte getProducte(String p) {
        for (Producte producte : this.lProductes) {
            if (producte.getId().equals(p)) return producte;
        }
        log.error("No exiteix el producte.");
        return null;
    }

    public List<Comanda> consultarComandesUsuari(String id) { /** PASSAR AL TEST LA PART DEL FOR!!! */
        Usuari u = lUsuaris.get(id);
        return u.getComandesUsuari();
        /*
        for (dsa.Comanda c : u.getComandesUsuari()){ /* Per cada comanda dins de la llista de comandes de l'usuari... */
        // for (dsa.Comanda.LP p : c.productesDemanats){ /* Per cada producte dins de la llista de productes demanats... */
        //   log.info("L'usuari " + u.getNom() + " ha consumit: dsa.Producte: " + p.p + ", Quantitat: " + p.q);
        //}
        /* }*/
    }

    public List<Producte> ordenarProductesVendes() { /** PASSAR AL TEST LA PART DEL FOR!!! */
        List<Producte> lAux = new ArrayList<Producte>();
        lAux.addAll(lProductes);
        Collections.sort(lAux, new Comparator<Producte>() {
            public int compare(Producte s1, Producte s2) {
                return s1.getVendes() - (s2.getVendes());
            }
        });

        for (int i = 0; i < lAux.size(); i++){
            log.info("Productes ordenats per vendes: " + lAux.get(i).getVendes());
        }
        return lAux;
    }

    public int numUsers() {
        return this.lUsuaris.size();
    }
    public int numComandes() {
        return this.cuaComanda.size();
    }
    public int numProducts() {
        return this.lProductes.size();
    }


    public List<Producte> ordenarProductesPreu() { /** PASSAR AL TEST LA PART DEL FOR!!! */
        List<Producte> lAux = new ArrayList<Producte>();
        lAux.addAll(lProductes);
        Collections.sort(lAux, new Comparator<Producte>() {
            public int compare(Producte s1, Producte s2) {
                return Double.compare(s1.getPreu(), s2.getPreu());
            }
        });

        for (int i = 0; i < lAux.size(); i++){
            log.info("Productes ordenats per preu: " + lAux.get(i).getPreu());
        }
        return lAux;
    }
}
