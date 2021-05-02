function deleteVisit(visitId) {
    let httpRequest = new XMLHttpRequest();
    httpRequest.open("POST", "/delete_id", true);
    httpRequest.send(visitId);
}

