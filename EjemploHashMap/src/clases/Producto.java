package clases;

public class Producto {
	private int codigoProducto;
	private String nombreProducto;
	public Producto(int codigoProducto, String nombreProducto) {
		super();
		this.codigoProducto = codigoProducto;
		this.nombreProducto = nombreProducto;
	}
	public int getCodigoProducto() {
		return codigoProducto;
	}
	public void setCodigoProducto(int codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	@Override
	public String toString() {
		return "Producto [codigoProducto=" + codigoProducto
				+ ", nombreProducto=" + nombreProducto + "]";
	}	
	
}
