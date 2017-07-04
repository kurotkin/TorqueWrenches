package com.kurotkin;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Created by Vitaly Kurotkin on 04.07.2017.
 */
public class PdfWriterReport {

    private static DateFormat dateFormatForName = new SimpleDateFormat("yyyyMMddHHmmss");
    private static DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    private static Logger log = Logger.getLogger(Settings.class.getName());

    public static void writePdfStahlwille(ArrayList<Fastener> fasteners) {
        try {
            controlFolder(Settings.stahlwilleReportsUrl);
            Document document = new Document(PageSize.A4.rotate(), 20, 20, 50, 50);

            String prUrlFull = Settings.stahlwilleReportsUrl + File.separator + dateFormatForName.format(new Date()) + ".pdf";
            PdfWriter.getInstance(document, new FileOutputStream(prUrlFull));
            document.open();

            // Шрифты
            BaseFont bf = BaseFont.createFont("Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font fontBig18 = new Font(bf, 18, Font.BOLDITALIC, new CMYKColor(0, 0, 0,255));
            Font fontBig14 = new Font(bf, 12, Font.BOLD, new CMYKColor(0, 0, 0,255));
            Font fontBig14red = new Font(bf, 12, Font.BOLD, new CMYKColor(0, 255, 20,0));
            Font font = new Font(bf, 12);

            Paragraph title1 = new Paragraph("Протокол замеров затяжки", fontBig18);
            title1.setAlignment(Element.ALIGN_CENTER);
            Chapter chapter1 = new Chapter(title1, 1);
            chapter1.setNumberDepth(0);

            Paragraph someSectionText1 = new Paragraph("Документ составляется автоматически. " +
                    "Дата составления документа: " + dateFormat.format(new Date()), font);
            someSectionText1.setAlignment(Element.ALIGN_CENTER);
            chapter1.add(someSectionText1);

            Paragraph someSectionText2 = new Paragraph("Оборудование: Stahlwille", font);
            someSectionText2.setAlignment(Element.ALIGN_CENTER);
            chapter1.add(someSectionText2);

            // Таблица
            PdfPTable t = new PdfPTable(9);
            t.setWidthPercentage(100);;
            t.setSpacingBefore(25);
            t.setSpacingAfter(25);
            t.addCell(new PdfPCell(new Phrase("№ п/п", fontBig14)));
            t.addCell(new PdfPCell(new Phrase("Обозначение тех. процесса / операции", fontBig14)));
            t.addCell(new PdfPCell(new Phrase("Серийный номер оборудования", fontBig14)));
            t.addCell(new PdfPCell(new Phrase("Дата, время затяжки", fontBig14)));
            t.addCell(new PdfPCell(new Phrase("Момент фактический на ключе (Нм)", fontBig14)));
            t.addCell(new PdfPCell(new Phrase("Момент номинальный (Нм)", fontBig14)));
            t.addCell(new PdfPCell(new Phrase("Нижний допустимый момент (Нм)", fontBig14)));
            t.addCell(new PdfPCell(new Phrase("Верхний допустимый момент (Нм)", fontBig14)));
            t.addCell(new PdfPCell(new Phrase("Оценка", fontBig14)));

            for(Fastener f : fasteners) {
                t.addCell(new PdfPCell(new Phrase(Integer.toString(f.id), font)));
                t.addCell(new PdfPCell(new Phrase(f.name, font)));
                t.addCell(new PdfPCell(new Phrase(Integer.toString(f.serno), font)));
                t.addCell(new PdfPCell(new Phrase(dateFormat.format(f.dat), font)));
                t.addCell(new PdfPCell(new Phrase(String.format("%.2f", f.torque), font)));
                t.addCell(new PdfPCell(new Phrase(String.format("%.2f", f.nom_torque), font)));
                t.addCell(new PdfPCell(new Phrase(String.format("%.2f", f.tol_lower), font)));
                t.addCell(new PdfPCell(new Phrase(String.format("%.2f", f.tol_upper), font)));
                String result = f.result ? "Норма" : "Не норма";
                Font fontResult = result.equals("Норма") ? font : fontBig14red;
                t.addCell(new PdfPCell(new Phrase(result, fontResult)));
            }
            chapter1.add(t);
            document.add(chapter1);
            document.close();

        } catch (DocumentException e) {
            log.warning(e.toString());
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            log.warning(e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            log.warning(e.toString());
            e.printStackTrace();
        }
    }

    private static void controlFolder(String folder) {
        // Проверка папки
        File file = new File(folder);
        if (!file.exists()) {
            file.mkdir();
        }
    }
}
