- Notification frontend sử dụng toastr
    + Thêm 2 jquery (jquery) cần đặt trước 
    + 2 file css, js sử dụng cdn
    + cấu hình toastr
    + sử dụng toastr.success("..."), toastr.erro("...")
    + Fragment thymeleaf (header, footer) , tạo layout cho ứng dụng

- file js và css

```html
# js
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
# css
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
```

- cấu hình
````javascript
toastr.options = {
  "closeButton": false,
  "debug": false,
  "newestOnTop": false,
  "progressBar": false,
  "positionClass": "toast-top-right",
  "preventDuplicates": false,
  "onclick": null,
  "showDuration": "300",
  "hideDuration": "1000",
  "timeOut": "5000",
  "extendedTimeOut": "1000",
  "showEasing": "swing",
  "hideEasing": "linear",
  "showMethod": "fadeIn",
  "hideMethod": "fadeOut"
}
````
- cách dùng
````javascript
    toastr.success("đánh giá thành công") 
    toastr.error("đánh giá thất bại") 
````
