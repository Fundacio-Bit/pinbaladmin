  <c:if test="${empty eventItems}">
     <%@include file="eventListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty eventItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="eventListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="eventListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="eventListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="event" items="${eventItems}">

        <tr id="event_rowid_${event.eventID}">
          <%@include file="eventListCoreMultipleSelect.jsp" %>

          <%@include file="eventListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="eventListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
