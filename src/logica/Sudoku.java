package logica;

import datos.DatosSudoku;
import presentacion.Modelo;

public class Sudoku {

    private Modelo modelo;
    private DatosSudoku tablero;
    private int[][] matriz;
    private int[] error2 = new int [2];
    private final int[][] matrizErrores = new int[9][9];

    public Modelo getModelo() {
        return modelo;
    }

    public DatosSudoku getDatosSudoku() {
        if (tablero == null) {
            tablero = new DatosSudoku();
        }
        return tablero;
    }

    public void setMatriz(int[][] matriz) {
        this.matriz = matriz;
        for (int filas = 0; filas < 9; filas++) {
            for (int column = 0; column < 9; column++) {
                matrizErrores[filas][column] = 0;
            }
        }
    }

    public int[][] llamarTableroNuevo() {
        return getDatosSudoku().obtenerTableroNuevo();
    }

    public boolean verificarLleno() {
        int i, j, sum = 1, totalHorizontal = 0, totalVertical = 0, total3x3 = 0;

        for (i = 0; i < 9; i++) {//for horizontal
            for (j = 0; j < 9; j++) {
                if (matriz[i][j] == 0) {
                    return false;
                } else {
                    sum = sum * matriz[i][j];
                }
            }
            totalHorizontal = totalHorizontal + sum;
            sum = 1;
        }
        for (i = 0; i < 9; i++) {//for vertical
            for (j = 0; j < 9; j++) {
                sum = sum * matriz[i][j];
            }
            totalVertical = totalVertical + sum;
            sum = 1;
        }

        for (int c = 0; c < 9; c++) {//for para cuadros pequeños
            int inicio = 0, limite = 3;
            for (i = inicio; i < limite; i++) {
                for (j = inicio; j < limite; j++) {
                    sum = sum * matriz[i][j];
                }
            }
            total3x3 = total3x3 + sum;
            sum = 1;
            inicio = inicio + 3;
            limite = limite + 3;
        }

        if (totalHorizontal == 362880 * 9 && totalVertical == 362880 * 9 && total3x3 == 362880 * 9) {
            return true;
        }
        return false;
    }

    public int[][] verificarSinLlenar() {
        int contador = 0;
        
        //Verificacion de columnas sin repeticion
        for (byte numeros = 1; numeros < 10; numeros++) {// numeros del 1 al 10
            for (byte filas = 0; filas < 9; filas++) {
                for (byte column = 0; column < 9; column++) {
                    if (matriz[filas][column] == numeros) {
                        contador++;
                        if (contador == 2) {
                            matrizErrores[filas][column] = -1;
                            matrizErrores[error2[0]][error2[1]] = -1;
                            return matrizErrores;
                        } else {
                            error2[0] = filas;
                            error2[1] = column;
                        }
                    }
                }
                for (byte column = 0; column < 9; column++) {
                    if (matrizErrores[filas][column] == -1) {
                        matrizErrores[filas][column] = 0;
                    }
                }
                contador = 0; //reinicia contador, sigue otro numero.
            }
        }
        //Verificacion de filas sin repeticion

        for (byte numeros = 1; numeros < 10; numeros++) {// numeros del 1 al 10
            for (byte filas = 0; filas < 9; filas++) {
                for (byte column = 0; column < 9; column++) {
                    if (matriz[column][filas] == numeros) {
                        contador++;
                        if (contador == 2) {
                            matrizErrores[column][filas] = -2;
                            matrizErrores[error2[0]][error2[1]] = -2;
                            return matrizErrores;
                        } else {
                            error2[0] = column;
                            error2[1] = filas;
                        }
                    }
                }
                for (byte column = 0; column < 9; column++) {
                    if (matrizErrores[column][filas] == -2) {
                        matrizErrores[column][filas] = 0;
                    }
                }
                contador = 0; //reinicia contador, sigue otro numero.
            }
        }

        //Verificacion de regiones sin repeticion
        for (byte filExter = 0; filExter < 9; filExter += 3) {// matriz exterior
            for (byte colExter = 0; colExter < 9; colExter += 3) {// matriz exterior
                for (byte numeros = 1; numeros < 10; numeros++) {// numeros del 1 al 10
                    for (byte filInter = 0; filInter < 3; filInter++) {// matriz interior o de regiones
                        for (byte colInter = 0; colInter < 3; colInter++) {// matriz interior o de regiones
                            if (matriz[filInter + filExter][colInter + colExter] == numeros) {
                                contador++;
                                if (contador == 2) {
                                    matrizErrores[filInter + filExter][colInter + colExter] = -3;
                                    matrizErrores[error2[0]][error2[1]] = -3;
                                    return matrizErrores;
                                } else {
                                    error2[0] = filInter + filExter;
                                    error2[1] = colInter + colExter;
                                }
                            }
                        }
                    }
                    for (byte filInter = 0; filInter < 3; filInter++) {// matriz interior o de regiones
                        for (byte colInter = 0; colInter < 3; colInter++) {// matriz interior o de regiones
                            if (matrizErrores[filInter + filExter][colInter + colExter] == -3) {
                                matrizErrores[filInter + filExter][colInter + colExter] = 0;
                            }
                        }
                    }
                    contador = 0;
                }
            }
        }
        return matrizErrores; //se devuelve la matriz de errores
    }
}
