alert("LEARN MORE.JS IS LOADED!");

// ===== DOM ELEMENTS =====
const eventContainer = document.getElementById("event-container");
const detailsContainer = document.getElementById("details-section");
const accountPopup = document.getElementById('account-popup');
const overlayPopup = document.getElementById('overlay');
const editErrorPopup = document.getElementById('editError-popup');
const editEventPopup = document.getElementById('edit-event-popup');
const editVolunteerPopup = document.getElementById('edit-volunteer-popup');
const eventForm = document.getElementById('eventForm');
const volunteerForm = document.getElementById('volunteerForm');
const loginMessage = document.getElementById('login-message');
const signupMessage = document.getElementById('signup-message');
const directionsBox = document.querySelector('.directions');


let isLoggedIn = false;
let currentUserAccount = null;
let type = null;

const urlParams = new URLSearchParams(window.location.search);
const nameID = urlParams.get("name");
// null = bad
console.log("Fetched nameID from URL:", nameID);


//if (!nameID) {
    //alert("Invalid event. Returning to Explore.");
    //window.location.href = "../pages/explore.html";
//}

document.addEventListener("DOMContentLoaded", function () {
    console.log("Loaded DOM");
    // === Fetch logged-in user status ===
    fetch("/community-resources-app/api/current-user")
        .then(res => res.json())
        .then(data => {
            isLoggedIn = data.loggedIn;
            currentUserAccount = data.username;
        })
        .catch(() => {
            isLoggedIn = false;
            currentUserAccount = null;
        });
        console.log("Entered Fetch & got current user data");
        console.log("Data:", data);
    
    // === Render the page w/ information ===
    // call api that gets the organization
    console.log("Fetching with nameID:", nameID);
    fetch(`/community-resources-app/api/get-event?name=${encodeURIComponent(nameID)}`)
        .then(response => response.json())
        .then(data => {
            const imageSrc = data.image || "../assets/icon.png";
            const name = data.name || data.event_name || data.opportunityName || data.orgName || data.title || "N/A";
            const date = data.eventDate || data.date || data.hoursOfOperation || "N/A";
            const address = data.address || data.location || "N/A";
            const organizer = Array.isArray(data.organizers) ? data.organizers.join(", ") : data.organizers || data.org_name || data.organizerName || data.contactInfo || "N/A";
            const type = data.permanent || "N/A";
            const description = data.description || data.eventDescription || data.voDescription || data.services_provided || "No description available.";
            const id = data.id || data.event_id || data.vo_data_index || index;
            const hours = data.hoursOfOperation || "N/A";
            const contact = data.contactInfo || "N/A";
            const price = data.price || "N/A";
            const services = data.services || "N/A";
            // const coordinates = data.coordinates || "N/A";
            const currentVolunteers = data.currentVolunteers || 0;
            const volunteersNeeded = data.volunteersNeeded || 0;

            detailsContainer.innerHTML = "";
            const coordinates = data.coordinates || "N/A";

            console.log("Entered Fetch & got event data");
            console.log("Data:", data.date);
            console.log("Data:", data.name);

            switch(type) {
                // display event format
                case "event":
                    detailsContainer.innerHTML = `
                        <div class="event">
                            <h1>${name}</h1>
                            <div class="date-hours">
                                <p>${date}<br>${hours}</p>
                            </div>
                            <h2>Organizers:</h2>
                            <div class="organizers">
                                <p>${organizer}</p>
                            </div>
                            <h3>Event Description</h3>
                            <div class="description-text">
                                <p>${description}</p>
                            </div>
                        </div>
                    `;
                    break;

                // display volunteer format
                case "volunteer":
                    detailsContainer.innerHTML = `
                        <div class="event">
                            <h1>${name}</h1>
                            <div class="date-hours">
                                <p>${date}<br>${hours}</p>
                            </div>
                            <h2>Organizers:</h2>
                            <div class="organizers">
                                <p>${organizer}</p>
                            </div>
                            <h3>Event Description</h3>
                            <div class="description-text">
                                <p>${description}</p>
                            </div>
                            <div class="volunteer-count">
                                <h4>Volunteers</h4>
                                <p>${currentVolunteers}/${volunteersNeeded}</p>        
                            </div>
                        </div>
                    `;
                    break;
                    
                // display if it's a permanent location
                
                /* notes - Amari :
                    1. how are the hours formulated in the database? can we still use this dropdown?
                    2. can we get the business type through the database name? or is that not possible anymore
                    3. i believe that "services" is json. i'm not sure how to parse that through an inner-html without a for-loop. i will look into this more
                */

                case "place":
                    detailsContainer.innerHTML = `
                        <div class="place">
                            <h1>${name}</h1>
                            <div class="dropdown">
                                <h2>Hours</h2>
                                <button id="drop-btn" onclick="displayHours()"></button>        
                                <div class="dropdown-content" id="dropdown-content" style="display:none">
                                    <p id="day-header">Monday-Friday:</p>
                                    <p>Monday: ${hours}</p>
                                    <p>Tuesday:</p>
                                    <p>Wednesday:</p>
                                    <p>Thursday:</p>
                                    <p>Friday:</p>
                                    <p id="day-header">Saturday-Sunday:</p>
                                    <p>Saturday:</p>
                                    <p>Sunday:</p>
                                </div>
                            </div>
                            <div class="contact-info">
                                <h2>Contact Info:</h2>
                                <p id="contact">${contact}</p>
                            </div>
                            <div class="pricing">
                                <h2>Price:</h2>
                                <p id="price">${price}</p>
                            </div>
                            <div class = "services">
                                <h2>Services:</h2>
                                <p>${services}</p>
                            </div>
                            <div class="event-desc">
                                <h3>Event Description</h3>
                                <div class="description-text">
                                    <p>${description}</p>
                                </div>
                            </div>
                            <div class="business-type">                                                                         
                                <h4>Type</h4>
                                <p>[Food Bank]</p>        
                            </div>
                        </div>
                    `;
                    break;
                default:
                    detailsContainer.innerHTML = `No Information Found About Event ${nameID}`;
                    break;
            }
            
        })
        .catch((error) => {
            console.error("Fetch error:", error);
        });

    // === Render dynamic buttons ===
    renderActionButtons();

    // === Button Listeners ===
    document.body.addEventListener("click", (event) => {
        const target = event.target;
        const action = target.id;

        if (action === "save-button") {
            isLoggedIn ? alert("Event saved!") : showPopup("save");
        } else if (action === "signup-button") {
            isLoggedIn ? alert("Signed up!") : showPopup("signup");
        } else if (action === "editInfo-button") {
            if (!isLoggedIn) return showPopup("editInfoAcc");

            fetch(`/community-resources-app/api/event-organizer?id=${nameID}`)
                .then(response => response.json())
                .then(data => {
                    if (currentUserAccount === data.username) {
                        showPopup("editInfoEvent");
                        handleFormSubmission(eventForm);
                        closePopup();
                    } else {
                        showPopup("editErrorInfo");
                    }
                })
                .catch(error => console.error("Error fetching event organizer:", error));
        }
    });

    // Dropdown toggle
    const dropBtn = document.getElementById('drop-btn');
    const dropContent = document.getElementById('dropdown-content');
    if (dropBtn && dropContent) {
        dropBtn.addEventListener("click", function () {
            const isVisible = dropContent.style.display === "block";
            dropContent.style.display = isVisible ? "none" : "block";
            dropContent.style.visibility = isVisible ? "hidden" : "visible";
        });
    }
});

console.log("Map is loading for:", nameID);
// === Map Loader (called by Google Maps)
function initMap() {
    const map = new google.maps.Map(document.getElementById("map"), {
        zoom: 15,
        center: { lat: 29.9511, lng: -90.0715 }, // fallback location: New Orleans
    });

    const geocoder = new google.maps.Geocoder();

    const urlParams = new URLSearchParams(window.location.search);
    const name = urlParams.get("name");

    if (!name) {
        console.error("No name provided in URL");
        return;
    }

    fetch(`community-resources-app/api/get-event?name=${encodeURIComponent(nameID)}`)
        .then(response => {
            if (!response.ok) throw new Error("Network response was not ok.");
            return response.json();
        })
        .then(data => {
            console.log("Fetched data:", data);

            const address = data.address || data.addressLine1 || data.location || "New Orleans, LA 70118";
            console.log("Using address for geocoding:", address);

            if (!data || !data.address) {
                console.error("Invalid or missing address data", data);
                return;
            }

            geocoder.geocode({ address: address }, (results, status) => {
                if (status === "OK" && results[0]) {
                    map.setCenter(results[0].geometry.location);
                    new google.maps.Marker({
                        map: map,
                        position: results[0].geometry.location,
                    });
                } else {
                    console.error("Geocode was not successful for the following reason: " + status);
                }
            });

            // You can also update the rest of the page with data here if needed
            document.getElementById("orgName").textContent = data.name || "No name available";
            document.getElementById("orgDescription").textContent = data.description || "No description provided";
        })
        .catch(error => {
            console.error("Fetch error:", error);
        });
}


// === Expose initMap to global window (so Google callback can call it)
window.initMap = initMap;

// === Utility: Close any popup
function closePopup() {
    accountPopup.style.display = "none";
    overlayPopup.style.display = "none";
    editErrorPopup.style.display = "none";
    editEventPopup.style.display = "none";
    editVolunteerPopup.style.display = "none";
}

// === Utility: Form submission handler
function handleFormSubmission(form) {
    if (!form) return;
    form.addEventListener('submit', (event) => {
        event.preventDefault();
        const formData = new FormData(form);
        fetch(form.action, {
            method: 'POST',
            body: formData
        })
            .then(response => response.ok ? response.text() : Promise.reject('Network response was not ok.'))
            .then(data => alert(data))
            .catch(error => console.error('Error submitting form:', error));
    });
}

// === Utility: Show appropriate popup
function showPopup(button) {
    switch (button) {
        case "editInfoEvent":
            editEventPopup.style.display = "block";
            break;
        case "editInfoVolunteer":
            editVolunteerPopup.style.display = "block";
            break;
        case "editErrorInfo":
            editErrorPopup.style.display = "block";
            overlayPopup.style.display = "block";
            break;
        case "editInfoAcc":
            loginMessage.innerHTML = 'Sign In To Edit!<br>(You Must Be The Creator To Edit)';
            break;
        case "save":
            loginMessage.innerHTML = 'Sign In To Save This Event!<br><a href="../pages/login.html">Log In</a>';
            break;
        case "signup":
            loginMessage.innerHTML = 'Sign In To RSVP For This Event!<br><a href="../pages/login.html">Log In</a>';
            break;
        default:
            return;
    }

    signupMessage.innerHTML = 'Don\'t Have An Account?<br><a href="../pages/signup.html">Sign Up</a>';
    accountPopup.style.display = "block";
    overlayPopup.style.display = "block";
}

// === Utility: Render buttons in multiple places if needed
function renderActionButtons() {
    const buttonsContainer = document.getElementById("dynamic-buttons");
    if (buttonsContainer) {
        buttonsContainer.innerHTML = `
            <button type="button" class="save-button" id="save-button" data-id="${nameID}">Save</button>
            <button type="button" class="signup-button" id="signup-button" data-id="${nameID}">Sign Up</button>
            <button type="button" class="editInfo-button" id="editInfo-button" data-id="${nameID}">Edit Info</button>
        `;
    }
}
