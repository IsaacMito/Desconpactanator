package br.eti.ljr.imagemcompare;

import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;

import br.eti.ljr.imagemcompare.control.MouseControl;

public class ImageCompareConf {

	public static void main(String[] args) throws Exception {

		System.out.println("Iniciando...");

		System.out.println("Registrando NativeHook");
		System.out.println("Desligando log");
		GlobalScreen.registerNativeHook();
		Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
		logger.setUseParentHandlers(false);
		
		System.out.println("Selecione a area a ser capturada");
		GlobalScreen.addNativeMouseListener(new MouseControl());
	}

}
