package com.example.formatospdf.utils;

import org.springframework.stereotype.Service;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class WordService {
    public void generateWord(OutputStream out) throws IOException {
        XWPFDocument document = new XWPFDocument();
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText("Este es un documento personalizado.");
        document.write(out);
        document.close();
    }
}