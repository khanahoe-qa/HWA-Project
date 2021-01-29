"use strict";

const BASE_URL = "http://localhost:8080";

const create_name = document.querySelector("#create_name");
const create_type = document.querySelector("#create_type");

const update_id = document.querySelector("#update_id");
const update_name = document.querySelector("#update_name");
const update_type = document.querySelector("#update_type");

const delete_id = document.querySelector("#delete_id");

const readOne_id = document.querySelector("#read_id");

const createRace = () => {
    fetch(`${BASE_URL}/race/create`,{
        method: 'POST',
        body: JSON.stringify({
            name: create_name.value,
            type: create_type.value
        }),
        headers: {
            "Content-type": "application/json"
        }
    })
    .then(response => response.json())
    .then(json => console.log(json))
    .catch(err => console.error("Failed to create entry"));
}

const updateRace = () => {
    fetch(`${BASE_URL}/race/update?id=${update_id.value}`,{
        method: 'PUT',
        body: JSON.stringify({
            id: update_id.value,
            name: update_name.value,
            type: update_type.value
        }),
        headers: {
            "Content-type": "application/json"
        }
    })
    .then(response => response.json())
    .then(json => console.log(json))
    .catch(err => console.error("Failed to update entry"));
}

const deleteRace = () => {
    fetch(`${BASE_URL}/race/delete/${delete_id.value}`,{
        method: 'DELETE'
    })
    .then(
        response => {
            if(response.status !== 204){
                console.error("Failed to delete race");
            }
            else{
                console.log(`Deleted race with id ${delete_id.value}.`);
            }
        }
    )
    .catch(err => console.error("Error deleting data"));
}

const readOneRace = () => {
    fetch(`${BASE_URL}/race/read/${readOne_id.value}`,{
        method: 'GET'
    })
    .then(
        response => {
            if(response.status !== 200){
                console.error("Error reading race")
            }
            else{
                response.json().then(json => {
                    console.log(json);
                    output.appendChild(jsonToHtml(json));
                }
                )
            }
        }
    )
    .catch(err =>{console.error("Error fetching data")});
}

const readAllRaces = () => {
    fetch(`${BASE_URL}/race/readAll`, {
        method: 'GET'
    })
    .then(
        response => {
            if(response.status !== 200){
                console.error("Error reading races")
            }
            else{
                response.json().then(json => {
                    console.log(json);
                    for(let i=0; i<json.length; i++){
                        let entry = jsonToHtml(json[i]);
                        output.appendChild(entry);
                    }
                }
                )
            }
        }
    )
    .catch(err =>{console.error("Error fetching data")});
}

function jsonToHtml(entry){
    let div = document.createElement("div");
    div.appendChild(document.createElement("hr"));
    let h = document.createElement("h4");
    let name = document.createTextNode(entry.name);
    h.appendChild(name);

    let p = document.createElement("p");

    let id = document.createTextNode("Id: "+entry.id);
    p.appendChild(id);

    let type = document.createTextNode(", Type: "+entry.type);
    p.appendChild(type);

    p.appendChild(document.createTextNode(", Riders: "));

    for(let i=0; i<entry.riders.length; ++i){
        let riderName = null;
        if(i === 0){
            riderName = document.createTextNode(entry.riders[i].name);
        }
        else{
            riderName = document.createTextNode(", "+entry.riders[i].name);
        }
        p.appendChild(riderName);
    }

    h.appendChild(p);

    div.appendChild(h);

    return div;
}

const createButton = document.querySelector("#create_button");
createButton.addEventListener("click", createRace);

const updateButton = document.querySelector("#update_button");
updateButton.addEventListener("click", updateRace)

const deleteButton = document.querySelector("#delete_button");
deleteButton.addEventListener("click", deleteRace)

const readOneButton = document.querySelector("#read_one_button");
readOneButton.addEventListener("click", readOneRace);

const readAllButton = document.querySelector("#read_all_button");
readAllButton.addEventListener("click", readAllRaces);
