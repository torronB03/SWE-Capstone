document.addEventListener("DOMContentLoaded", () => {
    console.log("dashboard.js loaded and running!");

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

    console.log("Dashboard page loaded");
    loadUserGreeting();
    loadEvents();
});

function getIconByType(permanent) {
    if (!permanent) return "../assets/icon.png";

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

async function loadUserGreeting() {
    console.log("loading user greeting");
    fetch("../user-api/session-data")
        .then(response => response.json())
        .then(data => {
            console.log("User is logged in:", data); // Debugging line
            // Update the greeting with the user's name
            const userGreeting = document.getElementById("userGreeting");
            if (userGreeting) {
                userGreeting.innerHTML = `Welcome back, ${data.username}`;
            } else {
                console.error("User greeting element not found");
            }
        })
        .catch(error => console.error("Error fetching user data:", error));
}

// Load all events
async function loadEvents() {
    await loadData('/community-resources-app/api/all-data', document.getElementById("feedList"), "No events found.");
}

// Generic data loading function
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

// Function to render events to the feed
function renderEvents(items, targetFeed) {
    const fragment = document.createDocumentFragment();
    items.forEach((item, index) => {
        console.log(`Item ${index}:`, item);

        const feedItem = document.createElement('div');
        feedItem.classList.add('feed-item');

        const imageSrc = getIconByType(item.permanent) || "../assets/icon.png";
        const name = item.name || item.event_name || item.opportunityName || item.orgName || item.title || `Untitled ${index + 1}`;
        const date = item.eventDate || item.date || item.hoursOfOperation || "N/A";
        const address = item.address || item.location || "N/A";
        const description = item.description || item.eventDescription || item.voDescription || item.services_provided || "No description available.";

        feedItem.innerHTML = `
        <img src="${imageSrc}" alt="Resource Icon">
        <div class="explore-feed-content">
            <div class="explore-feed-header">
                <h1>${name}</h1>
                <h2>${date}</h2>
            </div>
            <p><strong>Address:</strong> ${address}</p>
            <p><strong>Description:</strong> ${description}</p>
            <div class="button-container">
                <button class="learnmore-button" onclick="window.location.href='../pages/learn_more.html?name=${name}'">Learn More</button>
            </div>
        </div>
        `;
        fragment.appendChild(feedItem);
    });
    targetFeed.appendChild(fragment);
}

// Apply filters to the events
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
        "saved": "/community-resources-app/api/saved"
    };

    const feedList = document.getElementById("feedList");
    feedList.innerHTML = "";

    if (selectedOptions.length === 0) {
        loadEvents();
        return;
    }

    selectedOptions.forEach(option => {
        const apiEndpoint = apiEndpoints[option];
        if (apiEndpoint) {
            loadData(apiEndpoint, feedList, "No events found for this filter.");
        }
    });
}
