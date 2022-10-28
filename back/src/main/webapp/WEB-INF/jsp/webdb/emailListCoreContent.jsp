<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EmailFields" className="org.fundaciobit.pinbaladmin.model.fields.EmailFields"/>



        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key < 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[email.emailID]}" />
             </c:if>
             <c:if test="${not empty __entry.value.valueField }">
               <c:set var="__tmp" value="${pageScope}" />
               <c:set var="__trosos" value="${fn:split(__entry.value.valueField.fullName,'.')}" />
               <c:forEach var="__tros" items="${__trosos}">
                  <c:set var="__tmp" value="${__tmp[__tros]}" />
               </c:forEach>
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__tmp}" />
             </c:if>
          </td>
          </c:if>
          </c:forEach>


        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EmailFields.EMAILID)}">
          <td>
          <c:out value="${email.emailID}" />
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EmailFields.DATAENVIAMENT)}">
          <td> <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${email.dataEnviament}" /></td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EmailFields.ENVIADOR)}">
          <td>
          <c:out value="${email.enviador}" />
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EmailFields.DESTINATARIS)}">
          <td>
          <c:out value="${email.destinataris}" />
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EmailFields.SUBJECT)}">
          <td>
          <c:out value="${email.subject}" />
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EmailFields.MESSAGE)}">
          <td>
          <c:out value="${email.message}" />
          </td>
        </c:if>


        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key >= 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[email.emailID]}" />
             </c:if>
             <c:if test="${not empty __entry.value.valueField }">
               <c:set var="__tmp" value="${pageScope}" />
               <c:set var="__trosos" value="${fn:split(__entry.value.valueField.fullName,'.')}" />
               <c:forEach var="__tros" items="${__trosos}">
                  <c:set var="__tmp" value="${__tmp[__tros]}" />
               </c:forEach>
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__tmp}" />
             </c:if>
          </td>
          </c:if>
          </c:forEach>


