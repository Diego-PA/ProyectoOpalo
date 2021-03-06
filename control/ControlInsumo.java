package ProyectoOpalo.control;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import ProyectoOpalo.igu.IGUInsumo;
import ProyectoOpalo.dao.DAOInsumo;
import ProyectoOpalo.dto.DTOInsumo;

public class ControlInsumo implements ActionListener{

	private DTOInsumo oDTO;
	private IGUInsumo oIGU;
	private DAOInsumo oDAO;
	

	public ControlInsumo(IGUInsumo oIGU){

		this.oIGU = oIGU;

	}
/*
	public ControlInsumo(DAOInsumo oDAO){

		this.oDAO = oDAO;

	}

	public ControlInsumo(DTOInsumo oDTO){

		this.oDTO = oDTO;

	}*/

	public void actionPerformed(ActionEvent oEvento){

		oDAO = new DAOInsumo();

		switch(oEvento.getActionCommand()){

			case "btAgregar":
				oDTO = oIGU.leerDTO();

				oDAO.agregar(oDTO);

			break;

			case "btBuscar":
				oDTO = oDAO.buscar(oIGU.getID());
				oIGU.mostrarDTO(oDTO);

			break;
		}

	}


}