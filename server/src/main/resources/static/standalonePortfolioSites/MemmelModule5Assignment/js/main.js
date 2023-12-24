$(function () {
    // Fab + FabBar
    const $fabOpenButton     = $('#fab-open');
    const $fabOpenSideBar    = $('#adder-menu');
    const $fabSideBarOptions = $('#adder-options');

    // Dialog
    const $adderDialog      = $('#adder-dialog');
    const $adderDialog_name = $('#add-category-name');
    const $adderDialog_icon = $('#category-icon-display');
    const $adderDialog_form = $adderDialog.find('form');
        // Dialog > inputs
    const $adderDialog_input_name     = $adderDialog_form.find('#dialog-form-name');
    const $adderDialog_input_quantity = $adderDialog_form.find('#dialog-form-quantity');

    // Results
    const $lists          = $('#lists-of-content > .items-list');
    const $isEmptyMessage = $('#emptyMessage');

    // Filter
    const $filterInputText    = $('#filter-input-text');
    const $filterInputChecked = $('#filter-checked');

    // All's
    const $forms = $('form');
    const $chips = $('.chipGroup button.chip.addRemove');


    // Data
    // const produceList = [];
    // const dairyList = [];
    // const bakingList = [];
    // const grainsList = [];
    // const meatList = [];
    // const otherList = [];
    const categoriesItemsList = [];

    // State holders
    let lastClickWasAdderDialog = false;
// Objects
    class GroceryItem {
        constructor(category, name, quantity, checkedOff) {
            this.category = category;
            this.name = name;
            this.quantity = quantity;
            this.checkedOff = checkedOff;

            this.htmlCard = null;
        }
        generateHtml() {
            const itemCard = document.createElement("li");
            itemCard.classList.add("card");
            itemCard.innerHTML =
                `
                    <input type="checkbox" name="checkedOff" ${this.checkedOff? 'checked' : ''}>
                    <section>
                        <h4>${this.name}</h4>
                    </section>
                    <section class="quantity">
                        <input type="number" value="${this.quantity}" min="1">
                    </section>
                    <button name="delete">
                        <span class="material-symbols-outlined delete"></span>
                    </button>
                `;
            if(this.checkedOff){
                itemCard.classList.add('checked-off');
            }
            return itemCard; // Type: HtmlElement
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
    }

    // https://stackoverflow.com/a/3314828 - detect clicking outside to hide, using to close dialog if clicked off
    $(document).click(function() {
        if(!lastClickWasAdderDialog){
            $adderDialog.addClass('hidden');
        }else{
            lastClickWasAdderDialog = false;
        }
    });
    $adderDialog.click(function(event) {
        event.stopPropagation();
    });


// Event Triggers
    // Toggle fab side menu
    $fabOpenButton.on('click', function(e){
        if(!$fabOpenSideBar.hasClass('open')){
            $fabSideBarOptions.css({
                opacity: 0
            });
            $fabSideBarOptions.animate({//Used from pg#335 from book "Javascript & JQuery"
                opacity: 1
            }, 1000, function(){
            });
        }
        $fabOpenSideBar.toggleClass('open');
        $fabSideBarOptions.toggleClass('hidden');
    });

    
    
    // Sidebar button clicks to open adderDialog
    $fabSideBarOptions.on('click', function(e){
        const $triggeredOption = $(e.target.closest('button'));
        openAdderDialog($triggeredOption.attr('name'));
        // Close sidebar
        $fabSideBarOptions.css({
            opacity: 1
        });
        $fabSideBarOptions.animate({//Used from pg#335 from book "Javascript & JQuery"
            opacity: 0
        }, 1000, function(){
            $fabOpenSideBar.removeClass('open');
        });
        $fabSideBarOptions.addClass('hidden');
        lastClickWasAdderDialog = true;
    });

    // Dialog add new list itme
    $adderDialog_form.on('submit', function(e){
        const categoryName     = $adderDialog_input_name.val();
        const categoryQuantity = Number($adderDialog_input_quantity.val());

        $adderDialog_form.trigger("reset");
        addNewItem($adderDialog_name.text(), categoryName, categoryQuantity);
        updateVisibalityForEmptyCategorysElementList();
        $adderDialog.addClass('hidden');
    });

    // Filter items - data
    $chips.on('click', function(e){
        e.target.classList.toggle('active');
        triggerSearch();
    });
    // Filter items - data
    $filterInputText.on('keyup', function(e){
        triggerSearch();
    });
    $filterInputChecked.on('click', function(e){
        triggerSearch();
    });

    // As a 
    function triggerSearch(){
        const activeChips = [];

        $chips.each(function(i, chipElement){
            const $chip = $(chipElement).closest('button');
            if($chip.hasClass('active')){
                activeChips.push($chip.attr('name'));
            }
        });

        const searchText = $filterInputText.val();
        // https://stackoverflow.com/a/2204253
        const hideCheckedOff = $filterInputChecked.is(":checked");
        filterResults(activeChips, searchText, hideCheckedOff);
    }


    // ALL's
    // Disable all default actions for all forms
    $forms.on('submit', e => { e.preventDefault(); });


// Methods to be called by event triggers/other code

    // GRAPHICAL
    function openAdderDialog(category){
        $adderDialog.removeClass('hidden');
        $adderDialog_name.text(category);
        $adderDialog_icon[0].className = "material-symbols-outlined "+category; //https://stackoverflow.com/a/15040335 - used to clear the classList in a single line instead of a loop;
    }

    function updateVisibalityForEmptyCategorysElementList(){
        $lists.each(function(i, sectionElement) {
            const $sectionElement = $(sectionElement);
            if($sectionElement.find('ul.results-list > li').length > 0){//Has results
                $sectionElement.removeClass('hidden');
            }else{//Dosn't have results
                $sectionElement.addClass('hidden');
            }
        });

    };

    function filterResults(filterAllowCategories, searchText, hideCheckedOff){
        searchText = searchText.toLowerCase();
        const filteredItems = categoriesItemsList.filter(function(groceryItem){    
            if(groceryItem.name.toLowerCase().includes(searchText) &&
               filterAllowCategories.indexOf(groceryItem.category) != -1 &&
               (!groceryItem.checkedOff || !hideCheckedOff)
            ){
                return true
            }
            return false;
        });

        categoriesItemsList.forEach(groceryItem => {
            groceryItem.setCardVisabality(false);
        });
        filteredItems.forEach(groceryItem => {
            groceryItem.setCardVisabality(true);
        });
        
        updateVisibalityForEmptyCategorysElementList();
        if(categoriesItemsList.length == 0){
            $isEmptyMessage.removeClass('hidden');
        }else{
            $isEmptyMessage.addClass('hidden');
        }
    }


// DATA
    function addNewItem(category, name, quantity, checkedOff){
        const newCategoryItem = new GroceryItem(category, name, quantity, checkedOff);
        categoriesItemsList.push(newCategoryItem);
        const $categoryResultsUl = $('#'+category+'-content ul.results-list');
        $categoryResultsUl.append(newCategoryItem.getHtmlCard());
        const $newLi = $categoryResultsUl.find('li.card').last();//https://api.jquery.com/last/

        const $quantityNumber = $newLi.find('.quantity input[type="number"]');
        const $checkedOffCheckbox = $newLi.find('input[name="checkedOff"]');
        const $deleteButton = $newLi.find('button[name="delete"]');

        $quantityNumber.on('change', function(e){
            newCategoryItem.quantity = e.target.value;
        });
        $checkedOffCheckbox.on('change', function(e){
            newCategoryItem.checkedOff = e.target.checked;
            if(newCategoryItem.checkedOff){
                $newLi.addClass('checked-off');
            }else{
                $newLi.removeClass('checked-off');
            }
        });
        $deleteButton.on('click', function(e){
            // https://api.jquery.com/hide/
            $newLi.hide("slow", function() {
                $newLi.remove();
            });
            categoriesItemsList.splice(categoriesItemsList.indexOf(newCategoryItem), 1);
            console.log(categoriesItemsList);
            updateVisibalityForEmptyCategorysElementList();
        });
    }




// Run once when page is ready
    // addNewItem("produce", "Apples", 42, false);
    // addNewItem("dairy", "Milk", 1, true);

    triggerSearch();
});
