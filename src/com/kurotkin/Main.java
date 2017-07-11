package com.kurotkin;

import com.cedarsoftware.util.io.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import static java.lang.System.exit;

public class Main {
    private static Logger log = Logger.getLogger(Main.class.getName());
    enum Wrenches {STAHLWILLE, TOHNICHI_CEM, TOHNICHI_STC2};

    public static void main(String[] args) {
        loadLog();
        if (args.length == 0) {
            argsBad();
        }
        Settings.loadSettings();
        ArrayList<Fastener> fasteners = null;
        switch (args[0]) {
            case "St" :
                fasteners = Connecter.Stahlwille();
                PdfWriterReport.writePdfStahlwille(fasteners);
                break;
            case "TohnCEM":
                fasteners = Connecter.TohnichiCEM();
                PdfWriterReport.writePdfTohnichiCEM(fasteners);
                break;
            case "TohnSTC2":
                fasteners = Connecter.TohnichiSTC2();
                PdfWriterReport.writePdfTohnichiSTC2(fasteners);
                break;
            default:
                argsBad();
        }
        XMLwriter.WriteXML(fasteners);
        logWrenches(fasteners);
    }

    public static void argsBad() {
        log.warning("Не верные аргументы, допустимые: St, TohnCEM, TohnSTC2");
        System.out.println("Для работы с ключами Stahlwille  запускайте         \"java -jar TorqueWrenches.jar St\"");
        System.out.println("Для работы с ключами Tohnichi серии CEM запускайте  \"java -jar TorqueWrenches.jar TohnCEM\"");
        System.out.println("Для работы с ключами Tohnichi серии STC2 запускайте \"java -jar TorqueWrenches.jar TohnSTC2\"");
        exit(0);
    }

    private static void loadLog() {
        try {
            LogManager.getLogManager().readConfiguration(Main.class.getResourceAsStream("logging.properties"));
        } catch (IOException e) {
            System.err.println("Could not setup logger configuration: " + e.toString());
        }
    }

    private static void logWrenches(ArrayList<Fastener> fasteners) {
        for(Fastener f : fasteners) {
            log.info(JsonWriter.objectToJson(f));
        }
    }
}
