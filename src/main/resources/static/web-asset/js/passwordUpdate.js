const oldPasswordEl = document.getElementById('oldPassword');
const newPasswordEl = document.getElementById('newPassword');
const confirmPasswordEl = document.getElementById('confirmPassword');
const passwordEl = document.querySelector('.form-user-password');

// PASSWORD
passwordEl.addEventListener('submit', async (e) => {
    e.preventDefault();
    if (newPasswordEl.value.trim() !== confirmPasswordEl.value.trim()) {
        toastr.error("mật khẩu mới không trùng nhau");
        return;
    }

    const data = {
        id: currentUser.id,
        oldPassword: oldPasswordEl.value,
        newPassword: newPasswordEl.value,
    }
    if (!$('.form-user-password').valid()) {
        return;
    }

    try {
        // Gửi yêu cầu lấy dữ liệu login từ backend
        const response = await axios.put("/api/auth/user/update/password", data);
        const userData = response.data;
        console.log(userData);
        setTimeout(() => {
            window.location.href = '/';
        }, 1000);
        toastr.success('Cập nhật mật khẩu thành công');
    } catch (error) {
        console.log(error);
        toastr.error(error.response.data.message);
        // Xử lý lỗi nếu có
    }
})


// thông báo
$('.form-user-password').validate({
    rules: {
        oldPassword: {
            required: true,
        },
        newPassword: {
            required: true,
        },
        confirmPassword: {
            required: true,
        }
    },
    messages: {
        oldPassword: {
            required: "không được để trống"
        },
        newPassword: {
            required: "không được để trống"
        },
        confirmPassword: {
            required: "không được để trống"
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