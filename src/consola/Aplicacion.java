package consola;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import anvorguesas.Combo;
import anvorguesas.Ingrediente;
import anvorguesas.ProductoAjustado;
import anvorguesas.ProductoMenu;
import anvorguesas.Restaurante;

public class Aplicacion {
	
	private Restaurante restaurante;
	private boolean continuar;
	
	
	public void ejecutarAplicacion() {
		
		restaurante = new Restaurante();
		continuar = true;
		
		File ingreFile = new File("./data/ingredientes.txt");
		File menuFile = new File("./data/menu.txt");
		File combosFile = new File("./data/combos.txt");
		
		restaurante.cargarInformacionRestaurante(ingreFile, menuFile, combosFile);
		
		while (continuar) {
			
			mostrarMenu();
			
			int opcionSeleccionada;
			
			try {
				opcionSeleccionada = Integer.parseInt(input("Seleccione una opción"));
				ejecutarOpcion(opcionSeleccionada);
				input("Presione 'Enter' para continuar");
			}
			catch (NumberFormatException e)
			{
				System.out.println("Debe seleccionar uno de los números de las opciones.");
			}
		
		}
	}
	
	public void ejecutarOpcion(int opcionSeleccionada){
		
		
		if ( opcionSeleccionada == 1) {
			ejecutarOpcion1();
		}
		else if ( opcionSeleccionada == 2) {
			ejecutarOpcion2();
		}
		else if ( opcionSeleccionada == 3 && restaurante.getPedidoEnCurso()!= null) {
			ejecutarOpcion3();
		}
			
		else if ( opcionSeleccionada == 4 && restaurante.getPedidoEnCurso()!= null) {
			ejecutarOpcion4();
		}
			
		else if ( opcionSeleccionada == 5) {
			ejecutarOpcion5();
		}
			
		else if ( opcionSeleccionada == 6) {
			System.out.println("\nVuelva pronto...");
			continuar = false;
		}
		else if(restaurante.getPedidoEnCurso()== null){
			System.out.println("\nPara ejecutar esta opcion primero debe iniciar un pedido");
		}
		else {
			System.out.println("Ingrese una opcion valida");
		}
			
		
		
	}
	
	private void ejecutarOpcion1() {
		System.out.println("\n\t\tMenú del Restaurante\n");
		
		System.out.println("1. Menú General");
		System.out.println("2. Combos");
		System.out.println("3. Ingredientes Adicionales");
		
		int opcionSeleccionada;
		
		try {
			opcionSeleccionada = Integer.parseInt(input("Seleccione una opción"));
			
			if (opcionSeleccionada == 1){
				mostrarMenuBase();
				
			}
			else if (opcionSeleccionada == 2) {
				mostrarCombos();
			}
			else if (opcionSeleccionada == 3) {
				mostrarIngredientes();
			}
		}
		catch (NumberFormatException e)
		{
			System.out.println("Debe seleccionar uno de los números de las opciones.");
		}
		
		
		

		
	}

	private void ejecutarOpcion2() {
		String nombre = input("\nIngrese su nombre");
		String direccion = input("Ingrese su dirección");
		
		restaurante.iniciarPedido(nombre, direccion);
		System.out.println("\nPedido Inciado con el Id " + restaurante.getPedidoEnCurso().getIdpedido());
	}

	private void ejecutarOpcion3() {		
		System.out.println("\n\t\tMenú del Restaurante\n");
		
		System.out.println("1. Menú General");
		System.out.println("2. Combos");
		
		int opcionSeleccionada;
		
		try {
			opcionSeleccionada = Integer.parseInt(input("Seleccione una opción"));
			
			if (opcionSeleccionada == 1){
				seleccionarProductoMenu();
			}
			else if (opcionSeleccionada == 2) {
				seleccionarProductoCombo();
			}else {
				System.out.println("Ingrese una opcion Valida");
			}
			
		}
		catch (NumberFormatException e)
		{
			System.out.println("Debe seleccionar uno de los números de las opciones.");
		}
		
	}
	
	private void seleccionarProductoMenu() {
		int indexMenu = 0;
		List<ProductoMenu> menuBase = mostrarMenuBase();
		try {
			indexMenu = Integer.parseInt(input("Seleccione una opción"));
			if (indexMenu > 0 && indexMenu <= menuBase.size()) {
				
				ProductoMenu productoMenu = menuBase.get(indexMenu-1);
				
				System.out.println("1. Agregar o eliminar ingredientes");
				System.out.println("2. Terminar");
				
				int opcionSeleccionada = Integer.parseInt(input("Seleccione una opción"));
				
				if (opcionSeleccionada == 1) 
					seleccionarIngrediente(productoMenu);
				else {
					restaurante.editarPedido(productoMenu);
					System.out.println("\n"+productoMenu.generarTextoFactura());
				}

				
			} else {
				System.out.println("Ingrese una opcion Valida");
			}
		}
		catch (NumberFormatException e){
			System.out.println("Debe seleccionar uno de los números de las opciones.");
		}
	
	}
	
	private void seleccionarProductoCombo() {
		int indexCombo = 0;
		List<Combo> combos = mostrarCombos();
		try {
			indexCombo = Integer.parseInt(input("Seleccione una opción"));
			if (indexCombo > 0 && indexCombo <= combos.size()) {
				
				Combo combo = combos.get(indexCombo-1);
				
				restaurante.editarPedido(combo);
				System.out.println("\n"+combo.generarTextoFactura());

				
			} else {
				System.out.println("Ingrese una opcion Valida");
			}
		}
		catch (NumberFormatException e){
			System.out.println("Debe seleccionar uno de los números de las opciones.");
		}
	}
	
	private void seleccionarIngrediente(ProductoMenu productoMenu) {
		int indexIngre = 0;
		ProductoAjustado productoAjustado = new ProductoAjustado(productoMenu);
		boolean editar = true;
		
		try {
			while (editar) {
				
				System.out.println("1. Agregar");
				System.out.println("2. Eliminar");
				
				
				int opcionSeleccionada = Integer.parseInt(input("Seleccione una opción"));
				
				if (opcionSeleccionada == 1) {
					List<Ingrediente> ingredientes = mostrarIngredientes();
					indexIngre = Integer.parseInt(input("Seleccione una opción"));
					System.out.println(indexIngre > 0 && indexIngre <= ingredientes.size());
					if (indexIngre > 0 && indexIngre <= ingredientes.size()) {
					
						Ingrediente ingrediente = ingredientes.get(indexIngre-1);
					
						restaurante.editarProducto(productoAjustado, ingrediente, true);
					}
				}else if (opcionSeleccionada == 2){
					List<Ingrediente> ingredientes = mostrarIngredientes();
					indexIngre = Integer.parseInt(input("Seleccione una opción"));
					if (indexIngre > 0 && indexIngre <= ingredientes.size()) {
					
						Ingrediente ingrediente = ingredientes.get(indexIngre-1);
					
						restaurante.editarProducto(productoAjustado, ingrediente, false);
					}
				}
				else {
					System.out.println("Opcion Invalida");
				}
				
				
				System.out.println("1. Agregar o eliminar ingredientes");
				System.out.println("2. Terminar");
				
				opcionSeleccionada = Integer.parseInt(input("Seleccione una opción"));
				
				if (opcionSeleccionada == 1)
					editar = true;	
				
				if (opcionSeleccionada == 2)
					editar = false;
				else 
					System.out.println("Opcion Invalida");
				
			}
			
		}
		catch (NumberFormatException e){
			System.out.println("Debe seleccionar uno de los números de las opciones.");
		}
		
		System.out.println("\n"+productoAjustado.generarTextoFactura());
		restaurante.editarPedido(productoAjustado);

	}
	
	private List<ProductoMenu> mostrarMenuBase() {
		List<ProductoMenu> menuBase = restaurante.getMenuBase();
		
		for (int i = 0; i < menuBase.size(); i++) {
			String nombre = menuBase.get(i).getNombre();
			int precio = menuBase.get(i).getPrecio();
			
			System.out.println((i+1)+". "+nombre+" = $"+precio);
		}
		return menuBase;
	}
	
	private List<Combo>  mostrarCombos() {
		List<Combo> combos = restaurante.getCombos();
		
		for (int i = 0; i < combos.size(); i++) {
			String nombre = combos.get(i).getNombre();
			int precio = combos.get(i).getPrecio();
			
			System.out.println((i+1)+". "+nombre+" = $"+precio);
		}
		return combos;
		
	}
	
	private List<Ingrediente> mostrarIngredientes() {
		List<Ingrediente> ingredientes = restaurante.getIngredientes();
		
		for (int i = 0; i < ingredientes.size(); i++) {
			String nombre = ingredientes.get(i).getNombre();
			int precio = ingredientes.get(i).getCostoAdicional();
			
			System.out.println((i+1)+". "+nombre+" = $"+precio);
		}
		return ingredientes;
	}
	
	
	private void ejecutarOpcion4() {
		try {
			restaurante.cerraryGuardarPedido();
			System.out.println("Factura Guardad");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void ejecutarOpcion5() {
		try {
			int pedido = Integer.parseInt(input("Ingrese el Id de su pedido"));
			
			String nameFileString = "./data/PedidoId-"+pedido+".txt";
			
			File archivoPedido = new File(nameFileString);
			restaurante.consultarPedido(archivoPedido);
		}
		catch (NumberFormatException e){
			System.out.println("Debe seleccionar uno de los números de las opciones.");
		}
		
		
	}

	private void mostrarMenu() {
		System.out.println("\nOpciones del Restaurante");
		System.out.println("1. Mostar Menú");
		System.out.println("2. Iniciar un pedido");
		System.out.println("3. Agregar un elemento a un pedido");
		System.out.println("4. Cerrar un pedido y guardar la factura");
		System.out.println("5. Consultar la información de un pedido dado su id");
		System.out.println("6. Salir");
		
	}

	public String input(String mensaje){
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
		
		Aplicacion consola = new Aplicacion();
		consola.ejecutarAplicacion();
		
		
			
	}

}
	


