function addClickEventForLoveButton(wishlistsArr = [], user = null) {
    $('.room__likeBtn').each(function () {
        const roomId = $(this).data('room-id');
        const children = $(this).children('svg');

        if (wishlistsArr.length && wishlistsArr.includes(roomId)) children.addClass('like');

        $(this).click(async function (event) {
            event.preventDefault();
            let getURL = '';
            let successMessage = '';
            let errorMessage = '';

            if (user === null) alertify.error('Vui lòng đăng nhập để thêm yêu thích');

            if (children.hasClass('like')) {
                $('.room__likeBtn').each(function () {
                    if ($(this).data('room-id') === roomId)
                        $(this).children('svg').removeClass('like');
                });

                getURL = `${baseURL}remove-from-wishlists/${roomId}`;
                successMessage = 'Gỡ bỏ danh sách yêu thích thành công';
                errorMessage = 'Gỡ bỏ danh sách yêu thích thất bại';
            } else {
                $('.room__likeBtn').each(function () {
                    if ($(this).data('room-id') === roomId)
                        $(this).children('svg').addClass('like');
                });

                getURL = `${baseURL}add-to-wishlists/${roomId}`;
                successMessage = 'Thêm vào danh sách yêu thích thành công';
                errorMessage = 'Thêm vào danh sách yêu thích thất bại';
            }

            const { data } = await axios.get(getURL);
            if (data === 'success') {
                alertify.success(successMessage);
            } else alertify.error(errorMessage);
        });
    });
}
