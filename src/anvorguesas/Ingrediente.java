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

	public String generarTextoFactura() {
		String factura;
		int tamaño = getNombre().length();
		int pos = getNombre().indexOf(" ", 12);
		
		if (tamaño>20 && pos>=12) {
			factura=getNombre().substring(0, pos) +" ".repeat(20-pos)+"$"+getCostoAdicional()+"\n";
			factura+=getNombre().substring(pos)+"\n";
		}else {
			factura=getNombre()+ " ".repeat(20-tamaño)+"$"+getCostoAdicional()+"\n";
		}

		return factura;
	}

}
