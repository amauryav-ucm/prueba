open module latina {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires javafx.web;
    requires jdk.jsobject;

    exports latina.negocio.rol to org.eclipse.persistence.core;
    exports latina;
}
