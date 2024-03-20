<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<c:if test="${isPublic == 'true'}">
	<%@ include file="/WEB-INF/jsp/all/tramitSistraPublic.jsp"%>

	<style>
form .lead {
	display: flex;
	flex-direction: row-reverse;
}

.titol_div {
	text-align: center;
	margin-bottom: 10px;
}

.tab_container {
	padding-top: 10px;
}

.row {
	margin: 0px;
}
</style>

	<script type="text/javascript">
		var form = $("form")
		var childrens = form[0].children

		var tab_container = document.createElement("div");
		tab_container.classList = "tab_container";

		var module_content = document.createElement("div");
		module_content.classList = "module_content";

		var titol_div = document.createElement("div");
		titol_div.classList = "titol_div";

		var botonera_tab_div = document.createElement("div");
		botonera_tab_div.classList = "botonera_tab_div";

		var footer = $(".lead");
		var titol = footer.find("label")[0];
		titol_div.append(titol);

		var idx = 0;
		while (childrens.length != 0) {
			var child = childrens[idx];
			tab_container.append(child);
		}

		var botonera = footer.find("a")

		for (let i = 0; i < botonera.length; i++) {
			var btn = botonera[i];

			if (btn.href.indexOf("/tramiti/new") > 0) {
				botonera_tab_div.append(btn);
				console.log(btn);
			}

			if (btn.outerHTML.indexOf("/deleteSelected") > 0) {
				botonera_tab_div.append(btn);
				console.log(btn);
			}
		}

		tab_container.append(botonera_tab_div);

		module_content.append(tab_container);

		form.append(titol_div);
		form.append(module_content);
		form.append(footer);

		$("#tramitIServ_pagination").hide();
	</script>



</c:if>