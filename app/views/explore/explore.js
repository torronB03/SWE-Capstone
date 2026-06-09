document.addEventListener("DOMContentLoaded", () => {
    console.log("explore.js loaded and running!");

    const showAllBtn = document.getElementById('showAllBtn');
    if (showAllBtn) {
        showAllBtn.addEventListener('click', () => {
            document.getElementById('filterForm').reset();
            loadEvents();
        });
    }

    const applyBtn = document.getElementById('applyBtn');
    if (applyBtn) {
        applyBtn.addEventListener('click', applyFilters);
    }

    const cancelBtn = document.getElementById('cancelBtn');
    if (cancelBtn) {
        cancelBtn.addEventListener('click', () => {
            document.getElementById('filterForm').reset();
            const feedList = document.getElementById("feedList");
            feedList.innerHTML = "";
            loadEvents();
        });
    }

    loadEvents();
});

async function loadEvents() {
    await loadData('/community-resources-app/api/all-data', document.getElementById("feedList"), "No events found.");
}

async function loadData(url, targetFeed, emptyMessage) {
    try {
        targetFeed.innerHTML = "<p>Loading...</p>";
        const response = await fetch(url);
        if (!response.ok) throw new Error("Failed to fetch data.");
        const data = await response.json();
        console.log("Loaded data:", data);

        targetFeed.innerHTML = "";
        const items = data.events || data || [];

        if (items.length === 0) {
            targetFeed.innerHTML = `<p>${emptyMessage}</p>`;
        } else {
            renderEvents(items, targetFeed);
        }
    } catch (error) {
        console.error('Error fetching data:', error);
        targetFeed.innerHTML = "<p>Error loading data. Please try again later.</p>";
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

function renderEvents(items, targetFeed) {
    const fragment = document.createDocumentFragment();
    items.forEach((item, index) => {
        console.log(`Item ${index}:`, item);

        const feedItem = document.createElement('div');
        feedItem.classList.add('feed-item');

        // const imageSrc = item.image || "../assets/icon.png";
        const imageSrc = getIconByType(item.permanent) || "../assets/icon.png"; 
        const name = item.name || item.event_name || item.opportunityName || item.orgName || item.title || `Untitled ${index + 1}`;
        const date = item.eventDate || item.date || item.hoursOfOperation || "N/A";
        const address = item.address || item.location || "N/A";
        //const organizer = Array.isArray(item.organizers) ? item.organizers.join(", ") : item.organizers || item.org_name || item.organizerName || item.contactInfo || "N/A";
        //const type = item.type || item.category || item.business_type || "N/A";
        const description = item.description || item.eventDescription || item.voDescription || item.services_provided || item.locationNotes || "No description available.";
        const id = item.id || item.event_id || item.vo_data_index || index;

        feedItem.innerHTML = `
        <img src="${imageSrc}" alt="Event Icon">
        <div class="feed-content">
            <div class="feed-header">
                <h1>${name}</h1>
                <h2>${date}</h2>
            </div>
            <p><strong>Date:</strong> ${date}</p>
            <p><strong>Address:</strong> ${address}</p>
            <p><strong>Description:</strong> ${description}</p>
            <div class="button-container">
                <button class="save-button" onclick="handleSave(${name})">Save</button>
                <button class="learnmore-button" onclick="window.location.href='../pages/learn_more.html?name=${name}'">Learn More</button>
            </div>
        </div>
        `;
        fragment.appendChild(feedItem);
    });
    targetFeed.appendChild(fragment);
}

function applyFilters() {
    const selectedOptions = Array.from(document.querySelectorAll('#filterForm input[type="checkbox"]:checked'))
        .map(cb => cb.id);

    const apiEndpoints = {
        "food-bank": "/community-resources-app/api/foodbanks",
        "shelter": "/community-resources-app/api/shelters",
        "community-event": "/community-resources-app/api/community-events",
        "medical-center": "/community-resources-app/api/medical-centers",
        "community-center": "/community-resources-app/api/community-centers",
        "volunteer-opportunity": "/community-resources-app/api/volunteer-opportunities",
        "clothing-store": "/community-resources-app/api/clothing-stores",
    };

    const feedList = document.getElementById("feedList");
    feedList.innerHTML = "";

    if (selectedOptions.length === 0) {
        loadEvents();
        return;
    }

    // Make associated API calls based on selected options from the list
    selectedOptions.forEach(option => {
        const apiEndpoint = apiEndpoints[option];
        if (apiEndpoint) {
            loadData(apiEndpoint, feedList, "Filter Events Failed");
        }
    });
}

function handleSave(name) {
    console.log("Save clicked on ID:", name);
    alert("Please log in to save this event.");
}
