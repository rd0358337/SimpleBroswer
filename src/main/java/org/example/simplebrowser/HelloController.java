package org.example.simplebrowser;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    //declaring variables
    @FXML
    private WebView webView;
    @FXML
    private TextField textField;

    private WebEngine engine;
    private WebHistory history;

    private String homePage;
    private double webZoom;
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        engine = webView.getEngine();
        homePage = "www.google.com";
        textField.setText(homePage);
        webZoom = 1;
        loadPage();

    }
    //gets text from textfield
    public void loadPage() {

        //engine.load("https://www.google.com");
        engine.load("https://" + textField.getText());

    }

    public void refreshPage() {
        engine.reload();
    }
    //zooms in by .25 every time zoom is clicked
    public void zoomIn(){
        webZoom += 0.25;
        webView.setZoom(webZoom);
    }
    //zooms out by .25 every time zoom out is clicked
    public void zoomOut(){
        webZoom -= 0.25;
        webView.setZoom(webZoom);

    }
    //history info displayed in terminal
    public void displayHistory() {
        history = engine.getHistory();
        ObservableList<WebHistory.Entry> entries = history.getEntries();
        //output in terminal for visited sites and date visited
        for(WebHistory.Entry entry : entries) {
            System.out.println(entry.getUrl() + " " + entry.getLastVisitedDate());
        }
    }
    //goes to previously visited website
    public void back() {
        history = engine.getHistory();
        ObservableList<WebHistory.Entry> entries = history.getEntries();
        history.go(-1);
        textField.setText(entries.get(history.getCurrentIndex()).getUrl());

    }

    public void forward() {
        history = engine.getHistory();
        ObservableList<WebHistory.Entry> entries = history.getEntries();
        history.go(1);
        textField.setText(entries.get(history.getCurrentIndex()).getUrl());

    }
    //takes user to youtube
    public void executeJS() {
        engine.executeScript("window.location = \"https://www.youtube.com\";");
    }


}