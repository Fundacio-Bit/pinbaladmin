      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${campSolicitud.campSolicitudID}"/>
       &nbsp;
      </td>
      </c:if>

