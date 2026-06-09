// === GLOBALS ===
const eventsBtn = document.getElementById('displayEvents');
const volunteerBtn = document.getElementById('displayVolunteer');
const eventFeed = document.getElementById('eventFeed');
const volunteerFeed = document.getElementById('volunteerFeed');

// === SWITCH VIEW BUTTONS ===
eventsBtn.addEventListener('click', () => switchView('events'));
volunteerBtn.addEventListener('click', () => switchView('volunteers'));

function switchView(view) {
    if (view === 'events') {
        eventsBtn.classList.add('active');
        volunteerBtn.classList.remove('active');
        volunteerFeed.style.display = "none";
        eventFeed.style.display = "block";
        loadEvents();
    } else {
        volunteerBtn.classList.add('active');
        eventsBtn.classList.remove('active');
        eventFeed.style.display = "none";
        volunteerFeed.style.display = "block";
        loadVolunteers();
    }
}

// === LOAD EVENTS FROM API ===
document.addEventListener('DOMContentLoaded', () => {
    loadEvents(); // default load
});


async function loadEvents() {
    try {
        eventFeed.innerHTML = "<p>Loading...</p>";
        const response = await fetch('../api/community-events');
        if (!response.ok) throw new Error("Failed to fetch data.");
        const data = await response.json();
        eventFeed.innerHTML = "";

        if (!data || data.length === 0) {
            eventFeed.innerHTML = `<p>$"No events available at the moment."</p>`;
        } else {
            renderEvents(data, eventFeed);
        }
    } catch (error) {
        console.error('Error fetching data:', error);
        eventFeed.innerHTML = "<p>Error loading data. Please try again later.</p>";
    }
    //await loadData('../api/community-events', eventFeed, "No events available at the moment.");
}

async function loadVolunteers() {
    try {
        volunteerFeed.innerHTML = "<p>Loading...</p>";
        const response = await fetch('../api/volunteer-opportunities');
        if (!response.ok) throw new Error("Failed to fetch data.");
        const data = await response.json();
        volunteerFeed.innerHTML = "";

        if (!data || data.length === 0) {
            volunteerFeed.innerHTML = `<p>"No opportunities available at the moment."</p>`;
        } else {
            renderEvents(data, volunteerFeed);
        }
    } catch (error) {
        console.error('Error fetching data:', error);
        volunteerFeed.innerHTML = "<p>Error loading data. Please try again later.</p>";
    }
}

function getIconByType(permanent) {
    if (!permanent) return "../assets/icon.png"; //Default icon is used if undefined or null value passed as permanent

    switch (permanent.toLowerCase()) {
        case "volunteer":
            return "../assets/volunteericon.png";
        case "place":
            return "../assets/communitycentericon.png";
        case "event":
            return "../assets/communityeventicon.png";
        default:
            return "../assets/icon.png";
    }
}

// === RENDER EVENTS/OPPORTUNITIES ===
function renderEvents(items, targetFeed) {
    const fragment = document.createDocumentFragment();
    items.forEach(item => {
        const feedItem = document.createElement('li');
        feedItem.classList.add('feed-item');

        // const imageSrc = item.image || "../assets/icon.png";
        const imageSrc = getIconByType(item.permanent) || "../assets/icon.png";
        const name = item.name || item.event_name || item.opportunityName || item.orgName || item.title || `Untitled ${index + 1}`;

        if (targetFeed === eventFeed) {
            feedItem.innerHTML = ` 
            <img src="${imageSrc}" alt="Event Icon">
            <div class="feed-item-content">
                <div class="feed-header">
                    <h1>${item.name || 'N/A'}</h1>
                    <h2>${item.eventDate || 'N/A'}</h2>
                </div>
                <p><strong>Address:</strong> ${item.address || 'N/A'}</p>
                <p><strong>Type:</strong> ${item.category || 'N/A'}</p>
                <p><strong>Description:</strong> ${item.description || 'No description available.'}</p>
                <p><strong>Contact Organizer:</strong> ${item.contactInfo || 'N/A'}</p>
                <div class="button-container">
                    <button class="learnmore-button" onclick="window.location.href='../pages/learn_more.html?name=${name}'">Learn More</button>
                </div>
            </div>
            `;
        } else if (targetFeed === volunteerFeed) {
            feedItem.innerHTML = `
            <img src="${imageSrc}" alt="Event Icon">
            <div class="feed-item-content">
                <div class="feed-header">
                    <h1>${item.orgName || 'N/A'}</h1>
                    <h2>${item.eventDate || 'N/A'}</h2>
                </div>
                <p><strong>Address:</strong> ${item.address || 'N/A'}</p>
                <p><strong>Type:</strong> ${item.category || 'N/A'}</p>
                <p><strong>Description:</strong> ${item.voDescription || 'No description available.'}</p>
                <p><strong>Contact Organizer:</strong> ${item.contactInfo || 'N/A'}</p>
                <div class="button-container">
                    <button class="learnmore-button" onclick="window.location.href='../pages/learn_more.html?name=${name}'">Learn More</button>
                </div>
            </div>
            `;
        }
        fragment.appendChild(feedItem);
    });
    targetFeed.innerHTML = '';
    targetFeed.appendChild(fragment);
}


// Apply Filters to Events or Volunteers
async function filterEvents(selectedOptions) {
    try {
        const responses = await fetch('/fetchData?type=events');
        if (!responses.ok) throw new Error(" Failed to fetch events.");
        const response = await fetch('app/assets/events.json');
        if (!response.ok) throw new Error("Failed to fetch events.");
        const data = await response.json();
        eventFeed.innerHTML = "";
        const filteredData = data.filter(item => 
            selectedOptions.some(option => item.type.toLowerCase().includes(option.toLowerCase()))
        );
        if (filteredData.length > 0) {
            renderEvents(filteredData, eventFeed);
        } else {
            eventFeed.innerHTML = "<p>No events match the selected filters.</p>";
        }
    } catch (error) {
        console.error('Error fetching events during filter:', error);
        eventFeed.innerHTML = "<p>Error applying filters. Please try again.</p>";
    }
}

