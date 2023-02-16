package modelos;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "almacen")
public class Almacen {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column (name= "idAlmacen")
    private int id;
    
    @Column(name= "Ubicacion")
    private String ciudad;
    
	@OneToMany(mappedBy = "almacen", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<Item> listaItems= new ArrayList<>();
    
	public Almacen()  {

	}
	
	public Almacen(String ciudad)  {
		this.ciudad = ciudad;
	}
	
	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setListaItems(List<Item> listaItems) {
		this.listaItems = listaItems;
	}


	public List<Item> getListaItems() {
		return listaItems;
	}
	
    
	public String getCiudad() {
		return ciudad;
	}




	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}




	@Override
	public String toString() {
		return "Almacen [listaItems=" + listaItems + "]";
	}

}
