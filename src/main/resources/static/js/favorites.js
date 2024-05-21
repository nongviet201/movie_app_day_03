const favoriteBtn = document.getElementById("favoriteBtn");
const unfavoriteBtn = document.getElementById("unFavoriteBtn");
const data = {
    userId: currentUser.id,
    movieId: movie.id
}

console.log(data)

const check = async () => {
    try {
        let res = await axios.get("/api/auth/user/favorite/get", data)
        console.log(res)
        if (res.data) {
            favoriteBtn.classList.add("d-none");
            unfavoriteBtn.classList.remove("d-none");
        }
    } catch (e) {
        console.log(e);
    }
}

document.addEventListener("DOMContentLoaded", function() {
    check();
});


favoriteBtn.addEventListener("click", async (e) => {
    e.preventDefault();

    if (currentUser=== null) {
        toastr.error("vui lòng đăng nhập để sử dụng tính năng");
        return;
    }

    console.log(data)
    try {
        let res = await axios.post("/api/auth/user/favorite/add", data)
        console.log(res)
        toastr.success("đã thêm phim vào danh sách yêu thích")
    } catch (e) {
        console.log(e);
        toastr.error(e.response.data.message);
    }
})

unfavoriteBtn.addEventListener("click", async (e) => {
    e.preventDefault();

    if (currentUser === null) {
        toastr.error("vui lòng đăng nhập để sử dụng tính năng");
        return;
    }

    console.log(data)
    try {
        let res = await axios.delete("/api/auth/user/favorite/del", data)
        console.log(res)
        toastr.success("đã xóa phim khỏi danh sách yêu thích")
    } catch (e) {
        console.log(e);
        toastr.error(e.response.data.message);
    }
})