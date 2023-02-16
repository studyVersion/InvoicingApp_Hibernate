package modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "item")
public class Item extends Producto {

	@Column (name = "Existencias")
	private int existencias;
	
	@ManyToOne()
	@JoinColumn(name = "idAlmacen")
	private Almacen almacen = new Almacen();

	public Item(String nombreProducto, double precio, int existencias) {
		super(nombreProducto, precio);
		this.existencias = existencias;
		
	}
	
	public Item() {
	    super();
	}



	public Almacen getAlmacen() {
		return almacen;
	}


	public void setAlmacen(Almacen almacen) {
		this.almacen = almacen;
	}

	public int getExistencias() {
		return existencias;
	}
	
	public void setExistencias(int existencias) {
		this.existencias += existencias;
	}
    public void reduceExistencias(int existencias) {
    	this.existencias -= existencias;
    }

	@Override
	public String toString() {
		int a = 21 - getNombre().length();
		return String.format("%s %" + a + "s", getNombre(), this.existencias);

	}

}
