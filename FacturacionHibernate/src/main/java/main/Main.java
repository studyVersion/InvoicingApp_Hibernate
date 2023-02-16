package main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import modelos.PruebaComercio;

public class Main {

	public static void menu() {

		System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
		System.out.println("| 1. Registrar un cliente                            |");
		System.out.println("| 2. Incorporacion de nuevos productos al almacen    |");
		System.out.println("| 3. Reposicion de existencias de productos          |");
		System.out.println("| 4. Generacion de pedido con control de existencias |");
		System.out.println("| 5. Generacion de una factura                       |");
		System.out.println("| 6. Visualizar por pantalla una factura             |");
		System.out.println("| 7. Visualizar las facturas de un cliente           |");
		System.out.println("| 8. Guardar una factura en un archivo               |");
		System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");

	}// menu

	public static void main(String[] args) throws SQLException, IOException {
		Scanner sc = new Scanner(System.in);
		PruebaComercio comercio = new PruebaComercio();
		String nombreProducto = "";
		int mensajeConsola = 0;
		double precio = 0;
		int cantidad = 0;
		String dni = "";

		while (true) {
			try {

				menu();
				int opcion = Integer.valueOf(sc.nextLine());

				switch (opcion) {

				case 1:
				
					System.out.println("Introduzca el nombre:");
					String nombre = sc.nextLine();

					System.out.println("Introduzca los apellidos:");
					String apellidos = sc.nextLine();

					System.out.println("Introduzca el DNI:");
					dni = sc.nextLine();

					// mensajeConsola = comercio.registrarCliente(nombre, apellidos, dni);

					if (comercio.encontrarCliente(dni) != null) {
						System.out.println("Ya hay un cliente con este dni en el sistema.");

					} else {
						System.out.println("Descuento aplicable para dicho cliente:");
						int desc = Integer.valueOf(sc.nextLine());
						comercio.registrarCliente(nombre, apellidos, dni, desc);
						// comercio.encontrarCliente(dni).setDescuento(desc);
					}
					break;

				case 2:
					System.out.println("Introduzca el nombre de producto:");
					nombreProducto = sc.nextLine();
					System.out.println("Introduzca el precio:");
					precio = Double.valueOf(sc.nextLine());

					if (comercio.encontrarItem(nombreProducto) == null) {
						System.out.println("Introduzca la cantidad:");
						cantidad = Integer.valueOf(sc.nextLine());
						comercio.incorporarProducto(nombreProducto, precio, cantidad);
					} else {
						System.out.println("Producto ya existe.");
					}
					break;
					
				case 3:
					System.out.println("Introduzca el nombre:");
					nombreProducto = sc.nextLine();

					if (comercio.encontrarItem(nombreProducto) != null) {
						System.out.println("Introduzca la cantidad");
						cantidad = Integer.valueOf(sc.nextLine());
						comercio.reponerProducto(nombreProducto, cantidad);
						
					} else {
						System.out.println("Este producto no existe");
					}
					break;
					
				case 4:
					System.out.println("Introduzca el DNI del cliente:");
					dni = sc.nextLine();
					if (comercio.encontrarCliente(dni) != null) {
						System.out.println(comercio.muestrarProductos());
						
						System.out.println("Nombre del producto:");
						nombreProducto = sc.nextLine();
						
						System.out.println("La cantidad:");
						cantidad = Integer.valueOf(sc.nextLine());
						
						int value = comercio.generarPedido(nombreProducto, cantidad, dni);
						if (value < 0) {
							System.out.println("producto inexistente o agotado.");
						} else {
							System.out.println("Pedido realizado.");
						}
					} else {
						System.out.println(comercio.encontrarCliente(dni));
						System.out.println("No hay cliente con este dni.");
					}
					break;

				case 5:
					System.out.println("Introduzca el DNI:");
					dni = sc.nextLine();
					nombreProducto = comercio.generarFactura(dni);
					if (nombreProducto != null) {
						System.out.println("Factura Nº " + nombreProducto + " generada.\n");
					} else {
						System.out.println("No existe ese cliente.");
					}

					break;

				case 6:
					System.out.println("Numero Factura:");
					nombreProducto = sc.nextLine();
					String factura = comercio.visualizarFactura(nombreProducto);
					if (factura != null) {
						System.out.println(comercio.visualizarFactura(nombreProducto));
					} else {
						System.out.println("No existe ninguna factura con ese numero");
					}
					break;
				case 7:
					System.out.println("Introduzca el DNI del cliente");
					dni = sc.nextLine();
					if (comercio.encontrarCliente(dni) != null) {
						System.out.println(" ______________________");
						System.out.println("|       FACTURAS       |");
						System.out.println("|----------------------|");

						System.out.println(comercio.facturasCliente(dni));
						System.out.println(" ______________________");
					} else {
						System.out.println("No existe ningun cliente con ese DNI");
					}
					break;
				case 8:
					System.out.println("Introduzca el Nº de factura:");
					String fac = sc.nextLine();
					mensajeConsola = comercio.guardarFactura(fac);
					if (mensajeConsola >= 0) {
						System.out.println("Factura generada con exito.");

					} else {
						System.out.println("No existe ninguna factura con este numero");
					}

				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

}
