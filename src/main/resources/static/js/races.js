"use strict";

const BASE_URL = "http://localhost:8080";

const create_name = document.querySelector("#create_name");
const create_type = document.querySelector("#create_type");

const update_id = document.querySelector("#update_id");
const update_name = document.querySelector("#update_name");
const update_type = document.querySelector("#update_type");

const delete_id = document.querySelector("#delete_id");

const read_id = document.querySelector("#read_id");

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

}

const deleteRace = () => {

}

const readOneRace = () => {

}

const readAllRaces = () => {

}

function jsonToHtml(entry){
    let name = document.createTextNode("h4");
    name.appendChild(entry.name);
    let id = document.createTextNode("p");
    id.appendChild(entry.id);
    name.appendChild(id);
    let type = document.createTextNode("p");
    type.appendChild(entry.type);
    name.appendChild(type);
    let riders = document.createTextNode("p");
    riders.appendChild(entry.riders);
    name.appendChild(riders);
    return name;
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