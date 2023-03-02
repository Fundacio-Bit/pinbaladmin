<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="CampSolicitudFields" className="org.fundaciobit.pinbaladmin.model.fields.CampSolicitudFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,CampSolicitudFields.CAMPFORMULARIID)}">
        <tr id="campSolicitud_campFormulariID_rowid">
          <td id="campSolicitud_campFormulariID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CampSolicitudFields.CAMPFORMULARIID])?'campSolicitud.campFormulariID':__theForm.labels[CampSolicitudFields.CAMPFORMULARIID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[CampSolicitudFields.CAMPFORMULARIID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CampSolicitudFields.CAMPFORMULARIID]}" ></i>
              </c:if>
            </td>
          <td id="campSolicitud_campFormulariID_columnvalueid">
          <form:errors path="campSolicitud.campFormulariID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,CampSolicitudFields.CAMPFORMULARIID)}" >
          <form:hidden path="campSolicitud.campFormulariID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.campSolicitud.campFormulariID,__theForm.listOfCampFormulariForCampFormulariID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,CampSolicitudFields.CAMPFORMULARIID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="campSolicitud_campFormulariID"  onchange="if(typeof onChangeCampFormulariID == 'function') {  onChangeCampFormulariID(this); };"  cssClass="form-control col-md-9-optional" path="campSolicitud.campFormulariID">
            <c:forEach items="${__theForm.listOfCampFormulariForCampFormulariID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CampSolicitudFields.SOLICITUDSERVEIID)}">
        <tr id="campSolicitud_solicitudServeiID_rowid">
          <td id="campSolicitud_solicitudServeiID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CampSolicitudFields.SOLICITUDSERVEIID])?'campSolicitud.solicitudServeiID':__theForm.labels[CampSolicitudFields.SOLICITUDSERVEIID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[CampSolicitudFields.SOLICITUDSERVEIID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CampSolicitudFields.SOLICITUDSERVEIID]}" ></i>
              </c:if>
            </td>
          <td id="campSolicitud_solicitudServeiID_columnvalueid">
          <form:errors path="campSolicitud.solicitudServeiID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,CampSolicitudFields.SOLICITUDSERVEIID)}" >
          <form:hidden path="campSolicitud.solicitudServeiID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.campSolicitud.solicitudServeiID,__theForm.listOfSolicitudServeiForSolicitudServeiID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,CampSolicitudFields.SOLICITUDSERVEIID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="campSolicitud_solicitudServeiID"  onchange="if(typeof onChangeSolicitudServeiID == 'function') {  onChangeSolicitudServeiID(this); };"  cssClass="form-control col-md-9-optional" path="campSolicitud.solicitudServeiID">
            <c:forEach items="${__theForm.listOfSolicitudServeiForSolicitudServeiID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CampSolicitudFields.VALOR)}">
        <tr id="campSolicitud_valor_rowid">
          <td id="campSolicitud_valor_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CampSolicitudFields.VALOR])?'campSolicitud.valor':__theForm.labels[CampSolicitudFields.VALOR]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[CampSolicitudFields.VALOR]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CampSolicitudFields.VALOR]}" ></i>
              </c:if>
            </td>
          <td id="campSolicitud_valor_columnvalueid">
              <form:errors path="campSolicitud.valor" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,CampSolicitudFields.VALOR)? 'true' : 'false'}" path="campSolicitud.valor"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_valor" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_valor" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('campSolicitud.valor'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('campSolicitud.valor'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('campSolicitud.valor'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_valor').on('click', function(){
					var valor = ($('#dropdownMenuContainer_valor').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_valor').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
