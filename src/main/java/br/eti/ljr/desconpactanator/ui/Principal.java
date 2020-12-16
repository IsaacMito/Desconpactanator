package br.eti.ljr.desconpactanator.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.eti.ljr.desconpactanator.engine.Desconpactar;
import br.eti.ljr.desconpactanator.engine.Excluir;
import br.eti.ljr.desconpactanator.exception.MensagemException;

public class Principal {

	private JFrame frame;
	private JTextField textDiretorio;
	private JButton btnBuscar;
	private JButton btnExtair;
	private JButton btnApagarZip;
	private JButton btnApagarPDF;
	private JTextField textPainel;

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Desconpactanator");
		frame.setBounds(100, 100, 465, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		textDiretorio = new JTextField();
		textDiretorio.setBounds(10, 11, 336, 20);
		frame.getContentPane().add(textDiretorio);
		textDiretorio.setColumns(10);

		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrir();
			}
		});
		btnBuscar.setBounds(353, 11, 86, 23);
		frame.getContentPane().add(btnBuscar);

		btnExtair = new JButton("Extrair");
		btnExtair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					Desconpactar.descompactar(textDiretorio.getText());
					JOptionPane.showMessageDialog(frame, "Finalizado!");
					
				} catch (MensagemException ex) {
					JOptionPane.showMessageDialog(frame, ex.getMessage());
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(frame, ex.getMessage());
				}
			}
		});
		btnExtair.setBounds(10, 41, 104, 23);
		frame.getContentPane().add(btnExtair);

		btnApagarZip = new JButton("Apagar ZIP");
		btnApagarZip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					Excluir.ecluir(textDiretorio.getText());
					JOptionPane.showMessageDialog(frame, "Finalizado!");
					
				} catch (MensagemException ex) {
					JOptionPane.showMessageDialog(frame, ex.getMessage());
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(frame, ex.getMessage());
				}
			}
		});
		btnApagarZip.setBounds(124, 41, 104, 23);
		frame.getContentPane().add(btnApagarZip);

		btnApagarPDF = new JButton("Apagar PDF");
		btnApagarPDF.setBounds(238, 41, 104, 23);
		frame.getContentPane().add(btnApagarPDF);
		
		textPainel = new JTextField();
		textPainel.setEditable(false);
		textPainel.setColumns(10);
		textPainel.setBounds(10, 96, 429, 155);
		frame.getContentPane().add(textPainel);
	}

	protected void abrir() {

		JFileChooser file = new JFileChooser();
		file.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		int i = file.showSaveDialog(null);
		if (i == 1) {
			textDiretorio.setText("");
			
			
			
		} else {
			File arquivo = file.getSelectedFile();
			textDiretorio.setText(arquivo.getPath());
		}
	}

	public void show() {
		frame.setVisible(true);
	}
}
