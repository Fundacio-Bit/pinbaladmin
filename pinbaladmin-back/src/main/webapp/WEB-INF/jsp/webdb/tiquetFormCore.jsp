<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TiquetFields" className="org.fundaciobit.pinbaladmin.model.fields.TiquetFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,TiquetFields.TITOL)}">
        <tr id="tiquet_titol_rowid">
          <td id="tiquet_titol_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TiquetFields.TITOL])?'tiquet.titol':__theForm.labels[TiquetFields.TITOL]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TiquetFields.TITOL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TiquetFields.TITOL]}" ></i>
              </c:if>
            </td>
          <td id="tiquet_titol_columnvalueid">
            <form:errors path="tiquet.titol" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TiquetFields.TITOL)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,TiquetFields.TITOL)? ' uneditable-input' : ''}"  style="" maxlength="256" path="tiquet.titol"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TiquetFields.DESCRIPCIO)}">
        <tr id="tiquet_descripcio_rowid">
          <td id="tiquet_descripcio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TiquetFields.DESCRIPCIO])?'tiquet.descripcio':__theForm.labels[TiquetFields.DESCRIPCIO]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TiquetFields.DESCRIPCIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TiquetFields.DESCRIPCIO]}" ></i>
              </c:if>
            </td>
          <td id="tiquet_descripcio_columnvalueid">
              <form:errors path="tiquet.descripcio" cssClass="errorField alert alert-danger" />
       <form:textarea cssClass=" ${gen:contains(__theForm.readOnlyFields ,TiquetFields.DESCRIPCIO)? 'mceEditorReadOnly':'mceEditor'}"  path="tiquet.descripcio"  />
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TiquetFields.INFORMADOR)}">
        <tr id="tiquet_informador_rowid">
          <td id="tiquet_informador_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TiquetFields.INFORMADOR])?'tiquet.informador':__theForm.labels[TiquetFields.INFORMADOR]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TiquetFields.INFORMADOR]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TiquetFields.INFORMADOR]}" ></i>
              </c:if>
            </td>
          <td id="tiquet_informador_columnvalueid">
            <form:errors path="tiquet.informador" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TiquetFields.INFORMADOR)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,TiquetFields.INFORMADOR)? ' uneditable-input' : ''}"  style="" maxlength="100" path="tiquet.informador"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TiquetFields.DATAALTA)}">
        <tr id="tiquet_dataAlta_rowid">
          <td id="tiquet_dataAlta_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TiquetFields.DATAALTA])?'tiquet.dataAlta':__theForm.labels[TiquetFields.DATAALTA]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TiquetFields.DATAALTA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TiquetFields.DATAALTA]}" ></i>
              </c:if>
            </td>
          <td id="tiquet_dataAlta_columnvalueid">
    <form:errors path="tiquet.dataAlta" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="tiquet_dataAlta" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TiquetFields.DATAALTA)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#tiquet_dataAlta" path="tiquet.dataAlta" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,TiquetFields.DATAALTA)}" >
                    <div class="input-group-append"  data-target="#tiquet_dataAlta"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#tiquet_dataAlta').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TiquetFields.ESTATTIQUETID)}">
        <tr id="tiquet_estatTiquetID_rowid">
          <td id="tiquet_estatTiquetID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TiquetFields.ESTATTIQUETID])?'tiquet.estatTiquetID':__theForm.labels[TiquetFields.ESTATTIQUETID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TiquetFields.ESTATTIQUETID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TiquetFields.ESTATTIQUETID]}" ></i>
              </c:if>
            </td>
          <td id="tiquet_estatTiquetID_columnvalueid">
          <form:errors path="tiquet.estatTiquetID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,TiquetFields.ESTATTIQUETID)}" >
          <form:hidden path="tiquet.estatTiquetID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.tiquet.estatTiquetID,__theForm.listOfEstatTiquetForEstatTiquetID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,TiquetFields.ESTATTIQUETID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="tiquet_estatTiquetID"  onchange="if(typeof onChangeEstatTiquetID == 'function') {  onChangeEstatTiquetID(this); };"  cssClass="form-control col-md-9-optional" path="tiquet.estatTiquetID">
            <c:forEach items="${__theForm.listOfEstatTiquetForEstatTiquetID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TiquetFields.TIPUSTIQUETID)}">
        <tr id="tiquet_tipusTiquetID_rowid">
          <td id="tiquet_tipusTiquetID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TiquetFields.TIPUSTIQUETID])?'tiquet.tipusTiquetID':__theForm.labels[TiquetFields.TIPUSTIQUETID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TiquetFields.TIPUSTIQUETID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TiquetFields.TIPUSTIQUETID]}" ></i>
              </c:if>
            </td>
          <td id="tiquet_tipusTiquetID_columnvalueid">
          <form:errors path="tiquet.tipusTiquetID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,TiquetFields.TIPUSTIQUETID)}" >
          <form:hidden path="tiquet.tipusTiquetID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.tiquet.tipusTiquetID,__theForm.listOfTipusTiquetForTipusTiquetID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,TiquetFields.TIPUSTIQUETID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="tiquet_tipusTiquetID"  onchange="if(typeof onChangeTipusTiquetID == 'function') {  onChangeTipusTiquetID(this); };"  cssClass="form-control col-md-9-optional" path="tiquet.tipusTiquetID">
            <c:forEach items="${__theForm.listOfTipusTiquetForTipusTiquetID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TiquetFields.VERSIOPINBAL)}">
        <tr id="tiquet_versioPinbal_rowid">
          <td id="tiquet_versioPinbal_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TiquetFields.VERSIOPINBAL])?'tiquet.versioPinbal':__theForm.labels[TiquetFields.VERSIOPINBAL]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TiquetFields.VERSIOPINBAL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TiquetFields.VERSIOPINBAL]}" ></i>
              </c:if>
            </td>
          <td id="tiquet_versioPinbal_columnvalueid">
            <form:errors path="tiquet.versioPinbal" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TiquetFields.VERSIOPINBAL)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,TiquetFields.VERSIOPINBAL)? ' uneditable-input' : ''}"  style="" maxlength="100" path="tiquet.versioPinbal"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TiquetFields.DATAINICI)}">
        <tr id="tiquet_dataInici_rowid">
          <td id="tiquet_dataInici_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TiquetFields.DATAINICI])?'tiquet.dataInici':__theForm.labels[TiquetFields.DATAINICI]}" />
             </label>
              <c:if test="${not empty __theForm.help[TiquetFields.DATAINICI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TiquetFields.DATAINICI]}" ></i>
              </c:if>
            </td>
          <td id="tiquet_dataInici_columnvalueid">
    <form:errors path="tiquet.dataInici" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="tiquet_dataInici" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TiquetFields.DATAINICI)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#tiquet_dataInici" path="tiquet.dataInici" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,TiquetFields.DATAINICI)}" >
                    <div class="input-group-append"  data-target="#tiquet_dataInici"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#tiquet_dataInici').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TiquetFields.DATAINCIDENCIA)}">
        <tr id="tiquet_dataIncidencia_rowid">
          <td id="tiquet_dataIncidencia_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TiquetFields.DATAINCIDENCIA])?'tiquet.dataIncidencia':__theForm.labels[TiquetFields.DATAINCIDENCIA]}" />
             </label>
              <c:if test="${not empty __theForm.help[TiquetFields.DATAINCIDENCIA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TiquetFields.DATAINCIDENCIA]}" ></i>
              </c:if>
            </td>
          <td id="tiquet_dataIncidencia_columnvalueid">
    <form:errors path="tiquet.dataIncidencia" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="tiquet_dataIncidencia" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TiquetFields.DATAINCIDENCIA)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#tiquet_dataIncidencia" path="tiquet.dataIncidencia" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,TiquetFields.DATAINCIDENCIA)}" >
                    <div class="input-group-append"  data-target="#tiquet_dataIncidencia"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#tiquet_dataIncidencia').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TiquetFields.SOLUCIONATPER)}">
        <tr id="tiquet_solucionatPer_rowid">
          <td id="tiquet_solucionatPer_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TiquetFields.SOLUCIONATPER])?'tiquet.solucionatPer':__theForm.labels[TiquetFields.SOLUCIONATPER]}" />
             </label>
              <c:if test="${not empty __theForm.help[TiquetFields.SOLUCIONATPER]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TiquetFields.SOLUCIONATPER]}" ></i>
              </c:if>
            </td>
          <td id="tiquet_solucionatPer_columnvalueid">
            <form:errors path="tiquet.solucionatPer" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TiquetFields.SOLUCIONATPER)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,TiquetFields.SOLUCIONATPER)? ' uneditable-input' : ''}"  style="" maxlength="100" path="tiquet.solucionatPer"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TiquetFields.DATAFI)}">
        <tr id="tiquet_datafi_rowid">
          <td id="tiquet_datafi_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TiquetFields.DATAFI])?'tiquet.datafi':__theForm.labels[TiquetFields.DATAFI]}" />
             </label>
              <c:if test="${not empty __theForm.help[TiquetFields.DATAFI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TiquetFields.DATAFI]}" ></i>
              </c:if>
            </td>
          <td id="tiquet_datafi_columnvalueid">
    <form:errors path="tiquet.datafi" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="tiquet_datafi" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TiquetFields.DATAFI)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#tiquet_datafi" path="tiquet.datafi" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,TiquetFields.DATAFI)}" >
                    <div class="input-group-append"  data-target="#tiquet_datafi"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#tiquet_datafi').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TiquetFields.NOTES)}">
        <tr id="tiquet_notes_rowid">
          <td id="tiquet_notes_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TiquetFields.NOTES])?'tiquet.notes':__theForm.labels[TiquetFields.NOTES]}" />
             </label>
              <c:if test="${not empty __theForm.help[TiquetFields.NOTES]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TiquetFields.NOTES]}" ></i>
              </c:if>
            </td>
          <td id="tiquet_notes_columnvalueid">
              <form:errors path="tiquet.notes" cssClass="errorField alert alert-danger" />
       <form:textarea cssClass=" ${gen:contains(__theForm.readOnlyFields ,TiquetFields.NOTES)? 'mceEditorReadOnly':'mceEditor'}"  path="tiquet.notes"  />
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TiquetFields.ENTORN)}">
        <tr id="tiquet_entorn_rowid">
          <td id="tiquet_entorn_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TiquetFields.ENTORN])?'tiquet.entorn':__theForm.labels[TiquetFields.ENTORN]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TiquetFields.ENTORN]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TiquetFields.ENTORN]}" ></i>
              </c:if>
            </td>
          <td id="tiquet_entorn_columnvalueid">
          <form:errors path="tiquet.entorn" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,TiquetFields.ENTORN)}" >
          <form:hidden path="tiquet.entorn"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.tiquet.entorn,__theForm.listOfValuesForEntorn)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,TiquetFields.ENTORN)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="tiquet_entorn"  onchange="if(typeof onChangeEntorn == 'function') {  onChangeEntorn(this); };"  cssClass="form-control col-md-9-optional" path="tiquet.entorn">
            <c:forEach items="${__theForm.listOfValuesForEntorn}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TiquetFields.ADJUNT1ID)}">
        <tr id="tiquet_adjunt1ID_rowid">
          <td id="tiquet_adjunt1ID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TiquetFields.ADJUNT1ID])?'tiquet.adjunt1ID':__theForm.labels[TiquetFields.ADJUNT1ID]}" />
             </label>
              <c:if test="${not empty __theForm.help[TiquetFields.ADJUNT1ID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TiquetFields.ADJUNT1ID]}" ></i>
              </c:if>
            </td>
          <td id="tiquet_adjunt1ID_columnvalueid">
              <form:errors path="tiquet.adjunt1ID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,TiquetFields.ADJUNT1ID)}" >
              <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.tiquet.adjunt1)}"/>">${__theForm.tiquet.adjunt1.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,TiquetFields.ADJUNT1ID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,TiquetFields.ADJUNT1ID)? 'true' : 'false'}" cssClass="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,TiquetFields.ADJUNT1ID)? ' uneditable-input' : ''}"   path="adjunt1ID" type="file" />
                  <label class="custom-file-label" for="adjunt1ID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.tiquet.adjunt1}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.tiquet.adjunt1)}"/>">${__theForm.tiquet.adjunt1.nom}</a>
</small>
                  </span>
                  <span class="input-group-text" id="">
                        <form:checkbox path="adjunt1IDDelete"/>
                        <small><fmt:message key="genapp.form.file.delete"/></small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="adjunt1ID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#adjunt1ID').on('change', function(){
						var ruta = $('#adjunt1ID').val(); 
						var rutaArray = ruta.split('\\');
						$('#adjunt1ID-custom-file-label').css('display','block');
						$('#adjunt1ID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TiquetFields.ADJUNT2ID)}">
        <tr id="tiquet_adjunt2ID_rowid">
          <td id="tiquet_adjunt2ID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TiquetFields.ADJUNT2ID])?'tiquet.adjunt2ID':__theForm.labels[TiquetFields.ADJUNT2ID]}" />
             </label>
              <c:if test="${not empty __theForm.help[TiquetFields.ADJUNT2ID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TiquetFields.ADJUNT2ID]}" ></i>
              </c:if>
            </td>
          <td id="tiquet_adjunt2ID_columnvalueid">
              <form:errors path="tiquet.adjunt2ID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,TiquetFields.ADJUNT2ID)}" >
              <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.tiquet.adjunt2)}"/>">${__theForm.tiquet.adjunt2.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,TiquetFields.ADJUNT2ID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,TiquetFields.ADJUNT2ID)? 'true' : 'false'}" cssClass="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,TiquetFields.ADJUNT2ID)? ' uneditable-input' : ''}"   path="adjunt2ID" type="file" />
                  <label class="custom-file-label" for="adjunt2ID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.tiquet.adjunt2}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.tiquet.adjunt2)}"/>">${__theForm.tiquet.adjunt2.nom}</a>
</small>
                  </span>
                  <span class="input-group-text" id="">
                        <form:checkbox path="adjunt2IDDelete"/>
                        <small><fmt:message key="genapp.form.file.delete"/></small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="adjunt2ID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#adjunt2ID').on('change', function(){
						var ruta = $('#adjunt2ID').val(); 
						var rutaArray = ruta.split('\\');
						$('#adjunt2ID-custom-file-label').css('display','block');
						$('#adjunt2ID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
