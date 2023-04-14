module test.hallohallo {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens test.hallohallo to javafx.fxml;
    exports test.hallohallo;
}