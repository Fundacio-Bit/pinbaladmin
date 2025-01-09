<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="org.fundaciobit.genapp.common.web.html.IconUtils"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<div>

<!-- S'ha de fer un formulari que vagi al llistat, amb un camp que serà un nom d'usuari. Abans de fer el submit, s'ha de comprovar via ajax si l'usuari existeix, i nomes fer submit si te permisos. Sino, mostrar un alert indicant que l'usuari no el tenim -->

<form id="userForm" action="permisos" method="POST">
    <label for="username">Nom d'usuari:</label>
    <input type="text" id="username" name="username" required>
    <button type="submit">Submit</button>
</form>

<!-- Espai per mostrar alertas -->
<div id="alert" style="display: none; color: red;"></div>

</div>

<script>
$(document).ready(function() {
    document.getElementById("userForm").onsubmit = function(event) {
        event.preventDefault(); // Evitar el envío del formulario
        alert('Validant usuari');
        validateUser(this);
    };
    
    function validateUser(form) {
        var username = document.getElementById("username").value;
        
        $.ajax({
            url: 'validateUser',
            type: 'GET',
            data: {
                username: username
            },
            success: function(data) {
                if (data.exists) {
                    alert('Usuari existeix: ' + data.data);

                    //Cambiamos el valor del campo username para que se envie en el submit
                    document.getElementById("username").value = data.data;
                    form.submit();
                } else {
                    $('#alert').text('L\'usuari no existeix');
                    $('#alert').show();
                }
            },
            error: function() {
                alert('Error');
            }
        });
    }
});


</script>