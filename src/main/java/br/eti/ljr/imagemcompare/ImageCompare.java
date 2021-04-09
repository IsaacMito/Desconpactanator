package br.eti.ljr.imagemcompare;

import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Toolkit;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import br.eti.ljr.imagemcompare.control.MouseControl;
import br.eti.ljr.imagemcompare.control.TecladoControl;
import br.eti.ljr.imagemcompare.util.ImageActions;
import br.eti.ljr.imagemcompare.util.Variaveis;

public class ImageCompare {

	public static void main(String[] args) throws Exception {
		
		
		// Desativando logger
		GlobalScreen.registerNativeHook();
		Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
		logger.setUseParentHandlers(false);

		// Setando tamhanho da tela
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Variaveis.telaWidth = screenSize.getWidth();
		Variaveis.telaHeight = screenSize.getHeight();
		
		System.out.println("Esperando posições:    |     Aperte BUTTON1 = X,Y  Aperte BUTTON2 = W,H\n");
		
		TecladoControl tecladoControl = new TecladoControl();
		MouseControl mouseControl = new MouseControl();
		
		GlobalScreen.addNativeMouseListener(mouseControl);
		GlobalScreen.addNativeKeyListener(tecladoControl);

//       -------------------------------------------------------------------------------------------------------------------        //		

	}
}
