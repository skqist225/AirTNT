jQuery(document).ready(function () {
    if (localStorage.getItem('room')) {
        const room = JSON.parse(localStorage.getItem('room'));

        const roomThumbnail = $('#roomThumbnail').attr(
            'src',
            `${baseURL}room_images/test/${room.photosFolderIndex}/${room.roomImages[0]}`
        );
        const roomTitle = $('#room-preview__room-title').text(room.roomTitle);
        const privacyType = room.privacyType.toString();
        const userName = /*[[${userName}]]*/ 'default';
        const roomType = $('#room-preview__room-type').text(
            `${privacyType.substring(0, privacyType.lastIndexOf(' '))} ${
                room.roomGroupText
            } cho thuê. Chủ nhà ${userName}`
        );
        const roomInfo = $('#room-preview__room-info').text(
            `${room.guestNumber} khách · ${room.bedRoomNumber} phòng ngủ  · ${room.bedNumber} giường · ${room.bathRoomNumber} phòng tắm`
        );
        const roomDescription = $('#room-preview__room-description').text(
            `Thư giãn tại địa điểm nghỉ dưỡng ${room.descriptions[0]
                .toString()
                .toLowerCase()} và ${room.descriptions[1]
                .toString()
                .toLowerCase()} này.`
        );

        /*-------------------------------AMENTITIES-----------------------------------------*/
        $('#prominentAmentity').attr(
            'src',
            `${baseURL}amentity_images/${room.prominentAmentityImageName}`
        );
        $('#favoriteAmentity').attr(
            'src',
            `${baseURL}amentity_images/${room.favoriteAmentityImageName}`
        );
        $('#safeAmentity').attr(
            'src',
            `${baseURL}amentity_images/${room.safeAmentityImageName}`
        );
        $('#prominentAmentityName').text(room.prominentAmentityName);
        $('#favoriteAmentityName').text(room.favoriteAmentityName);
        $('#safeAmentityName').text(room.safeAmentityName);
        /*-------------------------------AMENTITIES-----------------------------------------*/
    }
});

function backtoHomePage() {
    window.location.href = baseURL;
}

function nextPage() {
    window.location.href = `${baseURL}become-a-host/publish-celebration`;
}
