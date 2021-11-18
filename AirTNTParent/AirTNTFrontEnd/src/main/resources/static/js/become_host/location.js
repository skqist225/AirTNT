mapboxgl.accessToken =
    'pk.eyJ1IjoibG9yZGVkc3dpZnQyMjUiLCJhIjoiY2t3MDJvZ2E5MDB0dDJxbndxbjZxM20wOCJ9.hYxzgffyfc93Aiogipp5bA';
let userLat = 0,
    userLng = 0;

jQuery(document).ready(function () {
    const locationInputContainer = $('.location__input-container');
    locationInputContainer.each(function () {
        $(this).click(function () {
            locationInputContainer.each(function () {
                if ($(this).hasClass('focus')) {
                    $(this).removeClass('focus');
                    const input = $(this).children().last().children('input');
                    if (!input.val()) {
                        $(this).children().first().removeClass('focus');

                        input.removeClass('focus');
                    }
                }
            });

            $(this).children().first().addClass('focus');
            $(this).children().last().children('input').addClass('focus');
            $(this).addClass('focus');
        });
    });
});

function expandSelectTag(self) {
    const selectTagContainer = $('#selectTagContainer');
    selectTagContainer.css('display', 'block');
}

const x = document.getElementById('demo');
function getLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showPosition, showError, {
            timeout: 10000,
        });
    } else {
        x.innerHTML = 'Geolocation is not supported by this browser.';
    }
}

function onKeyDown(event) {
    const input = $('.location__input-container')
        .filter('.focus')
        .children()
        .last()
        .children('input.focus');

    const currentValue = input.val();
    console.log(currentValue);
    console.log(currentValue.toString().length);
    if (event.key === 'Backspace') {
        if (currentValue.length === 1) {
            input.parent().siblings().removeClass('focus');
        }
    }
}

function BackToSearchLocation(self) {
    const _self = $(self);

    _self.parent().parent().css('display', 'none');
    $('.location__search-location').first().css('display', 'block');
}

getLocation();

function showPosition(position) {
    userLat = position.coords.latitude;
    userLng = position.coords.longitude;

    var map = new mapboxgl.Map({
        container: 'map',
        style: 'mapbox://styles/mapbox/streets-v11',
        center: [userLng * 1, userLat * 1], // starting position [lng, lat]
        zoom: 13, // starting zoom
    });

    const markerHeight = 50;
    const markerRadius = 10;
    const linearOffset = 25;
    const popupOffsets = {
        top: [0, 0],
        'top-left': [0, 0],
        'top-right': [0, 0],
        bottom: [0, -markerHeight],
        'bottom-left': [
            linearOffset,
            (markerHeight - markerRadius + linearOffset) * -1,
        ],
        'bottom-right': [
            -linearOffset,
            (markerHeight - markerRadius + linearOffset) * -1,
        ],
        left: [markerRadius, (markerHeight - markerRadius) * -1],
        right: [-markerRadius, (markerHeight - markerRadius) * -1],
    };

    const userName = document.getElementById('userName').value;
    const image = document.createElement('img');
    image.src = document.getElementById('userAvatar').value;
    image.style = 'width:40px; height:40px; border-radius:50%;object-fit:cover';

    const marker = new mapboxgl.Marker(image)
        .setPopup(
            new mapboxgl.Popup({
                offset: popupOffsets,
                className: 'my-class',
            }) // add popups
                .setHTML(`<h1>${userName}</h1>`)
                .setMaxWidth('300px')
        )
        .setLngLat([userLng, userLat])
        .addTo(map);

    const popup = new mapboxgl.Popup({
        offset: popupOffsets,
        className: 'my-class',
    })
        .setLngLat([userLng, userLat])
        .setHTML(`<h1>${userName}</h1>`)
        .setMaxWidth('300px')
        .addTo(map);
}

function showError(error) {
    switch (error.code) {
        case error.PERMISSION_DENIED:
            x.innerHTML = 'User denied the request for Geolocation.';
            break;
        case error.POSITION_UNAVAILABLE:
            x.innerHTML = 'Location information is unavailable.';
            break;
        case error.TIMEOUT:
            x.innerHTML = 'The request to get user location timed out.';
            break;
        case error.UNKNOWN_ERROR:
            x.innerHTML = 'An unknown error occurred.';
            break;
    }
}

function nextPage() {
    let room = {};
    if (!localStorage.getItem('room')) {
        room = {
            longitude: userLat,
            latitude: userLng,
        };
    } else {
        room = JSON.parse(localStorage.getItem('room'));
        room = {
            ...room,
            longitude: userLat,
            latitude: userLng,
        };
    }
    localStorage.setItem('room', JSON.stringify(room));

    window.location.href = `${baseURL}become-a-host/room-info`;
}
