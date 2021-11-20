jQuery(document).ready(function () {
    if (localStorage.getItem('room')) {
        const room = JSON.parse(localStorage.getItem('room'));
        const privacyType = room.privacyType.toString();
        const userName = /*[[${userName}]]*/ 'default';

        $('#roomThumbnail').attr(
            'src',
            `${baseURL}room_images/temp/${room.userName2}/${room.roomImages[0]}`
        );
        $('#room-preview__room-title').text(room.roomTitle);

        $('#room-preview__room-type').text(
            `${privacyType.substring(0, privacyType.lastIndexOf(' '))} ${
                room.roomGroupText
            } cho thuê. Chủ nhà ${userName}`
        );
        $('#room-preview__room-info').text(
            `${room.guestNumber} khách · ${room.bedRoomNumber} phòng ngủ  · ${room.bedNumber} giường · ${room.bathRoomNumber} phòng tắm`
        );
        $('#room-preview__room-description').text(
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

        /*-------------------------------LOCATION-----------------------------------------*/
        $('#room-preview__room-location-txt').text(room.place_name);
        /*-------------------------------LOCATION-----------------------------------------*/
    }
});

function backtoHomePage() {
    window.location.href = baseURL;
}

async function nextPage() {
    //store room into db
    if (localStorage.getItem('room')) {
        const room = JSON.parse(localStorage.getItem('room'));
        const placeNameLength = room.place_name.toString().split(',').length;
        const country =
            room.place_name.toString().split(',')[placeNameLength - 1] ||
            'no-country';
        const state =
            room.place_name.toString().split(',')[placeNameLength - 2] ||
            'no-state';
        const city =
            room.place_name.toString().split(',')[placeNameLength - 3] ||
            'no-city';

        const fd = new FormData();

        let amentities = [];
        amentities.push(room.prominentAmentity);
        amentities.push(room.favoriteAmentity);
        amentities.push(room.safeAmentity);

        const roomEntity = {
            name: room.roomTitle,
            amentities,
            images: room.roomImages,
            country: 216,
            state: state,
            city: city,
            bedroomCount: room.bedRoomNumber,
            bathroomCount: room.bathRoomNumber,
            accomodatesCount: room.guestNumber,
            bedCount: room.bedNumber,
            currency: 2,
            category: 1,
            roomGroup: room.roomGroup,
            roomType: room.roomType,
            description: room.descriptions[0] + ',' + room.descriptions[1],
            latitude: room.latitude * 1,
            longitude: room.longitude * 1,
            price: room.roomPricePerNight,
            priceType: 'PER_NIGHT',
            minimumStay: 1,
            stayType: 'DAY',
            host: hostName,
        };

        console.log(roomEntity);

        for (let key in roomEntity) {
            fd.append(key, roomEntity[key]);
        }

        const { data } = await axios.post(`${baseURL}room/save`, fd, {
            headers: {
                'Content-Type': 'multipart/form-data',
            },
        });
        console.log(data);

        if (data.status === 'OK') {
            window.location.href = `${baseURL}become-a-host/publish-celebration`;
        }
    }
    //     {roomGroup: 4, roomType: 3, privacyType: "Phòng chung", roomLongitude: 11.034049,…}
    // bathRoomNumber: 4
    // bedNumber: 2
    // bedRoomNumber: 2
    // descriptions: ["Vị trí trung tâm", "Phù hợp cho gia đình"]
    // favoriteAmentity: 4
    // favoriteAmentityImageName: "tv.svg"
    // favoriteAmentityName: "TV"
    // guestNumber: 3
    // latitude: 11.034103712352547
    // longitude: 106.68781171676963
    // photosFolderIndex: 0
    // place_name: "822000, Phú Tân, Thị xã Thủ Dầu Một, Binh Duong, Vietnam"
    // privacyType: "Phòng chung"
    // prominentAmentity: 19
    // prominentAmentityImageName: "bep_dot_lua_trai.svg"
    // prominentAmentityName: "Bếp đốt lửa trại"
    // roomGroup: 4
    // roomGroupText: "Không gian độc đáo"
    // roomImages: ["BG.png", "Capture.PNG", "asdfsad.PNG", "247129345_909249943358568_6309293780230821703_n.jpg",…]
    // roomLatitude: 106.68776
    // roomLongitude: 11.034049
    // roomPricePerNight: "645000"
    // roomTitle: "xin chào newyork"
    // roomType: 3
    // safeAmentity: 11
    // safeAmentityImageName: "may_phat_hien_khoi_CO.svg"
    // safeAmentityName: "Máy phát hiện khói CO"
    // userName2: "thuan.leminhthuan.10.2999999@gmail.com"
}
