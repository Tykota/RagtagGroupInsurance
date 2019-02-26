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
    let searchValue = document.getElementById("searchCriteria").value;
    let searchWith = document.getElementById("valueSelect").value;

    console.log("Search by: " + searchWith + ", for: " + searchValue);
    let searchForm = document.getElementById("searchForm");
    //let admin = require("firebase-admin");
    //DB reference
    //let db = firebase.firestore();
    //collection reference
    let ref = db.collection("clients").get().then((snapshot) => {
        snapshot.docs.forEach(doc => {
            console.log(doc.data());
        })
        //console.log(snapshot.docs);
    });

    //let query = ref.where("name", "array-contains", "lol");

    //console.log(query);

/*

    // Attach an asynchronous callback to read the data at our posts reference
    ref.get().then(function(doc) {
        if (doc.exists) {
        console.log("Document data:", doc.data());
        } else {
            // doc.data() will be undefined in this case
            console.log("No such document!");
        }
    }).catch(function(error) {
        console.log("Error getting document:", error);
    });
}

        //Create HTML elements here

        //go through all of the values of the returned array
        //if it contains the searchValue then create an element based on it
        //this may need to be prettied up
        for(i = 0; i < snapshot.val().length; i++) {
            if(snapshot.val()[i].contains(searchValue)) {
                let element = Document.createElement("PARAGRAPH");
                element.text = snapshot.val()[i];
                element.parent = searchForm;
            }
        }
    }, function (errorObject) {
        console.log(errorObject.code);
    })
}

*/
}