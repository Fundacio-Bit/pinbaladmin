<%@page import="org.springframework.security.core.Authentication"%><%@page
	import="org.springframework.context.i18n.LocaleContextHolder"%><%@page
	import="org.springframework.security.core.context.SecurityContext"%><%@page
	import="org.springframework.security.core.context.SecurityContextHolder"%><%@ page
	language="java"%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<div class="clear"></div>
<div class="spacer"></div>

<div>
	<br />
	<center>
		<img src="<c:url value="/img/app-logo.png"/>" alt="PinbalAdmin"
			title="PinbalAdmin" /> <br /> <br /> Administració de les
		Peticions d'Alta a PINBAL per part de les entitats de les Illes
		Balears. <br /> <br />
		<table border="0">
			<tr>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td valign="top"><a
					href="http://blog.fundaciobit.org/category/admindigital/"
					target="_blank"> <img
						src="<c:url value="/img/fundaciobit.png"/>" alt="Fundaciï¿½ Bit"
						title="Fundaciï¿½ Bit" />
				</a></td>
			</tr>
		</table>
		<br />
	</center>

</div>

<br />
Username: ${loginInfo.username}
<br />
&#36;{pad:hasRole(ROLE_ADMIN)}= ${pad:hasRole('ROLE_ADMIN')}
<br />
&#36;{pad:hasRole(ROLE_USER) }= ${pad:hasRole('ROLE_USER') }
<br />
Locale =
<%=LocaleContextHolder.getLocale()%>
<br />
lang = ${lang}
<br />
> UserInformation:
<br />
<c:if test="${not empty loginInfo.userInfo}">
	name= ${loginInfo.userInfo.name} <br /> 
 	surname1= ${loginInfo.userInfo.surname1} <br />
 	surname2= ${loginInfo.userInfo.surname2} <br />
 	email= ${loginInfo.userInfo.email} <br />
 	nif= ${loginInfo.userInfo.attributes["nif"]} <br />
	<br />
</c:if>
<c:if test="${empty loginInfo.userInfo}">
	Error carregant Plugin UserInfo. Revisar logs per mes informacio.<br />
</c:if>

<c:if test="${pad:isDesenvolupament()}">
Only in Development Mode
</c:if>
