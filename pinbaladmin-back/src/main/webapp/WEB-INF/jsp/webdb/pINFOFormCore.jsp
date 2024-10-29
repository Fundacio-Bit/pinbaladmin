<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PinfoFields" className="org.fundaciobit.pinbaladmin.model.fields.PinfoFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,PinfoFields.INCIDENCIAID)}">
        <tr id="pinfo_incidenciaID_rowid">
          <td id="pinfo_incidenciaID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PinfoFields.INCIDENCIAID])?'pinfo.incidenciaID':__theForm.labels[PinfoFields.INCIDENCIAID]}" />
             </label>
              <c:if test="${not empty __theForm.help[PinfoFields.INCIDENCIAID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PinfoFields.INCIDENCIAID]}" ></i>
              </c:if>
            </td>
          <td id="pinfo_incidenciaID_columnvalueid">
          <form:errors path="pinfo.incidenciaID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PinfoFields.INCIDENCIAID)}" >
          <form:hidden path="pinfo.incidenciaID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.pinfo.incidenciaID,__theForm.listOfIncidenciaTecnicaForIncidenciaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PinfoFields.INCIDENCIAID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="pinfo_incidenciaID"  onchange="if(typeof onChangeIncidenciaID == 'function') {  onChangeIncidenciaID(this); };"  cssClass="form-control col-md-9-optional" path="pinfo.incidenciaID">
            <c:forEach items="${__theForm.listOfIncidenciaTecnicaForIncidenciaID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.pinfo.incidenciaID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.pinfo.incidenciaID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PinfoFields.SOLICITANTNIF)}">
        <tr id="pinfo_solicitantNIF_rowid">
          <td id="pinfo_solicitantNIF_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PinfoFields.SOLICITANTNIF])?'pinfo.solicitantNIF':__theForm.labels[PinfoFields.SOLICITANTNIF]}" />
             </label>
              <c:if test="${not empty __theForm.help[PinfoFields.SOLICITANTNIF]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PinfoFields.SOLICITANTNIF]}" ></i>
              </c:if>
            </td>
          <td id="pinfo_solicitantNIF_columnvalueid">
            <form:errors path="pinfo.solicitantNIF" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PinfoFields.SOLICITANTNIF)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,PinfoFields.SOLICITANTNIF)? ' uneditable-input' : ''}"  style="" maxlength="100" path="pinfo.solicitantNIF"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PinfoFields.ESTAT)}">
        <tr id="pinfo_estat_rowid">
          <td id="pinfo_estat_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PinfoFields.ESTAT])?'pinfo.estat':__theForm.labels[PinfoFields.ESTAT]}" />
             </label>
              <c:if test="${not empty __theForm.help[PinfoFields.ESTAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PinfoFields.ESTAT]}" ></i>
              </c:if>
            </td>
          <td id="pinfo_estat_columnvalueid">
            <form:errors path="pinfo.estat" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PinfoFields.ESTAT)? 'true' : 'false'}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,PinfoFields.ESTAT)? ' uneditable-input' : ''}"  style=""  path="pinfo.estat"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PinfoFields.FITXERID)}">
        <tr id="pinfo_fitxerID_rowid">
          <td id="pinfo_fitxerID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PinfoFields.FITXERID])?'pinfo.fitxerID':__theForm.labels[PinfoFields.FITXERID]}" />
             </label>
              <c:if test="${not empty __theForm.help[PinfoFields.FITXERID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PinfoFields.FITXERID]}" ></i>
              </c:if>
            </td>
          <td id="pinfo_fitxerID_columnvalueid">
              <form:errors path="pinfo.fitxerID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,PinfoFields.FITXERID)}" >
              <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.pinfo.fitxer)}"/>">${__theForm.pinfo.fitxer.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,PinfoFields.FITXERID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,PinfoFields.FITXERID)? 'true' : 'false'}" cssClass="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,PinfoFields.FITXERID)? ' uneditable-input' : ''}"   path="fitxerID" type="file" />
                  <label class="custom-file-label" for="fitxerID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.pinfo.fitxer}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.pinfo.fitxer)}"/>">${__theForm.pinfo.fitxer.nom}</a>
</small>
                  </span>
                  <span class="input-group-text" id="">
                        <form:checkbox path="fitxerIDDelete"/>
                        <small><fmt:message key="genapp.form.file.delete"/></small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="fitxerID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#fitxerID').on('change', function(){
						var ruta = $('#fitxerID').val(); 
						var rutaArray = ruta.split('\\');
						$('#fitxerID-custom-file-label').css('display','block');
						$('#fitxerID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PinfoFields.FITXERFIRMATID)}">
        <tr id="pinfo_fitxerfirmatID_rowid">
          <td id="pinfo_fitxerfirmatID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PinfoFields.FITXERFIRMATID])?'pinfo.fitxerfirmatID':__theForm.labels[PinfoFields.FITXERFIRMATID]}" />
             </label>
              <c:if test="${not empty __theForm.help[PinfoFields.FITXERFIRMATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PinfoFields.FITXERFIRMATID]}" ></i>
              </c:if>
            </td>
          <td id="pinfo_fitxerfirmatID_columnvalueid">
              <form:errors path="pinfo.fitxerfirmatID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,PinfoFields.FITXERFIRMATID)}" >
              <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.pinfo.fitxerfirmat)}"/>">${__theForm.pinfo.fitxerfirmat.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,PinfoFields.FITXERFIRMATID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,PinfoFields.FITXERFIRMATID)? 'true' : 'false'}" cssClass="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,PinfoFields.FITXERFIRMATID)? ' uneditable-input' : ''}"   path="fitxerfirmatID" type="file" />
                  <label class="custom-file-label" for="fitxerfirmatID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.pinfo.fitxerfirmat}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.pinfo.fitxerfirmat)}"/>">${__theForm.pinfo.fitxerfirmat.nom}</a>
</small>
                  </span>
                  <span class="input-group-text" id="">
                        <form:checkbox path="fitxerfirmatIDDelete"/>
                        <small><fmt:message key="genapp.form.file.delete"/></small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="fitxerfirmatID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#fitxerfirmatID').on('change', function(){
						var ruta = $('#fitxerfirmatID').val(); 
						var rutaArray = ruta.split('\\');
						$('#fitxerfirmatID-custom-file-label').css('display','block');
						$('#fitxerfirmatID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PinfoFields.PORTAFIBID)}">
        <tr id="pinfo_portafibid_rowid">
          <td id="pinfo_portafibid_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PinfoFields.PORTAFIBID])?'pinfo.portafibid':__theForm.labels[PinfoFields.PORTAFIBID]}" />
             </label>
              <c:if test="${not empty __theForm.help[PinfoFields.PORTAFIBID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PinfoFields.PORTAFIBID]}" ></i>
              </c:if>
            </td>
          <td id="pinfo_portafibid_columnvalueid">
            <form:errors path="pinfo.portafibid" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PinfoFields.PORTAFIBID)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,PinfoFields.PORTAFIBID)? ' uneditable-input' : ''}"  style="" maxlength="50" path="pinfo.portafibid"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PinfoFields.DESTINATARINIF)}">
        <tr id="pinfo_destinatariNIF_rowid">
          <td id="pinfo_destinatariNIF_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PinfoFields.DESTINATARINIF])?'pinfo.destinatariNIF':__theForm.labels[PinfoFields.DESTINATARINIF]}" />
             </label>
              <c:if test="${not empty __theForm.help[PinfoFields.DESTINATARINIF]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PinfoFields.DESTINATARINIF]}" ></i>
              </c:if>
            </td>
          <td id="pinfo_destinatariNIF_columnvalueid">
            <form:errors path="pinfo.destinatariNIF" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PinfoFields.DESTINATARINIF)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,PinfoFields.DESTINATARINIF)? ' uneditable-input' : ''}"  style="" maxlength="100" path="pinfo.destinatariNIF"   />

           </td>
        </tr>
        </c:if>
        
