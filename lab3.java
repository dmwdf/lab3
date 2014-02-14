//array to store values
var stores = Array();
//input field text
var inputField = document.getElementById('inputString');

var returnMsg;

//clear the storage
function clearStorage() {
    //clear the storage
    stores = Array();
    localStorage.clear("database");
    //visually cleared
    document.getElementById('write').innerHTML = "storage cleared.";
}

// save the string
function saveStatusLocally() {
    //grab the value of the text box
    var stringToSave = returnMsg;
    if ((stringToSave == null) || (stringToSave == "")) {
        document.getElementById('write').innerHTML = "nothing to store.";
    } else {
        //push that value to the array
        stores.push(stringToSave);
        //clear the input field for visual 
        inputField.value = "";
        //print that value into the local storage named database and joing by a non-breaking space
        window.localStorage.setItem("database", stores.join(" "));
        //confirm write
        document.getElementById('write').innerHTML = "data stored.";
        //clear message after 1s
        setTimeout(function() {
            document.getElementById('write').innerHTML = "";
        }, 1000);

    }
}

// read the string
function readStatus() {
    //print the value of the local storage "database" key
    if (window.localStorage.getItem("database") == null) {
        document.getElementById('write').innerHTML = "nothing stored.";
    } else {
        document.getElementById('write').innerHTML = window.localStorage.getItem("database");
    }
}


$(document).ready(function() {
 $.ajax({
       type: 'GET',
        url: 'http://api.wunderground.com/api/36b799dc821d5836/conditions/q/MO/Kansas City.json',
        async: false,
        contentType: "application/json",
        dataType: 'jsonp'
       
    }).then(function(data) {
     
     $.each(data, function(idx, obj) {
	 returnMsg = " Kansas City degree is " +obj.temp_f + " weather is " + obj.weather;
});
    });

       
               
});

$.ajax
    url: 'http://api.wunderground.com/api/36b799dc821d5836/conditions/q/PA/Horsham.json'
    dataType: 'jsonp'
    data: 'url'
    success: (data) ->
        for index, result of data
                temp = Math.round result.temp_f
                icon = result.icon_url
                weather = result.weather
                $('p.currentConditions').html "Now  #{temp} &deg; F and #{weather}"
                $('div.currentIcon').html "<img src='#{icon}' >"
