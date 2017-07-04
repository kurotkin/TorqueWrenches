package com.kurotkin;

import java.util.ArrayList;
import static java.lang.System.exit;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0)
            argsBad();
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
        System.out.println("Для работы с ключами Stahlwille  запускайте         \"java -jar TorqueWrenches.jar St\"");
        System.out.println("Для работы с ключами Tohnichi серии CEM запускайте  \"java -jar TorqueWrenches.jar TohnCEM\"");
        System.out.println("Для работы с ключами Tohnichi серии STC2 запускайте \"java -jar TorqueWrenches.jar TohnSTC2\"");
        exit(0);
    }
}
