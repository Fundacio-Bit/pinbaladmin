
<script type="text/javascript">
	var caducitat = document.querySelector('[name="tramitHProc.caducitat"]');
	var dataCad = document.querySelector('[name="tramitHProc.caducitatdata"]');

	testDate(caducitat.checked);

	caducitat.addEventListener('change', function() {
		testDate(caducitat.checked);
	});


	function testDate(caduca) {
		if (caduca) {
			dataCad.readOnly = "";
			var actual = dataCad.value;
			if (dataCad.value == "") {
				dataCad.value = moment().format('DD/MM/YYYY HH:mm:ss');
			}
		} else {
			dataCad.readOnly = "readOnly";
		}
	}
</script>