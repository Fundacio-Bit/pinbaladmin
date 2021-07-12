<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="SolicitudServeiFields" className="org.fundaciobit.pinbaladmin.model.fields.SolicitudServeiFields"/>



        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key < 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[solicitudServei.id]}" />
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


        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudServeiFields.ID)}">
          <td>
          <c:out value="${solicitudServei.id}" />
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudServeiFields.SOLICITUDID)}">
          <td>
          <c:set var="tmp">${solicitudServei.solicitudID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfSolicitudForSolicitudID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudServeiFields.SERVEIID)}">
          <td>
          <c:set var="tmp">${solicitudServei.serveiID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfServeiForServeiID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudServeiFields.ESTATSOLICITUDSERVEIID)}">
          <td>
          <c:set var="tmp">${solicitudServei.estatSolicitudServeiID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfEstatSolicitudServeiForEstatSolicitudServeiID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudServeiFields.NORMALEGAL)}">
          <td>
          <c:out value="${solicitudServei.normaLegal}" />
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudServeiFields.ENLLAZNORMALEGAL)}">
          <td>
                       <c:if test="${ not empty solicitudServei.enllazNormaLegal}">
               <a href="${solicitudServei.enllazNormaLegal}" target="_blank">${solicitudServei.enllazNormaLegal}</a>
             </c:if>

          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudServeiFields.ARTICLES)}">
          <td>
          <c:out value="${solicitudServei.articles}" />
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudServeiFields.TIPUSCONSENTIMENT)}">
          <td>
          <c:out value="${solicitudServei.tipusConsentiment}" />
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudServeiFields.CONSENTIMENT)}">
          <td>
          <c:out value="${solicitudServei.consentiment}" />
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudServeiFields.ENLLAZCONSENTIMENT)}">
          <td>
                       <c:if test="${ not empty solicitudServei.enllazConsentiment}">
               <a href="${solicitudServei.enllazConsentiment}" target="_blank">${solicitudServei.enllazConsentiment}</a>
             </c:if>

          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudServeiFields.NOTES)}">
          <td>
          <c:out value="${solicitudServei.notes}" />
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudServeiFields.CADUCA)}">
          <td>
          <c:out value="${solicitudServei.caduca}" />
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudServeiFields.FECHACADUCA)}">
          <td>
          <c:out value="${solicitudServei.fechaCaduca}" />
          </td>
        </c:if>


        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key >= 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[solicitudServei.id]}" />
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


