package domain;

import java.time.LocalDate;

public class Usuario {
	
	private String nombre;
    private String apellido;
    private int id;
    private String correoElectronico;
    private String contrasena;
    private LocalDate fechaNacimiento;

    public Usuario(String nombre, String apellido, String correoElectronico, String contrasena, int id, LocalDate fechaNacimiento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoElectronico = correoElectronico;
        this.contrasena = contrasena;
        this.id = id;
        this.fechaNacimiento = fechaNacimiento;
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

    public int getId() {
		return id;
	}

	public void setId(int id) {
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
    
	public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
	
    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", correoElectronico='" + correoElectronico + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                '}';
    }

}
