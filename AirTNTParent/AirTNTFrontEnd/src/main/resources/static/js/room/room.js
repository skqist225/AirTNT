async function fetchRooms(catId, page) {
    const {
        data: { root },
    } = await axios.post('http://localhost:8001/airtnt/homes', {
        catId,
        page,
    });

    return root;
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

function setActiveTab(catContainer) {
    catContainer.each(function () {
        $(this).removeClass(active);

        const image = $(imageClassName, this);
        image.removeClass(active);
    });

    $(self).parent().addClass(active);
    const image = $(imageClassName, self);
    image.addClass(active);
}

async function fetchRoomsByCategory(self, catContainer, roomsContainer, globalPage) {
    let root = [];
    setActiveTab(catContainer);

    const catName = $('.cat__name', self);
    const catId = $('.cat__id', self).val();

    if (catName.text() === 'Ryokan') {
        roomsContainer.empty();

        root = await fetchRooms(catId, globalPage);
        root.forEach(room => rooms.push(room));

        appendRoomToRoomsContainer(rooms, roomsContainer);
    } else {
        roomsContainer.html(`<p>${catName.text()}</p>`);
    }

    return new Promise((resolve, reject) => {
        resolve({ catId, page: ++globalPage });
    });
}

function likeRoom(self) {
    self = $(self);
    if (self.hasClass('like')) {
        self.removeClass('like');
    } else {
        self.addClass('like');
    }
}
