const stars = document.querySelectorAll(".star");
const ratingValue = document.getElementById("rating-value");


let currentRating = 0;

stars.forEach((star) => {
    star.addEventListener("mouseover", () => {
        resetStars();
        const rating = parseInt(star.getAttribute("data-rating"));
        highlightStars(rating);
    });

    star.addEventListener("mouseout", () => {
        resetStars();
        highlightStars(currentRating);
    });

    star.addEventListener("click", () => {
        currentRating = parseInt(star.getAttribute("data-rating"));
        ratingValue.textContent = `Bạn đã đánh giá ${currentRating} sao.`;
        highlightStars(currentRating);
    });
});

function resetStars() {
    stars.forEach((star) => {
        star.classList.remove("active");
    });
}

function highlightStars(rating) {
    stars.forEach((star) => {
        const starRating = parseInt(star.getAttribute("data-rating"));
        if (starRating <= rating) {
            star.classList.add("active");
        }
    });
}

// render review
const formatDate = dateStr => {
    const date = new Date(dateStr);
    const day = `0${date.getDate()}`.slice(-2); // 01 -> 01, 015 -> 15
    const month = `0${date.getMonth() + 1}`.slice(-2);
    const year = date.getFullYear();
    return `${day}/${month}/${year}`;
}
const reviewListEl = document.querySelector(".review-list");
const renderReview = reviews => {
    let html = "";
    reviews.forEach(review => {
        html += `
            <div class="rating-item d-flex align-items-center mb-3 pb-3">
                <div class="rating-avatar">
                    <img src="${review.user.avatar}" alt="${review.user.name}">
                </div>
                <div class="rating-info ms-3">
                    <div class="d-flex align-items-center">
                        <p class="rating-name mb-0">
                            <strong>${review.user.name}</strong>
                        </p>
                        <p class="rating-time mb-0 ms-2">${formatDate(review.createAt())}</p>
                    </div>
                    <div class="rating-star">
                       
                    </div>
                    
                   ${currentUser.id === review.user.id ? ' <p class="mb-0 fw-bold">\n                            <span class="rating-icon"><i class="fa fa-star"></i></span>\n                            <span>${review.rating}/10 Tuyệt vời</span>\n                        </p>' : ""}
                    <p class="rating-content mt-1 mb-0 text-muted">${review.content}</p>
                </div>
            </div>
        `
    })

    reviewListEl.innerHTML = html;
}

// Tạo review
const formReviewEl = document.getElementById("form-review");
const reviewContentEl = document.getElementById("review-content");
const modalReviewEl = document.getElementById("modal-review");
const myModalReviewEl = new bootstrap.Modal(modalReviewEl, {
    keyboard: false
})


modalReviewEl.addEventListener('hidden.bs.modal', event => {
    console.log("Su kien dong modal")
    currentRating = 0;
    reviewContentEl.value = "";
    ratingValue.textContent = "";
    resetStars();
})

toastr.success("đánh giá thành công")

formReviewEl.addEventListener("submit", async (e) => {
    e.preventDefault();
    // TODO: Validate các thông tin (sử dụng thư jQuery Validation)
    if (currentRating === 0) {
        alert("Vui lòng chọn số sao");

        return;
    }

    if (reviewContentEl.value.trim() === "") {
        alert("Nội dung đánh giá không được để trống");
        return;
    }

    const data = {
        content: reviewContentEl.value,
        rating: currentRating,
        movieId: movie.id
    }

    // Gọi API
    try {
        let res = await axios.post("/api/reviews", data);
        reviews.unshift(res.data);
        renderReview(reviews);
        toastr.success("đánh giá thành công")

        // Dong modal
        myModalReviewEl.hide();

        // reset
    } catch (e) {
        console.log(e)
    }
})

//nút xóa
document.addEventListener("DOMContentLoaded", function () {
    const deleteButtons = document.querySelectorAll(".review-delete");

    // Xử lý sự kiện click cho nút "Xóa"
    deleteButtons.forEach(button => {
        button.addEventListener("click", async (e) => {
            const reviewId = button.getAttribute("data-review-id");
            try {
                const res = await axios.delete("/api/reviews/" + reviewId);
                renderReview(reviews);
            } catch (error) {
                console.log(error);}
        });
    });
});

const editReviewBtn = document.getElementById("btn-edit-review");
const createReviewBtn = document.getElementById("btn-create-review");

//nút edit

document.addEventListener("DOMContentLoaded", function () {
    const editButtons = document.querySelectorAll(".review-edit");
    let reviewId; // Để lưu trữ ID của review cần chỉnh sửa


    // Xử lý sự kiện click cho nút "Chỉnh sửa"
    editButtons.forEach(button => {
        button.addEventListener("click", async (e) => {
            createReviewBtn.classList.add("hide");
            reviewId = button.getAttribute("data-review-id"); // Lưu ID của review cần chỉnh sửa

            try {
                // Gửi yêu cầu lấy dữ liệu của review từ backend
                const response = await axios.get("/api/reviews/" + reviewId);
                const reviewData = response.data; // Dữ liệu của review

                // Hiển thị dữ liệu của review lên form
                currentRating = parseInt(reviewData.rating);
                highlightStars(currentRating);
                reviewContentEl.value = reviewData.content;
                ratingValue.textContent = `Bạn đã đánh giá ${currentRating} sao.`;
            } catch (error) {
                console.log(error);
                // Xử lý lỗi nếu có
            }
        });
    });


    document.getElementById("btn-edit-review").addEventListener("click", async (e) => {
            e.preventDefault();
            // TODO: Validate các thông tin (sử dụng thư jQuery Validation)
            if (currentRating === 0) {
                alert("Vui lòng chọn số sao");
                return;
            }

            if (reviewContentEl.value.trim() === "") {
                alert("Nội dung đánh giá không được để trống");
                return;
            }

            const data = {
                content: reviewContentEl.value,
                rating: currentRating,
                movieId: movie.id
            }

            console.log(data)

            // Gọi API
            try {
                let res = await axios.put("/api/reviews/" + reviewId, data);

            } catch (e) {
                console.log(e)
            }
        })
    });
