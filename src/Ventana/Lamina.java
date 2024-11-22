package Ventana;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
/**
 * Clase que representa la lámina interna del marco
 */
public class Lamina extends JPanel implements ActionListener, KeyListener{
    
    JTextField texto;
    double num1, num2;
    String operador;
    
    static String[] botones = { "1", "2", "3", "+",
                         "4", "5", "6", "-",
                         "7", "8", "9", "*",
                         "C", "0", "=", "/" };
    
    /**
     * Constructor parametrizado.
     */
    public Lamina() {
        setLayout(new BorderLayout()); //Nuevo layout para poder organizar los elementos.
        texto = new JTextField();
        requestFocus();
        texto.addKeyListener(this);

        texto.setHorizontalAlignment(SwingConstants.RIGHT); //Dicta la escritura de derecha a izquierda.
        texto.setFont(new Font("Arial", Font.BOLD, 50));

        add(texto, BorderLayout.NORTH); //Coloca el marco de texto en la parte superior de la lámina.

        JPanel panelbotones = new JPanel(); //Creamos un nuevo panel para colocar los botones.
        panelbotones.setLayout(new GridLayout(4, 4, 2, 2)); //Creamos un GridLayout dentro del Panel para organizar los botones.
        agregarBotones(botones, panelbotones);

        add(panelbotones, BorderLayout.CENTER); //Agregamos el panel a la lámina en el centro. Si lo colocamos así, ocupa todo el tamaño restante.

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        
        String comando = e.getActionCommand(); //Obtiene el texto que contiene los botones.
        if ("0123456789".contains(comando)) {
            texto.setEditable(true);
            texto.setText(texto.getText() + comando);//Agrega el texto al textField.
            requestFocus();
        } else if ("+-/*".contains(comando)) {
            operador = comando;
            num1 = Double.parseDouble(texto.getText());
            texto.setText(" ");
            requestFocus();
        } else if (comando.equals("=")) {
            num2 = Double.parseDouble(texto.getText());
            requestFocus();
            //Switch para poder gestionar los signos.
            
            switch (operador) {
                case "+":
                    texto.setText(String.valueOf(Operaciones.suma(num1, num2)));

                    break;
                case "-":
                    texto.setText(String.valueOf(Operaciones.resta(num1, num2)));

                    break;
                case "*":
                    texto.setText(String.valueOf(Operaciones.producto(num1, num2)));

                    break;

                case "/":
                    texto.setText(String.valueOf(Operaciones.div(num1, num2)));

                    break;

                default:
                    break;
            }
        }
        else if(comando.equals("C")){
            texto.setText("");
            num1 = 0;
            num2=0;
        }

    }

    /**
     * Metodo que agrega los botones a la calculadora.
     * 
     * @param botones
     */
    public void agregarBotones(String[] botones, JPanel panel) {
        for (int i = 0; i < botones.length; i++) {
            String boton = botones[i] + "";
            JButton btn = new JButton(boton);
            panel.add(btn);

            btn.setFont(new Font("Arial", Font.BOLD, 20));
            btn.setBackground(Color.GRAY);
            btn.setForeground(Color.WHITE);

            btn.addActionListener(this);
        }
    }

    @OverridePesonas
    public void keyPressed(KeyEvent e) {
        
        if(e.getKeyCode()==KeyEvent.VK_NUMPAD2){
            texto.setText("Hola");
        }
        
        
    }
    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
}