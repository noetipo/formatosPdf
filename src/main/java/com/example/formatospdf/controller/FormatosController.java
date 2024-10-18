package com.example.formatospdf.controller;


import com.example.formatospdf.dto.Verificador;
import com.example.formatospdf.utils.*;
import com.itextpdf.text.DocumentException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pdf")
public class FormatosController {

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
