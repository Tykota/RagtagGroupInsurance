// Script for dynamically building  html components

function displaySearchResults(data){
    //Change polnum
    let polnum = data.newAppNum;
    let title = "Name: " + data.name;
    let addr = "Address: " 
            + data.address + " " 
            + data.city + ", " 
            + data.state + " " 
            + data.zip;
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

function createTabs(data){
    let tabsEle = document.getElementById("clientTabs");
    let clientT = document.getElementById("clientTab");
    let vehicleT = document.getElementById("vehicleTab");
    let insuranceT = document.getElementById("insuranceTab");
    var cp = document.getElementById("contentPane");
   
    // Client Tab info
    let l1 = document.createElement('p');
    l1.innerText = ("Name: " + data.name)
    let l2 = document.createElement('p');
    l2.innerText = ("Address: " 
                    + data.address + " " 
                    + data.city + " " 
                    + data.state 
                    + " , " + data.zip);
    let l3 = document.createElement('p');
    l3.innerText = ("Phone: " + data.phone);
    let l4 = document.createElement('p');
    l4.innerText = ("Driver's License #: " + data.dlnumber);
    let l5 = document.createElement('p');
    l5.innerText = ("Date of birth: " + data.dob);
    let l6 = document.createElement('p');
    l6.innerText = ("License type: " + data.drivertype);
    let l7 = document.createElement('p');
    l7.innerText = ("Social Security Number: " + data.ssn);

    // Vehicle Tab Info
    vehicleT.innerHTML = '';
    if(data.vehicles == undefined){
        let div = document.createElement('div');
        let header = document.createElement('h6');
        header.innerText = "No vehicle information provided.";
        vehicleT.appendChild(header);
    }
    else {
        for(i = 0; i < data["vehicles"].length; i++){
            let div = document.createElement('div');
            let header = document.createElement('h6');
            header.innerText = "Vehicle " + (i+1) + ": ";
            let l1 = document.createElement('p');
            l1.innerText = ("Make: " + data["vehicles"][i].make);
            let l2 = document.createElement('p');
            l2.innerText = ("Model: " + data["vehicles"][i].model);
            let l3 = document.createElement('p');
            l3.innerText = ("Year: " + data["vehicles"][i].year);
            let l4 = document.createElement('p');
            l4.innerText = ("VIN: " + data["vehicles"][i].vin);
            vehicleT.appendChild(header);
            vehicleT.appendChild(l1);
            vehicleT.appendChild(l2);
            vehicleT.appendChild(l3);
            vehicleT.appendChild(l4);
        }
    }

    // Insurance Tab info
    insuranceT.innerHTML = '';
    let ins1 = document.createElement('p');
    ins1.innerText = ("Previous insurance company: " + data.prevInssurance);
    let ins2 = document.createElement('p');
    ins2.innerText = ("Vehicle incidents in the last five years: " + data.prevAccident);
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
    
    /*
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
    
    /*
    contain.appendChild(clientDiv);
    contain.appendChild(vehicleDiv);
    contain.appendChild(insuranceDiv);
    divTabs.appendChild(contain);
    */
    //cp.appendChild(divTabs);
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
    console.log("displaying application")
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
    l8.innerText = ("Application Number: " + data["applicationNum"]);
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
function buildClaimCard(data, type){
    let name = "Name: " + data["name"];
    let polnum = "Policy Number: " + data["policyNum"];
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
    

    let date = "Date and Time: " + data.date;

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
    let tabEle = document.getElementById("claimTabs");
    let claimT = document.getElementById("claimInfoTab");
    let streetT = document.getElementById("streetViewTab");
    let photoT = document.getElementById("photosTab");
    var cp = document.getElementById("contentPane");

    // Claim info tab
    let l1 = document.createElement('p');
    l1.innerText = ("Claim Number: " + data["claimNumber"])
    let l2 = document.createElement('p');
    l2.innerText = ("Claim Status: " + data["claimStatus"]);
    let l3 = document.createElement('p');
    l3.innerText = ("Name: " + data["name"]);
    let l4 = document.createElement('p');
    l4.innerText = ("Date Submitted: " + data.date);
    let l5 = document.createElement('p');
    if(typeof data["location"] == "object"){
        var location = "Location: " + 
        data["location"]["latitude"] + '\xB0' + " Latitude " +
        data["location"]["longitude"] + '\xB0' + " Longitude";
    }
    else {
        var location = "Location: " + data["location"];
    }
    l5.innerText = (location);
    let l6 = document.createElement('p');
    l6.innerText = ("Description: " + data["description"]);
    let l7 = document.createElement('p');
    l7.innerText = ("Policy Number: " + data["policyNum"]);

    // Street View
    if(typeof data["location"] == "object"){
        var local = {lat: data["location"]["latitude"], lng: data["location"]["longitude"]};
    }
    else {
        var str = data.location;
        str1 = str.split(" and ")[0];
        str2 = str.split(" and ")[1];
        let coordX = Number(str1);
        let coordY = Number(str2);
        var local = {lat: coordX, lng: coordY};
        console.log(str1 + " and " + str2);
    }
    claimT.innerHTML = '';
    claimT.appendChild(l1);
    claimT.appendChild(l2);
    claimT.appendChild(l3);
    claimT.appendChild(l4);
    claimT.appendChild(l5);
    claimT.appendChild(l6);
    claimT.appendChild(l7);
    
    // Display info
    clearCP();
    console.log(local);
    initializeMap(local);
    let mapEle = document.getElementById("map-canvas");
    let panoEle = document.getElementById("pano");
    streetT.appendChild(mapEle);
    streetT.appendChild(panoEle);
    //streetT.appendChild(panoEle);
    cp.appendChild(tabEle);
    tabEle.style.display = "block";
    mapEle.style.display = "block";
    //panoEle.style.display = "block";
    //panoEle.style.visibility = "hidden";
    //google.maps.event.addDomListener(window, "load", initializeMap);
    
    //panoEle.style.display = "block";
    //onsole.log("Displaying claim: ");
    //console.log(data);   
}

function toggleMapView(){
    var tabEle = document.getElementById("streetViewTab");
    var mapEle = document.getElementById("map-canvas");
    var panoEle = document.getElementById("pano");
    if(mapEle.style.display == "block"){
        mapEle.style.display = "none";
        panoEle.style.display = "block";
        tabEle.appendChild(panoEle);
    }
    else {
        panoEle.style.display = "none";
        mapEle.style.display = "block";
        tabEle.appendChild(mapEle);
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
    document.getElementById("map-canvas").style.display = "none";
    document.getElementById("pano").style.display = "none";
}
function showAlert(alertText){
    let p = document.createElement('p');
    let txt = document.createTextNode(alertText);
    p.setAttribute("class", "alert");
    p.style.textAlign = "center";
    p.appendChild(txt);
    document.getElementById("contentPane").appendChild(p);
}