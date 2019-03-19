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

function search(query, criteria){
    clearCP();
    let ref = db.collection("clients").where(criteria, "==", query).get().then((snapshot) => {
        snapshot.docs.forEach(doc => {
            displaySearchResults(doc.data());
        });
    })
}