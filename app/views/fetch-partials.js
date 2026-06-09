// get header, get footer, also execute scripts if any 
document.addEventListener("DOMContentLoaded", () => {
  fetch("../partials/header.html")
  .then(res => res.text())
  .then(html => {
    
    document.getElementById("header").innerHTML = html;
    
  });
  fetch("../partials/footer.html")
  .then(res => res.text())
  .then(html => {
    document.getElementById("footer").innerHTML = html;
    // grab session check script
    const script = document.createElement("script");
    script.src = "../js/session-check.js";
    document.body.appendChild(script);
  });
});