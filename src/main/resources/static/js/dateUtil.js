function today() {
	var date = new Date();
    return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
}

function getDateAfter(day) {
	var date = new Date();
	date.setTime(date.getTime()+day*24*3600*1000);
    return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
}

function getDateBefore(day) {
	var date = new Date();
	date.setTime(date.getTime()-day*24*3600*1000);
    return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
}