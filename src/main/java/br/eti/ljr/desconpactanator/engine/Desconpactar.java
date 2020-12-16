package br.eti.ljr.desconpactanator.engine;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;

import br.eti.ljr.desconpactanator.exception.MensagemException;

public class Desconpactar {

	public static void descompactar(String path) throws Exception {

		File pasta = new File(path);
		if (path.equals("")) {
			throw new MensagemException("Indique um diretorio!");
		}

		if (!pasta.isDirectory()) {
			throw new MensagemException("O caminho não é uma diretorio!");
		}

		for (File arquivo : pasta.listFiles()) {

			System.out.print(arquivo.getAbsolutePath());
			System.out.print("  ");

			if (arquivo.isDirectory()) {

				System.out.println(" diretorio entrando");
				descompactar(arquivo.getAbsolutePath());

			} else if (arquivo.getName().endsWith(".zip")) {
				System.out.println("Descompactando");
				desZip(arquivo, pasta);

			} else if (arquivo.getName().endsWith(".7z")) {

				System.out.println("Descompactando");
				des7z(arquivo, pasta);

			} else {
				System.out.println("Arquivo estranho");
			}
		}
	}

	private static void desZip(File arquivo, File pastaDestino) throws IOException {

		byte[] buffer = new byte[1024];

		FileInputStream fis = new FileInputStream(arquivo);
		BufferedInputStream bis = new BufferedInputStream(fis);
		ZipInputStream zis = new ZipInputStream(bis);

		ZipEntry zipEntry = zis.getNextEntry();
		
		while (zipEntry != null) {
			
			File newFile = new File(pastaDestino, zipEntry.getName());
			
			if (zipEntry.isDirectory()) {
				newFile.mkdirs();
			} else {
				
				FileOutputStream fos = new FileOutputStream(newFile);
				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.close();
			}
			zipEntry = zis.getNextEntry();
		}

		zis.close();
	}

	private static void des7z(File in, File destination) throws IOException {

		SevenZFile sevenZFile = new SevenZFile(in);
		SevenZArchiveEntry entry = sevenZFile.getNextEntry();

		while (entry  != null) {
			
			File curfile = new File(destination, entry.getName());
			
			if (entry.isDirectory()) {
				curfile.mkdirs();
			} else {
				FileOutputStream out = new FileOutputStream(curfile);
				byte[] content = new byte[(int) entry.getSize()];
				sevenZFile.read(content, 0, content.length);
				out.write(content);
				out.close();
			}
		
		}
		sevenZFile.close();
	}

}
