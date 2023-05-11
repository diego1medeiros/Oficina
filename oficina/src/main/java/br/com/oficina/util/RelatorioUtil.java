package br.com.oficina.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import javax.swing.JDialog;

import net.sf.jasperreports.engine.JRException;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

public class RelatorioUtil {

	public static void criarRelatorio(String pathJasper, List<?> listaRelatorio)  {
	
		
	try {
		JasperReport relatorio = JasperCompileManager.compileReport(
				pathJasper);
		JasperPrint relatorioPreenchido;
		relatorioPreenchido = JasperFillManager.fillReport(relatorio, null,
				new JRBeanCollectionDataSource(listaRelatorio));
	
	//JasperPrintManager .printReport(relatorioPreenchido, true);
	
	JDialog tela = new JDialog();
	tela.setSize(1000, 600);
	JRViewer painel = new JRViewer(relatorioPreenchido);
	tela.getContentPane().add(painel);
	tela.setVisible(true);
	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	
	public static void gerarArquivoPdf (String pathJasper,List<?> listaRelatorio, 	String saidaDoPdf)  {
		
		OutputStream outputStream;
		try {
			outputStream = new FileOutputStream(saidaDoPdf);
	
		JasperPrint relatorioPreenchido = JasperFillManager.fillReport(pathJasper, null,
				new JRBeanCollectionDataSource(listaRelatorio));
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setExporterInput(new SimpleExporterInput(relatorioPreenchido));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
		SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
		exporter.setConfiguration(conf);
		exporter.exportReport();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
