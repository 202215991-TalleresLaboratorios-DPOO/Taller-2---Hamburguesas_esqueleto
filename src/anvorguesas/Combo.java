package anvorguesas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Combo implements Producto{
	
	private double descuento;
	private String nombreCombo;
	private List<ProductoMenu> itemsCombo;
	
	public Combo(String nombre, double descuento) {
		nombreCombo = nombre;
		this.descuento = descuento;
		itemsCombo = new ArrayList<>();
		
	}
	

	public void agregarItemACombo(Producto itemCombo) {
		itemsCombo.add((ProductoMenu)itemCombo);
	}

	@Override
	public int getPrecio() {
		double total = 0;
		for (ProductoMenu producto:itemsCombo) {
			total += producto.getPrecio();
		}
		return (int) (total*(descuento+1));
	}

	@Override
	public String getNombre() {
		return nombreCombo;
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
		
		for (Producto producto:itemsCombo) {
			String nombreString = "  - " + producto.getNombre();
			tamaño = nombreString.length();
			pos = nombreString.indexOf(" ", 12);
			
			if (tamaño>16 && pos>=12) {
					factura+=nombreString.substring(0, pos)+"\n";
					factura+=nombreString.substring(pos)+"\n";
			}else {
				
				factura+=nombreString+"\n";
			}
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
		Combo other = (Combo) obj;
		return Double.doubleToLongBits(descuento) == Double.doubleToLongBits(other.descuento)
				&& Objects.equals(itemsCombo, other.itemsCombo) && Objects.equals(nombreCombo, other.nombreCombo);
	}
	
}
