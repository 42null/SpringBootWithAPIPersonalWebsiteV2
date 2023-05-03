function copyTextToClipboard(textToCopy) {
    // document.execCommand("copy");
    navigator.clipboard.writeText(textToCopy)
        .then(() => console.log("Text copied to clipboard: \""+textToCopy+"\""))
        .catch((error) => console.error('Error copying text: ', error));
}