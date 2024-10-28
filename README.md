# Project_QLBVT

Dự án quản lý bán vé tàu tại nhà ga

## Hướng dẫn dùng GIT

I. Thao tác trên Local:
1. Chuyển sang nhánh chính bằng lệnh `git checkout main`.
2. Tạo nhánh mới bằng lệnh `git checkout -b feature/<tính năng>`.
<br>Nếu sửa lỗi thì `git checkout -b hotfix/<tính năng>`.
3. Chỉnh sửa xong thì add vào để chuyển bị commit `git add <file đã thay đổi>`.
<br>**Lưu ý:** Chỉ add những file cần commit, không được add tất cả khi chưa kiểm tra kỹ.
4. Commit những thay đổi vừa add bằng lệnh `git commit -m "<tin nhắn>"`.
<br>**Lưu ý:** Tin nhắn commit phải có ý nghĩa, không được đặt quá chung dẫn đến không biết là gì.
5. Đẩy lên GitHub bằng lệnh `git push -u origin feature/<tính năng>`.
<br>Từ lần thứ 2 trở đi chỉ cần dùng lệnh `git push`.

II. Thao tác trên GitHub:
1. Vào repo: <https://github.com/nguyenanhtungdev/Project_QLBVT>.
2. Chọn tab *Pull Requests*.
3. Chọn *New pull request*.
4. Chọn nhánh base là `main`
<br>Chọn nhánh compare là nhánh cần gộp
5. Chọn *Create pull request*.
6. Nhập tiêu đề có ý nghĩa. *Ví dụ: Gộp tính năng bán vé*.
7. Chọn *Reviewers* và *Assignees* là Tú.
<br>Nếu phần bạn làm có ảnh hưởng, liên quan đến người khác thì phải thêm người đó vào *Reviewers*.
8. Chọn *Create pull request*.


## COMMIT TAGS

- feat:  a new feature is introduced with the changes
- fix: a bug fix has occurred
- chore: changes that do not relate to a fix or feature and don't modify src or test files (for example updating dependencies)
- refactor: refactored code that neither fixes a bug nor adds a feature
- docs: updates to documentation such as a the README or other markdown files
- style: changes that do not affect the meaning of the code, likely related to code formatting such as white-space, missing semi-colons, and so on.
- test: including new or correcting previous tests
- perf: performance improvements
- ci: continuous integration related
- build: changes that affect the build system or external dependencies
- revert: reverts a previous commit
