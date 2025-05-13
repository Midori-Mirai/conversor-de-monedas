package com.alura.cursos.herramientas;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaApiDivisa {

    private static final String apiKeyRox = "2373d860d38cb90759ac9cc1";
    //metodo para obtener los tipos de cambios con la base MXN
    public String obtenerDivisa(){

        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/" + apiKeyRox + "/latest/MXN");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();
        try{
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Código de estado: "  + response.statusCode());
            //String json = response.body();
            return response.body();


        } catch (IOException | InterruptedException e){
            throw new RuntimeException("Error al consultar API de divisas");
        }

    }
}
