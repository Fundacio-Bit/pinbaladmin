      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${estatTiquet.estatTiquetID}"/>
       &nbsp;
      </td>
      </c:if>

