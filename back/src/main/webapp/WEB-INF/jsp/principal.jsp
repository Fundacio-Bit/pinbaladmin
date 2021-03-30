<%@page import="org.springframework.security.core.Authentication"
%><%@page import="org.springframework.security.core.context.SecurityContext"
%><%@page import="org.springframework.security.core.context.SecurityContextHolder"
%><%@ page language="java" 
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp" 
%>
<div class="clear"></div>
<div class="spacer"></div>

<div>
<br/>
<center>
<img src="<c:url value="/img/app-logo-2.png"/>"  alt="PinbalAdmin" title="PinbalAdmin"/>

<br/>
<br/>
Administraci&oacute; de les Peticions d'Alta a PINBAL per part de les entitats de les Illes Balears.

<br/>
<br/>
<table border="0" >
<tr>
<td valign="top">
<a href="http://dgtic.caib.es/" target="_blank">
<img src="<c:url value="/img/dgidt.jpg"/>"  alt="DGIDT" title="DGIDT"/>
</a>
</td>
<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td valign="top">
<a href="http://blog.fundaciobit.org/category/admindigital/" target="_blank">
<img src="<c:url value="/img/fundaciobit.jpg"/>"  alt="Fundació Bit" title="Fundació Bit"/>
</a>
</td>
</tr>
</table>
<br/>
</center>
 
</div>

<c:if test="${pad:isDesenvolupament()}">
Only in Development Mode
</c:if>
