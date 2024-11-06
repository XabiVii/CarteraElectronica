package gui;

public class Usuario {
	private String nombre;
    private String apellido;
    private String id;
    private String correoElectronico;
    private String contrasena;
    private double saldo;

    public Usuario(String nombre, String apellido, String correoElectronico, String contrasena, String id) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoElectronico = correoElectronico;
        this.contrasena = contrasena;
        this.saldo = 0.0;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
    public String getApellido() {
		return apellido;
	}

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
    
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getCorreoElectronico() {
		return correoElectronico;
	}


	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public double getSaldo() {
        return saldo;
    }
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
    
    @Override
    public String toString() {
        return "Usuario{" +
                "nombre= " + nombre + '\'' +
                ", apellido= " + apellido + '\'' +
                ", correoElectronico= " + correoElectronico + '\'' +
                ", saldo= " + saldo +
                '}';
    }

}
