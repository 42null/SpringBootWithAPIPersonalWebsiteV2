$(function () {
    //Use the following Data to prepopulate your Arrays

    class Employee {
        constructor(id, firstName, lastName, email, department, startedDate, terminatedDate) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.department = department;
            this.startedDate = startedDate;
            this.terminatedDate = terminatedDate;

            this.htmlCard = null;
        }
        formatDate(date) {
            if (date == null) {
                return "";
            }
            return (date.getMonth() + 1) + "-" + (date.getDate() + 1) + "-" + date.getFullYear();
        }
        generateHtml() {
            const employeeCard = document.createElement("li");
            employeeCard.classList.add("card");
            employeeCard.innerHTML =
                `
                    <header>
                        <span class="material-symbols-outlined">
                            badge
                        </span>
                        <h5 class="card-title">${this.firstName} ${this.lastName}</h5>
                    </header>
                    <section>
                        <p class="card-email"><a href="mailto:${this.email}">${this.email}</a></p>
                        <p>ID: <span class="card-id">#${this.id}</span></p>
                        <p>Started: <span class="card-hire-date">${this.formatDate(this.startedDate)}</span></p>
                        <p>Ended: <span class="card-termination-date${(this.terminatedDate === null ? " hidden" : "")}">${this.formatDate(this.terminatedDate)}</span></p>
                    </section>
                    <footer>
                        <p class="card-color-bar ${this.department.toLowerCase()}"><span class="material-symbols-outlined"></span></p>
                    </footer>
                `;
            return employeeCard;// Type: HtmlElement
        }

        setCardVisabality(visiable) {
            if (visiable) {
                this.htmlCard.classList.remove('hidden');
            } else {
                this.htmlCard.classList.add('hidden');
            }
        }
        getHtmlCard() {
            if (this.htmlCard === null) {
                this.htmlCard = this.generateHtml();
            }
            return this.htmlCard;
        }
    };

    class Supplier {
        constructor(id, companyName, zipCode, contactFirstName, contactLastName, contactEmail, lastDeliveryDate, isActive) {
            this.id = id;
            this.companyName = companyName;
            this.zipCode = zipCode;
            this.contactFirstName = contactFirstName;
            this.contactLastName = contactLastName;
            this.contactEmail = contactEmail;
            this.lastDeliveryDate = lastDeliveryDate;
            this.isActive = isActive;

            this.htmlCard = null;
        }
        formatDate(date) {
            if (date == null) {
                return "";
            }
            return (date.getMonth() + 1) + "-" + (date.getDate() + 1) + "-" + date.getFullYear();
        }
        generateHtml() {
            const employeeCard = document.createElement("li");
            employeeCard.classList.add("card");
            employeeCard.innerHTML =
                `
                    <header>
                        <span class="material-symbols-outlined">
                            local_shipping
                        </span>
                        <h5 class="card-title">${this.companyName}</h5>
                    </header>
                    <section>

                        <p class="card-email"><a href="mailto:${this.contactEmail}">${this.contactEmail}</a></p>
                        <p>ID: <span class="card-id">${this.id}</span></p>
                        <p>Zip Code: <span class="card-zip">#${this.zipCode}</span></p>
                        <p class="card-names">${this.contactFirstName} ${this.contactLastName}</p>

                        <p>Last Delivery: <span class="card-last-delivery-date">${this.formatDate(this.lastDeliveryDate)}</span></p>

                    </section>
                    <footer>
                        <p class="card-color-bar card-is-active-show ${(this.isActive) ? "is-active" : "not-active"}"></p>
                    </footer>
                `;
            return employeeCard;// Type: HtmlElement
        }

        setCardVisabality(visiable) {
            if (visiable) {
                this.htmlCard.classList.remove('hidden');
            } else {
                this.htmlCard.classList.add('hidden');
            }
        }
        getHtmlCard() {
            if (this.htmlCard === null) {
                this.htmlCard = this.generateHtml();
            }
            return this.htmlCard;
        }
    };

    class Customer {
        constructor(id, firstName, lastName, email, phoneNumber, optedOutOfMailing, lastPurchase) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.phoneNumber = phoneNumber;
            this.optedOutOfMailing = optedOutOfMailing;
            this.lastPurchase = lastPurchase;

            this.htmlCard = null;
        }
        formatDate(date) {
            if (date == null) {
                return "";
            }
            return (date.getMonth() + 1) + "-" + (date.getDate() + 1) + "-" + date.getFullYear();
        }
        generateHtml() {
            const employeeCard = document.createElement("li");
            employeeCard.classList.add("card");
            employeeCard.innerHTML =
                `
                    <header>
                        <span class="material-symbols-outlined">
                            person
                        </span>
                        <h5 class="card-title">${this.firstName} ${this.lastName}</h5>
                    </header>
                    <section>
                        <p class="card-email"><a href="mailto:${this.email}">${this.email}</a></p>
                        <p class="card-phone"><a href="tel:${this.phoneNumber}">${this.phoneNumber}</a></p>
                        <p>ID: <span class="card-id">${this.id}</span></p>

                        <p>Last Purchase: <span class="card-last-purchase">${this.formatDate(this.lastPurchase)}</span></p>
                    </section>
                    <footer>
                        <p class="card-color-bar card-on-mailing-list-show ${(this.optedOutOfMailing) ? "opted-out" : "on-mailing"}"><span class="material-symbols-outlined"></span></p>
                    </footer>
                `;
            return employeeCard;// Type: HtmlElement
        }

        setCardVisabality(visiable) {
            if (visiable) {
                this.htmlCard.classList.remove('hidden');
            } else {
                this.htmlCard.classList.add('hidden');
            }
        }
        getHtmlCard() {
            if (this.htmlCard === null) {
                this.htmlCard = this.generateHtml();
            }
            return this.htmlCard;
        }
    };






    const employees = [
        new Employee(38647, 'Patricia', 'Barber', 'pbarber@company.com', 'Sales', new Date('2005-01-23'), new Date('2015-01-01')),
        new Employee(72102, 'Kimberley', 'Berry', 'kberry@company.com', 'Manufacturing', new Date('2007-06-21'), new Date('2016-05-01')),
        new Employee(36693, 'Burton', 'Miles', 'bmiles@company.com', 'Manufacturing', new Date('2009-03-01'), new Date('2017-03-15')),
        new Employee(58000, 'Rosa', 'Smith', 'rsmith@company.com', 'Sales', new Date('2012-09-26'), new Date('2018-08-15')),
        new Employee(54929, 'Jane', 'Pruitt', 'jpruitt@company.com', 'Manufacturing', new Date('2015-04-01'), null),
        new Employee(24612, 'Regina', 'Suarez', 'rsuarez@company.com', 'Manufacturing', new Date('2018-11-01'), null),
        new Employee(68392, 'Monroe', 'Carr', 'mcarr@company.com', 'Manufacturing', new Date('2019-01-11'), null),
        new Employee(73604, 'Lonny', 'Contreras', 'lcontreras@company.com', 'HR', new Date('2020-02-24'), null),
        new Employee(37640, 'Alba', 'Guzman', 'aguzman@company.com', 'Manufacturing', new Date('2021-03-10'), null),
        new Employee(61036, 'Drew', 'Cowan', 'dcowan@company.com', 'Manufacturing', new Date('2021-04-04'), null),
        new Employee(33211, 'Ike', 'Wyatt', 'iwyatt@company.com', 'HR', new Date('2021-05-06'), null)
    ];

    const suppliers = [
        new Supplier('ACMED34234', 'ACME Inc.', '34234', 'John', 'Doe', 'jdoe@acmeinc.com', new Date('2021-08-01'), false),
        new Supplier('BESTC23532', 'Best Company', '23532', 'Jane', 'Smith', 'jsmith@bestcompany.com', new Date('2022-02-15'), true),
        new Supplier('COOLS53462', 'Cool Corp', '53462', 'Bob', 'Johnson', 'bjohnson@coolcorp.com', new Date('2022-05-20'), true),
        new Supplier('DELTA23743', 'Delta LLC', '23743', 'Alice', 'Brown', 'abrown@deltallc.com', new Date('2023-01-01'), true),
        new Supplier('EXCEL34865', 'Excel Enterprises', '34865', 'Tom', 'Wilson', 'twilson@excelenterprises.com', new Date('2023-06-30'), true),
        new Supplier('FRESH34235', 'Fresh Foods', '34235', 'Sara', 'Lee', 'slee@freshfoods.com', new Date('2020-05-01'), false),
    ];

    const customers = [
        new Customer(9690528, 'Rickey', 'Key', 'rickeykey@sample.com', '578-708-7817', false, new Date('2023-08-11')),
        new Customer(3985939, 'Clarissa', 'Singleton', 'clarissasingleton@sample.com', '339-593-1528', true, new Date('2023-07-01')),
        new Customer(6268069, 'Domenic', 'Maldonado', 'domenicmaldonado@sample.com', '959-706-4190', false, new Date('2023-06-14')),
        new Customer(3868672, 'Isiah', 'Lowery', 'isiahlowery@test.com', '945-715-3043', true, new Date('2022-05-17')),
        new Customer(5880281, 'Evangeline', 'Figueroa', 'evangelinefigueroa@example.com', '526-803-4658', false, new Date('2023-04-01')),
        new Customer(5916088, 'Mabel', 'Prince', 'mabelprince@test.com', '422-381-8753', true, new Date('2022-03-15')),
        new Customer(5405271, 'Bret', 'Melendez', 'bretmelendez@example.com', '877-575-2516', false, new Date('2023-02-18')),
        new Customer(9239813, 'Robby', 'Haley', 'robbyhaley@trial.com', '601-387-5361', false, new Date('2021-01-21')),
        new Customer(2861338, 'Michelle', 'Lawson', 'michellelawson@test.com', '347-376-8539', false, new Date('2020-12-30')),
    ];

    const employeeCardsList = [], supplierCardsList = [], customerCardsList = [];
    const resultsElementEmployees = document.getElementById("results-employees");
    const resultsElementSuppliers = document.getElementById("results-suppliers");
    const resultsElementCustomers = document.getElementById("results-customers");

    const $allAddForms = $('.add-form');
    function hideAllAddForms() {
        $allAddForms.addClass('hidden');
    }

    // https://stackoverflow.com/a/5081471 - concat arrays
    // const allTypesList = employees.concat(suppliers, customers);
    employees.forEach(typeInstance => {
        //I have it set up sorta like a singleton where if it doesn't already exist, it will generate it new
        resultsElementEmployees.appendChild(typeInstance.getHtmlCard());
        typeInstance.setCardVisabality(true);
    });
    suppliers.forEach(typeInstance => {
        //I have it set up sorta like a singleton where if it doesn't already exist, it will generate it new
        resultsElementSuppliers.appendChild(typeInstance.getHtmlCard());
        typeInstance.setCardVisabality(true);
    });
    customers.forEach(typeInstance => {
        //I have it set up sorta like a singleton where if it doesn't already exist, it will generate it new
        resultsElementCustomers.appendChild(typeInstance.getHtmlCard());
        typeInstance.setCardVisabality(true);
    });

    function unFilterAllCardsResults(){
        // allTypesList.forEach(typeInstance => {
        employees.forEach(typeInstance => {
            typeInstance.setCardVisabality(true);
        });
        suppliers.forEach(typeInstance => {
            typeInstance.setCardVisabality(true);
        });
        customers.forEach(typeInstance => {
            typeInstance.setCardVisabality(true);
        });
    }

// APPLY TO ALL INSTANCES
    // Applies to every single instance of .chipGroup
    const $chips = $('.chipGroup button.chip.addRemove');
    $chips.each(function (i, element) {
        $(this).closest('button').on('click', function (e) {
            e.target.classList.toggle('active');
        });
    });

    const $forms = $('form');
    $forms.on('submit', e => {
        e.preventDefault();
    });

    // Make all *'s in labels red for required
    $('label').each(function (i, labelElement) {
        if(labelElement.textContent.endsWith('*:')){
            labelElement.textContent = labelElement.textContent.substring(0, labelElement.textContent.length-2);
            labelElement.innerHTML += `<span class="requiredTextStar">*</span>:`
        }
    });

    // Specific to specific forms
    const $addNewEmployeeForm = $('#add-employee-form');
    const $addNewSupplierForm = $('#add-supplier-form');
    const $addNewCustomerForm = $('#add-customer-form');

    // Add Employee
    $addNewEmployeeForm.on('submit', function(){
        let dateOrNull = new Date($('#employee-form__termination-date').val());
        if(dateOrNull == "Invalid Date"){
            dateOrNull = null;
        }
        const newAddEmployee = new Employee(
            $('#employee-form__id').val(),
            $('#employee-form__first_name').val(),
            $('#employee-form__last_name').val(),
            $('#employee-form__email').val(),
            $('#employee-form__department').val(),
            new Date($('#employee-form__hire-date').val()),
            dateOrNull
        );

        resultsElementEmployees.appendChild(newAddEmployee.getHtmlCard());
        newAddEmployee.setCardVisabality(true);

        // https://thewebdev.info/2021/03/21/how-to-prepend-values-to-a-javascript-array/
        employees.unshift(newAddEmployee);
        $addNewEmployeeForm[0].reset();
        unFilterAllCardsResults();
    });
    // Add Supplier
    $addNewSupplierForm.on('submit', function(){
        const newAddSupplier = new Supplier(
            $('#supplier-form__id').val(),
            $('#supplier-form__name').val(),
            $('#supplier-form__zip').val(),
            $('#supplier-form__contact-first_name').val(),
            $('#supplier-form__contact-last_name').val(),
            $('#supplier-form__contact-email').val(),
            new Date($('#supplier-form__last-delivery-date').val()),
            $('#supplier-form__is-active').val(),
        );

        resultsElementSuppliers.appendChild(newAddSupplier.getHtmlCard());
        newAddSupplier.setCardVisabality(true);

        employees.unshift(newAddSupplier);
        $addNewEmployeeForm[0].reset();

        unFilterAllCardsResults();
    });
    // Add Customer
    $addNewCustomerForm.on('submit', function(){
        const newAddCustomer = new Customer(
            $('#customer-form__id').val(),
            $('#customer-form__first_name').val(),
            $('#customer-form__last_name').val(),
            $('#customer-form__email').val(),
            $('#customer-form__phone').val(),
            // $('#customer-form__opted-out').val(),
            $('#customer-form__opted-out').is(':checked'),// See if value is checked, .val was just returning "on" https://stackoverflow.com/a/2204253/16041898
            new Date($('#customer-form__last-purchase-date').val()),
        );

        resultsElementCustomers.appendChild(newAddCustomer.getHtmlCard());
        newAddCustomer.setCardVisabality(true);

        employees.unshift(newAddCustomer);

        unFilterAllCardsResults();
    });



    // Specific to the filter options
    const $adjustableFilterFields = $('#filtering-fields>.options-by-type-holder');
    const chipsSelected = [true, true, true];

    const $filterChips = $('#filter-options-sidebar .chipGroup button.chip.addRemove');
    $filterChips.each(function (i, element) {
        const $chip = $(this).closest('button');
        $chip.on('click', function (e) {
            const newValue = $chip.hasClass('active');
            let wasPicked = true;
            switch ($chip.attr('name')) {
                case "type_employee":
                    chipsSelected[0] = newValue;
                    break;
                case "type_supplier":
                    chipsSelected[1] = newValue;
                    break;
                case "type_customer":
                    chipsSelected[2] = newValue;
                    break;
                default:
                    wasPicked = false;
                    break;
            }
            if (wasPicked) {
                updateFilterSectionVisibalitiy();
            }
        });
    });

    function updateFilterSectionVisibalitiy() {
        $adjustableFilterFields.each(function (i, element) {
            // console.log(element);
            $element = $(element);
            $element.addClass('disabled');
            if ((chipsSelected[0] && $element.hasClass('employee-option')) || (chipsSelected[1] && $element.hasClass('supplier-option')) || (chipsSelected[2] && $element.hasClass('customer-option'))) {
                $element.removeClass('disabled');
            }
        });

    }

    const $tabs = $('.tabs');
    $tabs.on('click', e => {
        const $target = $(e.target);
        if (!$target.is('.helper-tab-bar-fill') && $target.is('.tab')) {//Don't target non-tabs such as it's parent
            const $tabGroup = $target.parent();//Doing it this way so that I only target tabs in the tab group

            // https://stackoverflow.com/a/47634236 - Find is query selector but for children inside jQuery object
            $tabGroup.find('li.tab:not(.helper-tab-bar-fill)').removeClass('active');

            $target.addClass('active');
            hideAllAddForms();
            // https://stackoverflow.com/a/8852688
            $("#" + $target.attr('name')).removeClass('hidden');
        }
    });

    // Filter submit button
    $filterForm = $('#filter-fields');
    const $elementFieldSearch_onlyActiveRoles = $('#filter_form-only_active_roles');

    const $elementFieldSearch_ID = $('#filter_form-id');
    const $elementFieldSearch_email = $('#filter_form-email');
    const $elementFieldSearch_firstName = $('#filter_form-first_name');
    const $elementFieldSearch_lastName = $('#customer_form-last_name');
    const $elementFieldSearch_department = $('#filter_form-department');
    const $elementFieldSearch_hireDate = $('#filter_form-hire_date');
    const $elementFieldSearch_fireDate = $('#filter_form-termination_date');
    const $elementFieldSearch_supplierName = $('#supplier_form-name');
    const $elementFieldSearch_zip = $('#filter_form-zip');
    const $elementFieldSearch_lastDeliveryDate = $('#filter_form-last_delivery');
    const $elementFieldSearch_active = $('#filter_form-active');
    const $elementFieldSearch_phoneNumber = $('#filter_form-phone_number');
    const $elementFieldSearch_mailingList = $('#customer-filter_form-mailing_list');
    const $elementFieldSearch_lastPurchaseDate = $('#filter_form-last_purchase');

    $filterForm.on('submit', function(e){
        const onlyActiveRoles = $elementFieldSearch_onlyActiveRoles.is(':checked');

        const searchFieldValue_ID = $elementFieldSearch_ID.val();
        const searchFieldValue_email = $elementFieldSearch_email.val();
        const searchFieldValue_firstName = $elementFieldSearch_firstName.val();
        const searchFieldValue_lastName = $elementFieldSearch_lastName.val();
        const searchFieldValue_department = $elementFieldSearch_department.val();
        const searchFieldValue_hireDate = new Date($elementFieldSearch_hireDate.val());
        const searchFieldValue_fireDate = new Date($elementFieldSearch_fireDate.val());
        const searchFieldValue_supplierName = $elementFieldSearch_supplierName.val();
        const searchFieldValue_zip = $elementFieldSearch_zip.val();
        const searchFieldValue_lastDeliveryDate = new Date($elementFieldSearch_lastDeliveryDate.val());
        const searchFieldValue_active = $elementFieldSearch_active.is(':checked');
        const searchFieldValue_phoneNumber = $elementFieldSearch_phoneNumber.val();
        const searchFieldValue_mailingList = $elementFieldSearch_mailingList.is(':checked');
        const searchFieldValue_lastPurchaseDate = new Date($elementFieldSearch_lastPurchaseDate.val());
    

        // allTypesList.forEach(typeInstance => {
        // Hide all

        employees.forEach(employee => {
            if(chipsSelected[0]
                && (!onlyActiveRoles || employee.terminatedDate === null)
                && ((employee.id+"").includes(searchFieldValue_ID))
                && (employee.email.includes(searchFieldValue_email))
                && (employee.firstName.includes(searchFieldValue_firstName))
                && (employee.lastName.includes(searchFieldValue_lastName))

                && (employee.department.includes(searchFieldValue_department)) //(Any) is "" so this works
                // https://www.geeksforgeeks.org/compare-two-dates-using-javascript/
                && (searchFieldValue_hireDate == 'Invalid Date' || employee.startedDate.getTime() == searchFieldValue_hireDate.getTime())
                && (onlyActiveRoles || searchFieldValue_fireDate == 'Invalid Date' || employee.terminatedDate.getTime() == searchFieldValue_fireDate.getTime())
            ){
                employee.setCardVisabality(true);
            }else{
                employee.setCardVisabality(false);
            }
        });

        suppliers.forEach(supplier => {

            if(chipsSelected[1]
                && (!onlyActiveRoles || supplier.isActive)
                && ((supplier.id+"").includes(searchFieldValue_ID))
                && (supplier.contactEmail.includes(searchFieldValue_email))
                && (supplier.contactFirstName.includes(searchFieldValue_firstName))
                && (supplier.contactLastName.includes(searchFieldValue_lastName))

                && (supplier.companyName.includes(searchFieldValue_supplierName))
                && (supplier.zipCode.includes(searchFieldValue_zip))
                && (searchFieldValue_lastDeliveryDate == 'Invalid Date' || supplier.lastDeliveryDate.getTime() == searchFieldValue_lastDeliveryDate.getTime())
                // && (onlyActiveRoles || supplier.isActive == searchFieldValue_active)
            ){
                supplier.setCardVisabality(true);
            }else{
                supplier.setCardVisabality(false);
            }
        });

        const oneYearAgo = new Date().setFullYear(new Date().getFullYear() - 1);//Learned from https://bobbyhadz.com/blog/javascript-date-add-years
        customers.forEach(customer => {
            if(chipsSelected[2]
                && (!onlyActiveRoles || (!customer.optedOutOfMailing && customer.lastPurchase.getTime() > oneYearAgo))
                && ((customer.id+"").includes(searchFieldValue_ID))
                && (customer.email.includes(searchFieldValue_email))
                && (customer.firstName.includes(searchFieldValue_firstName))
                && (customer.lastName.includes(searchFieldValue_lastName))

                && (customer.phoneNumber.includes(searchFieldValue_phoneNumber))
                // && (customer.optedOutOfMailing == searchFieldValue_mailingList)
                && (onlyActiveRoles || searchFieldValue_lastPurchaseDate == 'Invalid Date' || customer.lastPurchase.getTime() == searchFieldValue_lastPurchaseDate.getTime())
            ){
                customer.setCardVisabality(true);
            }else{
                customer.setCardVisabality(false);
            }
        });


    });



    // Run once on page load to startup values
    updateFilterSectionVisibalitiy();
    $filterForm.submit();

});