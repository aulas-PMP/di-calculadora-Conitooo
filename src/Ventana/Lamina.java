package Ventana;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * Clase que representa la lámina interna del marco.
 */
public class Lamina extends JPanel implements ActionListener, KeyListener {

    // Atributos
    private JTextField texto,variables;
    private double num1, num2;
    private String operador;
    private Color myBackground = new Color(67, 46, 84);
    private Color myButton = new Color(55, 58, 64);

    private static final String[] BOTONES = {
            "1", "2", "3", "+",
            "4", "5", "6", "-",
            "7", "8", "9", "*",
            "C", "0", "=", "/"
    };

    /**
     * Constructor parametrizado.
     */
    public Lamina() {
        configurarLamina();
        configurarTexto();
        agregarPanelBotones();
        agregarVariables();
    }

    /**
     * Configura las propiedades generales de la lámina.
     */
    private void configurarLamina() {
        setLayout(new BorderLayout());
    }

    /**
     * Configura el campo de texto para la calculadora.
     */
    private void configurarTexto() {
        texto = new JTextField();
        texto.setEditable(false);
        texto.setHorizontalAlignment(SwingConstants.RIGHT);
        texto.setFont(new Font("Arial", Font.BOLD, 50));
        texto.addKeyListener(this);
        add(texto, BorderLayout.NORTH);
    }

    /**
     * Crea y configura el panel de botones.
     */
    private void agregarPanelBotones() {
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(4, 6, 2, 2));
        agregarBotones(BOTONES, panelBotones);
        add(panelBotones, BorderLayout.CENTER);
        panelBotones.setBackground(myBackground);
    }

    /**
     * Agrega una sección para las variables que se están sumando.
     */
    private void agregarVariables(){
        variables = new JTextField();
        variables.setEditable(false);
        variables.setFont(new Font("Arial",Font.BOLD,25));
        add(variables,BorderLayout.SOUTH);
        variables.setForeground(Color.BLACK);
    }

    /**
     * Agrega los botones al panel.
     * 
     * @param botones Array de etiquetas de botones.
     * @param panel   Panel donde se agregarán los botones.
     */
    private void agregarBotones(String[] botones, JPanel panel) {
        for (String boton : botones) {
            JButton btn = new JButton(boton);
            btn.setFocusable(false);
            btn.setFont(new Font("Arial", Font.BOLD, 20));
            btn.setBackground(myButton);
            btn.setForeground(Color.WHITE);
            btn.addActionListener(this);
            panel.add(btn);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        texto.setForeground(Color.BLACK);
        try {
            String comando = e.getActionCommand();
            if ("0123456789".contains(comando)) {
                texto.setText(texto.getText() + comando);
            } else if ("+-/*".contains(comando)) {
                operador = comando;
                num1 = Double.parseDouble(texto.getText());
                variables.setText(""+num1);
                texto.setText("");
            } else if ("=".equals(comando)) {
                procesarResultado();
            } else if ("C".equals(comando)) {
                limpiar();
            }
        } catch (NullPointerException | NumberFormatException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Procesa el resultado según el operador seleccionado.
     */
    private void procesarResultado() {
        num2 = Double.parseDouble(texto.getText());
        double resultado = 0;

        switch (operador) {
            case "+":
                resultado = Operaciones.suma(num1, num2);
                break;
            case "-":
                resultado = Operaciones.resta(num1, num2);
                break;
            case "*":
                resultado = Operaciones.producto(num1, num2);
                break;
            case "/":
                resultado = Operaciones.div(num1, num2);
                break;
        }

        if (resultado < 0) {
            texto.setForeground(Color.RED);
        } else {
            texto.setForeground(Color.BLACK);
        }
        texto.setText(String.valueOf(resultado));
        if (texto.getText().endsWith(".0")) {
            int resultadoInt = (int) resultado;
            texto.setText(String.valueOf(resultadoInt));

        }

    }

    /**
     * Limpia los datos de la calculadora.
     */
    private void limpiar() {
        texto.setText("");
        num1 = 0;
        num2 = 0;
        variables.setText("");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyLocation() == KeyEvent.KEY_LOCATION_NUMPAD) {
            manejarEntradaTeclado(e);
        }
    }

    private void manejarEntradaTeclado(KeyEvent e) {
        texto.setForeground(Color.BLACK);
        String comando = String.valueOf(e.getKeyChar());
        if ("0123456789.".contains(comando)) {
            texto.setText(texto.getText() + comando);
            
        } else if ("+-/*".contains(comando)) {
            operador = comando;
            num1 = Double.parseDouble(texto.getText());
            texto.setText("");
            variables.setText(""+num1);
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            procesarResultado();
            variables.setText("");
        } else if ("C".equals(comando)) {
            limpiar();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // No implementado
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // No implementado
    }
}
