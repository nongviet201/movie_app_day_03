const formLoginEl = document.getElementById('form-login');
const emailEl = document.getElementById('email');
const passwordEl = document.getElementById('password');


formLoginEl.addEventListener('submit', async (e) => {
    e.preventDefault();
    const data = {
        email: emailEl.value,
        password: passwordEl.value
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
    } catch (error) {
        console.log(error);
        alert("tai khoan hoac mat khau khong chinh xac");
        // Xử lý lỗi nếu có
    }
})