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