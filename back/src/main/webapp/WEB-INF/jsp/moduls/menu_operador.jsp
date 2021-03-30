﻿<%@ page contentType="text/html;charset=UTF-8" language="java"
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<c:set var="url" value="${urlActual}" />
<div>
  <h5><fmt:message key="menuoperador" /></h5>
  <ul class="tree" style="margin: 3px; padding: 0px;">

    <li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/operador/solicitudactiva/list"/>">
        <span style="${(fn:contains(url, '/solicitudactiva'))? "font-weight: bold;" : ""}"><fmt:message key="solicitud.solicitudactiva.plural" /></span>
      </a>
    </li>

    <li><hr  style="margin-top: 6px;  margin-bottom: 6px;" /></li>

    <li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/operador/solicituddesdefitxer/nou"/>">
        <span style="${(fn:contains(url, '/solicituddesdefitxer/'))? "font-weight: bold;" : ""}">Sol·licitud Local des de Fitxer</span>
      </a>
    </li>

    <li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/operador/solicitudlocal/list"/>">
        <span style="${(fn:contains(url, '/solicitudlocal/'))? "font-weight: bold;" : ""}"><fmt:message key="solicitud.local.plural" /></span>
      </a>
    </li>
    <li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/operador/solicitudestatal/list"/>">
        <span style="${(fn:contains(url, '/solicitudestatal/'))? "font-weight: bold;" : ""}"><fmt:message key="solicitud.estatal.plural" /></span>
      </a>
    </li>
    <li><hr  style="margin-top: 6px;  margin-bottom: 6px;" /></li>
    <li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/operador/servei/list"/>">
        <span style="${(fn:contains(url, '/servei/'))? "font-weight: bold;" : ""}"><fmt:message key="servei.servei.plural" /></span>
      </a>
    </li>
    <li><hr  style="margin-top: 6px;  margin-bottom: 6px;" /></li>
    
    <li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/operador/entitat/list"/>">
        <span style="${(fn:contains(url, 'entitat'))? "font-weight: bold;" : ""}"><fmt:message key="entitat.entitat.plural" /></span>
      </a>
    </li>
    
    <li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/operador/area/list"/>">
        <span style="${(fn:contains(url, 'area'))? "font-weight: bold;" : ""}"><fmt:message key="area.area.plural" /></span>
      </a>
    </li>
    
    <li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/operador/departament/list"/>">
        <span style="${(fn:contains(url, 'departament'))? "font-weight: bold;" : ""}"><fmt:message key="departament.departament.plural" /></span>
      </a>
    </li>
    

    <li>
    <hr  style="margin-top: 6px;  margin-bottom: 6px;" />
    </li>

    <li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/operador/cedent/list"/>">
        <span style="${(fn:contains(url, '/cedent/'))? "font-weight: bold;" : ""}"><fmt:message key="entitatServei.entitatServei.plural" /></span>
      </a>
    </li>

    <li>
    <hr  style="margin-top: 6px;  margin-bottom: 6px;" />
    </li>

    <li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/operador/tiquet/list"/>">
        <span style="${(fn:contains(url, '/tiquet/'))? "font-weight: bold;" : ""}"><fmt:message key="tiquet.tiquet.plural" /></span>
      </a>
    </li>
    
     <li>
    <hr  style="margin-top: 6px;  margin-bottom: 6px;" />
    </li>

    <li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/operador/email/list"/>">
        <span style="${(fn:contains(url, '/email/'))? "font-weight: bold;" : ""}">Gestió de Correus</span>
      </a>
    </li>
    
    
     <li>
    <hr  style="margin-top: 6px;  margin-bottom: 6px;" />
    </li>
    
        <li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/operador/borrarcedentdegrup/list"/>">
        <span style="${(fn:contains(url, '/operador/borrarcedentdegrup'))? "font-weight: bold;" : ""}">Descartar Cedent de Grup</span>
      </a>
    </li>
    
    
    
    
<%--
    <hr  style="margin-top: 6px;  margin-bottom: 6px;" />
    <li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/common/option1"/>">
        <span style="${(fn:contains(url, 'option1'))? "font-weight: bold;" : ""}">Menú Option 1</span>
      </a>
    </li>
--%>
   <%-- Example with security: virtual roles  --%>
   <%--
   <sec:authorize access="hasAnyRole('ROLE_SOLI', 'ROLE_DEST', 'ROLE_COLA', 'ROLE_DELE')">
      <hr  style="margin-top: 6px;  margin-bottom: 6px;" />
      <li style="list-style-type: disc; list-style-position: inside;">
       <a href="<c:url value="/common/rebreAvis/list/1"/>" >
       <span style="${(fn:contains(url, 'optionxxxxx/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
       Option XXXXX</span></a></li>
   </sec:authorize>
    --%>
<%--
    <hr  style="margin-top: 6px;  margin-bottom: 6px;" />
    <li style="list-style-type: disc; list-style-position: inside;">
        <a target="_blank" href="/common/option2">
        Last Menu Option (other page)
        </a>
    </li>
   --%>
  </ul>
</div>

