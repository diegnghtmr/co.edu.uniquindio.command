module co.edu.uniquindio.command.command {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.command.command to javafx.fxml;
    exports co.edu.uniquindio.command.command;

    exports co.edu.uniquindio.command.command.viewController;
    opens co.edu.uniquindio.command.command.viewController to javafx.fxml;
}