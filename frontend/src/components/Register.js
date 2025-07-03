import React, { useState } from 'react';
import { Link } from 'react-router-dom';

const Register = () => {
    const [isLogin, setIsLogin] = useState(true);
    const [formData, setFormData] = useState({
        email: '',
        password: '',
        fullName: '',
        phone: '',
        confirmPassword: ''
    });

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        if (isLogin) {
            console.log('Login:', { email: formData.email, password: formData.password });
            alert('Đăng nhập thành công!');
        } else {
            console.log('Register:', formData);
            alert('Đăng ký thành công!');
        }
    };

    return (
        <div>
            {/* Banner */}
            <section className="auth-banner">
                <div className="auth-banner-content">
                    <h1>{isLogin ? 'Đăng Nhập' : 'Đăng Ký'}</h1>
                    <p>Tham gia cộng đồng hiến máu nhân đạo</p>
                </div>
            </section>

            <main className="auth-main">
                <div className="auth-container">
                    <div className="auth-tabs">
                        <button 
                            className={isLogin ? 'active' : ''}
                            onClick={() => setIsLogin(true)}
                        >
                            Đăng nhập
                        </button>
                        <button 
                            className={!isLogin ? 'active' : ''}
                            onClick={() => setIsLogin(false)}
                        >
                            Đăng ký
                        </button>
                    </div>

                    <form className="auth-form" onSubmit={handleSubmit}>
                        {!isLogin && (
                            <div className="auth-form-group">
                                <label>Họ và tên</label>
                                <input 
                                    type="text" 
                                    name="fullName"
                                    value={formData.fullName}
                                    onChange={handleChange}
                                    required 
                                />
                            </div>
                        )}

                        <div className="auth-form-group">
                            <label>Email</label>
                            <input 
                                type="email" 
                                name="email"
                                value={formData.email}
                                onChange={handleChange}
                                required 
                            />
                        </div>

                        {!isLogin && (
                            <div className="auth-form-group">
                                <label>Số điện thoại</label>
                                <input 
                                    type="tel" 
                                    name="phone"
                                    value={formData.phone}
                                    onChange={handleChange}
                                    required 
                                />
                            </div>
                        )}

                        <div className="auth-form-group">
                            <label>Mật khẩu</label>
                            <input 
                                type="password" 
                                name="password"
                                value={formData.password}
                                onChange={handleChange}
                                required 
                            />
                        </div>

                        {!isLogin && (
                            <div className="auth-form-group">
                                <label>Xác nhận mật khẩu</label>
                                <input 
                                    type="password" 
                                    name="confirmPassword"
                                    value={formData.confirmPassword}
                                    onChange={handleChange}
                                    required 
                                />
                            </div>
                        )}

                        {isLogin && (
                            <div className="auth-form-options">
                                <label className="auth-checkbox">
                                    <input type="checkbox" />
                                    Ghi nhớ đăng nhập
                                </label>
                                <a href="#" className="auth-forgot">Quên mật khẩu?</a>
                            </div>
                        )}

                        <button type="submit" className="auth-submit-btn">
                            {isLogin ? 'Đăng nhập' : 'Đăng ký'}
                        </button>

                    </form>

                    <div className="auth-benefits">
                        <h3>Lợi ích khi đăng ký tài khoản</h3>
                        <ul>
                            <li>✅ Theo dõi lịch sử hiến máu</li>
                            <li>✅ Nhận thông báo về các điểm hiến máu gần bạn</li>
                            <li>✅ Tham gia cộng đồng hiến máu</li>
                            <li>✅ Lưu trữ thông tin cá nhân an toàn</li>
                            <li>✅ Nhận các ưu đãi đặc biệt</li>
                        </ul>
                    </div>
                </div>
            </main>
        </div>
    );
};

export default Register;
