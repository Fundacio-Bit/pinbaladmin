<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TramitAPersAutFields" className="org.fundaciobit.pinbaladmin.model.fields.TramitAPersAutFields"/>



        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key < 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[tramitAPersAut.persautid]}" />
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


        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitAPersAutFields.PERSAUTID)}">
          <td>
          ${tramitAPersAut.persautid}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitAPersAutFields.TRAMITID)}">
          <td>
          ${tramitAPersAut.tramitid}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitAPersAutFields.DATATRAMIT)}">
          <td> <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${tramitAPersAut.datatramit}" /></td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitAPersAutFields.NIF)}">
          <td>
          ${tramitAPersAut.nif}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitAPersAutFields.MAIL)}">
          <td>
          ${tramitAPersAut.mail}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitAPersAutFields.TELEFON)}">
          <td>
          ${tramitAPersAut.telefon}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitAPersAutFields.NOM)}">
          <td>
          ${tramitAPersAut.nom}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitAPersAutFields.LLINATGE1)}">
          <td>
          ${tramitAPersAut.llinatge1}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitAPersAutFields.LLINATGE2)}">
          <td>
          ${tramitAPersAut.llinatge2}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitAPersAutFields.URLSISTRA)}">
          <td>
          ${tramitAPersAut.urlsistra}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitAPersAutFields.IDSESIONFORMULARIO)}">
          <td>
          ${tramitAPersAut.idsesionformulario}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitAPersAutFields.IDSESIONTRAMITE)}">
          <td>
          ${tramitAPersAut.idsesiontramite}
          </td>
        </c:if>


        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key >= 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[tramitAPersAut.persautid]}" />
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


