import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        Producte cola = new Producte("cola",1.5);
        Producte cañaChoco = new Producte("cañaChoco", 1.6);
        Producte cafe = new Producte("cafe", 1);

        List<Producte> lProductes = new ArrayList<Producte>();
        lProductes.add(cola);
        lProductes.add(cañaChoco);
        lProductes.add(cafe);

        for (Producte product : lProductes) {
            System.out.println(product.getNom());
        }
    }

}
