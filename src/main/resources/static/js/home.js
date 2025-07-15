document.addEventListener('DOMContentLoaded', function() {
    const productGrid = document.getElementById('productGrid');
    
    // Load featured products (first 8 products)
    function loadFeaturedProducts() {
        const featuredProducts = products.slice(0, 8);
        productGrid.innerHTML = featuredProducts.map(product => createProductCard(product)).join('');
    }
    
    loadFeaturedProducts();
});