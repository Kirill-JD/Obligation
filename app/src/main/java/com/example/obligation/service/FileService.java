package com.example.obligation.service;

import com.example.obligation.domain.ReceiptLite;
import com.example.obligation.domain.Word;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Section;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;


public class FileService {
    public Word renderWord(ReceiptLite receiptLite, InputStream inputStream) throws IOException {

        POIFSFileSystem fs = new POIFSFileSystem(inputStream);
        HWPFDocument document = new HWPFDocument(fs);

        document = replaceText(document, "$City", receiptLite.getCity());
        document = replaceText(document, "$DataToday", receiptLite.getToday());
        document = replaceText(document, "$FullNameBorrower", receiptLite.getFullNameBorrower());
        document = replaceText(document, "$FullNameLender", receiptLite.getFullNameLender());
        document = replaceText(document, "$Amount", receiptLite.getAmount().toString());
        document = replaceText(document, "$AmountText", receiptLite.getAmountText());
        document = replaceText(document, "$Day", receiptLite.getDay());
        document = replaceText(document, "$Today", receiptLite.getToday());

        return createWord("raspiska_" + receiptLite.getFullNameBorrower() + "_" + receiptLite.getToday() + ".doc", document);
    }

    private HWPFDocument replaceText(HWPFDocument document, String findText, String replaceText) {
        Range range = document.getRange();

        for (int i = 0; i < range.numSections(); ++i ) {
            Section s = range.getSection(i);
            for (int x = 0; x < s.numParagraphs(); x++) {
                Paragraph p = s.getParagraph(x);
                for (int z = 0; z < p.numCharacterRuns(); z++) {
                    CharacterRun run = p.getCharacterRun(z);
                    String text = run.text();
                    if(text.contains(findText)) {
                        run.replaceText(findText, replaceText);
                    }
                }
            }
        }
        return document;
    }
    private Word createWord(String fileName, HWPFDocument document) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            document.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
            document.close();
        }
        byte[] array = out.toByteArray();
        return new Word(fileName, array);
    }
}
