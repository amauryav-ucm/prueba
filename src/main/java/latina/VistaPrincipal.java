package latina;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import latina.vista.controlador.Controlador;
import latina.vista.Eventos;
import netscape.javascript.JSObject;

import java.io.File;

public class VistaPrincipal extends Application {
    private WebView webView;
    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage primaryStage) {
        // Quitar la barra de título del sistema
        primaryStage.initStyle(StageStyle.UNDECORATED);

        // Crear la barra personalizada con una imagen, texto y botones
        HBox titleBar = new HBox();
        titleBar.setStyle("-fx-background-color: #2a5298; -fx-padding: 8px; -fx-alignment: center-left;");
        titleBar.setSpacing(10);

        // Imagen en la barra de título
        ImageView imageView = new ImageView(new Image("file:src/main/resources/latina/mi_imagen.png"));
        imageView.setFitHeight(30);
        imageView.setPreserveRatio(true);

        // Texto "LaTina"
        Text titleText = new Text("LaTina");
        titleText.setFont(Font.font("Arial", 20));
        titleText.setStyle("-fx-fill: white; -fx-font-weight: bold;");

        // Botón de minimizar
        Button minimizeButton = new Button("-");
        minimizeButton.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 18;");
        minimizeButton.setOnAction(event -> primaryStage.setIconified(true));

        // Botón de cerrar
        Button closeButton = new Button("X");
        closeButton.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 14;");
        closeButton.setOnAction(event -> System.exit(0));

        // Contenedor de botones alineado a la derecha
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox buttonContainer = new HBox(minimizeButton, closeButton);
        buttonContainer.setSpacing(10);

        titleBar.getChildren().addAll(imageView, titleText, spacer, buttonContainer);

        // Permitir mover la ventana al arrastrar la barra
        titleBar.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        titleBar.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });

        // WebView para mostrar la ventana HTML
        webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        File htmlFile = new File("src/main/resources/latina/VentanaPrincipal.html");
        webEngine.load(htmlFile.toURI().toString());

        // Conectar Java con JavaScript en el WebView
        webEngine.setJavaScriptEnabled(true);
        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == javafx.concurrent.Worker.State.SUCCEEDED) {
                JSObject window = (JSObject) webEngine.executeScript("window");
                window.setMember("java", this);
            }
        });

        // Contenedor principal con la barra superior y el WebView
        VBox root = new VBox(titleBar, webView);
        root.setStyle("-fx-border-color: #2a5298; -fx-border-width: 2px;");

        Scene scene = new Scene(root, 900, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void accion(String eventoStr, Object datos)
    {
        Eventos evento = Eventos.valueOf(eventoStr);
        Controlador.getInstance(this).accion(evento, datos);
    }

    public WebView getWebView()
    {
        return webView;
    }

    public void changeScene(String nuevaEscena)
    {
        webView.getEngine().load(new File(nuevaEscena).toURI().toString());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
