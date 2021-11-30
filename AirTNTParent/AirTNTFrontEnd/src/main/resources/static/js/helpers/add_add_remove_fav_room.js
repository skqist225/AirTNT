function addClickEventForLoveButton() {
    $('.room__likeBtn').each(function () {
        const roomId = $(this).data('room-id');
        const children = $(this).children('svg');

        if (wishlistsArr.length && wishlistsArr.includes(roomId)) children.addClass('like');

        $(this).click(async function (event) {
            event.preventDefault();
            let getURL = '';
            let successMessage = '';
            let errorMessage = '';

            if (children.hasClass('like')) {
                children.removeClass('like');
                getURL = `${baseURL}remove-from-wishlists/${roomId}`;
                successMessage = 'Gỡ bỏ danh sách yêu thích thành công';
                errorMessage = 'Gỡ bỏ danh sách yêu thích thất bại';
            } else {
                children.addClass('like');
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
