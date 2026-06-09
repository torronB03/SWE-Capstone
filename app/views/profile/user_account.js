
// Update the DOM with user data
function displayUserInfo(user) {
  const usernameEl = document.getElementById("username");
  const emailEl = document.getElementById("email");
  const nameEl = document.getElementById("name");
  const over18El = document.getElementById("over18");
  const transportationEl = document.getElementById("transport");

  // same data members as User.java
  if (usernameEl) usernameEl.textContent = user.username || "Unknown";
  if (emailEl) emailEl.textContent = user.email || "Unknown";
  if (over18El) over18El.textContent = "Over 18? " + (user.over18 ? "Yes" : "No") || "Over 18? Don't know";
  if (nameEl) nameEl.textContent = user.firstName + " " + user.lastName || "Unknown";
  if (transportationEl) transportationEl.textContent = user.transport || "Unknown";
}

document.addEventListener("DOMContentLoaded", () => {
  fetch("../user-api/session-data")
  .then(response => response.json())
  .then(user => {
    displayUserInfo(user)
  })
  .catch(error => console.error("Session data not found!! ", error))
});
