import React, { useState } from 'react';

const Donate = () => {
    const [formData, setFormData] = useState({
        fullName: '',
        birthDate: '',
        gender: 'Nam',
        idNumber: '',
        phone: '',
        email: '',
        address: '',
        bloodType: 'A',
        donationDate: ''
    });

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log('Form submitted:', formData);
        alert('Đăng ký hiến máu thành công! Chúng tôi sẽ liên hệ với bạn sớm.');
    };

    return (
        <div>
            {/* Banner */}
            <section className="dkhm-banner">
                <div className="dkhm-banner-content">
                    <h1>Đăng Ký Hiến Máu Nhân Đạo</h1>
                    <p>Một giọt máu cho đi, một cuộc đời ở lại</p>
                </div>
            </section>

            {/* Đăng ký form */}
            <main>
                <section className="dkhm-form-box">
                    <form className="dkhm-form" onSubmit={handleSubmit}>
                        <h2>Thông Tin Người Hiến Máu</h2>
                        
                        <div className="dkhm-form-group">
                            <label>Họ và tên</label>
                            <input 
                                type="text" 
                                name="fullName"
                                value={formData.fullName}
                                onChange={handleChange}
                                required 
                            />
                        </div>

                        <div className="dkhm-form-row">
                            <div className="dkhm-form-group">
                                <label>Ngày tháng năm sinh</label>
                                <input 
                                    type="date" 
                                    name="birthDate"
                                    value={formData.birthDate}
                                    onChange={handleChange}
                                    required 
                                />
                            </div>
                            <div className="dkhm-form-group">
                                <label>Giới tính</label>
                                <select 
                                    name="gender"
                                    value={formData.gender}
                                    onChange={handleChange}
                                    required
                                >
                                    <option value="Nam">Nam</option>
                                    <option value="Nữ">Nữ</option>
                                    <option value="Khác">Khác</option>
                                </select>
                            </div>
                        </div>

                        <div className="dkhm-form-row">
                            <div className="dkhm-form-group">
                                <label>Số CMND/CCCD</label>
                                <input 
                                    type="text" 
                                    name="idNumber"
                                    value={formData.idNumber}
                                    onChange={handleChange}
                                    required 
                                />
                            </div>
                            <div className="dkhm-form-group">
                                <label>Số điện thoại</label>
                                <input 
                                    type="text" 
                                    name="phone"
                                    value={formData.phone}
                                    onChange={handleChange}
                                    required 
                                />
                            </div>
                        </div>

                        <div className="dkhm-form-group">
                            <label>Email</label>
                            <input 
                                type="email" 
                                name="email"
                                value={formData.email}
                                onChange={handleChange}
                                required 
                            />
                        </div>

                        <div className="dkhm-form-group">
                            <label>Địa chỉ</label>
                            <input 
                                type="text" 
                                name="address"
                                value={formData.address}
                                onChange={handleChange}
                                required 
                            />
                        </div>

                        <div className="dkhm-form-row">
                            <div className="dkhm-form-group">
                                <label>Nhóm máu (nếu biết)</label>
                                <select 
                                    name="bloodType"
                                    value={formData.bloodType}
                                    onChange={handleChange}
                                >
                                    <option value="A">A</option>
                                    <option value="B">B</option>
                                    <option value="AB">AB</option>
                                    <option value="O">O</option>
                                </select>
                            </div>
                            <div className="dkhm-form-group">
                                <label>Ngày muốn hiến máu</label>
                                <input 
                                    type="date"
                                    name="donationDate"
                                    value={formData.donationDate}
                                    onChange={handleChange}
                                />
                            </div>
                        </div>

                        <button type="submit" className="btn-submit">Đăng ký hiến máu</button>
                    </form>
                </section>

                {/* Điều kiện cần đáp ứng */}
                <section className="dkhm-block">
                    <h2>Điều Kiện Cần Đáp Ứng</h2>
                    <div className="dkhm-grid">
                        <div className="dkhm-check-item">
                            <span>✔</span> Độ tuổi<br />
                            <small>18-60 tuổi</small>
                        </div>
                        <div className="dkhm-check-item">
                            <span>✔</span> Cân nặng<br />
                            <small>Nam ≥ 50kg, Nữ ≥ 45kg</small>
                        </div>
                        <div className="dkhm-check-item">
                            <span>✔</span> Sức khỏe<br />
                            <small>Tình trạng sức khỏe tốt</small>
                        </div>
                        <div className="dkhm-check-item">
                            <span>✔</span> Bệnh lý<br />
                            <small>Không mắc các bệnh truyền nhiễm</small>
                        </div>
                        <div className="dkhm-check-item">
                            <span>✔</span> Thời gian<br />
                            <small>Giữa 2 lần hiến máu ≥ 12 tuần</small>
                        </div>
                        <div className="dkhm-check-item">
                            <span>✔</span> Tiền sử<br />
                            <small>Không có tiền sử bệnh lý nghiêm trọng</small>
                        </div>
                    </div>
                </section>

                {/* Quy trình hiến máu */}
                <section className="dkhm-block">
                    <h2>Quy Trình Hiến Máu</h2>
                    <div className="dkhm-proc-grid">
                        <div className="dkhm-proc-item">
                            <div className="dkhm-proc-icon">📝</div>
                            <div>Đăng ký & khai báo y tế</div>
                        </div>
                        <div className="dkhm-proc-item">
                            <div className="dkhm-proc-icon">👩‍⚕️</div>
                            <div>Khám sàng lọc</div>
                        </div>
                        <div className="dkhm-proc-item">
                            <div className="dkhm-proc-icon">🧪</div>
                            <div>Xét nghiệm máu</div>
                        </div>
                        <div className="dkhm-proc-item">
                            <div className="dkhm-proc-icon">❤️</div>
                            <div>Hiến máu</div>
                        </div>
                        <div className="dkhm-proc-item">
                            <div className="dkhm-proc-icon">🛏️</div>
                            <div>Nghỉ ngơi và phục hồi</div>
                        </div>
                    </div>
                </section>

                {/* Lợi ích */}
                <section className="dkhm-block">
                    <h2>Lợi ích Khi Tham Gia Hiến Máu</h2>
                    <div className="dkhm-grid">
                        <div className="dkhm-check-item"><span>✔</span> Được khám sức khỏe miễn phí</div>
                        <div className="dkhm-check-item"><span>✔</span> Được xét nghiệm máu miễn phí</div>
                        <div className="dkhm-check-item"><span>✔</span> Được cấp giấy chứng nhận hiến máu</div>
                        <div className="dkhm-check-item"><span>✔</span> Được tư vấn sức khỏe</div>
                        <div className="dkhm-check-item"><span>✔</span> Được hỗ trợ chi phí đi lại</div>
                        <div className="dkhm-check-item"><span>✔</span> Góp phần cứu giúp người bệnh</div>
                    </div>
                </section>
            </main>
        </div>
    );
};

export default Donate;
