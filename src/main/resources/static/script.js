document.addEventListener('DOMContentLoaded', function() {
    // Sample product data
    const products = [
        {
            id: 1,
            name: "Gaming Laptop Pro",
            price: 1299.99,
            originalPrice: 1499.99,
            category: "laptops",
            image: "https://via.placeholder.com/300x200?text=Gaming+Laptop+Pro",
            description: "High-performance gaming laptop with RTX 3080"
        },
        {
            id: 2,
            name: "Ultra Slim Notebook",
            price: 899.99,
            category: "laptops",
            image: "https://via.placeholder.com/300x200?text=Ultra+Slim+Notebook",
            description: "Lightweight and powerful ultrabook"
        },
        {
            id: 3,
            name: "Gaming Desktop Extreme",
            price: 1999.99,
            originalPrice: 2299.99,
            category: "desktops",
            image: "https://via.placeholder.com/300x200?text=Gaming+Desktop+Extreme",
            description: "VR-ready gaming PC with liquid cooling"
        },
        {
            id: 4,
            name: "Office PC Bundle",
            price: 699.99,
            category: "desktops",
            image: "https://via.placeholder.com/300x200?text=Office+PC+Bundle",
            description: "Complete office solution with monitor"
        },
        {
            id: 5,
            name: "Mechanical Keyboard",
            price: 89.99,
            category: "peripherals",
            image: "https://via.placeholder.com/300x200?text=Mechanical+Keyboard",
            description: "RGB mechanical keyboard with Cherry MX switches"
        },
        {
            id: 6,
            name: "4K Gaming Monitor",
            price: 499.99,
            originalPrice: 599.99,
            category: "peripherals",
            image: "https://via.placeholder.com/300x200?text=4K+Gaming+Monitor",
            description: "144Hz 4K monitor with HDR"
        },
        {
            id: 7,
            name: "Wireless Mouse",
            price: 39.99,
            category: "peripherals",
            image: "https://via.placeholder.com/300x200?text=Wireless+Mouse",
            description: "Ergonomic wireless mouse with 12 buttons"
        },
        {
            id: 8,
            name: "SSD 1TB",
            price: 109.99,
            category: "components",
            image: "https://via.placeholder.com/300x200?text=SSD+1TB",
            description: "NVMe SSD with 3500MB/s read speed"
        }
    ];

    // Cart state
    let cart = [];

    // DOM elements
    const productGrid = document.getElementById('productGrid');
    const cartItems = document.getElementById('cartItems');
    const cartTotal = document.getElementById('cartTotal');
    const cartCounter = document.getElementById('cartCounter');
    const categoryItems = document.querySelectorAll('.category-item');

    // Initialize the page
    renderProducts(products);
    setupEventListeners();

    // Render products to the page
    function renderProducts(productsToRender) {
        productGrid.innerHTML = '';

        if (productsToRender.length === 0) {
            productGrid.innerHTML = '<div class="col-12"><p class="text-center text-muted">No products found</p></div>';
            return;
        }

        productsToRender.forEach(product => {
            const discount = product.originalPrice ?
                Math.round(((product.originalPrice - product.price) / product.originalPrice) * 100) :
                0;

            const productCard = document.createElement('div');
            productCard.className = 'col-md-4 col-sm-6 mb-4';
            productCard.innerHTML = `
                <div class="card product-card h-100">
                    ${product.originalPrice ? `<span class="discount-badge">-${discount}%</span>` : ''}
                    <img src="${product.image}" class="card-img-top product-img" alt="${product.name}">
                    <div class="card-body">
                        <h5 class="card-title">${product.name}</h5>
                        <p class="card-text">${product.description}</p>
                        <div class="d-flex justify-content-between align-items-center">
                            <div>
                                <span class="price">$${product.price.toFixed(2)}</span>
                                ${product.originalPrice ? `<span class="original-price ms-2">$${product.originalPrice.toFixed(2)}</span>` : ''}
                            </div>
                            <button class="btn btn-primary btn-sm add-to-cart" data-id="${product.id}">
                                <i class="bi bi-cart-plus"></i> Add
                            </button>
                        </div>
                    </div>
                </div>
            `;

            productGrid.appendChild(productCard);
        });
    }

    // Set up event listeners
    function setupEventListeners() {
        // Category filter
        categoryItems.forEach(item => {
            item.addEventListener('click', function() {
                categoryItems.forEach(i => i.classList.remove('active'));
                this.classList.add('active');

                const category = this.dataset.category;
                if (category === 'all') {
                    renderProducts(products);
                } else {
                    const filteredProducts = products.filter(p => p.category === category);
                    renderProducts(filteredProducts);
                }
            });
        });

        // Add to cart buttons (delegated event)
        productGrid.addEventListener('click', function(e) {
            if (e.target.classList.contains('add-to-cart') || e.target.closest('.add-to-cart')) {
                const button = e.target.classList.contains('add-to-cart') ?
                    e.target : e.target.closest('.add-to-cart');
                const productId = parseInt(button.dataset.id);
                addToCart(productId);
            }
        });

        // Cart item quantity changes (delegated event)
        cartItems.addEventListener('click', function(e) {
            const itemId = e.target.closest('.cart-item')?.dataset.id;
            if (!itemId) return;

            if (e.target.classList.contains('increase')) {
                updateCartItemQuantity(itemId, 1);
            } else if (e.target.classList.contains('decrease')) {
                updateCartItemQuantity(itemId, -1);
            } else if (e.target.classList.contains('remove-item')) {
                removeFromCart(itemId);
            }
        });
    }

    // Cart functions
    function addToCart(productId) {
        const product = products.find(p => p.id === productId);
        if (!product) return;

        const existingItem = cart.find(item => item.id === productId);

        if (existingItem) {
            existingItem.quantity += 1;
        } else {
            cart.push({
                id: product.id,
                name: product.name,
                price: product.price,
                quantity: 1,
                image: product.image
            });
        }

        updateCart();

        // Show a toast notification (you can implement this if you want)
        alert(`${product.name} added to cart!`);
    }

    function removeFromCart(productId) {
        cart = cart.filter(item => item.id !== productId);
        updateCart();
    }

    function updateCartItemQuantity(productId, change) {
        const item = cart.find(item => item.id === productId);
        if (!item) return;

        item.quantity += change;

        if (item.quantity < 1) {
            removeFromCart(productId);
        } else {
            updateCart();
        }
    }

    function updateCart() {
        // Update cart counter
        const totalItems = cart.reduce((sum, item) => sum + item.quantity, 0);
        cartCounter.textContent = totalItems;

        // Update cart items list
        if (cart.length === 0) {
            cartItems.innerHTML = '<p class="text-muted">Your cart is empty</p>';
            cartTotal.textContent = '$0.00';
            return;
        }

        cartItems.innerHTML = '';
        let total = 0;

        cart.forEach(item => {
            const itemTotal = item.price * item.quantity;
            total += itemTotal;

            const cartItem = document.createElement('div');
            cartItem.className = 'cart-item';
            cartItem.dataset.id = item.id;
            cartItem.innerHTML = `
                <div class="d-flex">
                    <img src="${item.image}" width="60" height="60" class="me-3" alt="${item.name}">
                    <div class="flex-grow-1">
                        <h6>${item.name}</h6>
                        <div class="d-flex justify-content-between align-items-center">
                            <span>$${item.price.toFixed(2)}</span>
                            <button class="btn btn-sm btn-outline-danger remove-item">
                                <i class="bi bi-trash"></i>
                            </button>
                        </div>
                        <div class="quantity-control mt-2">
                            <button class="btn btn-sm decrease">-</button>
                            <input type="text" class="form-control form-control-sm" value="${item.quantity}" readonly>
                            <button class="btn btn-sm increase">+</button>
                        </div>
                        <div class="text-end mt-2">
                            <strong>$${itemTotal.toFixed(2)}</strong>
                        </div>
                    </div>
                </div>
            `;

            cartItems.appendChild(cartItem);
        });

        // Update total
        cartTotal.textContent = `$${total.toFixed(2)}`;
    }
});