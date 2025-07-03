import React from 'react';
import { useNavigate } from 'react-router-dom';

const ErrorPage = ({ error }) => {
    const navigate = useNavigate();

    const handleGoHome = () => {
        navigate('/');
    };

    return (
        <div className="min-h-screen bg-gray-100 flex flex-col items-center justify-center p-4">
            <div className="max-w-md w-full bg-white rounded-lg shadow-xl p-8 text-center">
                <div className="mb-8">
                    <svg className="mx-auto h-16 w-16 text-red-500" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                        <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M12 9v2m0 4h.01m-6.938 4h13.856c1.54 0 2.502-1.667 1.732-3L13.732 4c-.77-1.333-2.694-1.333-3.464 0L3.34 16c-.77 1.333.192 3 1.732 3z" />
                    </svg>
                </div>
                <h1 className="text-3xl font-bold text-gray-800 mb-4">
                    Oops! Đã xảy ra lỗi
                </h1>
                <p className="text-gray-600 mb-8">
                    Chúng tôi xin lỗi vì sự bất tiện này. Vui lòng thử lại sau.
                </p>
                {error && (
                    <p className="text-sm text-gray-500 mb-8">
                        Chi tiết lỗi: {error.message}
                    </p>
                )}
                <button
                    onClick={handleGoHome}
                    className="bg-red-500 hover:bg-red-600 text-white font-semibold py-2 px-6 rounded-lg transition duration-200"
                >
                    Về Trang Chủ
                </button>
            </div>
        </div>
    );
};

export default ErrorPage;
