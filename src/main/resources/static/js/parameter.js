document.addEventListener('DOMContentLoaded', function() {
    let currentEditId = null;
    
    // Add parameter function
    function addParameter() {
        const category = document.getElementById('categorySelect').value;
        const parameterName = document.getElementById('parameterName').value;
        
        if (category && parameterName.trim()) {
            // In a real app, this would send data to the server
            console.log('Adding parameter:', category, parameterName);
            
            // Reset form
            document.getElementById('addParameterForm').reset();
            
            // Show success message
            showSuccessMessage('Parameter added successfully!');
            
            // In a real app, you would reload the table here
        }
    }
    
    // Edit parameter function
    window.editParameter = function(id) {
        currentEditId = id;
        
        // In a real app, you would fetch the parameter data from the server
        // For demo purposes, we'll use placeholder data
        document.getElementById('editParameterName').value = 'Sample Parameter';
        
        const modal = new bootstrap.Modal(document.getElementById('editParameterModal'));
        modal.show();
    };
    
    // Update parameter function
    window.updateParameter = function() {
        const parameterName = document.getElementById('editParameterName').value;
        
        if (parameterName.trim() && currentEditId) {
            // In a real app, this would send data to the server
            console.log('Updating parameter:', currentEditId, parameterName);
            
            // Close modal and reset form
            const modal = bootstrap.Modal.getInstance(document.getElementById('editParameterModal'));
            modal.hide();
            document.getElementById('editParameterForm').reset();
            
            // Show success message
            showSuccessMessage('Parameter updated successfully!');
            
            currentEditId = null;
        }
    };
    
    // Delete parameter function
    window.deleteParameter = function(id) {
        if (confirm('Are you sure you want to delete this parameter?')) {
            // In a real app, this would send delete request to the server
            console.log('Deleting parameter:', id);
            
            // Show success message
            showSuccessMessage('Parameter deleted successfully!');
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
    document.getElementById('addParameterForm').addEventListener('submit', function(e) {
        e.preventDefault();
        addParameter();
    });
    
    document.getElementById('editParameterForm').addEventListener('submit', function(e) {
        e.preventDefault();
        updateParameter();
    });
});