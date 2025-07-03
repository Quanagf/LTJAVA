import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Header from './components/Header';
import Footer from './components/Footer';
import Home from './components/Home';
import Donate from './components/Donate';
import FindLocation from './components/FindLocation';
import Blog from './components/Blog';
import Register from './components/Register';
import ErrorBoundary from './components/ErrorBoundary';
import ErrorPage from './components/ErrorPage';
import './App.css';

function App() {
  return (
    <ErrorBoundary>
      <Router>
        <div className="App">
          <Header />
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/donate" element={<Donate />} />
            <Route path="/find-location" element={<FindLocation />} />
            <Route path="/blog" element={<Blog />} />
            <Route path="/register" element={<Register />} />
            <Route path="/error" element={<ErrorPage />} />
            <Route path="*" element={<ErrorPage />} />
          </Routes>
          <Footer />
        </div>
      </Router>
    </ErrorBoundary>
  );
}

export default App;
