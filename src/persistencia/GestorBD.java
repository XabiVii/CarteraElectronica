package persistencia;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import domain.Usuario;



public class GestorBD {
	
	//lo de dentro del resource
		protected static String DRIVER_NAME;  
		protected static String DATABASE_FILE;
		protected static String CONNECTION_STRING;
		
		public GestorBD() {		
			try {
				Properties connectionProperties = new Properties();
				connectionProperties.load(new FileReader("resources/parametros.properties"));
				
				DRIVER_NAME = connectionProperties.getProperty("DRIVER_NAME");
				DATABASE_FILE = connectionProperties.getProperty("DATABASE_FILE");
				CONNECTION_STRING = connectionProperties.getProperty("CONNECTION_STRING") + DATABASE_FILE;
				
				//Cargar el diver SQLite
				Class.forName(DRIVER_NAME);
			} catch (Exception ex) {
				System.err.format("\n* Error al cargar el driver de BBDD: %s", ex.getMessage());
				ex.printStackTrace();
			}
		}
		public void crearUsuario() {
	        try (Connection con = DriverManager.getConnection(CONNECTION_STRING)) {
	            String sql = """
	                CREATE TABLE IF NOT EXISTS USUARIO (
	                    ID INTEGER PRIMARY KEY AUTOINCREMENT,
	                    NOMBRE TEXT NOT NULL,
	                    APELLIDO TEXT NOT NULL,
	                    CORREO TEXT NOT NULL,
	                    PASSWORD TEXT NOT NULL,
	                    FECHA_NACIMIENTO TEXT
	                );
	            """;

	            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
	                pstmt.execute();
	                System.out.println("-> Se ha creado la tabla USUARIO");
	            }
	        } catch (Exception ex) {
	            System.err.println("* Error al crear la BBDD: " + ex.getMessage());
	            ex.printStackTrace();
	        }
	    }
		
		public void insertarUsuario(Usuario usuario) {
			//Se abre la conexión y se obtiene el Statement
			try (Connection con = DriverManager.getConnection(CONNECTION_STRING)) {
				//Se define la plantilla de la sentencia SQL
				String sql = "INSERT INTO USUARIO (NOMBRE, APELLIDO, CORREO, PASSWORD, FECHA_NACIMIENTO) VALUES (?, ?, ?, ?, ?);";
				
				try (PreparedStatement pstmt = con.prepareStatement(sql)) {
					pstmt.setString(1, usuario.getNombre());
					pstmt.setString(2, usuario.getApellido());
					pstmt.setString(3, usuario.getCorreoElectronico());
					pstmt.setString(4, usuario.getContrasena());
					pstmt.setString(5, usuario.getFechaNacimiento().toString());
					pstmt.executeUpdate();
					System.out.println("-> Usuario insertado: " + usuario);
				}
					
				
				
			} catch (Exception ex) {
				System.err.format("\n* Error al insertar datos de la BBDD: %s", ex.getMessage());
				ex.printStackTrace();						
			}
		}
			
		public List<Usuario> obtenerUsuario() {
			List<Usuario> usuarios = new ArrayList<>();
			
			//Se abre la conexión y se obtiene el Statement
			try (Connection con = DriverManager.getConnection(CONNECTION_STRING)) {
				String sql = "SELECT * FROM USUARIO";
				try (PreparedStatement pstmt = con.prepareStatement(sql); 
					ResultSet rsltst = pstmt.executeQuery()) {
						while (rsltst.next()) {
							Usuario usuario = 
									new Usuario(rsltst.getString("NOMBRE"),
											rsltst.getString("APELLIDO"),
											rsltst.getString("CORREO"), 
											rsltst.getString("PASSWORD"), 
											String.valueOf(rsltst.getInt("ID")),
											LocalDate.parse(rsltst.getString("FECHA_NACIMIENTO"))
						            );
									usuarios.add(usuario);
						}
					}
				
					
			} catch (Exception ex) {
				System.err.format("\n* Error al obtener datos de la BBDD: %s", ex.getMessage());
				ex.printStackTrace();						
			}
			return usuarios;		
			
			
}
