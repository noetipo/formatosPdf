package com.example.formatospdf.utils;

import com.example.formatospdf.dto.Verificador;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Anexo05 {

    public static ByteArrayOutputStream generatePdfStream(List<Verificador> verificadores) throws DocumentException, IOException {
        // Crear el documento con márgenes personalizados
        Document document = new Document(PageSize.A4, 60, 60, 40, 40);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, outputStream);
        document.open();

        // Agregar imagen (logo)
        addImage(document);

        // Fuentes
        Font titleFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
        Font subtitleFont = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
        Font normalFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);
        Font smallFont = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL);

        // Encabezado
        addParagraph(document, "ANEXO 05: PRUEBA HOL ", titleFont, Element.ALIGN_CENTER);
        document.add(Chunk.NEWLINE);
        addParagraph(document, "DECENIO DE LAS PERSONAS CON DISCAPACIDAD EN EL PERÚ", subtitleFont, Element.ALIGN_CENTER);
        addParagraph(document, "AÑO DE (COLOCAR EL AÑO QUE CORRESPONDA)", subtitleFont, Element.ALIGN_CENTER);

        // Espaciado
        document.add(Chunk.NEWLINE);

        // Carta número
        addParagraph(document, "Carta N°", normalFont, Element.ALIGN_LEFT);
        document.add(Chunk.NEWLINE);
        // Ciudad y Fecha
        addParagraph(document, "Ciudad y Fecha", normalFont, Element.ALIGN_LEFT);

        // Datos de la empresa
        document.add(Chunk.NEWLINE);
        addParagraph(document, "Razón Social / Apellidos y nombres", normalFont, Element.ALIGN_LEFT);
        addParagraph(document, "Tipo y Número de Documento", normalFont, Element.ALIGN_LEFT);
        addParagraph(document, "Domicilio", normalFont, Element.ALIGN_LEFT);
        addParagraph(document, "Apellidos y Nombres del Representante Legal", normalFont, Element.ALIGN_LEFT);
        addParagraph(document, "Tipo y Número de Documento de Identidad", normalFont, Element.ALIGN_LEFT);

        // Saludo
        addParagraphUnderlined(document, "Presente.-", normalFont);

        document.add(Chunk.NEWLINE);

        // Cuerpo de la carta
        Paragraph cuerpo = new Paragraph(
                "Por el presente nos dirigimos a ustedes en relación a nuestra Orden de Verificación N° ___________________________  de fecha______________  " +
                        "para comunicarles que se está ampliando el alcance del procedimiento de Verificación a los asegurados detallados en la siguiente tabla:", normalFont);
        cuerpo.setAlignment(Element.ALIGN_JUSTIFIED);
        document.add(cuerpo);
        document.add(Chunk.NEWLINE);

        // Tabla de Verificadores
        addVerificadoresTable(document, verificadores, normalFont);

        document.add(Chunk.NEWLINE);

        // Texto final
        addParagraph(document, "Cabe precisar que el personal verificador designado mediante la Orden de Verificación es competente para continuar llevando a cabo el procedimiento de Verificación de la condición de asegurados y de entidades empleadoras del hogar en su representada.", normalFont, Element.ALIGN_JUSTIFIED);

        // Firma
        document.add(Chunk.NEWLINE);
        addParagraph(document, "____________________________________________", normalFont, Element.ALIGN_CENTER);
        addParagraph(document, "Firma y Sello del Jefe de Unidad de Control de las Filtraciones u OSPE según el tipo de oficina", smallFont, Element.ALIGN_CENTER);

        // Cierre del documento
        document.close();
        return outputStream;
    }

    private static void addParagraph(Document document, String text, Font font, int alignment) throws DocumentException {
        Paragraph paragraph = new Paragraph(text, font);
        paragraph.setAlignment(alignment);
        document.add(paragraph);
    }

    private static void addParagraphUnderlined(Document document, String text, Font font) throws DocumentException {
        Chunk underlinedText = new Chunk(text, font);
        underlinedText.setUnderline(0.1f, -2f);  // Subrayado
        Paragraph paragraph = new Paragraph(underlinedText);
        document.add(paragraph);
    }

    private static void addVerificadoresTable(Document document, List<Verificador> verificadores, Font normalFont) throws DocumentException {
        PdfPTable table = new PdfPTable(4); // 4 columnas
        table.setWidthPercentage(100); // Ancho de la tabla

        // Configuración de tamaños de las columnas
        float[] columnWidths = {0.30f, 2f, 1.5f, 1f}; // Columna 1: 30%, Columna 2: 70%, las otras distribuidas según el tamaño restante
        table.setWidths(columnWidths);

        // Primera fila (Encabezados)
        addCellToTable(table, "N°", normalFont, Element.ALIGN_CENTER);
        addCellToTable(table, "Apellidos y Nombres", normalFont, Element.ALIGN_CENTER);
        addCellToTable(table, "Tipo y Número de Documento", normalFont, Element.ALIGN_CENTER);
        addCellToTable(table, "Período a Verificar", normalFont, Element.ALIGN_CENTER);

        // Llenar la tabla con los datos de la lista
        for (int i = 0; i < verificadores.size(); i++) {
            Verificador verificador = verificadores.get(i);
            addCellToTable(table, String.valueOf(i + 1), normalFont, Element.ALIGN_LEFT); // Columna 1
            addCellToTable(table, verificador.getNombre(), normalFont, Element.ALIGN_LEFT); // Columna 2
            addCellToTable(table, verificador.getDocumento(), normalFont, Element.ALIGN_LEFT); // Columna 3
            addCellToTable(table, "Desde el aa/mm", normalFont, Element.ALIGN_LEFT); // Columna 4
        }

        document.add(table);
    }

    private static void addCellToTable(PdfPTable table, String text, Font font, int alignment) {
        PdfPCell cell = new PdfPCell(new Paragraph(text, font));
        cell.setHorizontalAlignment(alignment);
        cell.setPadding(5f);  // Padding
        table.addCell(cell);
    }

    // Método para agregar una imagen (logo)
    private static void addImage(Document document) throws IOException, DocumentException {
        ClassPathResource imgFile = new ClassPathResource("static/img.png");
        try (InputStream imgStream = imgFile.getInputStream()) {
            Image image = Image.getInstance(imgStream.readAllBytes());
            image.scaleToFit(80, 80); // Escalar imagen
            image.setAbsolutePosition(60, 750); // Posicionar imagen en el encabezado
            document.add(image);
        }
    }
}
