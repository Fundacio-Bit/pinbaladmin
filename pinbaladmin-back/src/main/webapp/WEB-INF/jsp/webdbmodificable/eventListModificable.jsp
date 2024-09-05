

<c:if test="${eventsEnviats == 'true'}">
	<style>
table td:nth-child(-n+3) {
	width: 0px;
}

table td:nth-child(4) {
	width: 25rem;
}

#FilterDiv .form-inline {
    display: flex;
    flex-flow: column;
    width: 50rem;
    margin: auto;
    align-items: start;
}

#FilterDiv .form-inline .input-group {
  align-items: center;
}

.input-group-text {
    padding: 10px;
}

span.add-on {
    margin: 10px 5px;
}

input#event\.caidNumeroSeguiment {
    width: 33.5rem;
}
</style>

<script type="text/javascript">
document.getElementById("event.caidNumeroSeguiment").classList.add("form-control")
</script>
</c:if>