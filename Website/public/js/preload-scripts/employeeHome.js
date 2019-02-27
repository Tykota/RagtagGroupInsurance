// Initialize select boxes
document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('select');
    var instances = M.FormSelect.init(elems, '');
});

function load(form){
    let cp = document.getElementById("contentPane");
    let getForm = document.getElementById(form);
    if(cp.firstChild != getForm){
        clearCP();
    }
    cp.appendChild(getForm);
    cp.style.display = "block";
    getForm.style.display = "block";
}

function displayClient(data){
    // Display client data with json from firestore
    console.log(data);

}

function displaySearchResults(data){
    let home = data["home-info"];
    let polnum = data["policy-number"];

    let title = "Name: " + home["name"];
    let addr = "Address: " 
            + home["address"] + " " 
            + home["city"] + ", " 
            + home["state"] + " " 
            + home["zip"];
    let poltxt = "Policy Number: " + polnum;

    // create card element
    let holder = document.createElement('div');
    holder.setAttribute("class", "col s12");
    let header = document.createElement('span');
    let headerTxt = document.createTextNode(title);
    header.appendChild(headerTxt);
    let card = document.createElement("div");
    card.setAttribute("class", "card small blue-grey darken-1");
    card.setAttribute("id", "result");
    let stack = document.createElement("div");
    stack.setAttribute("class", "card-stacked");
    let content = document.createElement("div");
    content.setAttribute("class", "card-content white-text");
    let action = document.createElement("div");
    action.setAttribute("class", "card-action");
    let p = document.createElement("p");
    let ptxt = document.createTextNode(addr);
    let p2 = document.createElement("p");
    let p2txt = document.createTextNode(poltxt);

    let viewBtn = document.createElement('btn');
    let btnTxt = document.createTextNode("View Client Details")
    viewBtn.setAttribute("class", "wave-effect waves-light btn");
    viewBtn.setAttribute("onclick", "displayClient(data)");
    viewBtn.appendChild(btnTxt);

    action.appendChild(viewBtn);
    p2.appendChild(p2txt);
    p.appendChild(ptxt);
    content.appendChild(header);
    content.appendChild(p2);
    content.appendChild(p);
    content.appendChild(action);
    stack.appendChild(content);
    card.appendChild(stack);
    //holder.appendChild(header);
    holder.appendChild(card);

    document.getElementById("contentPane").appendChild(holder);
    console.log(home);
    console.log(polnum);
}

function createResultCard(title, address){
    let div = document.createElement('div');
    div.setAttribute("class", "col s12");
    let header = document.createElement('h6');
    let divH = document.createElement('div');
    divH.setAttribute("class", "card-horizontal");
    let divS = document.createElement('div');
    divS.setAttribute('class', "card-stacked");
    let divC = document.createElement('div');
    divC.setAttribute("class", "card-content");

    let txtTitle = document.createTextNode(title);
    let txtContent = document.createTextNode(address);
    let p = document.createElement('p');
    p.appendChild(txtContent);

    divC.appendChild(p);
    divS.appendChild(divC);
    divH.appendChild(divS);
    div.appendChild(divH);

    document.getElementById("contentPane").appendChild(div);
}

function showAlert(alertText){
    let p = document.createElement('p');
    let txt = document.createTextNode(alertText);
    //p.setAttribute("class", "alert");
    //p.style.textAlign = "center";
    p.appendChild(txt);
    document.getElementById("contentPane").appendChild(p);
}

function clearAlerts(){

// Moved to firebaseFunctions.js
/*
function searchByClient(){
    let searchValue = document.getElementById("searchCriteria").value;
    let searchWith = document.getElementById("valueSelect").value;

    console.log("Search by: " + searchWith + ", for: " + searchValue);
    let searchForm = document.getElementById("searchForm");
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
*/






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

        //name
        createItem("Name : " + data.home-info.name);
        //address
        createItem("Address : " +data.home-info.address + " " + data.home-info.city + " " + data.home-info.state + " , " + data.home-info.zip);
        //phone number
        createItem("Phone : " + data.home-info.phone);
        //dl num
        createItem("DL Number : " + data.home-info.dl-number);
        //dob
        createItem("Date of birth : " + data.home-info.dob);
        //driver-type
        createItem("Liscese type : " + data.home-info.driver-type)
        //ssn
        createItem("Social Security Number : " + data.home-info.ssn)

        //list of cars
        createItem("Vehicle Information");
        for(i = 0; i < data.car-info.length; i++){
            createItem("Make : " + data.car-info.make);
            createItem("Model : " + data.car-info.model);
            createItem("VIN : " + data.car-info.vin);
        }
}

// Clears content pane for loading new content
function clearCP() {
    let cp = document.getElementById("contentPane");
    while(cp.firstElementChild){
        let current = cp.firstChild;
        current.style.display = "none";
        cp.removeChild(cp.firstElementChild);
        document.getElementById("body").appendChild(current);
    }
}