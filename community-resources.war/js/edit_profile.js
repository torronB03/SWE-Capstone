document.addEventListener("DOMContentLoaded", function () {
    const editForm = document.getElementById("editForm");

    if (editForm) {
        const fullName = document.getElementById("nameField");
        const username = document.getElementById("usernameField");
        const email = document.getElementById("emailField");
        const oldPassword = document.getElementById("currentPasswordField");
        const newPassword = document.getElementById("newPasswordField");
        const confirmPassword = document.getElementById("confirmNewPasswordField");

        editForm.addEventListener("submit", async function (event) {
            event.preventDefault();

            const splitName = fullName.value.split(" ");
            var firstNameVal = "";
            var lastNameVal = "";
            if (splitName.length > 1) {
                firstNameVal = splitName[0];
                lastNameVal = splitName[1];
            }
            const emailVal = email.value;
            const usernameVal = username.value;
            const oldPasswordVal = oldPassword.value.trim();
            const passwordHashVal = newPassword.value.trim();
            const confirmNewPasswordVal = confirmPassword.value.trim();

            try {
                const response = await fetch("../user-api/edit-profile", {
                    method: "POST",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify({ email: emailVal, passwordHash: passwordHashVal, firstName: firstNameVal, lastName: lastNameVal, username: usernameVal, currentPassword: oldPasswordVal, confirmPassword: confirmNewPasswordVal })
                });

                const data = await response.json();
                alert(data.message);

                if (data.success) {
                    window.location.href = "../pages/user_account.html";
                }
            } catch (error) {
                console.error("Edit profile error:", error);
                alert("It's over bro. Please try again.");
            }
        });
    }
});
