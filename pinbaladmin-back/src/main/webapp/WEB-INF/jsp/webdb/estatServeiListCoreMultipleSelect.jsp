      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${estatServei.estatServeiID}"/>
       &nbsp;
      </td>
      </c:if>
