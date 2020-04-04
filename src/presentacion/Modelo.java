package presentacion;

import logica.Sudoku;

public class Modelo {

    private VistaPrincipal ventanaInicial;
    private Sudoku sudoku;
    int matriz[][];

    public void iniciar() {
        pintarDatos();
        getVentanaInicial().setSize(485, 460);
        getVentanaInicial().setVisible(true);
    }

    public VistaPrincipal getVentanaInicial() {
        if (ventanaInicial == null) {
            ventanaInicial = new VistaPrincipal(this);
        }
        return ventanaInicial;
    }

    public void pintarDatos() {
        matriz = getSudoku().llamarTableroNuevo();
        String numero;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (matriz[i][j] != 0) { //Si es un numero pinta solo el numero
                    numero = "" + matriz[i][j]; 
                    getVentanaInicial().setLblCajon(j, i, numero);
                }else{ // si es 0 se coloca mouseListener 
                    getVentanaInicial().mouseLabel(j, i);
                }
            }
        }
    }

    public Sudoku getSudoku() {
        if (sudoku == null) {
            sudoku = new Sudoku();
        }

        return sudoku;
    }

    public void llenarTablero(String texto, int i, int j) { //Se coloca el numero que se recibe
        getVentanaInicial().getLblCajon(i, j).setText(texto);
       
    }

    public void calcularSudoku() {
        boolean respuesta;
        int[][] matrizErrores;
        matriz = getVentanaInicial().getLblMatriz(); //Se recibe la matriz
        getSudoku().setMatriz(matriz);//Se envia la matriz a la logica
        respuesta = getSudoku().verificarLleno(); //Verifica que este lleno el sudoku completamente
        if (respuesta == true) { //Si esta lleno lo muestra
            getVentanaInicial().fontLblResultado(true);
            getVentanaInicial().getLblResultado().setText("Resultado: ¡¡Ganaste!!");
        } else { //Si no esta lleno verifica que no haya errores
            matrizErrores = getSudoku().verificarSinLlenar(); //Verifica la matriz cuando faltan numeros
            for (int filas = 0; filas < 9; filas++) {
                for (int column = 0; column < 9; column++) { //Se recorre la matriz
                    if (matrizErrores[filas][column] == -1) { //Envia el tipo de error si es -1 columnas
                        getVentanaInicial().repintar(filas,column,-1);
                    }else{ 
                        if (matrizErrores[filas][column] == -2) { //Si es -2 Filas
                            getVentanaInicial().repintar(filas,column,-2);
                        }else{
                            if (matrizErrores[filas][column] == -3) { //si es -3 regiones
                                getVentanaInicial().repintar(filas,column,-3);
                            }else{
                                getVentanaInicial().repintar(filas,column,-4); // por defecto no se encontro errores, 
                            }                                                  // quita el fondo de error o lo deja como estaba
                        }
                    }
                }

            }
            getVentanaInicial().fontLblResultado(false); //Muestra que aún esta mal el error
            getVentanaInicial().getLblResultado().setText("Resultado:  AUN ESTA MAL");
        }
    }
}
