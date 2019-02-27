package es.test.test_controllers.file_generators;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@Named
@ViewScoped
public class FileGenerators implements Serializable {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("static-method")
	public StreamedContent getPdfExample() throws DocumentException {
		Document document = new Document();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PdfWriter.getInstance(document, outputStream);
		document.open();
		// Left
		Paragraph paragraph = new Paragraph("This is right aligned text");
		paragraph.setAlignment(Element.ALIGN_RIGHT);
		document.add(paragraph);
		// Centered
		paragraph = new Paragraph("This is centered text");
		paragraph.setAlignment(Element.ALIGN_CENTER);
		document.add(paragraph);
		// Left
		paragraph = new Paragraph("This is left aligned text");
		paragraph.setAlignment(Element.ALIGN_LEFT);
		document.add(paragraph);
		// Left with indentation
		paragraph = new Paragraph("This is left aligned text with indentation");
		paragraph.setAlignment(Element.ALIGN_LEFT);
		paragraph.setIndentationLeft(50);
		document.add(paragraph);

		document.close();
		ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
		DefaultStreamedContent streamedContent = new DefaultStreamedContent(inputStream, "application/pdf");
		streamedContent.setName("Example.pdf");
		return streamedContent;
	}
	
	@SuppressWarnings("static-method")
	public StreamedContent getPoiExample() throws IOException {
        try(XSSFWorkbook workbook = new XSSFWorkbook()) {
        	XSSFSheet sheet = workbook.createSheet("Test");
        	XSSFRow row = sheet.createRow(0);
        	row.createCell(0).setCellValue("Cell test");
        	row.createCell(1).setCellValue(123.2);
        	
        	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        	workbook.write(outputStream);
        	
        	ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        	DefaultStreamedContent streamedContent = new DefaultStreamedContent(inputStream, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        	streamedContent.setName("Example.xlsx");
        	return streamedContent;
        }
	}

}