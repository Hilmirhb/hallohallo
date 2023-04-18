module test.hallohallo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens test.hallohallo to javafx.fxml;
    exports test.hallohallo;
}