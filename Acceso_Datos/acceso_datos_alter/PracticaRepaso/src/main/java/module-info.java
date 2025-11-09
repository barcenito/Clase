module org.example.practicarepaso {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens org.example.practicarepaso to javafx.fxml;
    exports org.example.practicarepaso;
}