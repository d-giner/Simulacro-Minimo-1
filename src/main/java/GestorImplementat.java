import kotlin.reflect.jvm.internal.impl.types.model.TypeSystemOptimizationContext;
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
    public List<Producte> lProductes = new ArrayList<Producte>(); /** Llistat de productes */
    public HashMap<String, Usuari> lUsuaris = new HashMap<String, Usuari>(); /** Llistat d'usuaris */
    public Queue<Comanda> cuaComanda = new LinkedList<Comanda>();; /** Cua de comandes */

    public int numComandes = 0;

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

    public void consultarComandesUsuari(String id) {
        Usuari u = lUsuaris.get(id);
        for (Comanda c : u.getComandesUsuari()){ /* Per cada comanda dins de la llista de comandes de l'usuari... */
            for (Comanda.LP p : c.productesDemanats){ /* Per cada producte dins de la llista de productes demanats... */
                log.info("L'usuari " + u.getNom() + " ha consumit: Producte: " + p.p + ", Quantitat: " + p.q);
            }
        }
    }

    public List<Producte> ordenarProductesVendes() {
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

    public List<Producte> ordenarProductesPreu() {
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
