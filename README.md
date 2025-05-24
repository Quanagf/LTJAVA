------------------------------------Map địa chỉ file----------------------------

PROJECT_ROOT/
├── frontend/                   # Chứa toàn bộ code giao diện người dùng (Client-side)
│   ├── public/                 # Chứa các file tĩnh không cần xử lý (index.html, favicon, images...)
│   │   ├── index.html
│   │   ├── favicon.ico
│   │   └── assets/
│   │       ├── images/
│   │       ├── fonts/
│   │       └── css/            # CSS global hoặc reset CSS
│   ├── src/                    # Chứa mã nguồn chính của Frontend
│   │   ├── App.js              # Component gốc của ứng dụng (hoặc main.js, index.js tùy framework)
│   │   ├── index.js            # Điểm vào của ứng dụng, render App.js
│   │   ├── components/         # Chứa các UI component tái sử dụng (Button, Card, Modal, Navbar...)
│   │   │   ├── common/         # Các component chung, nhỏ lẻ
│   │   │   └── specific/       # Các component lớn, đặc thù cho từng trang/tính năng
│   │   ├── pages/              # (Hoặc views/) Chứa các component đại diện cho từng trang (HomePage, LoginPage, BloodDrivePage...)
│   │   ├── layouts/            # (Tùy chọn) Chứa cấu trúc layout chung (ví dụ: MainLayout có header, footer, sidebar)
│   │   ├── services/           # (Hoặc api/, store/) Chứa logic gọi API, quản lý state (Redux, Zustand, Context API...)
│   │   ├── routes/             # Định nghĩa các tuyến đường (routes) cho Frontend
│   │   ├── assets/             # (Trong src) Chứa các file tĩnh được import vào code (CSS Modules, hình ảnh dùng trong component)
│   │   ├── styles/             # (Tùy chọn) Chứa các file style global, variables, mixins
│   │   ├── utils/              # Chứa các hàm tiện ích dùng chung cho Frontend
│   │   └── config/             # (Tùy chọn) Cấu hình cho Frontend (ví dụ: endpoint API)
│   ├── package.json            # Quản lý dependencies cho Frontend (nếu dùng Node.js/npm/yarn)
│   ├── .env                    # (Tùy chọn) Biến môi trường cho Frontend
│   └── README.md               # Hướng dẫn riêng cho Frontend
│
├── backend-java/               # Chứa toàn bộ code backend Java
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/yourgroupname/blooddonation/  # Gói (package) gốc của ứng dụng
│   │   │   │       ├── BloodDonationApplication.java # File chính khởi chạy ứng dụng (ví dụ: SpringBootApplication)
│   │   │   │       ├── config/                       # Chứa các lớp cấu hình (SecurityConfig, AppConfig, Bean definitions...)
│   │   │   │       ├── controller/                   # (Hoặc web/rest/) Chứa các REST Controllers xử lý HTTP requests
│   │   │   │       │   ├── AuthController.java
│   │   │   │       │   ├── UserController.java
│   │   │   │       │   └── BloodDriveController.java
│   │   │   │       ├── model/                        # (Hoặc domain/entity/) Chứa các lớp Entity (JPA) hoặc POJOs đại diện cho dữ liệu
│   │   │   │       │   ├── User.java
│   │   │   │       │   ├── BloodType.java
│   │   │   │       │   └── DonationRecord.java
│   │   │   │       ├── dto/                          # Data Transfer Objects - Các đối tượng dùng để truyền dữ liệu giữa các lớp hoặc qua API
│   │   │   │       │   ├── UserDTO.java
│   │   │   │       │   └── LoginRequest.java
│   │   │   │       ├── repository/                   # Chứa các interface cho Data Access Objects (DAO) (ví dụ: Spring Data JPA repositories)
│   │   │   │       │   ├── UserRepository.java
│   │   │   │       │   └── DonationRecordRepository.java
│   │   │   │       ├── service/                      # Chứa business logic của ứng dụng
│   │   │   │       │   ├── impl/                     # (Tùy chọn) Chứa các lớp implement interface service
│   │   │   │       │   ├── UserService.java
│   │   │   │       │   └── BloodDriveService.java
│   │   │   │       ├── security/                     # (Tùy chọn) Chứa các lớp liên quan đến bảo mật (JWT Utils, UserDetailsService...)
│   │   │   │       ├── exception/                    # Chứa các lớp exception tùy chỉnh
│   │   │   │       └── util/                         # Chứa các lớp tiện ích
│   │   │   └── resources/
│   │   │       ├── static/                         # (Nếu phục vụ file tĩnh từ backend, nhưng thường frontend sẽ đảm nhiệm)
│   │   │       ├── templates/                      # (Nếu dùng server-side templating như Thymeleaf, JSP)
│   │   │       ├── application.properties          # (Hoặc application.yml) File cấu hình chính của Spring Boot
│   │   │       ├── data.sql                        # (Tùy chọn) Script SQL để khởi tạo dữ liệu ban đầu (Spring Boot tự động chạy)
│   │   │       └── db/migration/                   # (Nếu dùng Flyway hoặc Liquibase để quản lý schema CSDL)
│   │   └── test/
│   │       └── java/
│   │           └── com/yourgroupname/blooddonation/ # Các lớp kiểm thử (Unit test, Integration test)
│   │               ├── controller/
│   │               └── service/
│   ├── pom.xml                 # (Nếu dùng Maven) File quản lý dự án và dependencies
│   ├── build.gradle            # (Nếu dùng Gradle) File quản lý dự án và dependencies
│   ├── .mvn/                   # (Maven wrapper files)
│   ├── mvnw                    # (Maven wrapper executable)
│   ├── mvnw.cmd                # (Maven wrapper executable for Windows)
│   ├── gradlew                 # (Gradle wrapper executable)
│   ├── gradlew.bat             # (Gradle wrapper executable for Windows)
│   └── README.md               # Hướng dẫn riêng cho Backend Java
│
├── database/                   # (Tương tự như gợi ý trước)
│   ├── ERD.png                 # Sơ đồ Quan hệ Thực thể (Entity Relationship Diagram)
│   └── design_notes.txt        # Ghi chú thiết kế CSDL
│
├── docs/                       # (Tương tự như gợi ý trước)
│   ├── api_docs/               # (Có thể dùng SpringDoc OpenAPI / Swagger UI)
│   ├── design_docs/
│   ├── project_report.docx
│   └── presentation_slides.pptx
│
├── .gitignore                  # Cập nhật để bỏ qua các file của Java (target/, .idea/, .classpath, .project, *.class, *.jar, *.war...)
└── README.md                   # Thông tin chung về dự án
