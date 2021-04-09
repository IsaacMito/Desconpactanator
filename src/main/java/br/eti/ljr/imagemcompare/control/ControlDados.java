package br.eti.ljr.imagemcompare.control;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import br.eti.ljr.imagemcompare.util.ImageActions;
import br.eti.ljr.imagemcompare.util.Variaveis;

public class ControlDados {

	private static final String PROP_NAME = "configuracao.properties";
	private static final String IMG_NAME = "imagemInicial.jpg";

	public static void gravar() throws IOException {

		Properties properties = new Properties();

		properties.setProperty("x", Variaveis.x + "");
		properties.setProperty("y", Variaveis.y + "");

		properties.setProperty("width", Variaveis.width + "");
		properties.setProperty("height", Variaveis.height + "");

		FileOutputStream fos = new FileOutputStream(PROP_NAME);
		properties.store(fos, "Configuracoes");
		fos.close();
		
		ImageActions.saveImage(Variaveis.imagem, IMG_NAME);
	}

	public static void ler() throws IOException {

		Properties properties = new Properties();

		FileInputStream fis = new FileInputStream(PROP_NAME);
		properties.load(fis);

		Variaveis.x = Integer.valueOf(properties.getProperty("x"));
		Variaveis.y = Integer.valueOf(properties.getProperty("y"));
		Variaveis.width = Integer.valueOf(properties.getProperty("width"));
		Variaveis.height = Integer.valueOf(properties.getProperty("height"));
		
		Variaveis.imagem = ImageActions.lerImg(IMG_NAME);
	}
}
