package latina;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import latina.negocio.rol.SARol;
import latina.negocio.rol.TRol;
import latina.negocio.rol.imp.SARolImp;
import netscape.javascript.JSObject;
import org.w3c.dom.Document;

import java.io.File;

public class VistaPrincipal extends Application {
    private WebView webView;
    private double xOffset = 0;
    private double yOffset = 0;
    private final Color PRIMARY_COLOR = Color.web("#2a5298");
    private final Color HOVER_COLOR = Color.web("#3a62a8");
    private final Color TEXT_COLOR = Color.WHITE;

    @Override
    public void start(Stage primaryStage) {
        // Quitar la barra de título del sistema
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("LaTina - Gestión del Restaurante");

        // Crear la barra personalizada
        HBox titleBar = createTitleBar(primaryStage);

        // WebView para mostrar la ventana HTML
        webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        File htmlFile = new File("src/main/resources/latina/VentanaPrincipal.html");
        webEngine.load(htmlFile.toURI().toString());

        // Para asegurar que el webView se estire con la ventana
        VBox.setVgrow(webView, Priority.ALWAYS);
        HBox.setHgrow(webView, Priority.ALWAYS);

        // Conectar Java con JavaScript en el WebView
        configureJavaScriptBridge(webEngine);

        // Contenedor principal con la barra superior y el WebView
        VBox root = new VBox(titleBar, webView);
        root.setStyle("-fx-border-color: #2a5298; -fx-border-width: 2px;");

        // Asegurar que root ocupe todo el espacio disponible
        root.prefWidthProperty().bind(primaryStage.widthProperty());
        root.prefHeightProperty().bind(primaryStage.heightProperty());

        Scene scene = new Scene(root, 900, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Listener para redimensionar componentes cuando cambia el tamaño de la ventana
        primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> {
            // Podríamos añadir aquí más ajustes específicos si son necesarios
        });

        primaryStage.heightProperty().addListener((obs, oldVal, newVal) -> {
            // Podríamos añadir aquí más ajustes específicos si son necesarios
        });
    }

    private HBox createTitleBar(Stage primaryStage) {
        HBox titleBar = new HBox();
        titleBar.setPadding(new Insets(8));
        titleBar.setSpacing(10);
        titleBar.setAlignment(Pos.CENTER_LEFT);
        titleBar.setStyle("-fx-background-color: #2a5298;");

        // Esta línea asegura que la barra se extienda por todo el ancho
        HBox.setHgrow(titleBar, Priority.ALWAYS);

        // Texto "LaTina"
        Text titleText = new Text("Gestionando LaTina");
        titleText.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
        titleText.setFill(TEXT_COLOR);

        // Separador para empujar los botones de control a la derecha
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // Botones de control de ventana
        Button minimizeButton = createWindowControlButton("-", e -> primaryStage.setIconified(true));
        Button maximizeButton = createWindowControlButton("□", e -> {
            if (primaryStage.isMaximized()) {
                primaryStage.setMaximized(false);
            } else {
                primaryStage.setMaximized(true);
            }
        });
        Button closeButton = createWindowControlButton("X", e -> System.exit(0));

        // Configurar eventos para mover la ventana
        setupWindowDragListeners(titleBar, primaryStage);

        // Agregar elementos a la barra (sin los botones de navegación)
        titleBar.getChildren().addAll(
                titleText,
                spacer,
                minimizeButton, maximizeButton, closeButton
        );

        return titleBar;
    }

    private Button createWindowControlButton(String text, javafx.event.EventHandler<javafx.event.ActionEvent> action) {
        Button button = new Button(text);
        button.setFont(Font.font("Helvetica", FontWeight.NORMAL, 12));
        button.setTextFill(TEXT_COLOR);
        button.setStyle("-fx-background-color: transparent; -fx-min-width: 24px; -fx-min-height: 24px; -fx-cursor: hand;");
        button.setOnAction(action);

        // Efectos especiales para botones de control
        if (text.equals("X")) {
            button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #e81123; -fx-min-width: 24px; -fx-min-height: 24px; -fx-cursor: hand;"));
        } else {
            button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: " + HOVER_COLOR.toString().replace("0x", "#") + "; -fx-min-width: 24px; -fx-min-height: 24px; -fx-cursor: hand;"));
        }
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: transparent; -fx-min-width: 24px; -fx-min-height: 24px; -fx-cursor: hand;"));

        return button;
    }

    private void setupWindowDragListeners(HBox titleBar, Stage primaryStage) {
        titleBar.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        titleBar.setOnMouseDragged(event -> {
            // Solo mover si no está maximizada
            if (!primaryStage.isMaximized()) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });

        // Doble clic para maximizar/restaurar
        titleBar.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                if (primaryStage.isMaximized()) {
                    primaryStage.setMaximized(false);
                } else {
                    primaryStage.setMaximized(true);
                }
            }
        });
    }

    private void configureJavaScriptBridge(WebEngine webEngine) {
        webEngine.setJavaScriptEnabled(true);
        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == javafx.concurrent.Worker.State.SUCCEEDED) {
                JSObject window = (JSObject) webEngine.executeScript("window");
                window.setMember("java", this);
            }
        });
    }

    public void sendFormData(JSObject datos) {
        try {
            TRol t = new TRol(datos.getMember("nombre").toString(),
                    Double.parseDouble(datos.getMember("salario").toString()));

            SARol sa = new SARolImp();
            int result = sa.altaRol(t);

            String mensaje;
            if (result >= 0) mensaje = "Se ha registrado el rol correctamente con ID: " + result;
            else if (result == -1) mensaje = "Ya existe un rol con el nombre introducido";
            else if (result == -2) mensaje = "El salario debe ser un número positivo";
            else if (result == -3) mensaje = "El nombre no puede tener mayúsculas, números o caracteres especiales";
            else mensaje = "";

            WebEngine webEngine = webView.getEngine();
            String finalMensaje = mensaje;
            webEngine.documentProperty().addListener(new ChangeListener<Document>() {
                @Override
                public void changed(ObservableValue<? extends Document> obs, Document oldDoc, Document newDoc) {
                    if (newDoc != null) {
                        webEngine.executeScript(String.format("mostrarMensaje('%s')", finalMensaje));
                        webEngine.documentProperty().removeListener(this);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            showError("Error al procesar los datos: " + e.getMessage());
        }
    }

    public void changeSceneToForm() {
        webView.getEngine().load(new File("src/main/resources/latina/registrarRol.html").toURI().toString());
    }

    public void changeSceneToMain() {
        webView.getEngine().load(new File("src/main/resources/latina/VentanaPrincipal.html").toURI().toString());
    }

    private void showError(String message) {
        WebEngine webEngine = webView.getEngine();
        webEngine.executeScript(String.format("alert('%s')", message.replace("'", "\\'")));
    }

    public static void main(String[] args) {
        launch(args);
    }
}