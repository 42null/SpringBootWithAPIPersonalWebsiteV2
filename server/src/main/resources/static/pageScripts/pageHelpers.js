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
    const overlay = document.getElementById('overlay');
    const popup = document.getElementById('popup');
    overlay.style.display = 'block';
    popup.style.display = 'block';

    if(onClickContent.includes(".pdf")){//TODO: Make more accurate
        const pdf = document.createElement("object");


        pdf.setAttribute('data', onClickContent);
        pdf.style.width = "100%";
        pdf.style.height = "100%";
        // thumbnail.parentElement.appendChild(pdf);
        popup.appendChild(pdf);
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
}