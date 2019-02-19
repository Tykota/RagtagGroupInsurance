// Initialize select boxes
document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('select');
    var instances = M.FormSelect.init(elems, '');
});

function loadSearch(value){
    var cp = document.getElementById("contentPane");
    var searchForm = document.getElementById("searchForm");
    if(cp.firstChild != searchForm){
        while(cp.firstChild){
            //cp.firstChild.visibility = "hidden";
            var current = document.getElementById("body").appendChild(cp.firstChild);
            current.style.visibility = "hidden";
            cp.removeChild(cp.firstChild);
        }
    }
    
    //cp.innerHTML='';
    cp.appendChild(searchForm);
    cp.style.visibility = "visible";
    searchForm.style.visibility = "visible";
}

function loadReview(){
    var cp = document.getElementById("contentPane");
    var reviewForm = document.getElementById("reviewForm");
    if(cp.firstChild != reviewForm){
        while(cp.firstChild){
            //cp.firstChild.visibility = "hidden";
            var current = document.getElementById("body").appendChild(cp.firstChild);
            current.style.visibility = "hidden";
            cp.removeChild(cp.firstChild);
        }
    }
    
    //cp.innerHTML='';
    cp.style.visibility = "visible";
    reviewForm.style.visibility = "visible";
    cp.appendChild(reviewForm);
}

function loadClaims(){
    var cp = document.getElementById("contentPane");
    var claimForm = document.getElementById("claimForm");
    if(cp.firstChild != claimForm){
        while(cp.firstChild){
            //cp.firstChild.visibility = "hidden";
            var current = document.getElementById("body").appendChild(cp.firstChild);
            current.style.visibility = "hidden";
            cp.removeChild(cp.firstChild);
        }
    }
    
    //cp.innerHTML='';
    cp.style.visibility = "visible";
    claimForm.style.visibility = "visible";
    cp.appendChild(claimForm);
}

function searchByClient(){
    let searchValue = document.getElementById("searchCriteria").value;

    let searchForm = Document.getElementById("searchForm");
    let admin = require("firebase-admin");
    //DB reference
    let db = admin.database();
    //collection reference
    let ref = db.collection("clients");

    // Attach an asynchronous callback to read the data at our posts reference
    ref.on("value", function(snapshot) {
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