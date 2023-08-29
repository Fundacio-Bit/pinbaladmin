      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${tramitGDadesTit.dadestitid}"/>
       &nbsp;
      </td>
      </c:if>

