package com.kurotkin;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
/**
 * Created by Vitaly Kurotkin on 27.06.2017.
 */
public class Settings {
    // Настройки по умолчанию
    public static String sqliteUrl = "C:/ProgramData/STAHLWILLE/Sensomaster4/sensomaster4.db";
    public static String dbUrl = "C:/ProgramData/STAHLWILLE/Sensomaster4/Database.accdb";
    public static String prUrl = "C:/StahlwilleReports";
    public static String XMLUrl = "C:/zxy";

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
            prUrl = map.get("prUrl").toString();
            XMLUrl = map.get("XMLUrl").toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (YamlException e) {
            e.printStackTrace();
        }
    }
}
