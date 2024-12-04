package domain;

public class Operacion {
	String tipoOperacion;
	Integer cantidad;
	String fecha;
	String descripción;
	String metodoPago;
	String tipoPago;
	
	// Vamos con los getters
	
	public String getTipoOperacion() {
		return tipoOperacion;
	}
	
	public Integer getCantidad() {
		return cantidad;
	}
	
	public String getFecha() {
		return fecha;
	}
	
	public String getDescripción() {
		return descripción;
	}
	
	public String getMetodoPago() {
		return metodoPago;
	}
	
	public String getTipoPago() {
		return tipoPago;
	}
	
	// Empezamos con los setters
	
	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}
	
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public void setDescripción(String descripción) {
		this.descripción = descripción;
	}
	
	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}
	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}
		
}

