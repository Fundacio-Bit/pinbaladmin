<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
PRUEBA FORMULATIO PUBLICO

				<%
                Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                request.getSession().setAttribute("principal", principal);
                %>

                <h2>
                    <i><fmt:message key="autenticat.si" /></i>
                </h2>

                <table class="table table-striped table-bordered" style="width: auto;">

                    <tr>
                        <td>Name</td>
                        <td>${principal.usuario.name}</td>
                    </tr>
                    <tr>
                        <td>Surname1</td>
                        <td>${principal.usuario.surname1}</td>
                    </tr>
                    <tr>
                        <td>Surname2</td>
                        <td>${principal.usuario.surname2}</td>
                    </tr>
                    <tr>
                        <td>NIF</td>
                        <td>${principal.usuario.administrationID}</td>
                    </tr>
                    <tr>
                        <td>Method</td>
                        <td>${principal.usuario.authenticationMethod}</td>
                    </tr>
                    <tr>
                        <td>LevelAut</td>
                        <td>${principal.usuario.qaa}</td>
                    </tr>
                    <tr>
                        <td>identityProvider</td>
                        <td>${principal.usuario.identityProvider}</td>
                    </tr>
                    <tr>
                        <td>EsEmpresa</td>
                        <td>${principal.usuario.business}</td>
                    </tr>
                </table>

</body>
</html>