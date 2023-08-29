      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${tramitBDadesSoli.dadessoliid}"/>
       &nbsp;
      </td>
      </c:if>

