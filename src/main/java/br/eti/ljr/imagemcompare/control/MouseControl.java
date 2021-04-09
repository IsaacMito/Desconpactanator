package br.eti.ljr.imagemcompare.control;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.io.IOException;

import org.jnativehook.GlobalScreen;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseListener;

import br.eti.ljr.imagemcompare.util.ImageActions;
import br.eti.ljr.imagemcompare.util.Variaveis;

public class MouseControl implements NativeMouseListener {

	@Override
	public void nativeMouseClicked(NativeMouseEvent e) {

	}

	@Override
	public void nativeMousePressed(NativeMouseEvent e) {

		if (e.getButton() == NativeMouseEvent.BUTTON1) {

			if (Variaveis.x == null) {
				
				System.out.println("Capturando primeira posicao");
				Variaveis.x = MouseInfo.getPointerInfo().getLocation().x;
				Variaveis.y = MouseInfo.getPointerInfo().getLocation().y;
				System.out.println("x: "+ MouseInfo.getPointerInfo().getLocation().x);
				System.out.println("y: "+ MouseInfo.getPointerInfo().getLocation().y);
			} else if (Variaveis.width == null) {
				
				System.out.println("Capturando segunda posicao");
				Variaveis.width = MouseInfo.getPointerInfo().getLocation().x - Variaveis.x;
				Variaveis.height = MouseInfo.getPointerInfo().getLocation().y - Variaveis.y;
				System.out.println("x: "+ MouseInfo.getPointerInfo().getLocation().x);
				System.out.println("y: "+ MouseInfo.getPointerInfo().getLocation().y);
				try {
					System.out.println("Capturar imagem da area selecionada");
					Variaveis.imagem = ImageActions.printTela(Variaveis.x, Variaveis.y, Variaveis.width, Variaveis.height);
					
					System.out.println("Gravando dados no disco");
					ControlDados.gravar();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (AWTException e1) {
					e1.printStackTrace();
				}

				System.out.println("Removendo ouvidor");
				GlobalScreen.removeNativeMouseListener(this);

				// TODO ver o que fazer
				System.exit(0);
			}
		}
	}

	@Override
	public void nativeMouseReleased(NativeMouseEvent e) {

	}

}
