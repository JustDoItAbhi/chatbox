const baseUrl = 'http://localhost:8000';

// Function to send message from client
function sendMessage() {
    const message = document.getElementById('messageInput').value;
    if (message.trim() !== "") {
        fetch(`${baseUrl}/client/send?message=${encodeURIComponent(message)}`)
            .then(response => response.text())
            .then(data => {
                displayMessage('client: ' + message);
                document.getElementById('messageInput').value = "";  // Clear input field
            })
            .catch(error => console.error("Error:", error));
    }
}

// Function to get reply from Abhi
function getReply() {
    fetch(`${baseUrl}/client/get-reply`)
        .then(response => response.text())
        .then(data => {
            document.getElementById('clientReplyText').innerText = data;
        })
        .catch(error => console.error("Error:", error));
}

// Display message in the message box
function displayMessage(message) {
    const messageBox = document.getElementById('clientMessagesBox');
    const messageDiv = document.createElement('p');
    messageDiv.textContent = message;
    messageBox.appendChild(messageDiv);
    messageBox.scrollTop = messageBox.scrollHeight;  // Scroll to the bottom
}
