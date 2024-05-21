document.addEventListener("DOMContentLoaded", () => {
    const renderFavorite = () => {
        const divFavorite = document.getElementById("div-favorites");
        let html = "";

        if (favorites && favorites.length > 0) {
            favorites.forEach(favorite => {
                html += `
                <div class="col-3 mt-2">
                    <div class="movie-item">
                        <div class="movie-poster rounded overflow-hidden position-relative">
                            <a href="/phim/${favorite.movie.id}/${favorite.movie.slug}">
                                <img class="w-100 h-100" src="${favorite.movie.poster}" alt="Bo-doi-bao-thu">
                            </a>
                            <button class="btn btn-danger position-absolute m-1 delete-favorite-button" style="top: 0; right: 0;"
                                    data-id="${favorite.id}">X</button>
                        </div>
                        <a href="/phim/${favorite.movie.id}/${favorite.movie.slug}">
                            <p class="mt-2">${favorite.movie.name}</p>
                        </a>
                    </div>
                </div>`;
            });
        } else {
            html = '<div>Nhấn Follow phim để thêm vào danh sách yêu thích</div>';
        }
        divFavorite.innerHTML = html;
        attachDeleteEventListeners(); // Gắn sự kiện click cho các nút mới
    };

    const attachDeleteEventListeners = () => {
        const deleteButtons = document.querySelectorAll(".delete-favorite-button");

        deleteButtons.forEach(button => {
            button.addEventListener("click", async (e) => {
                e.preventDefault();
                if (currentUser === null) {
                    toastr.error("Vui lòng đăng nhập để sử dụng tính năng");
                    return;
                }
                let id = parseInt(button.getAttribute('data-id'));

                console.log(id);
                const confirm = window.confirm("Bạn có chắc muốn xóa không?");
                if (!confirm) {
                    return;
                }

                try {
                    let res = await axios.delete(`/api/auth/user/favorite/del/${id}`);
                    console.log(res);
                    favorites = favorites.filter(favorite => favorite.id !== id);
                    toastr.success("Đã xóa phim khỏi danh sách yêu thích");
                    // Cập nhật giao diện sau khi xóa phim
                    renderFavorite();
                } catch (e) {
                    console.log(e);
                    toastr.error(e.response.data.message);
                }
            });
        });
    };

    renderFavorite();
});
