      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${documentEntitat.documentEntitatID}"/>
       &nbsp;
      </td>
      </c:if>

