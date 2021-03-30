<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="DocumentSolicitudFields" className="org.fundaciobit.pinbaladmin.model.fields.DocumentSolicitudFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,DocumentSolicitudFields.DOCUMENTID)}">
        <tr id="documentSolicitud_documentID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[DocumentSolicitudFields.DOCUMENTID])?'documentSolicitud.documentID':__theForm.labels[DocumentSolicitudFields.DOCUMENTID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[DocumentSolicitudFields.DOCUMENTID]}">
              <i class="icon-info-sign" title="${__theForm.help[DocumentSolicitudFields.DOCUMENTID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="documentSolicitud.documentID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,DocumentSolicitudFields.DOCUMENTID)}" >
          <form:hidden path="documentSolicitud.documentID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.documentSolicitud.documentID,__theForm.listOfDocumentForDocumentID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,DocumentSolicitudFields.DOCUMENTID)}" >
          <form:select id="documentSolicitud_documentID"  onchange="if(typeof onChangeDocumentID == 'function') {  onChangeDocumentID(this); };"  cssClass="input-xxlarge" path="documentSolicitud.documentID">
            <c:forEach items="${__theForm.listOfDocumentForDocumentID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,DocumentSolicitudFields.SOLICITUDID)}">
        <tr id="documentSolicitud_solicitudID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[DocumentSolicitudFields.SOLICITUDID])?'documentSolicitud.solicitudID':__theForm.labels[DocumentSolicitudFields.SOLICITUDID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[DocumentSolicitudFields.SOLICITUDID]}">
              <i class="icon-info-sign" title="${__theForm.help[DocumentSolicitudFields.SOLICITUDID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="documentSolicitud.solicitudID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,DocumentSolicitudFields.SOLICITUDID)}" >
          <form:hidden path="documentSolicitud.solicitudID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.documentSolicitud.solicitudID,__theForm.listOfSolicitudForSolicitudID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,DocumentSolicitudFields.SOLICITUDID)}" >
          <form:select id="documentSolicitud_solicitudID"  onchange="if(typeof onChangeSolicitudID == 'function') {  onChangeSolicitudID(this); };"  cssClass="input-xxlarge" path="documentSolicitud.solicitudID">
            <c:forEach items="${__theForm.listOfSolicitudForSolicitudID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
