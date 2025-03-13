function recogerDatos() {
        var rol = {};
        rol.nombre = document.getElementById("name").value.trim();
        rol.salario = document.getElementById("wage").value.trim();
        if (rol.nombre === "") return;
        if (rol.salario === "" || isNaN(rol.salario) || parseFloat(rol.salario) <= 0) return;
        enviarAJava(rol);
    }

   function enviarAJava(rol){
        if (window.java && window.java.sendFormData) {
            window.java.sendFormData(rol);
        }
    }

    function volverAlaVentanaPrincipal() {
        if (window.java && window.java.changeSceneToMain) {
            window.java.changeSceneToMain(); // Llamar a la función en Java para cambiar de escena
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