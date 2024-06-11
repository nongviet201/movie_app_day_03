//LOGOUT

const logout = document.getElementById("logout");
logout.addEventListener("click", async () => {
    try {
        await axios.post("/api/auth/logout");
        setTimeout(() => {
            window.location.href = '/';
        }, 1000); // sau 1s chuyển hướng về trang chủ
        toastr.success("Đăng xuat thành công");
    } catch (error) {
        toastr.error("Bạn chưa đăng nhập");
    }
})


//Favorite

const favoriteBtn = document.getElementById("favorite");
favoriteBtn.addEventListener("click", (e) => {
    e.preventDefault();
    window.location.href = '/user-info/' +currentUser.id+ '/favorites';
})