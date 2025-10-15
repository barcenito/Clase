module org.example.basesnorelacionales {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens org.example.basesnorelacionales to javafx.fxml;
    exports org.example.basesnorelacionales;
	exports org.example.basesnorelacionales.Controllers;
	opens org.example.basesnorelacionales.Controllers to javafx.fxml;
}