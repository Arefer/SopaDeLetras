import java.util.ArrayList;
public class Estado{
	// Atributos
	Estado estadoAnterior;
	int i;
	int j;
	char letraActual;
	int indiceActual;

	// Métodos
	// Constructores
	public Estado(int i, int j, char letra, int indice){
		this.i = i;
		this.j = j;
		this.letraActual = letra;
		this.indiceActual = indice;
	}

	public Estado(int i, int j, char letra, int indice, Estado estadoAnterior){
		this.i = i;
		this.j = j;
		this.letraActual = letra;
		this.indiceActual = indice;
		this.estadoAnterior = estadoAnterior;
	}
	/*
	Metodo que genera nuevos estados a partir del propio estado
	Entrada: nada
	Salida: un arraylist de estados
	*/
	public ArrayList<Estado> buscarAdyacentes(String palabra, char[][] sopa){
		ArrayList<Estado> estadosGenerados = new ArrayList<Estado>();
		if(palabra.length() > this.indiceActual+1){
			System.out.println("Entré a comprobar si puedo generar estados");
			int siguienteIndice = this.indiceActual + 1;
			char siguienteLetra = palabra.charAt(siguienteIndice);
			int filasSopa = sopa.length;
			int columnasSopa = sopa[i].length;
			Estado estadoActual = new Estado(this.i, this.j, this.letraActual,
					this.indiceActual);

			/* 
			Buscar a la derecha
			Si no estoy en la ultima columna y siguienteletra coicide con 
			lo que estoy buscando
			*/
			if(this.j != columnasSopa-1 && sopa[this.i][this.j+1] == siguienteLetra){
				Estado estadoGenerado = new Estado(this.i, this.j+1, siguienteLetra, siguienteIndice,
					estadoActual); 
				// Agrego el estado generado a la lista de estados generados
				estadosGenerados.add(estadoGenerado);
				System.out.println("Estoy en la palabra " + palabra + ". Letra " + palabra.charAt(this.indiceActual) + ". Indice " + this.indiceActual);
				System.out.println("Generé un estado hacia la derecha con letra " + siguienteLetra + ". Indice " + siguienteIndice);
			}

			/*
			Buscar a la izquierda si no estoy en la primera columna y siguienteLetra
			coindice con lo que estoy buscando
			*/
			if(this.j != 0 && sopa[this.i][this.j-1] == siguienteLetra){
				Estado estadoGenerado = new Estado(this.i, this.j-1, siguienteLetra, siguienteIndice,
					estadoActual);
				// Agrego el estado generado a la lista de estados generados
				estadosGenerados.add(estadoGenerado);
				System.out.println("Estoy en la palabra " + palabra + ". Letra " + palabra.charAt(this.indiceActual) + ". Indice " + this.indiceActual);
				System.out.println("Generé un estado hacia la izquierda con letra " + siguienteLetra + ". Indice " + siguienteIndice);
			}
			/*
			Buscar arriba si no estoy en la primera fila y siguienteLetra coincide
			con lo que estoy buscando
			*/
			if(this.i != 0 && sopa[this.i-1][this.j] == siguienteLetra){
				Estado estadoGenerado = new Estado(this.i-1, this.j, siguienteLetra, siguienteIndice,
					estadoActual);
				// Agrego el estado generado a la lista de estados generados
				estadosGenerados.add(estadoGenerado);
				System.out.println("Estoy en la palabra " + palabra + ". Letra " + palabra.charAt(this.indiceActual) + ". Indice " + this.indiceActual);
				System.out.println("Generé un estado hacia arriba con letra " + siguienteLetra + ". Indice " + siguienteIndice);
			}

			/*
			Buscar abajo si no estoy en la'ultima fila y siguienteLetra coincide
			con lo que estoy buscando
			*/
			if(this.i != filasSopa-1 && sopa[this.i+1][this.j] == siguienteLetra){
				Estado estadoGenerado = new Estado(this.i+1, this.j, siguienteLetra, siguienteIndice,
					estadoActual);
				// Agrego el estado generado a la lista de estados generados
				estadosGenerados.add(estadoGenerado);
				System.out.println("Estoy en la palabra " + palabra + ". Letra " + palabra.charAt(this.indiceActual) + ". Indice " + this.indiceActual);
				System.out.println("Generé un estado hacia abajo con letra " + siguienteLetra + ". Indice " + siguienteIndice);
			}
		}
		
		return estadosGenerados;
	}

}