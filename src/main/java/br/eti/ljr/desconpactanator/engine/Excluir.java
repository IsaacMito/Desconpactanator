package br.eti.ljr.desconpactanator.engine;

import java.io.File;

import br.eti.ljr.desconpactanator.exception.MensagemException;

public class Excluir {
	
	public static void ecluir(String diretorio) throws Exception {
		
		File pasta = new File(diretorio);
		
		if (diretorio.equals("")) {
			throw new MensagemException("Indique um diretorio!");
		}

		if (!pasta.isDirectory()) {
			throw new MensagemException("O caminho não é uma diretorio!");
		}
		
		for (File arquivo : pasta.listFiles()) {
			
			if (arquivo.isDirectory()) {
				ecluir(arquivo.getAbsolutePath());
				
			} else if(arquivo.getName().endsWith(".zip")) {
				
				File file = new File(arquivo.getPath()); 
				file.delete();
			} else if(arquivo.getName().endsWith(".7z")) {
				
				File file = new File(arquivo.getPath()); 
				file.delete();
			}
		}
	}
}
