function recogerDatos() {
        var rol = {};
        rol.nombre = document.getElementById("name").value.trim();
        rol.salario = document.getElementById("wage").value.trim();
        if (rol.nombre === "") return;
        if (rol.salario === "" || isNaN(rol.salario)) return;
        enviarAJava(rol);
    }

   function enviarAJava(rol){
        if (window.java && window.java.accion) {
                    window.java.accion("REGISTRAR_ROL", rol);
                }
    }

    function volverAlaVentanaPrincipal() {
         if (window.java && window.java.changeScene) {
                    window.java.changeScene("src/main/resources/latina/html/VentanaPrincipal.html"); // Llamar a la función en Java para cambiar de escena
                }
    }

    function mostrarMensaje(mensaje) {
        const popup = document.getElementById("popup");
        popup.style.display = "flex";
        document.getElementById("popup-message").innerText = mensaje;
        setTimeout(() => popup.classList.add("show"), 10);
    }

    function cerrarMensaje() {
        const popup = document.getElementById("popup");
        popup.classList.remove("show");
        setTimeout(() => popup.style.display = "none", 300);
    }