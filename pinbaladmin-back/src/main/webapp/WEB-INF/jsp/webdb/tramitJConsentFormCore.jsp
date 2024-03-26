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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitJConsentFields.CONSENTIMENT)}">
        <tr id="tramitJConsent_consentiment_rowid">
          <td id="tramitJConsent_consentiment_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitJConsentFields.CONSENTIMENT])?'tramitJConsent.consentiment':__theForm.labels[TramitJConsentFields.CONSENTIMENT]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitJConsentFields.CONSENTIMENT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitJConsentFields.CONSENTIMENT]}" ></i>
              </c:if>
            </td>
          <td id="tramitJConsent_consentiment_columnvalueid">
          <form:errors path="tramitJConsent.consentiment" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,TramitJConsentFields.CONSENTIMENT)}" >
          <form:hidden path="tramitJConsent.consentiment"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.tramitJConsent.consentiment,__theForm.listOfValuesForConsentiment)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,TramitJConsentFields.CONSENTIMENT)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="tramitJConsent_consentiment"  onchange="if(typeof onChangeConsentiment == 'function') {  onChangeConsentiment(this); };"  cssClass="form-control col-md-9-optional" path="tramitJConsent.consentiment">
            <c:forEach items="${__theForm.listOfValuesForConsentiment}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitJConsentFields.CONSENTIMENTADJUNT)}">
        <tr id="tramitJConsent_consentimentadjunt_rowid">
          <td id="tramitJConsent_consentimentadjunt_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitJConsentFields.CONSENTIMENTADJUNT])?'tramitJConsent.consentimentadjunt':__theForm.labels[TramitJConsentFields.CONSENTIMENTADJUNT]}" />
             </label>
              <c:if test="${not empty __theForm.help[TramitJConsentFields.CONSENTIMENTADJUNT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitJConsentFields.CONSENTIMENTADJUNT]}" ></i>
              </c:if>
            </td>
          <td id="tramitJConsent_consentimentadjunt_columnvalueid">
          <form:errors path="tramitJConsent.consentimentadjunt" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,TramitJConsentFields.CONSENTIMENTADJUNT)}" >
          <form:hidden path="tramitJConsent.consentimentadjunt"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.tramitJConsent.consentimentadjunt,__theForm.listOfValuesForConsentimentadjunt)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,TramitJConsentFields.CONSENTIMENTADJUNT)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="tramitJConsent_consentimentadjunt"  onchange="if(typeof onChangeConsentimentadjunt == 'function') {  onChangeConsentimentadjunt(this); };"  cssClass="form-control col-md-9-optional" path="tramitJConsent.consentimentadjunt">
            <c:forEach items="${__theForm.listOfValuesForConsentimentadjunt}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.tramitJConsent.consentimentadjunt }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.tramitJConsent.consentimentadjunt }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitJConsentFields.URLCONSENTIMENT)}">
        <tr id="tramitJConsent_urlconsentiment_rowid">
          <td id="tramitJConsent_urlconsentiment_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitJConsentFields.URLCONSENTIMENT])?'tramitJConsent.urlconsentiment':__theForm.labels[TramitJConsentFields.URLCONSENTIMENT]}" />
             </label>
              <c:if test="${not empty __theForm.help[TramitJConsentFields.URLCONSENTIMENT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitJConsentFields.URLCONSENTIMENT]}" ></i>
              </c:if>
            </td>
          <td id="tramitJConsent_urlconsentiment_columnvalueid">
           <c:if test="${gen:contains(__theForm.readOnlyFields ,TramitJConsentFields.URLCONSENTIMENT)}">

             <c:if test="${ not empty __theForm.tramitJConsent.urlconsentiment}">
               <a href="${__theForm.tramitJConsent.urlconsentiment}" target="_blank">${__theForm.tramitJConsent.urlconsentiment}</a>

             </c:if>
           </c:if>

           <c:if test="${not (gen:contains(__theForm.readOnlyFields ,TramitJConsentFields.URLCONSENTIMENT))}">

            <form:errors path="tramitJConsent.urlconsentiment" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitJConsentFields.URLCONSENTIMENT)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitJConsentFields.URLCONSENTIMENT)? ' uneditable-input' : ''}"  style="" maxlength="255" path="tramitJConsent.urlconsentiment"   />

           </c:if>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitJConsentFields.ADJUNTID)}">
        <tr id="tramitJConsent_adjuntID_rowid">
          <td id="tramitJConsent_adjuntID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitJConsentFields.ADJUNTID])?'tramitJConsent.adjuntID':__theForm.labels[TramitJConsentFields.ADJUNTID]}" />
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
                  <span class="input-group-text" id="">
                        <form:checkbox path="adjuntIDDelete"/>
                        <small><fmt:message key="genapp.form.file.delete"/></small>
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
        
