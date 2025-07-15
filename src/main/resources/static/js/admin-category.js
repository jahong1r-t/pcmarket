document.addEventListener('DOMContentLoaded', function() {
    let currentEditId = null;
    
    // Add category function
    window.addCategory = function() {
        const categoryName = document.getElementById('categoryName').value;
        
        if (categoryName.trim()) {
            // In a real app, this would send data to the server
            console.log('Adding category:', categoryName);
            
            // Close modal and reset form
            const modal = bootstrap.Modal.getInstance(document.getElementById('addCategoryModal'));
            modal.hide();
            document.getElementById('addCategoryForm').reset();
            
            // Show success message
            showSuccessMessage('Category added successfully!');
            
            // In a real app, you would reload the table here
            // For demo purposes, we'll just show the success message
        }
    };
    
    // Edit category function
    window.editCategory = function(id, currentName) {
        currentEditId = id;
        document.getElementById('editCategoryName').value = currentName;
        
        const modal = new bootstrap.Modal(document.getElementById('editCategoryModal'));
        modal.show();
    };
    
    // Update category function
    window.updateCategory = function() {
        const categoryName = document.getElementById('editCategoryName').value;
        
        if (categoryName.trim() && currentEditId) {
            // In a real app, this would send data to the server
            console.log('Updating category:', currentEditId, categoryName);
            
            // Close modal and reset form
            const modal = bootstrap.Modal.getInstance(document.getElementById('editCategoryModal'));
            modal.hide();
            document.getElementById('editCategoryForm').reset();
            
            // Show success message
            showSuccessMessage('Category updated successfully!');
            
            currentEditId = null;
        }
    };
    
    // Delete category function
    window.deleteCategory = function(id) {
        if (confirm('Are you sure you want to delete this category?')) {
            // In a real app, this would send delete request to the server
            console.log('Deleting category:', id);
            
            // Show success message
            showSuccessMessage('Category deleted successfully!');
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
    
    // Form submission handlers
    document.getElementById('addCategoryForm').addEventListener('submit', function(e) {
        e.preventDefault();
        addCategory();
    });
    
    document.getElementById('editCategoryForm').addEventListener('submit', function(e) {
        e.preventDefault();
        updateCategory();
    });
});