import java.io.IOException;

public class SopaDeLetras {

    public static void main(String[] args) throws IOException {
        Archivo archivo = new Archivo("sopa.in");
        String[] lineasArchivo = archivo.leer();
        char[][] sopa = archivo.aMatriz(lineasArchivo);
        for(char[] fila: sopa){
        	for (char caracter: fila){
        		System.out.print(caracter);
        	}
        	System.out.println();
        }
    }
}