chrome.runtime.onInstalled.addListener(() => {
    console.log('To-Do List extension installed and background script running.');
});


chrome.runtime.onMessage.addListener((message, sender, sendResponse) => {
    if (message.action === 'fetchTasks') {

        fetch('http://localhost:8080/app/todo/get')
            .then(response => response.json())
            .then(data => {
                sendResponse({ tasks: data });
            })
            .catch(err => {
                sendResponse({ error: err.message });
            });
        return true;  
    }
});
