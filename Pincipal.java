import java.io.IOException;
import java.util.ArrayList;
public class Principal{

    public static void main(String[] args) throws IOException {
        Archivo archivoSopa = new Archivo("sopa.in");
        String[] lineasArchivo = archivoSopa.leer();
        char[][] sopaDeLetras = archivoSopa.aMatriz(lineasArchivo);
        System.out.println("Sopa:");
        for(char[] fila: sopaDeLetras){
        	for (char caracter: fila){
        		System.out.print(caracter + " ");
            }
            System.out.println();
        }
        System.out.println();
        
        Archivo archivoPalabras = new Archivo("palabras.in");
        String[] palabrasABuscar = archivoPalabras.leer();        
        Sopa sopa = new Sopa(sopaDeLetras);
        System.out.println("Palabras encontradas: ");
        ArrayList<String> encontradas = sopa.resolverSopa(palabrasABuscar);       
        for (String palabra: encontradas){
            System.out.println("- " + palabra);
        }
        System.out.println();
        System.out.println("Palabras no encontradas: ");
        ArrayList<String> noEncontradas = sopa.noEncontradas(palabrasABuscar, encontradas);
        for (String palabra: noEncontradas){
            System.out.println("- " + palabra);
        }
        Archivo respuesta = new Archivo("solucion.out");
        if (respuesta.escribirResultado(encontradas, noEncontradas, sopa)){
            System.out.println("Resultado escrito correctamente en 'solucion.out'");
        }
    }
}
