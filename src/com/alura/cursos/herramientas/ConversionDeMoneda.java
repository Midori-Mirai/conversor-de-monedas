package com.alura.cursos.herramientas;

public class ConversionDeMoneda {
    private double conversion;
    public double convertirEntreMonedas(double moneda, double cantidad, int seleccion){
        if(seleccion == 1){
            conversion = moneda * cantidad;
        } else if (seleccion == 0) {
            conversion = cantidad / moneda;
            //System.out.println("Esta es la conversion de la division: " + conversion);
        }
        return conversion;
    }
}
