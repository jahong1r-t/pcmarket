document.addEventListener('DOMContentLoaded', function() {
    const categorySelect = document.getElementById('categorySelect');
    const parametersContainer = document.getElementById('parametersContainer');
    const parametersAccordion = document.getElementById('parametersAccordion');
    
    // Sample parameters data for different categories
    const categoryParameters = {
        monitors: [
            {
                name: 'Brand',
                items: ['Dell', 'HP', 'ASUS', 'Acer', 'Samsung', 'LG']
            },
            {
                name: 'Size',
                items: ['21.5 inch', '24 inch', '27 inch', '32 inch', '34 inch', '49 inch']
            },
            {
                name: 'Resolution',
                items: ['Full HD', '2K', '4K', '5K', '8K']
            },
            {
                name: 'Panel Type',
                items: ['IPS', 'VA', 'TN', 'OLED']
            }
        ],
        cpus: [
            {
                name: 'Brand',
                items: ['Intel', 'AMD']
            },
            {
                name: 'Cores',
                items: ['4', '6', '8', '12', '16', '24', '32']
            },
            {
                name: 'Socket',
                items: ['LGA 1700', 'LGA 1200', 'AM4', 'AM5']
            },
            {
                name: 'TDP',
                items: ['65W', '95W', '125W', '150W', '170W']
            }
        ],
        ram: [
            {
                name: 'Brand',
                items: ['Corsair', 'G.Skill', 'Kingston', 'Crucial', 'Teamgroup']
            },
            {
                name: 'Capacity',
                items: ['8GB', '16GB', '32GB', '64GB', '128GB']
            },
            {
                name: 'Type',
                items: ['DDR4', 'DDR5']
            },
            {
                name: 'Speed',
                items: ['2400 MHz', '2666 MHz', '3200 MHz', '3600 MHz', '4000 MHz', '5600 MHz']
            }
        ],
        gpus: [
            {
                name: 'Brand',
                items: ['NVIDIA', 'AMD']
            },
            {
                name: 'Memory',
                items: ['4GB', '6GB', '8GB', '12GB', '16GB', '24GB']
            },
            {
                name: 'Memory Type',
                items: ['GDDR5', 'GDDR6', 'GDDR6X']
            },
            {
                name: 'Interface',
                items: ['PCIe 3.0', 'PCIe 4.0', 'PCIe 5.0']
            }
        ],
        storage: [
            {
                name: 'Brand',
                items: ['Samsung', 'Western Digital', 'Seagate', 'Crucial', 'Intel']
            },
            {
                name: 'Capacity',
                items: ['256GB', '512GB', '1TB', '2TB', '4TB', '8TB']
            },
            {
                name: 'Type',
                items: ['SSD', 'HDD', 'NVMe', 'SATA']
            },
            {
                name: 'Interface',
                items: ['SATA III', 'NVMe PCIe 3.0', 'NVMe PCIe 4.0']
            }
        ],
        motherboards: [
            {
                name: 'Brand',
                items: ['ASUS', 'MSI', 'Gigabyte', 'ASRock', 'EVGA']
            },
            {
                name: 'Socket',
                items: ['LGA 1700', 'LGA 1200', 'AM4', 'AM5']
            },
            {
                name: 'Form Factor',
                items: ['ATX', 'Micro-ATX', 'Mini-ITX', 'E-ATX']
            },
            {
                name: 'Chipset',
                items: ['Z790', 'Z690', 'B660', 'B550', 'X570', 'B650']
            }
        ]
    };
    
    // Handle category selection
    categorySelect.addEventListener('change', function() {
        const selectedCategory = this.value;
        
        if (selectedCategory && categoryParameters[selectedCategory]) {
            loadParametersForCategory(selectedCategory);
        } else {
            parametersContainer.innerHTML = '<p class="text-muted">Select a category to see available parameters.</p>';
            parametersAccordion.innerHTML = '';
        }
    });
    
    // Load parameters for selected category
    function loadParametersForCategory(category) {
        const parameters = categoryParameters[category];
        
        // Update parameters container
        parametersContainer.innerHTML = `
            <p class="text-muted">Configure the following parameters for ${category}:</p>
            <div class="row">
                ${parameters.map(param => `
                    <div class="col-md-6 mb-2">
                        <span class="badge bg-primary">${param.name}</span>
                    </div>
                `).join('')}
            </div>
        `;
        
        // Update parameters accordion
        parametersAccordion.innerHTML = parameters.map((param, index) => `
            <div class="accordion-item">
                <h2 class="accordion-header">
                    <button class="accordion-button ${index === 0 ? '' : 'collapsed'}" type="button" data-bs-toggle="collapse" data-bs-target="#param-${index}">
                        ${param.name}
                    </button>
                </h2>
                <div id="param-${index}" class="accordion-collapse collapse ${index === 0 ? 'show' : ''}" data-bs-parent="#parametersAccordion">
                    <div class="accordion-body">
                        <div class="row">
                            ${param.items.map(item => `
                                <div class="col-md-6 col-lg-4 mb-2">
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" value="${item}" id="param-${index}-${item.replace(/\s+/g, '-')}">
                                        <label class="form-check-label" for="param-${index}-${item.replace(/\s+/g, '-')}">
                                            ${item}
                                        </label>
                                    </div>
                                </div>
                            `).join('')}
                        </div>
                    </div>
                </div>
            </div>
        `).join('');
    }
    
    // Handle form submission
    document.getElementById('addProductForm').addEventListener('submit', function(e) {
        e.preventDefault();
        
        const formData = new FormData(this);
        const productData = {
            category: document.getElementById('categorySelect').value,
            name: document.getElementById('productName').value,
            description: document.getElementById('productDescription').value,
            price: parseFloat(document.getElementById('productPrice').value),
            quantity: parseInt(document.getElementById('productQuantity').value),
            parameters: {}
        };
        
        // Collect selected parameters
        const checkboxes = document.querySelectorAll('#parametersAccordion input[type="checkbox"]:checked');
        checkboxes.forEach(checkbox => {
            const parameterName = checkbox.closest('.accordion-item').querySelector('.accordion-button').textContent.trim();
            if (!productData.parameters[parameterName]) {
                productData.parameters[parameterName] = [];
            }
            productData.parameters[parameterName].push(checkbox.value);
        });
        
        // In a real app, this would send data to the server
        console.log('Product data:', productData);
        
        // Show success message
        showSuccessMessage('Product added successfully!');
        
        // Reset form
        this.reset();
        parametersContainer.innerHTML = '<p class="text-muted">Select a category to see available parameters.</p>';
        parametersAccordion.innerHTML = '';
    });
    
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
});