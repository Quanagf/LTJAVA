import React from 'react';

const Footer = () => {
    return (
        <footer className="dkhm-footer">
            <div className="footer-content">
                <div>
                    <b>Thông Tin Liên Hệ</b><br />
                    📞 1900 1234<br />
                    ✉ info@hienmaunv.org.vn<br />
                    📍 123 Đường ABC, Quận XYZ, TP. Hồ Chí Minh
                </div>
                <div>
                    <b>Giờ Làm Việc</b><br />
                    Thứ 2 - Thứ 6: 7:30 - 17:00<br />
                    Thứ 7 - Chủ nhật: 7:30 - 12:00
                </div>
                <div>
                    <b>Liên Kết Hữu Ích</b><br />
                    <a href="#">Về chúng tôi</a><br />
                    <a href="#">Điều khoản sử dụng</a><br />
                    <a href="#">Liên hệ</a>
                </div>
                <div>
                    <b>Kết Nối</b><br />
                    <span style={{fontSize: '1.4em'}}>
                        <a href="#" style={{color:'#fff', marginRight:'8px'}}>&#x25CF;</a>
                        <a href="#" style={{color:'#fff', marginRight:'8px'}}>&#x25CF;</a>
                        <a href="#" style={{color:'#fff'}}>&#x25CF;</a>
                    </span>
                </div>
            </div>
            <div className="footer-bottom">
                © 2024 Trung tâm Hiến máu Nhân đạo. Tất cả quyền được bảo lưu.
            </div>
        </footer>
    );
};

export default Footer;
