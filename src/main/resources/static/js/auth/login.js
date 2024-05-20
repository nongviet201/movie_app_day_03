// LOGIN

const formLoginEl = document.getElementById('form-login');
const emailEl = document.getElementById('email');
const passwordEl = document.getElementById('password');


formLoginEl.addEventListener('submit', async (e) => {
    e.preventDefault();
    const data = {
        email: emailEl.value,
        password: passwordEl.value
    }
    if (!$('#form-login').valid()) {
        return;
    }

    console.log(data)
    try {
        // Gửi yêu cầu lấy dữ liệu login từ backend
        const response = await axios.post("/api/auth/login", data);
        const loginRes = response.data;
        console.log(loginRes);
        setTimeout(() => {
            window.location.href = '/';
            }, 1000);
        toastr.success('Login Successfully');
    } catch (error) {
        console.log(error);
        toastr.error(error.response.data.message);
        // Xử lý lỗi nếu có
    }
})


// thông báo
$('#form-login').validate({
    rules: {
        email: {
            required: true,
            email: true,
        },
        password: {
            required: true,
        }
    },
    messages: {
        email: {
            required: "không được để trống email",
            email: "email không đúng định dạng "
        },
        password: {
            required: "không được để trống password"
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
