package com.example.formatospdf.utils;

import com.example.formatospdf.dto.Verificador;

import org.apache.poi.xwpf.usermodel.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class Anexo01Word {

    public static ByteArrayOutputStream generateWordStream(List<Verificador> verificadores) throws IOException {
        // Crear un documento Word
        XWPFDocument document = new XWPFDocument();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // Fuentes
        XWPFParagraph titleParagraph = document.createParagraph();
        titleParagraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun titleRun = titleParagraph.createRun();
        titleRun.setBold(true);
        titleRun.setFontSize(12);
        titleRun.setText("ANEXO 01: CARTA DE PRESENTACIÓN DE VERIFICACIÓN");

        // Espaciado
        document.createParagraph().createRun().addBreak();

        // Encabezados
        XWPFParagraph headerParagraph = document.createParagraph();
        headerParagraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun headerRun = headerParagraph.createRun();
        headerRun.setBold(true);
        headerRun.setFontSize(10);
        headerRun.setText("DECENIO DE LAS PERSONAS CON DISCAPACIDAD EN EL PERÚ");
        headerParagraph.createRun().addBreak();
        headerRun = headerParagraph.createRun();
        headerRun.setBold(true);
        headerRun.setFontSize(10);
        headerRun.setText("AÑO DE (COLOCAR EL AÑO QUE CORRESPONDA)");

        // Espaciado
        document.createParagraph().createRun().addBreak();

        // Carta número y datos de la empresa
        addParagraph(document, "Carta N° ");
        addParagraph(document, "Ciudad y Fecha ");
        document.createParagraph().createRun().addBreak();
        addParagraph(document, "Razón Social / Apellidos y nombres ");
        addParagraph(document, "Tipo y Número de Documento ");
        addParagraph(document, "Domicilio ");
        addParagraph(document, "Apellidos y Nombres del Representante Legal ");
        addParagraph(document, "Tipo y Número de Documento de Identidad ");

        // Saludo
        addParagraph(document, "Presente.-");

        // Espaciado
        document.createParagraph().createRun().addBreak();

        // Cuerpo de la carta
        XWPFParagraph bodyParagraph = document.createParagraph();
        XWPFRun bodyRun = bodyParagraph.createRun();
        bodyRun.setFontSize(12);
        bodyRun.setText(
                "Por el presente nos dirigimos a ustedes en relación a nuestra Orden de Verificación N°  " +
                        "de fecha ______________, con la finalidad de presentar al personal verificador que iniciará el procedimiento de " +
                        "verificación de ____ (colocar la verificación que se realizará).");
        bodyParagraph.setAlignment(ParagraphAlignment.BOTH);

        // Crear y agregar tabla
        addVerificadoresTable(document, verificadores);

        // Información adicional
        addInfoAdicional(document);

        // Firma
        document.createParagraph().createRun().addBreak();
        addParagraph(document, "_________________________________________________________________");
        addParagraph(document, "Firma y Sello del Jefe de Unidad de Control de las Filtraciones u OSPE, según el tipo de oficina");

        // Escribir el documento a la salida
        document.write(outputStream);
        document.close();
        return outputStream;
    }

    private static void addParagraph(XWPFDocument document, String text) {
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setFontSize(12);
        run.setText(text);
    }

    private static void addVerificadoresTable(XWPFDocument document, List<Verificador> verificadores) {
        // Crear la tabla con 2 columnas
        XWPFTable table = document.createTable();

        // Primera fila (Encabezados)
        XWPFTableRow row = table.getRow(0);
        row.getCell(0).setText("Verificador");
        row.addNewTableCell().setText("Tipo y número del Documento de Identidad");

        // Agregar verificadores a la tabla
        for (Verificador verificador : verificadores) {
            XWPFTableRow newRow = table.createRow();
            newRow.getCell(0).setText(verificador.getNombre());
            newRow.getCell(1).setText(verificador.getDocumento());
        }
    }

    private static void addInfoAdicional(XWPFDocument document) {
        XWPFParagraph infoParagraph = document.createParagraph();
        XWPFRun infoRun = infoParagraph.createRun();
        infoRun.setFontSize(12);
        infoRun.setText(
                "Si usted desea confirmar la identidad de los servidores designados, podrá acceder a la siguiente dirección electrónica "
                        + "http://www.essalud.gob.pe/agencias-y-oficinas-de-seguros/ "
                        + "y/o comunicarse telefónicamente al número (Teléfono y anexo de la OSPE) en el horario de (colocar horario de atención), "
                        + "a efectos de confirmar su identidad.");
    }
}