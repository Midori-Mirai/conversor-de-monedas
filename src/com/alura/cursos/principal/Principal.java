package com.alura.cursos.principal;

import com.alura.cursos.herramientas.ConsultaApiDivisa;
import com.alura.cursos.herramientas.ConversionDeMoneda;
import com.alura.cursos.herramientas.ExtraerMoneda;
import com.alura.cursos.herramientas.GuardarHistorial;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int contador = 0;
        int opcion;
        int seleccion = 0;
        double cantidad = 0;
        double resultadoDeLaConversion = 0;
        double moneda;
        String json;
        String currencyCode = "";
        String nombreArchivo;
        ConsultaApiDivisa consulta = new ConsultaApiDivisa();
        ExtraerMoneda monedaDeCambio = new ExtraerMoneda();
        ConversionDeMoneda convertir = new ConversionDeMoneda();
        GuardarHistorial guardar =  new GuardarHistorial();
        json = consulta.obtenerDivisa();
        List<String> historial = new ArrayList<>();


        while(true){
            System.out.println("****+++++Bienvenido(a) al conversor de monedas+++++****\n" +
                    "1) - Peso MX a Dólar (MXN a USD)\n" +
                    "2) - Dólar a peso MX (USD a MXN)\n" +
                    "3) - Peso Mx a Dólar Canadiense (MXN a CAD)\n" +
                    "4) - Dólar Canadiense a Peso Mx (CAD a MXN)\n" +
                    "5) - Peso Mx a Soles peruanos (MXN a PEN)\n" +
                    "6) - Soles peruanos a Peso MX (PEN a MXN)\n" +
                    "7) - Ver el historial de conversiones\n" +
                    "8) - Guardar el historial - la aplicación finalizará" +
                    "°°°° Elige la opción que deseas usar - Si deseas salir escribe 0: °°°°");
            opcion = entrada.nextInt();

            switch (opcion){
                case 1 -> {seleccion = 1; currencyCode = "USD";}
                case 2 -> {seleccion = 0; currencyCode = "USD";}
                case 3 -> {seleccion = 1; currencyCode = "CAD";}
                case 4 -> {seleccion = 0; currencyCode = "CAD";}
                case 5 -> {seleccion = 1; currencyCode = "PEN";}
                case 6 -> {seleccion = 0; currencyCode = "PEN";}
                case 7 -> {
                    System.out.println("~~~~~~~~Historial de Conversiones~~~~~~~~~");
                    if(historial.isEmpty()){
                        System.out.println("°°°|||||||--El historial esta vacío--|||||||°°°");
                    }else {
                        for(String h : historial){
                            System.out.println(h);
                        }
                    }
                    continue;
                }
                case 8 -> {
                    System.out.println("Tecleé el nombre con el que desea guadar el historial: ");
                    nombreArchivo = entrada.next();
                    try {
                        guardar.guardarListaDeHistorial(historial, nombreArchivo);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return;
                }
                case 0 -> {
                    System.out.println("¡¡¡¡¡Hasta pronto!!!!!");
                    //continuar = false;
                    //break;
                    return;
                }
                default -> {
                    System.out.println("...-----*****Opción no valida, intenta de nuevo*****-----...");
                    continue;
                }
            }
            System.out.println("Ingresa la cantidad a convertir: ");
            cantidad = entrada.nextDouble();
            moneda = monedaDeCambio.filtrarMoneda(json,currencyCode);
            resultadoDeLaConversion = convertir.convertirEntreMonedas(moneda, cantidad,seleccion) ;
            contador++;
            System.out.println("Conversión final: " + resultadoDeLaConversion);
            String registro = "Conversión " + contador + " -> " + cantidad
                    + (seleccion == 1 ? " MXN a " + currencyCode : " " + currencyCode + " a MXN")
                    + " es: " + resultadoDeLaConversion;
            historial.add(registro);
        }

    }
}
