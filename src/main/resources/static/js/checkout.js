document.addEventListener('DOMContentLoaded', function() {
    const orderSummary = document.getElementById('orderSummary');
    const orderTotals = document.getElementById('orderTotals');
    const emptyCheckout = document.getElementById('emptyCheckout');
    const placeOrderBtn = document.getElementById('placeOrderBtn');
    const checkoutForm = document.getElementById('checkoutForm');
    
    // Load cart items on page load
    loadOrderSummary();
    
    // Load order summary
    function loadOrderSummary() {
        if (cart.items.length === 0) {
            emptyCheckout.style.display = 'block';
            orderTotals.style.display = 'none';
            placeOrderBtn.disabled = true;
            return;
        }
        
        emptyCheckout.style.display = 'none';
        orderTotals.style.display = 'block';
        placeOrderBtn.disabled = false;
        
        // Display cart items
        orderSummary.innerHTML = cart.items.map(item => `
            <div class="d-flex align-items-center mb-3 pb-3 border-bottom">
                <div class="cart-item-image me-3">
                    <i class="bi bi-laptop"></i>
                </div>
                <div class="flex-grow-1">
                    <h6 class="mb-1">${item.name}</h6>
                    <small class="text-muted">Quantity: ${item.quantity}</small>
                </div>
                <div class="text-end">
                    <strong>$${(item.price * item.quantity).toFixed(2)}</strong>
                </div>
            </div>
        `).join('');
        
        // Calculate totals
        const subtotal = cart.getTotal();
        const shipping = subtotal > 100 ? 0 : 9.99;
        const tax = subtotal * 0.08; // 8% tax
        const total = subtotal + shipping + tax;
        
        document.getElementById('subtotal').textContent = subtotal.toFixed(2);
        document.getElementById('shipping').textContent = shipping.toFixed(2);
        document.getElementById('tax').textContent = tax.toFixed(2);
        document.getElementById('finalTotal').textContent = total.toFixed(2);
    }
    
    // Place order function
    window.placeOrder = function() {
        if (!checkoutForm.checkValidity()) {
            checkoutForm.reportValidity();
            return;
        }
        
        // Collect form data
        const orderData = {
            customer: {
                firstName: document.getElementById('firstName').value,
                lastName: document.getElementById('lastName').value,
                email: document.getElementById('email').value,
                phone: document.getElementById('phone').value,
                address: document.getElementById('address').value,
                city: document.getElementById('city').value,
                state: document.getElementById('state').value,
                zipCode: document.getElementById('zipCode').value,
                notes: document.getElementById('notes').value
            },
            items: cart.items,
            totals: {
                subtotal: cart.getTotal(),
                shipping: cart.getTotal() > 100 ? 0 : 9.99,
                tax: cart.getTotal() * 0.08,
                total: cart.getTotal() + (cart.getTotal() > 100 ? 0 : 9.99) + (cart.getTotal() * 0.08)
            },
            orderDate: new Date().toISOString()
        };
        
        // In a real app, this would send the order to the server
        console.log('Order placed:', orderData);
        
        // Generate order ID
        const orderId = 'ORD-' + Date.now().toString().slice(-6);
        
        // Show success modal
        document.getElementById('orderIdDisplay').textContent = orderId;
        const modal = new bootstrap.Modal(document.getElementById('orderSuccessModal'));
        modal.show();
        
        // Clear cart
        cart.clearCart();
        
        // Reset form
        checkoutForm.reset();
        
        // Update order summary
        loadOrderSummary();
    };
    
    // Form validation
    checkoutForm.addEventListener('submit', function(e) {
        e.preventDefault();
        placeOrder();
    });
    
    // Enable/disable place order button based on cart contents
    if (cart.items.length === 0) {
        placeOrderBtn.disabled = true;
    }
});