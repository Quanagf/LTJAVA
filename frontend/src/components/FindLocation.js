import React, { useState } from 'react';
import { Link } from 'react-router-dom';

const FindLocation = () => {
    const [searchTerm, setSearchTerm] = useState('');
    const [selectedDistrict, setSelectedDistrict] = useState('');
    const [activeFilter, setActiveFilter] = useState('Tất cả');

    const locations = [
        {
            id: 1,
            name: 'Bệnh viện Bình Dân',
            type: 'Bệnh viện',
            address: '371 Điện Biên Phủ, Phường 4, Quận 3, TP.HCM',
            distance: '3.1 km',
            hours: '08:00 - 17:00',
            phone: '028 3839 4747'
        },
        {
            id: 2,
            name: 'Bệnh viện Chợ Rẫy',
            type: 'Bệnh viện',
            address: '201B Nguyễn Chí Thanh, Quận 5, TP.HCM',
            distance: '2.5 km',
            hours: '07:00 - 16:00',
            phone: '028 3855 4137'
        },
        {
            id: 3,
            name: 'Bệnh viện Da khoa Quốc tế Vinmec',
            type: 'Bệnh viện',
            address: '208 Nguyễn Hữu Cảnh, Quận Bình Thạnh, TP.HCM',
            distance: '3.0 km',
            hours: '08:00 - 17:30',
            phone: '028 3622 1166'
        },
        {
            id: 4,
            name: 'Viện Huyết học - Truyền máu',
            type: 'Trung tâm hiến máu',
            address: '118 Hồng Bàng, Phường 12, Quận 5, TP.HCM',
            distance: '3.1 km',
            hours: '07:30 - 16:00',
            phone: '028 3957 1342'
        },
        {
            id: 5,
            name: 'Bệnh viện Đại học Y Dược',
            type: 'Bệnh viện',
            address: '215 Hồng Bàng, Phường 11, Quận 5, TP.HCM',
            distance: '3.8 km',
            hours: '07:00 - 16:00',
            phone: '028 3855 4269'
        }
    ];

    const handleSearch = (e) => {
        e.preventDefault();
        console.log('Searching for:', searchTerm, selectedDistrict);
    };

    const filteredLocations = locations.filter(location => {
        if (activeFilter === 'Tất cả') return true;
        return location.type === activeFilter;
    });

    return (
        <main className="find-main">
            <h1 className="find-title">Tìm điểm hiến máu gần bạn</h1>
            
            <form className="find-search-bar" onSubmit={handleSearch}>
                <input 
                    type="text" 
                    placeholder="Nhập địa chỉ hoặc khu vực của bạn"
                    value={searchTerm}
                    onChange={(e) => setSearchTerm(e.target.value)}
                />
                <select 
                    value={selectedDistrict}
                    onChange={(e) => setSelectedDistrict(e.target.value)}
                >
                    <option value="">Chọn quận/huyện</option>
                    <option value="quan1">Quận 1</option>
                    <option value="quan3">Quận 3</option>
                    <option value="quan5">Quận 5</option>
                    <option value="binhthanh">Bình Thạnh</option>
                </select>
                <button type="submit" className="find-btn-search">Tìm kiếm</button>
            </form>

            <div className="find-filter-bar">
                {['Tất cả', 'Bệnh viện', 'Trung tâm hiến máu', 'Điểm hiến máu lưu động'].map(filter => (
                    <button 
                        key={filter}
                        className={activeFilter === filter ? 'active' : ''}
                        onClick={() => setActiveFilter(filter)}
                    >
                        {filter}
                    </button>
                ))}
            </div>

            {/* Danh sách điểm hiến máu */}
            <section className="find-location-list">
                {filteredLocations.map(location => (
                    <div key={location.id} className="find-card">
                        <div className="find-card-header">
                            <div className="find-card-title">{location.name}</div>
                            <span className="find-distance">{location.distance}</span>
                        </div>
                        <div className={`find-card-type ${location.type === 'Bệnh viện' ? 'hospital' : ''}`}>
                            {location.type}
                        </div>
                        <div className="find-card-address">{location.address}</div>
                        <div className="find-card-info">
                            <span>🕒 {location.hours}</span>
                            <span>📞 {location.phone}</span>
                        </div>
                        <div className="find-card-actions">
                            <a href="#" className="btn-outline">Xem chi tiết</a>
                            <Link to="/donate" className="btn-primary">Đăng ký hiến máu</Link>
                        </div>
                    </div>
                ))}
            </section>

            {/* Thông tin hữu ích */}
            <section className="find-infor-box">
                <h2>Thông tin hữu ích</h2>
                <div className="find-infor-list">
                    <div className="find-infor-item">
                        <div className="find-infor-icon">❓</div>
                        <div>
                            <b>Điều kiện hiến máu</b>
                            <p>Tìm hiểu các yêu cầu cần thiết để có thể hiến máu an toàn</p>
                        </div>
                    </div>
                    <div className="find-infor-item">
                        <div className="find-infor-icon">⭐</div>
                        <div>
                            <b>Quyền lợi người hiến máu</b>
                            <p>Các quyền lợi và chế độ dành cho người tham gia hiến máu</p>
                        </div>
                    </div>
                    <div className="find-infor-item">
                        <div className="find-infor-icon">📖</div>
                        <div>
                            <b>Quy trình hiến máu</b>
                            <p>Các bước thực hiện và thời gian cần thiết khi hiến máu</p>
                        </div>
                    </div>
                </div>
            </section>
        </main>
    );
};

export default FindLocation;
