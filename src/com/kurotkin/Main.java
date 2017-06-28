package com.kurotkin;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Settings.loadSettings();
        ArrayList<Fastener> fasteners = Connecter.Stahlwille();

    }
}
