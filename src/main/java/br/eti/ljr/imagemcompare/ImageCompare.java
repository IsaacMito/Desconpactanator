package br.eti.ljr.imagemcompare;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jnativehook.GlobalScreen;

import com.github.romankh3.image.comparison.model.ImageComparisonResult;

import br.eti.ljr.imagemcompare.control.ControlDados;
import br.eti.ljr.imagemcompare.control.TecladoControl;
import br.eti.ljr.imagemcompare.util.Funcoes;
import br.eti.ljr.imagemcompare.util.ImageActions;
import br.eti.ljr.imagemcompare.util.Variaveis;

public class ImageCompare {

	private static final String PATH_XLSX = "C:\\Users\\DEV\\Desktop\\teste\\teste.xlsx";
	private static final String PATH_ARQUIVO = "C:\\Users\\DEV\\Desktop\\teste\\teste.xlsx";

	public static void main(String[] args) throws Exception {

		System.out.println("Iniciando...");

		System.out.println("Registrando NativeHook");
		GlobalScreen.registerNativeHook();
		System.out.println("Desligando log");
		Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
		logger.setUseParentHandlers(false);

		System.out.println("Registrando tecla de escape");
		GlobalScreen.addNativeKeyListener(new TecladoControl());

		System.out.println("Lendo dados");
		ControlDados.ler();

		// -------------------------------------------------------------------//

		System.out.println("Carregando o arquivo " + PATH_XLSX);
		FileInputStream fileExel = new FileInputStream(new File(PATH_XLSX));

		XSSFWorkbook workbook = new XSSFWorkbook(fileExel);
		XSSFSheet sheet = workbook.getSheetAt(0);

		Iterator<Row> rows = sheet.iterator();

		System.out.println("Pulando primeira linha");
		rows.next();

		int cont = 1;
		int total = sheet.getPhysicalNumberOfRows();
		while (rows.hasNext()) {
			System.out.println("Linha " + cont++ + " de " + total);
			XSSFRow row = (XSSFRow) rows.next();
			XSSFCell cellNome = row.getCell(0);
			XSSFCell cellOk = row.getCell(2);

			if (cellNome != null) {

				System.out.println("Tratando " + cellNome.toString());

				if (cellOk == null || !cellOk.toString().equals("ok")) {

					row.createCell(2).setCellValue("ok");
					FileOutputStream fileOut = new FileOutputStream(PATH_XLSX);
					workbook.write(fileOut);

					enviar(cellNome.toString());

				}

			}
		}

		workbook.close();

		System.out.println("FIM!");
		System.exit(0);
	}

	private static void enviar(String nome) throws Exception {

		Funcoes.move(100, 110);
		Funcoes.click3();

		Funcoes.copy(nome);
		Funcoes.cola();

		Thread.sleep(800);

		Funcoes.keyEvent(KeyEvent.VK_ENTER);

		BufferedImage actualImage = ImageActions.printTela(Variaveis.x, Variaveis.y, Variaveis.width, Variaveis.height);
		ImageComparisonResult imgResult = ImageActions.comparaImg(Variaveis.imagem, actualImage);

		if (imgResult.getDifferencePercent() > 1.0) {

			Funcoes.move(480, 690);

			Funcoes.click();

			Funcoes.move(480, 495);
			Funcoes.click();

			Funcoes.copy(PATH_ARQUIVO);
			Funcoes.cola();

			System.out.println("");
			System.out.println("Enviando arquivo");

			Funcoes.keyEvent(KeyEvent.VK_ENTER);

			Funcoes.move(1300, 600);

			Funcoes.delay(1000);

			Funcoes.click3();

			Thread.sleep(1000);
			Funcoes.click();
		}
	}
}
