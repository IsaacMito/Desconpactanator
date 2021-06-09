package br.eti.ljr.imagemcompare.control;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import br.eti.ljr.imagemcompare.util.ImageActions;
import br.eti.ljr.imagemcompare.util.Vars;

public class ControlDados {

	private static final String PROP_NAME = "configuracao.properties";
	private static final String IMG_NAME = "imagemInicial.jpg";

	public static void gravar() throws IOException {

		Properties properties = new Properties();

		properties.setProperty("x", Vars.x + "");
		properties.setProperty("y", Vars.y + "");

		properties.setProperty("width", Vars.width + "");
		properties.setProperty("height", Vars.height + "");

		FileOutputStream fos = new FileOutputStream(PROP_NAME);
		properties.store(fos, "Configuracoes");
		fos.close();
		
		ImageActions.saveImage(Vars.imagem, IMG_NAME);
	}

	public static void ler() throws IOException {

		Properties properties = new Properties();

		FileInputStream fis = new FileInputStream(PROP_NAME);
		properties.load(fis);

		Vars.x = Integer.valueOf(properties.getProperty("x"));
		Vars.y = Integer.valueOf(properties.getProperty("y"));
		Vars.width = Integer.valueOf(properties.getProperty("width"));
		Vars.height = Integer.valueOf(properties.getProperty("height"));
		
		Vars.imagem = ImageActions.lerImg(IMG_NAME);
	}
}
