package br.eti.ljr.imagemcompare.control;

import java.awt.MouseInfo;

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
		// Setando posição X e Y
				if (Variaveis.x == 0 || Variaveis.y == 0) {

					if (e.getButton() == NativeMouseEvent.BUTTON1) {

						System.out.println("x: " + MouseInfo.getPointerInfo().getLocation().x + " y: "+ MouseInfo.getPointerInfo().getLocation().y);
						Variaveis.x = MouseInfo.getPointerInfo().getLocation().x;
						Variaveis.y = MouseInfo.getPointerInfo().getLocation().y;
					}
				}

				// Setando posição W e H
				if (Variaveis.x != 0 || Variaveis.y != 0) {
										
					if (e.getButton() == NativeMouseEvent.BUTTON2) {

						System.out.println("w: " + MouseInfo.getPointerInfo().getLocation().x + " h: "+ MouseInfo.getPointerInfo().getLocation().y);
						Variaveis.w = MouseInfo.getPointerInfo().getLocation().x;
						Variaveis.h = MouseInfo.getPointerInfo().getLocation().y;
						
						System.out.println("\nAperte F10 para EXE! (TIRE O MOUSE DA TELA!!!)");
					}

				}
		
	}

	@Override
	public void nativeMouseReleased(NativeMouseEvent e) {
		
	}

}
