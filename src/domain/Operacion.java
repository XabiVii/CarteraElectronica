package domain;

public class Operacion {
	String tipoOperacion;
	Integer cantidad;
	String fecha;
	String descripcion;
	String metodoPago;
	String tipoPago;
	
	// Creamos el constructor 
	
	public Operacion(String tipoOp, Integer cant, String fch, String desp, String metPago, String tipoPa) {
		super();
		tipoOperacion = tipoOp;
		cantidad = cant;
		fecha = fch;
		descripcion = desp;
		metodoPago = metPago;
		tipoPago = tipoPa;
	}
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
		return descripcion;
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
		this.descripcion = descripción;
	}
	
	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}
	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}
		
}

