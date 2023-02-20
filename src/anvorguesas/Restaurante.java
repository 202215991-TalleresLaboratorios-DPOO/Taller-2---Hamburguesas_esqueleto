package anvorguesas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Restaurante {
	private List<Combo> combos;
	private List<Pedido> pedidos;
	private Pedido pedidoEnCurso;
	private List<ProductoMenu> menuBase;
	private List<ProductoMenu> bebidas;
	private List<Ingrediente> ingredientes;
	
	public Restaurante() {
		pedidos = new ArrayList<>();
	}
	
	public void iniciarPedido(String nombreCliente,String direccionCliente) {
		if (pedidoEnCurso == null) {
			pedidoEnCurso = new Pedido(nombreCliente, direccionCliente);
		}else {
			System.out.println("Ya hay un pedido en curso");
		}
	}
	
	public void editarPedido(Producto nuevoProducto) {
		pedidoEnCurso.agregarProducto(nuevoProducto);
	}
	
	public void editarProducto(ProductoAjustado productoAjustado, Ingrediente ingrediente, boolean agregar) {
		if (agregar) 
			productoAjustado.agregarIngrediente(ingrediente);
		else 
			productoAjustado.eliminarIngrediente(ingrediente);
	}
	
	public void cerraryGuardarPedido() throws IOException {
		String nameFileString = "./data/PedidoId-"+pedidoEnCurso.getIdpedido()+".txt";
		
		File file = new File(nameFileString);
		file.createNewFile();
		pedidoEnCurso.guardarFactura(file);
		
		if (pedidos.contains(pedidoEnCurso)){
			System.out.println("Un pedido Similar ya habia sido guardado");
		}
		pedidos.add(pedidoEnCurso);
		pedidoEnCurso = null;
		
		
	}

	public Pedido getPedidoEnCurso() {
		return pedidoEnCurso;
	}

	public List<ProductoMenu> getMenuBase() {
		return menuBase;
	}
	
	public List<Combo> getCombos() {
		return combos;
	}
	
	public List<ProductoMenu> getBebidas() {
		return bebidas;
	}

	public List<Ingrediente> getIngredientes() {
		return ingredientes;
	}
	public String consultarPedido(File archivoPedido) {
		BufferedReader br;
		String archivoString="";
		try {
			br = new BufferedReader(new FileReader(archivoPedido));
			String linea = br.readLine();
			while (linea != null) {
				archivoString+=linea;
				linea = br.readLine();
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("El Pedido Buscado No existe");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return archivoString;
	}
			
			
	public void cargarInformacionRestaurante(File archivoIngredientes, File archivoMenu, File archivoCombos) {
		cargarIngredientes(archivoIngredientes);
		cargarMenu(archivoMenu);
		cargarCombos(archivoCombos);
	}
	
	private void cargarIngredientes(File archivoIngredientes) {
		BufferedReader br;
		
		
		try {
			br = new BufferedReader(new FileReader(archivoIngredientes));
			String linea = br.readLine();
			ingredientes = new ArrayList<>();
			
			while (linea != null) {
				String[] datos = linea.split(";");
				
				String nombre = datos[0];
				int precio = Integer.parseInt(datos[1]);
				
				Ingrediente ingrediente = new Ingrediente(nombre, precio);
				ingredientes.add(ingrediente);
				
				linea = br.readLine();
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void cargarMenu(File archivoMenu) {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(archivoMenu));
			String linea = br.readLine();
			menuBase = new ArrayList<>();
			while (linea != null) {
				String[] datos = linea.split(";");
				
				String nombre = datos[0];
				int precio = Integer.parseInt(datos[1]);
				
				ProductoMenu pMenu = new ProductoMenu(nombre, precio);
				menuBase.add(pMenu);
				
				linea = br.readLine();
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void cargarCombos(File archivoCombos) {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(archivoCombos));
			String linea = br.readLine();
			combos = new ArrayList<>();
			while (linea != null) {
				String[] datos = linea.split(";");
				
				String nombre = datos[0];
				double descuento = Double.parseDouble(datos[1].split("%")[0])/100;
				
				Combo combo = new Combo(nombre, descuento);
				
				for (int i = 2; i<5;i++) {
					int j = 0;
					while (j < menuBase.size()) {
						Producto temProducto = menuBase.get(j);
						if (datos[i].equals(temProducto.getNombre())) {
							combo.agregarItemACombo(temProducto);
							j = menuBase.size();
						}
						j++;
						
					}
				}
				
				combos.add(combo);
								
				linea = br.readLine();
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
