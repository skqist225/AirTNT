function Room(room, indexParent) {
    return `
                    <div class="room__container">
                        <div class="room__image__container">
                            <a href="/airtnt/rooms/${room.id}">
                                <div class="image__slider">
                                    ${(function () {
                                        const newArr = room.images.map((image, index) => {
                                            return `<img src='/airtnt${
                                                image.imagePath
                                            }' data-index="${index + 1}"
                                                class='room__image${indexParent + 1}'/>`;
                                        });

                                        return newArr.join('');
                                    })()}
                                </div>
                            </a>
                            <div class="room__changeImage__pseudoContainer">
                                <div class="room__changeImage">
                                    <button type="button" class="nextImgBtn" data-room-id="${
                                        indexParent + 1
                                    }" onclick="changeImage(this, 'room__image');" data-function-name="prevImage">
                                        <svg viewBox="0 0 16 16" role="presentation" aria-hidden="true" focusable="false" style="height: 10px; width: 10px; display: block; fill: currentcolor;">
                                            <path d="m10.8 16c-.4 0-.7-.1-.9-.4l-6.8-6.7c-.5-.5-.5-1.3 0-1.8l6.8-6.7c.5-.5 1.2-.5 1.7 0s .5 1.2 0 1.7l-5.8 5.9 5.8 5.9c.5.5.5 1.2 0 1.7-.2.3-.5.4-.8.4"></path>
                                        </svg>
                                    </button>
                                    <button type="button" class="nextImgBtn" data-room-id="${
                                        indexParent + 1
                                    }" onclick="changeImage(this, 'room__image');" data-function-name="nextImage">
                                        <span class="_3hmsj">
                                            <svg viewBox="0 0 16 16" role="presentation" aria-hidden="true" focusable="false" style="height: 10px; width: 10px; display: block; fill: currentcolor;">
                                                <path d="m5.3 16c .3 0 .6-.1.8-.4l6.8-6.7c.5-.5.5-1.3 0-1.8l-6.8-6.7c-.5-.5-1.2-.5-1.7 0s-.5 1.2 0 1.7l5.8 5.9-5.8 5.9c-.5.5-.5 1.2 0 1.7.2.3.5.4.9.4">
                                                </path>
                                            </svg>
                                        </span>
                                    </button>
                                </div>
                            </div>
                            <div class="room__button__like__container">
                                <button class="room__likeBtn">
                                    <svg
                                        viewBox="0 0 32 32"
                                        xmlns="http://www.w3.org/2000/svg"
                                        aria-hidden="true"
                                        role="presentation"
                                        focusable="false"
                                        style="
                                            display: block;
                                            fill: rgba(0, 0, 0, 0.5);
                                            height: 24px;
                                            width: 24px;
                                            stroke: rgb(255, 255, 255);
                                            stroke-width: 2;
                                            overflow: visible;
                                        "
                                        class="heartSvg"
                                        onclick="likeRoom(this);"
                                    >
                                        <path
                                            d="m16 28c7-4.733 14-10 14-17 0-1.792-.683-3.583-2.05-4.95-1.367-1.366-3.158-2.05-4.95-2.05-1.791 0-3.583.684-4.949 2.05l-2.051 2.051-2.05-2.051c-1.367-1.366-3.158-2.05-4.95-2.05-1.791 0-3.583.684-4.949 2.05-1.367 1.367-2.051 3.158-2.051 4.95 0 7 7 12.267 14 17z"
                                        ></path>
                                    </svg>
                                </button>
                            </div>
                        </div>
                        <a href="/airtnt/rooms/${room.id}">
                            <div class="flex" style="padding-top: 15px;">
                                <div class="room__name">${room.name}</div>
                                <div class="room__price">${room.currencySymbol} ${
        room.price
    } / ${room.priceType === 'PER_NIGHT' ? 'đêm' : 'tuần'}</div>
                            </div>
                        </a>
                    </div>
                `;
}
