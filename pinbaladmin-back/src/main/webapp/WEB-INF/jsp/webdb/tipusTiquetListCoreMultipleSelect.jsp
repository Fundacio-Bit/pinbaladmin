      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${tipusTiquet.tipusTiquetID}"/>
       &nbsp;
      </td>
      </c:if>

