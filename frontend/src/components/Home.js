import React from 'react';
import { Link } from 'react-router-dom';

const Home = () => {
    return (
        <div>
            {/* Banner lớn */}
            <section className="home-hero">
                <div className="home-hero-content">
                    <h1>MỘT GIỌT MÁU - TRIỆU TẤM LÒNG</h1>
                    <p>Chia sẻ sự sống - Kết nối yêu thương</p>
                    <Link to="/donate" className="btn-hero">ĐĂNG KÝ HIẾN MÁU NGAY</Link>
                </div>
            </section>

            {/* Thống kê nhanh */}
            <section className="home-stats">
                <div>
                    <div className="stats-icon">🩸</div>
                    <b>125,700+</b>
                    <span>Người hiến máu</span>
                </div>
                <div>
                    <div className="stats-icon">🏥</div>
                    <b>82,456+</b>
                    <span>Đơn vị máu tiếp nhận</span>
                </div>
                <div>
                    <div className="stats-icon">➕</div>
                    <b>64,320+</b>
                    <span>Bệnh nhân được cứu sống</span>
                </div>
                <div>
                    <div className="stats-icon">👍</div>
                    <b>97%</b>
                    <span>Tỉ lệ hài lòng</span>
                </div>
            </section>

            {/* Giới thiệu */}
            <section className="home-about">
                <div className="about-content">
                    <h2>Về Chương Trình Hiến Máu</h2>
                    <p>
                        Chương trình hiến máu nhân đạo là một hoạt động xã hội quan trọng, nhằm kêu gọi sự chung tay của cộng đồng để cứu giúp những bệnh nhân cần máu.<br />
                        Mỗi năm, Việt Nam cần khoảng 1,8 - 2 triệu đơn vị máu để chữa trị cho người bệnh. Hiến máu tình nguyện không chỉ là nghĩa cử cao đẹp mà còn giúp bảo vệ sức khỏe cho chính người hiến máu.
                    </p>
                    <a href="#" className="btn-learnmore">Tìm hiểu thêm</a>
                </div>
                <div className="about-image">
                    <img src="/assets/img/dangkyhienmau.png" alt="Hiến máu" />
                    <div className="about-note">
                        Mỗi lần hiến máu<br />
                        Có thể cứu sống tới 3 người!
                    </div>
                </div>
            </section>

            {/* Điều kiện hiến máu */}
            <section className="home-block">
                <h2>Điều Kiện Hiến Máu</h2>
                <div className="block-grid">
                    <div className="block-item">
                        <span className="block-check">✔</span> 
                        <b>Độ tuổi</b><br />
                        <span className="block-small">Từ 18 đến 60 tuổi, người từ 60-65 tuổi có thể hiến máu nếu đủ sức khỏe</span>
                    </div>
                    <div className="block-item">
                        <span className="block-check">✔</span> 
                        <b>Cân nặng</b><br />
                        <span className="block-small">Nam ≥ 50kg, Nữ ≥ 45kg</span>
                    </div>
                    <div className="block-item">
                        <span className="block-check">✔</span> 
                        <b>Sức khỏe</b><br />
                        <span className="block-small">Tình trạng sức khỏe tốt, không mắc các bệnh mãn tính</span>
                    </div>
                    <div className="block-item">
                        <span className="block-check">✔</span> 
                        <b>Bệnh lý</b><br />
                        <span className="block-small">Không mắc các bệnh truyền nhiễm như HIV, viêm gan B, C...</span>
                    </div>
                    <div className="block-item">
                        <span className="block-check">✔</span> 
                        <b>Thời gian</b><br />
                        <span className="block-small">Giữa 2 lần hiến máu ít nhất là 12 tuần đối với cả nam và nữ</span>
                    </div>
                    <div className="block-item">
                        <span className="block-check">✔</span> 
                        <b>Tiền sử</b><br />
                        <span className="block-small">Không có tiền sử bệnh lý nghiêm trọng như tim mạch, huyết áp cao, đột quỵ...</span>
                    </div>
                </div>
                <a href="#" className="btn-outline mt-18">Xem thêm điều kiện</a>
            </section>

            {/* Quy trình hiến máu */}
            <section className="home-block">
                <h2>Quy Trình Hiến Máu</h2>
                <div className="block-process">
                    <div><span className="proc-icon">📝</span> Đăng ký và khai báo y tế</div>
                    <div><span className="proc-icon">🩺</span> Khám sàng lọc</div>
                    <div><span className="proc-icon">🧪</span> Xét nghiệm máu và chỉ số cơ bản</div>
                    <div><span className="proc-icon">❤️</span> Hiến máu</div>
                    <div><span className="proc-icon">🛏️</span> Nghỉ ngơi và phục hồi</div>
                </div>
            </section>

            {/* Tin tức & Sự kiện */}
            <section className="home-news">
                <h2>Tin Tức & Sự Kiện</h2>
                <div className="news-list">
                    <div className="news-card">
                        <img src="/assets/img/news-1.jpg" alt="" />
                        <div className="news-meta">
                            <span className="news-tag">Sự kiện</span> | 15/06/2024
                        </div>
                        <b>Ngày hội hiến máu tình nguyện toàn quốc 2024</b>
                        <p>Chương trình hiến máu nhân đạo lớn nhất năm với sự tham gia của hàng nghìn tình nguyện viên trên cả nước.</p>
                        <a href="#">Xem chi tiết &rarr;</a>
                    </div>
                    <div className="news-card">
                        <img src="/assets/img/news-2.jpg" alt="" />
                        <div className="news-meta">
                            <span className="news-tag">Tin tức</span> | 10/06/2024
                        </div>
                        <b>Việt Nam đạt mốc 2 triệu đơn vị máu trong năm 2023</b>
                        <p>Lần đầu tiên Việt Nam đạt được con số kỷ lục về lưu trữ máu tình nguyện, đáp ứng đủ nhu cầu cấp cứu, điều trị.</p>
                        <a href="#">Xem chi tiết &rarr;</a>
                    </div>
                    <div className="news-card">
                        <img src="/assets/img/news-3.jpg" alt="" />
                        <div className="news-meta">
                            <span className="news-tag">Hướng dẫn</span> | 03/06/2024
                        </div>
                        <b>Chế độ dinh dưỡng cho người hiến máu</b>
                        <p>Những thực phẩm nên ăn trước và sau khi hiến máu để đảm bảo sức khỏe và phục hồi nhanh chóng.</p>
                        <a href="#">Xem chi tiết &rarr;</a>
                    </div>
                </div>
                <a href="#" className="btn-outline mt-18">Xem tất cả tin tức</a>
            </section>

            {/* Tài liệu & Chia sẻ kinh nghiệm */}
            <section className="home-doc-blog">
                <div className="home-doc">
                    <h2>Tài liệu về các loại máu</h2>
                    <ul>
                        <li>📄 <a href="#">Kiến thức về nhóm máu</a> <span className="doc-dl"><a href="#">Tải xuống</a></span></li>
                        <li>📄 <a href="#">Hướng dẫn hiến máu an toàn</a> <span className="doc-dl"><a href="#">Tải xuống</a></span></li>
                        <li>📄 <a href="#">Chế độ dinh dưỡng cho người hiến máu</a> <span className="doc-dl"><a href="#">Tải xuống</a></span></li>
                        <li>📄 <a href="#">Các câu hỏi thường gặp</a> <span className="doc-dl"><a href="#">Tải xuống</a></span></li>
                    </ul>
                </div>
                <div className="home-blog">
                    <h2>Blog chia sẻ kinh nghiệm</h2>
                    <ul>
                        <li>
                            <b>Lần đầu hiến máu – Trải nghiệm đáng nhớ</b>
                            <span>Nguyễn Văn A • 21/05/2024</span>
                        </li>
                        <li>
                            <b>Sợi chỉ sức khỏe khi hiến máu định kỳ</b>
                            <span>Trần Thị B • 15/05/2024</span>
                        </li>
                        <li>
                            <b>Hiến máu nhóm hiếm – Câu chuyện của tôi</b>
                            <span>Lê Văn C • 08/05/2024</span>
                        </li>
                        <li>
                            <b>Tình nguyện viên tại trung tâm hiến máu</b>
                            <span>Phạm Thị D • 05/05/2024</span>
                        </li>
                    </ul>
                    <a href="#" className="doc-more">Xem tất cả bài viết &rarr;</a>
                </div>
            </section>

            {/* Nhận thông báo */}
            <section className="home-subscribe">
                <h2>Nhận thông báo về các đợt hiến máu</h2>
                <p>Đăng ký để nhận thông tin về các sự kiện hiến máu mới nhất tại địa phương của bạn</p>
                <form className="subscribe-form">
                    <input type="email" placeholder="Nhập địa chỉ email của bạn" required />
                    <button type="submit">Đăng ký</button>
                </form>
            </section>
        </div>
    );
};

export default Home;
