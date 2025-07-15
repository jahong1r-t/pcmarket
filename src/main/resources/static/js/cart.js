class Cart {
    constructor() {
        this.items = JSON.parse(localStorage.getItem('cart')) || [];
        this.updateCartUI();
    }

    addItem(product) {
        const existingItem = this.items.find(item => item.id === product.id);
        
        if (existingItem) {
            existingItem.quantity += 1;
        } else {
            this.items.push({
                ...product,
                quantity: 1
            });
        }
        
        this.saveCart();
        this.updateCartUI();
        this.showAddToCartFeedback();
    }

    removeItem(productId) {
        this.items = this.items.filter(item => item.id !== productId);
        this.saveCart();
        this.updateCartUI();
    }

    updateQuantity(productId, quantity) {
        const item = this.items.find(item => item.id === productId);
        if (item) {
            if (quantity <= 0) {
                this.removeItem(productId);
            } else {
                item.quantity = quantity;
                this.saveCart();
                this.updateCartUI();
            }
        }
    }

    clearCart() {
        this.items = [];
        this.saveCart();
        this.updateCartUI();
    }

    getTotal() {
        return this.items.reduce((total, item) => total + (item.price * item.quantity), 0);
    }

    getItemCount() {
        return this.items.reduce((total, item) => total + item.quantity, 0);
    }

    saveCart() {
        localStorage.setItem('cart', JSON.stringify(this.items));
    }

    updateCartUI() {
        const cartCount = document.getElementById('cartCount');
        const cartItems = document.getElementById('cartItems');
        const cartTotal = document.getElementById('cartTotal');
        const emptyCart = document.getElementById('emptyCart');

        if (cartCount) {
            cartCount.textContent = this.getItemCount();
        }

        if (cartItems) {
            if (this.items.length === 0) {
                if (emptyCart) emptyCart.style.display = 'block';
                cartItems.innerHTML = `
                    <div class="text-center text-muted">
                        <i class="bi bi-basket2 fs-1"></i>
                        <p>Your cart is empty</p>
                    </div>
                `;
            } else {
                if (emptyCart) emptyCart.style.display = 'none';
                cartItems.innerHTML = this.items.map(item => `
                    <div class="cart-item">
                        <div class="d-flex align-items-center">
                            <div class="cart-item-image me-3">
                                <i class="bi bi-laptop"></i>
                            </div>
                            <div class="flex-grow-1">
                                <h6 class="mb-1">${item.name}</h6>
                                <small class="text-muted">$${item.price.toFixed(2)}</small>
                            </div>
                        </div>
                        <div class="d-flex align-items-center justify-content-between mt-2">
                            <div class="quantity-controls">
                                <button class="quantity-btn" onclick="cart.updateQuantity('${item.id}', ${item.quantity - 1})">
                                    <i class="bi bi-dash"></i>
                                </button>
                                <span class="quantity-display">${item.quantity}</span>
                                <button class="quantity-btn" onclick="cart.updateQuantity('${item.id}', ${item.quantity + 1})">
                                    <i class="bi bi-plus"></i>
                                </button>
                            </div>
                            <button class="btn btn-sm btn-outline-danger" onclick="cart.removeItem('${item.id}')">
                                <i class="bi bi-trash"></i>
                            </button>
                        </div>
                    </div>
                `).join('');
            }
        }

        if (cartTotal) {
            cartTotal.textContent = this.getTotal().toFixed(2);
        }
    }

    showAddToCartFeedback() {
        // Create a temporary notification
        const notification = document.createElement('div');
        notification.className = 'alert alert-success alert-dismissible fade show position-fixed';
        notification.style.cssText = 'top: 80px; right: 20px; z-index: 1055; width: 300px;';
        notification.innerHTML = `
            <i class="bi bi-check-circle me-2"></i>
            Item added to cart!
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        `;
        
        document.body.appendChild(notification);
        
        // Remove notification after 3 seconds
        setTimeout(() => {
            if (notification && notification.parentNode) {
                notification.remove();
            }
        }, 3000);
    }
}

// Initialize cart instance
const cart = new Cart();

// Export for use in other files
window.cart = cart;