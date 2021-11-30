const active = 'active';
const imageClassName = '.' + 'cat__image';
const loadTime = 200;
const defaultHeight = 1902;
let wishlistsArr = [];
let globalPage = 1;
let globalCatId = 0;
let rooms = [];

$(document).ready(async function () {
    const catContainers = $('.cat__container');
    const buttonContainers = $('.button__container');
    const roomsContainer = $('#rooms__container');

    const activeWhenLoaded = catContainers.first().addClass(active);
    const img = $(imageClassName, catContainers.first());
    img.addClass(active);

    let { catId, page } = await fetchRoomsByCategory(
        buttonContainers.first(),
        catContainers,
        roomsContainer,
        globalPage,
        rooms
    );
    globalPage = page;
    globalCatId = catId;

    buttonContainers.each(function (index) {
        $(this).click(async function () {
            let { catId, page } = await fetchRoomsByCategory(
                this,
                catContainers,
                roomsContainer,
                globalPage,
                rooms
            );
            globalPage = page;
            globalCatId = catId;
        });
    });

    $(this).on('scroll', async function () {
        let root = [];
        const scrollY = $(this).scrollTop();
        console.log(scrollY);
        if (
            scrollY > defaultHeight * (globalPage - 1) &&
            scrollY < defaultHeight * (globalPage - 1) + 50
        ) {
            root = await fetchRooms(globalCatId, globalPage);

            if (root.length > 0) {
                rooms.push.apply(root);
                appendRoomToRoomsContainer(rooms, roomsContainer);
            }
        }
    });
});

async function fetchRooms(catId, page) {
    const {
        data: { root, wishlists },
    } = await axios.post('/airtnt/homes', {
        catId,
        page,
    });

    if (wishlists) {
        wishlistsArr = wishlists;
    }

    return Promise.resolve(root);
}

function appendRoomToRoomsContainer(rooms, roomsContainer) {
    let roomsHtml = [];
    if (rooms.length > 0) {
        rooms.forEach((room, indexParent) => {
            roomsHtml.push(Room(room, indexParent));
        });

        roomsHtml.map(roomHtml => {
            let div = document.createElement('div');
            div.style.width = '100%';
            div.style.height = '100%';
            div.classList = 'animationHere';
            div.innerHTML = roomHtml;
            div = $(div);
            div = div.hide();

            roomsContainer.append(div);
        });
        const animationDiv = $('.animationHere');
        animationDiv.each(function (index) {
            $(this).fadeIn(loadTime * (index + 1));
        });

        for (let i = 1; i <= rooms.length; i++) {
            const roomImages = $('.room__image' + i);
            roomImages.first().addClass(active);
        }
    } else {
        roomsContainer.html('<p>There is no rooms in here</p>');
    }
}

function setActiveTab(catContainer, self) {
    catContainer.each(function () {
        $(this).removeClass(active);

        const insideLoopimage = $(imageClassName, this);
        insideLoopimage.removeClass(active);
    });

    $(self).parent().addClass(active);
    const image = $(imageClassName, self);
    image.addClass(active);
}

async function fetchRoomsByCategory(self, catContainer, roomsContainer, globalPage, rooms) {
    let root = [];
    rooms = [];
    setActiveTab(catContainer, self);

    const catName = $('.cat__name', self);
    const catId = $('.cat__id', self).val();

    if (catName.text() === 'Ryokan' || catName.text() === 'Nhà trên cây') {
        roomsContainer.empty();
        if (globalPage !== 1) {
            globalPage = 1;
        }

        root = await fetchRooms(catId, globalPage);
        console.log(root);
        root.forEach(room => {
            rooms.push(room);
        });

        appendRoomToRoomsContainer(rooms, roomsContainer);
        addClickEventForLoveButton();
    } else {
        roomsContainer.html(`<p>${catName.text()}</p>`);
    }
    if (globalPage === 1) {
        globalPage++;
    }

    return Promise.resolve({ catId, page: globalPage });
}

function redirectToRoomDetails(self) {
    window.location.href = `${baseURL}rooms/${self.data('room-id')}`;
}
