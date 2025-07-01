import React from 'react';

const Blog = () => {
    const blogPosts = [
        {
            id: 1,
            title: 'Lần đầu hiến máu – Trải nghiệm đáng nhớ',
            author: 'Nguyễn Văn A',
            date: '21/05/2024',
            category: 'Chia sẻ',
            excerpt: 'Tôi vẫn nhớ như in cảm giác hồi hộp trong lần đầu tiên hiến máu. Từ một người sợ kim tiêm, tôi đã trở thành người hiến máu thường xuyên...',
            image: '/assets/img/blog-1.jpg'
        },
        {
            id: 2,
            title: 'Sức khỏe khi hiến máu định kỳ',
            author: 'Trần Thị B',
            date: '15/05/2024',
            category: 'Sức khỏe',
            excerpt: 'Nhiều người quan tâm về tác động của việc hiến máu định kỳ đến sức khỏe. Qua 5 năm hiến máu, tôi muốn chia sẻ những trải nghiệm thực tế...',
            image: '/assets/img/blog-2.jpg'
        },
        {
            id: 3,
            title: 'Hiến máu nhóm hiếm – Câu chuyện của tôi',
            author: 'Lê Văn C',
            date: '08/05/2024',
            category: 'Chia sẻ',
            excerpt: 'Với nhóm máu AB(-), tôi hiểu được tầm quan trọng của việc hiến máu. Mỗi lần hiến máu là một lần tôi có thể cứu sống người khác...',
            image: '/assets/img/blog-3.jpg'
        },
        {
            id: 4,
            title: 'Tình nguyện viên tại trung tâm hiến máu',
            author: 'Phạm Thị D',
            date: '05/05/2024',
            category: 'Tình nguyện',
            excerpt: 'Là một tình nguyện viên tại trung tâm hiến máu, tôi được chứng kiến những câu chuyện cảm động về lòng nhân ái của con người...',
            image: '/assets/img/blog-4.jpg'
        }
    ];

    return (
        <div>
            {/* Banner */}
            <section className="blog-banner">
                <div className="blog-banner-content">
                    <h1>Blog Hiến Máu</h1>
                    <p>Chia sẻ kinh nghiệm, kiến thức và câu chuyện về hiến máu nhân đạo</p>
                </div>
            </section>

            <main className="blog-main">
                {/* Header với filter */}
                <div className="blog-header">
                    <h2>Bài viết nổi bật</h2>
                    <div className="blog-categories">
                        <button className="blog-category-btn active">Tất cả</button>
                        <button className="blog-category-btn">Kiến thức</button>
                        <button className="blog-category-btn">Chia sẻ</button>
                        <button className="blog-category-btn">Sức khỏe</button>
                        <button className="blog-category-btn">Tình nguyện</button>
                    </div>
                </div>

                {/* Blog featured */}
                <div className="blog-posts">
                    <article className="blog-post-card">
                        <div className="blog-post-image">
                            <span>📝 Bài viết nổi bật</span>
                        </div>
                        <div className="blog-post-content">
                            <div className="blog-post-meta">
                                <span className="blog-category-tag">Kiến thức</span>
                                <span className="blog-post-date">25/06/2024</span>
                            </div>
                            <h3 className="blog-post-title">10 điều cần biết trước khi hiến máu lần đầu</h3>
                            <div className="blog-post-author">Bác sĩ Nguyễn Minh</div>
                            <p className="blog-post-excerpt">Hướng dẫn chi tiết cho những người mới bắt đầu tham gia hiến máu, từ chuẩn bị tinh thần đến chăm sóc sau hiến máu...</p>
                            <a href="#" className="blog-read-more">Đọc tiếp →</a>
                        </div>
                    </article>

                    {blogPosts.map(post => (
                        <article key={post.id} className="blog-post-card">
                            <div className="blog-post-image">
                                <span>📄 {post.category}</span>
                            </div>
                            <div className="blog-post-content">
                                <div className="blog-post-meta">
                                    <span className={`blog-category-tag ${post.category === 'Chia sẻ' ? 'share' : post.category === 'Sức khỏe' ? 'health' : post.category === 'Tình nguyện' ? 'volunteer' : ''}`}>
                                        {post.category}
                                    </span>
                                    <span className="blog-post-date">{post.date}</span>
                                </div>
                                <h3 className="blog-post-title">{post.title}</h3>
                                <div className="blog-post-author">{post.author}</div>
                                <p className="blog-post-excerpt">{post.excerpt}</p>
                                <a href="#" className="blog-read-more">Đọc tiếp →</a>
                            </div>
                        </article>
                    ))}
                </div>

                {/* Sidebar */}
                <div className="blog-sidebar">
                    <h3>Bài viết liên quan</h3>
                    <ul>
                        <li><a href="#">Chế độ ăn uống sau hiến máu</a></li>
                        <li><a href="#">Hiến máu và COVID-19: Những điều cần biết</a></li>
                        <li><a href="#">Tại sao nên hiến máu định kỳ?</a></li>
                        <li><a href="#">Quy trình hiến máu an toàn</a></li>
                        <li><a href="#">Những lợi ích của việc hiến máu</a></li>
                    </ul>
                </div>
            </main>
        </div>
    );
};

export default Blog;
