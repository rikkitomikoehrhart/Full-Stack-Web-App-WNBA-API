

function HamburgerButton({ isOpen, toggleMenu }) {
    return (
        <>
            <button className='hamburger-btn' onClick={toggleMenu} aria-label='Toggle navigation'>
                <span className={`hamburger-line ${isOpen ? 'open' : ''}`}></span>
                <span className={`hamburger-line ${isOpen ? 'open' : ''}`}></span>
                <span className={`hamburger-line ${isOpen ? 'open' : ''}`}></span>
            </button>
        </>
    );
}

export default HamburgerButton;