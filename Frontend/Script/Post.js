document.getElementById('addIngredient').addEventListener('click', function() {
    const ingredientList = document.getElementById('ingredientList');
    const inputGroup = document.createElement('div');
    inputGroup.className = 'input-group mb-2';
    
    const input = document.createElement('input');
    input.type = 'text';
    input.className = 'form-control';
    input.name = 'ingredients';
    input.placeholder = 'Enter ingredient';
    
    const removeButton = document.createElement('button');
    removeButton.className = 'btn btn-outline-danger remove-button';
    removeButton.textContent = '-';
    removeButton.type = 'button';
    removeButton.onclick = function() {
        ingredientList.removeChild(inputGroup);
    };

    const inputGroupAppend = document.createElement('div');
    inputGroupAppend.className = 'input-group-append';
    inputGroupAppend.appendChild(removeButton);

    inputGroup.appendChild(input);
    inputGroup.appendChild(inputGroupAppend);
    ingredientList.appendChild(inputGroup);
});

document.getElementById('addStep').addEventListener('click', function() {
    const stepList = document.getElementById('stepList');
    const inputGroup = document.createElement('div');
    inputGroup.className = 'input-group mb-2';

    const input = document.createElement('input');
    input.type = 'text';
    input.className = 'form-control';
    input.name = 'steps';
    input.placeholder = 'Enter step';

    const removeButton = document.createElement('button');
    removeButton.className = 'btn btn-outline-danger remove-button';
    removeButton.textContent = '-';
    removeButton.type = 'button';
    removeButton.onclick = function() {
        stepList.removeChild(inputGroup);
    };

    const inputGroupAppend = document.createElement('div');
    inputGroupAppend.className = 'input-group-append';
    inputGroupAppend.appendChild(removeButton);

    inputGroup.appendChild(input);
    inputGroup.appendChild(inputGroupAppend);
    stepList.appendChild(inputGroup);
});

document.getElementById('recipeForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent default form submission

    // Show loading spinner
    document.getElementById('errorMessage').style.display = 'none';
    document.getElementById('successMessage').style.display = 'none';
    document.querySelector('.loading').style.display = 'block';

    const userId = sessionStorage.getItem('userId'); // Get userId from sessionStorage
    if (!userId) {
        alert("User not logged in. Please login first.");
        return;
    }

    const itemName = document.getElementById('itemName').value;
    const description = document.getElementById('description').value;
    const category = document.getElementById('category').value;
    const type = document.getElementById('type').value;

    const ingredients = Array.from(document.getElementsByName('ingredients')).map(input => input.value);
    const steps = Array.from(document.getElementsByName('steps')).map(input => input.value);

    const thumbnail = document.getElementById('thumbnail').files[0];
    const video = document.getElementById('video').files[0];
    const images = Array.from(document.getElementById('images').files);

    const formData = new FormData();
    formData.append('itemName', itemName);
    formData.append('description', description);
    formData.append('category', category);
    formData.append('type', type);
    formData.append('ingredients', JSON.stringify(ingredients));
    formData.append('steps', JSON.stringify(steps));
    formData.append('thumbnail', thumbnail);
    if (video) formData.append('video', video);
    images.forEach(image => formData.append('images', image));

    // Submit form data via fetch
    fetch(`http://localhost:8080/api/recipes/create/${userId}`, {
        method: 'POST',
        body: formData
    })
    .then(response => response.text())
    .then(data => {
        document.querySelector('.loading').style.display = 'none';
        if (data === "Recipe post created successfully!") {
            document.getElementById('successMessage').style.display = 'block';
        } else {
            document.getElementById('errorMessage').style.display = 'block';
        }
    })
    .catch(error => {
        document.querySelector('.loading').style.display = 'none';
        document.getElementById('errorMessage').style.display = 'block';
        console.error('Error:', error);
    });
});
