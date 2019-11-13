package edu.upc.dsa;

import java.util.*;

public interface Gestor {
    public void afegirProducte(String nom, double preu);
    public void afegirUsuari(String id, String name, String surname);
    public void anotarComanda(Comanda p); /** Recollim les dades de la comanda i afegim a la cua de comandes*/
    public void servirComanda(); /** Crearem nova comanda i actualitzarem la llista i la cua de comandes*/
    public List<Comanda> consultarComandesUsuari(String id); /** Mostrar totes les comandes realitzades d'un usuari concret */
    public List<Producte> ordenarProductesPreu(); /** Mostrar el llistat de productes ordenats per preu */
    public List<Producte> ordenarProductesVendes(); /** Mostrar el lisstat de prodcutes ordenats per nombre de vendes */
    public int numUsers();
    public int numProducts();
    public int numComandes();
    public void clear();
}
