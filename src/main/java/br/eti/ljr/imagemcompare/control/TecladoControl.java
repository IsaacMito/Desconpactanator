package br.eti.ljr.imagemcompare.control;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class TecladoControl implements NativeKeyListener {

	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {

		if (e.getKeyCode() == NativeKeyEvent.VC_KP_0) {
			System.out.println("Desistindo...");
			System.exit(0);
		}
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent e) {

	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent e) {
		
	}

}
