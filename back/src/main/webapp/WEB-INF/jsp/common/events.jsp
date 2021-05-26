<%@ page contentType="text/html; charset=UTF-8" language="java"%><%@ include
    file="/WEB-INF/jsp/moduls/includes.jsp"%>

<link rel="stylesheet" type="text/css" media="all"
    href="<c:url value="/ayuda/css/morfos/reset.css"/>">
<link rel="stylesheet" type="text/css" media="all"
    href="<c:url value="/ayuda/css/morfos/main.css"/>">
<link rel="stylesheet" type="text/css" media="all"
    href="<c:url value="/ayuda/css/morfos/typography.css"/>">
<link rel="stylesheet" type="text/css" media="all"
    href="<c:url value="/ayuda/css/morfos/libraries.css"/>">
<link rel="stylesheet" type="text/css" media="all"
    href="<c:url value="/ayuda/css/morfos/components.css"/>">
<link rel="stylesheet" type="text/css" media="all"
    href="<c:url value="/ayuda/css/morfos/colors.css"/>">
<link rel="stylesheet" type="text/css" media="all"
    href="<c:url value="/ayuda/css/morfos/grid-min.css"/>">
<link rel="stylesheet" type="text/css" media="all"
    href="<c:url value="/ayuda/css/morfos/menus.css"/>">
<link rel="stylesheet" type="text/css" media="screen"
    href="<c:url value="/ayuda/css/frontend/custom.css"/>">
<link rel="stylesheet" type="text/css" media="screen"
    href="<c:url value="/ayuda/css/jquery/jquery-ui.css"/>">
<link rel="stylesheet" type="text/css" media="screen"
    href="<c:url value="/ayuda/css/timeline/timeline.css"/>">
    

 
<script type="text/javascript" src="<c:url value="/ayuda/js/jquery/jquery.js"/>"></script>
     
<script type="text/javascript" src="<c:url value="/ayuda/js/jquery/jquery-ui.js"/>"></script>

<script type="text/javascript" src="<c:url value="/ayuda/js/morfos/main.js"/>"></script>


<style>
.full-width {
 max-width: 1200px;
 margin: 0 auto;
 /*background-color:#CFCFC4;*/
 border-left: 1px solid #C0C6BD;
 border-right: 1px solid #C0C6BD;
 border-top: 1px solid #C0C6BD;
 border-bottom: 1px solid #C0C6BD;
 padding: 15px;
}

.timeline-content {
 height: auto !important;
}

.timeline-date {
 width: 250px !important;
 font-size: 10pt;
}

.label-custom {
 border-radius: 0.25em;
 color: #fff;
 display: inline;
 font-weight: 700;
 line-height: 1;
 padding: 0.2em 0.6em 0.3em;
 text-align: center;
 vertical-align: baseline;
 white-space: nowrap;
 background-color: #A3A3A3;
}


.minibutton {
  appearance: button;
 
 background-color: rgb(218, 79, 73);
background-image: linear-gradient(rgb(238, 95, 91), rgb(189, 54, 47));
 
  background-repeat: repeat-x;border-bottom-color: rgba(0, 0, 0, 0.25);border-bottom-left-radius: 3px;
  border-bottom-right-radius: 3px;border-bottom-style: solid;border-bottom-width: 1px;border-image-outset: 0;border-image-repeat: stretch;
  border-image-slice: 100%;border-image-source: none;border-image-width: 1;border-left-color: rgba(0, 0, 0, 0.1);border-left-style: solid;
  border-left-width: 1px;border-right-color: rgba(0, 0, 0, 0.1);border-right-style: solid;border-right-width: 1px;border-top-color: rgba(0, 0, 0, 0.1);
  border-top-left-radius: 3px;border-top-right-radius: 3px;border-top-style: solid;border-top-width: 1px;
  box-shadow: rgba(255, 255, 255, 0.2) 0px 1px 0px 0px inset, rgba(0, 0, 0, 0.05) 0px 1px 2px 0px;color: rgb(255, 255, 255);
  cursor: pointer;display: inline-block;font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;font-size: 10.5px;font-weight: 400;line-height: 20px;
  margin-bottom: 0px;margin-left: 0px;margin-right: 0px;margin-top: 0px;
  padding-bottom: 4px;padding-left: 4px;padding-right: 4px;padding-top: 4px;
  text-align: center;text-shadow: rgba(0, 0, 0, 0.25) 0px -1px 0px;vertical-align: middle;
}


.minibutton1 {
  appearance: button;

 background-color: rgb(0, 109, 204);background-image: linear-gradient(rgb(0, 136, 204), rgb(0, 68, 204)); 
  

  background-repeat: repeat-x;border-bottom-color: rgba(0, 0, 0, 0.25);border-bottom-left-radius: 3px;
  border-bottom-right-radius: 3px;border-bottom-style: solid;border-bottom-width: 1px;border-image-outset: 0;border-image-repeat: stretch;
  border-image-slice: 100%;border-image-source: none;border-image-width: 1;border-left-color: rgba(0, 0, 0, 0.1);border-left-style: solid;
  border-left-width: 1px;border-right-color: rgba(0, 0, 0, 0.1);border-right-style: solid;border-right-width: 1px;border-top-color: rgba(0, 0, 0, 0.1);
  border-top-left-radius: 3px;border-top-right-radius: 3px;border-top-style: solid;border-top-width: 1px;
  box-shadow: rgba(255, 255, 255, 0.2) 0px 1px 0px 0px inset, rgba(0, 0, 0, 0.05) 0px 1px 2px 0px;color: rgb(255, 255, 255);
  cursor: pointer;display: inline-block;font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;font-size: 10.5px;font-weight: 400;line-height: 20px;
  margin-bottom: 0px;margin-left: 0px;margin-right: 0px;margin-top: 0px;
  padding-bottom: 2px;padding-left: 2px;padding-right: 2px;padding-top: 2px;
  text-align: center;text-shadow: rgba(0, 0, 0, 0.25) 0px -1px 0px;vertical-align: middle;
}



.iconpencil {
background-image: url("<c:url value="/img/glyphicons-halflings-white.png"/>");
background-position: 0px -72px;
background-position-x: 0px;
background-position-y: -72px;
background-repeat: no-repeat;
box-sizing: border-box;
color: rgb(102, 102, 102);
cursor: pointer;
display: inline-block;
font-family: Arial, "Helvetica Neue", Helvetica, sans-serif;
font-size: 10.5px;
height: 14px;
line-height: 14px;
margin-top: -1px;
text-align: center;
text-shadow: rgba(255, 255, 255, 0.75) 0px 1px 1px;
vertical-align: text-top;
width: 14px;
}



</style>

<%--  style="background-image: url('<c:url value="/img/caibbg.png"/>');" --%>

<div id="container" style="background-color: #FFFFFF" >

    <div id="wrap" style="background-color: #FFFFFF">

        
        <div id="mainWindow" class="shadow2 overflowh sandboxd w960"  >

            
            <div id="contentData" class="p20">





<div class="full-width" style="background-color: #EEEEEE">
<%-- 
                    <h4>${titol}</h4>--%>

                    <h5>Número: ${ID}</h5>
                    <br> Estat: <strong class="label-custom">${estat}</strong>
                    <hr>
                    Nom Contacte: ${soli.personaContacte}<br>
                    Email Contacte: ${soli.personaContacteEmail}<br>
                    

                </div>

                <div class="container">


                    <c:forEach var="event" items="${eventItems}">

                        <c:set var="isPublic" value="${event.tipus > 0}" />
                        <c:set var="isContacte" value="${event.tipus == 2}" />

                        <c:set var="show" value="true" />

                        <c:if test="${showOnlyPublic==true && isPublic == false }">
                            <c:set var="show" value="false" />
                        </c:if>


                        <c:if test="${show}">
                        
                        
                        <c:set var="background" value="#cce6ff" />
                        <c:set var="border" value="#F0E68C" />
        
                        <c:if test="${!isPublic}">
        
                            <c:set var="background" value="#ffb3b3" />
                            <c:set var="border" value="#ff0000" />
        
                        </c:if>
                        
                        <c:if test="${isContacte}">
                            <c:set var="background" value="#ccffcc" />
                            <c:set var="border" value="#9ACD32" />
        
                        </c:if>
                        
                        

                            <c:if test="${isContacte}">
                                <div
                                    class="timeline  timeline-left timeline-with-arrows blue-blue-blue">
                                    <div class="timeline-block">
                                        <div class="timeline-icon"
                                            style="border: 4px solid ${border};"></div>
                                        <div class="timeline-content">

                                            <div style="white-space2: pre-wrap;">

                                                <%@ include
                                                    file="/WEB-INF/jsp/common/eventscore.jsp"%>

                                            </div>
                                            <div class="timeline-date"
                                                style="left: -190px;">
                                                ${event.dataEvent}<br> <span
                                                    style="font-weight: bold; font-size: 10pt">De
                                                    ${event.persona}</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:if>

                            <c:if test="${!isContacte}">
                                <div class="timeline timeline-with-arrows blue-blue-blue">
                                    <div class="timeline-block">
                                        <div class="timeline-icon"
                                            style="border: 4px solid ${border};"></div>
                                        <div class="timeline-content">

                                            <div style="white-space2: pre-wrap;">


                                                <%@ include
                                                    file="/WEB-INF/jsp/common/eventscore.jsp"%>

                                                <div class="timeline-date">
                                                    ${event.dataEvent}<br> <span
                                                        style="font-weight: bold; font-size: 10pt">De
                                                        ${event.persona}</span>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                            </c:if>


                        </c:if>

                    </c:forEach>

                </div>
                </div>
                
                </div>
    </div>
    </div>
    
    <%-- 
                <form id="formularioMensaje" name="formularioMensaje"
                    action="/ayuda/seguimientoEnviarMensaje" method="post" class="centrd"
                    enctype="multipart/form-data">
                    <fieldset>
                        

                        <div class="fsb mb20 mr20 ml20">
                            <h4>Enviar un mensaje al tramitador</h4>
                            <h4></h4>
                        </div>
                        <div class="clearfix"></div>
                        <div class="fsb mb20 mr20 ml20" style="text-align: right">

                            <textarea style="width: 840px; height: 100px;" name="mensaje"
                                id="mensaje"></textarea>
                            <input style="visibility: hidden; display: none" type="file"
                                name="fichero" id="fichero">

                            <div style="height: 10px"></div>
                            <a style="margin-top: 5px;" id="boton_fichero"
                                class="pickfiles simbutton" href="javascript:;">Subir
                                un fichero</a> <input class="primary" type="submit"
                                value="Enviar">&nbsp;&nbsp;&nbsp;
                        </div>
                    </fieldset>
                </form>
        
       --%>
   <br/>
   <br/>
   
   <center><b>©Fundaci&oacute Bit - &Agrave;rea de Govern Digital - PinbalAdmin</b></center>
<%--
<div id="footer">
    <p>©Fundaci&oacute Bit - &Agrave;rea de Govern Digital - PinbalAdmin</p>
</div>



<div id="loading-ajax" style="display: none">
    <img alt="Cargando..." title="Cargando..." src="<c:url value="/ayuda/images/morfos/ajax-loader.gif"/>">
</div>


 
<div id="ui-datepicker-div"
    class="ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all ui-helper-hidden-accessible"></div>
<div style="display: none; z-index: 1000; outline: currentcolor none 0px;"
    class="ui-dialog ui-widget ui-widget-content ui-corner-all  ui-draggable"
    tabindex="-1" role="dialog" aria-labelledby="ui-dialog-title-AjaxShow">
    <div class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix">
        <span class="ui-dialog-title" id="ui-dialog-title-AjaxShow">&nbsp;</span><a
            href="#" class="ui-dialog-titlebar-close ui-corner-all" role="button"><span
            class="ui-icon ui-icon-closethick">close</span></a>
    </div>
    <div id="AjaxShow" class="ui-dialog-content ui-widget-content"></div>
    
    --%>


</div>



