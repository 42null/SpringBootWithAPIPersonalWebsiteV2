function copyTextToClipboard(textToCopy) {
    navigator.clipboard.writeText(textToCopy)
        .then(() => console.log("Text copied to clipboard: \""+textToCopy+"\""))
        .catch((error) => console.error('Error copying text: ', error));
    const hiddenElements = document.querySelectorAll(".hiddenClipboard");
    for (let i = 0; i < hiddenElements.length; i++) {
        hiddenElements[i].classList.remove("hiddenClipboard");
    }
    const lastCopiedElements = document.querySelectorAll(".lastCopiedToClipboard");
    for (let i = 0; i < lastCopiedElements.length; i++) {
        lastCopiedElements[i].classList.remove("lastCopiedToClipboard");
    }

    const allSameCopyTextElements = document.querySelectorAll('td[onclick="copyTextToClipboard(\''+textToCopy+'\')"]');
    for (let i = 0; i < allSameCopyTextElements.length; i++) {
        allSameCopyTextElements[i].querySelector(".clipboard-wrap").classList.add("lastCopiedToClipboard");
        allSameCopyTextElements[i].querySelector(".clipboard-copy").classList.add("hiddenClipboard");
    }

}

//Gallery thumbnail popups
function expandImage(onClickContent, id) {
    // const thumbnail = document.getElementById(id);

    if(onClickContent.includes(".pdf")){//TODO: Make more accurate
        const overlay = document.getElementById('overlay');
        const popup = document.getElementById('popup');
        overlay.style.display = 'block';
        popup.style.display = 'block';
        disableScroll();

        const pdf = document.createElement("object");

        pdf.setAttribute('data', onClickContent);
        pdf.style.width = "100%";
        pdf.style.height = "100%";
        // thumbnail.parentElement.appendChild(pdf);
        popup.appendChild(pdf);

    }else if(onClickContent.startsWith("http")){
        window.open(onClickContent);
    }

}
// Closes popup when Esc key is pressed
document.addEventListener('keydown', function(event) {
    if (event.key === 'Escape') {
        closeImagePopup();
    }
});

// Closes popup when clicked outside the popup

document.addEventListener('click', function(event) {
    const overlay = document.getElementById('overlay');
    if(event.target === overlay){
        closeImagePopup();
    }
});
function closeImagePopup() {
    const overlay = document.getElementById('overlay');
    const popup = document.getElementById('popup');
    popup.innerHTML = ''; // Remove the content inside the popup
    overlay.style.display = 'none';
    popup.style.display = 'none';
    enableScroll();
}

//START DISABLE SCROLLING: Code used from (https://stackoverflow.com/a/4770179)
var keys = {37: 1, 38: 1, 39: 1, 40: 1};

function preventDefault(e) {
    e.preventDefault();
}

function preventDefaultForScrollKeys(e) {
    if (keys[e.keyCode]) {
        preventDefault(e);
        return false;
    }
}

// modern Chrome requires { passive: false } when adding event
var supportsPassive = false;
try {
    window.addEventListener("test", null, Object.defineProperty({}, 'passive', {
        get: function () { supportsPassive = true; }
    }));
} catch(e) {}

var wheelOpt = supportsPassive ? { passive: false } : false;
var wheelEvent = 'onwheel' in document.createElement('div') ? 'wheel' : 'mousewheel';

// call this to Disable
function disableScroll() {
    window.addEventListener('DOMMouseScroll', preventDefault, false); // older FF
    window.addEventListener(wheelEvent, preventDefault, wheelOpt); // modern desktop
    window.addEventListener('touchmove', preventDefault, wheelOpt); // mobile
    window.addEventListener('keydown', preventDefaultForScrollKeys, false);
}
// call this to Enable
function enableScroll() {
    window.removeEventListener('DOMMouseScroll', preventDefault, false);
    window.removeEventListener(wheelEvent, preventDefault, wheelOpt);
    window.removeEventListener('touchmove', preventDefault, wheelOpt);
    window.removeEventListener('keydown', preventDefaultForScrollKeys, false);
}
//END DISABLE SCROLLING