import React, { useState } from 'react';
import './NavBar.css';
import HeroImage from './Navbar/HeroImage';
import HamburgerButton from './Navbar/HamburgerButton';
import ChartButton from './Navbar/ChartButton';
import TitleSection from './Navbar/TitleSection';
import SocialIcons from './Navbar/SocialIcons';
import DesktopLinks from './Navbar/DesktopLinks';
import MobileLinks from './Navbar/MobileLinks';


function NavBar() {
    const [isOpen, setIsOpen] = useState(false);
    const toggleMenu = () => {
        setIsOpen(!isOpen);
    };


    return (
        <nav className='navbar-container'>
            <HeroImage />

            <div className='mobile-nav-icons d-lg-none'>
                <HamburgerButton isOpen={isOpen} toggleMenu={toggleMenu} />
                <ChartButton />
            </div>

            <TitleSection />

            <SocialIcons />


            <div className="nav-links-container">
                <DesktopLinks />
                <MobileLinks isOpen={isOpen} toggleMenu={toggleMenu} />
            </div>

            {/* Mobile Overlay */}
            <div className={`mobile-overlay d-lg-none ${isOpen ? 'active' : ''}`} onClick={toggleMenu}></div>

        </nav>
    );
}

export default NavBar;