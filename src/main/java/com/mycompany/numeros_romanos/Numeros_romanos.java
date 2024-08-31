package com.mycompany.numeros_romanos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Numeros_romanos {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.println("cantidad de numeros romanos");
        int n = scn.nextInt();
        String[] romanos = new String[n];
        scn.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.println("Numero en romano:");
            romanos[i] = scn.nextLine();
        }
        int[] decimal = new int[n];
        for (int i = 0; i < n; i++) {
            String numeroRomano = romanos[i];
            int longitud = numeroRomano.length();

            for (int j = 0; j < longitud; j++) {
                char actual = numeroRomano.charAt(j);

                switch (actual) {
                    case 'M':
                        decimal[i] += 1000;
                        break;
                    case 'D':
                        decimal[i] += 500;
                        break;
                    case 'C':
                        if (j + 1 < longitud && numeroRomano.charAt(j + 1) == 'D') {
                            decimal[i] += 400;
                            j++;
                        } else if (j + 1 < longitud && numeroRomano.charAt(j + 1) == 'M') {
                            decimal[i] += 900;
                            j++;
                        } else {
                            decimal[i] += 100;
                        }
                        break;
                    case 'L':
                        decimal[i] += 50;
                        break;
                    case 'X':
                        if (j + 1 < longitud && numeroRomano.charAt(j + 1) == 'L') {
                            decimal[i] += 40;
                            j++;
                        } else if (j + 1 < longitud && numeroRomano.charAt(j + 1) == 'C') {
                            decimal[i] += 90;
                            j++;
                        } else {
                            decimal[i] += 10;
                        }
                        break;
                    case 'V':
                        decimal[i] += 5;
                        break;
                    case 'I':
                        if (j + 1 < longitud && numeroRomano.charAt(j + 1) == 'V') {
                            decimal[i] += 4;
                            j++;
                        } else if (j + 1 < longitud && numeroRomano.charAt(j + 1) == 'X') {
                            decimal[i] += 9;
                            j++;
                        } else {
                            decimal[i] += 1;
                        }
                        break;
                    default:
                        System.out.println("Caracter invalido: " + actual);
                        break;
                }
            }
        }
        String nombreArchivo = "numeros_romanos.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            writer.write("Numero Romano,Valor Decimal");
            writer.newLine();

            for (int i = 0; i < romanos.length; i++) {
                writer.write(romanos[i] + "," + decimal[i]);
                writer.newLine();
            }

            System.out.println("Archivo CSV creado exitosamente: " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al crear el archivo CSV: " + e.getMessage());
        }
    }
}
