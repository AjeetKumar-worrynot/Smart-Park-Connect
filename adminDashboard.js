// Create Parking Spot
document.getElementById("create-spot-form").addEventListener("submit", async (e) => {
  e.preventDefault();
  const location = document.getElementById("location").value;
  const status = document.getElementById("status").value;

  const token = localStorage.getItem("jwtToken");

  try {
    const response = await fetch("http://localhost:8080/api/admin/create-spot", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"},
      body: JSON.stringify({ location, status }),  // Fix here: Pass object not comma-separated values
  });

    const text = await response.text();
    const result = text ? JSON.parse(text) : {};

    if (response.ok) {
      document.getElementById("create-spot-message").innerText =
        result.message || "Parking spot created successfully!";
    } else {
      document.getElementById("create-spot-message").innerText =
        result.message || "Failed to create parking spot";
    }
  } catch (error) {
    console.error("Error:", error);
    document.getElementById("create-spot-message").innerText =
      "An error occurred. Please try again later.";
  }
});

// Fetch Users
async function fetchUsers() {
  const token = localStorage.getItem("jwtToken"); // Use the correct token name here
  try {
    const response = await fetch("http://localhost:8080/api/admin/users", {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    });

    const text = await response.text();
    const users = text ? JSON.parse(text) : [];

    const tableBody = document.querySelector("#users-table tbody");
    tableBody.innerHTML = "";

    users.forEach((user) => {
      const row = document.createElement("tr");
      row.innerHTML = `
        <td>${user.id}</td>
        <td>${user.name}</td>
        <td>${user.email}</td>
        <td>
          <button onclick="deleteUser(${user.id})">Delete</button>
        </td>`;
      tableBody.appendChild(row);
    });
  } catch (error) {
    console.error("Error fetching users:", error);
    document.getElementById("users-table").innerText =
      "Failed to fetch users.";
  }
}

// Delete User
async function deleteUser(userId) {
  const token = localStorage.getItem("jwtToken");
  try {
    const response = await fetch(`http://localhost:8080/api/admin/users/${userId}`, {
      method: "DELETE",
      headers: {
       "Content-Type": "application/json" ,
      },
    });

    if (response.ok) {
      fetchUsers();
    } else {
      alert("Failed to delete user.");
    }
  } catch (error) {
    console.error("Error deleting user:", error);
  }
}
fetchUsers();