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

import domain.Operacion;
import domain.Usuario;



public class GestorBD {
	
	// Lo de dentro del resource
	
		protected static String DRIVER_NAME;  
		protected static String DB_FILE;
		protected static String CONNECTION_STRING;
		
		public GestorBD() {		
			try {
				Properties connectionProperties = new Properties();
				connectionProperties.load(new FileReader("resources/parametros.properties"));
				
				DRIVER_NAME = connectionProperties.getProperty("DRIVER_NAME");
				DB_FILE = connectionProperties.getProperty("DATABASE_FILE");
				CONNECTION_STRING = connectionProperties.getProperty("CONNECTION_STRING") + DB_FILE;
				
				// Cargar el diver SQLite
				
				Class.forName(DRIVER_NAME);
			} catch (Exception ex) {
				System.err.format("\n* Error al cargar el driver de BDD: %s", ex.getMessage());
				ex.printStackTrace();
			}
		}
		
		public void crearTablas() {
		    // SQL para crear la tabla USUARIO
		    String sqlUsuario = """
		        CREATE TABLE IF NOT EXISTS USUARIO (
		            ID INTEGER PRIMARY KEY,
		            NOMBRE TEXT NOT NULL,
		            APELLIDO TEXT NOT NULL,
		            CORREO TEXT NOT NULL UNIQUE,
		            PASSWORD TEXT NOT NULL,
		            FECHA_NACIMIENTO TEXT NOT NULL
		        );
		    """;

		    // SQL para crear la tabla OPERACION
		    String sqlOperacion = """
		        CREATE TABLE IF NOT EXISTS OPERACION (
		            ID INTEGER PRIMARY KEY,
		            TIPOOPERACION TEXT NOT NULL,
		            CANTIDAD REAL NOT NULL,
		            FECHA TEXT NOT NULL,
		            DESCRIPCION TEXT,
		            METODOPAGO TEXT NOT NULL,
		            TIPOPAGO TEXT,
		            BALANCE REAL,
		            ID_USUARIO INTEGER NOT NULL,
		            FOREIGN KEY (ID_USUARIO) REFERENCES USUARIO(ID) ON DELETE CASCADE
		        );
		    """;

		    try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		         PreparedStatement pStmtUsuario = con.prepareStatement(sqlUsuario);
		         PreparedStatement pStmtOperacion = con.prepareStatement(sqlOperacion)) {

		        pStmtUsuario.execute();
		        System.out.println("Tabla USUARIO creada o ya existía.");

		        pStmtOperacion.execute();
		        System.out.println("Tabla OPERACION creada o ya existía.");

		    } catch (Exception ex) {
		        System.err.println("Error al crear las tablas: " + ex.getMessage());
		        ex.printStackTrace();
		    }
		}

		public void insertarUsuario(Usuario usuario) {
			
			// Se abre la conexión y se obtiene el Statement
			
			try (Connection con = DriverManager.getConnection(CONNECTION_STRING)) {
				
				// Se define la plantilla de la sentencia SQL
				
				String sql = "INSERT INTO USUARIO (NOMBRE, APELLIDO, CORREO, PASSWORD, FECHA_NACIMIENTO) VALUES (?, ?, ?, ?, ?);";
				
				try (PreparedStatement pstmt = con.prepareStatement(sql)) {
					pstmt.setString(1, usuario.getNombre());
					pstmt.setString(2, usuario.getApellido());
					pstmt.setString(3, usuario.getCorreoElectronico());
					pstmt.setString(4, usuario.getContrasena());
					pstmt.setString(5, usuario.getFechaNacimiento().toString());
					pstmt.executeUpdate();
					System.out.println("--> Usuario insertado: " + usuario);
					pstmt.close();
				}	
				
			} catch (Exception ex) {
				System.err.format("\n* Error al insertar datos de la BDD: %s", ex.getMessage());
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
						pstmt.close();
						}
					}
					
			} catch (Exception ex) {
				System.err.format("\n* Error al obtener datos de la BDD: %s", ex.getMessage());
				ex.printStackTrace();						
			}
			return usuarios;
		}
		
		public int getBalance() {
			 try (Connection con = DriverManager.getConnection(CONNECTION_STRING)) {
				String sql = "SELECT BALANCE FROM USUARIO";
				
				try(PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rsltst = pstmt.executeQuery()) {
					return rsltst.getInt("BALANCE");
				}
				
			} catch (Exception ex) {
				System.err.format("\n* Error al obtener el balance de la BDD", ex.getMessage());
				ex.printStackTrace();
				return 0;
			 }
		 }
		
		public void insertarOperacion(Operacion operacion) {
			try (Connection con = DriverManager.getConnection(CONNECTION_STRING)) {
				String sql = "INSERT INTO OPERACION (TIPOOPERACION, CANTIDAD, FECHA, DESCRIPCION, METODOPAGO, TIPOPAGO,  BALANCE) VALUES (?,?,?,?,?,?,?)";
				
				PreparedStatement pstmt = con.prepareStatement(sql);
				
				System.out.println("--> Insertando operación");
				
				// Metemos al insert los datos de la operacion
				
				pstmt.setString(1, operacion.getTipoOperacion());
				pstmt.setInt(2, operacion.getCantidad());
				pstmt.setString(3, operacion.getFecha());
				pstmt.setString(4, operacion.getDescripción());
				pstmt.setString(5, operacion.getMetodoPago());
				pstmt.setString(6, operacion.getTipoPago());
				
				
				// Calculamos el balance actual
				
				int balance = getBalance(); // Cambiar por getBalance()
				if (operacion.getTipoOperacion().toUpperCase() == "INGRESO") {
					balance+= operacion.getCantidad();
				} else {
					balance-= operacion.getCantidad();
				}
				
				// Metemos el balance en la BDD
				
				pstmt.setInt(7, balance);
				
				
				if (1 == pstmt.executeUpdate()) {
					System.out.println("\n --> Operación insertada ");
				} else {
					System.out.println("\n --> No se ha insertado la Operación");
				}
				
				pstmt.close();
				
			} catch (Exception ex) {
				System.err.format("\n* Error al insertar la operación  de la BDD: %s", ex.getMessage());
				ex.printStackTrace();
			}
		}
		
		
		public Operacion getOperacion() {
			Operacion operacion = new Operacion("", 1, "", "", "", "");
			return operacion;
		}
}