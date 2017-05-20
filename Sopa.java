import java.util.ArrayList;
public class Sopa{
	// Atributos
	char[][] sopa;
	ArrayList<Estado> ABIERTOS = new ArrayList<Estado>();
	ArrayList<Estado> CERRADOS = new ArrayList<Estado>();
	ArrayList<int[][]> COORD = new ArrayList<int[][]>(); 
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
	Metodo que obtiene las coordenadas de una palabra encontrada
	Entrada: palabra
	Salida: Arraylist de pares de coordenadas
	*/
	public ArrayList<int[]> obtenerCoordenadas(String palabra){
		ArrayList<int[]> coordenadas = new ArrayList<int[]>();
		ArrayList<Estado> estados = new ArrayList<Estado>();
		estados.add(this.ABIERTOS.get(0));
		/*int iAnterior = this.ABIERTOS.get(0).estadoAnterior.i;
		int jAnterior = this.ABIERTOS.get(0).estadoAnterior.j;
		int indiceAnterior = this.ABIERTOS.get(0).estadoAnterior.indiceActual;
		char letraAnterior = this.ABIERTOS.get(0).estadoAnterior.letraActual;
		System.out.println("i anterior: " + iAnterior + " j anterior: " + jAnterior);
		System.out.println("letra anterior: " + letraAnterior + " indice anterior: " + indiceAnterior);
		// System.out.println(iAnterior + jAnterior + letraAnterior + indiceAnterior); */
		//Estado prueba = new Estado(iAnterior, jAnterior, letraAnterior, indiceAnterior);
		/*if (prueba.i == iAnterior && prueba.j == jAnterior && prueba.indiceActual == indiceAnterior && prueba.letraActual == letraAnterior){
			System.out.println("Aasda");
		}
		if (prueba.esIgual(this.ABIERTOS.get(0).estadoAnterior)){
			System.out.println("Aasda");
		}*/
		
		while(estados.size() < palabra.length()){
			int iAnterior = estados.get(0).estadoAnterior.i;
			int jAnterior = estados.get(0).estadoAnterior.j;
			int indiceAnterior = estados.get(0).estadoAnterior.indiceActual;
			char letraAnterior = estados.get(0).estadoAnterior.letraActual;
			for(Estado estado: this.CERRADOS){
				if(estado.i == iAnterior && estado.j == jAnterior && estado.indiceActual == indiceAnterior && 
					estado.letraActual == letraAnterior){
					estados.add(0, estado);
					System.out.println("Añadi un estado");
				}
			}
		}
		for (Estado estado: estados){
			int[] coord = new int[2];
 			coord[0] = estado.i;
			coord[1] = estado.j;
			coordenadas.add(coord);
		}

		return coordenadas;
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
							System.out.println("Palabra encontrada: " + palabra);
							/*int ii = this.ABIERTOS.get(0).i;
							int jj = this.ABIERTOS.get(0).j;
							System.out.print("i: " + ii);
							System.out.println("j: " + jj);*/
							ArrayList<int[]> coord = obtenerCoordenadas(palabra);
							for (int[] coordenada: coord){
								System.out.print("i= " + coordenada[0]);
								System.out.println("j= " + coordenada[1]);
							}

							System.out.println("Hay " + this.CERRADOS.size() + " elementos en CERRADOS");
							
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
}