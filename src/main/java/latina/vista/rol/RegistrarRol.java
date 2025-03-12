package latina.vista.rol;

public class RegistrarRol {

    private static String htmlString = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "    <title>Pretty Form</title>\n" +
            "    <style>\n" +
            "        * {\n" +
            "            margin: 0;\n" +
            "            padding: 0;\n" +
            "            box-sizing: border-box;\n" +
            "            font-family: 'Arial', sans-serif;\n" +
            "        }\n" +
            "\n" +
            "        body {\n" +
            "            display: flex;\n" +
            "            justify-content: center;\n" +
            "            align-items: center;\n" +
            "            height: 100vh;\n" +
            "            background-color: #f2f2f2;\n" +
            "            background-image: linear-gradient(to right, #5c6bc0, #42a5f5);\n" +
            "        }\n" +
            "\n" +
            "        form {\n" +
            "            background: rgba(255, 255, 255, 0.9);\n" +
            "            padding: 30px;\n" +
            "            border-radius: 10px;\n" +
            "            width: 400px;\n" +
            "            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);\n" +
            "            animation: fadeIn 1s ease-out;\n" +
            "        }\n" +
            "\n" +
            "        label {\n" +
            "            font-size: 16px;\n" +
            "            margin-bottom: 8px;\n" +
            "            color: #555;\n" +
            "            display: block;\n" +
            "            transition: color 0.3s ease;\n" +
            "        }\n" +
            "\n" +
            "        input {\n" +
            "            width: 100%;\n" +
            "            padding: 12px;\n" +
            "            margin-bottom: 20px;\n" +
            "            border: 2px solid #ddd;\n" +
            "            border-radius: 5px;\n" +
            "            font-size: 16px;\n" +
            "            outline: none;\n" +
            "            transition: border-color 0.3s ease, box-shadow 0.3s ease;\n" +
            "        }\n" +
            "\n" +
            "        input:focus {\n" +
            "            border-color: #42a5f5;\n" +
            "            box-shadow: 0 0 8px rgba(66, 165, 245, 0.6);\n" +
            "        }\n" +
            "\n" +
            "        button {\n" +
            "            width: 100%;\n" +
            "            padding: 12px;\n" +
            "            background-color: #42a5f5;\n" +
            "            color: white;\n" +
            "            font-size: 18px;\n" +
            "            border: none;\n" +
            "            border-radius: 5px;\n" +
            "            cursor: pointer;\n" +
            "            transition: background-color 0.3s ease, transform 0.3s ease;\n" +
            "        }\n" +
            "\n" +
            "        button:hover {\n" +
            "            background-color: #5c6bc0;\n" +
            "            transform: scale(1.05);\n" +
            "        }\n" +
            "\n" +
            "        button:active {\n" +
            "            background-color: #42a5f5;\n" +
            "            transform: scale(1);\n" +
            "        }\n" +
            "\n" +
            "        /* Popup Styling */\n" +
            "        .popup-overlay {\n" +
            "            display: none;\n" +
            "            position: fixed;\n" +
            "            top: 0; left: 0;\n" +
            "            width: 100%; height: 100%;\n" +
            "            background: rgba(0, 0, 0, 0.5);\n" +
            "            justify-content: center;\n" +
            "            align-items: center;\n" +
            "            opacity: 0;\n" +
            "            transition: opacity 0.3s ease-in-out;\n" +
            "        }\n" +
            "\n" +
            "        .popup-overlay.show {\n" +
            "            display: flex;\n" +
            "            opacity: 1;\n" +
            "        }\n" +
            "\n" +
            "        .popup-box {\n" +
            "            background: white;\n" +
            "            padding: 20px;\n" +
            "            border-radius: 10px;\n" +
            "            box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.2);\n" +
            "            text-align: center;\n" +
            "            transform: scale(0.8);\n" +
            "            transition: transform 0.3s ease-in-out;\n" +
            "        }\n" +
            "\n" +
            "        .popup-overlay.show .popup-box {\n" +
            "            transform: scale(1);\n" +
            "        }\n" +
            "\n" +
            "        .popup-button {\n" +
            "            margin-top: 15px;\n" +
            "            padding: 10px 20px;\n" +
            "            border: none;\n" +
            "            background-color: #007bff;\n" +
            "            color: white;\n" +
            "            border-radius: 5px;\n" +
            "            cursor: pointer;\n" +
            "        }\n" +
            "\n" +
            "        .popup-button:hover {\n" +
            "            background-color: #0056b3;\n" +
            "        }\n" +
            "    </style>\n" +
            "</head>\n" +
            "<body>\n" +
            "<div class=\"form-container\">\n" +
            "    <form>\n" +
            "        <label for=\"name\">Nombre</label>\n" +
            "        <input type=\"text\" id=\"name\" name=\"name\" required>\n" +
            "\n" +
            "        <label for=\"wage\">Salario</label>\n" +
            "        <input type=\"number\" id=\"wage\" name=\"wage\" required>\n" +
            "\n" +
            "        <button type=\"submit\" onclick=\"recogerDatos()\">Registrar</button>\n" +
            "    </form>\n" +
            "</div>\n" +
            "\n" +
            "<!-- Popup Container -->\n" +
            "<div id=\"popup\" class=\"popup-overlay\">\n" +
            "    <div class=\"popup-box\">\n" +
            "        <p>%s</p>\n" +
            "        <button class=\"popup-button\" onclick=\"closePopup()\">OK</button>\n" +
            "    </div>\n" +
            "</div>\n" +
            "\n" +
            "<script>\n" +
            "    function recogerDatos() {\n" +
            "        var nombre = document.getElementById(\"name\").value;\n" +
            "        var salario = document.getElementById(\"wage\").value;\n" +
            "        enviarAJava(nombre, salario);\n" +
            "    }\n" +
            "\n" +
            "    function enviarAJava(nombre, salario){\n" +
            "        if (window.java && window.java.sendFormData) {\n" +
            "            window.java.sendFormData(nombre, salario);\n" +
            "        }\n" +
            "    }\n" +
            "\n" +
            "    function showPopup() {\n" +
            "        const popup = document.getElementById(\"popup\");\n" +
            "        popup.style.display = \"flex\";\n" +
            "        setTimeout(() => popup.classList.add(\"show\"), 10);\n" +
            "    }\n" +
            "\n" +
            "    function closePopup() {\n" +
            "        const popup = document.getElementById(\"popup\");\n" +
            "        popup.classList.remove(\"show\");\n" +
            "        setTimeout(() => popup.style.display = \"none\", 300);\n" +
            "    }\n" +
            "\n" +
            "%s" +
            "</script>\n" +
            "</body>\n" +
            "</html>\n";

    public static String getHtmlString(boolean popup, String message) {
        if(popup) {
            return String.format(htmlString, message, "    // Show popup automatically when the page loads\n" +
                    "    window.onload = function() {\n" +
                    "        setTimeout(showPopup, 500);\n" +
                    "    };\n" );
        }
        else return String.format(htmlString, "", "");
    }
}
