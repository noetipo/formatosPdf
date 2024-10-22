package com.example.formatospdf.utils;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.wp.usermodel.HeaderFooterType;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CartaPresentacionWord {

    public static ByteArrayOutputStream generateWordStream() throws IOException {
        // Crear un documento Word
        XWPFDocument document = new XWPFDocument();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // Agregar encabezado con imagen
        addHeaderWithImage(document);

        // Agregar pie de página con imagen
        addFooterWithImage(document);

        // Título Principal
        XWPFParagraph titleParagraph = document.createParagraph();
        titleParagraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun titleRun = titleParagraph.createRun();
        titleRun.setBold(true);
        titleRun.setFontSize(14);
        titleRun.setText("ANEXO 01: “CARTA DE PRESENTACIÓN”");

        // Espaciado
        document.createParagraph().createRun().addBreak();

        // Carta N° y otros detalles (el resto del contenido se mantiene igual)
        XWPFParagraph cartaParagraph = document.createParagraph();
        XWPFRun cartaRun = cartaParagraph.createRun();
        cartaRun.setFontSize(12);
        cartaRun.setText("Carta N° 945-2024-VCA-00000-237-001");
        cartaParagraph.createRun().addBreak();
        cartaRun.setText("Jesús María, ");
        cartaRun.addBreak();
        cartaRun.setText("Razón Social / Apellidos y nombres: TODO COMERCIO S.A.C.");
        cartaRun.addBreak();
        cartaRun.setText("Tipo y Número de Documento: RUC. 20549029303");
        cartaRun.addBreak();
        cartaRun.setText("Domicilio: JR. LAS TAPADAS 126 404 EL MANZANO - RIMAC");
        cartaRun.addBreak();
        cartaRun.setText("Apellidos y Nombres del Representante Legal: CONDE DELGADO DEMETRIO ALBERTO");
        cartaRun.addBreak();
        cartaRun.setText("Tipo y Número de Documento de Identidad: 08044428");
        cartaRun.addBreak();
        cartaRun.setText("Presente.-");

        // Escribir el documento a la salida
        document.write(outputStream);
        document.close();
        return outputStream;
    }

    // Método para agregar imagen en el encabezado
    private static void addHeaderWithImage(XWPFDocument document) throws IOException {
        XWPFHeader header = document.createHeader(HeaderFooterType.DEFAULT);
        XWPFParagraph paragraph = header.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);

        // Cargar la imagen desde la ruta en el classpath
        ClassPathResource imgFile = new ClassPathResource("static/ministerio.png");
        try (InputStream imgStream = imgFile.getInputStream()) {
            XWPFRun run = paragraph.createRun();
            run.addPicture(imgStream, XWPFDocument.PICTURE_TYPE_PNG, imgFile.getFilename(), Units.toEMU(50), Units.toEMU(50)); // Ajusta el tamaño de la imagen
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        }
    }

    // Método para agregar imagen en el pie de página
    private static void addFooterWithImage(XWPFDocument document) throws IOException {
        XWPFFooter footer = document.createFooter(HeaderFooterType.DEFAULT);
        XWPFParagraph paragraph = footer.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);

        // Cargar la imagen desde la ruta en el classpath
        ClassPathResource imgFile = new ClassPathResource("static/direccion.jpg");
        try (InputStream imgStream = imgFile.getInputStream()) {
            XWPFRun run = paragraph.createRun();
            run.addPicture(imgStream, XWPFDocument.PICTURE_TYPE_PNG, imgFile.getFilename(), Units.toEMU(50), Units.toEMU(50)); // Ajusta el tamaño de la imagen
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        }
    }
}