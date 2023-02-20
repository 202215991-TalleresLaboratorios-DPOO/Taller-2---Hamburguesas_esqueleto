package anvorguesas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductoAjustado implements Producto {
	
	private ProductoMenu base;
	private List<Ingrediente> agregados;
	private List<Ingrediente> eliminados;
	
	public ProductoAjustado(ProductoMenu base) {
		this.base = base;
		agregados = new ArrayList<>();
		eliminados = new ArrayList<>();
	}
	
	public void agregarIngrediente(Ingrediente ingrediente) {
		boolean agregado = agregados.contains(ingrediente);
		boolean eliminado = eliminados.contains(ingrediente);
		
		if (!agregado) {
			agregados.add(ingrediente);
		}
		
		if (eliminado) {
			eliminados.remove(ingrediente);
		}
	}
	
	public void eliminarIngrediente(Ingrediente ingrediente) {
		boolean agregado = agregados.contains(ingrediente);
		boolean eliminado = eliminados.contains(ingrediente);
		
		if (!eliminado) {
			eliminados.add(ingrediente);
		}
		if (agregado) {
			agregados.remove(ingrediente);
		}
		
	}

	@Override
	public int getPrecio() {
		int total = 0;
		total += base.getPrecio();
		
		for (Ingrediente ingrediente:agregados) {
			total+=ingrediente.getCostoAdicional();
		}
		return total;
	}

	@Override
	public String getNombre() {
		return base.getNombre();
	}

	@Override
	public String generarTextoFactura() {
		String factura;
		int tamaño = getNombre().length();
		int pos = getNombre().indexOf(" ", 16);
		
		if (tamaño>20 && pos>=16) {
			factura=getNombre().substring(0, pos) +"\t".repeat(5-(pos/5))+"$"+getPrecio()+"\n";
			factura+=getNombre().substring(pos)+"\n";
		}else {
			factura=getNombre()+ "\t".repeat(5-(tamaño/5))+"$"+getPrecio()+"\n";
		}
		
		if (agregados.size()>0) {
		
			factura += "Adicionales:\n";
			
			for (Ingrediente ingrediente:agregados) {
				factura+="  - "+ingrediente.getNombre()+"\n";
			}
		}
		
		if (eliminados.size()>0) {
			factura += "Eliminado:\n";
			
			for (Ingrediente ingrediente:eliminados) {
				factura+="  - "+ingrediente.getNombre()+"\n";
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
		ProductoAjustado other = (ProductoAjustado) obj;
		return Objects.equals(agregados, other.agregados) && Objects.equals(base, other.base)
				&& Objects.equals(eliminados, other.eliminados);
	}
	
	
}
