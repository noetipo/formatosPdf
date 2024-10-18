package com.example.formatospdf.utils;

import com.example.formatospdf.dto.Asegurado;
import com.example.formatospdf.dto.Personal;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Anexo05 {

    public static ByteArrayOutputStream generatePdfStream() throws DocumentException, IOException {

        // Inicializar el documento
        Document document = new Document(PageSize.A4, 40, 40, 40, 40);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, outputStream);
        document.open();

        // Definir las fuentes
        Font headerFont = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD);
        Font initFont = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
        Font normalFont = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL);
        Font nameDocumentFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD); // Fuente para el encabezado

        // Encabezado
        addParagraph(document, "ANEXO 05: “ACTA DE VERIFICACIÓN”", nameDocumentFont, Element.ALIGN_CENTER);
        document.add(Chunk.NEWLINE); // Línea en blanco después del encabezado

        // Crear tabla con 4 columnas
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);

        // Fila 1 - Acta de Verificación
        PdfPCell combinedRow1 = new PdfPCell(new Paragraph("Acta de Verificación N°  ………………..-201….(COLOCAR SIGLAS DE LA OSPE QUE REALIZA VERIFICACION)", initFont));
        combinedRow1.setColspan(4);
        combinedRow1.setHorizontalAlignment(Element.ALIGN_CENTER);
        combinedRow1.setPadding(8f);
        table.addCell(combinedRow1);

        // Fila 2: Texto con espacios para completar
        PdfPCell combinedRow2 = new PdfPCell(new Paragraph(
                "En ………………………., siendo las .....................horas del día................de........................................," +
                " del año.................., los verificadores de EsSalud que suscriben la presente Acta, nos constituimos en el local de la Entidad Empleadora" +
                " ........................................................................................... sito en " +
                " ........................................................................." +
                " con el fin de realizar la Verificación de (colocar verificación que va a realizarse), " +
                " iniciada mediante Orden de Verificación N° …………………………………………………..…… por la modalidad de …………………………………………………………., " +
                " constatándose lo siguiente:\n", normalFont));
        combinedRow2.setColspan(4);
        combinedRow2.setHorizontalAlignment(Element.ALIGN_LEFT);
        combinedRow2.setPadding(2f);
        table.addCell(combinedRow2);

        // Fila 3 - Participantes de la Verificación
        PdfPCell combinedRow3 = new PdfPCell(new Paragraph("I. Participantes de la Verificación: ", initFont));
        combinedRow3.setColspan(4);
        combinedRow3.setHorizontalAlignment(Element.ALIGN_LEFT);
        combinedRow3.setPadding(2f);
        table.addCell(combinedRow3);

        // Fila 4 - Personal y Representantes
        PdfPCell combinedRow4 = new PdfPCell(new Paragraph("Personal y Representantes de la Entidad Empleadora:", normalFont));
        combinedRow4.setColspan(4);
        combinedRow4.setHorizontalAlignment(Element.ALIGN_LEFT);
        combinedRow4.setPadding(2f);
        table.addCell(combinedRow4);

        // Agregar tabla al documento
        document.add(table);

        // Crear nueva tabla para la lista dinámica con 4 columnas
        PdfPTable dynamicTable = new PdfPTable(4);
        dynamicTable.setWidthPercentage(100);

        // Configurar los anchos de las columnas
        float[] columnWidths = {1f, 4f, 2f, 4f};
        dynamicTable.setWidths(columnWidths);

        // Agregar las cabeceras
        addCellToTable(dynamicTable, "N°", normalFont, Element.ALIGN_CENTER);
        addCellToTable(dynamicTable, "Apellidos y Nombres", normalFont, Element.ALIGN_CENTER);
        addCellToTable(dynamicTable, "DNI", normalFont, Element.ALIGN_CENTER);
        addCellToTable(dynamicTable, "Cargo", normalFont, Element.ALIGN_CENTER);

        // Simulando una lista dinámica
        List<Personal> personales = Arrays.asList(
                new Personal(1, "Smith John ", "12345678", "Cargo 1"),
                new Personal(2, "Johnson Emily ", "87654321", "Cargo 1"),
                new Personal(3, "Brown Michael", "45678912", "Cargo 1"),
                new Personal(4, "Williams Sarah", "98765432", "Cargo 1")
        );

        // Agregar contenido de la lista dinámica a la tabla
        for (Personal personal : personales) {
            addCellToTable(dynamicTable, String.valueOf(personal.getNumero()), normalFont, Element.ALIGN_CENTER);
            addCellToTable(dynamicTable, personal.getApellidosNombres(), normalFont, Element.ALIGN_LEFT);
            addCellToTable(dynamicTable, personal.getDni(), normalFont, Element.ALIGN_CENTER);
            addCellToTable(dynamicTable, personal.getCargo(), normalFont, Element.ALIGN_CENTER);
        }

        // Agregar la tabla dinámica al documento
        document.add(dynamicTable);

        // Crear una nueva tabla con una fila y un colspan de 4
        PdfPTable finalTable = new PdfPTable(2);
        finalTable.setWidthPercentage(100);

        // Crear una celda con colspan de 4
        PdfPCell finalRow = new PdfPCell(new Paragraph("Verificadores designados por EsSalud:", normalFont));
        finalRow.setColspan(4);
        finalRow.setHorizontalAlignment(Element.ALIGN_LEFT);
        finalRow.setPadding(2f); // Reducir el padding

        // Agregar la celda a la tabla
        finalTable.addCell(finalRow);

        // Agregar la tabla al documento
        document.add(finalTable);

        // Crear nueva tabla dinámica con 4 columnas
        PdfPTable secondDynamicTable = new PdfPTable(4);
        secondDynamicTable.setWidthPercentage(100);

        // Configurar los anchos de las columnas
        float[] secondColumnWidths = {1f, 4f, 2f, 4f};
        secondDynamicTable.setWidths(secondColumnWidths);

        // Agregar las cabeceras
        addCellToTable(secondDynamicTable, "N°", normalFont, Element.ALIGN_CENTER);

        // Crear la celda para "Nombres y Apellidos" con un colspan de 2
        PdfPCell nameCell = new PdfPCell(new Paragraph("Apellidos y Nombres", normalFont));
        nameCell.setColspan(2);
        nameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        nameCell.setPadding(2f); // Reducir el padding
        secondDynamicTable.addCell(nameCell);

        /*// Simulando una lista dinámica para la nueva tabla
        List<Asegurado> verificadores = Arrays.asList(
                new Asegurado(1, "Carlos Diaz", "65432109", ""),
                new Asegurado(2, "Ana Torres", "78965432", ""),
                new Asegurado(3, "Miguel Lopez", "45612378", ""),
                new Asegurado(4, "Lucia Fernandez", "98765432", "")
        );

        // Agregar contenido de la lista dinámica a la nueva tabla
        for (Asegurado verificador : verificadores) {
            addCellToTable(secondDynamicTable, String.valueOf(verificador.getNumero()), normalFont, Element.ALIGN_CENTER);

            // Agregar la celda para el nombre con un colspan de 2
            PdfPCell verificadorNameCell = new PdfPCell(new Paragraph(verificador.getNombresApellidos(), normalFont));
            verificadorNameCell.setColspan(2);
            verificadorNameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            verificadorNameCell.setPadding(2f); // Reducir el padding
            secondDynamicTable.addCell(verificadorNameCell);

            // Agregar la celda para el DNI
            addCellToTable(secondDynamicTable, verificador.getDni(), normalFont, Element.ALIGN_CENTER);
        }

        // Agregar la nueva tabla dinámica al documento
        document.add(secondDynamicTable);

        // Crear una celda con colspan de 4 para el texto en negrita
        PdfPCell finalRowBold = new PdfPCell(new Paragraph("En virtud de lo dispuesto en el Artículo 11° del Reglamento aprobado por Decreto Supremo N° 002-2009-TR, le solicitamos poner a disposición del personal verificador los siguientes documentos:", headerFont));
        finalRowBold.setColspan(4);
        finalRowBold.setHorizontalAlignment(Element.ALIGN_LEFT);
        finalRowBold.setPadding(2f); // Reducir el padding

        // Agregar la celda a la tabla
        finalTable.addCell(finalRowBold);

        // Agregar la tabla al documento
        document.add(finalTable); */

       /* // Crear la tabla con 3 columnas
        PdfPTable staticTable = new PdfPTable(3);
        staticTable.setWidthPercentage(100);

        // Configurar los anchos de las columnas: columna 2 será muy estrecha
        float[] staticColumnWidths = {4.5f, 0.5f, 4.5f};
        staticTable.setWidths(staticColumnWidths);

        // Crear las celdas para cada elemento de la tabla
        String[] column1 = {
                "1) Contrato de Trabajo del asegurado",
                "2) Declaraciones Tributarias-PDT- últimos seis (06) meses",
                "3) Registro en el MTPE",
                "4) Registros especiales según su actividad económica",
                "5) Planilla de Sueldos o Remuneraciones/ Planilla Electrónica",
                "6) Boletas de Pago del asegurado de los últimos seis (06) meses",
                "7) Partes diarios de Asistencia de los últimos seis (06) meses",
                "8) Seis (06) últimos pagos de Aporte ONP/AFP del asegurado",
                "9) Descansos médicos de los últimos doce (12) meses"
        };

        String[] column3 = {
                "10) Documentos de asignación de funciones del asegurado",
                "11) Documentos presentados por el trabajador al empleador que evidencien las funciones desarrolladas en los últimos seis (06) meses",
                "12) Registro de Ventas. Comprobantes de pago que emite",
                "13) Registro de Compras",
                "14) Relación de Productos o Servicios que vende",
                "15) Carta de empleador para depósito de CTS de los últimos semestres",
                "16) CIR empleador de trabajador del hogar",
                "17) Título de propiedad, Título de Posesión, Contrato de Arrendamiento o cesión de Uso del terreno donde se realiza la actividad Agraria"
        };

        // Agregar las celdas de la columna 1
        for (String text : column1) {
            PdfPCell cell = new PdfPCell(new Paragraph(text, normalFont));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_TOP);
            cell.setPadding(2f); // Reducir el padding
            staticTable.addCell(cell);

            // Agregar una celda vacía para la columna 2
            PdfPCell emptyCell = new PdfPCell(new Paragraph(" "));
            emptyCell.setBorder(PdfPCell.NO_BORDER); // Sin borde
            emptyCell.setPadding(2f); // Reducir el padding
            staticTable.addCell(emptyCell);

            // Agregar las celdas de la columna 3
            int index = Arrays.asList(column1).indexOf(text);
            if (index < column3.length) {
                PdfPCell cell3 = new PdfPCell(new Paragraph(column3[index], normalFont));
                cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell3.setVerticalAlignment(Element.ALIGN_TOP);
                cell3.setPadding(2f); // Reducir el padding
                staticTable.addCell(cell3);
            } else {
                PdfPCell cell3 = new PdfPCell(new Paragraph(""));
                cell3.setPadding(2f); // Reducir el padding
                staticTable.addCell(cell3);
            }
        }

        // Agregar la tabla estática al documento
        document.add(staticTable);

        // Crear la tabla con una columna para el texto largo
        PdfPTable fullTextTable = new PdfPTable(1);
        fullTextTable.setWidthPercentage(100);

        // Crear la celda con el texto largo
        PdfPCell fullTextCell = new PdfPCell(new Paragraph(
                "El verificador, de acuerdo a lo dispuesto en el Artículo 07° del Reglamento aprobado por Decreto Supremo N° 002-2009-TR, " +
                        "está facultado para iniciar la verificación inmediatamente después de recibida la Orden de Verificación, ingresar al centro de trabajo, " +
                        "levantar actas, practicar cualquier diligencia de investigación, examen o prueba que considere necesario, requerir información e identificación " +
                        "de las personas que se encuentren en el centro de trabajo materia de la acción de verificación y solicitar la comparecencia de la entidad empleadora " +
                        "o sus representantes, de los trabajadores y de cualesquiera sujetos incluidos en su ámbito de actuación en el centro inspeccionado.\n\n" +
                        "El empleador debe permitir el ingreso a los funcionarios y/o servidores públicos en el centro de trabajo, lugar o establecimiento donde se lleva a cabo " +
                        "la verificación, colaborar con ellos durante su visita y facilitar la información y documentación que le sea solicitada para desarrollar la función de verificación.\n\n" +
                        "Cabe precisar que el incumplimiento de lo señalado en el párrafo anterior constituye infracción tipificada en el Artículo 25° del Reglamento aprobado por Decreto Supremo " +
                        "N° 002-2009-TR, estando sujetos a las sanciones contenidas en el anexo de Tabla de Infracciones y Sanciones contenidas en el referido Decreto Supremo.\n\n" +
                        "Si usted desea confirmar la identidad de los servidores designados podrá acceder a la siguiente dirección electrónica http://www.essalud.gob.pe/agencias-y-oficinas-de-seguros/ " +
                        "y/o comunicarse telefónicamente al número (Teléfono y anexo de la Unidad de Control de las Filtraciones u OSPE, según el tipo de oficina) en el (horario de atención) para " +
                        "comprobar su identidad.\n\n" +
                        "Base Legal: Ley N° 29135 reglamentada por el Decreto Supremo N° 002-2009-TR.", normalFont
        ));
        fullTextCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        fullTextCell.setPadding(2f); // Reducir el padding
        fullTextTable.addCell(fullTextCell);

        // Agregar la tabla al documento
        document.add(fullTextTable);

        // Crear la tabla para la firma con una columna
        PdfPTable signatureTable = new PdfPTable(1);
        signatureTable.setWidthPercentage(100);

        // Crear el Paragraph con espacio en blanco antes de la firma
        Paragraph signatureParagraph = new Paragraph();
        signatureParagraph.add(Chunk.NEWLINE); // Añadir una línea en blanco
        signatureParagraph.add(Chunk.NEWLINE); // Añadir otra línea en blanco si es necesario
        signatureParagraph.add(new Chunk("------------------------------------------------------------\n", normalFont));
        signatureParagraph.add(new Chunk("Firma y Sello del Jefe Unidad de Control de las Filtraciones u OSPE, según el tipo de oficina", normalFont));

        // Crear la celda con la línea para la firma y agregar el Paragraph
        PdfPCell signatureCell = new PdfPCell(signatureParagraph);
        signatureCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        signatureCell.setPadding(2f); // Reducir el padding
        signatureTable.addCell(signatureCell);

        // Agregar la tabla de la firma al documento
        document.add(signatureTable); */

        // Cierre del documento
        document.close();
        return outputStream;
    }

    private static void addParagraph(Document document, String text, Font font, int alignment) throws DocumentException {
        Paragraph paragraph = new Paragraph(text, font);
        paragraph.setAlignment(alignment);
        document.add(paragraph);
    }

    private static void addCellToTable(PdfPTable table, String text, Font font, int alignment) {
        PdfPCell cell = new PdfPCell(new Paragraph(text, font));
        cell.setHorizontalAlignment(alignment);
        cell.setPadding(2f); // Reducir el padding
        table.addCell(cell);
    }
}
