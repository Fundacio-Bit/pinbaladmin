<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PINFOFields" className="org.fundaciobit.pinbaladmin.model.fields.PINFOFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,PINFOFields.INCIDENCIAID)}">
        <tr id="pINFO_IncidenciaID_rowid">
          <td id="pINFO_IncidenciaID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PINFOFields.INCIDENCIAID])?'pINFO.IncidenciaID':__theForm.labels[PINFOFields.INCIDENCIAID]}" />
             </label>
              <c:if test="${not empty __theForm.help[PINFOFields.INCIDENCIAID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PINFOFields.INCIDENCIAID]}" ></i>
              </c:if>
            </td>
          <td id="pINFO_IncidenciaID_columnvalueid">
          <form:errors path="pINFO.IncidenciaID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PINFOFields.INCIDENCIAID)}" >
          <form:hidden path="pINFO.IncidenciaID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.pINFO.IncidenciaID,__theForm.listOfIncidenciaTecnicaForIncidenciaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PINFOFields.INCIDENCIAID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="pINFO_IncidenciaID"  onchange="if(typeof onChangeIncidenciaID == 'function') {  onChangeIncidenciaID(this); };"  cssClass="form-control col-md-9-optional" path="pINFO.IncidenciaID">
            <c:forEach items="${__theForm.listOfIncidenciaTecnicaForIncidenciaID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.pINFO.IncidenciaID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.pINFO.IncidenciaID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PINFOFields.ESTAT)}">
        <tr id="pINFO_estat_rowid">
          <td id="pINFO_estat_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PINFOFields.ESTAT])?'pINFO.estat':__theForm.labels[PINFOFields.ESTAT]}" />
             </label>
              <c:if test="${not empty __theForm.help[PINFOFields.ESTAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PINFOFields.ESTAT]}" ></i>
              </c:if>
            </td>
          <td id="pINFO_estat_columnvalueid">
            <form:errors path="pINFO.estat" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PINFOFields.ESTAT)? 'true' : 'false'}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,PINFOFields.ESTAT)? ' uneditable-input' : ''}"  style=""  path="pINFO.estat"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PINFOFields.FITXERID)}">
        <tr id="pINFO_fitxerID_rowid">
          <td id="pINFO_fitxerID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PINFOFields.FITXERID])?'pINFO.fitxerID':__theForm.labels[PINFOFields.FITXERID]}" />
             </label>
              <c:if test="${not empty __theForm.help[PINFOFields.FITXERID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PINFOFields.FITXERID]}" ></i>
              </c:if>
            </td>
          <td id="pINFO_fitxerID_columnvalueid">
              <form:errors path="pINFO.fitxerID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,PINFOFields.FITXERID)}" >
              <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.pINFO.fitxer)}"/>">${__theForm.pINFO.fitxer.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,PINFOFields.FITXERID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,PINFOFields.FITXERID)? 'true' : 'false'}" cssClass="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,PINFOFields.FITXERID)? ' uneditable-input' : ''}"   path="fitxerID" type="file" />
                  <label class="custom-file-label" for="fitxerID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.pINFO.fitxer}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.pINFO.fitxer)}"/>">${__theForm.pINFO.fitxer.nom}</a>
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PINFOFields.FITXERFIRMATID)}">
        <tr id="pINFO_fitxerfirmatID_rowid">
          <td id="pINFO_fitxerfirmatID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PINFOFields.FITXERFIRMATID])?'pINFO.fitxerfirmatID':__theForm.labels[PINFOFields.FITXERFIRMATID]}" />
             </label>
              <c:if test="${not empty __theForm.help[PINFOFields.FITXERFIRMATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PINFOFields.FITXERFIRMATID]}" ></i>
              </c:if>
            </td>
          <td id="pINFO_fitxerfirmatID_columnvalueid">
              <form:errors path="pINFO.fitxerfirmatID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,PINFOFields.FITXERFIRMATID)}" >
              <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.pINFO.fitxerfirmat)}"/>">${__theForm.pINFO.fitxerfirmat.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,PINFOFields.FITXERFIRMATID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,PINFOFields.FITXERFIRMATID)? 'true' : 'false'}" cssClass="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,PINFOFields.FITXERFIRMATID)? ' uneditable-input' : ''}"   path="fitxerfirmatID" type="file" />
                  <label class="custom-file-label" for="fitxerfirmatID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.pINFO.fitxerfirmat}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.pINFO.fitxerfirmat)}"/>">${__theForm.pINFO.fitxerfirmat.nom}</a>
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PINFOFields.PORTAFIBID)}">
        <tr id="pINFO_portafibid_rowid">
          <td id="pINFO_portafibid_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PINFOFields.PORTAFIBID])?'pINFO.portafibid':__theForm.labels[PINFOFields.PORTAFIBID]}" />
             </label>
              <c:if test="${not empty __theForm.help[PINFOFields.PORTAFIBID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PINFOFields.PORTAFIBID]}" ></i>
              </c:if>
            </td>
          <td id="pINFO_portafibid_columnvalueid">
            <form:errors path="pINFO.portafibid" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PINFOFields.PORTAFIBID)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,PINFOFields.PORTAFIBID)? ' uneditable-input' : ''}"  style="" maxlength="50" path="pINFO.portafibid"   />

           </td>
        </tr>
        </c:if>
        
