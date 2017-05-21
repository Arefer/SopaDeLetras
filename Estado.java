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
			}
		}
		
		return estadosGenerados;
	}
	/*
	Metodo para comprobar si dos estados son iguales (exceptuando el atributo estadoAnterior)
	Entrada: nada
	Salida: un booleano de acuerdo a la respuesta
	*/
	public boolean esIgual(Estado e2){
		if (this.i == e2.i && this.j == e2.j && this.letraActual == e2.letraActual && this.indiceActual == e2.indiceActual){
			return true;
		}else{
			return false;
		} 
	}

}