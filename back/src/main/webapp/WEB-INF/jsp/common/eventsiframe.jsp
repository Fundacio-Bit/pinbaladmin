<%@ page contentType="text/html; charset=UTF-8" language="java"
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
                                
<style>

.mainwindow {
background-color: rgb(222, 233, 244);
border-bottom-color: rgb(255, 255, 255);
border-bottom-left-radius: 7px;
border-bottom-right-radius: 7px;
border-bottom-style: solid;
border-bottom-width: 3px;
border-image-outset: 0;
border-image-repeat: stretch;
border-image-slice: 100%;
border-image-source: none;
border-image-width: 1;
border-left-color: rgb(255, 255, 255);
border-left-style: solid;
border-left-width: 3px;
border-right-color: rgb(255, 255, 255);
border-right-style: solid;
border-right-width: 3px;
border-top-color: rgb(255, 255, 255);
border-top-left-radius: 7px;
border-top-right-radius: 7px;
border-top-style: solid;
border-top-width: 3px;
box-shadow: rgba(0, 0, 0, 0.4) 1px 2px 5px 0px;
color: rgb(89, 89, 89);
font-family: Arial, "Helvetica Neue", Helvetica, sans-serif;
font-size: 12px;
margin-bottom: 5px;
margin-left: 5px;
margin-right: 5px;
margin-top: 5px;
overflow: hidden;
overflow-x: hidden;
overflow-y: hidden;
width: auto;
height:inherit;
}


</style>


<div class="mainwindow" >

 <table>
    <tr>
      <td><img src="<c:url value="/img/caibbg.png"/>"/>
    </td>
    <td>
        <h3 class="windowTitle">Sol·licitud</h3>
    <h4 class="windowSubtitle">${titol}</h4>
    
    </td>
    </tr>
    <tr>
    <td>&nbsp;&nbsp;</td>
     <td>
     <div>
     
        <a  class="btn btn-small " role="button" data-toggle="modal"
            href="<c:url value="${contextweb}/new"/>"> <i class="icon-plus-sign"></i>
                Nova Entrada
        </a>

        <c:if test="${!isPublic}">
        &nbsp;  
        <a  class="btn btn-small btn-warning" role="button" data-toggle="modal"
            href="<c:url value="${contextweb}/enviarcorreu/${ID}"/>"> <i class="icon-envelope"></i>
                Enviar aquesta p&agrave;gina al Contacte
        </a>
        </c:if>
     </div>
     </td>
     </tr>
 </table>



        

</div>

 <iframe id="myiframe" scrolling="no" src="${iframe}" frameborder="0" style="overflow:hidden;height:auto;width:100%;min-height: 400px;" height="auto" width="100%"></iframe>

<script type="text/javascript" language="javascript">
    var lastSize = 0;

    function checkIframeSize() {
        setTimeout(checkIframeSize, 1000);
        var iframe = document.getElementById('myiframe');
        var iframeDocument = iframe.contentDocument || iframe.contentWindow.document;

        var h1 = $(iframeDocument.body).height();
        var h2 = iframeDocument.body.scrollHeight;
        var h = Math.max(h1,h2);

        var log = false;

        var d = new Date();
        if (log) {
            console.log("================ " + d + " (H = " + h +" | H1= " + h1 + " | H2= " + h2 + ") ===================");
        }

        if (h != lastSize) {
            h = h + 100;
            lastSize = h;
            if (log) {
              console.log(" checkIframeSize()::iframeDocument.body.scrollHeight = " + iframeDocument.body.scrollHeight);
              console.log(" checkIframeSize()::$(iframeDocument.body).height() = " + $(iframeDocument.body).height());
              console.log(" checkIframeSize()::$(TABLE).height() = " + $("#tablefull").height());
              console.log(" checkIframeSize():: SET " + h);
            }
            document.getElementById('myiframe').style.height=h + "px";
            lastSize =  Math.max($(iframeDocument.body).height(),iframeDocument.body.scrollHeight); <%--  $("#tablefull").height() --%>
            if (log) {
              console.log(" checkIframeSize():: GET " + lastSize);
            }
        }
    }
    
    $(document).ready(function ()  {
        setTimeout(checkIframeSize, 1000);
      });
</script>


