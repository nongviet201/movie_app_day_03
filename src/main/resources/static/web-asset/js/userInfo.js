

const formUserInfoEl = document.querySelector('.form-user-info');
const usernameEl = document.getElementById('username');

// USER INFO
formUserInfoEl.addEventListener('submit', async (e) => {
    e.preventDefault();

    const data = {
        id: currentUser.id,
        username: usernameEl.value,
    }
    if (!$('.form-user-info').valid()) {
        return;
    }
    //
    // console.log(data)
    try {
        // Gửi yêu cầu lấy dữ liệu login từ backend
        const response = await axios.put("/api/auth/user/update", data);
        const userData = response.data;
        console.log(userData);
        setTimeout(() => {
            window.location.href = '/';
        }, 1000);
        toastr.success('Cập nhật thành công');
    } catch (error) {
        console.log(error);
        toastr.error(error.response.data.message);
        // Xử lý lỗi nếu có
    }
})


// thông báo
$('.form-user-info').validate({
    rules: {
        username: {
            required: true,
        },
    },
    messages: {
        username: {
            required: "không được để trống username"
        },
    },
    errorElement: 'span',
    errorPlacement: function (error, element) {
        error.addClass('invalid-feedback');
        element.closest('.form-group').append(error);
    },
    highlight: function (element, errorClass, validClass) {
        $(element).addClass('is-invalid');
    },
    unhighlight: function (element, errorClass, validClass) {
        $(element).removeClass('is-invalid');
    }
});
