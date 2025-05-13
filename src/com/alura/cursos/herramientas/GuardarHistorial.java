package com.alura.cursos.herramientas;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GuardarHistorial {
    public void guardarListaDeHistorial(List<String> historial, String nombreArchivo) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter archivo = new FileWriter(nombreArchivo + ".json");
        archivo.write(gson.toJson(historial));
        archivo.close();
        System.out.println("El archivo se guardo correctamente, con el nombre de: " + nombreArchivo);
    }
}
