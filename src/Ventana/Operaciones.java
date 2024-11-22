package Ventana;

public class Operaciones {
    /**
     * 
     * @param a primer numero a sumar.
     * @param b segundo número a sumar.
     * @return la suma de ambos números.
     */
    public static double suma(double a, double b) {

        return a + b;
    }
    /**
     *
     * @param a primer numero a restar
     * @param b segundo numero a restar.
     * @return la resta de dos números.
     */
    public static double resta(double a, double b) {

        return a - b;
    }
/**
 * 
 * @param a el primer número a multiplicar.
 * @param b el segundo número a multiplicar.
 * @return el producto de dos números
 */
    public static double producto(double a, double b) {

        return a * b;
    }

    /**
     * 
     * @param a el primer número a dividir.
     * @param b el segundo número a dividir.
     * @return la división de dos números.
     */
    public static double div(double a, double b) {
        double res = 0.0;
        if (b != 0) {
            res = a / b;
            return res;
        }

        return res;

    }
}