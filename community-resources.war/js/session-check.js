// session-check.js — handles redirect if user is not authenticated

function getCookie(name) {
  const value = `; ${document.cookie}`;
  const parts = value.split(`; ${name}=`);
  if (parts.length === 2) return parts.pop().split(';').shift();
}

const isAuthenticated = getCookie("loggedIn")

const currentPath = window.location.pathname;

// List of pages that don't require authentication
const publicPages = [
  "login.html",
  "event_creation.html" ,                   // REMOVE PLS FOR TESTING ONLY
  "signup.html",
  "help.html",
  "home.html",
  "learn_more.html",
  "search.html" 
];

// Check if current path ends with a public filename
const isPublicPage = publicPages.some(page => currentPath.endsWith(page));
if (isAuthenticated != "true" && !isPublicPage) {
  // Redirect to signup page if not logged in
  //window.location.href = "../pages/signup.html"; // PLEASE UNCOMMENT THIS IN THE DEMO THANKS
  console.log("You aren't supposed to be here...")
}

// if we're logged in, then...
// header links
if (isAuthenticated === "true")
{
  // get session data from SessionStatusServlet
  fetch("../user-api/session-data")
  .then(response => response.json())
  .then(data => {
    console.log("user is logged in")
    // change user profile element to user's name, and lead to user_account etc. 
    const profileDiv = document.getElementById("userProfile");
    profileDiv.innerHTML = `
                        <img src="../assets/profile.png" alt="User profile picture">
                        <a href="../pages/user_account.html">${data.username}</a>
                    `;
  })
  .catch(error => console.error("Session check failed:", error));

  // footer logout button
  const footerAuthLinks = document.getElementById("footerAuthLinks");
  if (footerAuthLinks) {
    footerAuthLinks.innerHTML = `<a href="#" id="footerLogout">Sign Out</a>`;
  
    document.getElementById("footerLogout").addEventListener("click", function (e) {
      e.preventDefault();
      fetch("../user-api/logout").then(() => {
        window.location.href = "../pages/home.html";
      });
    });
  }
}

