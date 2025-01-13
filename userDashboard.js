// Fetch Available Spots
async function fetchAvailableSpots() {
  const token = localStorage.getItem("jwtToken"); // Ensure the correct token is used

  try {
    const response = await fetch("http://localhost:8080/api/user/parking/spots", {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    });

    // Check if the response is successful
    if (!response.ok) {
      throw new Error(`Error: ${response.statusText}`);
    }

    // Parse response body as JSON
    const spots = await response.json();

    // Populate spots in UI
    const spotsList = document.getElementById("spots-list");
    const spotDropdown = document.getElementById("spot-id");

    spotsList.innerHTML = "";
    spotDropdown.innerHTML = "";

    spots.forEach((spot) => {
      if (spot.status === "AVAILABLE") {
        // Add to list
        const listItem = document.createElement("li");
        listItem.innerText = `ID: ${spot.id}, Location: ${spot.location}`;
        spotsList.appendChild(listItem);

        // Add to dropdown
        const option = document.createElement("option");
        option.value = spot.id;
        option.innerText = `${spot.location}`;
        spotDropdown.appendChild(option);
      }
    });
  } catch (error) {
    console.error("Error fetching spots:", error);
    document.getElementById("spots-list").innerText =
      "Failed to fetch parking spots.";
  }
}

// Book Parking Spot
document.getElementById("book-spot-form").addEventListener("submit", async (e) => {
  e.preventDefault();

  const spotId = document.getElementById("spot-id").value;
  const bookingDate = document.getElementById("bookingDate").value;
  const bookingTime = document.getElementById("bookingTime").value;
  
  const token = localStorage.getItem("jwtToken");

  try {
    const response = await fetch("http://localhost:8080/api/user/booking/create", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        parkingSpot: { id: spotId },
      
        bookingDate: bookingDate,
        bookingTime: bookingTime,
      }),
    });

    const text = await response.text();
    let result = {};
    try {
      result = text ? JSON.parse(text) : {};
    } catch (error) {
      console.error("Error parsing JSON:", error);
      document.getElementById("booking-message").innerText =
        "Server returned an invalid response.";
      return;
    }

    if (response.ok) {
      document.getElementById("booking-message").innerText =
        result.message || "Booking successful!";
    } else {
      document.getElementById("booking-message").innerText =
        result.message || "Failed to book parking spot";
    }
  } catch (error) {
    console.error("Error:", error);
    document.getElementById("booking-message").innerText =
      "An error occurred. Please try again later.";
  }
});

fetchAvailableSpots();