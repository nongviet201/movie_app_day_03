const favoriteBtn = document.getElementById("favoriteBtn");
const unfavoriteBtn = document.getElementById("unFavoriteBtn");

const userId = currentUser.id;
const movieId = movie.id;

const data = {
    userId: currentUser.id,
    movieId: movie.id
}

let favoriteData;
console.log(data)

let check = async (userId, movieId) => {
    try {
        let res = await axios.get("/api/auth/user/favorite/get/"+ userId+"/"+ movieId)
        favoriteData = res.data;
        console.log(favoriteData)
        if (favoriteData) {
            favoriteBtn.classList.  add("d-none");
            unfavoriteBtn.classList.remove("d-none");
        } else {
            favoriteBtn.classList.remove("d-none");
            unfavoriteBtn.classList.add("d-none");
        }
    } catch (e) {
        console.log(e);
    }
}

document.addEventListener("DOMContentLoaded",  check(userId, movieId));


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
        check(userId, movieId)
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
        let res = await axios.delete("/api/auth/user/favorite/del/"+favoriteData.id)
        console.log(res)
        toastr.success("đã xóa phim khỏi danh sách yêu thích")
        favoriteData = null;
        check(userId, movieId)
    } catch (e) {
        console.log(e);
        toastr.error(e.response.data.message);
    }
})