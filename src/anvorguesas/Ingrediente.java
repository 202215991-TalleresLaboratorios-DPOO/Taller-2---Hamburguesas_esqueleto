package anvorguesas;

import java.util.Objects;

public class Ingrediente {
	
	private String nombre;
	private int costoAdicional;
	
	public Ingrediente(String nombre, int costoAdicional) {
		this.nombre = nombre;
		this.costoAdicional = costoAdicional;
	}

	public String getNombre() {
		return nombre;
		
	}
	
	public int getCostoAdicional() {
		return costoAdicional;
			
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ingrediente other = (Ingrediente) obj;
		return costoAdicional == other.costoAdicional && Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "Ingrediente [nombre=" + nombre + ", costoAdicional=" + costoAdicional + "]";
	}

}
