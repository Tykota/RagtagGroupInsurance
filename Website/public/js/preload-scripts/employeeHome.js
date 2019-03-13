// Initialize select boxes
document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('select');
    var instances = M.FormSelect.init(elems, '');
    var el = document.querySelectorAll('ul.tabs')
    var instance = M.Tabs.init(el, '');
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
    // Create Tab elements
    var cp = document.getElementById("contentPane");
    let contain = document.createElement('div');
    contain.setAttribute("class", "container col s12");
    let divTabs = document.createElement('div');
    divTabs.setAttribute("class", "row");
    let col = document.createElement('div');
    col.setAttribute("class", "col s12");
    let tabs = document.createElement('ul');
    tabs.setAttribute("id", "clientTab");
    tabs.setAttribute("class", "tabs blue-grey darken-1");
    
    // For each tab
    let item1 = document.createElement('li');
    item1.setAttribute("class", "tab col s4");
    let clientDiv = document.createElement('div');
    clientDiv.setAttribute("id", "clientDiv");
    clientDiv.setAttribute("class", "col s12");
    let clientTab = document.createElement('a');
    clientTab.setAttribute("class", "active white-text");
    clientTab.setAttribute("href", "#clientDiv");
    clientTab.innerText = "Home Information";

    let item2 = document.createElement('li');
    item2.setAttribute("class", "tab col s4");
    let vehicleDiv = document.createElement('div');
    vehicleDiv.setAttribute("id", "vehicleDiv");
    vehicleDiv.setAttribute("class", "col s12");
    let vehicleTab = document.createElement('a');
    vehicleTab.setAttribute("class", "white-text");
    vehicleTab.setAttribute("href", "#vehcileDiv");
    vehicleTab.innerText = "Vehicles";

    let item3 = document.createElement('li');
    item3.setAttribute("class", "tab col s4");
    var insuranceDiv = document.createElement('div');
    insuranceDiv.setAttribute("id", "insuranceDiv");
    insuranceDiv.setAttribute("class", "col s12");
    let insuranceTab = document.createElement('a');
    insuranceTab.setAttribute("class", "white-text");
    insuranceTab.setAttribute("href", "#insuranceDiv");
    insuranceTab.innerText = "Insurance Details";



   
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
    for(i = 0; i < data["car-info"].length; i++){
        let div = document.createElement('div');
        let header = document.createElement('h6');
        header.innerText = "Vehicle " + (i+1);
        let l1 = document.createElement('p');
        l1.innerText = ("Make: " + data["car-info"][i].make);
        let l2 = document.createElement('p');
        l2.innerText = ("Model: " + data["car-info"][i].model);
        let l3 = document.createElement('p');
        l3.innerText = ("VIN: " + data["car-info"][i].vin);
        div.appendChild(header);
        div.appendChild(l1);
        div.appendChild(l2);
        div.appendChild(l3);
        vehicleDiv.appendChild(div);
    }

    // Insurance Tab info

    // Display
    clearCP();
    clientDiv.appendChild(l1);
    clientDiv.appendChild(l2);
    clientDiv.appendChild(l3);
    clientDiv.appendChild(l4);
    clientDiv.appendChild(l5);
    clientDiv.appendChild(l6);
    clientDiv.appendChild(l7);
    
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
    contain.appendChild(divTabs);
    cp.appendChild(contain);

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