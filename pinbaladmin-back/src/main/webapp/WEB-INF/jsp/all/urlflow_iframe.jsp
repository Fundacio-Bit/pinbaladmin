<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<div id="firmaFluxContainer">

    <c:if test="${not empty urlflow}">
        <center>
            <iframe id="iframediv" src="${urlflow}" width="100%" height="900px" onload="miFuncionTest(this);" ></iframe>
        </center>
    </c:if>

    <c:if test="${empty urlflow}">
        <%@ include file="/WEB-INF/jsp/webdb/pinfoForm.jsp"%>
    </c:if>

</div>

<style>
#iframediv {
    border: none;
}
</style>

<script type="text/javascript">
    var firmaFluxContainer = document.getElementById('firmaFluxContainer');
    firmaFluxContainer.parentElement.style.padding = "0px";

    function miFuncionTest(elem){
        console.log('miFuncionTest');
        var iframeDocument = elem.contentDocument || elem.contentWindow.document;
        console.log(iframeDocument);
        
        // Esperar hasta que el iframe esté completamente cargado
        iframeDocument.addEventListener("DOMContentLoaded", function() {
            let h6 = iframeDocument.querySelectorAll("h6");
            console.log(h6);

            h6.forEach(function(element) {
                element.style.lineHeight = "1.5";
                element.style.backgroundColor = "red";
            });
        });
    }
</script>
