<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TramitJConsentFields" className="org.fundaciobit.pinbaladmin.model.fields.TramitJConsentFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitJConsentFields.TRAMITID)}">
        <tr id="tramitJConsent_tramitid_rowid">
          <td id="tramitJConsent_tramitid_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitJConsentFields.TRAMITID])?'tramitJConsent.tramitid':__theForm.labels[TramitJConsentFields.TRAMITID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitJConsentFields.TRAMITID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitJConsentFields.TRAMITID]}" ></i>
              </c:if>
            </td>
          <td id="tramitJConsent_tramitid_columnvalueid">
          <form:errors path="tramitJConsent.tramitid" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,TramitJConsentFields.TRAMITID)}" >
          <form:hidden path="tramitJConsent.tramitid"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.tramitJConsent.tramitid,__theForm.listOfTramitAPersAutForTramitid)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,TramitJConsentFields.TRAMITID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="tramitJConsent_tramitid"  onchange="if(typeof onChangeTramitid == 'function') {  onChangeTramitid(this); };"  cssClass="form-control col-md-9-optional" path="tramitJConsent.tramitid">
            <c:forEach items="${__theForm.listOfTramitAPersAutForTramitid}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitJConsentFields.ADJUNTID)}">
        <tr id="tramitJConsent_adjuntID_rowid">
          <td id="tramitJConsent_adjuntID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitJConsentFields.ADJUNTID])?'tramitJConsent.adjuntID':__theForm.labels[TramitJConsentFields.ADJUNTID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitJConsentFields.ADJUNTID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitJConsentFields.ADJUNTID]}" ></i>
              </c:if>
            </td>
          <td id="tramitJConsent_adjuntID_columnvalueid">
              <form:errors path="tramitJConsent.adjuntID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,TramitJConsentFields.ADJUNTID)}" >
              <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.tramitJConsent.adjunt)}"/>">${__theForm.tramitJConsent.adjunt.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,TramitJConsentFields.ADJUNTID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,TramitJConsentFields.ADJUNTID)? 'true' : 'false'}" cssClass="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,TramitJConsentFields.ADJUNTID)? ' uneditable-input' : ''}"   path="adjuntID" type="file" />
                  <label class="custom-file-label" for="adjuntID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.tramitJConsent.adjunt}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.tramitJConsent.adjunt)}"/>">${__theForm.tramitJConsent.adjunt.nom}</a>
</small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="adjuntID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#adjuntID').on('change', function(){
						var ruta = $('#adjuntID').val(); 
						var rutaArray = ruta.split('\\');
						$('#adjuntID-custom-file-label').css('display','block');
						$('#adjuntID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
