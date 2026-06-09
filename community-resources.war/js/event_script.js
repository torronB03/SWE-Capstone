document.addEventListener("DOMContentLoaded", function () {

    const volunteerBtn = document.getElementById('volunteerBtn');
    const eventBtn = document.getElementById('eventBtn');
    const formTitle = document.getElementById('formTitle');
    const volunteerForm = document.getElementById('volunteerForm');
    const eventForm = document.getElementById('eventForm');

    // limiting each form to one event listener
    let isBound = {
        volunteer: false,
        event: false
    };

    // Default view
    showVolunteerForm();

    // Event listeners for tab switching
    volunteerBtn.addEventListener('click', showVolunteerForm);
    eventBtn.addEventListener('click', showEventForm);

    function showVolunteerForm() {
        volunteerBtn.classList.add('active');
        eventBtn.classList.remove('active');
        formTitle.textContent = "Create New Volunteer Opportunity";
        volunteerForm.style.display = "block";
        eventForm.style.display = "none";
        // adding the event listener only for this form
        if (isBound.volunteer) {
            handleFormSubmission(volunteerForm);
            isBound.volunteer = true;
        }
    }

    function showEventForm() {
        eventBtn.classList.add('active');
        volunteerBtn.classList.remove('active');
        formTitle.textContent = "Create New Event";
        volunteerForm.style.display = "none";
        eventForm.style.display = "block";
        // adding the event listener only for this form
        if (isBound.event) {
            handleFormSubmission(eventForm);
            isBound.event = true;
        }
    }

    //handleFormSubmission(volunteerForm);
    //handleFormSubmission(eventForm);

    function handleFormSubmission(form) {
        form.addEventListener('submit', (event) => {
            event.preventDefault();
            const formData = new FormData(form);
            console.log("Submitting form:", form.id);
    
            fetch(form.action, { method: 'POST', body: formData })
                .then(response => {
                    if (!response.ok) {
                        return response.text().then(err => { throw new Error(err); });
                    }
                    return response.json();
                })
                .then(data => {
                    if (data.status === "success") {
                        window.location.href = form.dataset.redirectUrl || "/community-resources-app";
                    } else {
                        console.error('Submission Failed:', error);
                    }
                })
                .catch(error => {
                    console.error('Error during submission:', error);
                });
        });
    }

    // Organizer addition handling
    setupOrganizerAddition('addOrganizerBtnVolunteer', 'newOrganizerVolunteer', 'organizersListVolunteer');
    setupOrganizerAddition('addOrganizerBtnEvent', 'newOrganizerEvent', 'organizersListEvent');

    function setupOrganizerAddition(buttonId, inputId, listId) {
        document.getElementById(buttonId).addEventListener('click', function() {
            let newOrganizer = document.getElementById(inputId).value;
            let organizersList = document.getElementById(listId);
            if (newOrganizer && organizersList.children.length < 3) {
                let organizerElement = createListItem(newOrganizer, 'organizers[]');
                organizersList.appendChild(organizerElement);
                document.getElementById(inputId).value = '';
            } else {
                alert("You can add a maximum of 3 organizers.");
            }
        });
    }

    // Volunteer addition handling
    document.getElementById('addVolunteerBtn').addEventListener('click', function() {
        let newVolunteer = document.getElementById('newVolunteer').value;
        if (newVolunteer) {
            let volunteerElement = createListItem(newVolunteer, 'volunteers[]');
            document.getElementById('volunteersList').appendChild(volunteerElement);
            document.getElementById('newVolunteer').value = '';
        }
    });

    function createListItem(value, inputName) {
        let itemElement = document.createElement('div');
        itemElement.textContent = value;
        let hiddenInput = document.createElement('input');
        hiddenInput.type = 'hidden';
        hiddenInput.name = inputName;
        hiddenInput.value = value;
        itemElement.appendChild(hiddenInput);
        return itemElement;
    }
});