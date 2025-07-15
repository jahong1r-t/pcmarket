document.addEventListener('DOMContentLoaded', function() {
    const productGrid = document.getElementById('productGrid');
    const categoryTitle = document.getElementById('categoryTitle');
    
    // Get category from URL parameters
    const urlParams = new URLSearchParams(window.location.search);
    const category = urlParams.get('category');
    
    let currentProducts = [];
    
    // Load products for the selected category
    function loadCategoryProducts() {
        if (category) {
            currentProducts = getProductsByCategory(category);
            categoryTitle.textContent = category.charAt(0).toUpperCase() + category.slice(1);
        } else {
            currentProducts = products;
            categoryTitle.textContent = 'All Products';
        }
        
        displayProducts(currentProducts);
    }
    
    // Display products in the grid
    function displayProducts(productsToShow) {
        if (productsToShow.length === 0) {
            productGrid.innerHTML = `
                <div class="col-12">
                    <div class="text-center py-5">
                        <i class="bi bi-search fs-1 text-muted"></i>
                        <h3 class="text-muted mt-3">No products found</h3>
                        <p class="text-muted">Try adjusting your filters or search criteria.</p>
                    </div>
                </div>
            `;
            return;
        }
        
        productGrid.innerHTML = productsToShow.map(product => createProductCard(product)).join('');
    }
    
    // Sort products
    window.sortProducts = function(sortBy) {
        let sortedProducts = [...currentProducts];
        
        switch (sortBy) {
            case 'name':
                sortedProducts.sort((a, b) => a.name.localeCompare(b.name));
                break;
            case 'price':
                sortedProducts.sort((a, b) => a.price - b.price);
                break;
            default:
                break;
        }
        
        displayProducts(sortedProducts);
    };
    
    // Clear all filters
    window.clearFilters = function() {
        document.querySelectorAll('.filter-checkbox').forEach(checkbox => {
            checkbox.checked = false;
        });
        applyFilters();
    };
    
    // Apply filters
    function applyFilters() {
        const filters = {
            brands: [],
            priceRange: null,
            ram: [],
            storage: []
        };
        
        // Get selected brands
        document.querySelectorAll('#brandFilter .filter-checkbox:checked').forEach(checkbox => {
            filters.brands.push(checkbox.value);
        });
        
        // Get selected price range
        const priceCheckbox = document.querySelector('#priceFilter .filter-checkbox:checked');
        if (priceCheckbox) {
            filters.priceRange = priceCheckbox.value;
        }
        
        // Get selected RAM options
        document.querySelectorAll('#ramFilter .filter-checkbox:checked').forEach(checkbox => {
            filters.ram.push(checkbox.value);
        });
        
        // Get selected storage options
        document.querySelectorAll('#storageFilter .filter-checkbox:checked').forEach(checkbox => {
            filters.storage.push(checkbox.value);
        });
        
        const filteredProducts = filterProducts(category, filters);
        displayProducts(filteredProducts);
    }
    
    // Add event listeners to filter checkboxes
    document.querySelectorAll('.filter-checkbox').forEach(checkbox => {
        checkbox.addEventListener('change', applyFilters);
    });
    
    // Initial load
    loadCategoryProducts();
});