package controller;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ControladorArchivo {
	private String ruta;

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public ControladorArchivo() {
		buscarArchivo();
	}

	public void buscarArchivo() {
		try {
			JFileChooser buscador = new JFileChooser("./");
			FileFilter filtroExcel = new FileNameExtensionFilter("Excel file", "xls", "xlsx");
			buscador.addChoosableFileFilter(filtroExcel);
			buscador.setFileFilter(filtroExcel);
			buscador.setAcceptAllFileFilterUsed(false);

			buscador.showOpenDialog(buscador);
			ruta = buscador.getSelectedFile().getAbsolutePath();
		} catch (Exception ex) {
		}
	}
}
