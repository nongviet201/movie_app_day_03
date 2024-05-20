
// REGISTER

// Xử lý register
const formRegisterEl = document.getElementById('form-register');
const emailEl = document.getElementById('email');
const passwordEl = document.getElementById('password');
const userNameEl = document.getElementById('userName');
const confirmPassword = document.getElementById('confirmPassword');

formRegisterEl.addEventListener("submit", async (e) => {
    e.preventDefault();

    if (emailEl.value.trim() === "") {
        alert("Email không được để trống");
        return;
    }

    if (passwordEl.value.trim() === "") {
        alert("Password không được để trống");
        return;
    }

    if (passwordEl.value.trim() !== confirmPassword.value.trim()) {
        alert("Password khong chính xác");
        return;
    }

    const data = {
        email: emailEl.value,
        password: passwordEl.value,
        name: userNameEl.value,
        confirmPassword: confirmPassword.value
    };

    console.log(data);

    try {
        const response = await axios.post('/api/auth/register', data);
        if (response.status === 200) {
            toastr.success("Đăng ký thành công");
            setTimeout(() => {
                window.location.href = '/';
            }, 1000); // sau 1s chuyển hướng về trang chủ
        }
    } catch (error) {
        console.error(error);
        toastr.error("Tai khoan hoac mat khau khong dung");
    }
})
