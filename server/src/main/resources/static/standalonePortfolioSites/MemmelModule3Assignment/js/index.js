// IPO
console.group("M3: Assignment - Restaurant Filter: IPO");
console.group("Input");
console.log("Restaurant Name (text)");
console.log("Restaurant Address (text)");
console.log("Has gluten-free options (chip)");
console.log("Has vegan options (chip)");
console.log("Is local (chip)");
console.log("Run filter (button-submit)");
console.groupEnd();
console.group("Process");
console.log("Generate html cards for the restaurant based on hardcoded data into restaurant objects");
console.log("Upon submitting the form that contains all the inputs, filter all the restaurant objects to the ones that match the filter criteria");
console.groupEnd();
console.group("Output");
console.log("Hide all the cards, then only display the ones that match the filter");
console.log("If there are no restaurants, inform the viewer so that they know it did not break, but just has nothing matching");
console.groupEnd();
console.groupEnd();

$(document).ready(function () {
    const localIcon = `<img src="/images/distance.svg" alt="Local Icon" title="Restaurant is local">`;
    const veganIcon = `<img src="/images/vegan.svg" alt="Vegan Icon" title="Restaurant has vegan options">`;
    // const glutenIcon = `<img src="/images/gluten.svg" alt="Gluten Free Icon" title="Restaurant has gluten-free options">`;
    const glutenIcon = `<img class="gluten-free-icon" src="https://img.icons8.com/ios/50/no-gluten.png" alt="Gluten Free Icon" title="Restaurant has gluten-free options">`;

    const $filterOptionInputs = $('#restaurant_filter_selector > section > input');
    const $filterOptionSelects = $('#restaurant_filter_selector > section > .addRemove');
    const filterNoResults = $('#no-results')[0];

    const $filterForm = $('form#restaurant_filter_selector');
    // https://api.jquery.com/event.preventDefault/
    $filterForm.on('submit', e => {
        e.preventDefault();
        updateRestaurantFilterResults();
    });

    class Restaurant {
        constructor(name, address, glutenFreeAvailable, veganAvailable, isLocal) {
            this.name = name;
            this.address = address;
            this.glutenFreeAvailable = glutenFreeAvailable;
            this.veganAvailable = veganAvailable;
            this.isLocal = isLocal;

            this.generatedId = "restaurant_card_" + stringToHash(name + "-" + address);

            this.htmlCard = this.generateHtml();
            this.setCardVisabality(false);
        }

        generateHtml() {
            const restaurantCard = document.createElement("li");
            restaurantCard.classList.add("card");
            restaurantCard.id = this.generatedId;
            restaurantCard.innerHTML =
                `
                    <h3 class="card-title">${this.name}</h3>
                    <ul class="card-icons">
                    </ul>
                    <p class="card-address">${this.address}</p>
                `;
            const cardIconList = restaurantCard.querySelector(".card-icons");
            let hasIcon = false;

            if (this.glutenFreeAvailable) {
                this.addIconToCard(cardIconList, glutenIcon);
                hasIcon = true;
            }
            if (this.veganAvailable) {
                this.addIconToCard(cardIconList, veganIcon);
                hasIcon = true;
            }
            if (this.isLocal) {
                this.addIconToCard(cardIconList, localIcon);
                hasIcon = true;
            }
            if (!hasIcon) {
                this.addIconToCard(cardIconList, `<div class="placeholder-empty-icons"></div>`);
            }

            // Type: HtmlElement
            return restaurantCard;
        }

        getHtmlCard() {
            return this.htmlCard;
        }

        addIconToCard(cardIconList, htmlString) {
            const icon = document.createElement("li");
            icon.innerHTML = htmlString;
            cardIconList.appendChild(icon);
        }

        getGeneratedId() {
            return this.generatedId;
        }

        setCardVisabality(visiable) {
            if (visiable) {
                this.htmlCard.classList.remove('hidden');
            } else {
                this.htmlCard.classList.add('hidden');
            }
        }

    };

    const constructedRestaurants = [
        new Restaurant("Macho Meals", "337 St Paul Ave.", false, false, false),
        new Restaurant("Veganic Corner", "24 S. Buckingham Road", true, true, true),
        new Restaurant("Sherryl Meals", "7897 Glen Eagles Court", true, false, false),
        new Restaurant("Salad Heaven", "593 Harvey Street", false, true, true),
        new Restaurant("Root Shoots", "18 South Chapel Street", true, true, true),
        new Restaurant("Grill Moguls", "40 State Rd.", true, false, false),
        new Restaurant("NovaFood", "9026 Jones Rd.", true, true, false),
        new Restaurant("Sangli", "426 Summerhouse Ave.", false, false, false),
        new Restaurant("Lavoya Diner", "83 Beacon Lane", true, false, true),
        new Restaurant("Andisova", "474 Mayfield Ave.", true, false, false)
    ];



    // Place all restaurants in the results section
    const resultsElement = document.getElementById("results");
    constructedRestaurants.forEach(restaurant => {
        resultsElement.appendChild(restaurant.getHtmlCard());
        restaurant.setCardVisabality(true);
    });

    // Filter restaurants
    function filterRestaurants(restaurants, name, address, glutenFreeAvailable, veganAvailable, isLocal) {
        const filteredRestaurants = [];
        name = name.toLowerCase();

        restaurants.forEach(restaurant => {
            // Got the .includes from https://www.w3schools.com/Jsref/jsref_includes.asp
            if ((name == "" || restaurant.name.toLowerCase().includes(name.toLowerCase())) &&
                (address == "" || restaurant.address.toLowerCase().includes(address.toLowerCase())) &&
                (!glutenFreeAvailable || restaurant.glutenFreeAvailable) &&
                (!veganAvailable || restaurant.veganAvailable) &&
                (!isLocal || restaurant.isLocal)
            ) {
                // Restaurant passed all filters
                filteredRestaurants.push(restaurant);
            }
        });

        return filteredRestaurants;
    }

    function clearRestaurants() {
        constructedRestaurants.forEach(restaurant => {
            restaurant.setCardVisabality(false);
        });
    }

    // User Input Filters
    const $selectedFilterNameElement = $('#restaurant_input_filter__name');
    const $selectedFilterAddressElement = $('#restaurant_input_filter__address');
    const $selectedFilterGlutenFreeElement = $('#filter_select__gluten_free');
    const $selectedFilterVeganElement = $('#filter_select__vegan');
    const $selectedFilterLocalElement = $('#filter_select__local');

    function updateRestaurantFilterResults() {
        // Used https://learn.jquery.com/using-jquery-core/faq/how-do-i-pull-a-native-dom-element-from-a-jquery-object/ to learn how to esily get the htmlElementObject from the jqueryObject
        const filteredRestaurants = filterRestaurants(constructedRestaurants,
            $selectedFilterNameElement[0].value,
            $selectedFilterAddressElement[0].value,
            $selectedFilterGlutenFreeElement[0].classList.contains("selected"),
            $selectedFilterVeganElement[0].classList.contains("selected"),
            $selectedFilterLocalElement[0].classList.contains("selected")
        );

        clearRestaurants();
        filteredRestaurants.forEach(restaurant => {
            restaurant.setCardVisabality(true);
        });
        if (filteredRestaurants.length == 0) {
            filterNoResults.classList.remove("hidden");
        } else {
            filterNoResults.classList.add("hidden");
        }
    }

    updateRestaurantFilterResults();

    // Set filter background statuses
    $filterOptionSelects.each($select => {
        $(this).on('click', e => {
            e.target.classList.toggle('selected');
        });
    });

    // stringToHash used from https://www.geeksforgeeks.org/how-to-create-hash-from-string-in-javascript/#
    function stringToHash(string) {
        let hash = 0;
        if (string.length == 0) return hash;
        for (i = 0; i < string.length; i++) {
            char = string.charCodeAt(i);
            hash = ((hash < 5) - hash) + char;
            hash = hash & hash;
        }

        return hash;
    }

});