package modelos;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "factura")

public class Factura {
	@Id
	@Column(name = "numero_factura")
	private String numeroFactura;

	@Column(name = "estado_pago")
	private String estado;

	@ManyToOne()
	@JoinColumn(name = "DNI_cliente")
	private Cliente client;

	@OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Pedido> listaPedidos;

	public Factura(Cliente client, String numeroFactura) {
		this.listaPedidos = new ArrayList<>();
		this.numeroFactura = numeroFactura;
		this.client = client;
		this.estado=  "PENDIENTE DE PAGO";

	}

	public Factura() {

	}

	public String getNumeroFactura() {
		return numeroFactura;
	}

	public double subtotalPedidos() {
		double subtotal = 0;
		for (Pedido pedido : listaPedidos) {
			subtotal += pedido.subtotal();
		}
		return subtotal;
	}

	public List<Pedido> getListaPedidos() {
		return this.listaPedidos;
	}

	public void setListaPedidos(List<Pedido> listaPedidos) {
		this.listaPedidos = listaPedidos;
	}

	public double getIVA() {
		double IVA = subtotalPedidos() * 0.21;
		return IVA;
	}

	public double getTotal() {
		double total = subtotalPedidos() + getIVA();
		return total;
	}

	public String printPedidos() {
		String list = "";
		for (Pedido pedido : listaPedidos) {
			list = list + pedido.toString() + "\n";
		}
		return list;
	}

	


	public String getEstado() {
	
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public Cliente getClient() {
		return client;
	}

	public void setClient(Cliente client) {
		this.client = client;
	}

	public String PrintFactura() {
		String value = "";
		value = "\nCliente: " + this.client.getNombre() + " " + this.client.getApellidos() + "\n" + "Factura Nº: "
				+ this.numeroFactura + "\t(" + getEstado() + ")\n\n"

				+ String.format("%s %12s %10s %15s %16s", "Producto", "Unidades", "Precio", "Descuento", "Subtotal")
				+ "\n" + "------------------------------------------------------------------- \n" + printPedidos()
				+ "\n" + String.format("%s %56.2f", "Subtotal", subtotalPedidos()) + "\n"
				+ String.format("%s %57.2f", "IVA 21%", getIVA()) + "\n"
				+ String.format("%s %59.2f", "TOTAL", getTotal()) + "\n";

		return value;
	}

}
