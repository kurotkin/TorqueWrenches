package com.kurotkin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import static java.lang.System.exit;

public class Main {
    private static Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        loadLog();
        if (args.length == 0) {
            argsBad();
        }
        Settings.loadSettings();
        ArrayList<Fastener> fasteners;
        switch (args[0]) {
            case "St" :
                fasteners = Connecter.Stahlwille();
                XMLwriter.WriteXML(fasteners);
                break;
            case "TohnCEM":
                fasteners = Connecter.TohnichiCEM();
                XMLwriter.WriteXML(fasteners);
                break;
            case "TohnSTC2":
                fasteners = Connecter.TohnichiSTC2();
                XMLwriter.WriteXML(fasteners);
                break;
            default:
                argsBad();
        }

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
            log.warning(e.toString());
            System.err.println("Could not setup logger configuration: " + e.toString());
        }
    }
}
