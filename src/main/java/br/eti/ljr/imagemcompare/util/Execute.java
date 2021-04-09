package br.eti.ljr.imagemcompare.util;

import java.awt.image.BufferedImage;

public class Execute {
	
	public static void exe() throws Exception{
		
		System.out.println("\nTirando print");
		
		//Setando imagem de comparação
		System.out.println("\nRecortando area area selecionada para comparação");
		BufferedImage bufferedImage = ImageActions.printTela();
		Variaveis.bufferedImageRec = ImageActions.printRecorte(bufferedImage);
		
		
		
		//ImageActions.comparaImg(null, null);
	}
}
