package Factory;

import dao.AlmacenDao;
import dao.ClienteDao;
import dao.FacturaDao;
import dao.ItemDao;
import dao.PedidoDao;
import dao.ProductoDao;


public class Factory {

	public Factory() {

	}

	public ClienteDao clienteDao() {
		ClienteDao cliente = new ClienteDao();
		return cliente;

	}

	public FacturaDao facturaDao() {
		FacturaDao facturadao = new FacturaDao();
		return facturadao;
	}

	public ItemDao itemdao() {
		ItemDao itemdao = new ItemDao();
		return itemdao;
	}

	public PedidoDao pedidoDao() {
		PedidoDao pedidodao = new PedidoDao();
		return pedidodao;
	}

	public ProductoDao productoDao() {
		ProductoDao productodao = new ProductoDao();
		return productodao;
	}
	public AlmacenDao almacenDao() {
		AlmacenDao almacendao = new AlmacenDao();
		return almacendao;
	}
}
