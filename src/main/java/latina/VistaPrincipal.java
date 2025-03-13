package latina;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        // Crear la ventana principal con el WebView para cargar el archivo HTML
        StackPane root1 = new StackPane();
        WebView webView1 = new WebView();
        WebEngine webEngine1 = webView1.getEngine();

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

        root1.getChildren().add(webView1);

        Scene scene = new Scene(root1, 800, 600);
        primaryStage.setTitle("JavaFX WebView Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void sendFormData(String nombre, String salario) {
        try {
            // Mostrar los datos recibidos en la consola
            System.out.println("Datos recibidos: " + nombre + " " + salario);

            // Crear un nuevo objeto TRol con los datos del formulario
            TRol t = new TRol(nombre, Double.parseDouble(salario));

            // Registrar el rol (puedes modificar esta parte según tu lógica de negocio)
            SARol sa = new SARolImp();
            int id = sa.altaRol(t);
            // Variables para cambiar la escena después
            StackPane root1 = new StackPane();
            WebView webView1 = new WebView();
            WebEngine webEngine1 = webView1.getEngine();
            File htmlFile1 = new File("src/main/resources/latina/test.html");

            if (id > 0)
            {
                // Mostrar información del rol registrado
                System.out.println(t);
                htmlFile1 = new File("src/main/resources/latina/VentanaPrincipal.html");
            }
            else
            {
                String mensajeError = "Error desconocido";
                if(id == -1)
                {
                    /////////OP1 : AQUI PONER VISTA DE ERROR DE NOMBRE DE ROL REPETIDO
                    //htmlFile1 = new File("src/main/resources/latina/ErrorNombre.html");
                    ////////OP2 : POSIBLE VENTANA EMERGENTE:
                    mensajeError = "El nombre introducido ya existe. Por favor intente otro distinto.";
                    System.out.println("Nombre introducido repetido");

                }
                else if(id == -2)
                {
                    //OP1 : AQUI PONER VISTA DE ERROR DE SALARIO MENOS O IGUAL QUE 0
                    //htmlFile1 = new File("src/main/resources/latina/ErrorSalario.html");
                    ////////OP2 POSIBLE VENTANA EMERGENTE:
                    mensajeError = "El salario debe ser mayor que 0.";
                    System.out.println("Salario introducido negativo");

                }
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Datos no validos");
                alert.setContentText("Detalles del error: " + mensajeError);
                // Mostrar la alerta
                alert.showAndWait();
            }

            // Cargar el archivo HTML para la ventana principal (con el botón)
            webEngine1.load(htmlFile1.toURI().toString());

            webEngine1.setJavaScriptEnabled(true);
            webEngine1.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue == javafx.concurrent.Worker.State.SUCCEEDED) {
                    JSObject window = (JSObject) webEngine1.executeScript("window");
                    window.setMember("java", this); // Vincular el objeto Java a la ventana de JavaScript
                }
            });

            root1.getChildren().add(webView1);

            Scene scene1 = new Scene(root1, 800, 600);  // Primera escena con el botón

            // Cambiar a la primera escena
            primaryStage.setScene(scene1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método que es llamado desde JavaScript para cambiar a la segunda escena
    public void changeSceneToForm() {
        // Cambiar a la segunda escena con el formulario
        StackPane root2 = new StackPane();
        WebView webView2 = new WebView();
        WebEngine webEngine2 = webView2.getEngine();

        File htmlFile2 = new File("src/main/resources/latina/test.html");
        webEngine2.load(htmlFile2.toURI().toString());

        webEngine2.setJavaScriptEnabled(true);

        // Asegurarse de que la referencia Java esté correctamente vinculada en la nueva escena
        webEngine2.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == javafx.concurrent.Worker.State.SUCCEEDED) {
                JSObject window = (JSObject) webEngine2.executeScript("window");
                window.setMember("java", this); // Vincular nuevamente el objeto Java
            }
        });

        root2.getChildren().add(webView2);

        Scene scene2 = new Scene(root2, 800, 600);  // Nueva escena con el formulario
        primaryStage.setScene(scene2);
    }

    public void changeSceneToMain()
    {
        StackPane root3 = new StackPane();
        WebView webView3 = new WebView();
        WebEngine webEngine3 = webView3.getEngine();

        // Cargar el archivo HTML para la ventana principal (con el botón)
        File htmlFile3 = new File("src/main/resources/latina/VentanaPrincipal.html");
        webEngine3.load(htmlFile3.toURI().toString());

        webEngine3.setJavaScriptEnabled(true);
        webEngine3.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == javafx.concurrent.Worker.State.SUCCEEDED) {
                JSObject window = (JSObject) webEngine3.executeScript("window");
                window.setMember("java", this); // Vincular el objeto Java a la ventana de JavaScript
            }
        });

        root3.getChildren().add(webView3);

        Scene scene3 = new Scene(root3, 800, 600);  // Primera escena con el botón

        // Cambiar a la primera escena
        primaryStage.setScene(scene3);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
