package com.practica.estructura.base.domain.controller.arreglo;
//import com.practica.estructura.base.domain.controller.dataStruct.list.LinkedList;
//import com.practica.estructura.base.domain.controller.dataStruct.list.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Arreglo {

    // Cuenta los números en el archivo
    public int countNumbers(String filePath) {
        int totalNumbers = 0;
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    String[] parts = line.split("\\s*,\\s*");
                    totalNumbers += parts.length;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + filePath);
        }
        return totalNumbers;
    }

    // Extrae los datos del archivo y los devuelve como array
    public int[] extractData(String filePath) {
        int totalNumbers = countNumbers(filePath);
        int[] numbers = new int[totalNumbers];
        int index = 0;

        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    String[] parts = line.split("\\s*,\\s*");
                    for (String part : parts) {
                        try {
                            numbers[index++] = Integer.parseInt(part.trim());
                        } catch (NumberFormatException e) {
                            System.out.println("Valor inválido ignorado: " + part);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + filePath);
        }

        return numbers;
    }

    

    // Presenta los resultados y mide el rendimiento
    public void procesarArreglo() {
        String filePath = "Data/data.txt";

        long inicioLectura = System.nanoTime();
        int[] numeros = extractData(filePath);
        long finLectura = System.nanoTime();

        System.out.println("\nDatos extraídos del archivo:");
        for (int num : numeros) {
            System.out.print(num + " ");
        }

        System.out.println("\n\nTiempo de lectura de datos: " + (finLectura - inicioLectura) / 1e6 + " ms");

        // Medir el tiempo para detectar repetidos
        long inicioRepetidos = System.nanoTime();
        Nodo repetidos = detectarRepetidos(numeros);
        long finRepetidos = System.nanoTime();

        // Mostrar los repetidos
        System.out.println("\nElementos repetidos:");
        Nodo temp = repetidos;
        while (temp != null) {
            System.out.println("Número: " + temp.valor);
            temp = temp.siguiente;
        }

        System.out.println("\nTiempo para detectar repetidos: " + (finRepetidos - inicioRepetidos) / 1e6 + " ms");
    }

    // Método principal para ejecutar la clase directamente
    public static void main(String[] args) {
        Arreglo arreglo = new Arreglo();
        arreglo.procesarArreglo();
    }
}
