const active = 'active';

$(document).ready(async function () {
    const date = new Date();
    const month = date.getMonth();
    const year = date.getFullYear();

    const closeShowImgBtn = $('#closeShowImgBtn');
    closeShowImgBtn.click(function () {
        toggleHiddenImages();
    });

    const firstMonthAndYear = $('.firstMonthAndYear').first();
    const secondMonthAndYear = $('.secondMonthAndYear').first();
    const getTheNextTwoMonth = $('.getTheNextTwoMonth').first();

    await fetchTheNextCoupleOfMonth(
        firstMonthAndYear,
        secondMonthAndYear,
        month,
        year
    );

    getTheNextTwoMonth.on('click', async function () {
        const currentFirstMonthInCalendar = secondMonthAndYear
            .text()
            .split(' ')[1];
        const currentYearInCalendar = secondMonthAndYear.text().split(' ')[3];

        await fetchTheNextCoupleOfMonth(
            firstMonthAndYear,
            secondMonthAndYear,
            currentFirstMonthInCalendar * 1,
            currentYearInCalendar * 1
        );
    });

    let isClicked = false;
    const navMenu = $('.loginAndLogoutHidden').first();
    $('.account__button')
        .first()
        .click(function () {
            if (!isClicked) {
                navMenu.classList.add('active');
                isClicked = true;
            } else {
                navMenu.classList.remove('active');
                isClicked = false;
            }
        });
});

async function fetchTheNextCoupleOfMonth(
    firstMonthAndYear,
    secondMonthAndYear,
    month,
    year
) {
    let secondMonth;
    if (month === 11) {
        secondMonth = await fetchDaysInMonth(0, year + 1);
        secondMonthAndYear.html(`Tháng ${1} năm ${year + 1}`);
    } else {
        secondMonth = await fetchDaysInMonth(month + 1, year);
        secondMonthAndYear.html(`Tháng ${month * 1 + 2} năm ${year}`);
    }

    firstMonthAndYear.html(`Tháng ${month * 1 + 1} năm ${year}`);

    const firstMonth = await fetchDaysInMonth(month, year);

    const rdt_calender__days = $('.rdt_calender__days').first();
    const rdt_calender__days_plus__1 = $('.rdt_calender__days_plus-1').first();

    const daysInMonthJs1 = getDaysInMonth(firstMonth.daysInMonth);
    const daysInMonthJs2 = getDaysInMonth(secondMonth.daysInMonth);

    rdt_calender__days.empty();
    rdt_calender__days_plus__1.empty();

    daysInMonthJs1.forEach(day => {
        rdt_calender__days.append(day);
    });
    daysInMonthJs2.forEach(day => {
        rdt_calender__days_plus__1.append(day);
    });
}

const fetchDaysInMonth = async (month, year) => {
    const {
        data: { daysInMonth, startInWeek },
    } = await axios.get(`/airtnt/calendar/${month + 1}/${year}`);
    return Promise.resolve({ daysInMonth, startInWeek });
};

function getDaysInMonth(daysInMonth) {
    let daysInMonthJs = [];
    let weeks = daysInMonth.split('*');
    weeks.forEach(week => {
        daysInMonthJs.push('<tbody><tr>');
        const weekArray = week.trim().split(' ');
        weekArray.forEach(dayInWeek => {
            if (dayInWeek.trim() !== '_') {
                const dayInHtml = `<td><div data-is-blocked="${false}" class="dayInWeek block__date">${dayInWeek.trim()}</div></td>`;
                daysInMonthJs.push(dayInHtml);
            } else if (dayInWeek.trim() === '_') {
                const dayInHtml = `<td></td>`;
                daysInMonthJs.push(dayInHtml);
            }
        });
        daysInMonthJs.push('</tr></tbody>');
    });
    return daysInMonthJs;
}

function toggleHiddenImages() {
    const hiddenSlider = $('.rdt_hidden').first();
    const fullScreen = $('.rdt__fullScreen').first();
    if (hiddenSlider.hasClass('show')) {
        hiddenSlider.removeClass('show');
    } else {
        hiddenSlider.addClass('show');
    }
}

async function showFullscreenImage() {
    const header = $('.header');
    header.hide();
    toggleHiddenImages();

    const shareIcon = $('.shareSvg');
    const loveIcon = $('.loveSvg');
    const backIcon = $('.backSvg');
    shareIcon.attr('class', 'shareSvg white');
    loveIcon.attr('class', 'loveSvg white');
    backIcon.attr('class', 'backIcon white');
    backIcon.attr(
        'style',
        `height: 16px;
        width: 16px;
        display: block;
        fill: #fff;`
    );

    const rdt_container = $('.rdt_container').first();
    rdt_container.hide();
    const fullScreen = $('.rdt__fullScreen').first();
    fullScreen.addClass('block');
    const roomId = $('#roomId').val();
    let { images, userName } = await fetchRoomImages(roomId);
    const innerContainer = $('#innerContainer');

    images.forEach((image, index) => {
        let imgHtml;
        if (index === 0) {
            imgHtml = `
                        <img src="/airtnt/room_images/${userName}/${
                image.image
            }" id="${image.id}" class="bigImage active" data-index="${
                index + 1
            }"/>
                    `;
        } else {
            imgHtml = `<img src="/airtnt/room_images/${userName}/${
                image.image
            }" id="${image.id}" class="bigImage" data-index="${index + 1}"/>`;
        }

        innerContainer.append(imgHtml);
    });
}

async function fetchRoomImages(roomId) {
    const { data } = await axios.get('/airtnt/homes/' + roomId);
    return data;
}
