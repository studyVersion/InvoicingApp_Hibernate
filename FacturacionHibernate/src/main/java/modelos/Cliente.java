package modelos;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;



@Entity
@Table(name = "cliente")

public class Cliente {
	
    @Id  
    @Column (name = "Dni")
	private String dni; 
    @Column (name = "Nombre")
	private String nombre;   
    @Column (name = "Apellidos")
	private String apellidos;
    @Column (name = "Descuento")
	private int descuento;
	
	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Factura> facturas;
	
	
	public Cliente(String dni, String nombre, String apellidos, double descuento) {
		
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.descuento = 10;
		this.facturas = new ArrayList<>();
	}

	 

	public Cliente() {

	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public int getDescuento() {
		return descuento;
	}

	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}
	
	public List<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(Factura factura) {
		
		this.facturas.add(factura);

	}

}
