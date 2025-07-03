import React from 'react';
import { Link, useLocation } from 'react-router-dom';

const Header = () => {
    const location = useLocation();

    const isActive = (path) => {
        return location.pathname === path ? 'active' : '';
    };

    return (
        <header>
            <nav className="navbar">
                <div className="logo">
                    <img src="/assets/img/logo.png" alt="Logo" height="40" />
                    <span><b>HỖ TRỢ HIẾN MÁU</b></span>
                </div>
                <ul className="nav-links">
                    <li><Link to="/" className={isActive('/')}>Trang Chủ</Link></li>
                    <li><Link to="/donate" className={isActive('/donate')}>Đăng ký hiến máu</Link></li>
                    <li><Link to="/find-location" className={isActive('/find-location')}>Tìm điểm hiến máu</Link></li>
                    <li><Link to="/blog" className={isActive('/blog')}>Blog</Link></li>
                </ul>
                <ul className="nav-links">
                    <li><Link to="/register" className={isActive('/register')}>Đăng nhập/Đăng ký</Link></li>
                </ul>
            </nav>
        </header>
    );
};

export default Header;
