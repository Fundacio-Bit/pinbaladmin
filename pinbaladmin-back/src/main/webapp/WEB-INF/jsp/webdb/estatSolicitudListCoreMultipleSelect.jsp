      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${estatSolicitud.estatSolicitudID}"/>
       &nbsp;
      </td>
      </c:if>

