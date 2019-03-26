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
    viewBtn.addEventListener("click", displayClient.bind(null, data));
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

function createTabs(data){
    let tabsEle = document.getElementById("clientTabs");
    let clientT = document.getElementById("clientTab");
    let vehicleT = document.getElementById("vehicleTab");
    let insuranceT = document.getElementById("insuranceTab");
    var cp = document.getElementById("contentPane");
   
    // Client Tab info
    let l1 = document.createElement('p');
    l1.innerText = ("Name: " + data["home-info"].name)
    let l2 = document.createElement('p');
    l2.innerText = ("Address: " +data["home-info"].address + " " + data["home-info"].city + " " + data["home-info"].state + " , " + data["home-info"].zip);
    let l3 = document.createElement('p');
    l3.innerText = ("Phone: " + data["home-info"]["home-phone"]);
    let l4 = document.createElement('p');
    l4.innerText = ("Driver's License #: " + data["home-info"]["dl-number"]);
    let l5 = document.createElement('p');
    l5.innerText = ("Date of birth: " + data["home-info"].dob);
    let l6 = document.createElement('p');
    l6.innerText = ("License type: " + data["home-info"]["driver-type"]);
    let l7 = document.createElement('p');
    l7.innerText = ("Social Security Number: " + data["home-info"].ssn);

    // Vehicle Tab Info
    vehicleT.innerHTML = '';
    for(i = 0; i < data["car-info"].length; i++){
        let div = document.createElement('div');
        let header = document.createElement('h6');
        header.innerText = "Vehicle " + (i+1) + ": ";
        let l1 = document.createElement('p');
        l1.innerText = ("Make: " + data["car-info"][i].make);
        let l2 = document.createElement('p');
        l2.innerText = ("Model: " + data["car-info"][i].model);
        let l3 = document.createElement('p');
        l3.innerText = ("VIN: " + data["car-info"][i].vin);
        vehicleT.appendChild(header);
        vehicleT.appendChild(l1);
        vehicleT.appendChild(l2);
        vehicleT.appendChild(l3);
    }

    // Insurance Tab info
    insuranceT.innerHTML = '';
    let ins1 = document.createElement('p');
    ins1.innerText = ("Previous insurance company: " + data["insurance-info"]["prior-insurance"]);
    let ins2 = document.createElement('p');
    ins2.innerText = ("Vehicle incidents in the last five years: " + data["insurance-info"]["past-five-year-incidents"]);
    insuranceT.appendChild(ins1);
    insuranceT.appendChild(ins2);
    // Display
    clearCP();
    clientT.innerHTML = '';

    clientT.appendChild(l1);
    clientT.appendChild(l2);
    clientT.appendChild(l3);
    clientT.appendChild(l4);
    clientT.appendChild(l5);
    clientT.appendChild(l6);
    clientT.appendChild(l7);

    cp.appendChild(tabsEle);
    tabsEle.style.display = "block";
    
    item1.appendChild(clientTab);
    item2.appendChild(vehicleTab);
    item3.appendChild(insuranceTab);
    tabs.appendChild(item1);
    tabs.appendChild(item2);
    tabs.appendChild(item3);
    col.appendChild(tabs);
    divTabs.appendChild(col);
    divTabs.appendChild(clientDiv);
    divTabs.appendChild(vehicleDiv);
    divTabs.appendChild(insuranceDiv);
    
    /*contain.appendChild(clientDiv);
    contain.appendChild(vehicleDiv);
    contain.appendChild(insuranceDiv);
    divTabs.appendChild(contain);
    */
    cp.appendChild(divTabs);
}

function displayClient(data){
    createTabs(data);
    /*
    console.log(data);
        //name
        createItem("Name: " + data["home-info"].name);
        //address
        createItem("Address: " +data["home-info"].address + " " + data["home-info"].city + " " + data["home-info"].state + " , " + data["home-info"].zip);
        //phone number
        createItem("Phone: " + data["home-info"]["home-phone"]);
        //dl num
        createItem("Driver's License #: " + data["home-info"]["dl-number"]);
        //dob
        createItem("Date of birth: " + data["home-info"].dob);
        //driver-type
        createItem("License type: " + data["home-info"]["driver-type"])
        //ssn
        createItem("Social Security Number: " + data["home-info"].ssn)
        
        //list of cars
        createItem("Vehicle Information");
        for(i = 0; i < data["car-info"].length; i++){
            createItem("Make : " + data["car-info"][i].make);
            createItem("Model : " + data["car-info"][i].model);
            createItem("VIN : " + data["car-info"][i].vin);
        }
        clearCP();
        let propList = document.getElementById("propertyList");
        
        let div = document.createElement('div');
        div.setAttribute("class", "row");
        div.appendChild(propList);
        propList.style.display = "block";
        document.getElementById("contentPane").appendChild(div);
        */
}

function showApplications(){
    document.getElementById("openApps").innerHTML = '';
    document.getElementById("closedApps").innerHTML = '';
    document.getElementById("newApps").innerHTML = '';
    searchApplications("submitted");
    searchApplications("open");
    searchApplications("closed");
}

function buildApplicationCard(data, type){
    let name = "Name: " + data["name"];
    let dob = "DOB: " + data["dob"];
    let driverT = "Driver Type: " + data["drivertype"];
    let appNumber = "Application Number: " + data["applicationNum"];

     // create card element
     let holder = document.createElement('div');
     holder.setAttribute("class", "col s12");
     let header = document.createElement('span');
     let headerTxt = document.createTextNode(name);
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
     let ptxt = document.createTextNode(appNumber);
     let p2 = document.createElement("p");
     let p2txt = document.createTextNode(dob);
     let p3 = document.createElement("p");
     let p3txt = document.createTextNode(driverT);

    let viewBtn = document.createElement('btn');
    let btnTxt = document.createTextNode("View Application Details")
    viewBtn.setAttribute("class", "wave-effect waves-light btn");
    viewBtn.addEventListener("click", displayApplication.bind(null, data));
    viewBtn.appendChild(btnTxt);

    action.appendChild(viewBtn);
    p.appendChild(ptxt);
    p2.appendChild(p2txt);
    p3.appendChild(p3txt);
    
    content.appendChild(header);

    content.appendChild(p3);
    content.appendChild(p2);
    content.appendChild(p);
    content.appendChild(action);
    stack.appendChild(content);
    card.appendChild(stack);
    holder.appendChild(card);
    if(type == "open"){
        document.getElementById("openApps").appendChild(holder);
    }
    if(type == "submitted"){
        document.getElementById("newApps").appendChild(holder);
    }
    if(type == "closed"){
        document.getElementById("closedApps").appendChild(holder);
    }
}

function displayApplication(data){
    clearCP();
    let cp = document.getElementById("contentPane");
    let header = document.createElement('h5');
    header.classList.add("center-align");
    let headerTxt = document.createTextNode("Application Details: ");
    header.appendChild(headerTxt);
    let l1 = document.createElement('p');
    l1.innerText = ("Name: " + data["name"])
    let l2 = document.createElement('p');
    l2.innerText = ("Address: " + data["address"] + " " + data["city"] + " " + data["state"] + " , " + data["zip"]);
    let l3 = document.createElement('p');
    l3.innerText = ("Phone: " + data["phone"]);
    let l4 = document.createElement('p');
    l4.innerText = ("Driver's License #: " + data["dlnumber"]);
    let l5 = document.createElement('p');
    l5.innerText = ("Date of birth: " + data["dob"]);
    let l6 = document.createElement('p');
    l6.innerText = ("License type: " + data["drivertype"]);
    let l7 = document.createElement('p');
    l7.innerText = ("Social Security Number: " + data["ssn"]);
    let l8 = document.createElement('p');
    l8.innerText = ("Application Number: " + data["applicationNumber"]);
    let l9 = document.createElement('p');
    l9.innerText = ("Application Status: " + data["appStatus"]);

    cp.appendChild(header);
    cp.appendChild(l8);
    cp.appendChild(l9);
    cp.appendChild(l1);
    cp.appendChild(l2);
    cp.appendChild(l3);
    cp.appendChild(l4);
    cp.appendChild(l5);
    cp.appendChild(l6);
    cp.appendChild(l7);

    let status = data["appStatus"];
    if(status == "submitted"){
        let openBtn = document.createElement('btn');
        let btnTxt = document.createTextNode("Open Application");
        openBtn.setAttribute("id", "openApplicationBtn");
        openBtn.setAttribute("class", "center-align wave-effect waves-light btn");
        openBtn.addEventListener("click", openApplication.bind(null, data));
        openBtn.appendChild(btnTxt);
        cp.appendChild(openBtn);
    }
    else if(status == "open"){
        let acceptBtn = document.createElement('btn');
        let btnTxt = document.createTextNode("Accept New Client");
        acceptBtn.setAttribute("id", "openApplicationBtn");
        acceptBtn.setAttribute("class", "center-align wave-effect waves-light btn");
        acceptBtn.addEventListener("click", formatNewClient.bind(null, data));
        acceptBtn.appendChild(btnTxt);
        cp.appendChild(acceptBtn);

        let rejectBtn = document.createElement('btn');
        let rejectTxt = document.createTextNode("Reject Application");
        rejectBtn.setAttribute("id", "openApplicationBtn");
        rejectBtn.setAttribute("class", "center-align wave-effect waves-light btn");
        rejectBtn.addEventListener("click", rejectApplication.bind(null, data));
        rejectBtn.appendChild(rejectTxt);
        cp.appendChild(rejectBtn);
    }
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

function buildClaimCard(data, type){
    let name = "Name: " + data["name"];
    let polnum = "Policy Number: " + data["policyNumber"];
    let claimNum = "Claim Number: " + data["claimNumber"];
    /*
    if(typeof data["location"] == "object"){
        var location = "Location: " + 
        data["location"]["latitude"] + '\xB0' + " Latitude " +
        data["location"]["longitude"] + '\xB0' + " Longitude";
    }
    else {
        var location = "Location: " + data["location"];
    }
    */
    //let location = "Location: " + data["location"];
    

    let date = "Date and Time: " + toDateTime(data["date"]["seconds"]);

    let holder = document.createElement('div');
    holder.setAttribute("class", "col s12");
    let header = document.createElement('span');
    let headerTxt = document.createTextNode(claimNum);
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
    let ptxt = document.createTextNode(name);
    let p2 = document.createElement("p");
    let p2txt = document.createTextNode(polnum);
    let p3 = document.createElement("p");
    let p3txt = document.createTextNode(date);

    let viewBtn = document.createElement('btn');
    let btnTxt = document.createTextNode("View Claim Details")
    viewBtn.setAttribute("class", "wave-effect waves-light btn");
    viewBtn.addEventListener("click", displayClaim.bind(null, data));
    viewBtn.appendChild(btnTxt);

    action.appendChild(viewBtn);
    p.appendChild(ptxt);
    p2.appendChild(p2txt);
    p3.appendChild(p3txt);
    
    content.appendChild(header);
    content.appendChild(p3);
    content.appendChild(p2);
    content.appendChild(p);
    content.appendChild(action);
    stack.appendChild(content);
    card.appendChild(stack);
    holder.appendChild(card);

    document.getElementById("claimsList").appendChild(holder);
}

function displayClaim(data){
    console.log("Displaying claim: ");
    console.log(data);
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

//Check claims for the user
function checkClaims(){
    
}
function toDateTime(secs) {
    var t = new Date(1970, 0, 1); // Epoch
    t.setSeconds(secs);
    return t;
}