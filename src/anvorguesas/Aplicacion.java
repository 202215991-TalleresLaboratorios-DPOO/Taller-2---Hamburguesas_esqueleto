package anvorguesas;

public class Aplicacion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		public void ejecutarAplicacion()
		{
			System.out.println("Estadísticas sobre los Juegos Olímpicos\n");

			boolean continuar = true;
			while (continuar)
			{
				try
				{
					mostrarMenu();
					int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
					if (opcion_seleccionada == 1)
						ejecutarCargarAtletas();
					else if (opcion_seleccionada == 2 && calculadora != null)
						ejecutarAtletasPorAnio();
					else if (opcion_seleccionada == 3 && calculadora != null)
						ejecutarMedallasEnRango();
					else if (opcion_seleccionada == 4 && calculadora != null)
						ejecutarAtletasPorPais();
					else if (opcion_seleccionada == 5 && calculadora != null)
						ejecutarPaisConMasMedallistas();
					else if (opcion_seleccionada == 6 && calculadora != null)
						ejecutarMedallistasPorEvento();
					else if (opcion_seleccionada == 7 && calculadora != null)
						ejecutarAtletasConMasMedallasQue();
					else if (opcion_seleccionada == 8 && calculadora != null)
						ejecutarAtletaEstrella();
					else if (opcion_seleccionada == 9 && calculadora != null)
						ejecutarMejorPaisEnUnEvento();
					else if (opcion_seleccionada == 10 && calculadora != null)
						ejecutarTodoterreno();
					else if (opcion_seleccionada == 11 && calculadora != null)
						ejecutarMedallistasPorNacionYGenero();
					else if (opcion_seleccionada == 12 && calculadora != null)
						ejecutarPorcentajeMedallistas();
					else if (opcion_seleccionada == 13 && calculadora != null)
						ejecutarPaisAtleta();
					else if (opcion_seleccionada == 14)
					{
						System.out.println("Saliendo de la aplicación ...");
						continuar = false;
					}
					else if (calculadora == null)
					{
						System.out.println("Para poder ejecutar esta opción primero debe cargar un archivo de atletas.");
					}
					else
					{
						System.out.println("Por favor seleccione una opción válida.");
					}
				}
				catch (NumberFormatException e)
				{
					System.out.println("Debe seleccionar uno de los números de las opciones.");
				}
			}
		}
		
		
		Aplicacion consola = new Aplicacion();
		consola.ejecutarAplicacion();

	}

}
