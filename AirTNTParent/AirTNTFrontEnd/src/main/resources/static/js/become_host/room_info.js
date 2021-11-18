$(document).ready(function () {
    $('.incAndDecBtn').each(function () {
        $(this).click(function () {
            if ($(this).data('function') === 'dec') {
                const spanInfoTag = $(this).siblings(
                    `#${$(this).data('edit')}`
                );
                if (spanInfoTag.html() * 1 > 0) {
                    if (spanInfoTag.html() * 1 === 1) {
                        $(this).attr('disabled', 'true');
                    }

                    spanInfoTag.html(spanInfoTag.html() * 1 - 1);
                }
            }

            if ($(this).data('function') === 'inc') {
                const spanInfoTag = $(this).siblings(
                    `#${$(this).data('edit')}`
                );

                if (spanInfoTag.html() * 1 === 0) {
                    $(this)
                        .siblings(`#${$(this).data('edit')}DecBtn`)
                        .removeAttr('disabled');
                }

                spanInfoTag.html(spanInfoTag.html() * 1 + 1);
            }
        });
    });
});

function backtoHomePage() {
    window.location.href = baseURL;
}

function nextPage() {
    const guestNumber = $('#guestNumber').text() * 1;
    const bedNumber = $('#bedNumber').text() * 1;
    const bedRoomNumber = $('#bedRoomNumber').text() * 1;
    const bathRoomNumber = $('#bathRoomNumber').text() * 1;

    let room = {};
    if (!localStorage.getItem('room')) {
        room = {
            guestNumber,
            bedNumber,
            bedRoomNumber,
            bathRoomNumber,
        };
    } else {
        room = JSON.parse(localStorage.getItem('room'));
        room = {
            ...room,
            guestNumber,
            bedNumber,
            bedRoomNumber,
            bathRoomNumber,
        };
    }
    localStorage.setItem('room', JSON.stringify(room));

    window.location.href = `${baseURL}become-a-host/amenities`;
}
