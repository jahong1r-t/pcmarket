document.addEventListener('DOMContentLoaded', function() {
    // Sample order data
    const orderData = {
        1: {
            id: '#ORD-001',
            customer: 'John Doe',
            email: 'john.doe@email.com',
            phone: '+1 (555) 123-4567',
            address: '123 Main St, New York, NY 10001',
            date: '2024-01-15',
            total: '$1,299.99',
            items: [
                { name: 'Dell UltraSharp 27" 4K Monitor', quantity: 1, price: 599.99 },
                { name: 'AMD Ryzen 9 7950X', quantity: 1, price: 699.99 }
            ],
            seen: false,
            sold: false
        },
        2: {
            id: '#ORD-002',
            customer: 'Jane Smith',
            email: 'jane.smith@email.com',
            phone: '+1 (555) 987-6543',
            address: '456 Oak Ave, Los Angeles, CA 90210',
            date: '2024-01-14',
            total: '$849.99',
            items: [
                { name: 'ASUS ROG Swift 32" Gaming Monitor', quantity: 1, price: 849.99 }
            ],
            seen: true,
            sold: true
        },
        3: {
            id: '#ORD-003',
            customer: 'Mike Johnson',
            email: 'mike.johnson@email.com',
            phone: '+1 (555) 456-7890',
            address: '789 Pine St, Chicago, IL 60601',
            date: '2024-01-13',
            total: '$2,149.99',
            items: [
                { name: 'NVIDIA RTX 4090', quantity: 1, price: 1599.99 },
                { name: 'Intel Core i9-13900K', quantity: 1, price: 549.99 }
            ],
            seen: false,
            sold: false
        },
        4: {
            id: '#ORD-004',
            customer: 'Sarah Wilson',
            email: 'sarah.wilson@email.com',
            phone: '+1 (555) 321-0987',
            address: '321 Elm St, Miami, FL 33101',
            date: '2024-01-12',
            total: '$599.99',
            items: [
                { name: 'NVIDIA RTX 4070', quantity: 1, price: 599.99 }
            ],
            seen: true,
            sold: false
        },
        5: {
            id: '#ORD-005',
            customer: 'David Brown',
            email: 'david.brown@email.com',
            phone: '+1 (555) 654-3210',
            address: '654 Maple Ave, Seattle, WA 98101',
            date: '2024-01-11',
            total: '$1,899.99',
            items: [
                { name: 'Samsung 980 PRO 2TB NVMe SSD', quantity: 2, price: 199.99 },
                { name: 'NVIDIA RTX 4090', quantity: 1, price: 1499.99 }
            ],
            seen: true,
            sold: true
        }
    };
    
    // Toggle seen status
    window.toggleSeen = function(orderId) {
        const order = orderData[orderId];
        if (order) {
            order.seen = !order.seen;
            updateOrderButtons(orderId, order);
            updateOrderStatus(orderId, order);
        }
    };
    
    // Toggle sold status
    window.toggleSold = function(orderId) {
        const order = orderData[orderId];
        if (order) {
            order.sold = !order.sold;
            updateOrderButtons(orderId, order);
            updateOrderStatus(orderId, order);
        }
    };
    
    // Update order buttons
    function updateOrderButtons(orderId, order) {
        const seenBtn = document.getElementById(`seen-btn-${orderId}`);
        const soldBtn = document.getElementById(`sold-btn-${orderId}`);
        
        if (seenBtn) {
            if (order.seen) {
                seenBtn.className = 'btn btn-sm btn-info';
                seenBtn.innerHTML = '<i class="bi bi-eye-fill"></i> Seen';
            } else {
                seenBtn.className = 'btn btn-sm btn-outline-info';
                seenBtn.innerHTML = '<i class="bi bi-eye"></i> Mark as Seen';
            }
        }
        
        if (soldBtn) {
            if (order.sold) {
                soldBtn.className = 'btn btn-sm btn-success';
                soldBtn.innerHTML = '<i class="bi bi-check-circle-fill"></i> Sold';
            } else {
                soldBtn.className = 'btn btn-sm btn-outline-success';
                soldBtn.innerHTML = '<i class="bi bi-check-circle"></i> Mark as Sold';
            }
        }
    }
    
    // Update order status badge
    function updateOrderStatus(orderId, order) {
        const statusBadge = document.getElementById(`status-${orderId}`);
        const row = document.querySelector(`tr[data-status]`);
        
        if (statusBadge) {
            if (order.sold) {
                statusBadge.className = 'badge bg-success';
                statusBadge.textContent = 'Completed';
                if (row) row.setAttribute('data-status', 'completed');
            } else if (order.seen) {
                statusBadge.className = 'badge bg-info';
                statusBadge.textContent = 'Seen';
                if (row) row.setAttribute('data-status', 'pending');
            } else {
                statusBadge.className = 'badge bg-warning';
                statusBadge.textContent = 'Pending';
                if (row) row.setAttribute('data-status', 'pending');
            }
        }
    }
    
    // Filter orders
    window.filterOrders = function(filter) {
        const rows = document.querySelectorAll('#ordersTableBody tr');
        
        rows.forEach(row => {
            const status = row.getAttribute('data-status');
            
            switch (filter) {
                case 'all':
                    row.style.display = '';
                    break;
                case 'pending':
                    row.style.display = status === 'pending' ? '' : 'none';
                    break;
                case 'completed':
                    row.style.display = status === 'completed' ? '' : 'none';
                    break;
                default:
                    row.style.display = '';
            }
        });
    };
    
    // View order details
    window.viewOrderDetails = function(orderId) {
        const order = orderData[orderId];
        if (order) {
            // Populate modal with order details
            document.getElementById('modal-customer-name').textContent = order.customer;
            document.getElementById('modal-customer-email').textContent = order.email;
            document.getElementById('modal-customer-phone').textContent = order.phone;
            document.getElementById('modal-customer-address').textContent = order.address;
            document.getElementById('modal-order-id').textContent = order.id;
            document.getElementById('modal-order-date').textContent = order.date;
            document.getElementById('modal-order-total').textContent = order.total;
            
            // Set status
            const statusElement = document.getElementById('modal-order-status');
            if (order.sold) {
                statusElement.innerHTML = '<span class="badge bg-success">Completed</span>';
            } else if (order.seen) {
                statusElement.innerHTML = '<span class="badge bg-info">Seen</span>';
            } else {
                statusElement.innerHTML = '<span class="badge bg-warning">Pending</span>';
            }
            
            // Populate order items
            const itemsContainer = document.getElementById('modal-order-items');
            itemsContainer.innerHTML = order.items.map(item => `
                <tr>
                    <td>${item.name}</td>
                    <td>${item.quantity}</td>
                    <td>$${item.price.toFixed(2)}</td>
                    <td>$${(item.price * item.quantity).toFixed(2)}</td>
                </tr>
            `).join('');
            
            // Show modal
            const modal = new bootstrap.Modal(document.getElementById('orderDetailsModal'));
            modal.show();
        }
    };
});