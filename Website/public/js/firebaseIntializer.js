// This script will connect to the firebaseDB if included in an html header
// Use this script on any page that requires login/logout feature
// We can add more firebase functions here 

var config = {
    apiKey: "AIzaSyCMhdnqEpzfxFp9f8EyU0Seac69fYkmb7c",
    authDomain: "wizard-insurance.firebaseapp.com",
    databaseURL: "https://wizard-insurance.firebaseio.com",
    projectId: "wizard-insurance",
    storageBucket: "wizard-insurance.appspot.com",
    messagingSenderId: "348481372939"
};
firebase.initializeApp(config);


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
