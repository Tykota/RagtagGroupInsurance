// Script for initializing DOM elements and handling api calls
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
    updateApplicationStatus(data.applicationNum, "open").then(function(){
        load('reviewForm');
        //showApplications();
    });
    //displayApplication(data);
}

function rejectApplication(data){
    updateApplicationStatus(data.applicationNum, "closed").then(function(){
        load('reviewForm');
    });
    //showApplications();
    //displayApplication(data);
}

function formatNewClient(data){
    updateApplicationStatus(data.applicationNum, "accepted");
    displayClient(data);
}

function displayClaimsList(){
    document.getElementById("claimsList").innerHTML = '';
    searchClaims("submitted");
}

//Check claims for the client
function checkClaims(){
    //collection called claims with policy number as key    
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
    var map = new google.maps.Map(document.getElementById('map-canvas'), {
        center: location,
        zoom: 18
    });
    var panorama = new google.maps.StreetViewPanorama(
        document.getElementById('pano'), {
            position: location,
            pov: {
            heading: 34,
            pitch: 10
            },
            visible: true
        });
    map.setStreetView(panorama);
}

function getClaimPhotos(imgElement){
    // add functionality for searching photos in cloud storage
    var storageRef = storage.ref();
    var picRef = storageRef.child('test.jpg');
    picRef.getDownloadURL().then(function(url){
        document.getElementById('claimPhoto').src = url;
        //imgElement.src = url;
        picURL = url;
        console.log(picURL);
        return picURL;
    }).catch(function(error){
        console.log("error getting photos " + error);
    });

}

function addMetaData(){
    var storageRef = storage.ref();
    var picRef = storageRef.child('22234153408_78d945f6b5_b-e1531854292278.jpg');
    var data = {
        metadata: {
            'policyNum': '1010101010'
        }
    }
    picRef.updateMetadata(data).then(function(metadata){
        console.log("New metadata added " + metadata);
    }).catch(function(error){
        console.log(error);
    });
}

function nameAutofill(){
    document.getElementById("searchCriteria").innerText = "George Mooney";
}

function numAutoFill(){
    document.getElementById("searchCriteria").innerText = "9803418361";
}