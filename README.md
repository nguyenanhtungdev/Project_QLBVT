# Project_QLBVT

Dự án quản lý bán vé tàu tại nhà ga

## Hướng dẫn dùng GIT

I. Thao tác trên Local:

1. Chuyển sang nhánh chính bằng lệnh `git checkout main`.
2. Pull dữ liệu mới từ nhánh `main` về bằng lệnh `git pull`.\
Trong trường hợp đã chỉnh sửa source code thì phải dùng lệnh `git stash` để lưu lại tất cả thay đổi.
2. Tạo nhánh mới bằng lệnh `git checkout -b feature/<tính năng>`.\
Nếu sửa lỗi thì `git checkout -b hotfix/<tính năng>`.
3. Chỉnh sửa xong thì add vào để chuyển bị commit `git add <file đã thay đổi>`.\
**Lưu ý:** Chỉ add những file cần commit, không được add tất cả khi chưa kiểm tra kỹ.
4. Commit những thay đổi vừa add bằng lệnh `git commit -m "<tin nhắn>"`.\
**Lưu ý:** Tin nhắn commit phải có ý nghĩa, không được đặt quá chung dẫn đến không biết là gì.
5. Đẩy lên GitHub bằng lệnh `git push -u origin feature/<tính năng>`.\
Từ lần thứ 2 trở đi chỉ cần dùng lệnh `git push`.

II. Thao tác trên GitHub:

1. Vào repo: <https://github.com/nguyenanhtungdev/Project_QLBVT>.
2. Chọn tab *Pull Requests*.
3. Chọn *New pull request*.
4. Chọn nhánh base là `main`\
Chọn nhánh compare là nhánh cần gộp
5. Chọn *Create pull request*.
6. Nhập tiêu đề có ý nghĩa. *Ví dụ: Gộp tính năng bán vé*.
7. Chọn *Reviewers* và *Assignees* là Tú.\
Nếu phần bạn làm có ảnh hưởng, liên quan đến người khác thì phải thêm người đó vào *Reviewers*.
8. Chọn *Create pull request*.
