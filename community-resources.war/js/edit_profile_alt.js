document.addEventListener("DOMContentLoaded", function () {
    const editProfileForm = document.getElementById("editProfileForm");

    // EDIT PROFILE LOGIC
    if (editProfileForm) {
        const email = document.getElementById("email");
        const firstname = document.getElementById("firstName");
        const lastname = document.getElementById("lastName");
        const username = document.getElementById("username");
        const over18 = document.getElementById("over18");
        const transport = document.getElementById("transport");
        
        email = document.getElementById("email");
        firstname = document.getElementById("firstName");
        lastname = document.getElementById("lastName");
        username = document.getElementById("username");
        over18 = document.getElementById("over18");
        transport = document.getElementById("transport");

        editProfileForm.addEventListener("submit", async function (event) {
            event.preventDefault();
            const currentPassword = document.getElementById("currentPassword");
            const newPassword = document.getElementById("newPassword");
            const confirmNewPassword = document.getElementById("confirmNewPassword");

            const isUsernameValid = await validateUsername();
            const isEmailValid = validateEmail();
            const isPasswordValid = validatePassword();
            const doPasswordsMatch = matchPasswords();

            if (!isUsernameValid || !isEmailValid || !isPasswordValid || !doPasswordsMatch) {
                return;
            }

            const formData = new FormData();
            formData.append("username", username.value.trim());
            formData.append("email", email.value.trim());
            formData.append("password", newPassword.value);

            const passwordValue = password.value
            const emailValue = email.value.trim()

            console.log(emailValue)
            console.log(passwordValue)

            // path to wherever the resulting function should go
            fetch("/api/edit-profile", {
                method: "POST",
                headers: {
                  "Content-Type": "application/json"
                },
                body: JSON.stringify({ email: emailValue, passwordHash: passwordValue })
            })
                .then(response => response.json())
                .then(data => {
                    alert(data.message);
                    if (data.status == "success") {
                        localStorage.setItem("isAuthenticated", "true");
                    }
                })
                .catch(error => {
                    console.error("Sign-up error:", error);
                    alert("An error occurred. Please try again.");
                });

        });

        username.addEventListener("input", validateUsername);
        email.addEventListener("input", validateEmail);
        currentPassword.addEventListener("input", validatePassword);
        newPassword.addEventListener("input", validatePassword);
        confirmNewPassword.addEventListener("input", matchPasswords);
    }

    // VALIDATION FUNCTIONS
    async function validateUsername() {
        const usernameField = document.getElementById("username");
        const feedback = document.getElementById("usernameFeedback");
        const value = usernameField.value.trim();

        if (value.length < 3) {
            feedback.textContent = "Username must be at least 3 characters long.";
            feedback.style.color = "red";
            return false;
        }

        const pattern = /^[a-zA-Z0-9_]+$/;
        if (!pattern.test(value)) {
            feedback.textContent = "Username must contain only letters, numbers, or underscores.";
            feedback.style.color = "red";
            return false;
        }

        const isTaken = false; // await checkUsernameAvailability(value);
        if (isTaken) {
            feedback.textContent = "Username is already taken.";
            feedback.style.color = "red";
            return false;
        }

        feedback.textContent = "Username is available.";
        feedback.style.color = "green";
        return true;
    }

    function validateEmail() {
        const emailField = document.getElementById("email");
        const feedback = document.getElementById("emailFeedback");
        const pattern = /^\S+@\S+\.\S+$/;

        if (!pattern.test(emailField.value.trim())) {
            feedback.textContent = "Invalid email format.";
            feedback.style.color = "red";
            return false;
        }

        feedback.textContent = "";
        return true;
    }

    function validatePassword() {
        const passwordField = document.getElementById("newPassword");
        const feedback = document.getElementById("passwordFeedback");
        const value = passwordField.value;

        if (value.length < 6) {
            feedback.textContent = "Password must be at least 6 characters long.";
            feedback.style.color = "red";
            return false;
        }

        feedback.textContent = "Strong password.";
        feedback.style.color = "green";
        return true;
    }

    function matchPasswords() {
        const password = document.getElementById("newPassword").value;
        const confirm = document.getElementById("confirmNewPassword").value;
        const feedback = document.getElementById("confirmNewPasswordFeedback");

        if (password !== confirm) {
            feedback.textContent = "Passwords do not match.";
            feedback.style.color = "red";
            return false;
        }

        feedback.textContent = "Passwords match.";
        feedback.style.color = "green";
        return true;
    }

    handleFormSubmission(editProfileForm);

    function handleFormSubmission(form) {
        form.addEventListener('submit', (event) => {
            event.preventDefault();
            const formData = new FormData(form);
            fetch(form.action, { method: 'POST', body: formData })
                .then(response => response.ok ? response.text() : Promise.reject('Network response was not ok.'))
                .then(data => alert(data))
                .catch(error => console.error('Error:', error));
        });
    }

    // AUTH LINK MANAGEMENT
    const authLinks = document.getElementById("auth-links");
    const authLinksFooter = document.getElementById("auth-links-footer");
    const isAuthenticated = localStorage.getItem("isAuthenticated") === "true";

    if (authLinks) {
        authLinks.innerHTML = isAuthenticated
            ? '<a href="../pages/user_account.html">Account</a> | <a href="#" id="logout">Sign Out</a>'
            : '<a href="../pages/login.html">Login</a> | <a href="../pages/signup.html">Sign Up</a>';
    }

    if (authLinksFooter) {
        authLinksFooter.innerHTML = isAuthenticated
            ? '<a href="../pages/user_account.html">Account</a> | <a href="#" id="logout-footer">Sign Out</a>'
            : '<a href="../pages/login.html">Login</a> | <a href="../pages/signup.html">Sign Up</a>';
    }

    function logout(event) {
        event.preventDefault();
        localStorage.removeItem("isAuthenticated");
        window.location.href = "../pages/home.html";
    }

    document.body.addEventListener("click", function (e) {
        if (e.target && (e.target.id === "logout" || e.target.id === "logout-footer")) {
            logout(e);
        }
    });
});