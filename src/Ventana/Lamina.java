package Ventana;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
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
        texto.setEditable(false);

        texto.setHorizontalAlignment(SwingConstants.RIGHT); //Dicta la escritura de derecha a izquierda.
        texto.setFont(new Font("Arial", Font.BOLD, 50));

        add(texto, BorderLayout.NORTH); //Coloca el marco de texto en la parte superior de la lámina.
        JPanel panelbotones = new JPanel(); //Creamos un nuevo panel para colocar los botones.
        panelbotones.setLayout(new GridLayout(4, 6, 2, 2)); //Creamos un GridLayout dentro del Panel para organizar los botones.
        agregarBotones(botones, panelbotones);

        add(panelbotones, BorderLayout.CENTER); //Agregamos el panel a la lámina en el centro. Si lo colocamos así, ocupa todo el tamaño restante.
        texto.addKeyListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            String comando = e.getActionCommand(); //Obtiene el texto que contiene los botones.
            if ("0123456789".contains(comando)) {   
                texto.setText(texto.getText() + comando);//Agrega el texto al textField.
            } else if ("+-/*".contains(comando)) {
                operador = comando;
                num1 = Double.parseDouble(texto.getText());
                texto.setText(" ");

            } else if (comando.equals("=")) {
                num2 = Double.parseDouble(texto.getText());

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
    
        } catch (NullPointerException | NumberFormatException j) {
            j.printStackTrace();
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
            btn.setFocusable(false);
            btn.setFont(new Font("Arial", Font.BOLD, 20));
            btn.setBackground(Color.GRAY);
            btn.setForeground(Color.WHITE);
            btn.setFocusable(false);
            btn.addActionListener(this);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyLocation()==KeyEvent.KEY_LOCATION_NUMPAD){
            String comando = e.getKeyChar()+""; //Obtiene el texto que contiene los botones.
            if ("0123456789".contains(comando)) {
                texto.setText(texto.getText() + comando);//Agrega el texto al textField.

            } else if ("+-/*".contains(comando)) {
                operador = comando;
                num1 = Double.parseDouble(texto.getText());
                texto.setText(" ");
            

            } else if (e.getKeyCode()==10) {
                num2 = Double.parseDouble(texto.getText());
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
      
        
    }
    @Override
    public void keyReleased(KeyEvent e) {
        
        
    }
    @Override
    public void keyTyped(KeyEvent e) {
        
        
    }

    
    
}