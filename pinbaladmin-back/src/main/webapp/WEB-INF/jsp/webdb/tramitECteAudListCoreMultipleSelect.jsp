      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${tramitECteAud.cteaudid}"/>
       &nbsp;
      </td>
      </c:if>

