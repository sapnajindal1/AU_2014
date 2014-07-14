var mode = 0;

function setMode(m) {
	mode = m;
	var unitslabel1 = document.getElementById("units1");
	var unitslabel2 = document.getElementById("units2");

	var lenthPill = document.getElementById("lengthpill");
	var weigthPill = document.getElementById("weightpill");
	var temperaturePill = document.getElementById("temperaturepill");

	document.getElementById("unitstxt1").value = "";
	document.getElementById("unitstxt2").value = "";
	if (mode === 0) {
		unitslabel1.innerHTML = "Distance in KM";
		unitslabel2.innerHTML = "Distance in Miles";

		lenthPill.className = "active";
		weigthPill.className = "";
		temperaturePill.className = "";
	} else if (mode === 1) {
		unitslabel1.innerHTML = "Weight in KG";
		unitslabel2.innerHTML = "Weight in Pounds";

		lenthPill.className = "";
		weigthPill.className = "active";
		temperaturePill.className = "";
	} else if (mode === 2) {
		unitslabel1.innerHTML = "Temperature in Fahrenheit";
		unitslabel2.innerHTML = "Temperature in Celcius";

		lenthPill.className = "";
		weigthPill.className = "";
		temperaturePill.className = "active";
	}
}

function convert() {
	var units1 = document.getElementById("unitstxt1");
	var units2 = document.getElementById("unitstxt2");
	if (mode === 0) {
		convertLength(units1, units2);
	} else if (mode === 1) {
		convertWeight(units1, units2);
	} else if (mode === 2) {
		convertTemperature(units1, units2);
	}
}

function convertLength(km, miles) {
	if (km.value != "") {
		miles.value = parseFloat(km.value) * 0.6214;
	} else if (miles.value != "") {
		km.value = parseFloat(miles.value) / 0.6214;
	} else {
		alert("Enter KMs or Miles");
	}
}

function convertWeight(kg, pound) {
	if (kg.value != "") {
		pound.value = parseFloat(kg.value) * 2.2046;
	} else if (pound.value != "") {
		kg.value = parseFloat(pound.value) / 2.2046;
	} else {
		alert("Enter KGs or Pounds");
	}
}

function convertTemperature(fahrenheit, celcius) {
	if (fahrenheit.value != "") {
		celcius.value = (5.0 / 9.0) * (parseFloat(fahrenheit.value) - 32);
	} else if (pound.value != "") {
		fahrenheit.value = (9.0 / 5.0) * parseFloat(celcius.value) + 32;
	} else {
		alert("Enter Fahrenheit or Celcius");
	}
}
