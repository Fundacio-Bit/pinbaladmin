<%@ page contentType="text/html;charset=UTF-8" language="java"%>
 <%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 <c:set var="url" value="${urlActual}" />
 <div>
 <h5>WebDatabase</h5>
 <ul class="tree" style="margin:3px; padding:0px;">
 <%-- ==== GENAPP MARK START --%>


    <%-- Area --%>
       <fmt:message var="entityname" key="area.area.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/area/list/1"/>" ><span style="${(fn:contains(url, 'area/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- CampFormulari --%>
       <fmt:message var="entityname" key="campFormulari.campFormulari.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/campFormulari/list/1"/>" ><span style="${(fn:contains(url, 'campFormulari/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- CampSolicitud --%>
       <fmt:message var="entityname" key="campSolicitud.campSolicitud.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/campSolicitud/list/1"/>" ><span style="${(fn:contains(url, 'campSolicitud/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- Departament --%>
       <fmt:message var="entityname" key="departament.departament.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/departament/list/1"/>" ><span style="${(fn:contains(url, 'departament/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- Document --%>
       <fmt:message var="entityname" key="document.document.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/document/list/1"/>" ><span style="${(fn:contains(url, 'document/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- DocumentCedent --%>
       <fmt:message var="entityname" key="documentCedent.documentCedent.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/documentCedent/list/1"/>" ><span style="${(fn:contains(url, 'documentCedent/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- DocumentEntitat --%>
       <fmt:message var="entityname" key="documentEntitat.documentEntitat.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/documentEntitat/list/1"/>" ><span style="${(fn:contains(url, 'documentEntitat/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- DocumentSolicitud --%>
       <fmt:message var="entityname" key="documentSolicitud.documentSolicitud.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/documentSolicitud/list/1"/>" ><span style="${(fn:contains(url, 'documentSolicitud/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- Email --%>
       <fmt:message var="entityname" key="email.email.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/email/list/1"/>" ><span style="${(fn:contains(url, 'email/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- Entitat --%>
       <fmt:message var="entityname" key="entitat.entitat.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/entitat/list/1"/>" ><span style="${(fn:contains(url, 'entitat/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- EntitatServei --%>
       <fmt:message var="entityname" key="entitatServei.entitatServei.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/entitatServei/list/1"/>" ><span style="${(fn:contains(url, 'entitatServei/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- EstatTiquet --%>
       <fmt:message var="entityname" key="estatTiquet.estatTiquet.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/estatTiquet/list/1"/>" ><span style="${(fn:contains(url, 'estatTiquet/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- Event --%>
       <fmt:message var="entityname" key="event.event.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/event/list/1"/>" ><span style="${(fn:contains(url, 'event/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- Fitxer --%>
       <fmt:message var="entityname" key="fitxer.fitxer.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/fitxer/list/1"/>" ><span style="${(fn:contains(url, 'fitxer/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- Formulari --%>
       <fmt:message var="entityname" key="formulari.formulari.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/formulari/list/1"/>" ><span style="${(fn:contains(url, 'formulari/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- GrupEntitat --%>
       <fmt:message var="entityname" key="grupEntitat.grupEntitat.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/grupEntitat/list/1"/>" ><span style="${(fn:contains(url, 'grupEntitat/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- GrupEntitatCedent --%>
       <fmt:message var="entityname" key="grupEntitatCedent.grupEntitatCedent.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/grupEntitatCedent/list/1"/>" ><span style="${(fn:contains(url, 'grupEntitatCedent/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- Idioma --%>
       <fmt:message var="entityname" key="idioma.idioma.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/idioma/list/1"/>" ><span style="${(fn:contains(url, 'idioma/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- IncidenciaTecnica --%>
       <fmt:message var="entityname" key="incidenciaTecnica.incidenciaTecnica.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/incidenciaTecnica/list/1"/>" ><span style="${(fn:contains(url, 'incidenciaTecnica/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- Operador --%>
       <fmt:message var="entityname" key="operador.operador.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/operador/list/1"/>" ><span style="${(fn:contains(url, 'operador/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- Organ --%>
       <fmt:message var="entityname" key="organ.organ.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/organ/list/1"/>" ><span style="${(fn:contains(url, 'organ/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- PINFO --%>
       <fmt:message var="entityname" key="pINFO.pINFO.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/pINFO/list/1"/>" ><span style="${(fn:contains(url, 'pINFO/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- PinfoData --%>
       <fmt:message var="entityname" key="pinfoData.pinfoData.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/pinfoData/list/1"/>" ><span style="${(fn:contains(url, 'pinfoData/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- Servei --%>
       <fmt:message var="entityname" key="servei.servei.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/servei/list/1"/>" ><span style="${(fn:contains(url, 'servei/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- Solicitud --%>
       <fmt:message var="entityname" key="solicitud.solicitud.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/solicitud/list/1"/>" ><span style="${(fn:contains(url, 'solicitud/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- SolicitudServei --%>
       <fmt:message var="entityname" key="solicitudServei.solicitudServei.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/solicitudServei/list/1"/>" ><span style="${(fn:contains(url, 'solicitudServei/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- TipusTiquet --%>
       <fmt:message var="entityname" key="tipusTiquet.tipusTiquet.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/tipusTiquet/list/1"/>" ><span style="${(fn:contains(url, 'tipusTiquet/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- Tiquet --%>
       <fmt:message var="entityname" key="tiquet.tiquet.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/tiquet/list/1"/>" ><span style="${(fn:contains(url, 'tiquet/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- Traduccio --%>
       <fmt:message var="entityname" key="traduccio.traduccio.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/traduccio/list/1"/>" ><span style="${(fn:contains(url, 'traduccio/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- TramitAPersAut --%>
       <fmt:message var="entityname" key="tramitAPersAut.tramitAPersAut.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/tramitAPersAut/list/1"/>" ><span style="${(fn:contains(url, 'tramitAPersAut/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- TramitBDadesSoli --%>
       <fmt:message var="entityname" key="tramitBDadesSoli.tramitBDadesSoli.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/tramitBDadesSoli/list/1"/>" ><span style="${(fn:contains(url, 'tramitBDadesSoli/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- TramitCDadesCesi --%>
       <fmt:message var="entityname" key="tramitCDadesCesi.tramitCDadesCesi.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/tramitCDadesCesi/list/1"/>" ><span style="${(fn:contains(url, 'tramitCDadesCesi/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- TramitDCteAut --%>
       <fmt:message var="entityname" key="tramitDCteAut.tramitDCteAut.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/tramitDCteAut/list/1"/>" ><span style="${(fn:contains(url, 'tramitDCteAut/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- TramitECteAud --%>
       <fmt:message var="entityname" key="tramitECteAud.tramitECteAud.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/tramitECteAud/list/1"/>" ><span style="${(fn:contains(url, 'tramitECteAud/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- TramitFCteTec --%>
       <fmt:message var="entityname" key="tramitFCteTec.tramitFCteTec.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/tramitFCteTec/list/1"/>" ><span style="${(fn:contains(url, 'tramitFCteTec/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- TramitGDadesTit --%>
       <fmt:message var="entityname" key="tramitGDadesTit.tramitGDadesTit.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/tramitGDadesTit/list/1"/>" ><span style="${(fn:contains(url, 'tramitGDadesTit/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- TramitHProc --%>
       <fmt:message var="entityname" key="tramitHProc.tramitHProc.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/tramitHProc/list/1"/>" ><span style="${(fn:contains(url, 'tramitHProc/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- TramitIServ --%>
       <fmt:message var="entityname" key="tramitIServ.tramitIServ.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/tramitIServ/list/1"/>" ><span style="${(fn:contains(url, 'tramitIServ/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- TramitJConsent --%>
       <fmt:message var="entityname" key="tramitJConsent.tramitJConsent.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/tramitJConsent/list/1"/>" ><span style="${(fn:contains(url, 'tramitJConsent/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>
<%-- ==== GENAPP MARK END --%>
 </ul>
 </div>
