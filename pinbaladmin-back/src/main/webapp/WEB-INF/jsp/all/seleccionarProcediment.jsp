<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="org.fundaciobit.genapp.common.web.html.IconUtils"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<%@ include
	file="/WEB-INF/jsp/all/tramitModificacioSolicitudsPublic.jsp"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Modificar solicitud</title>


<style>



.procediment-item{
	cursor: pointer;
	padding: 6px;
	background-color: #fff;
}

.procediment-item:hover:hover {
	background-color: #f1f1f1;
}

#autocomplete-procediments {
	display: block;
	position: absolute;
	z-index: 1;
	background-color: #f9f9f9;
	border: 1px solid #e9e9e9;
	max-height: 170px;
	overflow-y: auto;
}

#autocomplete-procediments div {
	padding: 6px;
}


#llistat-procediments ul {
	margin-top: 1rem;
}

.procediment-data-container {
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.procediment-li {
	padding-top: 5px;
	padding-right: 1rem;
	padding-bottom: 5px;
	margin: 2px 0px;
	border: 1px solid white;
}

.procediment-li:hover {
	background-color: #f1f1f1;
	border-bottom-color: black;
	border-top-color: black;
}

.procediment-data-text {
	margin-right: 1rem;
}

.procediment-data-delete {
	cursor: pointer;
	color: #ae0808;
}

#backToList-button-container {
	text-align: right;
	margin: 1rem 5rem;
}

#btn-container {
  text-align: right;
}

</style>


 <style>
    #dadesProcediment { display: none; }
    .error { color: #c00; font-size: 0.95em; margin-top: 8px; }
    .field { margin-bottom: 10px; }
    .btn { cursor: pointer; padding: 8px 12px; border-radius: 4px; border: none; }
    .btn-primary { background: #007bff; color: #fff; }
    .btn-secondary { background: #6c757d; color: #fff; margin-right: 8px; }
  </style>

</head>
<body>

	<div class="container">
		<h3>Seleccionar Procediment</h3>



  <form id="seleccionarProcediment" action="seleccionarProcediment" method="POST" novalidate>
    <!-- Paso 1: Datos de contacto -->
    <div id="dadesContacte" aria-hidden="false">
      <div class="field">
        <label for="nomContacte">Nombre</label><br>
        <input id="nomContacte" name="nomContacte" type="text" autocomplete="name" />
      </div>

      <div class="field">
        <label for="mailContacte">Email</label><br>
        <input id="mailContacte" name="mailContacte" type="email" autocomplete="email" />
      </div>

      <div>
        <button type="button" id="btnSiguiente" class="btn btn-primary">Siguiente →</button>
      </div>

      <div id="errorMsg" class="error" role="alert" aria-live="polite"></div>
    </div>

    <!-- Paso 2: Procedimiento -->
    <div id="dadesProcediment" aria-hidden="true">
      <div class="input-container procediment">
        <div id="cercador-procediments">
          <label for="cercadorProcediment">Procediment</label><br>
          <input id="cercadorProcediment" name="cercadorProcediment" type="text"
                 autocomplete="off" placeholder="Procediment. Mínim 2 caracters..." />
          <div id="autocomplete-procediments" aria-hidden="true"></div>
        </div>

        <div id="llistat-procediments">
          <ul></ul>
        </div>
      </div>

      <div id="btn-container" style="margin-top:12px;">
        <button type="button" id="btnAnterior" class="btn btn-secondary">← Anterior</button>
        <button type="submit" class="btn btn-primary">Continuar</button>
      </div>
    </div>
  </form>
  
  
  
	<%-- 	<form id="seleccionarProcediment" action="seleccionarProcediment"
			method="POST">

			<div id="dadesContacte>
				
				<input id="nomContacte" name="nomContacte"/>				
				
			</div>
			
			<div id="dadesProcediment">
				<div class="input-container procediment">
					<div id="cercador-procediments">
	
						<input id="cercadorProcediment" name="cercadorProcediment" type="text"
							autocomplete="off" class="w-100 form-control"
							placeholder="Procediment. Minim 2 caracters...">
	
						<div id="autocomplete-procediments"></div>
					</div>
					<div id="llistat-procediments">
						<ul></ul>
					</div>
				</div>
	
				<div id="btn-container">
					<div class="btn btn-sm btn-primary" onclick="submitForm();"
						title="<fmt:message key="genapp.continue"/>"> <i
						class="fas fa-arrow-right"></i> <fmt:message key="genapp.continue" />
					</div>
				</div>
			</div>
		</form>
 --%>

		<script type="text/javascript">
			$("#cercadorProcediment").on("input", function() {
				var procediment = $(this).val();
				console.log(procediment);
				if (procediment.length < 2) {
					$("#autocomplete-procediments").empty();
					return;
				}

				$.ajax({
					url : "jsonProcediments",
					type : "GET",
					data : {
						query : procediment
					},
					success : function(data) {
						$("#autocomplete-procediments").empty();
						data.forEach(function(proc) {
							//Si el procediment ja esta a la llista, no el mostri
							afegirProcediment(proc);
						});
					}
				});

			});

			function afegirProcediment(proc) {
				var procedimentDiv = document.createElement("div");
				procedimentDiv.classList.add("procediment-item");
				procedimentDiv.innerHTML = proc.key + " - " + proc.value;
				procedimentDiv.onclick = function() {
					elegirProcediment(proc);
				};
				$("#autocomplete-procediments").append(procedimentDiv);
			}

			function elegirProcediment(proc) {
				let li = $("<li></li>").addClass("procediment-li");
				let container = $("<div></div>").addClass(
						"procediment-data-container");

				let spanText = $("<span></span>").addClass(
						"procediment-data-text").text(
						proc.key + " - " + proc.value);

				let spanDelete = $("<span></span>").addClass(
						"procediment-data-delete").html(
						'<i class="fas fa-times"></i>').click(function() {
					procediments = procediments.filter(function(p) {
						return p.id != proc.id;
					});
					li.remove();
				});


			    let inputValue = document.createElement("input");
			    inputValue.type = "hidden"; // corregido
			    inputValue.value = proc.id;
			    inputValue.name = "solicitudID";

			    container.append(spanText);
			    container.append(spanDelete);

			    li.append(container);
			    li.append(inputValue); // aquí añades el input oculto

			    $("#llistat-procediments ul").html(li); // cuidado: con esto REEMPLAZAS todo el contenido (¿es lo que quieres?)
			    $("input[name='cercadorProcediment']").val("");
			    $("#autocomplete-procediments").empty();
			}
			

			function submitForm() {
				console.log('submitForm');
				document.getElementById('seleccionarProcediment').submit();
			}
		</script>




  <script>
    const form = document.getElementById('seleccionarProcediment');
    const dadesContacte = document.getElementById('dadesContacte');
    const dadesProcediment = document.getElementById('dadesProcediment');
    const btnSiguiente = document.getElementById('btnSiguiente');
    const btnAnterior = document.getElementById('btnAnterior');
    const errorMsg = document.getElementById('errorMsg');

    function validateContact() {
      const nombre = document.getElementById('nomContacte').value.trim();
      const email = document.getElementById('mailContacte').value.trim();

      if (nombre === '') {
        errorMsg.textContent = 'Por favor, introduce tu nombre.';
        document.getElementById('nomContacte').focus();
        return false;
      }

      // Validación básica email (suficiente para la mayoría de casos)
      const emailRe = /^\S+@\S+\.\S+$/;
      if (email === '' || !emailRe.test(email)) {
        errorMsg.textContent = 'Introduce un email válido.';
        document.getElementById('mailContacte').focus();
        return false;
      }

      // pasa validación
      errorMsg.textContent = '';
      return true;
    }

    btnSiguiente.addEventListener('click', () => {
      if (!validateContact()) return;

      // mostrar segunda parte
      dadesContacte.style.display = 'none';
      dadesContacte.setAttribute('aria-hidden', 'true');

      dadesProcediment.style.display = 'block';
      dadesProcediment.setAttribute('aria-hidden', 'false');

      // focus en campo del procedimiento
      const busc = document.getElementById('cercadorProcediment');
      if (busc) busc.focus();
    });

    btnAnterior.addEventListener('click', () => {
      // volver a la primera parte
      dadesProcediment.style.display = 'none';
      dadesProcediment.setAttribute('aria-hidden', 'true');

      dadesContacte.style.display = 'block';
      dadesContacte.setAttribute('aria-hidden', 'false');

      // devolver focus al nombre
      document.getElementById('nomContacte').focus();
    });

    // Por si alguien intenta enviar el formulario directamente (enter/submit),
    // comprobamos de nuevo los datos de contacto y forzamos volver si es necesario.
    form.addEventListener('submit', (e) => {
      if (!validateContact()) {
        e.preventDefault();
        // mostramos la parte de contacto para que corrija
        dadesProcediment.style.display = 'none';
        dadesProcediment.setAttribute('aria-hidden', 'true');
        dadesContacte.style.display = 'block';
        dadesContacte.setAttribute('aria-hidden', 'false');
      }
      // si todo OK, se envía normalmente
    });
  </script>
	</div>


</body>
</html>