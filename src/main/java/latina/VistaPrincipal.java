package latina;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import latina.negocio.rol.SARol;
import latina.negocio.rol.TRol;
import latina.negocio.rol.imp.SARolImp;
import netscape.javascript.JSObject;

import java.io.File;

public class VistaPrincipal extends Application {
    private WebView webView;

    @Override
    public void start(Stage primaryStage) {

        // Crear la ventana principal con el WebView para cargar el archivo HTML
        StackPane root = new StackPane();
        webView = new WebView();
        WebEngine webEngine1 = webView.getEngine();

        // Cargar el archivo HTML para la ventana principal (con el botón)
        File htmlFile1 = new File("src/main/resources/latina/VentanaPrincipal.html");
        webEngine1.load(htmlFile1.toURI().toString());

        webEngine1.setJavaScriptEnabled(true);
        webEngine1.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == javafx.concurrent.Worker.State.SUCCEEDED) {
                // Vincular el objeto Java a la ventana de JavaScript
                JSObject window = (JSObject) webEngine1.executeScript("window");
                window.setMember("java", this); // Vincular el objeto Java a la ventana de JavaScript
            }
        });

        root.getChildren().add(webView);

        WebEngine webEngine = webView.getEngine();

        webEngine.setJavaScriptEnabled(true);
        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == javafx.concurrent.Worker.State.SUCCEEDED) {
                // Get the JavaScript window object and bind Java method to it
                JSObject window = (JSObject) webEngine.executeScript("window");
                window.setMember("java", this); // Bind this Java object to the JavaScript window object
            }
        });

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("LaTina");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void sendFormData(JSObject datos) {
        try {
            // Mostrar los datos recibidos en la consola
            System.out.println("Datos recibidos: " + datos.getMember("nombre") + " " + datos.getMember("salario"));

            // Crear un nuevo objeto TRol con los datos del formulario
            TRol t = new TRol(datos.getMember("nombre").toString(),
                    Double.parseDouble(datos.getMember("salario").toString()));

            // Registrar el rol (puedes modificar esta parte según tu lógica de negocio)
            SARol sa = new SARolImp();
            int result = sa.altaRol(t);

            String mensaje;

            if(result >= 0) mensaje = "Se ha registrado el rol correctamente con ID: " + result;
            else if (result == -1) mensaje = "Ya existe un rol con el nombre introducido";
            else if(result == -2) mensaje = "El salario debe ser un número positivo";
            else {
                mensaje = "";
            }

            WebEngine webEngine = webView.getEngine();
            String finalMensaje = mensaje;
            webEngine.documentProperty().addListener((obs, oldDoc, newDoc) -> {
                if (newDoc != null) {
                    webEngine.executeScript(String.format("mostrarMensaje('%s')", finalMensaje));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método que es llamado desde JavaScript para cambiar a la segunda escena
    public void changeSceneToForm() {
        // Cambiar a la segunda escena con el formulario
        WebEngine webEngine = webView.getEngine();

        File htmlFile2 = new File("src/main/resources/latina/registrarRol.html");
        webEngine.load(htmlFile2.toURI().toString());
    }


    public static void main(String[] args) {
        launch(args);
    }
}
