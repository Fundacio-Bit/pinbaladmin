<%@ page contentType="text/html;charset=UTF-8" language="java"%>
 <%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 <c:set var="url" value="${urlActual}" />
 <div>
 <h5>WebDatabase</h5>
 <ul class="tree" style="margin:3px; padding:0px;">
 <%-- ==== GENAPP MARK START --%>


    <%-- Area --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'area/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'area/')? "font-weight: bold;" : ""}"><fmt:message key="area.area"/></span></a>
      <ul class="${fn:contains(url, 'area/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/area/new"/>" ><span style="${(fn:contains(url, 'area/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="area.area"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/area/list/1"/>" ><span style="${(fn:contains(url, 'area/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- CampFormulari --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'campFormulari/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'campFormulari/')? "font-weight: bold;" : ""}"><fmt:message key="campFormulari.campFormulari"/></span></a>
      <ul class="${fn:contains(url, 'campFormulari/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/campFormulari/new"/>" ><span style="${(fn:contains(url, 'campFormulari/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="campFormulari.campFormulari"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/campFormulari/list/1"/>" ><span style="${(fn:contains(url, 'campFormulari/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- CampSolicitud --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'campSolicitud/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'campSolicitud/')? "font-weight: bold;" : ""}"><fmt:message key="campSolicitud.campSolicitud"/></span></a>
      <ul class="${fn:contains(url, 'campSolicitud/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/campSolicitud/new"/>" ><span style="${(fn:contains(url, 'campSolicitud/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="campSolicitud.campSolicitud"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/campSolicitud/list/1"/>" ><span style="${(fn:contains(url, 'campSolicitud/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- Departament --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'departament/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'departament/')? "font-weight: bold;" : ""}"><fmt:message key="departament.departament"/></span></a>
      <ul class="${fn:contains(url, 'departament/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/departament/new"/>" ><span style="${(fn:contains(url, 'departament/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="departament.departament"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/departament/list/1"/>" ><span style="${(fn:contains(url, 'departament/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- Document --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'document/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'document/')? "font-weight: bold;" : ""}"><fmt:message key="document.document"/></span></a>
      <ul class="${fn:contains(url, 'document/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/document/new"/>" ><span style="${(fn:contains(url, 'document/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="document.document"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/document/list/1"/>" ><span style="${(fn:contains(url, 'document/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- DocumentCedent --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'documentCedent/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'documentCedent/')? "font-weight: bold;" : ""}"><fmt:message key="documentCedent.documentCedent"/></span></a>
      <ul class="${fn:contains(url, 'documentCedent/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/documentCedent/new"/>" ><span style="${(fn:contains(url, 'documentCedent/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="documentCedent.documentCedent"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/documentCedent/list/1"/>" ><span style="${(fn:contains(url, 'documentCedent/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- DocumentEntitat --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'documentEntitat/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'documentEntitat/')? "font-weight: bold;" : ""}"><fmt:message key="documentEntitat.documentEntitat"/></span></a>
      <ul class="${fn:contains(url, 'documentEntitat/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/documentEntitat/new"/>" ><span style="${(fn:contains(url, 'documentEntitat/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="documentEntitat.documentEntitat"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/documentEntitat/list/1"/>" ><span style="${(fn:contains(url, 'documentEntitat/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- DocumentSolicitud --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'documentSolicitud/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'documentSolicitud/')? "font-weight: bold;" : ""}"><fmt:message key="documentSolicitud.documentSolicitud"/></span></a>
      <ul class="${fn:contains(url, 'documentSolicitud/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/documentSolicitud/new"/>" ><span style="${(fn:contains(url, 'documentSolicitud/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="documentSolicitud.documentSolicitud"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/documentSolicitud/list/1"/>" ><span style="${(fn:contains(url, 'documentSolicitud/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- Email --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'email/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'email/')? "font-weight: bold;" : ""}"><fmt:message key="email.email"/></span></a>
      <ul class="${fn:contains(url, 'email/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/email/new"/>" ><span style="${(fn:contains(url, 'email/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="email.email"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/email/list/1"/>" ><span style="${(fn:contains(url, 'email/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- Entitat --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'entitat/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'entitat/')? "font-weight: bold;" : ""}"><fmt:message key="entitat.entitat"/></span></a>
      <ul class="${fn:contains(url, 'entitat/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/entitat/new"/>" ><span style="${(fn:contains(url, 'entitat/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="entitat.entitat"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/entitat/list/1"/>" ><span style="${(fn:contains(url, 'entitat/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- EntitatServei --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'entitatServei/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'entitatServei/')? "font-weight: bold;" : ""}"><fmt:message key="entitatServei.entitatServei"/></span></a>
      <ul class="${fn:contains(url, 'entitatServei/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/entitatServei/new"/>" ><span style="${(fn:contains(url, 'entitatServei/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="entitatServei.entitatServei"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/entitatServei/list/1"/>" ><span style="${(fn:contains(url, 'entitatServei/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- EstatServei --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'estatServei/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'estatServei/')? "font-weight: bold;" : ""}"><fmt:message key="estatServei.estatServei"/></span></a>
      <ul class="${fn:contains(url, 'estatServei/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/estatServei/new"/>" ><span style="${(fn:contains(url, 'estatServei/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="estatServei.estatServei"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/estatServei/list/1"/>" ><span style="${(fn:contains(url, 'estatServei/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- EstatSolicitud --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'estatSolicitud/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'estatSolicitud/')? "font-weight: bold;" : ""}"><fmt:message key="estatSolicitud.estatSolicitud"/></span></a>
      <ul class="${fn:contains(url, 'estatSolicitud/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/estatSolicitud/new"/>" ><span style="${(fn:contains(url, 'estatSolicitud/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="estatSolicitud.estatSolicitud"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/estatSolicitud/list/1"/>" ><span style="${(fn:contains(url, 'estatSolicitud/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- EstatSolicitudServei --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'estatSolicitudServei/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'estatSolicitudServei/')? "font-weight: bold;" : ""}"><fmt:message key="estatSolicitudServei.estatSolicitudServei"/></span></a>
      <ul class="${fn:contains(url, 'estatSolicitudServei/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/estatSolicitudServei/new"/>" ><span style="${(fn:contains(url, 'estatSolicitudServei/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="estatSolicitudServei.estatSolicitudServei"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/estatSolicitudServei/list/1"/>" ><span style="${(fn:contains(url, 'estatSolicitudServei/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- EstatTiquet --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'estatTiquet/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'estatTiquet/')? "font-weight: bold;" : ""}"><fmt:message key="estatTiquet.estatTiquet"/></span></a>
      <ul class="${fn:contains(url, 'estatTiquet/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/estatTiquet/new"/>" ><span style="${(fn:contains(url, 'estatTiquet/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="estatTiquet.estatTiquet"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/estatTiquet/list/1"/>" ><span style="${(fn:contains(url, 'estatTiquet/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- Fitxer --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'fitxer/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'fitxer/')? "font-weight: bold;" : ""}"><fmt:message key="fitxer.fitxer"/></span></a>
      <ul class="${fn:contains(url, 'fitxer/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/fitxer/new"/>" ><span style="${(fn:contains(url, 'fitxer/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="fitxer.fitxer"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/fitxer/list/1"/>" ><span style="${(fn:contains(url, 'fitxer/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- Formulari --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'formulari/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'formulari/')? "font-weight: bold;" : ""}"><fmt:message key="formulari.formulari"/></span></a>
      <ul class="${fn:contains(url, 'formulari/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/formulari/new"/>" ><span style="${(fn:contains(url, 'formulari/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="formulari.formulari"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/formulari/list/1"/>" ><span style="${(fn:contains(url, 'formulari/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- GrupEntitat --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'grupEntitat/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'grupEntitat/')? "font-weight: bold;" : ""}"><fmt:message key="grupEntitat.grupEntitat"/></span></a>
      <ul class="${fn:contains(url, 'grupEntitat/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/grupEntitat/new"/>" ><span style="${(fn:contains(url, 'grupEntitat/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="grupEntitat.grupEntitat"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/grupEntitat/list/1"/>" ><span style="${(fn:contains(url, 'grupEntitat/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- GrupEntitatCedent --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'grupEntitatCedent/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'grupEntitatCedent/')? "font-weight: bold;" : ""}"><fmt:message key="grupEntitatCedent.grupEntitatCedent"/></span></a>
      <ul class="${fn:contains(url, 'grupEntitatCedent/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/grupEntitatCedent/new"/>" ><span style="${(fn:contains(url, 'grupEntitatCedent/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="grupEntitatCedent.grupEntitatCedent"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/grupEntitatCedent/list/1"/>" ><span style="${(fn:contains(url, 'grupEntitatCedent/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- Idioma --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'idioma/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'idioma/')? "font-weight: bold;" : ""}"><fmt:message key="idioma.idioma"/></span></a>
      <ul class="${fn:contains(url, 'idioma/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/idioma/new"/>" ><span style="${(fn:contains(url, 'idioma/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="idioma.idioma"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/idioma/list/1"/>" ><span style="${(fn:contains(url, 'idioma/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- Servei --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'servei/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'servei/')? "font-weight: bold;" : ""}"><fmt:message key="servei.servei"/></span></a>
      <ul class="${fn:contains(url, 'servei/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/servei/new"/>" ><span style="${(fn:contains(url, 'servei/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="servei.servei"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/servei/list/1"/>" ><span style="${(fn:contains(url, 'servei/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- Solicitud --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'solicitud/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'solicitud/')? "font-weight: bold;" : ""}"><fmt:message key="solicitud.solicitud"/></span></a>
      <ul class="${fn:contains(url, 'solicitud/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/solicitud/new"/>" ><span style="${(fn:contains(url, 'solicitud/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="solicitud.solicitud"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/solicitud/list/1"/>" ><span style="${(fn:contains(url, 'solicitud/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- SolicitudServei --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'solicitudServei/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'solicitudServei/')? "font-weight: bold;" : ""}"><fmt:message key="solicitudServei.solicitudServei"/></span></a>
      <ul class="${fn:contains(url, 'solicitudServei/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/solicitudServei/new"/>" ><span style="${(fn:contains(url, 'solicitudServei/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="solicitudServei.solicitudServei"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/solicitudServei/list/1"/>" ><span style="${(fn:contains(url, 'solicitudServei/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- TipusTiquet --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'tipusTiquet/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'tipusTiquet/')? "font-weight: bold;" : ""}"><fmt:message key="tipusTiquet.tipusTiquet"/></span></a>
      <ul class="${fn:contains(url, 'tipusTiquet/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/tipusTiquet/new"/>" ><span style="${(fn:contains(url, 'tipusTiquet/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="tipusTiquet.tipusTiquet"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/tipusTiquet/list/1"/>" ><span style="${(fn:contains(url, 'tipusTiquet/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- Tiquet --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'tiquet/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'tiquet/')? "font-weight: bold;" : ""}"><fmt:message key="tiquet.tiquet"/></span></a>
      <ul class="${fn:contains(url, 'tiquet/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/tiquet/new"/>" ><span style="${(fn:contains(url, 'tiquet/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="tiquet.tiquet"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/tiquet/list/1"/>" ><span style="${(fn:contains(url, 'tiquet/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- Traduccio --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'traduccio/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'traduccio/')? "font-weight: bold;" : ""}"><fmt:message key="traduccio.traduccio"/></span></a>
      <ul class="${fn:contains(url, 'traduccio/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/traduccio/new"/>" ><span style="${(fn:contains(url, 'traduccio/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="traduccio.traduccio"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/traduccio/list/1"/>" ><span style="${(fn:contains(url, 'traduccio/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>
<%-- ==== GENAPP MARK END --%>
 </ul>
 </div>
