package br.eti.ljr.imagemcompare.control;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.io.IOException;

import org.jnativehook.GlobalScreen;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseListener;

import br.eti.ljr.imagemcompare.util.ImageActions;
import br.eti.ljr.imagemcompare.util.Vars;

public class MouseControl implements NativeMouseListener {

	@Override
	public void nativeMouseClicked(NativeMouseEvent e) {

	}

	@Override
	public void nativeMousePressed(NativeMouseEvent e) {

		if (e.getButton() == NativeMouseEvent.BUTTON1) {

			if (Vars.x == null) {
				
				System.out.println("Capturando primeira posicao");
				Vars.x = MouseInfo.getPointerInfo().getLocation().x;
				Vars.y = MouseInfo.getPointerInfo().getLocation().y;
				System.out.println("x: "+ MouseInfo.getPointerInfo().getLocation().x);
				System.out.println("y: "+ MouseInfo.getPointerInfo().getLocation().y);
			} else if (Vars.width == null) {
				
				System.out.println("Capturando segunda posicao");
				Vars.width = MouseInfo.getPointerInfo().getLocation().x - Vars.x;
				Vars.height = MouseInfo.getPointerInfo().getLocation().y - Vars.y;
				System.out.println("x: "+ MouseInfo.getPointerInfo().getLocation().x);
				System.out.println("y: "+ MouseInfo.getPointerInfo().getLocation().y);
				try {
					System.out.println("Capturar imagem da area selecionada");
					Vars.imagem = ImageActions.printTela(Vars.x, Vars.y, Vars.width, Vars.height);
					
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
