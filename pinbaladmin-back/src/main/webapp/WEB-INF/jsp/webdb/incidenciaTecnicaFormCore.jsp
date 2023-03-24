<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="IncidenciaTecnicaFields" className="org.fundaciobit.pinbaladmin.model.fields.IncidenciaTecnicaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,IncidenciaTecnicaFields.TITOL)}">
        <tr id="incidenciaTecnica_titol_rowid">
          <td id="incidenciaTecnica_titol_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[IncidenciaTecnicaFields.TITOL])?'incidenciaTecnica.titol':__theForm.labels[IncidenciaTecnicaFields.TITOL]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[IncidenciaTecnicaFields.TITOL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[IncidenciaTecnicaFields.TITOL]}" ></i>
              </c:if>
            </td>
          <td id="incidenciaTecnica_titol_columnvalueid">
            <form:errors path="incidenciaTecnica.titol" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,IncidenciaTecnicaFields.TITOL)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,IncidenciaTecnicaFields.TITOL)? ' uneditable-input' : ''}"  style="" maxlength="255" path="incidenciaTecnica.titol"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,IncidenciaTecnicaFields.DESCRIPCIO)}">
        <tr id="incidenciaTecnica_descripcio_rowid">
          <td id="incidenciaTecnica_descripcio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[IncidenciaTecnicaFields.DESCRIPCIO])?'incidenciaTecnica.descripcio':__theForm.labels[IncidenciaTecnicaFields.DESCRIPCIO]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[IncidenciaTecnicaFields.DESCRIPCIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[IncidenciaTecnicaFields.DESCRIPCIO]}" ></i>
              </c:if>
            </td>
          <td id="incidenciaTecnica_descripcio_columnvalueid">
              <form:errors path="incidenciaTecnica.descripcio" cssClass="errorField alert alert-danger" />
       <form:textarea cssClass=" ${gen:contains(__theForm.readOnlyFields ,IncidenciaTecnicaFields.DESCRIPCIO)? 'mceEditorReadOnly':'mceEditor'}"  path="incidenciaTecnica.descripcio"  />
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,IncidenciaTecnicaFields.DATAINICI)}">
        <tr id="incidenciaTecnica_dataInici_rowid">
          <td id="incidenciaTecnica_dataInici_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[IncidenciaTecnicaFields.DATAINICI])?'incidenciaTecnica.dataInici':__theForm.labels[IncidenciaTecnicaFields.DATAINICI]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[IncidenciaTecnicaFields.DATAINICI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[IncidenciaTecnicaFields.DATAINICI]}" ></i>
              </c:if>
            </td>
          <td id="incidenciaTecnica_dataInici_columnvalueid">
    <form:errors path="incidenciaTecnica.dataInici" cssClass="errorField alert alert-danger" />
            <div class="form-group">
                <div class="input-group date" id="incidenciaTecnica_dataInici" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,IncidenciaTecnicaFields.DATAINICI)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#incidenciaTecnica_dataInici" path="incidenciaTecnica.dataInici" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,IncidenciaTecnicaFields.DATAINICI)}" >
                    <div class="input-group-append"  data-target="#incidenciaTecnica_dataInici"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#incidenciaTecnica_dataInici').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,IncidenciaTecnicaFields.DATAFI)}">
        <tr id="incidenciaTecnica_datafi_rowid">
          <td id="incidenciaTecnica_datafi_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[IncidenciaTecnicaFields.DATAFI])?'incidenciaTecnica.datafi':__theForm.labels[IncidenciaTecnicaFields.DATAFI]}" />
             </label>
              <c:if test="${not empty __theForm.help[IncidenciaTecnicaFields.DATAFI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[IncidenciaTecnicaFields.DATAFI]}" ></i>
              </c:if>
            </td>
          <td id="incidenciaTecnica_datafi_columnvalueid">
    <form:errors path="incidenciaTecnica.datafi" cssClass="errorField alert alert-danger" />
            <div class="form-group">
                <div class="input-group date" id="incidenciaTecnica_datafi" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,IncidenciaTecnicaFields.DATAFI)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#incidenciaTecnica_datafi" path="incidenciaTecnica.datafi" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,IncidenciaTecnicaFields.DATAFI)}" >
                    <div class="input-group-append"  data-target="#incidenciaTecnica_datafi"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#incidenciaTecnica_datafi').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,IncidenciaTecnicaFields.ESTAT)}">
        <tr id="incidenciaTecnica_estat_rowid">
          <td id="incidenciaTecnica_estat_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[IncidenciaTecnicaFields.ESTAT])?'incidenciaTecnica.estat':__theForm.labels[IncidenciaTecnicaFields.ESTAT]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[IncidenciaTecnicaFields.ESTAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[IncidenciaTecnicaFields.ESTAT]}" ></i>
              </c:if>
            </td>
          <td id="incidenciaTecnica_estat_columnvalueid">
          <form:errors path="incidenciaTecnica.estat" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,IncidenciaTecnicaFields.ESTAT)}" >
          <form:hidden path="incidenciaTecnica.estat"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.incidenciaTecnica.estat,__theForm.listOfValuesForEstat)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,IncidenciaTecnicaFields.ESTAT)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="incidenciaTecnica_estat"  onchange="if(typeof onChangeEstat == 'function') {  onChangeEstat(this); };"  cssClass="form-control col-md-9-optional" path="incidenciaTecnica.estat">
            <c:forEach items="${__theForm.listOfValuesForEstat}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,IncidenciaTecnicaFields.CREADOR)}">
        <tr id="incidenciaTecnica_creador_rowid">
          <td id="incidenciaTecnica_creador_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[IncidenciaTecnicaFields.CREADOR])?'incidenciaTecnica.creador':__theForm.labels[IncidenciaTecnicaFields.CREADOR]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[IncidenciaTecnicaFields.CREADOR]}">
              <i class="fas fa-info-circle" title="${__theForm.help[IncidenciaTecnicaFields.CREADOR]}" ></i>
              </c:if>
            </td>
          <td id="incidenciaTecnica_creador_columnvalueid">
          <form:errors path="incidenciaTecnica.creador" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,IncidenciaTecnicaFields.CREADOR)}" >
          <form:hidden path="incidenciaTecnica.creador"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.incidenciaTecnica.creador,__theForm.listOfValuesForCreador)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,IncidenciaTecnicaFields.CREADOR)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="incidenciaTecnica_creador"  onchange="if(typeof onChangeCreador == 'function') {  onChangeCreador(this); };"  cssClass="form-control col-md-9-optional" path="incidenciaTecnica.creador">
            <c:forEach items="${__theForm.listOfValuesForCreador}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,IncidenciaTecnicaFields.TIPUS)}">
        <tr id="incidenciaTecnica_tipus_rowid">
          <td id="incidenciaTecnica_tipus_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[IncidenciaTecnicaFields.TIPUS])?'incidenciaTecnica.tipus':__theForm.labels[IncidenciaTecnicaFields.TIPUS]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[IncidenciaTecnicaFields.TIPUS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[IncidenciaTecnicaFields.TIPUS]}" ></i>
              </c:if>
            </td>
          <td id="incidenciaTecnica_tipus_columnvalueid">
          <form:errors path="incidenciaTecnica.tipus" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,IncidenciaTecnicaFields.TIPUS)}" >
          <form:hidden path="incidenciaTecnica.tipus"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.incidenciaTecnica.tipus,__theForm.listOfValuesForTipus)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,IncidenciaTecnicaFields.TIPUS)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="incidenciaTecnica_tipus"  onchange="if(typeof onChangeTipus == 'function') {  onChangeTipus(this); };"  cssClass="form-control col-md-9-optional" path="incidenciaTecnica.tipus">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,IncidenciaTecnicaFields.NOMENTITAT)}">
        <tr id="incidenciaTecnica_nomEntitat_rowid">
          <td id="incidenciaTecnica_nomEntitat_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[IncidenciaTecnicaFields.NOMENTITAT])?'incidenciaTecnica.nomEntitat':__theForm.labels[IncidenciaTecnicaFields.NOMENTITAT]}" />
             </label>
              <c:if test="${not empty __theForm.help[IncidenciaTecnicaFields.NOMENTITAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[IncidenciaTecnicaFields.NOMENTITAT]}" ></i>
              </c:if>
            </td>
          <td id="incidenciaTecnica_nomEntitat_columnvalueid">
            <form:errors path="incidenciaTecnica.nomEntitat" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,IncidenciaTecnicaFields.NOMENTITAT)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,IncidenciaTecnicaFields.NOMENTITAT)? ' uneditable-input' : ''}"  style="" maxlength="255" path="incidenciaTecnica.nomEntitat"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,IncidenciaTecnicaFields.CONTACTENOM)}">
        <tr id="incidenciaTecnica_contacteNom_rowid">
          <td id="incidenciaTecnica_contacteNom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[IncidenciaTecnicaFields.CONTACTENOM])?'incidenciaTecnica.contacteNom':__theForm.labels[IncidenciaTecnicaFields.CONTACTENOM]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[IncidenciaTecnicaFields.CONTACTENOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[IncidenciaTecnicaFields.CONTACTENOM]}" ></i>
              </c:if>
            </td>
          <td id="incidenciaTecnica_contacteNom_columnvalueid">
            <form:errors path="incidenciaTecnica.contacteNom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,IncidenciaTecnicaFields.CONTACTENOM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,IncidenciaTecnicaFields.CONTACTENOM)? ' uneditable-input' : ''}"  style="" maxlength="255" path="incidenciaTecnica.contacteNom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,IncidenciaTecnicaFields.CONTACTEEMAIL)}">
        <tr id="incidenciaTecnica_contacteEmail_rowid">
          <td id="incidenciaTecnica_contacteEmail_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[IncidenciaTecnicaFields.CONTACTEEMAIL])?'incidenciaTecnica.contacteEmail':__theForm.labels[IncidenciaTecnicaFields.CONTACTEEMAIL]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[IncidenciaTecnicaFields.CONTACTEEMAIL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[IncidenciaTecnicaFields.CONTACTEEMAIL]}" ></i>
              </c:if>
            </td>
          <td id="incidenciaTecnica_contacteEmail_columnvalueid">
            <form:errors path="incidenciaTecnica.contacteEmail" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,IncidenciaTecnicaFields.CONTACTEEMAIL)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,IncidenciaTecnicaFields.CONTACTEEMAIL)? ' uneditable-input' : ''}"  style="" maxlength="100" path="incidenciaTecnica.contacteEmail"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,IncidenciaTecnicaFields.CONTACTETELEFON)}">
        <tr id="incidenciaTecnica_contacteTelefon_rowid">
          <td id="incidenciaTecnica_contacteTelefon_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[IncidenciaTecnicaFields.CONTACTETELEFON])?'incidenciaTecnica.contacteTelefon':__theForm.labels[IncidenciaTecnicaFields.CONTACTETELEFON]}" />
             </label>
              <c:if test="${not empty __theForm.help[IncidenciaTecnicaFields.CONTACTETELEFON]}">
              <i class="fas fa-info-circle" title="${__theForm.help[IncidenciaTecnicaFields.CONTACTETELEFON]}" ></i>
              </c:if>
            </td>
          <td id="incidenciaTecnica_contacteTelefon_columnvalueid">
            <form:errors path="incidenciaTecnica.contacteTelefon" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,IncidenciaTecnicaFields.CONTACTETELEFON)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,IncidenciaTecnicaFields.CONTACTETELEFON)? ' uneditable-input' : ''}"  style="" maxlength="100" path="incidenciaTecnica.contacteTelefon"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,IncidenciaTecnicaFields.CAIDIDENTIFICADORCONSULTA)}">
        <tr id="incidenciaTecnica_caidIdentificadorConsulta_rowid">
          <td id="incidenciaTecnica_caidIdentificadorConsulta_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[IncidenciaTecnicaFields.CAIDIDENTIFICADORCONSULTA])?'incidenciaTecnica.caidIdentificadorConsulta':__theForm.labels[IncidenciaTecnicaFields.CAIDIDENTIFICADORCONSULTA]}" />
             </label>
              <c:if test="${not empty __theForm.help[IncidenciaTecnicaFields.CAIDIDENTIFICADORCONSULTA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[IncidenciaTecnicaFields.CAIDIDENTIFICADORCONSULTA]}" ></i>
              </c:if>
            </td>
          <td id="incidenciaTecnica_caidIdentificadorConsulta_columnvalueid">
            <form:errors path="incidenciaTecnica.caidIdentificadorConsulta" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,IncidenciaTecnicaFields.CAIDIDENTIFICADORCONSULTA)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,IncidenciaTecnicaFields.CAIDIDENTIFICADORCONSULTA)? ' uneditable-input' : ''}"  style="" maxlength="100" path="incidenciaTecnica.caidIdentificadorConsulta"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,IncidenciaTecnicaFields.CAIDNUMEROSEGUIMENT)}">
        <tr id="incidenciaTecnica_caidNumeroSeguiment_rowid">
          <td id="incidenciaTecnica_caidNumeroSeguiment_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[IncidenciaTecnicaFields.CAIDNUMEROSEGUIMENT])?'incidenciaTecnica.caidNumeroSeguiment':__theForm.labels[IncidenciaTecnicaFields.CAIDNUMEROSEGUIMENT]}" />
             </label>
              <c:if test="${not empty __theForm.help[IncidenciaTecnicaFields.CAIDNUMEROSEGUIMENT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[IncidenciaTecnicaFields.CAIDNUMEROSEGUIMENT]}" ></i>
              </c:if>
            </td>
          <td id="incidenciaTecnica_caidNumeroSeguiment_columnvalueid">
            <form:errors path="incidenciaTecnica.caidNumeroSeguiment" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,IncidenciaTecnicaFields.CAIDNUMEROSEGUIMENT)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,IncidenciaTecnicaFields.CAIDNUMEROSEGUIMENT)? ' uneditable-input' : ''}"  style="" maxlength="100" path="incidenciaTecnica.caidNumeroSeguiment"   />

           </td>
        </tr>
        </c:if>
        
