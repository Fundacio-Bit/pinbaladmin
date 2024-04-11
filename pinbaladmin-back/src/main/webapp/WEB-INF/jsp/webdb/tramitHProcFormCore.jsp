<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TramitHProcFields" className="org.fundaciobit.pinbaladmin.model.fields.TramitHProcFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitHProcFields.TRAMITID)}">
        <tr id="tramitHProc_tramitid_rowid">
          <td id="tramitHProc_tramitid_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitHProcFields.TRAMITID])?'tramitHProc.tramitid':__theForm.labels[TramitHProcFields.TRAMITID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitHProcFields.TRAMITID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitHProcFields.TRAMITID]}" ></i>
              </c:if>
            </td>
          <td id="tramitHProc_tramitid_columnvalueid">
          <form:errors path="tramitHProc.tramitid" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,TramitHProcFields.TRAMITID)}" >
          <form:hidden path="tramitHProc.tramitid"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.tramitHProc.tramitid,__theForm.listOfTramitAPersAutForTramitid)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,TramitHProcFields.TRAMITID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="tramitHProc_tramitid"  onchange="if(typeof onChangeTramitid == 'function') {  onChangeTramitid(this); };"  cssClass="form-control col-md-9-optional" path="tramitHProc.tramitid">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitHProcFields.NOM)}">
        <tr id="tramitHProc_nom_rowid">
          <td id="tramitHProc_nom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitHProcFields.NOM])?'tramitHProc.nom':__theForm.labels[TramitHProcFields.NOM]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitHProcFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitHProcFields.NOM]}" ></i>
              </c:if>
            </td>
          <td id="tramitHProc_nom_columnvalueid">
            <form:errors path="tramitHProc.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitHProcFields.NOM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitHProcFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="240" path="tramitHProc.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitHProcFields.CODI)}">
        <tr id="tramitHProc_codi_rowid">
          <td id="tramitHProc_codi_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitHProcFields.CODI])?'tramitHProc.codi':__theForm.labels[TramitHProcFields.CODI]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitHProcFields.CODI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitHProcFields.CODI]}" ></i>
              </c:if>
            </td>
          <td id="tramitHProc_codi_columnvalueid">
            <form:errors path="tramitHProc.codi" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitHProcFields.CODI)? 'true' : 'false'}" cssClass="w-50 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitHProcFields.CODI)? ' uneditable-input' : ''}"  style="" maxlength="30" path="tramitHProc.codi"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitHProcFields.TIPUS)}">
        <tr id="tramitHProc_tipus_rowid">
          <td id="tramitHProc_tipus_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitHProcFields.TIPUS])?'tramitHProc.tipus':__theForm.labels[TramitHProcFields.TIPUS]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitHProcFields.TIPUS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitHProcFields.TIPUS]}" ></i>
              </c:if>
            </td>
          <td id="tramitHProc_tipus_columnvalueid">
          <form:errors path="tramitHProc.tipus" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,TramitHProcFields.TIPUS)}" >
          <form:hidden path="tramitHProc.tipus"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.tramitHProc.tipus,__theForm.listOfValuesForTipus)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,TramitHProcFields.TIPUS)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="tramitHProc_tipus"  onchange="if(typeof onChangeTipus == 'function') {  onChangeTipus(this); };"  cssClass="form-control col-md-9-optional" path="tramitHProc.tipus">
            <c:forEach items="${__theForm.listOfValuesForTipus}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitHProcFields.URLSEU)}">
        <tr id="tramitHProc_urlseu_rowid">
          <td id="tramitHProc_urlseu_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitHProcFields.URLSEU])?'tramitHProc.urlseu':__theForm.labels[TramitHProcFields.URLSEU]}" />
             </label>
              <c:if test="${not empty __theForm.help[TramitHProcFields.URLSEU]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitHProcFields.URLSEU]}" ></i>
              </c:if>
            </td>
          <td id="tramitHProc_urlseu_columnvalueid">
           <c:if test="${gen:contains(__theForm.readOnlyFields ,TramitHProcFields.URLSEU)}">

             <c:if test="${ not empty __theForm.tramitHProc.urlseu}">
               <a href="${__theForm.tramitHProc.urlseu}" target="_blank">${__theForm.tramitHProc.urlseu}</a>

             </c:if>
           </c:if>

           <c:if test="${not (gen:contains(__theForm.readOnlyFields ,TramitHProcFields.URLSEU))}">

            <form:errors path="tramitHProc.urlseu" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitHProcFields.URLSEU)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitHProcFields.URLSEU)? ' uneditable-input' : ''}"  style="" maxlength="240" path="tramitHProc.urlseu"   />

           </c:if>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitHProcFields.CADUCITAT)}">
        <tr id="tramitHProc_caducitat_rowid">
          <td id="tramitHProc_caducitat_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitHProcFields.CADUCITAT])?'tramitHProc.caducitat':__theForm.labels[TramitHProcFields.CADUCITAT]}" />
             </label>
              <c:if test="${not empty __theForm.help[TramitHProcFields.CADUCITAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitHProcFields.CADUCITAT]}" ></i>
              </c:if>
            </td>
          <td id="tramitHProc_caducitat_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,TramitHProcFields.CADUCITAT)}" >
              <form:errors path="tramitHProc.caducitat" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,TramitHProcFields.CADUCITAT)? 'false' : 'true'}" path="tramitHProc.caducitat" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,TramitHProcFields.CADUCITAT)}" >
                <fmt:message key="genapp.checkbox.${__theForm.tramitHProc.caducitat}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitHProcFields.CADUCITATDATA)}">
        <tr id="tramitHProc_caducitatdata_rowid">
          <td id="tramitHProc_caducitatdata_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitHProcFields.CADUCITATDATA])?'tramitHProc.caducitatdata':__theForm.labels[TramitHProcFields.CADUCITATDATA]}" />
             </label>
              <c:if test="${not empty __theForm.help[TramitHProcFields.CADUCITATDATA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitHProcFields.CADUCITATDATA]}" ></i>
              </c:if>
            </td>
          <td id="tramitHProc_caducitatdata_columnvalueid">
    <form:errors path="tramitHProc.caducitatdata" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="tramitHProc_caducitatdata" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitHProcFields.CADUCITATDATA)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#tramitHProc_caducitatdata" path="tramitHProc.caducitatdata" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,TramitHProcFields.CADUCITATDATA)}" >
                    <div class="input-group-append"  data-target="#tramitHProc_caducitatdata"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#tramitHProc_caducitatdata').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitHProcFields.DESCRIPCIO)}">
        <tr id="tramitHProc_descripcio_rowid">
          <td id="tramitHProc_descripcio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitHProcFields.DESCRIPCIO])?'tramitHProc.descripcio':__theForm.labels[TramitHProcFields.DESCRIPCIO]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitHProcFields.DESCRIPCIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitHProcFields.DESCRIPCIO]}" ></i>
              </c:if>
            </td>
          <td id="tramitHProc_descripcio_columnvalueid">
            <form:errors path="tramitHProc.descripcio" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitHProcFields.DESCRIPCIO)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitHProcFields.DESCRIPCIO)? ' uneditable-input' : ''}"  style="" maxlength="240" path="tramitHProc.descripcio"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitHProcFields.PETICIONSALDIA)}">
        <tr id="tramitHProc_peticionsaldia_rowid">
          <td id="tramitHProc_peticionsaldia_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitHProcFields.PETICIONSALDIA])?'tramitHProc.peticionsaldia':__theForm.labels[TramitHProcFields.PETICIONSALDIA]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitHProcFields.PETICIONSALDIA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitHProcFields.PETICIONSALDIA]}" ></i>
              </c:if>
            </td>
          <td id="tramitHProc_peticionsaldia_columnvalueid">
            <form:errors path="tramitHProc.peticionsaldia" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitHProcFields.PETICIONSALDIA)? 'true' : 'false'}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitHProcFields.PETICIONSALDIA)? ' uneditable-input' : ''}"  style=""  path="tramitHProc.peticionsaldia"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitHProcFields.PETICIONSALMES)}">
        <tr id="tramitHProc_peticionsalmes_rowid">
          <td id="tramitHProc_peticionsalmes_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitHProcFields.PETICIONSALMES])?'tramitHProc.peticionsalmes':__theForm.labels[TramitHProcFields.PETICIONSALMES]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitHProcFields.PETICIONSALMES]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitHProcFields.PETICIONSALMES]}" ></i>
              </c:if>
            </td>
          <td id="tramitHProc_peticionsalmes_columnvalueid">
            <form:errors path="tramitHProc.peticionsalmes" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitHProcFields.PETICIONSALMES)? 'true' : 'false'}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitHProcFields.PETICIONSALMES)? ' uneditable-input' : ''}"  style=""  path="tramitHProc.peticionsalmes"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitHProcFields.PERIODICO)}">
        <tr id="tramitHProc_periodico_rowid">
          <td id="tramitHProc_periodico_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitHProcFields.PERIODICO])?'tramitHProc.periodico':__theForm.labels[TramitHProcFields.PERIODICO]}" />
             </label>
              <c:if test="${not empty __theForm.help[TramitHProcFields.PERIODICO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitHProcFields.PERIODICO]}" ></i>
              </c:if>
            </td>
          <td id="tramitHProc_periodico_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,TramitHProcFields.PERIODICO)}" >
              <form:errors path="tramitHProc.periodico" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,TramitHProcFields.PERIODICO)? 'false' : 'true'}" path="tramitHProc.periodico" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,TramitHProcFields.PERIODICO)}" >
                <fmt:message key="genapp.checkbox.${__theForm.tramitHProc.periodico}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitHProcFields.AUTOMATIZADO)}">
        <tr id="tramitHProc_automatizado_rowid">
          <td id="tramitHProc_automatizado_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitHProcFields.AUTOMATIZADO])?'tramitHProc.automatizado':__theForm.labels[TramitHProcFields.AUTOMATIZADO]}" />
             </label>
              <c:if test="${not empty __theForm.help[TramitHProcFields.AUTOMATIZADO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitHProcFields.AUTOMATIZADO]}" ></i>
              </c:if>
            </td>
          <td id="tramitHProc_automatizado_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,TramitHProcFields.AUTOMATIZADO)}" >
              <form:errors path="tramitHProc.automatizado" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,TramitHProcFields.AUTOMATIZADO)? 'false' : 'true'}" path="tramitHProc.automatizado" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,TramitHProcFields.AUTOMATIZADO)}" >
                <fmt:message key="genapp.checkbox.${__theForm.tramitHProc.automatizado}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
