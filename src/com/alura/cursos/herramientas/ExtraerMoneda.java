package com.alura.cursos.herramientas;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ExtraerMoneda {

    public double filtrarMoneda(String json, String currencyCode){
        //Con JsonParser analizamos el archivo Json
        //Con getAsJsonObject convertimos el Json en un Objeto Json
        //Un JsonObject representa : un grupo de claves con sus valores, {firstName"KEY_NAME: "John"VALUE_STRING}
        JsonObject objetoJson = JsonParser.parseString(json).getAsJsonObject();
        JsonObject monedas = objetoJson.getAsJsonObject("conversion_rates");
        //System.out.println("Este es el campo conversion_rates: " + monedas);

        //double moneda = monedas.get(currencyCode).getAsDouble();
        //System.out.println("moneda USD = " + moneda);
        return monedas.get(currencyCode).getAsDouble();
    }
}
