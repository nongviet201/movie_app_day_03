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
    const day = `0${date.getDate()}`.slice(-2);
    const month = `0${date.getMonth() + 1}`.slice(-2);
    const year = date.getFullYear()
    return `${day}/${month}/${year}`;
}

const reviewListEl = document.querySelector(".review-list")

const renderReview = reviews => {
    let html = "";
    reviews.forEach(review => {
        html += `
                ${review.user != null ? `
                 <div class="text-black d-flex flex-column mb-3 p-3 user-review rounded-2 ">
                 <div class="d-flex">
                    <img src="${review.user.avatar}" alt="${review.user.username}">
                    <div class="d-flex flex-column ms-4">
                            <div class="d-flex">
                                <strong class="m-0 pe-2 review-name">${review.user.username}</strong>
                                <p class="m-0 review-time">${formatDate(review.createAt)}</p>
                            </div>
                            <div>
                                <p class="m-0 review-text">${review.content}</p>
                            </div>
                            ${currentUser.id === review.user.id ? `
                            <div class="mt-2">
                                <button onclick="deleteReview(${review.id}" type="button"
                                        class="btn btn-outline-secondary btn-sm btn-delete"
                                        style="--bs-btn-padding-y: .2rem; --bs-btn-padding-x: .3rem; --bs-btn-font-size: .7rem;">
                                    Xóa
                                </button>
                                <button data-review-id="${review.id}" type="button"
                                        class="btn btn-outline-secondary btn-sm btn-edit"
                                        style="--bs-btn-padding-y: .2rem; --bs-btn-padding-x: .3rem; --bs-btn-font-size: .7rem;">
                                    Sửa
                                </button>
                            </div>
                            ` : ''}
                        </div>
                    </div>
                </div> 
                 ` : ''}
                `
    })
    reviewListEl.innerHTML = html;
    editButtonFn();
};

const formReviewEl = document.getElementById("form-review");
const reviewContentEl = document.getElementById("review-content")
const modalReviewEl = document.getElementById("modal-review");

const btnOpenModal = document.querySelector(".btn-open-modal")

btnOpenModal.addEventListener("click", () => {
    myModalReviewEl.show();
    btnCreateReviewEl.textContent = `Tạo đánh giá`
});

// đóng modal
modalReviewEl.addEventListener('hidden.bs.modal', event => {
    console.log("Su kien dong modal")
    currentRating = 0;
    reviewContentEl.value = "";
    ratingValue.textContent = "";
    resetStars();
    idReviewEdit = null;
})

const btnCreate = document.querySelector(".btn-create")

// submit form
formReviewEl.addEventListener("submit", async (e) => {
    e.preventDefault();

    // TODO: validate các thông tin (sử dụng thư viện jquery validation

    if (currentRating === 0) {
        toastr.error("vui lòng chọn số sao");
        return;
    }

    if (reviewContentEl.value.trim() === "") {
        toastr.error("nội dung đánh giá không được để trống");
        return;
    }

    const data = {
        content: reviewContentEl.value,
        rating: currentRating,
        movieId: movie.id
    }

    if (idReviewEdit) {
        await editReview(data)
    } else {
        await createReview(data)
    }
})

// tạo review
const createReview = async (data) => {
    try {
        let res = await axios.post("/api/reviews", data)
        reviews.unshift(res.data);
        renderReview(reviews)
        // đóng modal
        myModalReviewEl.hide();
        toastr.success("Đánh giá thành công")
    } catch (e) {
        console.log(e);
        toastr.error("có lỗi sảy ra vui lòng thử lại sau");
    }
}


// mở modal chỉnh sửa và hiển thị dữ liệu của review đó
const modalReviewTitleEl = document.querySelector(".modal-review-title")
const btnCreateReviewEl = document.getElementById("btn-create-review")

let idReviewEdit = null;

const editButtonFn = () => {
    const btnEditReviewEl = document.querySelectorAll(".btn-edit");
    btnEditReviewEl.forEach(button => {
        button.addEventListener("click", async () => {
            console.log("sự kiện sửa review")
            const reviewIdNumber = parseInt(button.dataset.reviewId, 10);
            try {
                let reponse = await axios.get("/api/reviews/"+reviewIdNumber)
                let review = reponse.data;
                reviewContentEl.value = review.content;
                currentRating = review.rating;
                ratingValue.textContent = `Bạn đã đánh giá ${currentRating} sao.`;
                highlightStars(currentRating)
                idReviewEdit = reviewIdNumber
            } catch (e) {
                console.log(e)
                toastr.error("bạn không phải là tác giả");
            }
        })
    });
}

editButtonFn();


// sửa review
const editReview = async (data) => {
    try {
        const res = await axios.put(`/api/reviews/${idReviewEdit}`, data)
        const editedReviewIndex = reviews.findIndex(review => review.id === idReviewEdit);
        if (editedReviewIndex !== -1) {
            reviews[editedReviewIndex] = res.data;
            renderReview(reviews);
        }
        myModalReviewEl.hide()
        toastr.success("đã sửa thành công")
    } catch (error) {
        console.log(error)
        toastr.error("bạn không phải là tác giả");
    }
}

// xóa review
const deleteReview = async (id) => {
    const confirm = window.confirm("Bạn có chắc muốn xóa không")
    if (!confirm)
        return;

    try {
        const res = await axios.delete(`/api/reviews/${id}`)
        reviews = reviews.filter(review => review.id !== id);
        toastr.success("đã xóa thành công")
        renderReview(reviews)
    } catch (e) {
        console.log(e)
        toastr.error("bạn không phải là tác giả");
    }
}

// thông báo
$('#form-review').validate({
    rules: {
        content: {
            required: true,
        }
    },
    messages: {
        content: {
            required: "không được để trống nội dung",
        }
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


