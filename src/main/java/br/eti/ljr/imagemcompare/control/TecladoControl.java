package br.eti.ljr.imagemcompare.control;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import br.eti.ljr.imagemcompare.util.Execute;
import br.eti.ljr.imagemcompare.util.Variaveis;

public class TecladoControl implements NativeKeyListener {

	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent e) {

		// exutando
		if (Variaveis.x != 0 || Variaveis.y != 0) {
			if (Variaveis.w != 0 || Variaveis.h != 0) {
				if (e.getKeyCode() == NativeKeyEvent.VC_F10) {
					try {
						Execute.exe();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		}

		// Matando aplicação
		if (e.getKeyCode() == NativeKeyEvent.VC_0) {
			System.exit(0);
		}

	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent e) {
	}

}
