module com.project.luigi{
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.project.luigi to javafx.fxml;
    exports com.project.luigi;
    opens com.project.models;
    exports com.project.models;
    opens com.project.dbhandler;
    exports com.project.dbhandler;
}
