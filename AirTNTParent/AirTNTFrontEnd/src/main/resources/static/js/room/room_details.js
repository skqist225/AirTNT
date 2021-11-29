function displayStartDateAndEndDate(startDateArgs, endDateArgs) {
    $('#fromDayToDayContainer').css('display', 'block');
    $('#beforeEndDateContainer').css('display', 'none');
    $('#fromDay').text(startDateArgs);
    $('#toDay').text(endDateArgs);
}

function setCheckInAndOutDate(startDateArgs, endDateArgs) {
    $('#checkinDate').text(startDateArgs);
    $('#checkoutDate').text(endDateArgs);
}

function displayNumberOfDays(manyDays) {
    $('#numberOfDaysContainer').css('display', 'block');
    $('#numberOfDaysContainer').siblings('div').css('display', 'none');
    $('#daysAtHere').text(manyDays + 2);
    $('#numberOfNight').text(manyDays + 2);
    displayPreviewLine();
    setTotalPrice(manyDays + 2);
}

function setTotalPrice(manyDays) {
    manyDays *= 1;

    const totalRoomPrice = roomPrice * manyDays;
    const siteFee = totalRoomPrice * 0.05;
    console.log(siteFee);
    totalPrice = totalRoomPrice + siteFee;

    $('#totalPrice').text(seperateNumber(manyDays * roomPrice));
    $('#siteFee').text(seperateNumber(siteFee));
    $('#finalTotalPrice').text(seperateNumber(totalPrice));
}

function displayPreviewLine() {
    $('.previewPrice-line').css('display', 'block');
    $('.previewPrice-line').last().css('border-bottom', '1px solid rgb(211,211,211)');
}

function getElementsOfDate(date) {
    return [date.split('/')[0] * 1, date.split('/')[1] * 1, date.split('/')[2] * 1];
}

function addBlockClassAndRemoveClick(self) {
    self.addClass('block__date');
    self.removeClass('false');
}

function blockBetween(startDateArgs, endDateArgs) {
    const [startDateDate, startDateMonth, startDateYear] = getElementsOfDate(startDateArgs);
    const [endDateDate, endDateMonth, endDateYear] = getElementsOfDate(endDateArgs);

    $('.dayInWeek.false').each(function () {
        const [currDate, currMonth, currYear] = getElementsOfDate(
            $(this).text() + '/' + $(this).data('month') + '/' + $(this).data('year')
        );

        if (currMonth === startDateMonth && currMonth === endDateMonth) {
            if (
                currDate > startDateDate &&
                currDate < endDateDate &&
                currMonth >= startDateMonth &&
                currMonth <= endDateMonth &&
                currYear >= startDateYear &&
                currYear <= endDateYear
            ) {
                addBlockClassAndRemoveClick($(this));
            }
        }
        if (startDateMonth !== endDateMonth) {
            if (currMonth === startDateMonth) {
                if (currDate > startDateDate) {
                    addBlockClassAndRemoveClick($(this));
                }
            } else if (currMonth === endDateMonth) {
                if (currDate < endDateDate) {
                    addBlockClassAndRemoveClick($(this));
                }
            } else {
                if (currMonth > startDateMonth && currMonth < endDateMonth) {
                    addBlockClassAndRemoveClick($(this));
                }
            }
        }
    });
}

function addBgColorForElsBetweenAndCountDays(self, work, howManyDays) {}

function hightlightBetween(startDateArgs, endDateArgs) {
    const [startDateDate, startDateMonth, startDateYear] = getElementsOfDate(startDateArgs);
    const [endDateDate, endDateMonth, endDateYear] = getElementsOfDate(endDateArgs);
    let howManyDays = 0;

    $('.dayInWeek.false').each(function () {
        const [currDate, currMonth, currYear] = getElementsOfDate(
            $(this).text() + '/' + $(this).data('month') + '/' + $(this).data('year')
        );
        let work = false;

        if (currMonth === startDateMonth && currMonth === endDateMonth) {
            if (
                currDate > startDateDate &&
                currDate < endDateDate &&
                currMonth >= startDateMonth &&
                currMonth <= endDateMonth &&
                currYear >= startDateYear &&
                currYear <= endDateYear
            ) {
                $(this).addClass('between');
                work = true;
                howManyDays++;
            }
        }
        if (startDateMonth !== endDateMonth) {
            if (currMonth === startDateMonth) {
                if (currDate > startDateDate) {
                    $(this).addClass('between');
                    work = true;
                    howManyDays++;
                }
            } else if (currMonth === endDateMonth) {
                if (currDate < endDateDate) {
                    $(this).addClass('between');
                    work = true;
                    howManyDays++;
                }
            } else {
                if (currMonth > startDateMonth && currMonth < endDateMonth) {
                    $(this).addClass('between');
                    work = true;
                    howManyDays++;
                }
            }
        }

        if (!work) $(this).removeClass('between');
    });
    displayStartDateAndEndDate(startDate, endDate);
    setCheckInAndOutDate(startDate, endDate);
    displayNumberOfDays(howManyDays);
}

function setStartDate(
    self,
    startDateDate,
    startDateMonth,
    startDateYear,
    currDate,
    currMonth,
    currYear
) {
    const _self = $(self);
    $('.dayInWeek.false').each(function () {
        if (
            $(this).text() * 1 === startDateDate &&
            $(this).data('month') * 1 === startDateMonth &&
            $(this).data('year') * 1 === startDateYear &&
            $(this).hasClass('checked')
        ) {
            $(this).removeClass('checked');
        }
    });
    _self.addClass('checked');
    startDate = '';
    startDate = currDate + '/' + currMonth + '/' + currYear;
    haveStartDate = true;
    console.log('start date', startDate);

    hightlightBetween(startDate, endDate);
}

function setEndDate(self, endDateDate, endDateMonth, endDateYear, currDate, currMonth, currYear) {
    const _self = $(self);
    $('.dayInWeek.false').each(function () {
        if (
            $(this).text() * 1 === endDateDate &&
            $(this).data('month') * 1 === endDateMonth &&
            $(this).data('year') * 1 === endDateYear &&
            $(this).hasClass('checked')
        ) {
            $(this).removeClass('checked');
        }
    });
    _self.addClass('checked');
    endDate = '';
    endDate = currDate + '/' + currMonth + '/' + currYear;
    haveEndDate = true;
    console.log('end date', endDate);

    hightlightBetween(startDate, endDate);
}

const seperateNumber = number => {
    let dotNum = 0;

    const countDotNum = number => {
        const resultOfDivisionOperator = number / 1000;

        if (resultOfDivisionOperator >= 1) {
            dotNum++;
            const _number = resultOfDivisionOperator;
            countDotNum(_number);
        } else {
            return;
        }
    };

    countDotNum(number);

    let finalString = [];
    let numString = String(number);

    for (let i = 0; i < dotNum; i++) {
        const subString = '.' + numString.substr(-3);
        numString = numString.substring(0, numString.length - 3);
        finalString.unshift(subString);
    }

    return numString + finalString.join('');
};

function processBooking() {
    if (startDate === '' && endDate === '') {
        var toastLiveExample = document.getElementById('liveToast');
        $('#toast-message').text('Vui lòng chọn ngày bắt đầu và ngày kết thúc');
        var toast = new bootstrap.Toast(toastLiveExample);
        toast.show();
        return;
    }

    const numberOfNights = $('#numberOfNight').text();
    window.location.href = `${baseURL}book/${roomId}?checkin=${startDate.replace(
        /\//g,
        '-'
    )}&checkout=${endDate.replace(/\//g, '-')}&numberOfNights=${numberOfNights}`;
}

async function fetchTheNextCoupleOfMonth(firstMonthAndYear, secondMonthAndYear, month, year) {
    let secondMonth;
    let copyMonth;
    let copyYear;
    if (month === 11) {
        secondMonth = await fetchDaysInMonth(0, year + 1);
        firstMonthAndYear.html(`Tháng 12 năm ${year}`);
        secondMonthAndYear.html(`Tháng 1 năm ${year + 1}`);

        copyMonth = 12;
        copyYear = year;
    } else if (month === 12) {
        secondMonth = await fetchDaysInMonth(1, year + 1);
        firstMonthAndYear.html(`Tháng 1 năm ${year + 1}`);
        secondMonthAndYear.html(`Tháng 2 năm ${year + 1}`);

        copyMonth = 1;
        copyYear = year + 1;
    } else {
        secondMonth = await fetchDaysInMonth(month + 1, year);
        firstMonthAndYear.html(`Tháng ${month * 1 + 1} năm ${year}`);
        secondMonthAndYear.html(`Tháng ${month * 1 + 2} năm ${year}`);

        copyMonth = month * 1 + 1;
        copyYear = year;
    }

    const firstMonth = await fetchDaysInMonth(month, year);

    const rdt_calender__days = $('.rdt_calender__days').first();
    const rdt_calender__days_plus__1 = $('.rdt_calender__days_plus-1').first();

    const daysInMonthJs1 = getDaysInMonth(firstMonth.daysInMonth, copyMonth, copyYear);
    let decidedMonth = 0;
    if (copyMonth === 12) decidedMonth = 1;
    else if (copyMonth === 1) decidedMonth = 2;
    else decidedMonth = copyMonth + 1;

    const daysInMonthJs2 = getDaysInMonth(secondMonth.daysInMonth, decidedMonth, copyYear);

    rdt_calender__days.empty();
    rdt_calender__days_plus__1.empty();

    daysInMonthJs1.forEach(day => {
        rdt_calender__days.append(day);
    });
    daysInMonthJs2.forEach(day => {
        rdt_calender__days_plus__1.append(day);
    });

    addClickEventForDay();
    bookedDates.forEach(({ checkinDate, checkoutDate }) => {
        blockBetween(checkinDate, checkoutDate);
    });
}

const fetchDaysInMonth = async (month, year) => {
    const {
        data: { daysInMonth, startInWeek },
    } = await axios.get(`/airtnt/calendar/${month + 1}/${year}`);
    return Promise.resolve({ daysInMonth, startInWeek });
};

function getDaysInMonth(daysInMonth, month, year) {
    const date = new Date();
    let daysInMonthJs = [];
    let weeks = daysInMonth.split('*');

    weeks.forEach(week => {
        daysInMonthJs.push('<tbody><tr>');
        const weekArray = week.trim().split(' ');
        weekArray.forEach(dayInWeek => {
            if (dayInWeek === '') {
            } else if (dayInWeek.trim() !== '_') {
                let isBlocked = false;
                if (
                    dayInWeek < date.getDate() &&
                    month <= date.getMonth() + 1 &&
                    year <= date.getFullYear()
                )
                    isBlocked = true;
                else {
                    const dateThis =
                        (dayInWeek.length === 1 ? `0${dayInWeek}` : dayInWeek) +
                        '/' +
                        (month.length === 1 ? `0${month}` : month) +
                        '/' +
                        year;
                    bookedDates.forEach(({ checkinDate, checkoutDate }) => {
                        if (checkinDate === dateThis || dateThis === checkoutDate) {
                            isBlocked = true;
                        }
                    });
                }

                const dayInHtml = `<td><div data-is-blocked="${false}" data-month="${month}" data-year="${year}" class="dayInWeek ${
                    isBlocked && 'block__date'
                }">${dayInWeek.trim()}</div></td>`;
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
                        <img src="/airtnt/room_images/${userName}/${roomId}/${image.image}" id="${
                image.id
            }" class="bigImage active" data-index="${index + 1}"/>
                    `;
        } else {
            imgHtml = `<img src="/airtnt/room_images/${userName}/${roomId}/${image.image}" id="${
                image.id
            }" class="bigImage" data-index="${index + 1}"/>`;
        }

        innerContainer.append(imgHtml);
    });
}

async function fetchRoomImages(roomId) {
    const { data } = await axios.get('/airtnt/homes/' + roomId);
    return data;
}
