
<script type="text/javascript">

	var selectElement = document.querySelector('[name="tramitIServ.nom"]');
	var codi = document.querySelector('[name="tramitIServ.codi"]');
    codi.readOnly = "readOnly";

	selectElement.addEventListener('change', function() {
		codi.readOnly = "";
		var selectedValue = selectElement.value;
		codi.value = selectedValue;
		codi.readOnly = "readOnly";
	});

</script>