module org.example.simplebrowser {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.web;


    opens org.example.simplebrowser to javafx.fxml;
    exports org.example.simplebrowser;
}