<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="CampSolicitudFields" className="org.fundaciobit.pinbaladmin.model.fields.CampSolicitudFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,CampSolicitudFields.CAMPFORMULARIID)}">
        <tr id="campSolicitud_campFormulariID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[CampSolicitudFields.CAMPFORMULARIID])?'campSolicitud.campFormulariID':__theForm.labels[CampSolicitudFields.CAMPFORMULARIID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[CampSolicitudFields.CAMPFORMULARIID]}">
              <i class="icon-info-sign" title="${__theForm.help[CampSolicitudFields.CAMPFORMULARIID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="campSolicitud.campFormulariID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,CampSolicitudFields.CAMPFORMULARIID)}" >
          <form:hidden path="campSolicitud.campFormulariID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.campSolicitud.campFormulariID,__theForm.listOfCampFormulariForCampFormulariID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,CampSolicitudFields.CAMPFORMULARIID)}" >
          <form:select id="campSolicitud_campFormulariID"  onchange="if(typeof onChangeCampFormulariID == 'function') {  onChangeCampFormulariID(this); };"  cssClass="input-xxlarge" path="campSolicitud.campFormulariID">
            <c:forEach items="${__theForm.listOfCampFormulariForCampFormulariID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CampSolicitudFields.SOLICITUDSERVEIID)}">
        <tr id="campSolicitud_solicitudServeiID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[CampSolicitudFields.SOLICITUDSERVEIID])?'campSolicitud.solicitudServeiID':__theForm.labels[CampSolicitudFields.SOLICITUDSERVEIID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[CampSolicitudFields.SOLICITUDSERVEIID]}">
              <i class="icon-info-sign" title="${__theForm.help[CampSolicitudFields.SOLICITUDSERVEIID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="campSolicitud.solicitudServeiID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,CampSolicitudFields.SOLICITUDSERVEIID)}" >
          <form:hidden path="campSolicitud.solicitudServeiID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.campSolicitud.solicitudServeiID,__theForm.listOfSolicitudServeiForSolicitudServeiID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,CampSolicitudFields.SOLICITUDSERVEIID)}" >
          <form:select id="campSolicitud_solicitudServeiID"  onchange="if(typeof onChangeSolicitudServeiID == 'function') {  onChangeSolicitudServeiID(this); };"  cssClass="input-xxlarge" path="campSolicitud.solicitudServeiID">
            <c:forEach items="${__theForm.listOfSolicitudServeiForSolicitudServeiID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CampSolicitudFields.VALOR)}">
        <tr id="campSolicitud_valor_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[CampSolicitudFields.VALOR])?'campSolicitud.valor':__theForm.labels[CampSolicitudFields.VALOR]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[CampSolicitudFields.VALOR]}">
              <i class="icon-info-sign" title="${__theForm.help[CampSolicitudFields.VALOR]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="campSolicitud.valor" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,CampSolicitudFields.VALOR)? 'true' : 'false'}" path="campSolicitud.valor"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('campSolicitud.valor'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('campSolicitud.valor'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('campSolicitud.valor'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
           </td>
        </tr>
        </c:if>
        
