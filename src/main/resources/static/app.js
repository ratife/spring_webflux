

var chart;

function initGraph(){
	
	chart = new SmoothieChart();
}
function checkTransaction(id){
	var series = new TimeSeries();
	chart.addTimeSeries(series, getColor(id));
	chart.streamTo(document.getElementById("chart"), 500);
	var connection = new EventSource("/trans/"+id); 
	connection.onmessage = function(event){
		series.append(Date.now(),JSON.parse(event.data).price);
	}
}

function getColor(value) {
    switch (value) {
        case 'BNI':
            return { strokeStyle: 'rgba(255, 0, 0, 1)', fillStyle: 'rgba(255, 0, 0, 0.2)' }; // Rouge
        case 'MVOLA':
            return { strokeStyle: 'rgba(255, 255, 0, 1)', fillStyle: 'rgba(255, 255, 0, 0.2)' }; // Jaune
        case 'BFV':
            return { strokeStyle: 'rgba(0, 255, 0, 1)', fillStyle: 'rgba(0, 255, 0, 0.2)' }; // Vert
        case 'ORANGE':
            return { strokeStyle: 'rgba(255, 165, 0, 1)', fillStyle: 'rgba(255, 165, 0, 0.2)' }; // Orange
        case 'BOA':
            return { strokeStyle: 'rgba(0, 0, 255, 1)', fillStyle: 'rgba(0, 0, 255, 0.2)' }; // Bleu
        default:
            return { strokeStyle: 'rgba(128, 128, 128, 1)', fillStyle: 'rgba(128, 128, 128, 0.2)' }; // Gris par défaut
    }
}

