package modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "pedido")
public class Pedido {

	
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "numero_factura")
	private Factura factura;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nombre_producto")
	private Producto product;

	@Column(name = "Unidades")
	private int unidad;

	@Transient
	private int desc;

	public Pedido(Producto producto, int unidad) {
		this.product = producto;
		this.unidad = unidad;

	}

	public Pedido() {

	}

	
	
	public Producto getProduct() {
		return product;
	}

	public void setProduct(Producto product) {
		this.product = product;
	}

	public int getUnidad() {
		return unidad;
	}

	public void setUnidad(int unidad) {
		this.unidad += unidad;

	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}
    
	public int getDesc() {
		if(factura != null) {
			desc = factura.getClient().getDescuento();
		}
		return desc;
	}

	public void setDesc(int desc) {
		this.desc = desc;
	}
	
	public double subtotal() {
		double discuento = this.product.getPrecio() * this.unidad * getDesc() / 100;

		return this.product.getPrecio() * this.unidad - discuento;
	}
	
	
	@Override
	public String toString() {
		int a = 16 - this.product.getNombre().length();
		return String.format("%s%" + a + "d %14.2f %13d %19.2f", this.product.getNombre(), this.unidad,
				this.product.getPrecio(), getDesc(), subtotal());
	}
}
