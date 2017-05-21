import java.util.ArrayList;
public class Sopa{
	// Atributos
	char[][] sopa;
	ArrayList<Estado> ABIERTOS = new ArrayList<Estado>();
	ArrayList<Estado> CERRADOS = new ArrayList<Estado>();
	ArrayList<ArrayList<int[]>> COORD = new ArrayList<ArrayList<int[]>>(); 
	// Metodos 
	// Constructor
	public Sopa(char[][] matriz){
		this.sopa = matriz;
	}

	/*
	Metodo que busca si una palabra esta en la sopa
	Entrada: Estado inicial
	Salida: booleano que indica el resultado
	*/
	public boolean buscarPalabra(String palabra, Estado estadoInicial){
		// Añadir el estado inicial a ABIERTOS
		this.ABIERTOS.add(estadoInicial);
		while (this.ABIERTOS.size() > 0){
			int indiceActual = this.ABIERTOS.get(0).indiceActual;
			char letraActual = this.ABIERTOS.get(0).letraActual;
			int ultimoIndicePalabra = palabra.length()-1;
			// Verifico si es el estado final
			if(indiceActual != ultimoIndicePalabra){
				// Genero nuevos estados
				ArrayList<Estado> estadosGenerados;
				estadosGenerados = this.ABIERTOS.get(0).buscarAdyacentes(palabra, this.sopa);
				// Agrego los estadosGenerados a ABIERTOS si es que es un estado 
				// que no se ha analizado
				for(Estado estado: estadosGenerados){
					if(!(this.ABIERTOS.contains(estado) || this.CERRADOS.contains(estado))){
						this.ABIERTOS.add(estado);
					}
				}
				// Añado el estado actual a CERRADOS
				this.CERRADOS.add(this.ABIERTOS.get(0));
				// Elimino el estado actual de ABIERTOS
				this.ABIERTOS.remove(0);
			}else{
				return true;
			}	
		}
		return false;
	}
	/*
	Metodo que almacena las coordenadas de una palabra encontrada
	Entrada: palabra
	Salida: no posee
	*/
	public void almacenarCoordenadas(String palabra){
		ArrayList<int[]> coordenadas = new ArrayList<int[]>();
		ArrayList<Estado> estados = new ArrayList<Estado>();
		estados.add(this.ABIERTOS.get(0));
		while(estados.size() < palabra.length()){
			for(Estado estado: this.CERRADOS){
				if (estados.size() < palabra.length()){
					if(estado.esIgual(estados.get(0).estadoAnterior)){
						estados.add(0, estado);
					}
				}
			}
		}
		for (Estado estado: estados){
			int[] coord = new int[2];
 			coord[0] = estado.i;
			coord[1] = estado.j;
			coordenadas.add(coord);
		}

		this.COORD.add(coordenadas);
	}

	/*
	Metodo que busca todas las palabras recibidas dentro de la sopa
	Entrada: palabras a buscar
	Salida: una lista con las palabras encontradas
	*/ 
	public ArrayList<String> resolverSopa(String[] palabras){
		ArrayList<String> palabrasEncontradas = new ArrayList<String>();
		for(String palabra: palabras){
			// Para cada fila en la sopa
			for(int i=0; i<this.sopa.length; i++){
				// Para cada columna en la sopa
				for(int j=0; j<this.sopa[i].length; j++){
					if(palabra.charAt(0) == sopa[i][j]){
						Estado estadoInicial = new Estado(i, j, palabra.charAt(0), 0);
						if (buscarPalabra(palabra, estadoInicial)){
							palabrasEncontradas.add(palabra);
							almacenarCoordenadas(palabra);	
							this.ABIERTOS.clear();
							this.CERRADOS.clear();
						}
					}
				}
			}
		}
		return palabrasEncontradas;		
	}

	/*
	Metodo que indica las palabras no encontradas en la sopa
	Entrada: palabras a buscar, palabras encontradas
	Salida: palabras no encontradas
	*/
	public ArrayList<String> noEncontradas(String[] palabrasABuscar, ArrayList<String> encontradas){
		ArrayList<String> noEncontradas = new ArrayList<String>();
		for (String palabra: palabrasABuscar){
			if(!(encontradas.contains(palabra))){
				noEncontradas.add(palabra);
			}
		}
		return noEncontradas;
	} 

	/*
	Metodo para obtener las coordenadas de una palabra encontrada
	Entrada: la palabra (debe ser exclusivamente una palabra encontrada) y la lista de palabras encontradas
	Salida: un Arraylist de int[] con las coordenadas de la palabra 
	*/
	public ArrayList<int[]> obtenerCoordenadas(String palabra, ArrayList<String> encontradas){
		int indice = encontradas.indexOf(palabra);
		return this.COORD.get(indice);
	}
}