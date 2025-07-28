      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${infoMadrid.infoMadridID}"/>
       &nbsp;
      </td>
      </c:if>

