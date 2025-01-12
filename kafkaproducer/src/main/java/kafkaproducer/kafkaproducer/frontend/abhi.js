const baseUrl = 'http://localhost:8080/abhi';  // Make sure this is correct for Abhi's backend

// Function to send message from Abhi
function sendMessage() {
    const message = document.getElementById('messageInput').value;
    if (message.trim() !== "") {
        fetch(`${baseUrl}/send?message=${encodeURIComponent(message)}`)
            .then(response => response.text())
            .then(data => {
                // Call function to display the message in the chatbox
                displayMessage('Abhi: ' + message);
                document.getElementById('messageInput').value = "";  // Clear input field
            })
            .catch(error => {
                console.error("Error:", error);
                alert("Failed to send message!");
            });
    }
}

// Function to get reply from client
function getReply() {
    fetch(`${baseUrl}/get-reply`)
        .then(response => response.text())
        .then(data => {
            document.getElementById('abhiReplyText').innerText = "client: " + data;
        })
        .catch(error => {
            console.error("Error:", error);
            alert("Failed to fetch reply!");
        });
}

// Display message in the message box
function displayMessage(message) {
    const messageBox = document.getElementById('abhiMessagesBox');
    const messageDiv = document.createElement('p');
    messageDiv.textContent = message;
    messageBox.appendChild(messageDiv);
    messageBox.scrollTop = messageBox.scrollHeight;  // Scroll to the bottom to show the latest message
}

// Event listeners for the buttons
document.getElementById('sendButton').addEventListener('click', sendMessage);
document.getElementById('getReplyButton').addEventListener('click', getReply);
