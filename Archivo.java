import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Archivo {
    // Atributos
    String nombreArchivo;
    File archivo;
        
    // Constructor
    public Archivo(String nombre){
        this.nombreArchivo = nombre;
        try{
            this.archivo = new File(nombre);
        }catch(Exception e){
            System.out.println("Error al abrir o leer el archivo");
        }
    }
    /*
    Entrada: nada
    Salida: Un entero con la cantidad de lineas del archivo
    */
    public int contarLineas() throws IOException{
        FileReader lectorArchivo = new FileReader(this.archivo);
        BufferedReader lectorBuffer = new BufferedReader(lectorArchivo);

        
        int lineas = 0;
        while (lectorBuffer.readLine() != null){
            lineas++;
        }
        
        return lineas;
    }
    /*
    Entrada: nada
    Salida: Un array de Strings con las lineas del archivo
    */
    public String[] leer() throws IOException{
        int dim = contarLineas();
        String[] lineasArchivo = new String[dim];
        FileReader lectorArchivo = new FileReader(this.archivo);
        BufferedReader lectorBuffer = new BufferedReader(lectorArchivo);

        for (int linea=0; linea<dim; linea++){
            lineasArchivo[linea] = lectorBuffer.readLine();
        }

        return lineasArchivo;
    }
    /*
    Entrada: Array de lineas del archivo
    Salida: Array de chars
    */
    public char[][] aMatriz(String[] lineas){
        int filas = lineas.length;
        int columnas = 0;
        for (int q=0; q<lineas[0].length(); q++){
            if (lineas[0].charAt(q) != ' '){
                columnas++;
            }
        }
        char[][] matriz = new char[filas][columnas];
        for(int i=0; i<filas; i++){
            int j = 0;
            for(int k=0; j<columnas; k++){
                if (lineas[i].charAt(k) != ' '){
                    matriz[i][j] = lineas[i].charAt(k);
                    j++;
                }
            }
        }
        return matriz;
    }
    /*
    Metodo para escribir el resultado
    Entrada: palabras encontradas, palabras no encontradas
    Salida: booleano
    */
    public boolean escribirResultado(ArrayList<String> encontradas, ArrayList<String> noEncontradas)
    throws IOException{
        FileWriter fw = new FileWriter(this.archivo);
        BufferedWriter bw = new BufferedWriter(fw);
        
        bw.write("Palabras encontradas:\n");
        for (String palabra: encontradas){
               bw.write(palabra + "\n");
        }
        bw.write("\nPalabras no encontradas:\n");
        for (String noEncontrada: noEncontradas){
            bw.write(noEncontrada + "\n");
        }
        return true;
    }
}