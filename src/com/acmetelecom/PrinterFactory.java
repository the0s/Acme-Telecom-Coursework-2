package com.acmetelecom;

import com.acmetelecom.utils.ConsolePrinter;

public class PrinterFactory {
	
	public static Printer createHtmlPrinter(){
		return HtmlPrinter.getInstance();
	}
	
	public static Printer createConsolePrinter(){
		return ConsolePrinter.getInstance();
	}
}
