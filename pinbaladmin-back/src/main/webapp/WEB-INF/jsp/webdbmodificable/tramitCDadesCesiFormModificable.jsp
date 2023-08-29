
<script type="text/javascript">

var selectElement = document.querySelector('[name="tramitCDadesCesi.denominacio"]');
var nif = document.querySelector('[name="tramitCDadesCesi.nif"]');
nif.readOnly = "readOnly";

selectElement.addEventListener('change', function() {
	nif.readOnly = "";
	var selectedValue = selectElement.value;
    nif.value = selectedValue;
    nif.readOnly = "readOnly";
});



</script>