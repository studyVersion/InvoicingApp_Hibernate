package modelos;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Factory.Factory;

public class PruebaComercio {

	private Factory factoria = new Factory();

	private Almacen almacen;

	public PruebaComercio() throws SQLException, IOException {

		this.almacen = new Almacen("Zaragoza");

	}

	public void registrarCliente(String nombre, String apellidos, String dni, int descuento) {

		Cliente cliente = new Cliente();

		cliente.setNombre(nombre);
		cliente.setApellidos(apellidos);
		cliente.setDni(dni);
		cliente.setDescuento(descuento);

		factoria.clienteDao().create(cliente);

	}

	public Cliente encontrarCliente(String dni) {
		Cliente cliente = factoria.clienteDao().read(dni);
		return cliente;

	}

	public Item encontrarItem(String nombreProducto) {
		Item existencia = factoria.itemdao().read(nombreProducto);
		return existencia;

	}

	public short incorporarProducto(String nombreProducto, double precio, int cantidad) {

		short value = 0;
		if (encontrarItem(nombreProducto) == null) {
			Item item = new Item(nombreProducto, precio, cantidad);

			item.setAlmacen(almacen);
			almacen.getListaItems().add(item);
//			int a = item.getAlmacen().getId();
			if (factoria.almacenDao().read(1) != null) {
				factoria.almacenDao().update(almacen);
			} else {
				factoria.almacenDao().create(almacen);
			}

		} else {
			value = -1;
		}
		return value;

	}

	public short reponerProducto(String nombreProducto, int cantidad) throws SQLException, IOException {
		short value = 0;
		if (encontrarItem(nombreProducto) != null) {
			Item item = encontrarItem(nombreProducto);
			item.setExistencias(cantidad);
			factoria.itemdao().update(item);

		} else {
			value = -1;
		}

		return value;

	}

	public String muestrarProductos() {
		String value = "\n" + String.format("%s %15s", "Productos", "Cantidad") + "\n" + "--------------------------\n";
		for (Item item : factoria.itemdao().itemsList()) {
			int a = 21 - item.getNombre().length();
			value += String.format("%s %" + a + "s", item.getNombre(), item.getExistencias()) + "\n";
		}

		return value;

	}

	public String generarNumeroFactura() {
		SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmm");
		Date fecha = new Date();
		String numero = sdf.format(fecha);
		return numero;
	}

	private List<Pedido> listaPedidos = new ArrayList<>();

	public Producto encontrarProd(String nombreProducto) {
		Producto producto = factoria.productoDao().read(nombreProducto);
		return producto;

	}

	public short generarPedido(String nombreProducto, int cantidad, String dni) throws SQLException, IOException {
		short value = 0;
		Pedido pedido = null;

		Item item = encontrarItem(nombreProducto);
		Cliente cliente = encontrarCliente(dni);

		// Cliente cliente = encontrarCliente(dni);

		if (item != null && item.getExistencias() >= cantidad) {
			if (encontrarPedido(nombreProducto) != null) {
				encontrarPedido(nombreProducto).setUnidad(cantidad);
				System.out.println(listaPedidos);
			}

			else {

				pedido = new Pedido(item, cantidad);
				pedido.setDesc(cliente.getDescuento());
				listaPedidos.add(pedido);
				System.out.println(listaPedidos);
			}

		} else {
			value = -1;
		}
		return value;

	}

	public Pedido encontrarPedido(String nombreProducto) throws SQLException, IOException {
		Pedido pedido = null;

		for (Pedido pedidoRegistrado : this.listaPedidos) {
			if (pedidoRegistrado.getProduct().getNombre().equals(nombreProducto)) {
				pedido = pedidoRegistrado;
			}
		}

		return pedido;
	}

	public String generarFactura(String dni) throws SQLException, IOException {
		String value = null;

		Factura factura = null;
		Item item = null;

		Cliente cliente = encontrarCliente(dni);

		if (cliente != null) {
			factura = new Factura(cliente, generarNumeroFactura());
			factura.setListaPedidos(new ArrayList<>(listaPedidos));

			for (Pedido pedido : factura.getListaPedidos()) {
//				pedido = pedidoRegistrado;
				pedido.setFactura(factura);
				item = encontrarItem(pedido.getProduct().getNombre());
				item.reduceExistencias(pedido.getUnidad());
				factoria.itemdao().update(item);

			}
			factoria.facturaDao().create(factura);
			value = factura.getNumeroFactura();
			// listaFacturas.clear();
			listaPedidos.clear();

		}

		return value;

	}

	public String visualizarFactura(String numeroFactura) throws SQLException, IOException {
		String print = null;
		Factura factura = null;

		factura = factoria.facturaDao().read(numeroFactura);
		print = factura.PrintFactura();

		return print;

	}

	public String facturasCliente(String dni) throws SQLException, IOException {
		String value = "";
		Factura factura = null;

		value = factoria.facturaDao().facturaCliente(dni);

		return value;

	}

	public short guardarFactura(String numeroFactura) throws IOException, SQLException {
		FileWriter fichero = null;
		Factura factura = null;
		short value = -1;
		
		factura = factoria.facturaDao().read(numeroFactura);
		
		fichero = new FileWriter(factura.getNumeroFactura() + ".fac");
	    fichero.write(factura.PrintFactura());
		fichero.close();
		if (factura != null) {
				value = 0;
			}

		return value;
	}

}