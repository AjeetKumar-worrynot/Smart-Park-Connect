document.getElementById('signupForm').addEventListener('submit', function (e) {
    e.preventDefault();
    let name = document.getElementById('name').value;
    let email = document.getElementById('email').value;
    let contact = document.getElementById('contact').value;
    let password = document.getElementById('password').value;
    let role = document.getElementById('role').value;  // Get selected role

    // Validate input data
    if (!name || !email || !contact || !password || !role) {
        alert('All fields are required!');
        return;
    }

    fetch("http://localhost:8080/api/auth/signup", {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ name, email, contact, password, role })
    })
    .then(response => {
        if (response.status === 201) {
            alert('Signup successful');
            window.location.href = 'login.html';
        } else if (response.status === 409) {
            response.text().then(text => alert(text)); // Email already exists message
        } else {
            response.text().then(text => alert('Signup failed: ' + text)); // Error message from backend
        }
    })
    .catch(err => alert('Error: ' + err));
});
