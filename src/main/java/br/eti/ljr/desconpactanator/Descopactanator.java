package br.eti.ljr.desconpactanator;

import java.awt.EventQueue;

import br.eti.ljr.desconpactanator.ui.Principal;

public class Descopactanator {

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
