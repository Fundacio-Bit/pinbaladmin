<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="InfoMadridFields" className="org.fundaciobit.pinbaladmin.model.fields.InfoMadridFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoMadridFields.CODI)}">
        <tr id="infoMadrid_codi_rowid">
          <td id="infoMadrid_codi_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoMadridFields.CODI])?'infoMadrid.codi':__theForm.labels[InfoMadridFields.CODI]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[InfoMadridFields.CODI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoMadridFields.CODI]}" ></i>
              </c:if>
            </td>
          <td id="infoMadrid_codi_columnvalueid">
            <form:errors path="infoMadrid.codi" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,InfoMadridFields.CODI)? 'true' : 'false'}" cssClass="w-50 form-control  ${gen:contains(__theForm.readOnlyFields ,InfoMadridFields.CODI)? ' uneditable-input' : ''}"  style="" maxlength="20" path="infoMadrid.codi"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoMadridFields.ESTATPROCEDIMENT)}">
        <tr id="infoMadrid_estatProcediment_rowid">
          <td id="infoMadrid_estatProcediment_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoMadridFields.ESTATPROCEDIMENT])?'infoMadrid.estatProcediment':__theForm.labels[InfoMadridFields.ESTATPROCEDIMENT]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoMadridFields.ESTATPROCEDIMENT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoMadridFields.ESTATPROCEDIMENT]}" ></i>
              </c:if>
            </td>
          <td id="infoMadrid_estatProcediment_columnvalueid">
          <form:errors path="infoMadrid.estatProcediment" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,InfoMadridFields.ESTATPROCEDIMENT)}" >
          <form:hidden path="infoMadrid.estatProcediment"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.infoMadrid.estatProcediment,__theForm.listOfValuesForEstatProcediment)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,InfoMadridFields.ESTATPROCEDIMENT)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="infoMadrid_estatProcediment"  onchange="if(typeof onChangeEstatProcediment == 'function') {  onChangeEstatProcediment(this); };"  cssClass="form-control col-md-9-optional" path="infoMadrid.estatProcediment">
            <c:forEach items="${__theForm.listOfValuesForEstatProcediment}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.infoMadrid.estatProcediment }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.infoMadrid.estatProcediment }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoMadridFields.ESTATAUTORITZACIO)}">
        <tr id="infoMadrid_estatAutoritzacio_rowid">
          <td id="infoMadrid_estatAutoritzacio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoMadridFields.ESTATAUTORITZACIO])?'infoMadrid.estatAutoritzacio':__theForm.labels[InfoMadridFields.ESTATAUTORITZACIO]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoMadridFields.ESTATAUTORITZACIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoMadridFields.ESTATAUTORITZACIO]}" ></i>
              </c:if>
            </td>
          <td id="infoMadrid_estatAutoritzacio_columnvalueid">
          <form:errors path="infoMadrid.estatAutoritzacio" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,InfoMadridFields.ESTATAUTORITZACIO)}" >
          <form:hidden path="infoMadrid.estatAutoritzacio"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.infoMadrid.estatAutoritzacio,__theForm.listOfValuesForEstatAutoritzacio)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,InfoMadridFields.ESTATAUTORITZACIO)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="infoMadrid_estatAutoritzacio"  onchange="if(typeof onChangeEstatAutoritzacio == 'function') {  onChangeEstatAutoritzacio(this); };"  cssClass="form-control col-md-9-optional" path="infoMadrid.estatAutoritzacio">
            <c:forEach items="${__theForm.listOfValuesForEstatAutoritzacio}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.infoMadrid.estatAutoritzacio }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.infoMadrid.estatAutoritzacio }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoMadridFields.MISSATGE)}">
        <tr id="infoMadrid_missatge_rowid">
          <td id="infoMadrid_missatge_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoMadridFields.MISSATGE])?'infoMadrid.missatge':__theForm.labels[InfoMadridFields.MISSATGE]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoMadridFields.MISSATGE]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoMadridFields.MISSATGE]}" ></i>
              </c:if>
            </td>
          <td id="infoMadrid_missatge_columnvalueid">
              <form:errors path="infoMadrid.missatge" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,InfoMadridFields.MISSATGE)? 'true' : 'false'}" path="infoMadrid.missatge"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_missatge" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_missatge" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('infoMadrid.missatge'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('infoMadrid.missatge'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('infoMadrid.missatge'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_missatge').on('click', function(){
					var valor = ($('#dropdownMenuContainer_missatge').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_missatge').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoMadridFields.CONSULTA)}">
        <tr id="infoMadrid_consulta_rowid">
          <td id="infoMadrid_consulta_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoMadridFields.CONSULTA])?'infoMadrid.consulta':__theForm.labels[InfoMadridFields.CONSULTA]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoMadridFields.CONSULTA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoMadridFields.CONSULTA]}" ></i>
              </c:if>
            </td>
          <td id="infoMadrid_consulta_columnvalueid">
              <form:errors path="infoMadrid.consulta" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,InfoMadridFields.CONSULTA)? 'true' : 'false'}" path="infoMadrid.consulta"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_consulta" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_consulta" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('infoMadrid.consulta'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('infoMadrid.consulta'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('infoMadrid.consulta'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_consulta').on('click', function(){
					var valor = ($('#dropdownMenuContainer_consulta').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_consulta').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoMadridFields.TITULARNOM)}">
        <tr id="infoMadrid_titularNom_rowid">
          <td id="infoMadrid_titularNom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoMadridFields.TITULARNOM])?'infoMadrid.titularNom':__theForm.labels[InfoMadridFields.TITULARNOM]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoMadridFields.TITULARNOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoMadridFields.TITULARNOM]}" ></i>
              </c:if>
            </td>
          <td id="infoMadrid_titularNom_columnvalueid">
            <form:errors path="infoMadrid.titularNom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,InfoMadridFields.TITULARNOM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,InfoMadridFields.TITULARNOM)? ' uneditable-input' : ''}"  style="" maxlength="240" path="infoMadrid.titularNom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoMadridFields.TITULARNIF)}">
        <tr id="infoMadrid_titularNif_rowid">
          <td id="infoMadrid_titularNif_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoMadridFields.TITULARNIF])?'infoMadrid.titularNif':__theForm.labels[InfoMadridFields.TITULARNIF]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoMadridFields.TITULARNIF]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoMadridFields.TITULARNIF]}" ></i>
              </c:if>
            </td>
          <td id="infoMadrid_titularNif_columnvalueid">
            <form:errors path="infoMadrid.titularNif" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,InfoMadridFields.TITULARNIF)? 'true' : 'false'}" cssClass="w-50 form-control  ${gen:contains(__theForm.readOnlyFields ,InfoMadridFields.TITULARNIF)? ' uneditable-input' : ''}"  style="" maxlength="20" path="infoMadrid.titularNif"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoMadridFields.DATAAUTORITZACIO)}">
        <tr id="infoMadrid_dataAutoritzacio_rowid">
          <td id="infoMadrid_dataAutoritzacio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoMadridFields.DATAAUTORITZACIO])?'infoMadrid.dataAutoritzacio':__theForm.labels[InfoMadridFields.DATAAUTORITZACIO]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoMadridFields.DATAAUTORITZACIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoMadridFields.DATAAUTORITZACIO]}" ></i>
              </c:if>
            </td>
          <td id="infoMadrid_dataAutoritzacio_columnvalueid">
    <form:errors path="infoMadrid.dataAutoritzacio" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="infoMadrid_dataAutoritzacio" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,InfoMadridFields.DATAAUTORITZACIO)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#infoMadrid_dataAutoritzacio" path="infoMadrid.dataAutoritzacio" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,InfoMadridFields.DATAAUTORITZACIO)}" >
                    <div class="input-group-append"  data-target="#infoMadrid_dataAutoritzacio"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#infoMadrid_dataAutoritzacio').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
        </script>           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoMadridFields.DATAENVIAMENT)}">
        <tr id="infoMadrid_dataEnviament_rowid">
          <td id="infoMadrid_dataEnviament_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoMadridFields.DATAENVIAMENT])?'infoMadrid.dataEnviament':__theForm.labels[InfoMadridFields.DATAENVIAMENT]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoMadridFields.DATAENVIAMENT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoMadridFields.DATAENVIAMENT]}" ></i>
              </c:if>
            </td>
          <td id="infoMadrid_dataEnviament_columnvalueid">
    <form:errors path="infoMadrid.dataEnviament" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="infoMadrid_dataEnviament" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,InfoMadridFields.DATAENVIAMENT)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#infoMadrid_dataEnviament" path="infoMadrid.dataEnviament" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,InfoMadridFields.DATAENVIAMENT)}" >
                    <div class="input-group-append"  data-target="#infoMadrid_dataEnviament"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#infoMadrid_dataEnviament').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
        </script>           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoMadridFields.INTENTS)}">
        <tr id="infoMadrid_intents_rowid">
          <td id="infoMadrid_intents_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoMadridFields.INTENTS])?'infoMadrid.intents':__theForm.labels[InfoMadridFields.INTENTS]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[InfoMadridFields.INTENTS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoMadridFields.INTENTS]}" ></i>
              </c:if>
            </td>
          <td id="infoMadrid_intents_columnvalueid">
            <form:errors path="infoMadrid.intents" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,InfoMadridFields.INTENTS)? 'true' : 'false'}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,InfoMadridFields.INTENTS)? ' uneditable-input' : ''}"  style=""  path="infoMadrid.intents"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoMadridFields.DATACONSULTA)}">
        <tr id="infoMadrid_dataConsulta_rowid">
          <td id="infoMadrid_dataConsulta_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoMadridFields.DATACONSULTA])?'infoMadrid.dataConsulta':__theForm.labels[InfoMadridFields.DATACONSULTA]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoMadridFields.DATACONSULTA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoMadridFields.DATACONSULTA]}" ></i>
              </c:if>
            </td>
          <td id="infoMadrid_dataConsulta_columnvalueid">
    <form:errors path="infoMadrid.dataConsulta" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="infoMadrid_dataConsulta" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,InfoMadridFields.DATACONSULTA)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#infoMadrid_dataConsulta" path="infoMadrid.dataConsulta" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,InfoMadridFields.DATACONSULTA)}" >
                    <div class="input-group-append"  data-target="#infoMadrid_dataConsulta"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#infoMadrid_dataConsulta').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
        </script>           </td>
        </tr>
        </c:if>
        
