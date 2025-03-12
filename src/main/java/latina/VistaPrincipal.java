package latina;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import latina.negocio.factoria.SAFactory;
import latina.negocio.rol.TRol;
import latina.vista.rol.RegistrarRol;
import netscape.javascript.JSObject;

import java.io.File;

public class VistaPrincipal extends Application {

    WebEngine webEngine;
    @Override
    public void start(Stage primaryStage) {
        WebView webView = new WebView();
        webEngine = webView.getEngine();

        // Load an HTML file from the local filesystem
        File htmlFile = new File("src/main/resources/latina/vista/test.html");
        System.out.println(htmlFile.toURI());
        webEngine.load(htmlFile.toURI().toString());
        //webEngine.loadContent(RegistrarRol.getHtmlString(true, "Probando mensaje"));

        webEngine.setJavaScriptEnabled(true);
        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == javafx.concurrent.Worker.State.SUCCEEDED) {
                // Get the JavaScript window object and bind Java method to it
                JSObject window = (JSObject) webEngine.executeScript("window");
                window.setMember("java", this); // Bind this Java object to the JavaScript window object
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(webView);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("LaTina");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void accion(int evento, JSObject param) {
        try {
            TRol rol = new TRol(param.getMember("nombre").toString(),
                    Double.parseDouble(param.getMember("salario").toString()));
            System.out.println(rol.toString());
            int res = SAFactory.getInstance().createSARol().altaRol(rol);
            System.out.println(res);
            if(res >= 0) {
                cambiarVista(true, "Registrado correctamente con id " + res);
            }
            else if(res == -1) {
                cambiarVista(true, "Ya existe un rol con el nombre introducido");
            }
            else if(res == -2) {
                cambiarVista(true, "El salario debe ser positivo");
            }
        } catch (Exception e) {e.printStackTrace();}
    }

    public void cambiarVista(boolean popup, String mensaje){
        String mensajeURL = mensaje.replace(" ","%20");
        File htmlFile = new File("src/main/resources/latina/vista/test.html");
        System.out.println(htmlFile.toURI().toString() + String.format("?popup=%s&mensaje=%s", popup?"true":"false", "hola"));
        webEngine.load(htmlFile.toURI().toString() + String.format("?popup=%s&mensaje=%s", popup?"true":"false", mensajeURL));
    }
}
