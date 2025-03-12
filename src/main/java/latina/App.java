/*package latina;

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

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        // Load an HTML file from the local filesystem
        File htmlFile = new File("src/main/resources/latina/test.html");
        System.out.println(htmlFile.toURI().toString());
        webEngine.load(htmlFile.toURI().toString());

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
        primaryStage.setTitle("JavaFX WebView Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void sendFormData(String nombre, String salario) {
        try {
            System.out.println("HOla");
            System.out.println(nombre.toString() + salario);
            TRol t = new TRol(nombre, Double.parseDouble(salario));
            SARol sa = new SARolImp();
            sa.altaRol(t);
            System.out.println(t.toString());
            System.out.println("Hola2");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
*/