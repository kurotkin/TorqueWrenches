package com.kurotkin;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.logging.Logger;
import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
/**
 * Created by Vitaly Kurotkin on 27.06.2017.
 */
public class Settings {
    // Настройки по умолчанию
    public static String sqliteUrl = "C:/ProgramData/STAHLWILLE/Sensomaster4/sensomaster4.db";
    public static String dbUrl = "C:/ProgramData/STAHLWILLE/Sensomaster4/Database.accdb";
    public static String stahlwilleReportsUrl = "C:/StahlwilleReports";
    public static String tohnichiReportsUrl = "C:/StahlwilleReports";
    public static String XMLUrl = "C:/zxy";
    public static int bSizeCEM = 31;
    public static int bSizeSTC2 = 15;
    public static String portName = "COM4";
    private static Logger log = Logger.getLogger(Settings.class.getName());

    // Параметры
    public static DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    public static DateFormat dateFormatForName = new SimpleDateFormat("yyyyMMddHHmmss");

    public static void loadSettings() {
        try {
            YamlReader reader = new YamlReader(new FileReader("settings.yml"));
            Object object = reader.read();
            Map map = (Map)object;
            sqliteUrl = map.get("sqliteUrl").toString();
            dbUrl = map.get("dbUrl").toString();
            stahlwilleReportsUrl = map.get("StahlwilleReportsUrl").toString();
            tohnichiReportsUrl = map.get("TohnichiReportsUrl").toString();
            XMLUrl = map.get("XMLUrl").toString();
            bSizeCEM = Integer.parseInt(map.get("bSizeCEM").toString());
            bSizeSTC2 = Integer.parseInt(map.get("bSizeSTC2").toString());
            portName = map.get("portName").toString();
            log.info("Настройки загружены");
        } catch (FileNotFoundException e) {
            log.warning(e.toString());
            e.printStackTrace();
        } catch (YamlException e) {
            log.warning(e.toString());
            e.printStackTrace();
        }
    }
}
