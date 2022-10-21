package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.ContenidoHTML;
import view.Ventana;

public class ControladorPrincipal implements ActionListener {
	private Ventana ventana;
	private ControladorArchivo controladorArchivo;
	private ControladorCorreos controladorCorreos;
	
	public ControladorPrincipal() {
		ventana = new Ventana();
		asignarListener();
	}
	
	public void asignarListener() {
        ventana.getCargar().addActionListener(this);
        ventana.getEnviar().addActionListener(this);
        ventana.getSalir().addActionListener(this);
    }

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==ventana.getCargar()) {
			controladorArchivo = new ControladorArchivo();
			System.out.println(controladorArchivo.getRuta());
		}
		if(e.getSource() == ventana.getSalir()) {
			ContenidoHTML h = new ContenidoHTML();
			System.out.println(h.contenido());
		}
		if(e.getSource()==ventana.getEnviar()) {
			controladorCorreos = new ControladorCorreos();
			controladorCorreos.enviarCorreos();
		}
	}
}
