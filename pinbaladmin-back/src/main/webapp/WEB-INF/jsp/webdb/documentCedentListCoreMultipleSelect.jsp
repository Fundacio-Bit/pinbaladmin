      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${documentCedent.documentCedentID}"/>
       &nbsp;
      </td>
      </c:if>

