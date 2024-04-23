module co.edu.uniquindio.exposicion.app_gestionarchivos {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.exposicion.app_gestionarchivos to javafx.fxml;
    exports co.edu.uniquindio.exposicion.app_gestionarchivos;

    opens co.edu.uniquindio.exposicion.app_gestionarchivos.viewController;
    exports co.edu.uniquindio.exposicion.app_gestionarchivos.viewController;
}