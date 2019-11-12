import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class TestGestor {

    public GestorImplementat gestor = null;

    private void afegirUsuaris() {
        gestor.afegirUsuari("38383773X","Alberto","");
        gestor.afegirUsuari("383837B","Marc","");
        gestor.afegirUsuari("3838373T","Andreu","");
    }

    private void afegirProductes() {
        gestor.afegirProducte("cola", 1.5);
        gestor.afegirProducte("canyaChoco", 1.6);
        gestor.afegirProducte("cafe", 1);
    }

    @Before
    public void SetUp () {
        gestor = new GestorImplementat();
        afegirUsuaris();
        afegirProductes();
    }

    @Test
    public void testInicialitzador(){
        Assert.assertEquals("Nombre d'usuaris a llista.", 3, this.gestor.lUsuaris.size());
    }

    @Test
    public void testAnotarServirComanda(){
        Comanda p = new Comanda();
        p.addUser("383837B");
        p.addLP(3, "cola" );
        p.addLP(1, "canyaChoco");
        p.addLP(1, "cafe");

        gestor.anotarComanda(p);
        gestor.servirComanda();

        p = new Comanda();
        p.addUser("38383773X");
        p.addLP(15, "canyaChoco");

        gestor.anotarComanda(p);
        gestor.servirComanda();

        Assert.assertEquals("Servir Comanda", 3,gestor.getProducte("cola").getVendes());
        Assert.assertEquals("Comandes a la cua",0,gestor.cuaComanda.size());
        Assert.assertEquals("",2,gestor.numComandes);

        gestor.consultarComandesUsuari("38383773X");

        gestor.ordenarProductesVendes();
    }

    @Test
    public void testConsultarComandesUsuari(){
        Comanda p = new Comanda();
        p.addUser("383837B");
        p.addLP(3, "cola" );
        p.addLP(1, "canyaChoco");
        p.addLP(1, "cafe");

        gestor.anotarComanda(p);
        gestor.servirComanda();

        p = new Comanda();
        p.addUser("38383773X");
        p.addLP(15, "canyaChoco");

        gestor.anotarComanda(p);
        gestor.servirComanda();
        gestor.consultarComandesUsuari("383837B");
    }

    @Test
    public void testOrdreLlistaProductes(){
        Comanda p = new Comanda();
        p.addUser("383837B");
        p.addLP(3, "cola" );
        p.addLP(1, "canyaChoco");
        p.addLP(1, "cafe");

        gestor.anotarComanda(p);
        gestor.servirComanda();

        p = new Comanda();
        p.addUser("38383773X");
        p.addLP(15, "canyaChoco");

        gestor.anotarComanda(p);
        gestor.servirComanda();
        gestor.ordenarProductesVendes();
        gestor.ordenarProductesPreu();
    }
}
