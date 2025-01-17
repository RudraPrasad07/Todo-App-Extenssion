const apiUrl = "http://localhost:8080/app/todo";

document.addEventListener("DOMContentLoaded", function () {
    // Load tasks initially when the page is loaded
    loadTasks();

  
    document.getElementById("addTask").addEventListener("click", async function () {
        let taskInput = document.getElementById("taskInput").value;

        if (taskInput.trim() === "") return;

        try {
            await fetch(`${apiUrl}/save`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ task: taskInput, status: "Pending" })
            });
            document.getElementById("taskInput").value = "";
            loadTasks(); 
        } catch (error) {
            console.error("Error adding task:", error);
        }
    });
});

async function loadTasks() {
    try {
        let response = await fetch(`${apiUrl}/get`);
        if (!response.ok) throw new Error("Failed to fetch tasks");
        let data = await response.json();

        let taskList = document.getElementById("taskList");
        taskList.innerHTML = ""; 

        data.data.forEach(task => {
            let li = document.createElement("li");
            li.innerHTML = `
			<input type="text" value="${task.task}" id="edit-${task.id}" disabled>
			                <select id="status-${task.id}">
			                    <option value="Pending" ${task.status === "Pending" ? "selected" : ""}>Pending</option>
			                    <option value="In Progress" ${task.status === "In Progress" ? "selected" : ""}>In Progress</option>
			                    <option value="Completed" ${task.status === "Completed" ? "selected" : ""}>Completed</option>
			                </select>
                <button class="edit-btn" data-id="${task.id}">✏️</button>
                <button class="delete-btn" data-id="${task.id}">❌</button>
            `;
            taskList.appendChild(li);
        });

        document.querySelectorAll('.edit-btn').forEach(button => {
            button.addEventListener('click', function () {
                enableEdit(this.getAttribute('data-id'));
            });
        });

        document.querySelectorAll('.update-btn').forEach(button => {
            button.addEventListener('click', function () {
                updateTask(this.getAttribute('data-id'));
            });
        });

        document.querySelectorAll('.delete-btn').forEach(button => {
            button.addEventListener('click', function () {
                deleteTask(this.getAttribute('data-id'));
            });
        });

    } catch (error) {
        console.error("Error loading tasks:", error);
    }
}  

function enableEdit(id) {
    let inputField = document.getElementById(`edit-${id}`);
    inputField.disabled = false; 
    inputField.focus(); 
}

async function updateTask(id) {
    let updatedTask = document.getElementById(`edit-${id}`).value;

    if (updatedTask.trim() === "") return; 

    try {
        await fetch(`${apiUrl}/update/${id}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ task: updatedTask, status: "Updated" })
        });
        loadTasks(); 
    } catch (error) {
        console.error("Error updating task:", error);
    }
}

async function deleteTask(id) {
    try {
        await fetch(`${apiUrl}/Delete/${id}`, { method: "DELETE" });
        loadTasks();
    } catch (error) {
        console.error("Error deleting task:", error);
    }
}
