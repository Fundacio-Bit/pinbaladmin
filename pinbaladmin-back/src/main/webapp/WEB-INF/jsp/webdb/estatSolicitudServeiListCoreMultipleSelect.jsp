      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${estatSolicitudServei.estatSolicitudServeiID}"/>
       &nbsp;
      </td>
      </c:if>

