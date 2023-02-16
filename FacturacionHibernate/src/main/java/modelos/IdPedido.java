package modelos;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class IdPedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "numero_factura")
	private String numeroFactura;

	@Column(name = "nombre_producto")
	private String nombreProducto;
	

	
	public IdPedido() {
		super();
	}

	public String getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	
}