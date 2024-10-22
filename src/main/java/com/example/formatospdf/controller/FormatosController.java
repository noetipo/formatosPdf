package com.example.formatospdf.controller;


import com.example.formatospdf.dto.Verificador;
import com.example.formatospdf.utils.*;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pdf")
public class FormatosController {
    @Autowired
    private WordService wordService;
    @GetMapping("/anexo1")
    public ResponseEntity<byte[]> anexo1() throws IOException, DocumentException {
        List<Verificador> verificadors = new ArrayList<>();
        verificadors.add(new Verificador("noe", "demo"));
        verificadors.add(new Verificador("noe", "demo"));

        ByteArrayOutputStream pdfStream = Anexo01.generatePdfStream(verificadors);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=query_results.pdf");
        headers.setContentLength(pdfStream.size());
        return new ResponseEntity<>(pdfStream.toByteArray(), headers, HttpStatus.OK);
    }

    @GetMapping("/anexo2")
    public ResponseEntity<byte[]> anexo2() throws IOException, DocumentException {
        List<Verificador> verificadors = new ArrayList<>();
        verificadors.add(new Verificador("noe", "demo"));
        verificadors.add(new Verificador("noe", "demo"));

        ByteArrayOutputStream pdfStream = Anexo02.generatePdfStream();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=query_results.pdf");
        headers.setContentLength(pdfStream.size());
        return new ResponseEntity<>(pdfStream.toByteArray(), headers, HttpStatus.OK);
    }

    @GetMapping("/anexo3")
    public ResponseEntity<byte[]> anexo3() throws IOException, DocumentException {
        List<Verificador> verificadors = new ArrayList<>();
        verificadors.add(new Verificador("noe", "demo"));
        verificadors.add(new Verificador("noe", "demo"));

        ByteArrayOutputStream pdfStream = Anexo03.generatePdfStream(verificadors);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=query_results.pdf");
        headers.setContentLength(pdfStream.size());
        return new ResponseEntity<>(pdfStream.toByteArray(), headers, HttpStatus.OK);
    }

    @GetMapping("/anexo4")
    public ResponseEntity<byte[]> anexo4() throws IOException, DocumentException {
        List<Verificador> verificadors = new ArrayList<>();
        verificadors.add(new Verificador("noe", "demo"));
        verificadors.add(new Verificador("noe", "demo"));

        ByteArrayOutputStream pdfStream = Anexo04.generatePdfStream(verificadors);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=query_results.pdf");
        headers.setContentLength(pdfStream.size());
        return new ResponseEntity<>(pdfStream.toByteArray(), headers, HttpStatus.OK);
    }

    @GetMapping("/generate")
    public ResponseEntity<InputStreamResource> generateWord() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        // Generar el documento Word en el OutputStream
        wordService.generateWord(byteArrayOutputStream);

        // Convertir el OutputStream a InputStream para la respuesta
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

        // Configurar los encabezados para la descarga
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=documento.docx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(byteArrayInputStream));
    }
    @GetMapping("/anexo10")
    public ResponseEntity<InputStreamResource> generateAnexo1Word() throws IOException {
        // Crear lista de verificadores
        List<Verificador> verificadores = new ArrayList<>();
        verificadores.add(new Verificador("Noe", "12345678"));
        verificadores.add(new Verificador("Verificador 2", "87654321"));

        // Generar el documento Word en un ByteArrayOutputStream
        ByteArrayOutputStream wordStream = Anexo01Word.generateWordStream(verificadores);

        // Convertir el OutputStream a InputStream para la respuesta
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(wordStream.toByteArray());

        // Configurar los encabezados para la descarga
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=anexo01.docx");

        // Devolver el documento Word como archivo descargable
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(byteArrayInputStream));}

    @GetMapping("/anexo5")
    public ResponseEntity<byte[]> anexo5() throws IOException, DocumentException {
        List<Verificador> verificadors = new ArrayList<>();
        verificadors.add(new Verificador("noe", "demo"));
        verificadors.add(new Verificador("noe", "demo"));

        ByteArrayOutputStream pdfStream = Anexo05.generatePdfStream();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=query_results.pdf");
        headers.setContentLength(pdfStream.size());
        return new ResponseEntity<>(pdfStream.toByteArray(), headers, HttpStatus.OK);
    }
}
