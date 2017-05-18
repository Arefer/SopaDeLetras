import java.io.IOException;

public class Principal{

    public static void main(String[] args) throws IOException {
        Archivo archivo = new Archivo("sopa.in");
        String[] lineasArchivo = archivo.leer();
        char[][] sopaDeLetras = archivo.aMatriz(lineasArchivo);
        for(char[] fila: sopa){
        	for (char caracter: fila){
        		System.

        Sopa sopa = new Sopa(sopaDeLetras);
        // String palabras = {"casa", "hipopotamo", "carruaje", "perrito"};
        boolean respuesta = sopa.buscarPalabra("perrito");
        System.out.println(respuesta);
    }
}