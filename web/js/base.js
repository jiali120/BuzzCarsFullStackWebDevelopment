
var config = {
    rootUrl: 'http://127.0.0.1:8888/'
}


function getValueById(id){
    var ele = document.getElementById(id)
    return ele? ele.value: ''
}

//div span 
function getHtmlById(id){
    var ele = document.getElementById(id)
    return ele.innerHTML
}

function getTextById(id){
    var ele = document.getElementById(id)
    return ele.innerText
}

function setHtmlValueById(id, value){
    var ele = document.getElementById(id)
    ele.innerHTML = value
}

function setTextValueById(id, value){
    var ele = document.getElementById(id)
    ele.innerText = value
}
function setValueById(id, value){
    var ele = document.getElementById(id)
    ele.value = value
}


function flashToken() {
    localStorage.removeItem('token')
    location.reload()
}

function tokenInfoObj(token){
    const parts = token.split('.');
    const decodedPayload = JSON.parse(atob(parts[1]));
    return decodedPayload
}

function getTokenString(){
    var token = localStorage.getItem('token')
    return token;
}

function show(id){
    var ele = document.getElementById(id)
    ele.style.display='block'
}
function hide(id){
    var ele = document.getElementById(id)
    ele.style.display='none'
}