package anvorguesas;

import java.util.Objects;

public class ProductoMenu implements Producto {
	
	private String nombre;
	private int precioBase;
	
	
	public ProductoMenu(String nombre, int precioBase) {
		this.nombre = nombre;
		this.precioBase = precioBase;
	}

	@Override
	public int getPrecio() {
		return precioBase;
	}

	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public String generarTextoFactura() {
		String factura;
		int tamaño = getNombre().length();
		int pos = getNombre().indexOf(" ", 12);
		
		if (tamaño>20 && pos>=12) {
			factura=getNombre().substring(0, pos) +" ".repeat(20-pos)+"$"+getPrecio()+"\n";
			factura+=getNombre().substring(pos)+"\n";
		}else {
			factura=getNombre()+ " ".repeat(20-tamaño)+"$"+getPrecio()+"\n";
		}

		return factura;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductoMenu other = (ProductoMenu) obj;
		return Objects.equals(nombre, other.nombre) && precioBase == other.precioBase;
	}
	

}
