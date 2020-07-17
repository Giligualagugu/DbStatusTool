package com.xujiale.tools.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.IOException;

/**
 * @author xujiale 2020/7/17 15:09
 */
@Slf4j
public class PDFvalidateUtil {

    public static boolean validPdf(byte[] bytes) {
        try {
            PDDocument.load(bytes);
            return true;
        } catch (IOException e) {
            log.info(e.getMessage());
        }
        return false;
    }
}
