import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Archivo {
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
    Salida: Un array de Strings con las lineas del archivo
    */
    public String[] leer() throws IOException{
        FileReader lectorArchivo = new FileReader(archivo);
        BufferedReader lectorBuffer = new BufferedReader(lectorArchivo);
        String[] lineasArchivo = null;
        int i = 0;
        while (lectorBuffer.readLine() != null){
            lineasArchivo[i] = lectorBuffer.readLine();
            i++;
        }
        return lineasArchivo;
    }
}