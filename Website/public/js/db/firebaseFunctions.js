// Firebase functions here
var mapLocations = [];
function login(){
    let username = document.getElementById("email").value;
    let password = document.getElementById("pass").value;
    //Query database here to see if the username and password match anyone with Admin privledges
    firebase.auth().signInWithEmailAndPassword(username, password).then(function(result){
        //var token = result.credential.accessToken;
        var user = result.user;
        console.log("signing in... ")
        window.location = '/employee_home.html';
        }).catch(function(error) {
        var err = error.code;
        var msg = error.message;
        console.log("Error Code: " + err + " " + msg);
    });
}

function logout(){
    firebase.auth().signOut();
    window.location = '/index.html';
}

// Add data function

// Read data functions
function searchByClient(){
    clearAlerts();
    let searchValue = document.getElementById("searchCriteria").value;
    let searchWith = document.getElementById("valueSelect").value;

    if(searchValue == ''){
        showAlert('Enter a query in the text box to search.');
    }
    else if(searchWith == "choose"){
        showAlert('Choose a search criteria from the dropdown box.')
    }
    else {
        if(searchWith == "name"){
            search(searchValue, "name");
        }
        else if(searchWith == "policy"){
            search(searchValue, "newAppNum");
        }
    }
}

//Update a certain field in the firebase
//not finished
function updateField(collection, docKey, keyField, fieldName, value){
    var doc, docID;
    if(collection == "clients"){
        doc = db.collection(collection).doc(docKey);
        if(fieldName == "address"){
            doc.update({
                address: value[0],
                city: value[1],
                state: value[2],
                zip: value[3]
            }).catch(function(err){
                console.log("Error updating: " + err);
            })
        }
        else {
            doc.update({
                [fieldName]: value
            }).catch(function(err){
                console.log("Error updating: " + err);
            })
        }
        
    }
    else{
        var docRef = db.collection(collection).where(keyField, "==", docKey).get().then((snapshot)=> {
            snapshot.docs.forEach(doc => {
                docID = doc.id;
                doc = db.collection(collection).doc(docID);
                doc.update({
                    [fieldName]: value
                }).catch(function(err){
                    console.log("Error updating: " + err);
                })
            })
        });
    }

    /*
    var update = {};
    update['/' + collection + '/' + key] = newData;

    return firebase.database().ref().update(update);
    */
}

function search(query, criteria){
    clearCP();
    let ref = db.collection("clients").where(criteria, "==", query).get().then((snapshot) => {
        snapshot.docs.forEach(doc => {
            displaySearchResults(doc.data());
        });
    })
}

function searchApplications(type){
    clearCP();
    let ref = db.collection("clients").where("appStatus", "==", type).get().then((snapshot) => {
        snapshot.docs.forEach(doc => {
            buildApplicationCard(doc.data(), type);
        })
    })
}

function searchClaims(type){
    clearCP();
    let ref = db.collection("claims").where("claimStatus", "==", type).get().then((snapshot) => {
        snapshot.docs.forEach(doc => {
            buildClaimCard(doc.data(), type);
        })
    })
}

function getClaimLocations(){
    let locations= [];
    let ref = db.collection("claims").get().then((snapshot) => {
        snapshot.docs.forEach(doc => {

            let name = doc.data().name;
            let claimNum = doc.data().claimNumber;
            let description = doc.data().description;

            let info = '<strong> Claim Number: ' + claimNum + '</strong><br>\r\
                        Name: ' + name + '<br> Description: ' + description;
            let location = doc.data().location;
            let lat = location.split(" and ")[0];
            let lng = location.split(" and ")[1];
            let holder = [info, lat, lng];
            addToMap(holder);
            locations.push(holder);
        })
    });
    return locations;
}
function addToMap(loca){
    mapLocations.push(loca);
}

function getLocations(){
    return mapLocations;
}
    //console.log(ref);
    //return ref;


/*
function grabApplication(appNumber, status){
    let ref = db.collection("clients").where("newAppNum", "==", appNumber).get().then((snapshot) => {
        snapshot.docs.forEach(doc => {
            console.log(doc.id);
            updateApplication(doc.id, status);
        });
    })
}
*/

function updateApplicationStatus(id, status){
    var appRef = db.collection("clients").doc(id);
    return appRef.update({
        appStatus: status 
    })
    .then(function() {
        //console.log("Document successfully updated!");
    })
    .catch(function(error) {
        // The document probably doesn't exist.
        console.error("Error updating document: ", error);
    });
}