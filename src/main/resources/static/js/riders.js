"use strict";

const BASE_URL = "http://localhost:8080";

const output = document.querySelector("#output");

const create_name = document.querySelector("#create_name");
const create_dob = document.querySelector("#create_dob");
const create_sex = document.querySelector("#create_sex");

const update_id = document.querySelector("#update_id");
const update_name = document.querySelector("#update_name");
const update_dob = document.querySelector("#update_dob");
const update_sex = document.querySelector("#update_sex");

const delete_id = document.querySelector("#delete_id");

const readOne_id = document.querySelector("#read_id");

const add_rider_id = document.querySelector("#add_rider_id");
const add_race_id = document.querySelector("#add_race_id");

const createRider = () => {
    fetch(`${BASE_URL}/rider/create`, {
        method: 'POST',
        body: JSON.stringify({
            name: create_name.value,
            dateOfBirth: create_dob.value,
            sex: create_sex.value
        }),
        headers: {
            "Content-type": "application/json"
        }
    })
    .then(response => response.json())
    .then(json => console.log(json))
    .catch(err => console.error("Error sending data"));
}

const updateRider = () => {
    fetch(`${BASE_URL}/rider/update?id=${update_id.value}`,{
        method: 'PUT',
        body: JSON.stringify({
            id: update_id.value,
            name: update_name.value,
            dateOfBirth: update_dob.value,
            sex: update_sex.value
        }),
        headers: {
            "Content-type": "application/json"
        }
    })
    .then(response => response.json())
    .then(json => console.log(json))
    .catch(err => console.error("Failed to update entry"));
}

const deleteRider = () => {
    fetch(`${BASE_URL}/rider/delete/${delete_id.value}`,{
        method: 'DELETE'
    })
    .then(
        response => {
            if(response.status !== 204){
                console.error("Failed to delete rider");
            }
            else{
                console.log(`Deleted rider with id ${delete_id.value}.`);
            }
        }
    )
    .catch(err => console.error("Error deleting data"));
}

const readAllRiders = () => {
    fetch(`${BASE_URL}/rider/readAll`, {
        method: 'GET'
    })
    .then(
        response => {
            if(response.status !== 200){
                console.error("Error reading riders")
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

const readOneRider = () => {
    fetch(`${BASE_URL}/rider/read/${readOne_id.value}`,{
        method: 'GET'
    })
    .then(
        response => {
            if(response.status !== 200){
                console.error("Error reading rider")
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

const addToRace = () => {
    fetch(`${BASE_URL}/race/addRider?raceId=${add_race_id.value}&riderId=${add_rider_id.value}`,{
        method: `PUT`,
    })
    .then(
        response => {
            if(response.status !== 202){
                console.error("Error adding rider")
            }
            else{
                response.json().then(json => {
                    console.log(json);
                }
                )
            }
        }
    )
    .catch(err =>{console.error("Error sending data")});
}

const removeFromRace = () => {
    fetch(`${BASE_URL}/race/removeRider?raceId=${add_race_id.value}&riderId=${add_rider_id.value}`,{
        method: `PUT`,
    })
    .then(
        response => {
            if(response.status !== 202){
                console.error("Error removing rider")
            }
            else{
                response.json().then(json => {
                    console.log(json);
                }
                )
            }
        }
    )
    .catch(err =>{console.error("Error sending data")});
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

    console.log(entry.dateOfBirth);
    let dobString = entry.dateOfBirth;
    let dobDate = new Date(dobString);
    let birthDate = document.createTextNode(", Date of birth: "+dobDate.toLocaleDateString("en-GB"));
    p.appendChild(birthDate);

    let sex = document.createTextNode(", Sex: "+entry.sex);
    p.appendChild(sex);

    p.appendChild(document.createTextNode(", Races: "));

    for(let i=0; i<entry.races.length; ++i){
        let raceName = null;
        if(i === 0){
            raceName = document.createTextNode(entry.races[i].name);
        }
        else{
            raceName = document.createTextNode(", "+entry.races[i].name);
        }
        p.appendChild(raceName);
    }

    h.appendChild(p);

    div.appendChild(h);

    // let p4 = document.createTextNode("p");
    // let races = document.createTextNode(entry.races.name);
    // p4.appendChild(races);
    // h.appendChild(p4);

    return div;
}

const createButton = document.querySelector("#create_button");
createButton.addEventListener("click", createRider);

const updateButton = document.querySelector("#update_button");
updateButton.addEventListener("click", updateRider)

const deleteButton = document.querySelector("#delete_button");
deleteButton.addEventListener("click", deleteRider)

const readAllButton = document.querySelector("#read_all_button");
readAllButton.addEventListener("click", readAllRiders);

const readOneButton = document.querySelector("#read_one_button");
readOneButton.addEventListener("click", readOneRider);

const addButton = document.querySelector("#add_rider_race_button");
addButton.addEventListener("click", addToRace);

const removeButton = document.querySelector("#remove_rider_race_button");
removeButton.addEventListener("click", removeFromRace);
