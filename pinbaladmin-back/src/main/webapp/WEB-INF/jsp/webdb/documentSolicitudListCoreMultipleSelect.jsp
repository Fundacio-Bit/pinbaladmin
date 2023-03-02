      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${documentSolicitud.documentSolicitudID}"/>
       &nbsp;
      </td>
      </c:if>

