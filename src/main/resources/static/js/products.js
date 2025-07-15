const products = [
    // Monitors
    {
        id: 'monitor-1',
        name: 'Dell UltraSharp 27" 4K Monitor',
        price: 599.99,
        category: 'monitors',
        brand: 'Dell',
        description: 'Professional 27-inch 4K monitor with USB-C connectivity and excellent color accuracy.',
        image: 'https://images.pexels.com/photos/1029757/pexels-photo-1029757.jpeg?auto=compress&cs=tinysrgb&w=400',
        specs: {
            size: '27 inch',
            resolution: '4K',
            brand: 'Dell'
        }
    },
    {
        id: 'monitor-2',
        name: 'ASUS ROG Swift 32" Gaming Monitor',
        price: 899.99,
        category: 'monitors',
        brand: 'ASUS',
        description: 'High-performance gaming monitor with 144Hz refresh rate and G-Sync support.',
        image: 'https://images.pexels.com/photos/777001/pexels-photo-777001.jpeg?auto=compress&cs=tinysrgb&w=400',
        specs: {
            size: '32 inch',
            resolution: '2K',
            brand: 'ASUS'
        }
    },
    {
        id: 'monitor-3',
        name: 'HP EliteDisplay 24" Business Monitor',
        price: 299.99,
        category: 'monitors',
        brand: 'HP',
        description: 'Professional business monitor with adjustable stand and eye-care technology.',
        image: 'https://images.pexels.com/photos/1029757/pexels-photo-1029757.jpeg?auto=compress&cs=tinysrgb&w=400',
        specs: {
            size: '24 inch',
            resolution: 'Full HD',
            brand: 'HP'
        }
    },

    // CPUs
    {
        id: 'cpu-1',
        name: 'Intel Core i9-13900K',
        price: 589.99,
        category: 'cpus',
        brand: 'Intel',
        description: 'High-performance desktop processor with 24 cores and 32 threads.',
        image: 'https://images.pexels.com/photos/159378/pcb-circuit-board-computer-159378.jpeg?auto=compress&cs=tinysrgb&w=400',
        specs: {
            cores: '24',
            threads: '32',
            brand: 'Intel'
        }
    },
    {
        id: 'cpu-2',
        name: 'AMD Ryzen 9 7950X',
        price: 699.99,
        category: 'cpus',
        brand: 'AMD',
        description: 'Advanced AMD processor with 16 cores and exceptional performance.',
        image: 'https://images.pexels.com/photos/159378/pcb-circuit-board-computer-159378.jpeg?auto=compress&cs=tinysrgb&w=400',
        specs: {
            cores: '16',
            threads: '32',
            brand: 'AMD'
        }
    },
    {
        id: 'cpu-3',
        name: 'Intel Core i7-13700K',
        price: 419.99,
        category: 'cpus',
        brand: 'Intel',
        description: 'Powerful gaming and productivity processor with excellent performance.',
        image: 'https://images.pexels.com/photos/159378/pcb-circuit-board-computer-159378.jpeg?auto=compress&cs=tinysrgb&w=400',
        specs: {
            cores: '16',
            threads: '24',
            brand: 'Intel'
        }
    },

    // RAM
    {
        id: 'ram-1',
        name: 'Corsair Vengeance LPX 32GB DDR4',
        price: 159.99,
        category: 'ram',
        brand: 'Corsair',
        description: 'High-performance DDR4 memory kit with excellent overclocking potential.',
        image: 'https://images.pexels.com/photos/163100/circuit-circuit-board-resistor-computer-163100.jpeg?auto=compress&cs=tinysrgb&w=400',
        specs: {
            capacity: '32GB',
            type: 'DDR4',
            brand: 'Corsair'
        }
    },
    {
        id: 'ram-2',
        name: 'G.Skill Trident Z RGB 16GB DDR4',
        price: 89.99,
        category: 'ram',
        brand: 'G.Skill',
        description: 'Premium RGB memory with stunning lighting effects and high performance.',
        image: 'https://images.pexels.com/photos/163100/circuit-circuit-board-resistor-computer-163100.jpeg?auto=compress&cs=tinysrgb&w=400',
        specs: {
            capacity: '16GB',
            type: 'DDR4',
            brand: 'G.Skill'
        }
    },
    {
        id: 'ram-3',
        name: 'Kingston Fury Beast 64GB DDR5',
        price: 299.99,
        category: 'ram',
        brand: 'Kingston',
        description: 'Next-generation DDR5 memory for extreme performance and future-proofing.',
        image: 'https://images.pexels.com/photos/163100/circuit-circuit-board-resistor-computer-163100.jpeg?auto=compress&cs=tinysrgb&w=400',
        specs: {
            capacity: '64GB',
            type: 'DDR5',
            brand: 'Kingston'
        }
    },

    // Graphics Cards
    {
        id: 'gpu-1',
        name: 'NVIDIA RTX 4090',
        price: 1599.99,
        category: 'gpus',
        brand: 'NVIDIA',
        description: 'Ultimate gaming graphics card with exceptional 4K performance.',
        image: 'https://images.pexels.com/photos/159378/pcb-circuit-board-computer-159378.jpeg?auto=compress&cs=tinysrgb&w=400',
        specs: {
            memory: '24GB',
            type: 'GDDR6X',
            brand: 'NVIDIA'
        }
    },
    {
        id: 'gpu-2',
        name: 'AMD Radeon RX 7900 XTX',
        price: 999.99,
        category: 'gpus',
        brand: 'AMD',
        description: 'High-performance graphics card with excellent ray tracing capabilities.',
        image: 'https://images.pexels.com/photos/159378/pcb-circuit-board-computer-159378.jpeg?auto=compress&cs=tinysrgb&w=400',
        specs: {
            memory: '24GB',
            type: 'GDDR6',
            brand: 'AMD'
        }
    },
    {
        id: 'gpu-3',
        name: 'NVIDIA RTX 4070',
        price: 599.99,
        category: 'gpus',
        brand: 'NVIDIA',
        description: 'Great gaming graphics card with excellent price-to-performance ratio.',
        image: 'https://images.pexels.com/photos/159378/pcb-circuit-board-computer-159378.jpeg?auto=compress&cs=tinysrgb&w=400',
        specs: {
            memory: '12GB',
            type: 'GDDR6X',
            brand: 'NVIDIA'
        }
    },

    // Storage
    {
        id: 'storage-1',
        name: 'Samsung 980 PRO 2TB NVMe SSD',
        price: 199.99,
        category: 'storage',
        brand: 'Samsung',
        description: 'High-speed NVMe SSD with excellent performance for gaming and productivity.',
        image: 'https://images.pexels.com/photos/163100/circuit-circuit-board-resistor-computer-163100.jpeg?auto=compress&cs=tinysrgb&w=400',
        specs: {
            capacity: '2TB',
            type: 'NVMe',
            brand: 'Samsung'
        }
    },
    {
        id: 'storage-2',
        name: 'WD Black 4TB HDD',
        price: 129.99,
        category: 'storage',
        brand: 'Western Digital',
        description: 'High-capacity hard drive perfect for mass storage and backups.',
        image: 'https://images.pexels.com/photos/163100/circuit-circuit-board-resistor-computer-163100.jpeg?auto=compress&cs=tinysrgb&w=400',
        specs: {
            capacity: '4TB',
            type: 'HDD',
            brand: 'Western Digital'
        }
    },
    {
        id: 'storage-3',
        name: 'Crucial MX4 1TB SATA SSD',
        price: 79.99,
        category: 'storage',
        brand: 'Crucial',
        description: 'Reliable SATA SSD with good performance and value for money.',
        image: 'https://images.pexels.com/photos/163100/circuit-circuit-board-resistor-computer-163100.jpeg?auto=compress&cs=tinysrgb&w=400',
        specs: {
            capacity: '1TB',
            type: 'SSD',
            brand: 'Crucial'
        }
    },

    // Motherboards
    {
        id: 'motherboard-1',
        name: 'ASUS ROG Strix Z790-E',
        price: 399.99,
        category: 'motherboards',
        brand: 'ASUS',
        description: 'Premium motherboard with excellent features for high-end gaming builds.',
        image: 'https://images.pexels.com/photos/159378/pcb-circuit-board-computer-159378.jpeg?auto=compress&cs=tinysrgb&w=400',
        specs: {
            socket: 'LGA 1700',
            chipset: 'Z790',
            brand: 'ASUS'
        }
    },
    {
        id: 'motherboard-2',
        name: 'MSI MAG B650 TOMAHAWK',
        price: 219.99,
        category: 'motherboards',
        brand: 'MSI',
        description: 'Solid mid-range motherboard with good features and reliability.',
        image: 'https://images.pexels.com/photos/159378/pcb-circuit-board-computer-159378.jpeg?auto=compress&cs=tinysrgb&w=400',
        specs: {
            socket: 'AM5',
            chipset: 'B650',
            brand: 'MSI'
        }
    }
];

// Function to get products by category
function getProductsByCategory(category) {
    return products.filter(product => product.category === category);
}

// Function to get product by ID
function getProductById(id) {
    return products.find(product => product.id === id);
}

// Function to search products
function searchProducts(query) {
    return products.filter(product => 
        product.name.toLowerCase().includes(query.toLowerCase()) ||
        product.description.toLowerCase().includes(query.toLowerCase()) ||
        product.brand.toLowerCase().includes(query.toLowerCase())
    );
}

// Function to filter products
function filterProducts(category, filters) {
    let filteredProducts = category ? getProductsByCategory(category) : products;
    
    if (filters.brands && filters.brands.length > 0) {
        filteredProducts = filteredProducts.filter(product => 
            filters.brands.includes(product.brand)
        );
    }
    
    if (filters.priceRange) {
        filteredProducts = filteredProducts.filter(product => {
            const price = product.price;
            switch (filters.priceRange) {
                case '0-100':
                    return price >= 0 && price <= 100;
                case '100-500':
                    return price > 100 && price <= 500;
                case '500-1000':
                    return price > 500 && price <= 1000;
                case '1000+':
                    return price > 1000;
                default:
                    return true;
            }
        });
    }
    
    return filteredProducts;
}

// Function to create product card HTML
function createProductCard(product) {
    return `
        <div class="col-lg-3 col-md-4 col-sm-6 mb-4">
            <div class="card product-card h-100">
                <div class="product-image">
                    <i class="bi bi-laptop"></i>
                </div>
                <div class="card-body d-flex flex-column">
                    <h5 class="product-title">${product.name}</h5>
                    <p class="product-description">${product.description}</p>
                    <div class="mt-auto">
                        <div class="product-price">$${product.price.toFixed(2)}</div>
                        <button class="btn btn-primary btn-add-to-cart w-100" onclick="addToCart('${product.id}')">
                            <i class="bi bi-cart-plus me-2"></i>Add to Cart
                        </button>
                    </div>
                </div>
            </div>
        </div>
    `;
}

// Function to add product to cart
function addToCart(productId) {
    const product = getProductById(productId);
    if (product) {
        cart.addItem(product);
    }
}

// Export products for use in other files
window.products = products;
window.getProductsByCategory = getProductsByCategory;
window.getProductById = getProductById;
window.searchProducts = searchProducts;
window.filterProducts = filterProducts;
window.createProductCard = createProductCard;
window.addToCart = addToCart;