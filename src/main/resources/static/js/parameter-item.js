document.addEventListener('DOMContentLoaded', function() {
    let currentEditId = null;
    
    // Add parameter item function
    function addParameterItem() {
        const parameter = document.getElementById('parameterSelect').value;
        const itemName = document.getElementById('itemName').value;
        
        if (parameter && itemName.trim()) {
            // In a real app, this would send data to the server
            console.log('Adding parameter item:', parameter, itemName);
            
            // Reset form
            document.getElementById('addParameterItemForm').reset();
            
            // Show success message
            showSuccessMessage('Parameter item added successfully!');
            
            // In a real app, you would reload the table here
        }
    }
    
    // Edit parameter item function
    window.editParameterItem = function(id) {
        currentEditId = id;
        
        // In a real app, you would fetch the parameter item data from the server
        // For demo purposes, we'll use placeholder data
        document.getElementById('editItemName').value = 'Sample Item';
        
        const modal = new bootstrap.Modal(document.getElementById('editParameterItemModal'));
        modal.show();
    };
    
    // Update parameter item function
    window.updateParameterItem = function() {
        const itemName = document.getElementById('editItemName').value;
        
        if (itemName.trim() && currentEditId) {
            // In a real app, this would send data to the server
            console.log('Updating parameter item:', currentEditId, itemName);
            
            // Close modal and reset form
            const modal = bootstrap.Modal.getInstance(document.getElementById('editParameterItemModal'));
            modal.hide();
            document.getElementById('editParameterItemForm').reset();
            
            // Show success message
            showSuccessMessage('Parameter item updated successfully!');
            
            currentEditId = null;
        }
    };
    
    // Delete parameter item function
    window.deleteParameterItem = function(id) {
        if (confirm('Are you sure you want to delete this parameter item?')) {
            // In a real app, this would send delete request to the server
            console.log('Deleting parameter item:', id);
            
            // Show success message
            showSuccessMessage('Parameter item deleted successfully!');
        }
    };
    
    // Show success message
    function showSuccessMessage(message) {
        const alert = document.createElement('div');
        alert.className = 'alert alert-success alert-dismissible fade show position-fixed';
        alert.style.cssText = 'top: 80px; right: 20px; z-index: 1055; width: 300px;';
        alert.innerHTML = `
            <i class="bi bi-check-circle me-2"></i>
            ${message}
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        `;
        
        document.body.appendChild(alert);
        
        setTimeout(() => {
            if (alert && alert.parentNode) {
                alert.remove();
            }
        }, 3000);
    }
    
    // Form submission handler
    document.getElementById('addParameterItemForm').addEventListener('submit', function(e) {
        e.preventDefault();
        addParameterItem();
    });
    
    document.getElementById('editParameterItemForm').addEventListener('submit', function(e) {
        e.preventDefault();
        updateParameterItem();
    });
});