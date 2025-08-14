import React, { useState } from 'react';
import './NavBar.css';
import heroImage from '../../assets/hero-image.png'


function NavBar() {
    const [isOpen, setIsOpen] = useState(false);
    const toggleMenu = () => {
        setIsOpen(!isOpen);
    };


    return (
        <nav className='navbar-container'>
            <div className='hero-image'>
                <img src={heroImage} alt="The League Standard" className='hero-img'/>
            </div>

            <div className='mobile-nav-icons d-lg-none'>
                <button className='hamburger-btn' onClick={toggleMenu} aria-label='Toggle navigation'>
                    <span className={`hamburger-line ${isOpen ? 'open' : ''}`}></span>
                    <span className={`hamburger-line ${isOpen ? 'open' : ''}`}></span>
                    <span className={`hamburger-line ${isOpen ? 'open' : ''}`}></span>
                </button>

                <div className="chart-icon">
                    <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path d="M3 3V21H21" stroke="black" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"/>
                        <path d="M9 9L12 6L16 10L20 6" stroke="black" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"/>
                    </svg>
                </div>
            </div>


            <div className='title-section'>
                <h1 className='main-title'>The League Standard</h1>
                <p className='tagline'>this ain't your daddy's basketball</p>
            </div>


            <div className="social-icons d-none d-lg-flex">
                <a href="#" className="social-link">
                <svg width="24" height="24" viewBox="0 0 24 24" fill="currentColor">
                    <path d="M24 12.073c0-6.627-5.373-12-12-12s-12 5.373-12 12c0 5.99 4.388 10.954 10.125 11.854v-8.385H7.078v-3.47h3.047V9.43c0-3.007 1.792-4.669 4.533-4.669 1.312 0 2.686.235 2.686.235v2.953H15.83c-1.491 0-1.956.925-1.956 1.874v2.25h3.328l-.532 3.47h-2.796v8.385C19.612 23.027 24 18.062 24 12.073z"/>
                </svg>
                </a>
                <a href="#" className="social-link">
                <svg width="24" height="24" viewBox="0 0 24 24" fill="currentColor">
                    <path d="M12.017 0C5.396 0 .029 5.367.029 11.987c0 5.079 3.158 9.417 7.618 11.024-.105-.949-.199-2.403.041-3.439.219-.937 1.406-5.957 1.406-5.957s-.359-.72-.359-1.781c0-1.663.967-2.911 2.168-2.911 1.024 0 1.518.769 1.518 1.688 0 1.029-.653 2.567-.992 3.992-.285 1.193.6 2.165 1.775 2.165 2.128 0 3.768-2.245 3.768-5.487 0-2.861-2.063-4.869-5.008-4.869-3.41 0-5.409 2.562-5.409 5.199 0 1.033.394 2.143.889 2.741.099.12.112.225.085.345-.09.375-.293 1.199-.334 1.363-.053.225-.172.271-.402.165-1.495-.69-2.433-2.878-2.433-4.646 0-3.776 2.748-7.252 7.92-7.252 4.158 0 7.392 2.967 7.392 6.923 0 4.135-2.607 7.462-6.233 7.462-1.214 0-2.357-.629-2.746-1.378l-.749 2.848c-.269 1.045-1.004 2.352-1.498 3.146 1.123.345 2.306.535 3.55.535 6.624 0 11.99-5.367 11.99-11.987C24.007 5.367 18.641.001 12.017.001z"/>
                </svg>
                </a>
                <a href="#" className="social-link">
                <svg width="24" height="24" viewBox="0 0 24 24" fill="currentColor">
                    <path d="M23.498 6.186a3.016 3.016 0 0 0-2.122-2.136C19.505 3.545 12 3.545 12 3.545s-7.505 0-9.377.505A3.017 3.017 0 0 0 .502 6.186C0 8.07 0 12 0 12s0 3.93.502 5.814a3.016 3.016 0 0 0 2.122 2.136c1.871.505 9.376.505 9.376.505s7.505 0 9.377-.505a3.015 3.015 0 0 0 2.122-2.136C24 15.93 24 12 24 12s0-3.93-.502-5.814zM9.545 15.568V8.432L15.818 12l-6.273 3.568z"/>
                </svg>
                </a>
                <a href="#" className="social-link">
                <svg width="24" height="24" viewBox="0 0 24 24" fill="currentColor">
                    <path d="M20.447 20.452h-3.554v-5.569c0-1.328-.027-3.037-1.852-3.037-1.853 0-2.136 1.445-2.136 2.939v5.667H9.351V9h3.414v1.561h.046c.477-.9 1.637-1.85 3.37-1.85 3.601 0 4.267 2.37 4.267 5.455v6.286zM5.337 7.433c-1.144 0-2.063-.926-2.063-2.065 0-1.138.92-2.063 2.063-2.063 1.14 0 2.064.925 2.064 2.063 0 1.139-.925 2.065-2.064 2.065zm1.782 13.019H3.555V9h3.564v11.452zM22.225 0H1.771C.792 0 0 .774 0 1.729v20.542C0 23.227.792 24 1.771 24h20.451C23.2 24 24 23.227 24 22.271V1.729C24 .774 23.2 0 22.222 0h.003z"/>
                </svg>
                </a>
            </div>

            <div className="nav-links-container">
                {/* Desktop Navigation */}
                <ul className="nav-links desktop-nav d-none d-lg-flex">
                    <li><a href="/" className="nav-link">Home</a></li>
                    <li><a href="/standings" className="nav-link">Standings</a></li>
                    <li><a href="/games" className="nav-link">Games</a></li>
                    <li><a href="/teams" className="nav-link">Teams</a></li>
                    <li><a href="/players" className="nav-link">Players</a></li>
                </ul>

                {/* Mobile Navigation */}
                <div className={`mobile-nav d-lg-none ${isOpen ? 'active' : ''}`}>
                <ul className="mobile-nav-links">
                    <li><a href="/" className="mobile-nav-link" onClick={toggleMenu}>Home</a></li>
                    <li><a href="/standings" className="mobile-nav-link" onClick={toggleMenu}>Standings</a></li>
                    <li><a href="/games" className="mobile-nav-link" onClick={toggleMenu}>Games</a></li>
                    <li><a href="/teams" className="mobile-nav-link" onClick={toggleMenu}>Teams</a></li>
                    <li><a href="/players" className="mobile-nav-link" onClick={toggleMenu}>Players</a></li>
                </ul>
                </div>
            </div>

            {/* Mobile Overlay */}
            <div 
                className={`mobile-overlay d-lg-none ${isOpen ? 'active' : ''}`}
                onClick={toggleMenu}></div>

        </nav>
    );
}

export default NavBar;