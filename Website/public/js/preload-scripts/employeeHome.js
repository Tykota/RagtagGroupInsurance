// Script for handling database/api calls
// Initialize select boxes
document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('select');
    var instances = M.FormSelect.init(elems, '');
    //var instance = M.Tabs.init(document.querySelector('tabs'));
    
});

$(document).ready(function(){
    $('ul.tabs').tabs();
    $('#clientLink').click(function(){
        console.log("client tab");
        $('ul.tabs').tabs("select", "clientDiv")
    });
    $('#vehicleDiv').click(function(){
        $('ul.tabs').tabs("select", "vehicleDiv")
    });
    $('#insuranceDiv').click(function(){
        $('ul.tabs').tabs("select", "insuranceDiv")
    });
    $('.modal').modal();
  });
 
function load(form){
    let cp = document.getElementById("contentPane");
    let getForm = document.getElementById(form);
    if(cp.firstChild != getForm){
        clearCP();
    }
    if(getForm.id == "reviewForm"){
        showApplications();
    }
    if(getForm.id == "claimForm"){
        displayClaimsList();
    }
    cp.appendChild(getForm);
    cp.style.display = "block";
    getForm.style.display = "block";
}

function clearAlerts(){
}

function createItem(content){
    let item = document.createElement("LI");
    item.className = "collection-item";

    let theContent = document.createElement("H5");
    theContent.innerText = content;

    //This is the element that the items are created under
    //If this needs to be changed later on this is where we change it
    let listOfProperties = document.getElementById("propertyList");

    listOfProperties.appendChild(item);
    item.appendChild(theContent);
}

function displayClient(data){
    createTabs(data);
}

function showApplications(){
    document.getElementById("openApps").innerHTML = '';
    document.getElementById("closedApps").innerHTML = '';
    document.getElementById("newApps").innerHTML = '';
    searchApplications("submitted");
    searchApplications("open");
    searchApplications("closed");
}



function openApplication(data) {
    //var appRef = db.collection("clients").where("applicationNumber", "==", data["applicationNumber"]).doc();
    updateApplication(data.applicationNumber, "open");
}

function rejectApplication(data){
    updateApplication(data.applicationNumber, "closed");
}

function formatNewClient(data){
    console.log("Format new client data here")

}

function displayClaimsList(){
    document.getElementById("claimsList").innerHTML = '';
    searchClaims("submitted");
}



// Clears content pane for loading new content


//Check claims for the client
function checkClaims(){
    //collection called claims with policy number as key

    
}

function toggleMapView(){
    var tabEle = document.getElementById("streetViewTab");
    var mapEle = document.getElementById("map-canvas");
    var panoEle = document.getElementById("pano");
    if(mapEle.style.display == "block"){
        mapEle.style.display = "none";

        panoEle.style.display = "block";
        //panoEle.style.visibility = "visible";
        
        tabEle.appendChild(panoEle);
    }
    else {
        panoEle.style.display = "none";
        mapEle.style.display = "block";
        tabEle.appendChild(mapEle);
    }
    
}

function toDateTime(secs) {
    var t = new Date(1970, 0, 1);
    t.setSeconds(secs);
    return t;
}

function initMap(){
    //console.log("google maps initialized");
}

function initializeMap(location){
    var lat = location["latitude"];
    var lng = location["longitude"];
    var loca = { lat, lng }
    var map = new google.maps.Map(document.getElementById('map-canvas'), {
        center: loca,
        zoom: 18
    });
    var panorama = new google.maps.StreetViewPanorama(
        document.getElementById('pano'), {
            position: loca,
            pov: {
            heading: 34,
            pitch: 10
            },
            visible: true
        });
    map.setStreetView(panorama);
}