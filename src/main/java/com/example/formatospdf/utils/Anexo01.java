package com.example.formatospdf.utils;

import com.example.formatospdf.dto.Verificador;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Anexo01 {

    public static ByteArrayOutputStream generatePdfStream(List<Verificador> verificadores) throws DocumentException, IOException {
        // Crear el documento con márgenes personalizados: top 2.5 cm, left 3 cm, right 3 cm, bottom predeterminado (2.5 cm)
        Document document = new Document(PageSize.A4, 3 * 25, 3 * 25, 2.5f*15, 2.5f*15);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, outputStream);
        document.open();

        // Fuentes
        Font nameDocumentFont = getArialFont(10, Font.BOLD);
        Font nameYearboldFont = getArialFont(9, Font.BOLD);
        Font normalFont = getArialFont(12, Font.NORMAL);
        Font smallFont = getArialFont(9, Font.NORMAL);

        // Imagen desde el classpath
        addImage(document);

        // Encabezado
        addParagraph(document, "\"ANEXO 01: “CARTA DE PRESENTACIÓN DE VERIFICACIÓN\"", nameDocumentFont, Element.ALIGN_CENTER);
        document.add(Chunk.NEWLINE);
        document.add(new Paragraph(" ", new Font(Font.FontFamily.HELVETICA, 4f)));
        addParagraph(document, "\"DECENIO DE LAS PERSONAS CON DISCAPACIDAD EN EL PERÚ\"", nameYearboldFont, Element.ALIGN_CENTER);
        addParagraph(document, "\"AÑO DE (COLOCAR EL AÑO QUE CORRESPONDA)\"", nameYearboldFont, Element.ALIGN_CENTER);

        // Espaciado
        document.add(Chunk.NEWLINE);

        // Carta número
        addParagraph(document, "Carta N° ", normalFont);
        document.add(Chunk.NEWLINE);
        // Ciudad y Fecha
        addParagraph(document, "Ciudad y Fecha ", normalFont);

        // Información de la empresa
        document.add(Chunk.NEWLINE);
        addParagraph(document, "Razón Social / Apellidos y nombres ", normalFont);
        addParagraph(document, "Tipo y Número de Documento ", normalFont);
        addParagraph(document, "Domicilio ", normalFont);
        addParagraph(document, "Apellidos y Nombres del Representante Legal ", normalFont);
        addParagraph(document, "Tipo y Número de Documento de Identidad ", normalFont);

        // Saludo
        addParagraphUnderlined(document, "Presente.-", normalFont);

        document.add(Chunk.NEWLINE);

        // Cuerpo de la carta
        Paragraph cuerpo = new Paragraph(
                "Por el presente nos dirigimos a ustedes en relación a nuestra Orden de Verificación N°  " +
                        "de fecha ______________, con la finalidad de presentar al personal verificador que iniciará el procedimiento de " +
                        "verificación de ____ (colocar la verificación que se realizará).", normalFont);
        cuerpo.setAlignment(Element.ALIGN_JUSTIFIED);
        document.add(cuerpo);
        document.add(Chunk.createWhitespace("10"));
        // Crear y agregar tabla
        addVerificadoresTable(document, verificadores, smallFont, normalFont);

        document.add(Chunk.NEWLINE);

        // Información adicional
        addInfoAdicional(document, normalFont);

        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);

        // Firma
        addParagraph(document, "_________________________________________________________________", normalFont, Element.ALIGN_CENTER);
        addParagraph(document, "Firma y Sello del Jefe de Unidad de Control de las Filtraciones u OSPE, según el tipo de oficina", smallFont, Element.ALIGN_CENTER);

        // Cierre del documento
        document.close();
        return outputStream;
    }

    private static Font getArialFont(float size, int style) throws IOException, DocumentException {
        // Registrar la fuente Arial
        String arialPath = "static/fonts/arial-font/arial.ttf";  // Ajusta esta ruta según la ubicación del archivo
        BaseFont baseFont = BaseFont.createFont(arialPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        return new Font(baseFont, size, style);
    }

    private static void addParagraphUnderlined(Document document, String text, Font font) throws DocumentException {
        // Crear un Chunk con el texto y aplicarle el subrayado
        Chunk underlinedText = new Chunk(text, font);
        underlinedText.setUnderline(0.1f, -2f);  // (grosor de la línea, distancia desde la línea base)

        // Crear un párrafo con el Chunk subrayado
        Paragraph paragraph = new Paragraph(underlinedText);

        // Agregar el párrafo al documento
        document.add(paragraph);
    }

    private static void addImage(Document document) throws IOException, DocumentException {
        ClassPathResource imgFile = new ClassPathResource("static/img.png");
        try (InputStream imgStream = imgFile.getInputStream()) {
            Image image = Image.getInstance(imgStream.readAllBytes());
            image.scaleToFit(80, 80);
            image.setAbsolutePosition(60, 780);
            document.add(image);
        }
    }

    private static void addParagraph(Document document, String text, Font font) throws DocumentException {
        addParagraph(document, text, font, Element.ALIGN_LEFT);
    }

    private static void addParagraph(Document document, String text, Font font, int alignment) throws DocumentException {
        Paragraph paragraph = new Paragraph(text, font);
        paragraph.setAlignment(alignment);
        document.add(paragraph);
    }

    private static void addVerificadoresTable(Document document, List<Verificador> verificadores, Font boldFont, Font normalFont) throws DocumentException {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100); // Ancho de la tabla

        // Primera fila (Encabezados)
        addCellToTable(table, "Verificador", boldFont, Element.ALIGN_CENTER);
        addCellToTable(table, "Tipo y número del Documento de Identidad", boldFont, Element.ALIGN_CENTER);

        // Llenar la tabla con los datos de la lista
        for (Verificador verificador : verificadores) {
            addCellToTable(table, verificador.getNombre(), normalFont, Element.ALIGN_LEFT);
            addCellToTable(table, verificador.getDocumento(), normalFont, Element.ALIGN_LEFT);
        }

        document.add(table);
    }

    private static void addCellToTable(PdfPTable table, String text, Font font, int alignment) {
        PdfPCell cell = new PdfPCell(new Paragraph(text, font));
        cell.setHorizontalAlignment(alignment);
        cell.setBorderWidth(0.3f);  // Alinear el texto al centro
        cell.setPadding(10f);  // Aumentar el padding (espaciado interno) de la celda
        table.addCell(cell);
    }

    private static void addInfoAdicional(Document document, Font font) throws DocumentException {
        // Crear un Chunk para la URL con color azul
        Chunk urlChunk = new Chunk("http://www.essalud.gob.pe/agencias-y-oficinas-de-seguros/", font);
        urlChunk.setAnchor("http://www.essalud.gob.pe/agencias-y-oficinas-de-seguros/"); // Añadir un enlace a la URL
        urlChunk.setFont(new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLUE)); // Establecer color azul

        // Crear un párrafo y agregar el Chunk
        Paragraph infoAdicional = new Paragraph();
        infoAdicional.add(new Chunk("Si usted desea confirmar la identidad de los servidores designados, podrá acceder a la siguiente dirección electrónica ", font));
        infoAdicional.add(urlChunk); // Añadir la URL en azul
        infoAdicional.add(new Chunk(" y/o comunicarse telefónicamente al número (Teléfono y anexo de la OSPE) en el horario de (colocar horario de atención), a efectos de confirmar su identidad.", font));

        infoAdicional.setAlignment(Element.ALIGN_JUSTIFIED);
        document.add(infoAdicional);
    }
}
