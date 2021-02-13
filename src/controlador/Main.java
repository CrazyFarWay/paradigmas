package controlador;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        try {
            GestionConexion.inicializar();
        } catch (Exception ex) {
            System.out.println(ex);
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
