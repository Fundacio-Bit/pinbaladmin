      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${tramitCDadesCesi.dadescesiid}"/>
       &nbsp;
      </td>
      </c:if>

