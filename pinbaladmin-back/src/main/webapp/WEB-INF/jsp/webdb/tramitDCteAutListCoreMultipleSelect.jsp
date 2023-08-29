      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${tramitDCteAut.cteautid}"/>
       &nbsp;
      </td>
      </c:if>

