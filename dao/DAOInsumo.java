//ejecutar java ProyectoOpalo.dao.DAOInsumo
//compilo normal ejecuto ProyectoOpalo.igu.IGUAplicacionMenu

package ProyectoOpalo.dao; //Pertenece a este paquete

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import javax.swing.JOptionPane;

import ProyectoOpalo.dto.DTOInsumo;

public class DAOInsumo{

	private Connection oConexion;
	private PreparedStatement oSentencia;
	private DTOInsumo oDTOInsumo;
	

	public DAOInsumo(){

		Connection oConexion = null;
		PreparedStatement oSentencia = null;

	}
	
	public Connection getConexion(){

		try{

			String sDriver = "com.mysql.cj.jdbc.Driver";

			Class.forName(sDriver);

			String sJdbcUrl = "jdbc:mysql://25.67.54.75:3306/ProyectoOpalo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
			String sUsuario ="proyecto";
			String sPassword = "hola";

			oConexion = DriverManager.getConnection(sJdbcUrl, sUsuario, sPassword);


		}catch(SQLException oExcepcionSQL){

			JOptionPane.showMessageDialog(null, "Error al establecer la conexi\u00F3n");
			oExcepcionSQL.printStackTrace();

		} catch (Exception oExcepcion) {

			oExcepcion.printStackTrace();

		} finally {

			return oConexion;

		}

	}//getConexion

	public void agregar(DTOInsumo oInsumo){

		int eExecucion;
		String sConsultaBuscar;
		String sConsultaInsertar;
		ResultSet oResultado;

		try{

			oConexion = getConexion();

			//Buscamos si ya existe ese insumo
			sConsultaBuscar =     "SELECT id_Insumo " 
                               	+ "FROM Insumo " 
                         		+ "WHERE unidadMedida = ? AND nombre = ? "
                                + "AND existenciaMinima  = ? AND existenciaMaxima = ? AND existenciaActual = ?;";

            oSentencia = oConexion.prepareStatement(sConsultaBuscar);

            oSentencia.setString(1, oInsumo.getUnidadMedida());
            oSentencia.setString(2, oInsumo.getNombre());
            oSentencia.setDouble(3, oInsumo.getExistenciaMinima());
            oSentencia.setDouble(4, oInsumo.getExistenciaMaxima());
            oSentencia.setDouble(5, oInsumo.getExistenciaActual());

            oResultado = oSentencia.executeQuery();

            //Si no existe ese insumo lo insertamos
            if(!oResultado.next()){

            	sConsultaInsertar =   "INSERT INTO Insumo (unidadMedida, nombre, existenciaMinima,  existenciaMaxima, existenciaActual) " 
                               		+ "VALUES(?, ?, ?, ?, ?);";

	            oSentencia = oConexion.prepareStatement(sConsultaInsertar);

	            oSentencia.setString(1, oInsumo.getUnidadMedida());
	            oSentencia.setString(2, oInsumo.getNombre());
	            oSentencia.setDouble(3, oInsumo.getExistenciaMinima());
	            oSentencia.setDouble(4, oInsumo.getExistenciaMaxima());
	            oSentencia.setDouble(5, oInsumo.getExistenciaActual());

	            eExecucion = oSentencia.executeUpdate();

	            if (eExecucion == 1) {

	            	JOptionPane.showMessageDialog(null, "Insumo registrado");

				} 

            } else {

            	JOptionPane.showMessageDialog(null, "Error. El insumo ya existe, ingresar datos nuevamente.");

            }

            

		} catch (SQLException oExcepcionSQL) {

				oExcepcionSQL.printStackTrace();

		} catch (Exception oExcepcion) {

				oExcepcion.printStackTrace();

		} finally {

			try {

				if (oConexion != null) {

				   oConexion.close();
				   
				} 

			} catch (SQLException oExcepcion){

				oExcepcion.printStackTrace();

			}

		}

		

	}//Fin agregarInsumo


	public DTOInsumo buscar(int eID){

		String sConsultaBuscar;
		ResultSet oResultado;
		oDTOInsumo = new DTOInsumo();

		try{

			oConexion = getConexion();

			sConsultaBuscar =     "SELECT * " 
                               	+ "FROM Insumo " 
                         		+ "WHERE id_Insumo = ? ";
                                

            oSentencia = oConexion.prepareStatement(sConsultaBuscar);

            oSentencia.setInt(1, eID);

            oResultado = oSentencia.executeQuery();

            
            if(oResultado.next()){

            	oDTOInsumo = new DTOInsumo(	  oResultado.getInt("id_Insumo"), 
            								  oResultado.getString("nombre"), 
            							 	  oResultado.getString("unidadMedida"), 
            							 	  oResultado.getFloat("existenciaActual"), 
            							 	  oResultado.getFloat("existenciaMinima"), 
            							 	  oResultado.getFloat("existenciaMaxima"));

            } else {

            	JOptionPane.showMessageDialog(null, "Error. El insumo no existe no existe, intentar con otro codigo.");

            }

            

		} catch (SQLException oExcepcionSQL) {

				oExcepcionSQL.printStackTrace();

		} catch (Exception oExcepcion) {

				oExcepcion.printStackTrace();

		} finally {

			try {

				if (oConexion != null) {

				   oConexion.close();
				   
				} 

			} catch (SQLException oExcepcion){

				oExcepcion.printStackTrace();

			}

		}

		return oDTOInsumo;

		
	}//Buscar

}

/*


+consultar(in eIdInsumo: int): void
+modificarInsumo(in eIdProducto: int): void
+mostrarDatosInsumo(): String
+getInsumo(): String
+getInsumoss():String
+buscarInsumo(in eIdProducto: int): int
+modificarExistencias(in CantidadModificar: float): void
*/