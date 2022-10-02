let screenIndex = 1;
let optionIndex = 1;

function addScreen(campaignFormId) {
    let campaignForm = document.getElementById(campaignFormId);
    campaignForm.innerHTML += "<div id='screen" + (screenIndex++) + "'>" +
        "<input type='text' name='message'/>" +
        "<div id='option" + (optionIndex++) + "'>" +
        "<input type='text' name='message'/>" +
        "<input type='number' min='0' name='nextScreenId'>" +
        "</div>" +
        "</div>";
}

function addOption(screenDivId) {
    let screenDiv = document.getElementById(screenDivId);
    screenDiv.innerHTML += "<div id='option" + (optionIndex++) + "'>" +
        "<input type='text' name='message'/>" +
        "<input type='number' min='0' name='nextScreenId'>" +
        "</div>";
}