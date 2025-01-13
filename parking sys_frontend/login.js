document.getElementById('loginForm').addEventListener('submit', function (e) {
    e.preventDefault();

    let email = document.getElementById('email').value;
    let password = document.getElementById('password').value;

    if (!email || !password) {
        alert('Please fill in all fields!');
        return;
    }

    fetch("http://localhost:8080/api/auth/login", {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ email, password }),
    })
    .then(response => {
        if (!response.ok) {
            // Check for specific error codes
            if (response.status === 401) {
                throw new Error("Invalid email or password.");
            }
            throw new Error("Login failed: " + response.statusText);
        }
        return response.json(); // Parse JSON only if the response is valid
    })
    .then(data => {
        if (data.token && data.role) {
            localStorage.setItem('token', data.token);
            alert('Login successful');
            window.location.href = data.role === 'roleADMIN' ? 'adminDashboard.html' : 'userDashboard.html';
        } else {
            throw new Error("Unexpected server response.");
        }
    })

    .catch(err => {
        console.error(err);
        alert(err.message || 'An unexpected error occurred.');
    });
});
