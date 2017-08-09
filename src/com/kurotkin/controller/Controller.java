package com.kurotkin.controller;

import com.kurotkin.model.Fastener;
import com.kurotkin.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Created by Vitaly Kurotkin on 01.08.2017.
 * http://devcolibri.com/3160
 */
public class Controller {
    private ObservableList<Fastener> fasteners = FXCollections.observableArrayList();

    @FXML
    private TableView<Fastener> tableFasteners;

    @FXML
    private TableColumn<Fastener, Integer> idColumn;

    @FXML
    private TableColumn<Fastener, String> dateColumn;

    @FXML
    private TableColumn<Fastener, Double> torqueColumn;

    @FXML
    private TableColumn<Fastener, String> resultColumn;

    // инициализируем форму данными
    @FXML
    private void initialize() {
        fasteners.addAll(Main.fasteners);

        // устанавливаем тип и значение которое должно хранится в колонке
        idColumn.setCellValueFactory(new PropertyValueFactory<Fastener, Integer>("id"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Fastener, String>("dat"));
        torqueColumn.setCellValueFactory(new PropertyValueFactory<Fastener, Double>("torque"));
        resultColumn.setCellValueFactory(new PropertyValueFactory<Fastener, String>("result"));

        // заполняем таблицу данными
        tableFasteners.setItems(fasteners);

    }
}
