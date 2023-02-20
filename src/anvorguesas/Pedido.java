package anvorguesas;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pedido {
	
	private static int numeroPedidos;
	private int idPedido;
	private String nombreCliente;
	private String direccionCliente;
	private List<Producto> itemsPedido;
	
	public Pedido(String nombreCliente, String direccionCliente) {
		this.nombreCliente = nombreCliente;
		this.direccionCliente = direccionCliente;
		itemsPedido = new ArrayList<>();
		idPedido = numeroPedidos;
		numeroPedidos++;
		
	}


	public int getIdpedido() {
		return idPedido;
		
	}
	
	public void agregarProducto(Producto nuevoItem) {
		itemsPedido.add(nuevoItem);
	}
	
	private int getPrecioNetoPedido() {
		int total=0;
		for (Producto producto:itemsPedido) {
			total+=producto.getPrecio();
		}
		return total;
		
	}
	private int getPrecioIvaPedido() {
		return (int)(getPrecioNetoPedido()*0.19);
		
	}
	private int getPrecioTotalPedido() {
		return getPrecioIvaPedido() + getPrecioNetoPedido();
		
	}
	private String generarTextoFactura() {
		String factura = "Pedido Numero " + idPedido+"\n";
		factura += "Cliente: " + nombreCliente+"\n";
		factura += "Dirección Cliente: " + direccionCliente+"\n\n";
		
		for (Producto producto:itemsPedido) {
			factura += producto.generarTextoFactura();
		}
		
		factura +="SubTotal"+" ".repeat(12)+"$"+getPrecioNetoPedido()+"\n";
		factura +="IVA"+" ".repeat(17)+"$"+getPrecioIvaPedido()+"\n";
		factura +="Total"+" ".repeat(15)+"$"+getPrecioTotalPedido()+"\n";
		
		return factura;
	}
	
	public void guardarFactura(File archivo) {
		 try {
			java.io.PrintWriter output = new java.io.PrintWriter(archivo);
			output.print(generarTextoFactura());
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		return Objects.equals(direccionCliente, other.direccionCliente)
				&& Objects.equals(itemsPedido, other.itemsPedido) && Objects.equals(nombreCliente, other.nombreCliente);
	}
}
