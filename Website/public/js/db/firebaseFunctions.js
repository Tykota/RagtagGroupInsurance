// Firebase functions here
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
            search(searchValue, "home-info.name");
        }
        else if(searchWith == "policy"){
            search(searchValue, "policy-number");
        }
    }
}

//Update a certain field in the firebase
//not finished
function updateField(fieldName, value){
    var key = db.collection("clients");
    let ref = db.collection("clients").where(criteria, "==", query).get().then((snapshot))

    var update = {};
    update['/clients/' + key] = clientData;

    return firebase.database().ref().update(update);
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

function grabApplication(appNumber, status){
    let ref = db.collection("clients").where("newAppNum", "==", appNumber).get().then((snapshot) => {
        snapshot.docs.forEach(doc => {
            console.log(doc.id);
            updateApplication(doc.id, status);
        });
    })
}

function updateApplication(id, status){
    var appRef = db.collection("clients").doc(id);
    return appRef.update({
        appStatus: status 
    })
    .then(function() {
        console.log("Document successfully updated!");
    })
    .catch(function(error) {
        // The document probably doesn't exist.
        console.error("Error updating document: ", error);
    });
}