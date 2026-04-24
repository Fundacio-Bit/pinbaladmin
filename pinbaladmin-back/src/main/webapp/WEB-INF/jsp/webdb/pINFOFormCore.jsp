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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PinfoFields.ENTITAT)}">
        <tr id="pinfo_entitat_rowid">
          <td id="pinfo_entitat_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PinfoFields.ENTITAT])?'pinfo.entitat':__theForm.labels[PinfoFields.ENTITAT]}" />
             </label>
              <c:if test="${not empty __theForm.help[PinfoFields.ENTITAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PinfoFields.ENTITAT]}" ></i>
              </c:if>
            </td>
          <td id="pinfo_entitat_columnvalueid">
          <form:errors path="pinfo.entitat" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PinfoFields.ENTITAT)}" >
          <form:hidden path="pinfo.entitat"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.pinfo.entitat,__theForm.listOfValuesForEntitat)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PinfoFields.ENTITAT)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="pinfo_entitat"  onchange="if(typeof onChangeEntitat == 'function') {  onChangeEntitat(this); };"  cssClass="form-control col-md-9-optional" path="pinfo.entitat">
            <c:forEach items="${__theForm.listOfValuesForEntitat}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.pinfo.entitat }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.pinfo.entitat }">
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
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PinfoFields.ESTAT)}" >
          <form:hidden path="pinfo.estat"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.pinfo.estat,__theForm.listOfValuesForEstat)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PinfoFields.ESTAT)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="pinfo_estat"  onchange="if(typeof onChangeEstat == 'function') {  onChangeEstat(this); };"  cssClass="form-control col-md-9-optional" path="pinfo.estat">
            <c:forEach items="${__theForm.listOfValuesForEstat}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.pinfo.estat }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.pinfo.estat }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PinfoFields.DESTINATARINOM)}">
        <tr id="pinfo_destinatariNom_rowid">
          <td id="pinfo_destinatariNom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PinfoFields.DESTINATARINOM])?'pinfo.destinatariNom':__theForm.labels[PinfoFields.DESTINATARINOM]}" />
             </label>
              <c:if test="${not empty __theForm.help[PinfoFields.DESTINATARINOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PinfoFields.DESTINATARINOM]}" ></i>
              </c:if>
            </td>
          <td id="pinfo_destinatariNom_columnvalueid">
            <form:errors path="pinfo.destinatariNom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PinfoFields.DESTINATARINOM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,PinfoFields.DESTINATARINOM)? ' uneditable-input' : ''}"  style="" maxlength="255" path="pinfo.destinatariNom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PinfoFields.MISSATGEPINBAL)}">
        <tr id="pinfo_missatgePinbal_rowid">
          <td id="pinfo_missatgePinbal_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PinfoFields.MISSATGEPINBAL])?'pinfo.missatgePinbal':__theForm.labels[PinfoFields.MISSATGEPINBAL]}" />
             </label>
              <c:if test="${not empty __theForm.help[PinfoFields.MISSATGEPINBAL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PinfoFields.MISSATGEPINBAL]}" ></i>
              </c:if>
            </td>
          <td id="pinfo_missatgePinbal_columnvalueid">
              <form:errors path="pinfo.missatgePinbal" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,PinfoFields.MISSATGEPINBAL)? 'true' : 'false'}" path="pinfo.missatgePinbal"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_missatgePinbal" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_missatgePinbal" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('pinfo.missatgePinbal'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('pinfo.missatgePinbal'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('pinfo.missatgePinbal'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_missatgePinbal').on('click', function(){
					var valor = ($('#dropdownMenuContainer_missatgePinbal').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_missatgePinbal').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PinfoFields.LOGPPNBAL)}">
        <tr id="pinfo_logpPnbal_rowid">
          <td id="pinfo_logpPnbal_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PinfoFields.LOGPPNBAL])?'pinfo.logpPnbal':__theForm.labels[PinfoFields.LOGPPNBAL]}" />
             </label>
              <c:if test="${not empty __theForm.help[PinfoFields.LOGPPNBAL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PinfoFields.LOGPPNBAL]}" ></i>
              </c:if>
            </td>
          <td id="pinfo_logpPnbal_columnvalueid">
              <form:errors path="pinfo.logpPnbal" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,PinfoFields.LOGPPNBAL)? 'true' : 'false'}" path="pinfo.logpPnbal"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_logpPnbal" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_logpPnbal" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('pinfo.logpPnbal'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('pinfo.logpPnbal'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('pinfo.logpPnbal'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_logpPnbal').on('click', function(){
					var valor = ($('#dropdownMenuContainer_logpPnbal').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_logpPnbal').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PinfoFields.MISSATGESOLICITANT)}">
        <tr id="pinfo_missatgeSolicitant_rowid">
          <td id="pinfo_missatgeSolicitant_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PinfoFields.MISSATGESOLICITANT])?'pinfo.missatgeSolicitant':__theForm.labels[PinfoFields.MISSATGESOLICITANT]}" />
             </label>
              <c:if test="${not empty __theForm.help[PinfoFields.MISSATGESOLICITANT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PinfoFields.MISSATGESOLICITANT]}" ></i>
              </c:if>
            </td>
          <td id="pinfo_missatgeSolicitant_columnvalueid">
              <form:errors path="pinfo.missatgeSolicitant" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,PinfoFields.MISSATGESOLICITANT)? 'true' : 'false'}" path="pinfo.missatgeSolicitant"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_missatgeSolicitant" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_missatgeSolicitant" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('pinfo.missatgeSolicitant'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('pinfo.missatgeSolicitant'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('pinfo.missatgeSolicitant'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_missatgeSolicitant').on('click', function(){
					var valor = ($('#dropdownMenuContainer_missatgeSolicitant').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_missatgeSolicitant').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
