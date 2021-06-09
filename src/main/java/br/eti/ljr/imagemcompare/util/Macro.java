package br.eti.ljr.imagemcompare.util;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Iterator;

public class Macro {

	private static Robot macro;
	private static final int DELAY = 150;

	static {
		try {
			macro = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	public static void delay(int deley) throws Exception {
		macro.delay(deley);
	}

	public static void move(int x, int y) throws Exception {

		macro.mouseMove(x, y);
		macro.delay(DELAY);
	}

	public static void click(int quantidade) throws Exception {
		for (int i = 1; i < quantidade; i++) {
			click();
		}
		macro.delay(DELAY);
	}

	public static void cola() throws Exception {

		macro.keyPress(KeyEvent.VK_CONTROL);
		macro.keyPress(KeyEvent.VK_V);
		macro.keyRelease(KeyEvent.VK_CONTROL);
		macro.keyRelease(KeyEvent.VK_V);

		macro.delay(DELAY);
	}

	public static void click() throws Exception {

		macro.mousePress(KeyEvent.BUTTON1_DOWN_MASK);
		macro.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);

		macro.delay(DELAY);
	}

	public static void keyEvent(int key) {
		macro.keyPress(key);
		macro.keyRelease(key);
		macro.delay(DELAY);
	}

	public static void copy(String text) {
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		StringSelection selection = new StringSelection(text);
		clipboard.setContents(selection, null);
	}
}
