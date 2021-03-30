<%@page import="org.fundaciobit.pinbaladmin.logic.utils.LogicUtils"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
 %><%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
<table border=0 cellpadding="0" cellspacing="0" width="100%">

 <tr>
 <td width="40%" valign="top">
   <div class="pull-left colophon">
     <b>PinbalAdmin v<%=LogicUtils.getVersio()%></b><br/>
     <i><a href="http://otaeweb.ibit.org/" target="_blank"><fmt:message key="desenvolupatper" /></a></i><br/>
     <!-- Button to trigger modal -->
     <small><a href="#modalAjuda" role="button" data-toggle="modal"><fmt:message key="ajuda.necessitau" /></a></small>
   </div>
 </td>

 <td width="20%" valign="top">
   <div class="center" style=" margin-top: 20px;">
     <small>  
     Fundació Bit - Balears d'Innovació i Tecnologia<br/>
     Govern Digital<br/>
     Centre Empresarial Son Espanyol<br/>
     C/ Laura Bassi 07121 ParcBit<br/>
     Telf. 971.784.730<br/>
     </small>
   </div>
 </td>

 <td width="40%" valign="top">
  <div class="pull-right govern-footer">
    
    <a href="http://otaeweb.ibit.org/" target="_blank">
    <img src="<c:url value="/img/fundaciobit-logo-peu.png"/>"  alt="Fundacio Bit" />
    </a>
    
  </div>
 </td>
 </tr> 
</table> 

    <!-- Modal -->
    <div id="modalAjuda" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="<fmt:message key="ajuda.titol" />" aria-hidden="true">
    <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="myModalLabel"><fmt:message key="ajuda.titol" /></h3>
    </div>
    <div class="modal-body">
    <p><fmt:message key="ajuda.missatge" /></p>
     <ul>
     
        <li><fmt:message key="ajuda.viatelefon"/> 971177283</li>
        <li><fmt:message key="ajuda.viaweb"/>http://www.fundaciobit.org</li>
        <li><fmt:message key="ajuda.viaemail"/><a href="mailto: otae@fundaciobit.org"> otae@fundaciobit.org</a></li>
     
     </ul>
    
    </div>    
    </div>
