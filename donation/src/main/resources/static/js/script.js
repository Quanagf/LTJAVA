// script.js
document.getElementById('donationForm').addEventListener('submit', function(e) {
    e.preventDefault();

    const fullName = document.getElementById('full_name').value;
    const phone = document.getElementById('phone').value;
    const bloodType = document.getElementById('blood_type').value;
    const province = document.getElementById('province').value;

    if (fullName && phone && bloodType && province) {
        alert(`Cảm ơn bạn ${fullName} đã đăng ký hiến máu. Nhóm máu của bạn là ${bloodType}.`);
    } else {
        alert('Vui lòng điền đầy đủ thông tin!');
    }
});

.medical-center-card {
  border: 1px solid #ddd;
  padding: 16px;
  margin-bottom: 20px;
  border-radius: 8px;
  background-color: #fafafa;
  box-shadow: 0 2px 6px rgba(0,0,0,0.05);
}
.medical-center-card h3 {
  margin-top: 0;
  color: #b30000;
}
