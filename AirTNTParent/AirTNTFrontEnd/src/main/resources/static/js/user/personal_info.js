$(document).ready(function () {
    $('.editBtn').each(function () {
        $(this).click(function () {
            $(this).addClass('editMode'); // ok
            $(this).parent().addClass('editMode'); // ok

            const formNeedToBeShow = $(this).data('edit');

            const frmOn = $('.formEdit_' + formNeedToBeShow).first();
            frmOn.addClass('editMode');

            if (formNeedToBeShow === 'address') {
                $(this).parent().addClass('needMoreSpace');
            }

            if (formNeedToBeShow === 'password') {
                $(this).parent().addClass('passwordArea');
            }

            frmOn
                .siblings('.displayWhenNormalMode')
                .first()
                .addClass('editMode');
            $('.closeBtn', $(this).parent()).addClass('editMode');

            const _self = $(this);

            $('.editBtn').each(function () {
                if ($(this).data('edit') !== _self.data('edit')) {
                    $(this).attr('disabled', 'true');
                    $(this).attr(
                        'style',
                        'color: rgb(118, 118, 118) !important'
                    );
                }
            });
        });
    });
    const countryNameSelect = $('#countryNameSelect');
    getCountryName(countryNameSelect);

    countryNameSelect.on('change', function () {
        getStateName($(this).val());
    });
});

function turnOffEditMode(self) {
    const currentFieldOnEdit = $(self).siblings('#turnOffEditModeArgs1').val();

    $('.editBtn').each(function () {
        $(this).removeAttr('disabled');
        $(this).removeAttr('style');
    });

    $('.editBtn').each(function () {
        if ($(this).data('edit') == currentFieldOnEdit) {
            $(this).removeClass('editMode');
            $(this).parent().removeClass('editMode');
            const formEditFirstNameAndLastName = $(
                '.formEdit_' + currentFieldOnEdit,
                $(this).parent()
            ).first();
            formEditFirstNameAndLastName.removeClass('editMode');

            if (currentFieldOnEdit === 'address') {
                $(this).parent().removeClass('needMoreSpace');
            }
            if (currentFieldOnEdit === 'password') {
                $(this).parent().removeClass('passwordArea');
            }

            formEditFirstNameAndLastName
                .siblings('.displayWhenNormalMode')
                .first()
                .removeClass('editMode');

            return;
        }
    });
    $(self).removeClass('editMode');
}

async function getCountryName(countryNameSelect) {
    countryNameSelect = $(countryNameSelect);
    const {
        data: { data: countries },
    } = await axios.get('http://localhost:8001/airtnt/countries');

    let countryNameList = countries.map(({ name }) => name);

    const userCountryName = $('#userCountryName').val();

    countryNameList.sort().forEach(name => {
        countryNameSelect.append(
            $('<option>', {
                value: name,
                text: name,
                selected: userCountryName === name,
            })
        );
    });
    getStateName(userCountryName);
}

async function getStateName(countryName) {
    const {
        data: { data: states },
    } = await axios.get(
        `http://localhost:8001/airtnt/states/${countryName
            .toString()
            .toLowerCase()}`
    );

    const stateNameSelect = $('#stateNameSelect');
    stateNameSelect.empty();

    let countryNameList = states.filter(name => {
        if (
            !name.toLowerCase().startsWith('huyện') &&
            !name.toLowerCase().startsWith('thị xã') &&
            !name.toLowerCase().startsWith('ấp')
        ) {
            return name;
        }
    });

    const userStateName = $('#userStateName').val();

    countryNameList.sort().forEach(name => {
        stateNameSelect.append(
            $('<option>', {
                value: name,
                text: name,
                // selected: userStateName === name,
            })
        );
    });
}
