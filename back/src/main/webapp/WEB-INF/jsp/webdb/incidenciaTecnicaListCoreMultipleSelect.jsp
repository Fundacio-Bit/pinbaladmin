      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${incidenciaTecnica.incidenciaTecnicaID}"/>
       &nbsp;
      </td>
      </c:if>

