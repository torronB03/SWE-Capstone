document.addEventListener("DOMContentLoaded", function () {
    const loginForm = document.getElementById("loginForm");
    const signupForm = document.getElementById("signupForm");

    // LOGIN LOGIC
    if (loginForm) {
        loginForm.addEventListener("submit", async function (event) {
            event.preventDefault();

            const email = document.getElementById("email").value.trim();
            const password = document.getElementById("password").value;

            try {
                const response = await fetch("../user-api/login", {
                    method: "POST",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify({ email, password })
                });

                const data = await response.json();
                alert(data.message);

                if (data.success) {
                    window.location.href = "../pages/home.html";
                }
            } catch (error) {
                console.error("Login error:", error);
                alert("An error occurred during login. Please try again.");
            }
        });
    }

    // SIGN-UP LOGIC
    if (signupForm) {
        const firstname = document.getElementById("firstName");
        const lastname = document.getElementById("lastName");
        const username = document.getElementById("signupUsername");
        const email = document.getElementById("signupEmail");
        const password = document.getElementById("signupPassword");
        const confirmPassword = document.getElementById("signupConfirmPassword");

        signupForm.addEventListener("submit", async function (event) {
            event.preventDefault();

            const isUsernameValid = await validateUsername();
            const isEmailValid = validateEmail();
            const isPasswordValid = validatePassword();
            const doPasswordsMatch = matchPasswords();

            if (!isUsernameValid || !isEmailValid || !isPasswordValid || !doPasswordsMatch) {
                return;
            }

            const passwordValue = password.value
            const emailValue = email.value.trim()
            const usernameValue = username.value
            const firstnameValue = firstname.value
            const lastnameValue = lastname.value

            console.log(emailValue)
            console.log(passwordValue)

            // gson does NOT use constructors, getters/setters for parsing from json. values must be mapped to private members of their respective classes.
            fetch("../user-api/signup", {
                method: "POST",
                headers: {
                  "Content-Type": "application/json"
                },
                body: JSON.stringify({ firstName: firstnameValue, lastName: lastnameValue, username: usernameValue, email: emailValue, passwordHash: passwordValue })
            })
                .then(response => response.json())
                .then(data => {
                    if (data.success) {
                        window.location.href = "../pages/signup_success.html";
                    }
                    else
                    {
                        alert(data.message);
                    }
                })
                .catch(error => {
                    console.error("Sign-up error:", error);
                    alert("An error occurred. Please try again.");
                });

        });

        username.addEventListener("input", validateUsername);
        email.addEventListener("input", validateEmail);
        password.addEventListener("input", validatePassword);
        confirmPassword.addEventListener("input", matchPasswords);
    }

    // VALIDATION FUNCTIONS
    async function validateUsername() {
        const usernameField = document.getElementById("signupUsername");
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
        const emailField = document.getElementById("signupEmail");
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
        const passwordField = document.getElementById("signupPassword");
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
        const password = document.getElementById("signupPassword").value;
        const confirm = document.getElementById("signupConfirmPassword").value;
        const feedback = document.getElementById("confirmPasswordFeedback");

        if (password !== confirm) {
            feedback.textContent = "Passwords do not match.";
            feedback.style.color = "red";
            return false;
        }

        feedback.textContent = "Passwords match.";
        feedback.style.color = "green";
        return true;
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
