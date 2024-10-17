package com.example.formatospdf.utils;

import com.example.formatospdf.dto.Verificador;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class Anexo03 {

    public static ByteArrayOutputStream generatePdfStream(List<Verificador> verificadores) throws DocumentException, IOException {
        // Inicializar el documento
        Document document = new Document(PageSize.A4, 80, 80, 40, 40);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, outputStream);
        document.open();

        // Fuentes
        Font boldTitleFont = getArialFont(12, Font.BOLD);
        Font normalFont = getArialFont(11, Font.NORMAL);
        Font boldFont = getArialFont(11, Font.BOLD);

        // Encabezado actualizado
        addParagraph(document, "ANEXO 03: ACTA DE IMPEDIMENTO O DEMORA EN LA AUTORIZACIÓN DE INGRESO AL CENTRO DE TRABAJO", boldTitleFont, Element.ALIGN_CENTER);
        document.add(Chunk.NEWLINE); // Línea en blanco después del encabezado

        // Crear primera tabla con 4 columnas
        PdfPTable table1 = new PdfPTable(4);
        table1.setWidthPercentage(100);

        // Añadir la celda de Razón Social, Tipo de Documento, Domicilio en Arial 11
        PdfPCell combinedHeader1 = new PdfPCell(new Paragraph("Razón Social / Apellidos y nombres\n" +
                "Tipo y Número de Documento\n" +
                "Domicilio\n\n\n\n", normalFont));  // Arial 11 negrita
        combinedHeader1.setColspan(4); // Combinar las 4 columnas
        combinedHeader1.setHorizontalAlignment(Element.ALIGN_LEFT); // Alinear el texto a la izquierda
        combinedHeader1.setPadding(10f); // Aumentar el padding para dar espacio adicional abajo
        table1.addCell(combinedHeader1);

        // Crear el título del acta en Arial 11 negrita y centrado
        Paragraph titleParagraph = new Paragraph("Acta de Constatación de Impedimento o Demora en la Autorización de Ingreso al Centro de Trabajo", boldFont);
        titleParagraph.setAlignment(Element.ALIGN_CENTER);

        // Crear el párrafo con el contenido justificado en Arial 11
        Paragraph contentParagraph = new Paragraph(
                "Siendo las _________ del día ____/_____/_____, en mi calidad de verificador, me constituí en el " +
                        "domicilio del (Razón Social) con el propósito de realizar la función de verificación de (colocar " +
                        "verificación que va a hacerse) del Sr. (……………………………………………………………………………………………….) iniciada " +
                        "mediante Orden de Verificación N° (colocar número de Orden de Verificación). Al respecto, se deja " +
                        "constancia que en el referido domicilio:\n\n" +
                        "       - Se negaron a brindar atención\n" +
                        "       - Demoró más de quince minutos en abrir la puerta\n\n" +
                        "Por lo que, de conformidad con la Ley Nº 29135, Ley que establece el Porcentaje que deben pagar " +
                        "EsSalud y la Oficina de Normalización Previsional – ONP a la Superintendencia Nacional de " +
                        "Administración Tributaria – SUNAT por la Recaudación de sus Aportaciones, y Medidas para Mejorar la " +
                        "Administración de Tales Aportes, modificada por Decreto Legislativo Nº 1172, y su Reglamento " +
                        "aprobado por Decreto Supremo Nº 002-2009-TR, levanto la presente acta, procediendo a dejarla bajo " +
                        "la puerta del domicilio y retirarme.\n\n" +
                        "(Colocar el siguiente texto si se trata de primera visita:)\n" +
                        "Asimismo, en mérito a lo señalado en el Artículo 17° del Reglamento aprobado por Decreto Supremo " +
                        "N° 002-2009-TR, se notifica al empleador que se realizará una nueva visita dentro de los tres (3) " +
                        "días hábiles siguientes: (colocar fecha de visita).", normalFont);

        // Justificar el contenido
        contentParagraph.setAlignment(Element.ALIGN_JUSTIFIED);

        // Crear la celda que contendrá todo el contenido y ajustarla
        PdfPCell combinedHeader2 = new PdfPCell();
        combinedHeader2.addElement(titleParagraph); // Añadir el título centrado
        combinedHeader2.addElement(contentParagraph); // Añadir el contenido justificado
        combinedHeader2.setColspan(4); // Combinar las 4 columnas
        combinedHeader2.setPadding(5f); // Padding para el texto
        table1.addCell(combinedHeader2);

        // Agregar la primera tabla al documento
        document.add(table1);

        // Crear segunda tabla con 2 columnas (para la firma y características del domicilio)
        PdfPTable table2 = new PdfPTable(2);
        table2.setWidthPercentage(100);

        // Fila 1: Encabezados de las dos columnas
        PdfPCell header1 = new PdfPCell(new Paragraph("FIRMA Y NOMBRE DEL VERIFICADOR PARA TODOS LOS CASOS", normalFont));
        header1.setHorizontalAlignment(Element.ALIGN_CENTER);
        header1.setPadding(5f);
        table2.addCell(header1);

        PdfPCell header2 = new PdfPCell(new Paragraph("CARACTERÍSTICAS DEL DOMICILIO PARA TODOS LOS CASOS", normalFont));
        header2.setHorizontalAlignment(Element.ALIGN_CENTER);
        header2.setPadding(5f);
        table2.addCell(header2);

        // Fila 2: Información del verificador y las características del domicilio
        PdfPCell cell1 = new PdfPCell(new Paragraph("Nombre y Apellido: \n\n", normalFont));
        cell1.setPadding(5f);
        table2.addCell(cell1);

        PdfPCell cell2 = new PdfPCell(new Paragraph("Color Fachada:\n\n", normalFont));
        cell2.setPadding(5f);
        table2.addCell(cell2);

        // Fila 3: Documento de Identidad y Material de Puerta
        PdfPCell cell3 = new PdfPCell(new Paragraph("Documento de Identidad:\n\n", normalFont));
        cell3.setPadding(5f);
        table2.addCell(cell3);

        PdfPCell cell4 = new PdfPCell(new Paragraph("Material de Puerta:\n\n", normalFont));
        cell4.setPadding(5f);
        table2.addCell(cell4);

        // Agregar la segunda tabla al documento
        document.add(table2);

        // Crear tercera tabla con 2 columnas (firma del verificador y datos adicionales)
        PdfPTable table3 = new PdfPTable(2);
        table3.setWidthPercentage(100);

        // Fila 1: Firma del Verificador y características del domicilio
        PdfPCell cell5 = new PdfPCell(new Paragraph("__________________________\nFirma del Verificador", normalFont));
        cell5.setPadding(10f);
        cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell5.setVerticalAlignment(Element.ALIGN_BOTTOM);
        table3.addCell(cell5);

        PdfPCell cell6 = new PdfPCell();
        PdfPTable nestedTable = new PdfPTable(1);
        nestedTable.setWidthPercentage(100);

        // Subtabla con los detalles de la puerta y número de pisos/medidor
        addCellToNestedTable(nestedTable, "Color Puerta:", normalFont);
        addCellToNestedTable(nestedTable, "N° de pisos:", normalFont);
        addCellToNestedTable(nestedTable, "N° medidor:", normalFont);

        cell6.addElement(nestedTable);
        table3.addCell(cell6);

        // Agregar la tercera tabla al documento
        document.add(table3);

        // Cierre del documento
        document.close();
        return outputStream;
    }

    private static Font getArialFont(float size, int style) throws IOException, DocumentException {
        // Registrar la fuente Arial desde el classpath
        String arialPath = "static/fonts/arial-font/arial.ttf";
        BaseFont baseFont = BaseFont.createFont(arialPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        return new Font(baseFont, size, style);
    }

    private static void addCellToNestedTable(PdfPTable table, String text, Font font) {
        PdfPCell cell = new PdfPCell(new Paragraph(text, font));
        cell.setPadding(5f);
        table.addCell(cell);
    }

    private static void addParagraph(Document document, String text, Font font, int alignment) throws DocumentException {
        Paragraph paragraph = new Paragraph(text, font);
        paragraph.setAlignment(alignment);
        document.add(paragraph);
    }
}
