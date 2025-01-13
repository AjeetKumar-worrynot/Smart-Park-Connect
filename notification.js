// Notification function
function showNotification(message) {
    let notification = document.createElement('div');
    notification.classList.add('notification');
    notification.innerText = message;
    document.body.appendChild(notification);

    setTimeout(() => {
        notification.remove();
    }, 5000);
}
