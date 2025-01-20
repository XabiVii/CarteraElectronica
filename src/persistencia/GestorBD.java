package persistencia;

import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.sql.*;

import domain.Operacion;
import domain.Usuario;

public class GestorBD {

	// Lo de dentro del resource

	protected static String DRIVER_NAME;
	protected static String DB_FILE;
	protected static String CONNECTION_STRING;
	@SuppressWarnings("unused")
	private static int id_Operacion = 1;
	private static int id_userActual = 1;
	
	@SuppressWarnings("unused")
	private Usuario actual;

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
			usuario.setId(obtenerIdUsuario(usuario));
		} catch (Exception ex) {
			System.err.format("\n* Error al insertar datos de la BDD: %s", ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	public int obtenerIdUsuario(Usuario usuario) {
		int id = -1;
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING)) {
			String sql = "SELECT ID FROM USUARIO WHERE CORREO=?";
			try (PreparedStatement pstmt = con.prepareStatement(sql)) {
					pstmt.setString(1, usuario.getCorreoElectronico());
					ResultSet rsltst = pstmt.executeQuery();
					id=rsltst.getInt("ID");
					pstmt.close();
				
			}

		} catch (Exception ex) {
			System.err.format("\n* Error al obtener ID de la BDD: %s", ex.getMessage());
			ex.printStackTrace();
		}
		return id;
	}

	public List<Usuario> obtenerUsuario() {
		List<Usuario> usuarios = new ArrayList<>();

		// Se abre la conexión y se obtiene el Statement

		try (Connection con = DriverManager.getConnection(CONNECTION_STRING)) {
			String sql = "SELECT * FROM USUARIO";
			try (PreparedStatement pstmt = con.prepareStatement(sql); ResultSet rsltst = pstmt.executeQuery()) {
				while (rsltst.next()) {
					Usuario usuario = new Usuario(rsltst.getString("NOMBRE"), rsltst.getString("APELLIDO"),
							rsltst.getString("CORREO"), rsltst.getString("PASSWORD"),
							rsltst.getInt("ID"), LocalDate.parse(rsltst.getString("FECHA_NACIMIENTO")));
					usuarios.add(usuario);
				}
				pstmt.close();
			}

		} catch (Exception ex) {
			System.err.format("\n* Error al obtener datos de la BDD: %s", ex.getMessage());
			ex.printStackTrace();
		}
		return usuarios;
	}

	public int getBalance() {
		int balance_tot = 0;
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING)) {
			String sql_in = "SELECT CANTIDAD FROM OPERACION WHERE TIPOOPERACION = 'INGRESO' AND ID_USUARIO = " + String.valueOf(id_userActual);
			String sql_gas = "SELECT CANTIDAD FROM OPERACION WHERE TIPOOPERACION = 'GASTO' AND ID_USUARIO = " + String.valueOf(id_userActual);

			try (PreparedStatement pstmt = con.prepareStatement(sql_in); ResultSet rsltst = pstmt.executeQuery()) {
				balance_tot += rsltst.getInt("CANTIDAD");
				System.out.println(balance_tot);
			}
			System.out.println(balance_tot);
			try (PreparedStatement pstmt = con.prepareStatement(sql_gas); ResultSet rsltst = pstmt.executeQuery()) {
				balance_tot -= rsltst.getInt("CANTIDAD");
			}
			return balance_tot;

		} catch (Exception ex) {
			System.err.format("\n* Error al obtener el balance de la BDD", ex.getMessage());
			ex.printStackTrace();
			return 0;
		}
	}
	
	public ArrayList<Integer> getBalance2() {
		ArrayList<Integer> resultado = new ArrayList<Integer>();
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING)) {
			String sql = "SELECT BALANCE FROM OPERACION WHERE ID_USUARIO = " + String.valueOf(id_userActual);
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rsltst = pstmt.executeQuery();
			while(rsltst.next()) {
				resultado.add(rsltst.getInt("BALANCE"));
			}
			return resultado;
		} catch (Exception e) {
			System.err.format("Error al obtener los datos", e.getMessage());
			e.printStackTrace();
			return resultado;
		}
	}

	
	public List<Operacion> getOperaciones(int id) {
		List<Operacion> operaciones = new ArrayList<>();
		String sql = "SELECT * FROM OPERACION WHERE ID_USUARIO=?";

		// Se abre la conexión y se crea el PreparedStatement con la sentencia SQL
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
				PreparedStatement pStmt = con.prepareStatement(sql)) {
				pStmt.setInt(1, id);
			// Se ejecuta la sentencia y se obtiene el ResultSet
			ResultSet rs = pStmt.executeQuery();
			Operacion ope;

			// Se recorre el ResultSet y se crean objetos
			while (rs.next()) {//String tipoOp, Integer cant, String fch, String desp, String metPago, String tipoPa
				ope = new Operacion(rs.getString("TIPOOPERACION"), rs.getInt("CANTIDAD"), rs.getString("FECHA"),
						rs.getString("DESCRIPCION"),rs.getString("METODOPAGO"),rs.getString("TIPOPAGO"));

				// Se inserta cada nuevo cliente en la lista de clientes
				operaciones.add(ope);
			}
	        
			rs.close();

		} catch (Exception ex) {
		}
		System.out.println(operaciones);
		System.out.println("operaciones recibidas con exito");
		return operaciones;
	}
	
	public List<Operacion> getOperaciones() {
		List<Operacion> operaciones = new ArrayList<>();
		String sql = "SELECT * FROM OPERACION";

		// Se abre la conexión y se crea el PreparedStatement con la sentencia SQL
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
				PreparedStatement pStmt = con.prepareStatement(sql)) {
			// Se ejecuta la sentencia y se obtiene el ResultSet
			ResultSet rs = pStmt.executeQuery();
			Operacion ope;

			// Se recorre el ResultSet y se crean objetos
			while (rs.next()) {//String tipoOp, Integer cant, String fch, String desp, String metPago, String tipoPa
				ope = new Operacion(rs.getString("TIPOOPERACION"), rs.getInt("CANTIDAD"), rs.getString("FECHA"),
						rs.getString("DESCRIPCION"),rs.getString("METODOPAGO"),rs.getString("TIPOPAGO"));

				// Se inserta cada nuevo cliente en la lista de clientes
				operaciones.add(ope);
			}
	        
			rs.close();

		} catch (Exception ex) {
		}
		System.out.println("operaciones recibidas con exito");
		return operaciones;
	}

	public void insertarOperacion(Operacion operacion) {
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING)) {// corregir id usuario
			String sql = "INSERT INTO OPERACION (TIPOOPERACION, CANTIDAD, FECHA, DESCRIPCION, METODOPAGO, TIPOPAGO,  BALANCE,ID_USUARIO) VALUES (?,?,?,?,?,?,?,?)";

			PreparedStatement pstmt = con.prepareStatement(sql);

			System.out.println("--> Insertando operación");

			// Metemos al insert los datos de la operacion

			// pstmt.setInt(1, id_Operacion);
			// id_Operacion+= 1;
			pstmt.setString(1, operacion.getTipoOperacion());
			pstmt.setInt(2, operacion.getCantidad());
			pstmt.setString(3, operacion.getFecha());
			pstmt.setString(4, operacion.getDescripción());
			pstmt.setString(5, operacion.getMetodoPago());
			pstmt.setString(6, operacion.getTipoPago());
			pstmt.setInt(8, id_userActual);

			// Calculamos el balance actual

			int balance = getBalance(); // Cambiar por getBalance()
			if (operacion.getTipoOperacion().toUpperCase() == "INGRESO") {
				balance += operacion.getCantidad();
			} else {
				balance -= operacion.getCantidad();
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
	
	public double getMediaGastos() {
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING)) {
			String sql = "SELECT AVG(CANTIDAD) AS CANTIDAD FROM OPERACION WHERE TIPOOPERACION = 'GASTO' AND ID_USUARIO = " + String.valueOf(id_userActual);
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			double media = rs.getInt("CANTIDAD");
			
			return media;
		} catch (Exception e) {
			System.out.println("Ha habido un Error");
			return 0;
		}
	}
	
	public double getMediaIngresos() {
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING)) {
			String sql = "SELECT AVG(CANTIDAD) AS CANTIDAD FROM OPERACION WHERE TIPOOPERACION = 'INGRESO' AND ID_USUARIO = " + id_userActual;
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			double media = rs.getInt("CANTIDAD");
			
			return media;
		} catch (Exception e) {
			System.out.println("Ha habido un Error");
			return 0;
		}
	}
	
	public void cambiarUsuario(int id) {
		id_userActual=id;
	}
}