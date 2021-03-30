<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TiquetFields" className="org.fundaciobit.pinbaladmin.model.fields.TiquetFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,TiquetFields.TITOL)}">
        <tr id="tiquet_titol_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[TiquetFields.TITOL])?'tiquet.titol':__theForm.labels[TiquetFields.TITOL]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[TiquetFields.TITOL]}">
              <i class="icon-info-sign" title="${__theForm.help[TiquetFields.TITOL]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="tiquet.titol" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TiquetFields.TITOL)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,TiquetFields.TITOL)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="256" path="tiquet.titol"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TiquetFields.DESCRIPCIO)}">
        <tr id="tiquet_descripcio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[TiquetFields.DESCRIPCIO])?'tiquet.descripcio':__theForm.labels[TiquetFields.DESCRIPCIO]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[TiquetFields.DESCRIPCIO]}">
              <i class="icon-info-sign" title="${__theForm.help[TiquetFields.DESCRIPCIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="tiquet.descripcio" cssClass="errorField alert alert-error" />
              <form:textarea cssClass="input-xxlarge ${gen:contains(__theForm.readOnlyFields ,TiquetFields.DESCRIPCIO)? 'mceEditorReadOnly':'mceEditor'}" path="tiquet.descripcio"  />
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TiquetFields.INFORMADOR)}">
        <tr id="tiquet_informador_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[TiquetFields.INFORMADOR])?'tiquet.informador':__theForm.labels[TiquetFields.INFORMADOR]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[TiquetFields.INFORMADOR]}">
              <i class="icon-info-sign" title="${__theForm.help[TiquetFields.INFORMADOR]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="tiquet.informador" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TiquetFields.INFORMADOR)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,TiquetFields.INFORMADOR)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="100" path="tiquet.informador"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TiquetFields.DATAALTA)}">
        <tr id="tiquet_dataAlta_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[TiquetFields.DATAALTA])?'tiquet.dataAlta':__theForm.labels[TiquetFields.DATAALTA]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[TiquetFields.DATAALTA]}">
              <i class="icon-info-sign" title="${__theForm.help[TiquetFields.DATAALTA]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="tiquet.dataAlta" cssClass="errorField alert alert-error" />
              <div id="dataAlta" class="input-append">
                <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TiquetFields.DATAALTA)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,TiquetFields.DATAALTA)? 'input-medium uneditable-input' : 'input-medium'}"  path="tiquet.dataAlta" />
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,TiquetFields.DATAALTA)}" >
                <span class="add-on">
                  <i data-time-icon="icon-time" data-date-icon="icon-calendar">
                  </i>
                </span>
              </c:if>
              </div>
              <script type="text/javascript">                
                $(function() {
                  $('#dataAlta').datetimepicker({
                    language: '${lang}',
                    pick12HourFormat: <c:out value="${fn:contains(gen:getDateTimePattern(), 'a')?'true' : 'false'}"/>,
                    format:  '${gen:getJSDateTimePattern()}',
                    pickTime: true,
                    weekStart: ${gen:getFirstDayOfTheWeek()}
                  });
                });
              </script>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TiquetFields.ESTATTIQUETID)}">
        <tr id="tiquet_estatTiquetID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[TiquetFields.ESTATTIQUETID])?'tiquet.estatTiquetID':__theForm.labels[TiquetFields.ESTATTIQUETID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[TiquetFields.ESTATTIQUETID]}">
              <i class="icon-info-sign" title="${__theForm.help[TiquetFields.ESTATTIQUETID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="tiquet.estatTiquetID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,TiquetFields.ESTATTIQUETID)}" >
          <form:hidden path="tiquet.estatTiquetID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.tiquet.estatTiquetID,__theForm.listOfEstatTiquetForEstatTiquetID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,TiquetFields.ESTATTIQUETID)}" >
          <form:select id="tiquet_estatTiquetID"  onchange="if(typeof onChangeEstatTiquetID == 'function') {  onChangeEstatTiquetID(this); };"  cssClass="input-xxlarge" path="tiquet.estatTiquetID">
            <c:forEach items="${__theForm.listOfEstatTiquetForEstatTiquetID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TiquetFields.TIPUSTIQUETID)}">
        <tr id="tiquet_tipusTiquetID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[TiquetFields.TIPUSTIQUETID])?'tiquet.tipusTiquetID':__theForm.labels[TiquetFields.TIPUSTIQUETID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[TiquetFields.TIPUSTIQUETID]}">
              <i class="icon-info-sign" title="${__theForm.help[TiquetFields.TIPUSTIQUETID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="tiquet.tipusTiquetID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,TiquetFields.TIPUSTIQUETID)}" >
          <form:hidden path="tiquet.tipusTiquetID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.tiquet.tipusTiquetID,__theForm.listOfTipusTiquetForTipusTiquetID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,TiquetFields.TIPUSTIQUETID)}" >
          <form:select id="tiquet_tipusTiquetID"  onchange="if(typeof onChangeTipusTiquetID == 'function') {  onChangeTipusTiquetID(this); };"  cssClass="input-xxlarge" path="tiquet.tipusTiquetID">
            <c:forEach items="${__theForm.listOfTipusTiquetForTipusTiquetID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TiquetFields.VERSIOPINBAL)}">
        <tr id="tiquet_versioPinbal_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[TiquetFields.VERSIOPINBAL])?'tiquet.versioPinbal':__theForm.labels[TiquetFields.VERSIOPINBAL]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[TiquetFields.VERSIOPINBAL]}">
              <i class="icon-info-sign" title="${__theForm.help[TiquetFields.VERSIOPINBAL]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="tiquet.versioPinbal" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TiquetFields.VERSIOPINBAL)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,TiquetFields.VERSIOPINBAL)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="100" path="tiquet.versioPinbal"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TiquetFields.DATAINICI)}">
        <tr id="tiquet_dataInici_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[TiquetFields.DATAINICI])?'tiquet.dataInici':__theForm.labels[TiquetFields.DATAINICI]}" />
              <c:if test="${not empty __theForm.help[TiquetFields.DATAINICI]}">
              <i class="icon-info-sign" title="${__theForm.help[TiquetFields.DATAINICI]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="tiquet.dataInici" cssClass="errorField alert alert-error" />
              <div id="dataInici" class="input-append">
                <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TiquetFields.DATAINICI)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,TiquetFields.DATAINICI)? 'input-medium uneditable-input' : 'input-medium'}"  path="tiquet.dataInici" />
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,TiquetFields.DATAINICI)}" >
                <span class="add-on">
                  <i data-time-icon="icon-time" data-date-icon="icon-calendar">
                  </i>
                </span>
              </c:if>
              </div>
              <script type="text/javascript">                
                $(function() {
                  $('#dataInici').datetimepicker({
                    language: '${lang}',
                    pick12HourFormat: <c:out value="${fn:contains(gen:getDateTimePattern(), 'a')?'true' : 'false'}"/>,
                    format:  '${gen:getJSDateTimePattern()}',
                    pickTime: true,
                    weekStart: ${gen:getFirstDayOfTheWeek()}
                  });
                });
              </script>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TiquetFields.DATAINCIDENCIA)}">
        <tr id="tiquet_dataIncidencia_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[TiquetFields.DATAINCIDENCIA])?'tiquet.dataIncidencia':__theForm.labels[TiquetFields.DATAINCIDENCIA]}" />
              <c:if test="${not empty __theForm.help[TiquetFields.DATAINCIDENCIA]}">
              <i class="icon-info-sign" title="${__theForm.help[TiquetFields.DATAINCIDENCIA]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="tiquet.dataIncidencia" cssClass="errorField alert alert-error" />
              <div id="dataIncidencia" class="input-append">
                <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TiquetFields.DATAINCIDENCIA)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,TiquetFields.DATAINCIDENCIA)? 'input-medium uneditable-input' : 'input-medium'}"  path="tiquet.dataIncidencia" />
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,TiquetFields.DATAINCIDENCIA)}" >
                <span class="add-on">
                  <i data-time-icon="icon-time" data-date-icon="icon-calendar">
                  </i>
                </span>
              </c:if>
              </div>
              <script type="text/javascript">                
                $(function() {
                  $('#dataIncidencia').datetimepicker({
                    language: '${lang}',
                    pick12HourFormat: <c:out value="${fn:contains(gen:getDateTimePattern(), 'a')?'true' : 'false'}"/>,
                    format:  '${gen:getJSDateTimePattern()}',
                    pickTime: true,
                    weekStart: ${gen:getFirstDayOfTheWeek()}
                  });
                });
              </script>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TiquetFields.SOLUCIONATPER)}">
        <tr id="tiquet_solucionatPer_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[TiquetFields.SOLUCIONATPER])?'tiquet.solucionatPer':__theForm.labels[TiquetFields.SOLUCIONATPER]}" />
              <c:if test="${not empty __theForm.help[TiquetFields.SOLUCIONATPER]}">
              <i class="icon-info-sign" title="${__theForm.help[TiquetFields.SOLUCIONATPER]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="tiquet.solucionatPer" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TiquetFields.SOLUCIONATPER)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,TiquetFields.SOLUCIONATPER)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="100" path="tiquet.solucionatPer"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TiquetFields.DATAFI)}">
        <tr id="tiquet_datafi_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[TiquetFields.DATAFI])?'tiquet.datafi':__theForm.labels[TiquetFields.DATAFI]}" />
              <c:if test="${not empty __theForm.help[TiquetFields.DATAFI]}">
              <i class="icon-info-sign" title="${__theForm.help[TiquetFields.DATAFI]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="tiquet.datafi" cssClass="errorField alert alert-error" />
              <div id="datafi" class="input-append">
                <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TiquetFields.DATAFI)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,TiquetFields.DATAFI)? 'input-medium uneditable-input' : 'input-medium'}"  path="tiquet.datafi" />
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,TiquetFields.DATAFI)}" >
                <span class="add-on">
                  <i data-time-icon="icon-time" data-date-icon="icon-calendar">
                  </i>
                </span>
              </c:if>
              </div>
              <script type="text/javascript">                
                $(function() {
                  $('#datafi').datetimepicker({
                    language: '${lang}',
                    pick12HourFormat: <c:out value="${fn:contains(gen:getDateTimePattern(), 'a')?'true' : 'false'}"/>,
                    format:  '${gen:getJSDateTimePattern()}',
                    pickTime: true,
                    weekStart: ${gen:getFirstDayOfTheWeek()}
                  });
                });
              </script>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TiquetFields.NOTES)}">
        <tr id="tiquet_notes_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[TiquetFields.NOTES])?'tiquet.notes':__theForm.labels[TiquetFields.NOTES]}" />
              <c:if test="${not empty __theForm.help[TiquetFields.NOTES]}">
              <i class="icon-info-sign" title="${__theForm.help[TiquetFields.NOTES]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="tiquet.notes" cssClass="errorField alert alert-error" />
              <form:textarea cssClass="input-xxlarge ${gen:contains(__theForm.readOnlyFields ,TiquetFields.NOTES)? 'mceEditorReadOnly':'mceEditor'}" path="tiquet.notes"  />
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TiquetFields.ENTORN)}">
        <tr id="tiquet_entorn_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[TiquetFields.ENTORN])?'tiquet.entorn':__theForm.labels[TiquetFields.ENTORN]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[TiquetFields.ENTORN]}">
              <i class="icon-info-sign" title="${__theForm.help[TiquetFields.ENTORN]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="tiquet.entorn" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,TiquetFields.ENTORN)}" >
          <form:hidden path="tiquet.entorn"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.tiquet.entorn,__theForm.listOfValuesForEntorn)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,TiquetFields.ENTORN)}" >
          <form:select id="tiquet_entorn"  onchange="if(typeof onChangeEntorn == 'function') {  onChangeEntorn(this); };"  cssClass="input-xxlarge" path="tiquet.entorn">
            <c:forEach items="${__theForm.listOfValuesForEntorn}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TiquetFields.ADJUNT1ID)}">
        <tr id="tiquet_adjunt1ID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[TiquetFields.ADJUNT1ID])?'tiquet.adjunt1ID':__theForm.labels[TiquetFields.ADJUNT1ID]}" />
              <c:if test="${not empty __theForm.help[TiquetFields.ADJUNT1ID]}">
              <i class="icon-info-sign" title="${__theForm.help[TiquetFields.ADJUNT1ID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="tiquet.adjunt1ID" cssClass="errorField alert alert-error" />
              <div class="fileupload fileupload-new" data-provides="fileupload" style="margin-bottom: 0px">
                <div class="input-append">
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,TiquetFields.ADJUNT1ID)}" >
                    <div class="uneditable-input span3">
                      <i class="icon-file fileupload-exists"></i>
                      <span class="fileupload-preview"></span>
                    </div>
                    <span class="btn btn-file">
                      <span class="fileupload-new"><fmt:message key="genapp.form.file.select"/></span>
                      <span class="fileupload-exists"><fmt:message key="genapp.form.file.change"/></span>
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TiquetFields.ADJUNT1ID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,TiquetFields.ADJUNT1ID)? 'input uneditable-input' : 'input'}"  path="adjunt1ID" type="file" />
                    </span>
                    <a href="#" class="btn fileupload-exists" data-dismiss="fileupload"><fmt:message key="genapp.form.file.unselect"/></a>
                    <span class="add-on">&nbsp;</span>
                </c:if>
                <c:if test="${not empty __theForm.tiquet.adjunt1}">
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,TiquetFields.ADJUNT1ID)}" >
                    <span class="add-on">
                        <form:checkbox path="adjunt1IDDelete"/>
                        <fmt:message key="genapp.form.file.delete"/>
                    </span>
                    <span class="add-on">&nbsp;</span>   
                </c:if>
                    <span class="add-on">
                        <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.tiquet.adjunt1)}"/>">${__theForm.tiquet.adjunt1.nom}</a>
                    </span>
                </c:if>
                </div>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TiquetFields.ADJUNT2ID)}">
        <tr id="tiquet_adjunt2ID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[TiquetFields.ADJUNT2ID])?'tiquet.adjunt2ID':__theForm.labels[TiquetFields.ADJUNT2ID]}" />
              <c:if test="${not empty __theForm.help[TiquetFields.ADJUNT2ID]}">
              <i class="icon-info-sign" title="${__theForm.help[TiquetFields.ADJUNT2ID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="tiquet.adjunt2ID" cssClass="errorField alert alert-error" />
              <div class="fileupload fileupload-new" data-provides="fileupload" style="margin-bottom: 0px">
                <div class="input-append">
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,TiquetFields.ADJUNT2ID)}" >
                    <div class="uneditable-input span3">
                      <i class="icon-file fileupload-exists"></i>
                      <span class="fileupload-preview"></span>
                    </div>
                    <span class="btn btn-file">
                      <span class="fileupload-new"><fmt:message key="genapp.form.file.select"/></span>
                      <span class="fileupload-exists"><fmt:message key="genapp.form.file.change"/></span>
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TiquetFields.ADJUNT2ID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,TiquetFields.ADJUNT2ID)? 'input uneditable-input' : 'input'}"  path="adjunt2ID" type="file" />
                    </span>
                    <a href="#" class="btn fileupload-exists" data-dismiss="fileupload"><fmt:message key="genapp.form.file.unselect"/></a>
                    <span class="add-on">&nbsp;</span>
                </c:if>
                <c:if test="${not empty __theForm.tiquet.adjunt2}">
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,TiquetFields.ADJUNT2ID)}" >
                    <span class="add-on">
                        <form:checkbox path="adjunt2IDDelete"/>
                        <fmt:message key="genapp.form.file.delete"/>
                    </span>
                    <span class="add-on">&nbsp;</span>   
                </c:if>
                    <span class="add-on">
                        <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.tiquet.adjunt2)}"/>">${__theForm.tiquet.adjunt2.nom}</a>
                    </span>
                </c:if>
                </div>
              </div>
           </td>
        </tr>
        </c:if>
        
