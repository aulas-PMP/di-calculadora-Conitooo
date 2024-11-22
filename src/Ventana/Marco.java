package Ventana;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

public class Marco extends JFrame{

    public static String linea="";
    public Marco(){
        Toolkit pantalla = Toolkit.getDefaultToolkit(); //Se llama a la clase Toolkit para obtener sus métodos.
        Dimension tamano = pantalla.getScreenSize();// Se obtiene las dimensiones de la pantalla.
        int anchura = tamano.width;
        int altura = tamano.height;
        setSize(anchura/2,600); //Tamaño del Marco.
        setLocation(anchura/4,altura/4); //Localización del marco.
        Lamina lamina = new Lamina();
        lamina.setBackground(Color.gray);
        add(lamina);//Se agrega la nueva lámina al marco.
    }

    /**
     * Método que inicia el sistema.
     */
    public static void init(){
        Marco miMarco = new Marco();
        miMarco.setVisible(true);
        miMarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        miMarco.setTitle("Calculadora");
    }
}