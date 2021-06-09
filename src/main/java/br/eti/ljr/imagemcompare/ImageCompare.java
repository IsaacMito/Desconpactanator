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
import br.eti.ljr.imagemcompare.util.Macro;
import br.eti.ljr.imagemcompare.util.ImageActions;
import br.eti.ljr.imagemcompare.util.Vars;

public class ImageCompare {

	private static final String PATH_XLSX = "C:\\Users\\DEV\\Desktop\\Envio\\ClientesAsaNorte.xlsx";
	private static final String PATH_ARQUIVO = "C:\\Users\\DEV\\Desktop\\teste\\teste.xlsx";
	private static final String TEXTO = "*CALCE PERFEITO – DIA DOS NAMORADOS*\r\n"
			+ "\r\n"
			+ "Pensando no presente perfeito \r\n"
			+ "para o Dia dos Namorados❣️\r\n"
			+ "\r\n"
			+ "Aqui temos *desconto* \r\n"
			+ "progressivos de até *50%*.\r\n"
			+ "Isso mesmo *LEVA 4 PARES e PAGA 2*\r\n"
			+ "\r\n"
			+ "Confira os novos modelos de sandálias, sapatos, \r\n"
			+ "chinelos e tênis - Usaflex, Opanken e Democrata.\r\n"
			+ "Modelos ortopédicos e hiper confortáveis \r\n"
			+ "\r\n"
			+ "Conheça as *vantagens* de comprar na Calce👠👡🛍\r\n"
			+ "✅ Frete grátis, nas compras acima de R$ 200;\r\n"
			+ "✅ Loja especializada em calçados que aliviam dores;\r\n"
			+ "✅ Maior diversidade de Sandálias Ortopédicas de Brasília;\r\n"
			+ "✅ Faça suas compras pelo site, zap ou loja física; \r\n"
			+ "\r\n"
			+ "\r\n"
			+ "302 Sul – Asa Sul – 61992473148\r\n"
			+ "105 Norte – Asa Norte - 61992487937\r\n"
			+ "\r\n"
			+ "Ou acesse *nosso catálogo virtual:* https://wa.me/c/556192473148\r\n"
			+ "\r\n"
			+ "*Para maiores informações, consulte o regulamento de nossa campanha";

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
			XSSFCell cellNome = row.getCell(1);
			XSSFCell cellOk = row.getCell(2);

			if (cellNome != null) {

				System.out.println("Tratando " + cellNome.toString());

				if (cellOk == null || !cellOk.toString().equals("ok")) {

					row.createCell(2).setCellValue("ok");
					FileOutputStream fileOut = new FileOutputStream(PATH_XLSX);
					workbook.write(fileOut);
					
					String nome = getNumFormat(cellNome.toString());
					enviar(nome);

				}

			}
		}

		workbook.close();

		System.out.println("FIM!");
		System.exit(0);
	}

	private static String getNumFormat(String string) {
		
		string = string.replace(" ", "").replace("+55", "").replace("-", "").trim();
		
		string = string.substring(3);
		
		return string;
	}

	private static void enviar(String nome) throws Exception {

		Macro.move(100, 110);
		
		Macro.click(3);
		
		Macro.copy(nome);
		
		Macro.cola();
		
		Macro.delay(800);

		Macro.keyEvent(KeyEvent.VK_ENTER);

		BufferedImage actualImage = ImageActions.printTela(Vars.x, Vars.y, Vars.width, Vars.height);
		ImageComparisonResult imgResult = ImageActions.comparaImg(Vars.imagem, actualImage);

		if (imgResult.getDifferencePercent() > 2.0) {
			
			Macro.delay(500);
			
			Macro.copy(TEXTO);
			
			Macro.move(580, 690);
			
			Macro.click();
			
			Macro.cola();
			
			Macro.keyEvent(KeyEvent.VK_ENTER);
		
//			Funcoes.move(480, 495);
//			Funcoes.click();
//
//			Funcoes.copy(PATH_ARQUIVO);
//			Funcoes.cola();
//
//			System.out.println("");
//			System.out.println("Enviando arquivo");
//
//			Funcoes.keyEvent(KeyEvent.VK_ENTER);
//
//			Funcoes.move(1300, 600);
//
//			Funcoes.delay(1000);
//
//			Funcoes.click3();

			Macro.delay(1000);
		}
	}
	
}
