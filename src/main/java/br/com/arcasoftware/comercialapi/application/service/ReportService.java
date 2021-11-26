package br.com.arcasoftware.comercialapi.application.service;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class ReportService {

	public ByteArrayOutputStream impressaoPedido(List<?> dados) throws IOException, JRException {
		InputStream file = new ClassPathResource("relatorios/comercial/impressaoPedido.jrxml").getInputStream();
		JRPropertiesUtil.asBoolean("net.sf.jasperreports.awt.ignore.missing.font", true);
		JasperReport jasperReport = JasperCompileManager.compileReport(file);
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dados);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
		
		ByteArrayOutputStream finalReport = new ByteArrayOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, finalReport);

		return finalReport;
	}

}
