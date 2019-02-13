// Initialize select boxes
document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('select');
    var instances = M.FormSelect.init(elems, '');
});

function loadSearch(){
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
    console.log("search by client not available");
}